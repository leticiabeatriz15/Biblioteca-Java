package br.com.biblioteca.domain.emprestimo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByUsuarioId(Long id);
    
}
