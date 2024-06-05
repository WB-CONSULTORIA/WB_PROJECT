package com.br.wb.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.wb.dto.UsuarioDTO;
import com.br.wb.mapper.UsuarioMapper;
import com.br.wb.domain.Usuario;
import com.br.wb.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO usuarioDto) {
        Usuario model = usuarioMapper.mapToModel(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(model));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok().body(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable String id) throws BadRequestException {
        return ResponseEntity.ok().body(usuarioService.buscar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable String id,
                                             @RequestBody UsuarioDTO usuarioDTO) throws Exception {
        Usuario model = usuarioMapper.mapToModel(usuarioDTO);
        return ResponseEntity.ok().body(usuarioService.atualizar(id, model));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
