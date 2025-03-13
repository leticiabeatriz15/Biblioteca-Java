package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.domain.usuario.Usuario;
import br.com.biblioteca.domain.usuario.UsuarioRepository;

@Service

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario adicionarUsuario(Usuario usuario) {
        validarUsuario(usuario);

        return usuarioRepository.save(usuario);
    }

    public void validarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new RuntimeException("Nome do usuário não pode ser vazio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new RuntimeException("Email do usuário não pode ser vazio");
        }
    }

    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return usuario;

    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizaUsuario(Long id, Usuario usuarioAtualizado){
        if (id == null){
            throw new RuntimeException("Id inválido");
        }

        validarUsuario(usuarioAtualizado);

        Usuario usuarioAtual = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        usuarioAtual.setNome(usuarioAtualizado.getNome());
        usuarioAtual.setEmail(usuarioAtualizado.getEmail());

        usuarioRepository.save(usuarioAtual);

        return usuarioAtual;

}

    public void deletarUsuario(Long id) {
        if (id == null){
            throw new RuntimeException("Id inválido");
        }

        if (!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado!");
        }

        usuarioRepository.deleteById(id);
    }

}
