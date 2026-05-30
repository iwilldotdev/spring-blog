# Spring Blog

API REST em desenvolvimento para um mini blog, construída com Spring Boot.

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

### Estrutura de pacotes

Pacotes `controllers` e `services` já criados como base para as próximas camadas da API.

## O que ainda não foi implementado

- Controllers REST
- Services e regras de negócio
- Repositories JPA
- Configuração de segurança (autenticação/autorização)
- Endpoints públicos da API

## Como executar

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

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
├── enums/
│   └── Role.java
├── models/
│   ├── Comment.java
│   ├── Post.java
│   ├── Tag.java
│   └── User.java
└── services/
```
