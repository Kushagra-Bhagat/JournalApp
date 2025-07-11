package com.kushagra.journalApp.service;

import com.kushagra.journalApp.cache.AppCache;
import com.kushagra.journalApp.config.QuoteApiConfig;
import com.kushagra.journalApp.repository.ConfigJournalAppRepository;
import com.kushagra.journalApp.response.api.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuoteService {

    private final RestTemplate restTemplate;
    private final QuoteApiConfig quoteApiConfig;

    @Autowired
    private AppCache appCache;

    public QuoteService(RestTemplate restTemplate, QuoteApiConfig quoteApiConfig) {
        this.restTemplate = restTemplate;
        this.quoteApiConfig = quoteApiConfig;
    }

    public ResponseEntity<List<QuoteResponse>> getQuote() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Api-Key", quoteApiConfig.getKey());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(
                appCache.APP_CACHE.get("quote_api"),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<QuoteResponse>>() {}
        );
    }
}
