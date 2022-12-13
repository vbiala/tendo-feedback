package com.tendo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackQuestion extends BaseEntity {

    @Column(nullable = false)
    private UUID surveyId;

    @Column(nullable = false)
    private String question_prompt;

    @Column(nullable = false)
    private int question_order;

    public UUID getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(UUID surveyId) {
        this.surveyId = surveyId;
    }

    public int getQuestion_order() {
        return question_order;
    }

    public void setQuestion_order(int question_order) {
        this.question_order = question_order;
    }

    public String getQuestion_prompt() {
        return question_prompt;
    }

    public void setQuestion_prompt(String question_prompt) {
        this.question_prompt = question_prompt;
    }
}
