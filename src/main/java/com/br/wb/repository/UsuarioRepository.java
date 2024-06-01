package com.br.wb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.wb.domain.Usuario;



@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}