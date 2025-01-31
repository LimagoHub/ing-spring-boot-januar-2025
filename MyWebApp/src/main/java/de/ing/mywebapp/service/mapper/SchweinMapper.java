package de.ing.mywebapp.service.mapper;


import de.ing.mywebapp.persistence.entity.SchweinEntity;
import de.ing.mywebapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity entity);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> entities);
}
