package com.tendo;

import com.tendo.dto.FeedBackQuestionResource;
import com.tendo.dto.FeedbackAnswerResource;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface TendoHttpClient {

    Mono<List<FeedBackQuestionResource>> getFeedBackQuestionsForBundleId(UUID bundleId) throws InterruptedException;
    Mono<FeedbackAnswerResource> postFeedBackAnswer(FeedbackAnswerResource feedbackAnswerResource) throws InterruptedException;
}
