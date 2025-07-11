package com.kushagra.journalApp.service;

import com.kushagra.journalApp.response.api.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuoteService {

    private static final String API_KEY = "tlfK1o8uqw7beZsoEqHr7A==CrPE2BIlCcef0pTM";
    private static final String API = "https://api.api-ninjas.com/v1/quotes";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List<QuoteResponse>> getQuote() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Api-Key", API_KEY);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(
                API,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<QuoteResponse>>() {}
        );
    }
}
