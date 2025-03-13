package br.com.biblioteca.controller;

import java.util.List;

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
import br.com.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // CRUD

    // criação de usuario

    @PostMapping

    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid  Usuario usuario) {

        usuarioService.adicionarUsuario(usuario);

        return ResponseEntity.ok(usuario);
    }

    // selação de usuario

    @GetMapping

    public ResponseEntity<List<Usuario>> buscarUsuarios() {

        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {

        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);

    }

    // atualização de usuario

    @PutMapping("/{id}")

    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado){

        Usuario usuario = usuarioService.atualizaUsuario(id, usuarioAtualizado);

        return ResponseEntity.ok(usuario);

    }

    // deleção de usuario

    @DeleteMapping("/{id}")

    public ResponseEntity<String> deletarUsuario(@PathVariable Long id){

        usuarioService.deletarUsuario(id);

        return ResponseEntity.ok("Usuario deletado com sucesso");

    } 

}
