package de.ing.mywebapp.service.config;

import de.ing.mywebapp.persistence.PersonRepository;
import de.ing.mywebapp.service.PersonService;
import de.ing.mywebapp.service.internal.PersonServiceImpl;
import de.ing.mywebapp.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    @Qualifier("antipathen")
    public List<String> createAntipathen() {
        return List.of("Attila", "Peter", "Paul", "Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> fruits() {
        return List.of("Banana", "Cherry", "Strawberry", "Raspberry");
    }

    @Bean
    public PersonService personService(
            final PersonRepository personRepository
            , final PersonMapper personMapper,
            final @Qualifier("antipathen") List<String> antipathen) {
        return new PersonServiceImpl(personRepository, personMapper, antipathen);
    }
}
