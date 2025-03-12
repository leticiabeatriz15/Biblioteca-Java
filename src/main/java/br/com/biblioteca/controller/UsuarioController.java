package br.com.biblioteca.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.domain.usuario.Usuario;
import br.com.biblioteca.domain.usuario.UsuarioDto;
import br.com.biblioteca.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // CRUD

    // criação de usuario

    @PostMapping

    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDto dados) {
        Usuario usuario = new Usuario(dados);
        usuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);

    }

    // selação de usuario

    @GetMapping

    public ResponseEntity<List<Usuario>> buscarUsuarios() {

        List<Usuario> usuarios = this.usuarioRepository.findAll();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable UUID id) {

        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(usuario);

    }



    // atualização de usuario

    @PutMapping("/{id}")

    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDto dados){

        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();

        }

        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);


        
    }

    // deleção de usuario

    @DeleteMapping("/{id}")

    public ResponseEntity<String> deletarUsuario(@PathVariable UUID id){

        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();

        }

        this.usuarioRepository.delete(usuario);
        return ResponseEntity.ok("Usuário" + " " + usuario.getNome() + " " + "deletado com sucesso! ");

    } 



}
