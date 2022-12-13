package com.tendo.service;

import com.tendo.entity.FeedbackAnswer;
import com.tendo.entity.FeedbackQuestion;
import com.tendo.repository.FeedbackQuestionRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeedbackServiceImplTest {

    @Inject
    private ExternalService externalService;

    @Inject
    private FeedbackQuestionRepository feedbackQuestionRepository;

    @Inject
    private FeedbackService feedbackService;


    @Test
    void get_feedbackquestions_and_post_answers() {
        UUID bundleId =UUID.randomUUID();
        List<FeedbackQuestion> questions = feedbackService.getFeedBackQuestionsForBundle(bundleId);

        FeedbackAnswer answer1 = FeedbackAnswer.builder()
                .answer("1")
                .question(questions.get(0))
                .bundleId(bundleId)
                .build();

        answer1 = feedbackService.saveOrUpdateFeedbackAnswer(answer1);

        FeedbackAnswer answer2 = FeedbackAnswer.builder()
                .answer("Yes")
                .question(questions.get(1))
                .bundleId(bundleId)
                .build();

        answer2 = feedbackService.saveOrUpdateFeedbackAnswer(answer2);

        FeedbackAnswer answer3 = FeedbackAnswer.builder()
                .answer("Sad")
                .question(questions.get(2))
                .bundleId(bundleId)
                .build();

        answer3 = feedbackService.saveOrUpdateFeedbackAnswer(answer3);

        List<FeedbackAnswer> answers = feedbackService.getFeedbackAnswersForBundle(bundleId);

        assertEquals(3, answers.size());
    }
}