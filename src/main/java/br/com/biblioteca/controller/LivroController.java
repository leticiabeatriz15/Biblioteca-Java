package br.com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;
import br.com.biblioteca.BibliotecaApplication;
import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.livro.LivroDto;
import br.com.biblioteca.domain.livro.LivroRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/livros")

public class LivroController {

    private final UsuarioController usuarioController;

    private final BibliotecaApplication bibliotecaApplication;
    @Autowired
    private LivroRepository livroRepository;

    LivroController(BibliotecaApplication bibliotecaApplication, UsuarioController usuarioController) {
        this.bibliotecaApplication = bibliotecaApplication;
        this.usuarioController = usuarioController;
    }

    @PostMapping

    public ResponseEntity<Livro> criarLivro(@RequestBody @Valid LivroDto dados) {
        Livro livro = new Livro(dados);
        livroRepository.save(livro);

        return ResponseEntity.ok(livro);
    }
    
    @GetMapping

    public ResponseEntity <List<Livro>> buscarLivros() {
        List<Livro> livros = this.livroRepository.findAll();

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id){

        Livro livro = this.livroRepository.findById(id).orElse(null);

        if(livro == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(livro);
        
    }

    @PutMapping

    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody LivroDto dados){
        Livro livro = this.livroRepository.findById(id).orElse(null);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        livro.setTitulo(dados.titulo());
        livro.setAutor(dados.autor());
        livro.setGenero(dados.genero());
        livro.setEditora(dados.genero());
        livro.setAnoPublicacao(dados.anoPublicacao());
        livro.setIsbn(dados.isbn());
        livro.setStatus(dados.status());

        this.livroRepository.save(livro);

        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        Livro livro = this.livroRepository.findById(id).orElse(null);

        if(livro == null){
            return ResponseEntity.notFound().build();
        }

        this.livroRepository.delete(livro);
        
        return ResponseEntity.ok("Livro" + " " + livro.getTitulo() + " " + "deletado com sucesso!");
    }
}