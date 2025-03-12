package br.com.biblioteca.domain.emprestimo;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record EmprestimoDto (@NotBlank LocalDate dataEmprestimo, @NotBlank LocalDate dataDevolucao, @NotBlank String status){
    
}
