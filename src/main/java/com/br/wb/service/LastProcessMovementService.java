package com.br.wb.service;

import com.br.wb.domain.LastProcessMovement;
import com.br.wb.respositories.LastProcessMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LastProcessMovementService {

    private final LastProcessMovementRepository lastProcessMovementRepository;
    private final DataJudConnectionService dataJudConnectionService;


    public LastProcessMovement save(LastProcessMovement lastMove){
        lastMove.setDataHoraUltimaAtualizacao(new Date());
       return lastProcessMovementRepository.save(lastMove);
    }

    public List<LastProcessMovement> procurarPorNumero(String numeroProcesso, String id) throws IOException {
        return dataJudConnectionService.procurarPorNumero(numeroProcesso);
    }
    public Optional<LastProcessMovement> getMostRecentMovement() {
        return lastProcessMovementRepository.findFirstByOrderByMovimentoMaisRecenteDesc();
    }

}
