# 🧵 ForumHub API

API REST desenvolvida com **Spring Boot 3** para gerenciamento de tópicos de fórum, com autenticação básica, validações de negócio e persistência em banco de dados.

Projeto construído seguindo boas práticas de arquitetura em camadas (**Controller, Service, Repository, DTO**).

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security (Basic Auth)
- Hibernate
- Flyway
- MySQL
- Maven

---

## 🔐 Segurança

A API é protegida por **Basic Authentication**.

Credenciais padrão para acesso:

- **Usuário:** `admin`
- **Senha:** `123456`

Todas as requisições (exceto configurações internas) exigem autenticação.

---

## ✅ Funcionalidades

- ✔️ Cadastro de tópicos
- ✔️ Listagem de todos os tópicos
- ✔️ Detalhamento de tópico por ID
- ✔️ Atualização de tópico
- ✔️ Exclusão de tópico
- ✔️ Validação de dados
- ✔️ Regras de negócio para evitar duplicidade
- ✔️ Retorno de status HTTP adequados
- ✔️ Autenticação com login e senha

---

## 📂 Estrutura do projeto

```text
src/
└── main/
    └── java/
        └── br/
            └── com/
                └── alura/
                    └── forum/
                        └── forumhub/
                            ├── ForumhubApplication.java
                            │
                            ├── config/
                            │   └── SecurityConfig.java
                            │
                            └── topico/
                                ├── Topico.java
                                │
                                ├── controller/
                                │   └── TopicoController.java
                                │
                                ├── dto/
                                │   ├── TopicoRequest.java
                                │   ├── TopicoUpdateRequest.java
                                │   └── TopicoResponse.java
                                │
                                ├── repository/
                                │   └── TopicoRepository.java
                                │
                                └── service/
                                    └── TopicoService.java
```
---

## 🔁 Endpoints da API
### 📌 Criar tópico

POST /topicos

```
JSON
{
  "titulo": "Dúvida Spring Boot",
  "mensagem": "Erro ao subir aplicação",
  "autor": "Layla",
  "curso": "Spring Boot"
}
```
📌 Listar tópicos

- GET /topicos

📌 Detalhar tópico por ID

- GET /topicos/{id}

📌 Atualizar tópico

- PUT /topicos/{id}

```
JSON
{
  "titulo": "Título atualizado",
  "mensagem": "Mensagem atualizada",
  "autor": "Layla",
  "curso": "Spring Boot"
}
```

📌 Excluir tópico

- DELETE /topicos/{id}

---

## 🧠 Regras de negócio

- Não é permitido cadastrar ou atualizar um tópico com o mesmo título e mensagem

- As operações de PUT e DELETE verificam se o tópico existe

- Caso o tópico não exista, a API retorna 404 - Not Found

- Conflitos de duplicidade retornam 409 - Conflict

---

## 🧪 Testes da API

Os testes podem ser realizados utilizando:

- Insomnia

- Postman

Lembre-se de configurar Basic Auth com as credenciais da aplicação.

---

## ▶️ Como executar o projeto
### Pré-requisitos

- Java 17

- MySQL

- Maven

### Passo a passo:

- Clone o repositório

- Configure o banco de dados no arquivo application.properties

- Execute o projeto:

./mvnw spring-boot:run


A aplicação estará disponível em:

http://localhost:8081

---

## 🧾 Migrações de banco

As migrações de banco de dados são gerenciadas via Flyway e executadas automaticamente na inicialização da aplicação.

📌 Status do projeto

✅ Funcional

✅ Segurança aplicada

✅ Pronto para avaliação / entrega

Projeto desenvolvido com foco em boas práticas, organização e clareza de código.

- Ainda em desenvolvimento.