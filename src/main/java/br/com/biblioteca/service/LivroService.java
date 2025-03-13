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
            throw new RuntimeException("O título do livro é obrigatório");
        }

        if (livro.getAutor() == null || livro.getAutor().isEmpty()) {
            throw new RuntimeException("O autor do livro é obrigatório");
        }

        if (livro.getIsbn() == null || livro.getIsbn().isEmpty()) {
            throw new RuntimeException("O isbn do livro é obrigatório");
        }

        if (livro.getStatus() == null || livro.getStatus().isEmpty()) {
            throw new RuntimeException("O status do livro é obrigatório");
        }

    }

    public Livro buscarLivroPorId(Long id) {
        Livro livro = this.livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        return livro;
    }

    public List<Livro> buscarLivros() {
        return livroRepository.findAll();
    }

    public Livro atualizarLivro(Long id, Livro livroAtualizado){

        if (id == null){
            throw new RuntimeException("Id inválido");
        }
        validarLivro(livroAtualizado);

        Livro livroAtual = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        livroAtual.setTitulo(livroAtualizado.getTitulo());
        livroAtual.setAutor(livroAtualizado.getAutor());
        livroAtual.setGenero(livroAtualizado.getGenero());
        livroAtual.setEditora(livroAtualizado.getEditora());
        livroAtual.setIsbn(livroAtualizado.getIsbn());
        livroAtual.setStatus(livroAtualizado.getStatus());

        return livroRepository.save(livroAtual);

    }

    public Livro atualizaStatusLivro(Long id, Livro livro){
        if (id == null){
            throw new RuntimeException("Id inválido");
        }
        validarLivro(livro);

        Livro livroAtual = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        if("Emprestado".equals(livroAtual.getStatus())){
            livroAtual.setStatus("Disponivel");
        }else{
            livroAtual.setStatus("Emprestado");
        }

        return livroRepository.save(livroAtual);
    }

    public void deletarLivro(Long id){
      
        if (!livroRepository.existsById(id)){
            throw new RuntimeException("Livro não encontrado");
        }

        livroRepository.deleteById(id);

    }

}
