package com.br.wb.domain;

import com.br.wb.domain.proceeding.Movement;
import com.br.wb.domain.proceeding.Proceeding;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LastProcessMovement {
    private Movement movimentoMaisRecente;
    private Proceeding informations;
    private Date dataHoraUltimaAtualizacao;
    private LocalDateTime timestamp;
}





