package de.ing.mywebapp.persistence;


import de.ing.mywebapp.persistence.entity.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchweinRepository extends CrudRepository<SchweinEntity, UUID> {
}
