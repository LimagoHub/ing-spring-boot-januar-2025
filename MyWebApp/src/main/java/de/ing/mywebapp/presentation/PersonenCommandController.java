package de.ing.mywebapp.presentation;

import de.ing.mywebapp.SeltsamException;
import de.ing.mywebapp.presentation.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
//@SessionScope
public class PersonenCommandController {

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        System.out.println("PersonenCommandController.deletePerson");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertPerson(@Valid @RequestBody PersonDto dto, UriComponentsBuilder builder) {
        if(true) throw new SeltsamException("Häh");

        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());

        System.out.println("PersonenCommandController.insertPerson");
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody PersonDto dto) {


        System.out.println("PersonenCommandController.updatePerson");
        return ResponseEntity.ok().build();
    }
}
