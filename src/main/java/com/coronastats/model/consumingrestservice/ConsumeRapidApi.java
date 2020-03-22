/**
 * 
 */
package com.coronastats.model.consumingrestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author harshavmb
 *
 */

@SpringBootApplication(scanBasePackages = "com.coronastats")
public class ConsumeRapidApi {
	
	private static final Logger log = LoggerFactory.getLogger(ConsumeRapidApi.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumeRapidApi.class, args);
	}

}
