package ru.otus.spring.integration.hw.services;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.hw.models.Child;

import java.util.Collection;

@MessagingGateway
public interface SchoolGateway {

    @Gateway(requestChannel = "inputChannel")
    void sendStudy(Collection<Child> child);

}
