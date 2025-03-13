package br.com.biblioteca.domain.emprestimo;

import java.time.LocalDate;

import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column(nullable=false)
    private LocalDate dataDevolucao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;

    public Emprestimo(){
        
    }

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(Usuario usuario, Livro livro, Long id, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.usuario = usuario;
        this.livro = livro;
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
}
