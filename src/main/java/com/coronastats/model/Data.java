/**
 * 
 */
package com.coronastats.model;

import java.util.Date;
import java.util.List;

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
public class Data {
	
	private Date lastChecked;
	
	private List<Covid19Stats> covid19Stats;

}
