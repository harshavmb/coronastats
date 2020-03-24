/**
 * 
 */
package com.coronastats.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coronastats.model.CoronaStats;
import com.coronastats.model.CountryNinja;
import com.coronastats.model.Covid19Stats;
import com.coronastats.model.CumulativeCases;
import com.coronastats.service.CoronaStatsService;

/**
 * @author harshavmb
 *
 */
@Controller
public class WebCoronaController {

	@Autowired
	private CoronaStatsService coronaStatsService;

	Set<String> countries = new HashSet<String>();

	@GetMapping("/index")
	public String greeting(Model model) throws Exception {
		//formatResponseForAllCountries(model, coronaStatsService.runService());
		List<Covid19Stats> countriesData = new ArrayList<Covid19Stats>();
		List<Covid19Stats> covid19Stats = coronaStatsService.runService().getBody().getData().getCovid19Stats();
		Set<String> listAllCountries = listAllCountries(covid19Stats);
		for (String s : listAllCountries) {
			countriesData.add(formatResponseByCountry(covid19Stats, s));
		}

		setDataForIndex(model, countriesData);
		return "index";
	}
	
	@GetMapping("/ninjaindex")
	public String index(Model model) throws Exception {
		List<CountryNinja> countriesData = new ArrayList<CountryNinja>();
		CountryNinja[] ninjaCountries = coronaStatsService.runNinjaService();
		Set<String> listAllNinjaCountries = listAllNinjaCountries(ninjaCountries);
		for (String s : listAllNinjaCountries) {
			countriesData.add(formatResponseByNinjaCountry(ninjaCountries, s));
		}
		
		setDataForNinjaIndex(model, countriesData, coronaStatsService.runCumulativeService());
		return "index";
	}

	@RequestMapping("/")
	public String allCountries(Model model) throws Exception {
		List<Covid19Stats> countriesData = new ArrayList<Covid19Stats>();
		List<Covid19Stats> covid19Stats = coronaStatsService.runService().getBody().getData().getCovid19Stats();
		Set<String> listAllCountries = listAllCountries(covid19Stats);
		for (String s : listAllCountries) {
			countriesData.add(formatResponseByCountry(covid19Stats, s));
		}

		setDataForIndex(model, countriesData);

		return "index";
	}

	@RequestMapping("/country")
	public String byCountry(Model model, @RequestParam String country) throws Exception {
		formatResponse(model, coronaStatsService.runServiceByCountry(country), country);
		return "country";
	}
	
	@RequestMapping("/countries")
	public String byNinjaCountry(Model model, @RequestParam String country) throws Exception {
		formatNinjaResponse(model, coronaStatsService.runNinjaServiceByCountry(country), country);
		return "country";
	}

	@RequestMapping("/tables")
	public String byTable(Model model) throws Exception {
		formatResponseForTables(model, coronaStatsService.runService());
		return "tables";
	}
	
	@RequestMapping("/ninjatables")
	public String byNinjaTable(Model model) throws Exception {
		formatResponseForNinjaTables(model, coronaStatsService.runNinjaService());
		return "ninjatables";
	}
	
	@RequestMapping("/about")
	public String about() throws Exception {
		return "about";
	}
	
	@RequestMapping("/contact")
	public String contact() throws Exception {
		return "contact";
	}

	private Model formatResponse(Model model, ResponseEntity<CoronaStats> response, String country) throws Exception {
		Double total_confirmed = 0.0;
		Double total_deaths = 0.0;
		Double total_recoveries = 0.0;
		for (Covid19Stats c : response.getBody().getData().getCovid19Stats()) {
			if (c.getCountry().equals(country)) {
				total_confirmed = total_confirmed + c.getConfirmed();
				total_deaths = total_deaths + c.getDeaths();
				total_recoveries = total_recoveries + c.getRecovered();
			}
		}
		model.addAttribute("total", (int) Math.round(total_confirmed));
		model.addAttribute("deaths", (int) Math.round(total_deaths));
		model.addAttribute("recovered", (int) Math.round(total_recoveries));
		DecimalFormat df = new DecimalFormat("#.##");
		Double fatality_number = Double.valueOf(total_deaths / total_confirmed) * 100;
		model.addAttribute("fatality", df.format(fatality_number));
		model.addAttribute("country", country);
		return model;
	}
	
