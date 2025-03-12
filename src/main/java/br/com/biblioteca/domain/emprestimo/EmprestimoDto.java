package br.com.biblioteca.domain.emprestimo;

import java.time.LocalDate;

import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

public record EmprestimoDto (@NotBlank Usuario usuario, @NotBlank Livro livro, @NotBlank LocalDate dataEmprestimo, @NotBlank LocalDate dataDevolucao, @NotBlank String status){
    
}
