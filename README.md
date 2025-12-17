# 🛒 Ecommerce API com Spring Boot

Este projeto é uma **API REST de Ecommerce**, desenvolvida como **projeto pessoal de estudo**, com foco em **boas práticas de backend, segurança e arquitetura**, simulando um cenário real de mercado.

---

## 🚀 Objetivo do Projeto

Criar uma API robusta para o contexto de um ecommerce, abordando:

* Autenticação e autorização modernas
* Organização clara por camadas
* Modelagem relacional consistente
* Segurança com OAuth2 e JWT
* Documentação acessível via Swagger

---

## 🧱 Stack Utilizada

* **Java**
* **Spring Boot**
* **Spring Security**
* **Spring Authorization Server (OAuth2)**
* **JWT (Self-contained)**
* **PostgreSQL**
* **Docker**
* **Swagger / OpenAPI**

---

## 🔐 Autenticação & Segurança

O projeto implementa um fluxo completo de autenticação:

* Login tradicional com usuário e senha
* Login social com **Google OAuth2**
* Autocadastro de usuários via login social
* Geração de tokens JWT
* Proteção de endpoints com Spring Security

---

## 📦 Funcionalidades Principais

### 👤 Usuários

* Cadastro e autenticação de usuários
* Login com Google (OAuth2)

### 🛍️ Produtos

* Criar produto
* Listar produtos
* Buscar produto por ID
* Atualizar produto
* Remover produto

### 📦 Pedidos

* Criar pedido
* Listar pedidos
* Buscar pedido por ID
* Atualizar pedido
* Remover pedido
* Exportação de pedidos

### 🧾 Itens de Pedido

* Adicionar item ao pedido
* Listar itens
* Buscar item por ID
* Remover item

---

## 📄 Documentação da API

A API é totalmente documentada com **Swagger (OpenAPI)**.

Após subir a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

---

## 🗄️ Modelagem do Banco de Dados

Entidades principais:

* **Users**
* **Products**
* **Orders**
* **Order Items**

O modelo relacional foi pensado para garantir:

* Integridade dos dados
* Escalabilidade

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

* Java 21+
* Maven
* Docker
* PostgreSQL

### Passos

```bash
# Clonar o repositório
git clone https://github.com/williamandradesantana/ecommerce-with-spring-boot.git

# Entrar no projeto
cd ecommerce-with-spring-boot

# Build do projeto
mvn clean package

# Executar a aplicação
java -jar target/*.jar
```
---

## 🎯 Aprendizados

Este projeto permitiu aprofundar conhecimentos em:

* Spring Security avançado
* OAuth2 Authorization Server
* JWT e controle de acesso
* Arquitetura REST
* Integração com serviços externos (Google OAuth2)

---

## 👨‍💻 Autor

**William Santana**
Projeto pessoal voltado a estudos e evolução como desenvolvedor backend.

🔗 GitHub: [https://github.com/williamandradesantana](https://github.com/williamandradesantana)
