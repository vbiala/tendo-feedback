package com.tendo.mapper;

import com.tendo.dto.FeedBackQuestionResource;
import com.tendo.dto.FeedbackAnswerResource;
import com.tendo.entity.FeedbackQuestion;

import java.util.ArrayList;
import java.util.List;

public class FeedbackQuestionResourceMapper {

    public static FeedBackQuestionResource toResource(FeedbackQuestion feedbackQuestion) {
      return FeedBackQuestionResource.builder()
               .questionPrompt(feedbackQuestion.getQuestion_prompt())
               .questionOrder(feedbackQuestion.getQuestion_order())
               .id(feedbackQuestion.getId())
               .surveyId(feedbackQuestion.getSurveyId())
               .build();
    }

    public static List<FeedBackQuestionResource> toResource(List<FeedbackQuestion> feedbackQuestions) {

        List<FeedBackQuestionResource> feedBackQuestionResources = new ArrayList<>();
        for (FeedbackQuestion question : feedbackQuestions) {
            feedBackQuestionResources.add(toResource(question));
        }
        return feedBackQuestionResources;
    }
}
