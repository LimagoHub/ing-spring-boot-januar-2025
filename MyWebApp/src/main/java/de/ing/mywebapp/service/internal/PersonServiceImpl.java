package de.ing.mywebapp.service.internal;

import de.ing.mywebapp.persistence.PersonRepository;
import de.ing.mywebapp.service.PersonService;
import de.ing.mywebapp.service.PersonenServiceException;
import de.ing.mywebapp.service.mapper.PersonMapper;
import de.ing.mywebapp.service.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    /*
           Parameter null -> PSE
           vorname null -> PSE
           vorname zu kurz -> PSE
           nachname null -> PSE
           nachname zu kurz -> PSE
           Attila -> PSE
           runtimeException -> PSE
           happy day -> person wird an repo übergeben
        */

    @Override
    public void speichern(final Person person) throws PersonenServiceException {
        try {
            speichernImpl(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps" , e);
        }

    }

    /*public void bulkInsert(final List<Person> persons) throws PersonenServiceException {
        for(var person : persons) {
            speichern(person);
        }
    }*/

    private void speichernImpl(final Person person) throws PersonenServiceException {
        if(person == null) throw new PersonenServiceException("Person nicht vorhanden");

        if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
        if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");

        if(person.getVorname().equalsIgnoreCase("attila")) throw new PersonenServiceException("Antipath");

        personRepository.save(mapper.convert(person));
    }

    @Override
    public boolean aendern(final Person person) throws PersonenServiceException {
        try {
            if(! personRepository.existsById(person.getId())) {
                return false;
            }
            speichernImpl(person);
            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }

    @Override
    public boolean loeschen(final UUID id) throws PersonenServiceException {
        try {
            if(! personRepository.existsById(id)) {
                return false;
            }

            personRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }

    @Override
    public Optional<Person> findeAnhandId(final UUID id) throws PersonenServiceException {
        try {
            return personRepository.findById(id).map(mapper::convert);
        }catch (Exception e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(personRepository.findAll());
        } catch (Exception e) {
            throw new PersonenServiceException("Upps", e);
        }
    }
}
