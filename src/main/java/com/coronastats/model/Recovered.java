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
public class Recovered {
	
    private Map<String, Integer> recoveryFields;
	
	@JsonAnyGetter
	private Map<String, Integer> getRecoveryFields(){
		return recoveryFields;
	}
	
	@JsonAnySetter
    public void setRecoveryField(String key, Integer value) {
		recoveryFields.put(key, value);
    }

}
