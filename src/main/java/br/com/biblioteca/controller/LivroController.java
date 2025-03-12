package br.com.biblioteca.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.livro.LivroDto;
import br.com.biblioteca.domain.livro.LivroRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/livros")

public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @PostMapping

    public ResponseEntity<Livro> criarLivro(@RequestBody @Valid LivroDto dados) {
        Livro livro = new Livro();
        livro = livroRepository.save(livro);

        return ResponseEntity.ok(livro);
    }
    
    @GetMapping

    public ResponseEntity <List<Livro>> buscarLivros() {
        List<Livro> livros = this.livroRepository.findAll();

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable UUID id){

        Livro livro = this.livroRepository.findById(id).orElse(null);

        if(livro == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(livro);
        
    }
    
}
