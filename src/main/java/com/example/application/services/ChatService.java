package com.example.application.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;

@AnonymousAllowed
@BrowserCallable
public class ChatService {

    @Value("${openai.api.key}")
    private String API_KEY;
    private Assistant assistant;
    private StreamingAssistant streamingAssistant;
    private AngryTaxiDriverSteamAssistant angryTaxiDriverSteamAssistant;
    private GreedyCarpetSellerStreamAssistant greedyCarpetSellerStreamAssistant;


    @PostConstruct
    public void init() {

        if (API_KEY == null) {
            System.err.println("ERROR: OPENAI_API_KEY environment variable is not set.");
        }

        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(GPT_3_5_TURBO)
                .build();

        OpenAiStreamingChatModel streamingChatModel = OpenAiStreamingChatModel.builder()
                .apiKey(API_KEY)
                .modelName(GPT_3_5_TURBO)
                .build();

        assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemory(chatMemory)
                .build();


        streamingAssistant = AiServices.builder(StreamingAssistant.class)
                .streamingChatLanguageModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();

        angryTaxiDriverSteamAssistant = AiServices.builder(AngryTaxiDriverSteamAssistant.class)
                .streamingChatLanguageModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();

        greedyCarpetSellerStreamAssistant = AiServices.builder(GreedyCarpetSellerStreamAssistant.class)
                .streamingChatLanguageModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();
    }

    public String chat(String message) {
        return assistant.chat(message);
    }

    public Flux<String> chatStream(String message) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        streamingAssistant.chat(message)
                .onNext(sink::tryEmitNext)
                .onComplete(c -> sink.tryEmitComplete())
                .onError(sink::tryEmitError)
                .start();

        return sink.asFlux();
    }

    public Flux<String> AngryTaxiDriverChatStream(String message) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        angryTaxiDriverSteamAssistant.chat(message)
                .onNext(sink::tryEmitNext)
                .onComplete(c -> sink.tryEmitComplete())
                .onError(sink::tryEmitError)
                .start();

        return sink.asFlux();
    }

    public Flux<String> GreedyCarpetSellerChatStream(String message) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        greedyCarpetSellerStreamAssistant.chat(message)
                .onNext(sink::tryEmitNext)
                .onComplete(c -> sink.tryEmitComplete())
                .onError(sink::tryEmitError)
                .start();

        return sink.asFlux();
    }
}
