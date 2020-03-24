/**
 * 
 */
package com.coronastats.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * @author harshavmb
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class CountryNinja {
	
	private String country;
	
	private CountryInfo countryInfo;
	
	private int cases;
	
	private int todayCases;
	
	private int deaths;
	
	private int todayDeaths;
	
	private int recovered;
	
	private int active;
	
	private int critical;
	
	private int casesPerOneMillion;
	
	private int deathsPerOneMillion;

}
