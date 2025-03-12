package br.com.biblioteca.domain.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
}
