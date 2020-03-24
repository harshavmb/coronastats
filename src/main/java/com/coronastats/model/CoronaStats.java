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
public class CoronaStats {
	
	private boolean error;
	
	private int statusCode;
	
	private String message;
	
	private Data data;

}
