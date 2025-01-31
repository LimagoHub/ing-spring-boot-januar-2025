package de.ing.mywebapp.presentation.mapper;


import de.ing.mywebapp.presentation.dto.SchweinDTO;
import de.ing.mywebapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDTOMapper {
    SchweinDTO convert(Schwein schwein);
    Schwein convert(SchweinDTO schweinDto);

    Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
}
