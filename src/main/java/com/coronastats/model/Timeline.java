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
public class Timeline {
	
	private Cases cases;
	
	private Deaths deaths;
	
	private Recovered recovered;

}
