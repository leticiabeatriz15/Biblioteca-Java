package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.BibliotecaApplication;
import br.com.biblioteca.domain.livro.Livro;
import br.com.biblioteca.domain.livro.LivroRepository;

@Service
public class LivroService {

    private final BibliotecaApplication bibliotecaApplication;

    @Autowired
    private LivroRepository livroRepository;

    LivroService(BibliotecaApplication bibliotecaApplication) {
        this.bibliotecaApplication = bibliotecaApplication;
    }

    public Livro adicionarLivro(Livro livro) {
        validarLivro(livro);
        return livroRepository.save(livro);
    }

    public void validarLivro(Livro livro){
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            System.out.println("O titulo do livro é obrigatório");
        }

        if (livro.getAutor() == null || livro.getAutor().isEmpty()) {
            System.out.println("O autor do livro é obrigatório");
        }

        if (livro.getIsbn() == null || livro.getIsbn().isEmpty()) {
            System.out.println("O isbn do livro é obrigatório");
        }

        if (livro.getStatus() == null || livro.getStatus().isEmpty()) {
            System.out.println("O status do livro é obrigatório");
        }

    }

    public Livro buscarLivroPorId(Long id) {
        Livro livro = this.livroRepository.findById(id).orElse(null);

        return livro;
    }

    public List<Livro> buscarLivros() {
        return livroRepository.findAll();
    }

    public Livro atualizarLivro(Long id, Livro livroAtualizado){

        if (id == null){
            System.out.println("ID inválido");
        }
        validarLivro(livroAtualizado);

        Livro livroAtual = livroRepository.findById(id).orElse(null);

        livroAtual.setTitulo(livroAtualizado.getTitulo());
        livroAtual.setAutor(livroAtualizado.getAutor());
        livroAtual.setGenero(livroAtualizado.getGenero());
        livroAtual.setEditora(livroAtualizado.getEditora());
        livroAtual.setIsbn(livroAtualizado.getIsbn());
        livroAtual.setStatus(livroAtualizado.getStatus());

        return livroRepository.save(livroAtual);

    }

    public void deletarLivro(Long id){
      
        if (!livroRepository.existsById(id)){
            System.out.println("Livro não encontrado");
        }

        livroRepository.deleteById(id);

    }

}
