package de.ing.mywebapp.presentation.controller.v1;


import de.ing.mywebapp.presentation.dto.PersonDto;
import de.ing.mywebapp.presentation.mapper.PersonDtoMapper;
import de.ing.mywebapp.service.PersonService;
import de.ing.mywebapp.service.PersonenServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@RequiredArgsConstructor
public class PersonenQueryController {

    private final PersonService service;
    private final PersonDtoMapper mapper;

    @GetMapping(path ="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPerson(@PathVariable UUID id) throws PersonenServiceException {
        return ResponseEntity.of(service.findeAnhandId(id).map(mapper::convert));
    }
    @GetMapping(path ="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDto>> getPersons(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Mustermann") String nachname
    ) throws PersonenServiceException{

        System.out.println(vorname + " " + nachname);

        return ResponseEntity.ok(mapper.convert(service.findeAlle()));
    }

    @PostMapping(path="/scripts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> toUpperCase(
            @RequestParam(required = false, defaultValue = "toUpper")  String action
            ,PersonDto personDto) {
        personDto.setVorname(personDto.getVorname().toUpperCase());
        personDto.setNachname(personDto.getNachname().toUpperCase());
        return ResponseEntity.ok(personDto);

    }
}
