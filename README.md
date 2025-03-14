# Sistema de Gerenciamento da Biblioteca

## Descrição

Este projeto implementa um sistema simples de gerenciamento de empréstimos de livros em uma biblioteca. O sistema permite adicionar, atualizar e excluir livros e usuarios e gerenciar operações de empréstimos.

## Funcionalidades
- Registrar e gerenciar livros
- Registrar e gerenciar usuarios
- Emprestar livros para usuários
- Devolver livros
- Visualizar histórico de empréstimos

## Tecnologias Utilizadas
- Java
- Spring Boot
- Maven
- JPA (Java Persistence API)

### Gerenciamento de Empréstimos
- **Criar Empréstimo**
  - `POST /emprestimos/{idUsuario}/{idLivro}`
  - Cria um novo empréstimo para o usuário e livro especificados.

- **Obter Empréstimos**
  - `GET /emprestimos`
  - Recupera todos os empréstimos ou empréstimos para um usuário específico se `idUsuario` for fornecido.

- **Devolver Livro**
  - `PUT /emprestimos/{idLivro}`
  - Devolve o livro especificado e atualiza seu status.

### Gerenciamento de Livros
- **Adicionar Livro**
  - `POST /livros`
  - Adiciona um novo livro à biblioteca.

- **Atualizar Livro**
  - `PUT /livros/{id}`
  - Atualiza os detalhes do livro especificado.

- **Excluir Livro**
  - `DELETE /livros/{id}`
  - Exclui o livro especificado da biblioteca.

- **Obter Livro por ID**
  - `GET /livros/{id}`
  - Recupera os detalhes de um livro específico.

- **Obter Todos os Livros**
  - `GET /livros`
  - Recupera uma lista de todos os livros na biblioteca.

### Gerenciamento de Usuarios
- **Adicionar Usuarios**
  - `POST /usuarios`
  - Adiciona um novo usuario à biblioteca.

- **Atualizar Usuarios**
  - `PUT /usuarios/{id}`
  - Atualiza os detalhes do usuario especificado.

- **Excluir Usuario**
  - `DELETE /usuarios/{id}`
  - Exclui o usuarios especificado da biblioteca.

- **Obter Usuario por ID**
  - `GET /usuarios/{id}`
  - Recupera os detalhes de um usuario específico.

- **Obter Todos os Usuarios**
  - `GET /usuarios`
  - Recupera uma lista de todos os usuarios na biblioteca.

**Developers: Emanuelle de Carvalho Brito e Letícia Beatriz de Holanda Vieira**


