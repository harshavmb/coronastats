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
public class Country {
	
    private String country;
	
	private Double confirmed;
	
	private Double deaths;
	
	private Double recovered;

}
