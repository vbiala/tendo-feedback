package com.tendo.repository;

import com.tendo.entity.FeedbackQuestion;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedbackQuestionRepository extends CrudRepository<FeedbackQuestion, UUID> {

    @Executable
    Optional<FeedbackQuestion> findById(UUID id);

    @Executable
    FeedbackQuestion save(FeedbackQuestion question);

    @Executable
    int update(UUID id,FeedbackQuestion question);

    @Executable
    List<FeedbackQuestion> findBySurveyId(UUID surveyId);
}
