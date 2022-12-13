package com.tendo.service;

import com.tendo.entity.FeedbackAnswer;
import com.tendo.entity.FeedbackQuestion;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    List<FeedbackQuestion> getFeedBackQuestionsForBundle(UUID bundleId);
    FeedbackAnswer saveOrUpdateFeedbackAnswer(FeedbackAnswer answer);

    List<FeedbackAnswer> getFeedbackAnswersForBundle(UUID bundleId);

}
