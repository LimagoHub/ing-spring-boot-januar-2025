package de.ing.mywebapp;

import de.ing.mywebapp.persistence.PersonRepository;
import de.ing.mywebapp.persistence.entity.PersonEntity;
import de.ing.mywebapp.service.MailData;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final MailData data;

    @PostConstruct
    public void doIt() {
        System.out.println(data);
    }

}
