package com.br.wb.controller;

import com.br.wb.domain.Installment;
import com.br.wb.dto.InstallmentDTO;
import com.br.wb.enums.PaymentStatus;
import com.br.wb.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    @Autowired
    private InstallmentService service;

    @GetMapping()
    public ResponseEntity<List<Installment>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Installment>> findAllByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(service.findAllByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<List<Installment>> insert(@RequestBody InstallmentDTO installmentDTO) {
        List<Installment> installmentList = service.insert(installmentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(installmentList.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(installmentList);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<List<Installment>> update(@PathVariable String userId, @RequestBody InstallmentDTO installmentDTO) {
        return ResponseEntity.ok().body(service.update(userId, installmentDTO));
    }

    @PutMapping("/dueDate/{id}")
    public ResponseEntity<Installment> updateDueDate(@PathVariable String id, @RequestBody InstallmentDTO installmentDTO) {
        return ResponseEntity.ok().body(service.updateDueDate(id, installmentDTO));
    }
}
