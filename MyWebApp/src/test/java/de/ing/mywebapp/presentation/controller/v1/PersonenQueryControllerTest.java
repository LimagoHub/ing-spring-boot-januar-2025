package de.ing.mywebapp.presentation.controller.v1;

import de.ing.mywebapp.presentation.dto.PersonDto;
import de.ing.mywebapp.service.PersonService;
import de.ing.mywebapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenQueryControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonService personServiceMock;

    @Test
    void test1() throws Exception {

        Person p =
                Person.builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Doe").build();
        var optional = Optional.of(p);
        Mockito.when(personServiceMock.findeAnhandId(Mockito.any())).thenReturn(optional);

        PersonDto result = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        assertEquals("John", result.getVorname());
    }
    @Test
    void test2() throws Exception {

        Person p =
                Person.builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Doe").build();
        var optional = Optional.of(p);
        Mockito.when(personServiceMock.findeAnhandId(Mockito.any())).thenReturn(optional);

        String result = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(result);
    }
    @Test
    void test3() throws Exception {

        Person p =
                Person.builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Doe").build();
        var optional = Optional.of(p);
        Mockito.when(personServiceMock.findeAnhandId(Mockito.any())).thenReturn(optional);

        ResponseEntity<PersonDto> result = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        var dto = result.getBody();
        assertEquals("John", dto.getVorname());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void test4() throws Exception {

        PersonDto p = PersonDto.builder().id(UUID.randomUUID()).vorname("Jane").nachname("Doe").build();
        HttpEntity<PersonDto> request = new HttpEntity<>(p);;

        var liste = List.of(
                Person.builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Doe").build(),
                Person.builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Wick").build(),
                Person.builder()
                        .id(UUID.randomUUID())
                        .vorname("John")
                        .nachname("Rambo").build()
        );

        Mockito.when(personServiceMock.findeAlle()).thenReturn(liste);

        ResponseEntity<List<PersonDto>> result = restTemplate.exchange("/v1/personen", HttpMethod.GET,request,new ParameterizedTypeReference<List<PersonDto>>() { });
        var dto = result.getBody();
        assertEquals(3, dto.size());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}