	private Model formatNinjaResponse(Model model, CountryNinja ninjaCountry, String country) throws Exception {
		model.addAttribute("total", ninjaCountry.getCases());
		model.addAttribute("deaths", ninjaCountry.getDeaths());
		model.addAttribute("recovered", ninjaCountry.getRecovered());
		DecimalFormat df = new DecimalFormat("#.##");
		Double fatality_number = (Double.valueOf(ninjaCountry.getDeaths()) / ninjaCountry.getCases()) * 100;
		model.addAttribute("fatality", df.format(fatality_number));
		model.addAttribute("country", country);
		return model;
	}

	private Model formatResponseForTables(Model model, ResponseEntity<CoronaStats> response) throws Exception {
		List<Covid19Stats> covid19Stats = response.getBody().getData().getCovid19Stats();
		List<Covid19Stats> countriesData = new ArrayList<Covid19Stats>();
		for (Covid19Stats c : covid19Stats) {
			Covid19Stats cs = new Covid19Stats();
			cs.setCountry(c.getCountry());
			cs.setConfirmed(c.getConfirmed());
			cs.setDeaths(c.getDeaths());
			cs.setRecovered(c.getRecovered());
			cs.setProvince(c.getProvince());
			cs.setLastUpdate(c.getLastUpdate());
			countriesData.add(cs);
		}
		model.addAttribute("countriesData", countriesData);
		return model;
	}
	
	private Model formatResponseForNinjaTables(Model model, CountryNinja[] countries) throws Exception {
		List<CountryNinja> countriesDataTable = new ArrayList<CountryNinja>();
		for (CountryNinja c : countries) {
			CountryNinja cn = new CountryNinja();
			cn.setCountry(c.getCountry());
			cn.setCases(c.getCases());
			cn.setTodayCases(c.getTodayCases());
			cn.setDeaths(c.getDeaths());
			cn.setTodayDeaths(c.getTodayDeaths());
			cn.setRecovered(c.getRecovered());
			cn.setActive(c.getActive());
			cn.setCritical(c.getCritical());
			cn.setCasesPerOneMillion(c.getCasesPerOneMillion());
			countriesDataTable.add(cn);
		}
		model.addAttribute("countriesData", countriesDataTable);
		return model;
	}

	private Set<String> listAllCountries(List<Covid19Stats> covid19Stats) {
		for (Covid19Stats covid19Stats2 : covid19Stats) {
			countries.add(covid19Stats2.getCountry());
		}
		return countries;
	}
	
	private Set<String> listAllNinjaCountries(CountryNinja[] ninjaCountries) {
		Set<String> ninjaCountries2 = new HashSet<String>();
		for (CountryNinja c : ninjaCountries) {
			ninjaCountries2.add(c.getCountry());
		}
		return ninjaCountries2;
	}

	private List<Covid19Stats> sortByCofirmed(List<Covid19Stats> covid19Stats) {
		return covid19Stats.stream().sorted(Comparator.comparing(Covid19Stats::getConfirmed).reversed())
				.collect(Collectors.toList());
	}
	
	private List<CountryNinja> sortByNinjaConfirmed(List<CountryNinja> countriesData2) {
		return countriesData2.stream().sorted(Comparator.comparing(CountryNinja::getCases).reversed())
				.collect(Collectors.toList());
	}
	
	private List<Covid19Stats> sortByFatalities(List<Covid19Stats> covid19Stats) {
		return covid19Stats.stream().sorted(Comparator.comparing(Covid19Stats::getDeaths).reversed())
				.collect(Collectors.toList());
	}
	
	private List<CountryNinja> sortByNinjaFatalities(List<CountryNinja> countriesData2) {
		return countriesData2.stream().sorted(Comparator.comparing(CountryNinja::getDeaths).reversed())
				.collect(Collectors.toList());
	}

