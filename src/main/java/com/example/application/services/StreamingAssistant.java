package com.example.application.services;

import dev.langchain4j.service.TokenStream;

public interface StreamingAssistant {
    TokenStream chat(String message);
}
