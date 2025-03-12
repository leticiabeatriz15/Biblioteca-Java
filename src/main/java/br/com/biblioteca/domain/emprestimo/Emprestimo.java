package br.com.biblioteca.domain.emprestimo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    



    
}
