# Biblioteca Pessoal - API RESTful (P2)

API RESTful desenvolvida em Spring Boot para o gerenciamento de uma biblioteca pessoal, criada como parte da avaliação P2 da disciplina de desenvolvimento backend.

## Integrantes do Grupo
* Izabela Calheiras Bastos - RA: 6925107433
* Allefy Gabriel Dos Santos Agostinho - RA: 6925107828
* Luiz Gustavo Vargas Rezende - RA: 6925208016

---

## Tecnologias Utilizadas
* Java 21 (Adoptium JDK)
* Spring Boot 3.4.3
* Spring Data JPA & H2 Database (Banco de dados em memória)
* Springdoc OpenAPI (Swagger) para documentação e testes interativos
* Bean Validation para validação de dados e integridade das entradas

---

## Arquitetura do Projeto
O projeto foi organizado em camadas bem definidas para garantir a separação de responsabilidades:
* `controller`: Gerencia os endpoints HTTP (Requisições e Respostas).
* `service`: Contém as regras de negócio da aplicação.
* `repository`: Interface de comunicação direta com o banco de dados via JPA.
* `model` / `dto`: Definição das entidades do domínio e transferência de dados.

---

## Como Executar a Aplicação

### Pré-requisitos
* Ter o Java 21 instalado em sua máquina.

### Passos para execução (via Terminal/PowerShell):
1. Navegue até a pasta do projeto:
   ```bash
   cd biblioteca-pessoal

   Interface do Swagger (Testes e Endpoints): http://localhost:8080/swagger-ui.html

Console do Banco H2: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:bibliotecadb
User Name: sa
Password: (vazio)

Configure a variável de ambiente do Java 21 e inicie a aplicação com o Maven Wrapper:
Bash
$env:JAVA_HOME="C:\Users\Izabela\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.13.9-hotspot"
.\mvnw.cmd spring-boot:run