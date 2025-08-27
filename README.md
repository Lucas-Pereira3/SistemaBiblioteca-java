# 📚 Seminário Java - Sistema de Biblioteca com Spring Boot

## 🧾 Descrição do Projeto

Este projeto é um **sistema de gerenciamento de biblioteca** desenvolvido com **Spring Boot**.  
Ele permite o controle eficiente de livros, usuários, empréstimos e devoluções, com geração de relatórios relevantes para a administração da biblioteca.

---

## ✨ Funcionalidades Principais

- 📖 **Gestão de Livros**: cadastro, consulta, atualização e exclusão  
- 👥 **Controle de Usuários**: registro e administração de usuários  
- 🔁 **Sistema de Empréstimos**: controle e acompanhamento de livros emprestados  
- 📦 **Devoluções**: cálculo de multas por atraso e registro da devolução  
- 📊 **Relatórios**: livros mais emprestados, usuários com mais movimentações

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia       | Descrição                                  |
|------------------|----------------------------------------------|
| Java             | Linguagem principal                         |
| Spring Boot      | Framework para criação da API REST          |
| Spring Data JPA  | Abstração da camada de persistência         |
| Spring Security  | Autenticação e autorização                  |
| MySQL/PostgreSQL | Banco de dados relacional                   |
| Maven            | Gerenciador de dependências                 |
| Thymeleaf        | Template Engine (para views, se usado)      |

---

## 🚀 Como Executar o Projeto

### 1. Clone o repositório:

git clone https://github.com/PHBmarques/Seminario-java.git

### 2. Acesse o diretório do projeto:

cd Seminario-java

### 3. Configure o banco de dados:

No arquivo src/main/resources/application.properties, edite as propriedades do banco:

spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca

spring.datasource.username=SEU_USUARIO

spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update


💡 Lembre-se de criar o banco de dados no MySQL ou PostgreSQL antes de executar o projeto.

### 4. Execute a aplicação:

mvn spring-boot:run

### 5. Acesse no navegador:

http://localhost:8080

🧠 Exemplo de Endpoints REST
| Método | Endpoint     | Descrição                         |
| ------ | ------------ | --------------------------------- |
| GET    | /livros      | Listar todos os livros            |
| POST   | /livros      | Cadastrar um novo livro           |
| PUT    | /livros/{id} | Atualizar informações de um livro |
| DELETE | /livros/{id} | Remover um livro                  |
| POST   | /emprestimos | Registrar empréstimo              |
| POST   | /devolucoes  | Registrar devolução               |
