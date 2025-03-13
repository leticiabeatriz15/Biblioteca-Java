package br.com.biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.biblioteca.domain.emprestimo.Emprestimo;
import br.com.biblioteca.domain.emprestimo.EmprestimoRepository;
import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.livro.LivroRepository;
import br.com.biblioteca.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Emprestimo registrarEmprestimo(@RequestBody @Valid Emprestimo emprestimo) {
        if (!usuarioRepository.existsById(emprestimo.getUsuario().getId())){
            throw new RuntimeException("Usuário não encontrado!");
        }

        Livro livro = livroRepository.findById(emprestimo.getLivro().getId()).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        if ("Emprestado".equals(livro.getStatus())){
            throw new RuntimeException("Livro não disponível para empréstimo!");
        }

        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));

        livro.setStatus("Emprestado");
        livroRepository.save(livro);
            
        return emprestimoRepository.save(emprestimo);
    
    }

    public List<Emprestimo> buscarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    // public List<Emprestimo> devolucaEmprestimo(){
        
    // }
}
