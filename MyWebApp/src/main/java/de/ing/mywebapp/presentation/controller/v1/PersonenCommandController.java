package de.ing.mywebapp.presentation.controller.v1;

import de.ing.mywebapp.presentation.dto.PersonDto;
import de.ing.mywebapp.presentation.mapper.PersonDtoMapper;
import de.ing.mywebapp.service.PersonService;
import de.ing.mywebapp.service.PersonenServiceException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
//@SessionScope
@RequiredArgsConstructor
public class PersonenCommandController {

    private final PersonService personService;
    private final PersonDtoMapper mapper;

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) throws PersonenServiceException {
       if(personService.loeschen(id)) {
           return ResponseEntity.ok().build();
       }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertPerson(@Valid @RequestBody PersonDto dto, UriComponentsBuilder builder) throws PersonenServiceException{

        personService.speichern(mapper.convert(dto));

        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());


        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody PersonDto dto) throws PersonenServiceException{

        personService.aendern(mapper.convert(dto));


        return ResponseEntity.ok().build();
    }
}
