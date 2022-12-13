package com.tendo.controller;

import com.tendo.dto.FeedBackQuestionResource;
import com.tendo.dto.FeedbackAnswerResource;
import com.tendo.entity.FeedbackAnswer;
import com.tendo.entity.FeedbackQuestion;
import com.tendo.mapper.FeedbackAnswerResourceMapper;
import com.tendo.mapper.FeedbackQuestionResourceMapper;
import com.tendo.service.FeedbackServiceImpl;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@Controller("/")
public class FeedbackController {

    @Inject
    private FeedbackServiceImpl feedbackService;



    @Get("/bundle/{id}/feedbackquestions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FeedBackQuestionResource> getQuestionsByBundleId(UUID id) {
        List<FeedbackQuestion> feedbackQuestions = feedbackService.getFeedBackQuestionsForBundle(id);
        return FeedbackQuestionResourceMapper.toResource(feedbackQuestions);

    }

    @Get("/bundle/{id}/feedbackanswers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FeedbackAnswerResource> getAnswersByBundleId(UUID id) {
        List<FeedbackAnswer> feedbackAnswers = feedbackService.getFeedbackAnswersForBundle(id);
        return FeedbackAnswerResourceMapper.toResource(feedbackAnswers);
    }

    @Post("/bundle/{id}/feedbackanswer")
    @Produces(MediaType.APPLICATION_JSON)
    public FeedbackAnswerResource postFeedbackAnswer(@Body FeedbackAnswerResource answer) {

        FeedbackAnswer feedbackAnswer = FeedbackAnswerResourceMapper.fromResource(answer);
        feedbackAnswer = feedbackService.saveOrUpdateFeedbackAnswer(feedbackAnswer);
        if (feedbackAnswer != null) {
            return FeedbackAnswerResourceMapper.toResource(feedbackAnswer);
        } else {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "An error occurred");
        }
    }
}
