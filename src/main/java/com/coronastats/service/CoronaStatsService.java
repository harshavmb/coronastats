/**
 * 
 */
package com.coronastats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.coronastats.model.CoronaStats;
import com.coronastats.model.consumingrestservice.ConsumeRapidApi;

/**
 * @author harshavmb
 *
 */
@Service
public class CoronaStatsService {

	private static final Logger log = LoggerFactory.getLogger(ConsumeRapidApi.class);

	private final String url;

	private final String host;

	private final String host_key;

	@Autowired
	public CoronaStatsService(@Value("${rapidapi.url}") String url, @Value("${rapidapi.host}") String host,
			@Value("${rapiapi.key}") String host_key) {
		this.url = url;
		this.host = host;
		this.host_key = host_key;
	}

	private HttpHeaders setHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-rapidapi-host", host);
		headers.set("x-rapidapi-key", host_key);
		return headers;
	}

	RestTemplate restTemplate = new RestTemplate();

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<CoronaStats> runService() throws Exception {
		HttpEntity<CoronaStats> requestEntity = new HttpEntity<CoronaStats>(setHeaders());
		ResponseEntity<CoronaStats> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				CoronaStats.class);
		log.info(response.toString());
		return response;
	}

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<CoronaStats> runServiceByCountry(String country) throws Exception {
		HttpEntity<CoronaStats> requestEntity = new HttpEntity<CoronaStats>(setHeaders());
		ResponseEntity<CoronaStats> response = restTemplate.exchange(url + country, HttpMethod.GET, requestEntity,
				CoronaStats.class);
		log.info(response.toString());
		return response;
	}

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<CoronaStats> runServiceByGermany() throws Exception {
		HttpEntity<CoronaStats> requestEntity = new HttpEntity<CoronaStats>(setHeaders());
		ResponseEntity<CoronaStats> response = restTemplate.exchange(url + "Italy", HttpMethod.GET, requestEntity,
				CoronaStats.class);
		log.info(response.toString());
		return response;
	}

}
