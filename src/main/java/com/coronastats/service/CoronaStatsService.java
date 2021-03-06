/**
 * 
 */
package com.coronastats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.coronastats.app.ConsumeRapidApi;
import com.coronastats.model.CoronaStats;
import com.coronastats.model.CountryNinja;
import com.coronastats.model.CumulativeCases;
import com.coronastats.model.Historical;
import com.coronastats.model.HistoricalCases;

/**
 * @author harshavmb
 *
 */
@Service
public class CoronaStatsService {

	private static final Logger log = LoggerFactory.getLogger(ConsumeRapidApi.class);

	private final String url;

	private final String host;

	private final String host_key;
	
	private final String ninjaHost;
	
	private final String ninjaHostv2;

	@Autowired
	public CoronaStatsService(@Value("${rapidapi.url}") String url, @Value("${rapidapi.host}") String host,
			@Value("${rapiapi.key}") String host_key,@Value("${ninja.api}") String ninjaHost,@Value("${ninja.api.v2}") String ninjaHostv2) {
		this.url = url;
		this.host = host;
		this.host_key = host_key;
		this.ninjaHost = ninjaHost;
		this.ninjaHostv2 = ninjaHostv2;
	}
	
	private HttpHeaders setHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-rapidapi-host", host);
		headers.set("x-rapidapi-key", host_key);
		return headers;
	}
	
	private HttpHeaders setHeadersForNinja() {
	  HttpHeaders headers = new HttpHeaders();
	  headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	  return headers;	
	}

	RestTemplate restTemplate = new RestTemplate();

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<CoronaStats> runService() throws Exception {
		HttpEntity<CoronaStats> requestEntity = new HttpEntity<CoronaStats>(setHeaders());
		ResponseEntity<CoronaStats> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				CoronaStats.class);
		log.info(response.toString());
		return response;
	}

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<CoronaStats> runServiceByCountry(String country) throws Exception {
		HttpEntity<CoronaStats> requestEntity = new HttpEntity<CoronaStats>(setHeaders());
		ResponseEntity<CoronaStats> response = restTemplate.exchange(url + country, HttpMethod.GET, requestEntity,
				CoronaStats.class);
		log.info(response.toString());
		return response;
	}

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<CoronaStats> runServiceByGermany() throws Exception {
		HttpEntity<CoronaStats> requestEntity = new HttpEntity<CoronaStats>(setHeaders());
		ResponseEntity<CoronaStats> response = restTemplate.exchange(url + "Italy", HttpMethod.GET, requestEntity,
				CoronaStats.class);
		log.info(response.toString());
		return response;
	}
	
	@GetMapping(produces = { "application/json" })
	public CountryNinja[] runNinjaService() throws Exception {
		HttpEntity<CountryNinja> requestEntity = new HttpEntity<CountryNinja>(setHeadersForNinja());
		CountryNinja[] listOfCountries = restTemplate.exchange(ninjaHost+"countries/", HttpMethod.GET, requestEntity,
				CountryNinja[].class).getBody();
		log.info(listOfCountries.toString());
		return listOfCountries;
	}
	
	@GetMapping(produces = { "application/json" })
	public CountryNinja runNinjaServiceByCountry(String country) throws Exception {
		HttpEntity<CountryNinja> requestEntity = new HttpEntity<CountryNinja>(setHeadersForNinja());
		CountryNinja response = restTemplate.exchange(ninjaHost+"countries/"+country, HttpMethod.GET, requestEntity,
				CountryNinja.class).getBody();
		log.info(response.toString());
		return response;
	}
	
	@GetMapping(produces = { "application/json" })
	public CumulativeCases runCumulativeService() throws Exception {
		HttpEntity<CumulativeCases> requestEntity = new HttpEntity<CumulativeCases>(setHeadersForNinja());
		CumulativeCases response = restTemplate.exchange(ninjaHost+"all", HttpMethod.GET, requestEntity,
				CumulativeCases.class).getBody();
		log.info(response.toString());
		return response;
	}
	
	@GetMapping(produces = { "application/json" })
	public Historical[] runHistoricalService() throws Exception {
		HttpEntity<Historical> requestEntity = new HttpEntity<Historical>(setHeadersForNinja());
		Historical[] response = restTemplate.exchange(ninjaHostv2+"historical", HttpMethod.GET, requestEntity,
				Historical[].class).getBody();
		log.info(response.toString());
		return response;
	}
	
	@GetMapping(produces = { "application/json" })
	public HistoricalCases runHistoricalServiceByCountry(String country) throws Exception {
		HttpEntity<HistoricalCases> requestEntity = new HttpEntity<HistoricalCases>(setHeadersForNinja());
		HistoricalCases response = restTemplate.exchange(ninjaHostv2+"historical/"+country, HttpMethod.GET, requestEntity,
				HistoricalCases.class).getBody();
		log.info(response.toString());
		return response;
	}

}
