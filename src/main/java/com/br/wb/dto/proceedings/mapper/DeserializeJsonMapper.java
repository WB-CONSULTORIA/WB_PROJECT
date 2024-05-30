package com.br.wb.dto.proceedings.mapper;

import com.br.wb.domain.LastProcessMovement;
import com.br.wb.domain.proceeding.Movement;
import com.br.wb.domain.proceeding.Proceeding;
import com.br.wb.respositories.LastProcessMovementRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeserializeJsonMapper {

    private final LastProcessMovementRepository lastProcessMovementRepository;

    public List<LastProcessMovement> deserializeToObject(JsonNode jsonNode) throws JsonProcessingException {
        List<LastProcessMovement> lastProcessMovement = new ArrayList<>();
        if (jsonNode != null && jsonNode.hasNonNull("hits")) {
            JsonNode hitsNode = jsonNode.get("hits").get("hits");
            for (JsonNode node : hitsNode) {
                JsonNode sourceNode = node.get("_source");
                ObjectMapper objectMapper = new ObjectMapper();
                Proceeding proceedingInfo = objectMapper.treeToValue(sourceNode, Proceeding.class);

               var recentMove = findLastMove(proceedingInfo);
                LastProcessMovement hit = new LastProcessMovement();
                hit.setMovimentoMaisRecente(recentMove);
                hit.setInformations(proceedingInfo);
                lastProcessMovementRepository.save(hit);
                hit.getInformations().setMovimentos(null);
                lastProcessMovement.add(hit);
            }
        }
        return lastProcessMovement;
    }

    public Movement findLastMove(Proceeding proceeding) {
        List<Movement> movimentos = proceeding.getMovimentos();
        if (movimentos == null) {
            return null;
        }
        Movement movimentoMaisRecente = movimentos.stream()
                .max(Comparator.comparing(m -> ZonedDateTime.parse(m.getDataHora())))
                .orElse(null);
        assert movimentoMaisRecente != null;
        return movimentoMaisRecente;
    }



}





