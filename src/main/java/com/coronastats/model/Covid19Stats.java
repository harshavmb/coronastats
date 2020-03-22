/**
 * 
 */
package com.coronastats.model;

import java.util.Date;

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
public class Covid19Stats {
	
	private String province;
	
	private String country;
	
	private Date lastUpdate;
	
	private Double confirmed;
	
	private Double deaths;
	
	private Double recovered;

}
