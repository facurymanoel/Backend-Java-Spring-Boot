# API REST - Gerenciamento de Produtos

API REST desenvolvida com **Java e Spring Boot** para realizar o gerenciamento de produtos por meio das operações CRUD. O projeto utiliza **PostgreSQL** para persistência dos dados e conta com testes automatizados para verificar o comportamento das camadas de serviço e dos endpoints.

## 🎯 Objetivo do projeto

O objetivo deste projeto é desenvolver uma API REST para cadastrar e gerenciar produtos, colocando em prática conceitos importantes do desenvolvimento backend com Java e Spring Boot.

Além das operações tradicionais de CRUD, o projeto aplica separação de responsabilidades em camadas, utilização de DTOs para transferência de dados, mapeamento entre objetos, validação de informações e tratamento de exceções.

## ⚙️ Funcionalidades

- **Cadastrar produto:** registra um novo produto.
- **Listar produtos:** retorna os produtos cadastrados.
- **Buscar produto por ID:** consulta um produto específico.
- **Buscar produto pelo nome:** permite localizar produtos pelo nome.
- **Atualizar produto:** altera os dados de um produto existente.
- **Excluir produto:** remove um produto pelo seu identificador.
- **Validar dados:** verifica campos obrigatórios e limites de caracteres.
- **Tratar exceções:** retorna informações estruturadas para erros de validação.

## 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Java 8 | Linguagem de programação |
| Spring Boot 2.7.14 | Estrutura principal da aplicação |
| Spring Web | Criação dos endpoints REST |
| Spring Data JPA | Persistência e acesso aos dados |
| Hibernate | Implementação ORM utilizada pelo JPA |
| PostgreSQL | Banco de dados relacional |
| Maven | Gerenciamento de dependências e build |
| MapStruct | Mapeamento entre entidades e DTOs |
| Lombok | Redução de código repetitivo |
| JUnit 5 | Testes automatizados |
| Mockito | Criação de objetos simulados nos testes |
| MockMvc | Testes dos endpoints HTTP |

## 🧱 Estrutura do projeto

O projeto está organizado em camadas, separando as responsabilidades da aplicação:

```text
src
├── main
│   ├── java/com/everymind
│   │   ├── controller
│   │   ├── dto
│   │   ├── exception
│   │   ├── mapper
│   │   ├── model
│   │   ├── repository
│   │   ├── service
│   │   └── Everymind2024Application.java
│   └── resources
│       ├── application.properties
│       └── messages.properties
│
└── test
    └── java/com/everymind
        ├── builder
        ├── controller
        ├── service
        ├── utils
        └── Everymind2024ApplicationTests.java
```

### Responsabilidade das camadas

- **Controller:** recebe as requisições HTTP e disponibiliza os endpoints da API.
- **Service:** concentra as operações e regras relacionadas ao gerenciamento dos produtos.
- **Repository:** realiza o acesso e a comunicação com o banco de dados por meio do Spring Data JPA.
- **Model:** representa a entidade Produto persistida no banco de dados.
- **DTO:** define os dados utilizados na comunicação da API.
- **Mapper:** realiza a conversão entre a entidade Produto e ProdutoDTO.
- **Exception:** reúne as classes utilizadas no tratamento de exceções e na apresentação de erros.

## 🔗 Endpoints da API

A API utiliza o caminho-base `/produtos`.

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca um produto pelo ID |
| GET | `/produtos/produtoPorNome/{nome}` | Busca produtos pelo nome |
| POST | `/produtos` | Cadastra um produto |
| PUT | `/produtos/{id}` | Atualiza um produto |
| DELETE | `/produtos/{id}` | Exclui um produto |

### Exemplo de cadastro de produto

**Requisição**

```http
POST /produtos
Content-Type: application/json
```

**Corpo JSON**

```json
{
  "nomeProduto": "Notebook",
  "descricaoProduto": "Notebook para uso profissional",
  "precoProduto": 3500.00
}
```

A API recebe os dados, valida os campos definidos no DTO, realiza o mapeamento para a entidade e persiste o produto no banco de dados.

## 🧪 Testes automatizados

O projeto possui testes automatizados utilizando **JUnit 5 e Mockito**, além de **MockMvc** para simular requisições HTTP e verificar as respostas dos endpoints.

Os testes estão organizados principalmente em duas áreas:

- **ProdutoServiceTeste:** testes dos métodos de serviço responsáveis pelas operações de consulta, cadastro, atualização e exclusão.
- **ProdutoControllerTeste:** testes dos endpoints da API, verificando os códigos de resposta e os dados retornados.

Também existe um teste de inicialização do contexto da aplicação.

Para executar os testes pelo Maven:

```bash
./mvnw test
```

No Windows, utilize:

```bash
mvnw.cmd test
```

## ▶️ Como executar o projeto

### Pré-requisitos

- Java 8 instalado.
- PostgreSQL instalado e em execução.
- Maven (opcional, pois o projeto inclui Maven Wrapper).

### 1. Clone o repositório

```bash
git clone URL_DO_SEU_REPOSITORIO
```

### 2. Crie o banco de dados

No PostgreSQL, crie um banco de dados com o nome:

```sql
CREATE DATABASE bd_everymind_2024;
```

### 3. Configure a conexão com o banco

No arquivo `src/main/resources/application.properties`, configure os dados de acesso ao PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_everymind_2024
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
```

**Importante:** informe suas próprias credenciais. Não publique senhas reais no repositório. Para um ambiente de produção, prefira utilizar variáveis de ambiente e uma configuração apropriada de gerenciamento de credenciais.

### 4. Execute a aplicação

Pelo Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Após iniciar, a API estará disponível em:

```text
http://localhost:8080
```

## 📌 Considerações finais

Este projeto foi desenvolvido com o propósito de praticar e consolidar conhecimentos em desenvolvimento backend com Java e Spring Boot, incluindo a construção de APIs REST, persistência de dados com PostgreSQL, organização em camadas, validação, tratamento de exceções e testes automatizados.

---

**Projeto de estudo e desenvolvimento backend com Java.**
