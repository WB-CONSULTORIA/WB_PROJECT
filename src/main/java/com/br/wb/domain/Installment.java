package com.br.wb.domain;

import com.br.wb.enums.PaymentStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Installment {

    private String id;
    private String userId;
    private Integer numberOfInstallment;
    private String description;
    private Double amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime issueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime dueDate;

    private PaymentStatus paymentStatus;
}
