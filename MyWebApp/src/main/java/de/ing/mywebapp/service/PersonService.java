package de.ing.mywebapp.service;

import de.ing.mywebapp.service.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    void speichern(Person person) throws PersonenServiceException;
    boolean aendern(Person person) throws PersonenServiceException;

    boolean loeschen(UUID id) throws PersonenServiceException;

    Optional<Person> findeAnhandId(UUID id) throws PersonenServiceException;

    Iterable<Person> findeAlle() throws PersonenServiceException;
}
