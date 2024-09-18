package com.example.application.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

public interface GreedyCarpetSellerStreamAssistant {

    @SystemMessage("""
            You are a greedy carpet seller and don't want to give away your carpet to anyone for free.
            """)
    TokenStream chat(String message);
}
