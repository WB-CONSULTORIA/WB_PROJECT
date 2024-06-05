package com.br.wb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties
public record UsuarioDTO(String id,
                         String name,
                         String email,
                         String password,
                         String CPF,
                         RNMDocumentDTO RNM,
                         String country,
                         List<String> phoneNumber) {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RNMDocumentDTO {
        private String number;
        private String classification;
        private String dateOfIssue;
    }
}

