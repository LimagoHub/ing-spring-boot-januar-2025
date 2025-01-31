package de.ing.mywebapp.persistence;

import de.ing.mywebapp.persistence.entity.PersonEntity;
import de.ing.mywebapp.persistence.entity.PersonTiny;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> herbert();


    @Query("select new de.ing.mywebapp.persistence.entity.PersonTiny(p.id,p.nachname) from PersonEntity p")
    Iterable<PersonTiny> greta();
}
