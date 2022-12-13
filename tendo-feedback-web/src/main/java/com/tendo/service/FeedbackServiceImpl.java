package com.tendo.service;

import com.tendo.dto.BundleResource;
import com.tendo.entity.FeedbackAnswer;
import com.tendo.entity.FeedbackQuestion;
import com.tendo.repository.FeedbackAnswerRepository;
import com.tendo.repository.FeedbackQuestionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class FeedbackServiceImpl implements FeedbackService{

    @Inject
    private FeedbackQuestionRepository feedbackQuestionRepository;

    @Inject
    private FeedbackAnswerRepository feedbackAnswerRepository;

    @Inject
    private ExternalServiceImpl externalService;


    public List<FeedbackQuestion> getFeedBackQuestionsForBundle(UUID bundleId) {

        List<FeedbackQuestion> questions = feedbackQuestionRepository.findBySurveyId(
                UUID.fromString("80e07984-6ac3-46e3-b417-c4625fa08efe"));

        if (questions.size() == 0) {
            createSampleData();
            questions = feedbackQuestionRepository.findBySurveyId(
                    UUID.fromString("80e07984-6ac3-46e3-b417-c4625fa08efe"));
        }


        Optional<BundleResource> bundle = externalService.getBundle(bundleId);
        if (bundle.isPresent()) {
            replaceQuestionsTags(questions, bundle.get());
        }

        return questions;
    }

    public FeedbackAnswer saveOrUpdateFeedbackAnswer(FeedbackAnswer answer) {
        Optional<FeedbackAnswer> feedbackAnswer = feedbackAnswerRepository.
                findByBundleIdAndQuestion(answer.getBundleId(), answer.getQuestion());
        if (feedbackAnswer.isPresent()) {
            feedbackAnswerRepository.update(feedbackAnswer.get().getId(), answer);
            return answer;
        } else {
            return feedbackAnswerRepository.save(answer);
        }
    }

    public List<FeedbackAnswer> getFeedbackAnswersForBundle(UUID bundleId) {
        return feedbackAnswerRepository.findByBundleId(bundleId);
    }

    private void replaceQuestionsTags(List<FeedbackQuestion> questions, BundleResource bundleResource) {
        for (FeedbackQuestion question : questions) {

            bundleResource.getPatient().ifPresent(patient -> {
                question.setQuestion_prompt(question.getQuestion_prompt().replace("[Patient]",
                        patient.getName()));
            });

            bundleResource.getDoctor().ifPresent(doctor -> {
                question.setQuestion_prompt(question.getQuestion_prompt().replace("[Doctor]",
                        doctor.getName()));
            });

            bundleResource.getDiagnosis().ifPresent(diagnosis -> {
                question.setQuestion_prompt(question.getQuestion_prompt().replace("[Diagnosis]",
                        diagnosis.getCode()));
            });
        }
    }

    private void createSampleData() {
        UUID surveyId = UUID.fromString("80e07984-6ac3-46e3-b417-c4625fa08efe");
        FeedbackQuestion feedbackQuestion = FeedbackQuestion.builder()
                .question_order(1)
                .surveyId(surveyId)
                .question_prompt("Hi [Patient], on a scale of 1-10, would you recommend Dr " +
                        "[Doctor] to a friend or family member? 1 = Would not recommend, 10 = " +
                        "Would strongly recommend")
                .build();
        feedbackQuestionRepository.save(feedbackQuestion);

        feedbackQuestion = FeedbackQuestion.builder()
                .question_order(2)
                .surveyId(surveyId)
                .question_prompt("Thank you. You were diagnosed with [Diagnosis]. Did Dr [Doctor] explain " +
                        "how to manage this diagnosis in a way you could understand?")
                .build();
        feedbackQuestionRepository.save(feedbackQuestion);

        feedbackQuestion = FeedbackQuestion.builder()
                .question_order(3)
                .surveyId(surveyId)
                .question_prompt("We appreciate the feedback, one last question: how do you feel about being diagnosed" +
                        " with [Diagnosis]?")
                .build();
        feedbackQuestionRepository.save(feedbackQuestion);
    }
}
