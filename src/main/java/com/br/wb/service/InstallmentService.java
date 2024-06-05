package com.br.wb.service;

import com.br.wb.domain.Installment;
import com.br.wb.dto.InstallmentDTO;
import com.br.wb.enums.PaymentStatus;
import com.br.wb.respositories.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstallmentService {

    @Autowired
    private InstallmentRepository repository;

    public List<Installment> findAll() {
        return repository.findAll();
    }

    public List<Installment> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId);
    }

    public List<Installment> insert(InstallmentDTO installmentDTO) {

        List<Installment> installmentList = new ArrayList<>();
        for (int i = 0; i < installmentDTO.quantityInstallment(); i++) {
            Integer numberOfInstallment = (i + 1);
            Installment installment = new Installment(
                    null,
                    installmentDTO.userId(),
                    numberOfInstallment,
                    "Parcela " + numberOfInstallment + ": " + installmentDTO.description(),
                    installmentDTO.totalAmount() / installmentDTO.quantityInstallment(),
                    installmentDTO.date().plusMonths(i),
                    installmentDTO.date().plusMonths(i + 1),
                    PaymentStatus.PENDENTE);

            installmentList.add(installment);
        }

        return repository.saveAll(installmentList);
    }

    public List<Installment> update(String userId, InstallmentDTO installmentDTO) {
        List<Installment> installmentList = repository.findAllByUserId(userId);

        List<Installment> installmentListModified = new ArrayList<>();
        int i = 0;
        for (Installment installment : installmentList) {
            installment.setDescription(installmentDTO.description());
            installment.setAmount(installmentDTO.totalAmount() / installmentDTO.quantityInstallment());
            installment.setDueDate(installmentDTO.date().plusMonths(i));

            installmentListModified.add(installment);
            i++;
        }

        return repository.saveAll(installmentListModified);
    }

    public Installment updateDueDate(String id, InstallmentDTO installmentDTO) {
        Installment installment = repository.findById(id).orElseThrow();
        installment.setDueDate(installmentDTO.date());
        return repository.save(installment);
    }
}
