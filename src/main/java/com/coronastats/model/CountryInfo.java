/**
 * 
 */
package com.coronastats.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author harshavmb
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CountryInfo {
	
	private String iso2;
	
	private String iso3;
	
	private String _id;
	
	private Double lat;
	
	@JsonAlias(value="long")
	private Double longitude;
	
	private String flag;

}



