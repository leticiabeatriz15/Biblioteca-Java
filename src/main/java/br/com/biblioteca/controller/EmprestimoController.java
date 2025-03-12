package br.com.biblioteca.controller;

import br.com.biblioteca.BibliotecaApplication;
import br.com.biblioteca.domain.emprestimo.Emprestimo;
import br.com.biblioteca.domain.emprestimo.EmprestimoDto;
import br.com.biblioteca.domain.emprestimo.EmprestimoRepository;
import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.livro.LivroDto;
import br.com.biblioteca.domain.livro.LivroRepository;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/emprestimos")

public class EmprestimoController {

    private final LivroRepository livroRepository;

    private final LivroController livroController;

    private final BibliotecaApplication bibliotecaApplication;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    EmprestimoController(BibliotecaApplication bibliotecaApplication, LivroController livroController, LivroRepository livroRepository) {
        this.bibliotecaApplication = bibliotecaApplication;
        this.livroController = livroController;
        this.livroRepository = livroRepository;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody @Valid EmprestimoDto dados){
        Emprestimo emprestimo = new Emprestimo(dados);

        emprestimo = emprestimoRepository.save(emprestimo);

        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> buscarEmprestimo() {
        List<Emprestimo> emprestimos = this.emprestimoRepository.findAll();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorId(@PathVariable Long id){
        Emprestimo emprestimo = this.emprestimoRepository.findById(id).orElse(null);

        if(emprestimo == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(emprestimo);
    }

    @PutMapping

    public ResponseEntity<Emprestimo> atualizarLivro(@PathVariable Long id, @RequestBody EmprestimoDto dados){
        Emprestimo emprestimo = this.emprestimoRepository.findById(id).orElse(null);

        if (emprestimo == null) {
            return ResponseEntity.notFound().build();
        }

        emprestimo.setLivro(dados.livro());
        emprestimo.setUsuario(dados.usuario());
        emprestimo.setDataEmprestimo(dados.dataEmprestimo());
        emprestimo.setDataEmprestimo(dados.dataDevolucao());

        this.emprestimoRepository.save(emprestimo);
        return ResponseEntity.ok(emprestimo);
    }

}
