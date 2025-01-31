package de.ing.mywebapp;

import de.ing.mywebapp.persistence.PersonRepository;
import de.ing.mywebapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final PersonRepository repo;

    @PostConstruct
    public void doIt() {
        var p = PersonEntity.builder()
                .id(UUID.randomUUID())
                .vorname("John")
                .nachname("Doe")
                .build();
        repo.save(p);
    }

}
