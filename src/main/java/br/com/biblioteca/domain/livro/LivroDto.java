package br.com.biblioteca.domain.livro;

import jakarta.validation.constraints.NotBlank;

public record LivroDto(@NotBlank String titulo, @NotBlank String autor, @NotBlank String genero, @NotBlank String editora, @NotBlank int anoPublicacao, @NotBlank String isbn, @NotBlank String status) {
    
}