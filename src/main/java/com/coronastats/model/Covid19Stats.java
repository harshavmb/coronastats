/**
 * 
 */
package com.coronastats.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Covid19Stats {
	
	private String province;
	
	private String country;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastUpdate;
	
	private Double confirmed;
	
	private Double deaths;
	
	private Double recovered;

}
