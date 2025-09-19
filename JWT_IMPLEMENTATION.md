# Implementação JWT - Sistema de Cadastro

## 📋 Visão Geral

Este projeto implementa autenticação JWT (JSON Web Token) para um sistema de cadastro de estudantes e empresas. O JWT permite autenticação stateless e segura entre cliente e servidor.

## 🏗️ Arquitetura Implementada

### 1. **Configuração JWT**
- `JwtConfig.java` - Configurações centralizadas do JWT
- `JwtUtil.java` - Utilitário para geração e validação de tokens

### 2. **Entidades e DTOs**
- `User.java` - Entidade unificada para estudantes e empresas
- `LoginRequest.java` - DTO para requisições de login
- `RegisterRequest.java` - DTO para requisições de registro
- `AuthResponse.java` - DTO para respostas de autenticação

### 3. **Camada de Serviço**
- `UserService.java` - Serviço para gerenciamento de usuários
- `AuthService.java` - Serviço para autenticação e autorização

### 4. **Segurança**
- `JwtAuthenticationFilter.java` - Filtro para interceptar e validar tokens
- `SecurityConfig.java` - Configuração principal do Spring Security

### 5. **Controllers**
- `AuthController.java` - Endpoints de autenticação
- `TestController.java` - Endpoints de teste para validar JWT

## 🚀 Como Usar

### 1. **Registro de Usuário**

#### Registrar Estudante:
```bash
POST /api/auth/register/student
Content-Type: application/json

{
    "name": "João Silva",
    "email": "joao@email.com",
    "password": "123456",
    "documentNumber": "123.456.789-00",
    "phone": "(11) 99999-9999",
    "address": "Rua A, 123"
}
```

#### Registrar Empresa:
```bash
POST /api/auth/register/company
Content-Type: application/json

{
    "name": "Empresa ABC",
    "email": "contato@empresa.com",
    "password": "123456",
    "documentNumber": "12.345.678/0001-90",
    "phone": "(11) 3333-4444",
    "address": "Av. B, 456"
}
```

### 2. **Login**

```bash
POST /api/auth/login
Content-Type: application/json

{
    "email": "joao@email.com",
    "password": "123456"
}
```

**Resposta:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "email": "joao@email.com",
    "name": "João Silva",
    "userType": "STUDENT",
    "userId": 1
}
```

### 3. **Acessar Endpoints Protegidos**

```bash
GET /api/test/protected
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 4. **Endpoints de Teste**

- `GET /api/test/public` - Endpoint público (não requer autenticação)
- `GET /api/test/protected` - Endpoint protegido (requer JWT)
- `GET /api/test/student-only` - Apenas para estudantes
- `GET /api/test/company-only` - Apenas para empresas

## 🔧 Configuração

### application.yml
```yaml
jwt:
  secret: mySecretKey12345678901234567890123456789012345678901234567890
  expiration: 86400000 # 24 horas
  header: Authorization
  prefix: "Bearer "
```

### Banco de Dados
O sistema usa uma tabela unificada `users` que substitui as tabelas separadas de `estudantes` e `empresas`.

## 🔐 Segurança

### Características de Segurança:
- **Senhas criptografadas** com BCrypt
- **Tokens JWT** com assinatura HMAC-SHA256
- **Validação de tokens** em cada requisição
- **CORS configurado** para requisições cross-origin
- **Autorização baseada em roles** (STUDENT, COMPANY)

### Headers de Segurança:
- `Authorization: Bearer <token>`
- Tokens expiram em 24 horas
- Validação automática de expiração

## 📊 Fluxo de Autenticação

1. **Registro/Login** → Gera JWT
2. **Cliente armazena** o token
3. **Cliente envia** token no header Authorization
4. **Filtro JWT** valida o token
5. **Spring Security** autentica o usuário
6. **Controller** processa a requisição

## 🛠️ Desenvolvimento

### Estrutura de Pastas:
```
src/main/java/sys/sistema_cadastro_empresa_cliente/
├── config/
│   ├── JwtConfig.java
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   └── TestController.java
├── dto/
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   └── RegisterRequest.java
├── entity/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── security/
│   └── JwtAuthenticationFilter.java
└── service/
    ├── AuthService.java
    └── UserService.java
```

### Dependências Maven:
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

## ✅ Próximos Passos

1. **Testar** os endpoints com Postman/Insomnia
2. **Configurar** chave secreta mais segura em produção
3. **Implementar** refresh tokens se necessário
4. **Adicionar** validações de CPF/CNPJ
5. **Implementar** endpoints específicos para estudantes e empresas
6. **Adicionar** logs de auditoria
7. **Configurar** rate limiting

## 🐛 Troubleshooting

### Erro: "Token inválido"
- Verifique se o token está sendo enviado corretamente
- Confirme se o token não expirou
- Valide a chave secreta

### Erro: "Usuário não encontrado"
- Verifique se o email existe no banco
- Confirme se a senha está correta

### Erro: "Acesso negado"
- Verifique se o usuário tem a role necessária
- Confirme se o token é válido
