package com.kushagra.journalApp.response.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuoteResponse {
    private String quote;
    private String author;
    private String category;
}
