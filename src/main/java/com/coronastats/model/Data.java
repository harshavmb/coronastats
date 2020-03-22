/**
 * 
 */
package com.coronastats.model;

import java.util.Date;
import java.util.List;

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
public class Data {
	
	private Date lastChecked;
	
	private List<Covid19Stats> covid19Stats;

}
