/**
 * 
 */
package com.coronastats.model;


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
public class CumulativeCases {
	
	private int cases;
	
	private int deaths;
	
	private int recovered;
	
	private String updated;

}


