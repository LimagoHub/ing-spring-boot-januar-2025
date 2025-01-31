package de.ing.mywebapp.presentation.mapper;

import de.ing.mywebapp.persistence.entity.PersonEntity;
import de.ing.mywebapp.presentation.dto.PersonDto;
import de.ing.mywebapp.service.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonDtoMapper {
    Person convert(PersonDto personDto);
    PersonDto convert(Person person);
    Iterable<PersonDto> convert(Iterable<Person> personen);
}
