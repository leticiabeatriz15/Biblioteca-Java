package br.com.biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.biblioteca.BibliotecaApplication;
import br.com.biblioteca.domain.emprestimo.Emprestimo;
import br.com.biblioteca.domain.emprestimo.EmprestimoRepository;
import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.livro.LivroRepository;
import br.com.biblioteca.domain.usuario.Usuario;
import br.com.biblioteca.domain.usuario.UsuarioRepository;

@Service
public class EmprestimoService {

    private final BibliotecaApplication bibliotecaApplication;
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    EmprestimoService(BibliotecaApplication bibliotecaApplication) {
        this.bibliotecaApplication = bibliotecaApplication;
    }

    public Emprestimo registrarEmprestimo(@PathVariable Long idUsuario, @PathVariable Long idLivro) {
      
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        Livro livro = livroRepository.findById(idLivro).orElse(null);

        if ("Emprestado".equals(livro.getStatus())){
            System.out.println( "Livro não disponível para empréstimo!");
        }

        Emprestimo emprestimo = new Emprestimo(usuario, livro, LocalDate.now(), LocalDate.now().plusDays(7));

        livro.setStatus("Emprestado");
        livroRepository.save(livro);
            
        return emprestimoRepository.save(emprestimo);
    
    }

    public List<Emprestimo> buscarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> buscarEmprestimoPorIdUsuario(@PathVariable Long idUsuario){
        
        return emprestimoRepository.findByUsuarioId(idUsuario);

    }

   

    public void devolverLivro(@PathVariable Long idLivro) {

        Emprestimo emprestimo = emprestimoRepository.findByLivroId(idLivro);

        emprestimo.getLivro().setStatus("Disponivel");
        livroRepository.save(emprestimo.getLivro());

    }

}
