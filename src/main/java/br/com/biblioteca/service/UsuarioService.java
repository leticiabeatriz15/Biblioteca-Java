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
            System.out.println("Nome do usuário não pode estar vazio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            System.out.println("Email do usuário não pode estar vazio");
        }
    }

    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElse(null);

        return usuario;

    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizaUsuario(Long id, Usuario usuarioAtualizado){
        if (id == null){
            System.out.println("ID inválido");
        }

        validarUsuario(usuarioAtualizado);

        Usuario usuarioAtual = usuarioRepository.findById(id).orElse(null);

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
