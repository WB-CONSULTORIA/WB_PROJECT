package com.br.wb.mapper;

import org.springframework.stereotype.Component;

import com.br.wb.dto.UsuarioDTO;
import com.br.wb.domain.Usuario;

@Component
public class UsuarioMapper {
    public Usuario mapToModel(UsuarioDTO dto) {
        Usuario.RNMDocument RNM = null;
        if(dto.RNM() != null) {
            RNM = new Usuario.RNMDocument(dto.RNM().getNumber(), dto.RNM().getClassification(), dto.RNM().getDateOfIssue());
        }
        return new Usuario(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.password(),
                dto.CPF(),
                RNM,
                dto.country(),
                dto.phoneNumber()
        );
    }
}