	private Covid19Stats formatResponseByCountry(List<Covid19Stats> covid19Stats, String country) throws Exception {
		Double total_confirmed = 0.0;
		Double total_deaths = 0.0;
		Double total_recoveries = 0.0;
		Covid19Stats cs = new Covid19Stats();
		for (Covid19Stats c : covid19Stats) {
			if (c.getCountry().equals(country)) {
				total_confirmed = total_confirmed + c.getConfirmed();
				total_deaths = total_deaths + c.getDeaths();
				total_recoveries = total_recoveries + c.getRecovered();
			}
		}
		cs.setConfirmed(total_confirmed);
		cs.setDeaths(total_deaths);
		cs.setRecovered(total_recoveries);
		cs.setCountry(country);
		return cs;
	}
	
	private CountryNinja formatResponseByNinjaCountry(CountryNinja[] listAllNinjaCountries, String country) throws Exception {
		int total_confirmed = 0;
		int total_deaths = 0;
		int total_recoveries = 0;
		CountryNinja cn = new CountryNinja();
		for (CountryNinja c : listAllNinjaCountries) {
			if (c.getCountry().equals(country)) {
				total_confirmed = total_confirmed + c.getCases();
				total_deaths = total_deaths + c.getDeaths();
				total_recoveries = total_recoveries + c.getRecovered();
			}
		}
		cn.setCases(total_confirmed);
		cn.setDeaths(total_deaths);
		cn.setRecovered(total_recoveries);
		cn.setCountry(country);
		return cn;
	}

	private Model setDataForIndex(Model model, List<Covid19Stats> countriesData2) {

		DecimalFormat df = new DecimalFormat("#.##");
		Double total_confirmed = 0.0;
		Double total_deaths = 0.0;
		Double total_recoveries = 0.0;
		for (Covid19Stats covid19Stat : countriesData2) {
			total_confirmed = total_confirmed + covid19Stat.getConfirmed();
			total_deaths = total_deaths + covid19Stat.getDeaths();
			total_recoveries = total_recoveries + covid19Stat.getRecovered();
		}
		model.addAttribute("total", Math.round(total_confirmed));
		model.addAttribute("deaths", Math.round(total_deaths));
		model.addAttribute("recovered", Math.round(total_recoveries));
		Double fatality_number = Double.valueOf(total_deaths / total_confirmed) * 100;
		model.addAttribute("fatality", df.format(fatality_number));
		model.addAttribute("worstHitCountries", sortByCofirmed(countriesData2).subList(0, 10).stream()
				.map(Covid19Stats::getCountry).collect(Collectors.toList()));
		model.addAttribute("confirmedCases", sortByCofirmed(countriesData2).subList(0, 10).stream()
				.map(Covid19Stats::getConfirmed).collect(Collectors.toList()));
		model.addAttribute("worstFatalityCountries", sortByFatalities(countriesData2).subList(0, 5).stream()
				.map(Covid19Stats::getCountry).collect(Collectors.toList()));
		model.addAttribute("fatalities",sortByFatalities(countriesData2).subList(0, 5).stream()
				.map(Covid19Stats::getDeaths).collect(Collectors.toList()));
		return model;

	}
	
	private Model setDataForNinjaIndex(Model model, List<CountryNinja> countriesData2, CumulativeCases cumulativeCases) {

		DecimalFormat df = new DecimalFormat("#.##");
		model.addAttribute("total", cumulativeCases.getCases());
		model.addAttribute("deaths", cumulativeCases.getDeaths());
		model.addAttribute("recovered", cumulativeCases.getRecovered());
		model.addAttribute("updated", cumulativeCases.getUpdated());
		Double fatality_number = (Double.valueOf(cumulativeCases.getDeaths()) / Double.valueOf(cumulativeCases.getCases())) * 100;
		model.addAttribute("fatality", df.format(fatality_number));
		model.addAttribute("worstHitCountries", sortByNinjaConfirmed(countriesData2).subList(0, 10).stream()
				.map(CountryNinja::getCountry).collect(Collectors.toList()));
		model.addAttribute("confirmedCases", sortByNinjaConfirmed(countriesData2).subList(0, 10).stream()
				.map(CountryNinja::getCases).collect(Collectors.toList()));
		model.addAttribute("worstFatalityCountries", sortByNinjaFatalities(countriesData2).subList(0, 5).stream()
				.map(CountryNinja::getCountry).collect(Collectors.toList()));
		model.addAttribute("fatalities",sortByNinjaFatalities(countriesData2).subList(0, 5).stream()
				.map(CountryNinja::getDeaths).collect(Collectors.toList()));
		return model;

	}

}

