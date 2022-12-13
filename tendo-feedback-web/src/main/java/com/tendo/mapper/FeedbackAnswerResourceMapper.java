package com.tendo.mapper;

import com.tendo.dto.FeedBackQuestionResource;
import com.tendo.dto.FeedbackAnswerResource;
import com.tendo.entity.FeedbackAnswer;
import com.tendo.entity.FeedbackQuestion;

import java.util.ArrayList;
import java.util.List;

public class FeedbackAnswerResourceMapper {

    public static FeedbackAnswerResource toResource(FeedbackAnswer feedbackAnswer) {
        return FeedbackAnswerResource.builder()
                .questionPrompt(feedbackAnswer.getQuestion().getQuestion_prompt())
                .questionOrder(feedbackAnswer.getQuestion().getQuestion_order())
                .answer(feedbackAnswer.getAnswer())
                .id(feedbackAnswer.getId())
                .bundleId(feedbackAnswer.getBundleId())
                .build();
    }

    public static List<FeedbackAnswerResource> toResource(List<FeedbackAnswer> feedbackAnswers) {

        List<FeedbackAnswerResource> feedbackAnswerResources = new ArrayList<>();
        for (FeedbackAnswer answer : feedbackAnswers) {
            feedbackAnswerResources.add(toResource(answer));
        }
        return feedbackAnswerResources;
    }

    public static FeedbackAnswer fromResource(FeedbackAnswerResource feedbackAnswerResource) {
        FeedbackQuestion feedbackQuestion = FeedbackQuestion.builder()
                .question_order(feedbackAnswerResource.getQuestionOrder())
                .question_prompt(feedbackAnswerResource.getQuestionPrompt())
                .build();
        feedbackQuestion.setId(feedbackAnswerResource.getQuestionId());
        return FeedbackAnswer.builder()
                .bundleId(feedbackAnswerResource.getBundleId())
                .question(feedbackQuestion)
                .answer(feedbackAnswerResource.getAnswer())
                .build();
    }
}
