package de.ing.mywebapp.service.mapper;

import de.ing.mywebapp.persistence.entity.PersonEntity;
import de.ing.mywebapp.presentation.dto.PersonDto;
import de.ing.mywebapp.service.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person convert(PersonEntity personEntity);
    PersonEntity convert(Person person);
    Iterable<Person> convert(Iterable<PersonEntity> personEntities);
}
