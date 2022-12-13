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
public class FeedbackAnswerResource {

    private UUID id;

    private UUID questionId;

    private String questionPrompt;

    private int questionOrder;

    private String answer;

    private UUID bundleId;
}
