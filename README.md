# User Registration API

API RESTful para cadastro e gerenciamento de usuários, integração com banco de dados PostgreSQL.

---

## Tecnologias Utilizadas
- **Linguagem:** Java - version 21
- **Framework:** Spring Boot  
- **Banco de Dados:** PostgreSQL  
- **ORM:** JPA / Hibernate
- **Documentação/OpenAPI:** Swagger

---

## 📋 Funcionalidades
A API permite realizar as seguintes operações (CRUD):

| Método | Endpoint         | Descrição                         |
|--------|------------------|-----------------------------------|
| `POST` | `/users`         | Cadastrar novo usuário            |
| `GET`  | `/users`         | Listar todos os usuários          |
| `GET`  | `/users/{id}`    | Buscar usuário pelo ID            |
| `PUT`  | `/users/{id}`    | Atualizar dados de um usuário     |
| `DELETE` | `/users/{id}`  | Excluir um usuário do sistema     |

---

