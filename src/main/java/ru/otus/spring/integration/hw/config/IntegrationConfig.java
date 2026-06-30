package ru.otus.spring.integration.hw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.*;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.integration.hw.models.Child;
import ru.otus.spring.integration.hw.services.*;

@Configuration
@IntegrationComponentScan(basePackages = "ru.otus.spring.integration.hw")
public class IntegrationConfig {

    private static final int CLASSROOM_SIZE = 5;

    @Bean
    public MessageChannelSpec<?, ?> inputChannel() {
        return MessageChannels.queue();
    }

    @Bean
    public MessageChannelSpec<?, ?> outputChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedDelay(100)
                .maxMessagesPerPoll(10)
                .receiveTimeout(1000);
    }

    @Bean
    public IntegrationFlow fullEducationFlow(School preschool,
                                             School basicSchool,
                                             School secondarySchool,
                                             School highSchool) {
        return IntegrationFlow.from(inputChannel())
                .split()
                .publishSubscribeChannel(sub -> sub
                        .subscribe(subflow -> subflow.filter(this::isSuitPreSchool)
                                .aggregate(a -> a.correlationStrategy(message -> true)
                                        .releaseStrategy(group -> group.size() == CLASSROOM_SIZE)
                                        .expireGroupsUponCompletion(true))
                                .handle(preschool, "teach")
                                .handle(basicSchool, "teach")
                                .handle(secondarySchool, "teach")
                                .handle(highSchool, "teach"))

                        .subscribe(subflow -> subflow.filter(this::isSuitBasicSchool)
                                .aggregate(a -> a.correlationStrategy(message -> true)
                                        .releaseStrategy(group -> group.size() == CLASSROOM_SIZE)
                                        .expireGroupsUponCompletion(true))
                                .handle(basicSchool, "teach")
                                .handle(secondarySchool, "teach")
                                .handle(highSchool, "teach"))

                        .subscribe(subflow -> subflow.filter(this::isSuitSecondarySchool)
                                .aggregate(a -> a.correlationStrategy(message -> true)
                                        .releaseStrategy(group -> group.size() == CLASSROOM_SIZE)
                                        .expireGroupsUponCompletion(true))
                                .handle(secondarySchool, "teach")
                                .handle(highSchool, "teach"))

                        .subscribe(subflow -> subflow.filter(this::isSuitHighSchool)
                                .aggregate(a -> a.correlationStrategy(message -> true)
                                        .releaseStrategy(group -> group.size() == CLASSROOM_SIZE)
                                        .expireGroupsUponCompletion(true))
                                .handle(highSchool, "teach")))
                .get();
    }

    private boolean isSuitPreSchool(Child message) {
        return message.getEducation().equals(School.EducationType.NONE);
    }

    private boolean isSuitBasicSchool(Child message) {
        return message.getEducation().equals(School.EducationType.EARLY);
    }

    private boolean isSuitSecondarySchool(Child message) {
        return message.getEducation().equals(School.EducationType.BASIC);
    }

    private boolean isSuitHighSchool(Child message) {
        return message.getEducation().equals(School.EducationType.SECONDARY);
    }


}
