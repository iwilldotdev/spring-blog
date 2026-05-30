# Spring Blog

API REST para um mini blog, construída com Spring Boot.

## Stack

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Spring Security
- H2 (banco em memória)

## O que já foi implementado

### Configuração

- Aplicação Spring Boot inicializada na porta `8080`
- Banco H2 em memória (`springblog`) com console habilitado em `/h2-console`
- JPA configurado com `ddl-auto=update` e SQL formatado no log

### Modelo de domínio

Entidades JPA mapeadas para o domínio do blog:

| Entidade | Descrição |
|----------|-----------|
| `User` | Usuário com nome, e-mail, username, senha e papel (`Role`) |
| `Post` | Publicação com título, conteúdo, data e autor |
| `Comment` | Comentário vinculado a um post e a um usuário |
| `Tag` | Tag reutilizável associada a posts |

Relacionamentos principais:

- `User` → `Post` (1:N)
- `User` → `Comment` (1:N)
- `Post` → `Comment` (1:N)
- `Post` ↔ `Tag` (N:N)

### Enum

- `Role`: `ADMIN` e `USER`

### Repositories JPA

Repositórios Spring Data JPA para persistência das entidades:

- `UserRepository` — inclui verificação de e-mail e username duplicados
- `TagRepository` — inclui verificação de nome duplicado
- `PostRepository`
- `CommentRepository`

### Services

Camada de serviços com interfaces e implementações:

| Interface | Implementação |
|-----------|---------------|
| `UserService` | `UserServiceImpl` |
| `TagService` | `TagServiceImpl` |
| `PostService` | `PostServiceImpl` |
| `CommentService` | `CommentServiceImpl` |

As implementações ficam no pacote `services.imp`, utilizam `@Service` e injetam os repositórios com `@Autowired`. Cada serviço valida existência de registros antes de salvar, atualizar ou excluir.

### Controllers REST (CRUD)

Operações CRUD implementadas para todas as entidades, com endpoints padronizados:

| Operação | Método HTTP | Caminho |
|----------|-------------|---------|
| Create | `POST` | `/{recurso}/save` |
| Read (lista) | `GET` | `/{recurso}/getAll` |
| Read (por id) | `GET` | `/{recurso}/getById/{id}` |
| Update | `POST` | `/{recurso}/update` |
| Delete | `DELETE` | `/{recurso}/delete/{id}` |

Recursos disponíveis: `/users`, `/tags`, `/posts`, `/comments`.

Exemplos:

- `POST /users/save` — cria usuário (retorna `409` se já existir)
- `GET /posts/getAll` — lista todos os posts
- `GET /comments/getById/1` — busca comentário por id
- `POST /tags/update` — atualiza tag (id no body)
- `DELETE /users/delete/1` — remove usuário por id

## O que ainda não foi implementado

- Configuração de segurança (autenticação/autorização e endpoints públicos)
- DTOs e mapeamento de respostas
- Tratamento global de exceções
- Testes de integração dos endpoints

## Como executar

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

> **Nota:** o Spring Security está ativo por padrão e exige autenticação para acessar os endpoints. A senha gerada aparece no log ao iniciar a aplicação.

### Console H2

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:springblog`
- Usuário: `sa`
- Senha: *(vazia)*

## Estrutura do projeto

```
src/main/java/com/descomplica/spring_blog/
├── SpringBlogApplication.java
├── controllers/
│   ├── CommentController.java
│   ├── PostController.java
│   ├── TagController.java
│   └── UserController.java
├── enums/
│   └── Role.java
├── models/
│   ├── Comment.java
│   ├── Post.java
│   ├── Tag.java
│   └── User.java
├── repositories/
│   ├── CommentRepository.java
│   ├── PostRepository.java
│   ├── TagRepository.java
│   └── UserRepository.java
└── services/
    ├── CommentService.java
    ├── PostService.java
    ├── TagService.java
    ├── UserService.java
    └── imp/
        ├── CommentServiceImpl.java
        ├── PostServiceImpl.java
        ├── TagServiceImpl.java
        └── UserServiceImpl.java
```
