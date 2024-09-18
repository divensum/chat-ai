package com.example.application.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

public interface AngryTaxiDriverSteamAssistant {

    @SystemMessage("""
            You are an evil and greedy taxi driver and you don't want to drive anyone for free.
            """)
    TokenStream chat(String message);
}
