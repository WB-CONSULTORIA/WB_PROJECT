package com.br.wb.service;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.wb.domain.Usuario;
import com.br.wb.respositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscar(String id) throws BadRequestException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new BadRequestException("Usuario não encontrado"));
    }
    public Usuario atualizar(String id, Usuario novoUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioAtualizado = usuario.get();
            usuarioAtualizado.setName(novoUsuario.getName());
            usuarioAtualizado.setPhoneNumber(novoUsuario.getPhoneNumber());
            usuarioAtualizado.setEmail(novoUsuario.getEmail());
            usuarioAtualizado.setPassword(novoUsuario.getPassword());
            return usuarioRepository.save(usuarioAtualizado);
        } else throw new Exception("Usuario não encontrado");
    }
    public void deletar(String id) {
        usuarioRepository.deleteById(id);
    }
}
