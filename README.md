Seminário Java - Sistema de Biblioteca com Spring Boot
📚 Descrição do Projeto
Este projeto é um sistema de gerenciamento de biblioteca desenvolvido em Spring Boot que permite o controle de livros, empréstimos, usuários e devoluções de forma eficiente e organizada.

✨ Funcionalidades Principais
Gestão de Livros: Cadastro, consulta, atualização e exclusão de livros

Controle de Usuários: Registro e administração de usuários da biblioteca

Sistema de Empréstimos: Registro e acompanhamento de empréstimos de livros

Devoluções: Controle de devoluções e cálculo de multas por atraso

Relatórios: Geração de relatórios de livros mais emprestados, usuários com mais empréstimos, etc.

🛠️ Tecnologias Utilizadas
Java Spring Boot - Framework principal

Spring Data JPA - Persistência de dados

Spring Security - Autenticação e autorização

Banco de Dados - MySQL/PostgreSQL (dependendo da configuração)

Maven/Gradle - Gerenciamento de dependências

Thymeleaf - Template engine para as views (se aplicável)

🚀 Como Executar o Projeto
Clone o repositório:

bash
git clone https://github.com/PHBmarques/Seminario-java.git
Configure o banco de dados no arquivo application.properties

Execute a aplicação:

bash
mvn spring-boot:run
Acesse a aplicação em: http://localhost:8080

📋 Estrutura do Projeto
text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── biblioteca/
│   │           ├── controller/     # Controladores REST
│   │           ├── model/          # Entidades JPA
│   │           ├── repository/     # Interfaces de repositório
│   │           ├── service/        # Lógica de negócio
│   │           └── config/         # Configurações do Spring
│   └── resources/
│       ├── static/                 # Arquivos estáticos (CSS, JS)
│       ├── templates/              # Templates Thymeleaf
│       └── application.properties  # Configurações da aplicação
