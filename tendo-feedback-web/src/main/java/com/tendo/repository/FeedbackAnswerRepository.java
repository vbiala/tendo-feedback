package com.tendo.repository;

import com.tendo.entity.FeedbackAnswer;
import com.tendo.entity.FeedbackQuestion;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedbackAnswerRepository extends CrudRepository<FeedbackAnswer, UUID> {

    @Executable
    Optional<FeedbackAnswer> findById(UUID id);

    @Executable
    Optional<FeedbackAnswer> findByBundleIdAndQuestion(UUID bundleId, FeedbackQuestion question);

    @Executable
    FeedbackAnswer save(FeedbackAnswer answer);

    @Executable
    int update(UUID id,FeedbackAnswer question);

    @Executable
    List<FeedbackAnswer> findByBundleId(UUID bundleId);
}
