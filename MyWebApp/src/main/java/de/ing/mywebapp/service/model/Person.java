package de.ing.mywebapp.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {


    private UUID id;


    private String vorname;


    private String nachname;

}
