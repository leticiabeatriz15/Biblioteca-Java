package br.com.biblioteca.controller;

import br.com.biblioteca.BibliotecaApplication;
import br.com.biblioteca.domain.emprestimo.Emprestimo;
import br.com.biblioteca.domain.emprestimo.EmprestimoRepository;
import br.com.biblioteca.domain.livro.LivroRepository;
import br.com.biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/emprestimos")

public class EmprestimoController {

    private final LivroRepository livroRepository;

    private final LivroController livroController;

    private final BibliotecaApplication bibliotecaApplication;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    EmprestimoController(BibliotecaApplication bibliotecaApplication, LivroController livroController, LivroRepository livroRepository) {
        this.bibliotecaApplication = bibliotecaApplication;
        this.livroController = livroController;
        this.livroRepository = livroRepository;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody @Valid Emprestimo emprestimo){

        Emprestimo emprestimoRealizado = emprestimoService.registrarEmprestimo(emprestimo);

        return ResponseEntity.ok(emprestimoRealizado);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> buscarEmprestimo() {
        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimos();
        
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

}
