/**
 * 
 */
package com.coronastats.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
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
public class Cases {
	
	private Map<String, Integer> caseFields;
	
	@JsonAnyGetter
	private Map<String, Integer> getCaseFields(){
		return caseFields;
	}
	
	@JsonAnySetter
    public void setCaseField(String key, Integer value) {
		caseFields.put(key, value);
    }

}
