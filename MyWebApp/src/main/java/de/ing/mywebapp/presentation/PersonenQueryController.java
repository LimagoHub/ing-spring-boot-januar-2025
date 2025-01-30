package de.ing.mywebapp.presentation;


import de.ing.mywebapp.presentation.dto.PersonDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenQueryController {

    @GetMapping(path ="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPerson(@PathVariable UUID id) {

        Optional<PersonDto> optionalPersonDto;
        if(id.toString().endsWith("7")){
            optionalPersonDto = Optional.empty();
        } else {
            optionalPersonDto = Optional.of(
                    PersonDto
                            .builder()
                            .id(id)
                            .vorname("John")
                            .nachname("Doe")
                            .build()
            );
        }


        return ResponseEntity.of(optionalPersonDto);
    }
    @GetMapping(path ="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDto>> getPersons(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Mustermann") String nachname
    ) {

        System.out.println(vorname + " " + nachname);
        var persons = List.of(
                PersonDto
                        .builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Doe")
                        .build(),
                PersonDto
                        .builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Rambo")
                        .build(),
                PersonDto
                        .builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Wick")
                        .build()


        );

        return ResponseEntity.ok(persons);
    }

    @PostMapping(path="/a/b/c", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> toUpperCase(PersonDto personDto) {
        personDto.setVorname(personDto.getVorname().toUpperCase());
        personDto.setNachname(personDto.getNachname().toUpperCase());
        return ResponseEntity.ok(personDto);

    }
}
