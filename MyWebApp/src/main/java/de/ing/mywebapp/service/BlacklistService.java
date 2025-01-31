package de.ing.mywebapp.service;

import de.ing.mywebapp.service.model.Person;

public interface BlacklistService {

    boolean isBlacklisted(Person person);
}
