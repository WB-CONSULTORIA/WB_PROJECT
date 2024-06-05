package com.br.wb.respositories;

import com.br.wb.domain.Installment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InstallmentRepository extends MongoRepository<Installment, String> {

    public List<Installment> findAllByUserId(String userId);

    public void deleteAllByUserId(String userId);
}
