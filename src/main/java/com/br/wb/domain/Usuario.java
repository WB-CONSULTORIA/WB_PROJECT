package com.br.wb.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Usuario {
	@Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String CPF;
    private RNMDocument RNM;
    private String country;
    private List<String> phoneNumber;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RNMDocument {
        private String number;
        private String classification;
        private String dateOfIssue;
    }
}
