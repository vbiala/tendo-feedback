package com.tendo;

import com.tendo.dto.FeedBackQuestionResource;
import com.tendo.dto.FeedbackAnswerResource;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Command(name = "tendo-console", description = "...",
        mixinStandardHelpOptions = true)
public class TendoConsoleCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Inject
    private TendoHttpClient tendoHttpClient;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(TendoConsoleCommand.class, args);
    }

    public void run() {

        UUID bundleId = UUID.fromString("71cde2aa-b9bc-496a-a6f1-34964d05e6fd");
        try {
            List<FeedBackQuestionResource> feedBackQuestionResources = tendoHttpClient
                    .getFeedBackQuestionsForBundleId(bundleId).block();

            List<FeedbackAnswerResource> feedbackAnswerResources = new ArrayList<>();

            for (FeedBackQuestionResource resource : feedBackQuestionResources) {
                System.out.println(resource.getQuestionPrompt());
                String n= System.console().readLine();
                if (!n.isEmpty()) {
                    FeedbackAnswerResource answerResource = FeedbackAnswerResource.builder()
                            .answer(n)
                            .bundleId(bundleId)
                            .questionId(resource.getId())
                            .questionPrompt(resource.getQuestionPrompt())
                            .build();
                    tendoHttpClient.postFeedBackAnswer(answerResource).block();
                    feedbackAnswerResources.add(answerResource);
                }
            }

            System.out.println("Thanks again. Here’s what we heard:");
            System.out.println("*******************************************");

            for (FeedbackAnswerResource answerResource : feedbackAnswerResources) {
                System.out.println(answerResource.getQuestionPrompt());
                System.out.println(answerResource.getAnswer());
            }


        } catch (InterruptedException e) {
            System.err.println(e.toString());
            throw new RuntimeException(e);
        }
    }
}
