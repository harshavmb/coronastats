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
public class Deaths {
	
    private Map<String, Integer> deathFields;
	
	@JsonAnyGetter
	private Map<String, Integer> getDeathFields(){
		return deathFields;
	}
	
	@JsonAnySetter
    public void setDeathField(String key, Integer value) {
		deathFields.put(key, value);
    }

}
