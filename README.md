# CRM-API

Uma API RESTful para gerenciamento de um sistema de CRM (Customer Relationship Management).

---

## 🚀 Sobre o Projeto

Este projeto é uma API de back-end desenvolvida com Spring Boot, projetada para gerenciar clientes, projetos, tarefas e faturas. Ele serve como o motor de um futuro aplicativo de CRM, fornecendo endpoints para todas as operações de criação, leitura, atualização e exclusão (CRUD).

## ✨ Funcionalidades

- **Gerenciamento de Clientes**: CRUD completo para clientes.
- **Gerenciamento de Projetos**: Criação de projetos vinculados a um cliente e atualização de seus status.
- **Gerenciamento de Tarefas**: Criação de tarefas vinculadas a um projeto e controle de seus status.
- **Gerenciamento de Faturas**: Emissão de faturas ligadas a clientes e controle de seus status.
- **Validação de Dados**: Regras de negócio para garantir a integridade dos dados (ex: um projeto deve sempre ser associado a um cliente existente).

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.6**
- **Maven**
- **PostgreSQL**: Banco de dados relacional para persistência de dados.
- **Spring Data JPA**: Para fácil acesso e manipulação do banco de dados.
- **Lombok**: Para reduzir o código boilerplate (getters, setters, etc.).
- **Springdoc OpenAPI**: Para documentação automática e interativa da API.

---

## ⚙️ Pré-requisitos

Antes de começar, certifique-se de que você tenha o seguinte software instalado em sua máquina:

* **Java Development Kit (JDK) 17 ou superior**
* **Maven 3.6+**
* **Docker** (para rodar o banco de dados PostgreSQL)



## 📖 Endpoints da API

A documentação interativa completa pode ser acessada em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

Aqui estão os principais endpoints:

### Clientes (`/api/clients`)
| Método | URL | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/clients` | Cria um novo cliente. |
| `GET` | `/api/clients` | Retorna todos os clientes. |
| `GET` | `/api/clients/{id}` | Retorna um cliente por ID. |
| `PUT` | `/api/clients/{id}` | Atualiza um cliente por ID. |
| `DELETE` | `/api/clients/{id}` | Deleta um cliente por ID. |

### Projetos (`/api/projects`)
| Método | URL | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/projects` | Cria um novo projeto (associa a um cliente). |
| `GET` | `/api/projects` | Retorna todos os projetos. |
| `GET` | `/api/projects/{id}` | Retorna um projeto por ID. |
| `PUT` | `/api/projects/{id}` | Atualiza um projeto por ID. |
| `DELETE` | `/api/projects/{id}` | Deleta um projeto por ID. |

### Tarefas (`/api/tasks`)
| Método | URL | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/tasks` | Cria uma nova tarefa (associa a um projeto). |
| `GET` | `/api/tasks` | Retorna todas as tarefas. |
| `GET` | `/api/tasks/{id}` | Retorna uma tarefa por ID. |
| `PUT` | `/api/tasks/{id}` | Atualiza uma tarefa por ID. |
| `DELETE` | `/api/tasks/{id}` | Deleta uma tarefa por ID. |

### Faturas (`/api/invoices`)
| Método | URL | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/invoices` | Cria uma nova fatura (associa a um cliente). |
| `GET` | `/api/invoices` | Retorna todas as faturas. |
| `GET` | `/api/invoices/{id}` | Retorna uma fatura por ID. |
| `PUT` | `/api/invoices/{id}` | Atualiza uma fatura por ID. |
| `DELETE` | `/api/invoices/{id}` | Deleta uma fatura por ID. |

---

## 📝 Autores

* [Elio Junior](https://github.com/ejunior89) - <3