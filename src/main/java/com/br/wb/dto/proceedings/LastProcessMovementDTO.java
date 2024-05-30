package com.br.wb.dto.proceedings;


import com.br.wb.domain.proceeding.Movement;

import java.util.Date;

public record LastProcessMovementDTO(Movement movimentoMaisRecente,
                                     ProceedingInfo allMoves,
                                     String dataHoraUltimaAtualizacao

                                     ) {

}
