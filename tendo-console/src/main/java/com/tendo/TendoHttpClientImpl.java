package com.tendo;

import com.tendo.dto.FeedBackQuestionResource;
import com.tendo.dto.FeedbackAnswerResource;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class TendoHttpClientImpl implements TendoHttpClient {

    private final HttpClient httpClient;

    public TendoHttpClientImpl(@Client("http://localhost:8080") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Mono<List<FeedBackQuestionResource>> getFeedBackQuestionsForBundleId(UUID bundleId) throws InterruptedException {
        URI uri = UriBuilder.of("/bundle/" + bundleId + "/feedbackquestions")
                .build();
        HttpRequest<?> req = HttpRequest.GET(uri) // <4>
                .header(USER_AGENT, "Micronaut HTTP Client") // <5>
                .header(ACCEPT, "application/vnd.github.v3+json, application/json"); // <6>
        return Mono.from(httpClient.retrieve(req, Argument.listOf(FeedBackQuestionResource.class)));
    }

    public Mono<FeedbackAnswerResource> postFeedBackAnswer(FeedbackAnswerResource feedbackAnswerResource) throws InterruptedException {
        URI uri = UriBuilder.of("/bundle/" + feedbackAnswerResource.getBundleId() + "/feedbackanswer")
                .build();
        HttpRequest<?> req = HttpRequest.POST(uri, feedbackAnswerResource) // <4>
                .header(USER_AGENT, "Micronaut HTTP Client") // <5>
                .header(ACCEPT, "application/vnd.github.v3+json, application/json"); // <6>
        return Mono.from(httpClient.retrieve(req, FeedbackAnswerResource.class));
    }
}
