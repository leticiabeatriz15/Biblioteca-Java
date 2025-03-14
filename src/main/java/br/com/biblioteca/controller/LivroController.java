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

import br.com.biblioteca.BibliotecaApplication;
import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.service.LivroService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/livros")

public class LivroController {

    private final UsuarioController usuarioController;

    private final BibliotecaApplication bibliotecaApplication;

    @Autowired
    private LivroService livroService;

    LivroController(BibliotecaApplication bibliotecaApplication, UsuarioController usuarioController) {
        this.bibliotecaApplication = bibliotecaApplication;
        this.usuarioController = usuarioController;
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody @Valid Livro livro ) {
       
        Livro livroSalvo = livroService.adicionarLivro(livro);

        return ResponseEntity.ok(livroSalvo);
    }
    
    @GetMapping
    public ResponseEntity <List<Livro>> buscarLivros() {
        List<Livro> livros = livroService.buscarLivros();

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id){

        Livro livro = livroService.buscarLivroPorId(id);

        return ResponseEntity.ok(livro);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody @Valid Livro livroAtualizado){

        Livro livro = livroService.atualizarLivro(id, livroAtualizado);

        return ResponseEntity.ok(livro);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {

        livroService.deletarLivro(id);

        return ResponseEntity.ok("Livro deletado com sucesso!");
        
    }
}