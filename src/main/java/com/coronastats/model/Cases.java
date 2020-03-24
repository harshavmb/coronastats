/**
 * 
 */
package com.coronastats.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
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
public class Cases {
	
	Map<String, Integer> caseFields = new HashMap<String, Integer>();
	
	@JsonAnyGetter
	public Map<String, Integer> getCaseFields(){
		return this.caseFields;
	}
	
	@JsonAnySetter
    public void setCaseField(String key, Integer value) {
		caseFields.put(key, value);
    }

}
