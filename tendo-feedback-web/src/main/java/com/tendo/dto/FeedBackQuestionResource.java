package com.tendo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackQuestionResource {

    private UUID id;

    private String questionPrompt;

    private int questionOrder;

    private UUID surveyId;
}
