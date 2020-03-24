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
public class Deaths {
	
    Map<String, Integer> deathFields = new HashMap<String, Integer>();
	
	@JsonAnyGetter
	private Map<String, Integer> getDeathFields(){
		return deathFields;
	}
	
	@JsonAnySetter
    public void setDeathField(String key, Integer value) {
		deathFields.put(key, value);
    }

}
