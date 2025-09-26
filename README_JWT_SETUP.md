
---

## 🚀 **Como Executar o Projeto**

### **1. Pré-requisitos**
```bash
# Instalar PostgreSQL
# No Windows: https://www.postgresql.org/download/windows/
# No macOS: brew install postgresql
# No Linux: sudo apt install postgresql postgresql-contrib
```

### **2. Configurar Banco de Dados**
```sql
-- Conectar ao PostgreSQL como admin
psql -U postgres

-- Criar o banco de dados
CREATE DATABASE crm_db;

-- Criar usuário (opcional, caso não queira usar postgres)
CREATE USER crm_user WITH ENCRYPTED PASSWORD 'sua_senha';
GRANT ALL PRIVILEGES ON DATABASE crm_db TO crm_user;

\q
```

### **3. Configurar application.properties**
Se necessário, ajuste as credenciais do banco:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/crm_db
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
```

### **4. Executar o Projeto**
```bash
# Compilar
./mvnw clean compile

# Executar
./mvnw spring-boot:run
```

---

## 🧪 **Testando o Sistema JWT**

### **1. Registrar um Usuário**
```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{
    "username": "admin",
    "password": "admin123"
}'
```

**Resposta esperada:**
```json
{
    "message": "User registered successfully",
    "username": "admin", 
    "id": 1
}
```

### **2. Fazer Login**
```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
    "username": "admin",
    "password": "admin123"
}'
```

**Resposta esperada:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer"
}
```

### **3. Acessar Endpoint Protegido**
```bash
curl -X GET http://localhost:8080/api/clients \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## 🔧 **Arquitetura do Sistema JWT**

```
📁 Fluxo de Autenticação:
   │
   ├── 1️⃣ AuthController (/api/auth/login)
   │    └── Recebe credenciais
   │
   ├── 2️⃣ AuthService
   │    ├── Valida credenciais via AuthenticationManager
   │    └── Gera token JWT via JwtService
   │
   ├── 3️⃣ JwtService
   │    ├── Cria token com claims, subject e expiração
   │    └── Assina com chave secreta
   │
   └── 4️⃣ Response: {"token": "jwt_token", "type": "Bearer"}

📁 Fluxo de Autorização:
   │
   ├── 1️⃣ JwtAuthenticationFilter
   │    ├── Intercepta todas as requests
   │    ├── Extrai token do header "Authorization: Bearer ..."
   │    └── Valida token via JwtService
   │
   ├── 2️⃣ SecurityConfig
   │    └── Aplica filtro antes de UsernamePasswordAuthenticationFilter
   │
   └── 3️⃣ Endpoints protegidos são acessados se token válido
```

---

## 🛡️ **Recursos de Segurança Implementados**

- ✅ **Tokens JWT** com assinatura HMAC SHA-256
- ✅ **Expiração de Token** configurável (24h por padrão)
- ✅ **Senhas Criptografadas** com BCrypt
- ✅ **Filtro de Autenticação** personalizado
- ✅ **Validação de Token** em todas as requests
- ✅ **Endpoints Públicos** configurados (/login, /register)
- ✅ **Tratamento de Erros** com status HTTP apropriados

---

## 🔑 **Configurações JWT**

```properties
# Chave secreta Base64 (256 bits mínimo para HS256)
jwt.secret=Y3JtX2FwaV9zZWNyZXRfa2V5XzI1Nl9iaXRzX21pbmltdW1fZm9yX3NlY3VyaXR5XzEyMw==

# Expiração: 24 horas em milissegundos
jwt.expiration=86400000
```

⚠️ **IMPORTANTE**: Em produção, use variáveis de ambiente para a chave secreta!

---

## 📚 **Endpoints Disponíveis**

| Método | Endpoint | Público | Descrição |
|--------|----------|---------|-----------|
| POST | `/api/auth/register` | ✅ | Registrar usuário |
| POST | `/api/auth/login` | ✅ | Autenticar e obter token |
| GET | `/api/clients` | ❌ | Listar clientes (requer token) |
| GET | `/api/projects` | ❌ | Listar projetos (requer token) |
| GET | `/api/tasks` | ❌ | Listar tarefas (requer token) |
| GET | `/api/invoices` | ❌ | Listar faturas (requer token) |

---

## 🎉 **Status do Projeto**

✅ **JWT Completamente Funcional**  
✅ **Autenticação e Autorização Implementadas**  
✅ **Filtros de Segurança Configurados**  
✅ **Endpoints de Auth Funcionando**  
✅ **Validação de Token Ativa**  
✅ **Criptografia de Senhas Habilitada**  

**O sistema JWT está pronto para uso!** 🚀