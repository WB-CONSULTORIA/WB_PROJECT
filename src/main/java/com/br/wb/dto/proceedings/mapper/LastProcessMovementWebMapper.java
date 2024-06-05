package com.br.wb.dto.proceedings.mapper;

import com.br.wb.domain.proceeding.Movement;
import com.br.wb.domain.proceeding.Proceeding;
import com.br.wb.domain.LastProcessMovement;
import com.br.wb.dto.proceedings.LastProcessMovementDTO;
import com.br.wb.dto.proceedings.MovementsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LastProcessMovementWebMapper {

    LastProcessMovement mapToDomain(LastProcessMovementDTO dto);

    LastProcessMovementDTO mapToDto(LastProcessMovement domain);

    Movement mapToDomain (MovementsDTO dto);

    MovementsDTO mapToDto(Movement movement);


//mapeia o processo
    default Proceeding mapToProceeding(LastProcessMovement dto) {
        return Proceeding.builder()
                .numeroProcesso(dto.getInformations().getNumeroProcesso())
                .dataHora(dto.getDataHoraUltimaAtualizacao())
                .movimentos(dto.getInformations().getMovimentos().stream().toList())
                .build();
    }
}
