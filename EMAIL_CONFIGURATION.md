# 📧 Configuração de Email - Sistema de Cadastro

## 🎯 Funcionalidade Implementada

O sistema agora envia automaticamente um email de boas-vindas para o usuário após o registro bem-sucedido.

## ⚙️ Configuração Necessária

### 1. **Configurar Gmail (Recomendado)**

#### **Passo 1: Ativar Autenticação de 2 Fatores**
1. Acesse: https://myaccount.google.com/security
2. Ative a "Verificação em duas etapas"

#### **Passo 2: Gerar Senha de App**
1. Acesse: https://myaccount.google.com/apppasswords
2. Selecione "Email" e "Outro (nome personalizado)"
3. Digite "Sistema Cadastro" e clique "Gerar"
4. **COPIE A SENHA GERADA** (16 caracteres)

#### **Passo 3: Configurar Variáveis de Ambiente**
Crie um arquivo `.env` na raiz do projeto:
```bash
MAIL_USERNAME=testedaaplication12@gmail.com
MAIL_PASSWORD=senhafeia123
FRONTEND_URL=http://localhost:3000
```

### 2. **Configuração no application.yml**

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME:seu-email@gmail.com}
    password: ${MAIL_PASSWORD:sua-senha-app}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
          ssl:
            trust: smtp.gmail.com

app:
  frontend:
    url: ${FRONTEND_URL:http://localhost:3000}
```

### 3. **Outros Provedores de Email**

#### **Outlook/Hotmail:**
```yaml
spring:
  mail:
    host: smtp-mail.outlook.com
    port: 587
    username: ${MAIL_USERNAME:seu-email@outlook.com}
    password: ${MAIL_PASSWORD:sua-senha}
```

#### **Yahoo:**
```yaml
spring:
  mail:
    host: smtp.mail.yahoo.com
    port: 587
    username: ${MAIL_USERNAME:seu-email@yahoo.com}
    password: ${MAIL_PASSWORD:sua-senha-app}
```

## 📧 Template de Email

### **Características:**
- ✅ **Design responsivo** e moderno
- ✅ **Informações do usuário** (nome, email, tipo)
- ✅ **Data de cadastro** automática
- ✅ **Botão de acesso** à plataforma
- ✅ **Aviso de segurança** para emails não solicitados

### **Localização:**
- Template: `src/main/resources/templates/welcome-email.html`
- Processamento: `EmailService.java`

## 🧪 Como Testar

### **1. Configurar Email:**
```bash
# No terminal, defina as variáveis
export MAIL_USERNAME="seu-email@gmail.com"
export MAIL_PASSWORD="sua-senha-app"

# Ou crie arquivo .env
echo "MAIL_USERNAME=seu-email@gmail.com" > .env
echo "MAIL_PASSWORD=sua-senha-app" >> .env
```

### **2. Registrar Usuário:**
```bash
POST http://localhost:8080/api/auth/register/student
Content-Type: application/json

{
    "name": "João Silva",
    "email": "joao@email.com",
    "password": "123456",
    "documentNumber": "123.456.789-00"
}
```

### **3. Verificar Email:**
- Verifique a caixa de entrada do email cadastrado
- Procure por "Bem-vindo ao Sistema de Cadastro!"
- Verifique se o email chegou na pasta de spam

## 🔧 Troubleshooting

### **Erro: "Authentication failed"**
- ✅ Verifique se a senha de app está correta
- ✅ Confirme se a autenticação de 2 fatores está ativa
- ✅ Teste com outro provedor de email

### **Erro: "Connection refused"**
- ✅ Verifique a configuração de host e porta
- ✅ Confirme se o firewall permite conexões SMTP
- ✅ Teste conectividade: `telnet smtp.gmail.com 587`

### **Email não chega:**
- ✅ Verifique a pasta de spam
- ✅ Confirme se o email está correto
- ✅ Teste com email simples primeiro

### **Template não carrega:**
- ✅ Verifique se o arquivo está em `src/main/resources/templates/`
- ✅ Confirme se a dependência Thymeleaf está no pom.xml
- ✅ Reinicie a aplicação

## 📝 Logs de Debug

Para ver logs detalhados do email, adicione no `application.yml`:

```yaml
logging:
  level:
    org.springframework.mail: DEBUG
    sys.sistema_cadastro_empresa_cliente.service.EmailService: DEBUG
```

## 🚀 Próximos Passos

1. **Configurar email de recuperação de senha**
2. **Implementar confirmação de email**
3. **Adicionar templates para diferentes tipos de usuário**
4. **Configurar fila de emails para alta demanda**
5. **Implementar logs de envio de email**

## ⚠️ Segurança

- **NUNCA** commite credenciais no código
- **SEMPRE** use variáveis de ambiente
- **CONFIGURE** senhas de app específicas
- **MONITORE** logs de envio de email
- **IMPLEMENTE** rate limiting para envio

---

**✅ Sistema de email implementado com sucesso!** 

Agora cada novo registro receberá automaticamente um email de boas-vindas personalizado e profissional.
