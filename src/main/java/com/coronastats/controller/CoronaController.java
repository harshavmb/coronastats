package com.coronastats.controller;

import org.springframework.web.bind.annotation.RestController;

import com.coronastats.model.CoronaStats;
import com.coronastats.service.CoronaStatsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author harshavmb
 *
 */
@RestController
public class CoronaController {
	
	public static final Logger logger = LoggerFactory.getLogger(CoronaController.class);
	
	@Autowired
	private CoronaStatsService coronaStatsService;

	@RequestMapping("/restcountries")
	public CoronaStats allCountries() throws Exception {
		return coronaStatsService.runService().getBody();
	}
	
	@RequestMapping("/restcountry")
	public CoronaStats byCountry(@RequestParam String country) throws Exception {
		return coronaStatsService.runServiceByCountry(country).getBody();
	}

}
