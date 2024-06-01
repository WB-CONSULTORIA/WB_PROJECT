package com.br.wb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.wb.model.Usuario;



@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}