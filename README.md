# 🚀 App Finanças - Backend (Spring Boot + Docker)

Backend REST para o App Finanças (Android). Permite sincronização na nuvem para que o
mesmo usuário acesse a conta de diferentes dispositivos.

---

## 📁 Estrutura

```
backend-appfinances/
├── src/main/java/com/appfinances/backend/
│   ├── controller/      # REST Controllers (Auth, Lancamento, Fatura, Conta)
│   ├── model/           # Entidades JPA
│   ├── repository/      # Spring Data JPA Repositories
│   └── dto/             # Request/Response DTOs
├── src/main/resources/
│   └── application.properties
├── pom.xml              # Maven
├── Dockerfile
├── docker-compose.yml
├── .env.example
└── README.md
```

---

## 🐳 Rodando com Docker (recomendado)

### 1. Clone/acesse a pasta
```bash
cd C:\Users\mayro\Documents\backend-appfinances
```

### 2. Configure as variáveis de ambiente
Copie o exemplo:
```powershell
copy .env.example .env
```

Edite o `.env` com os dados do **Neon** (ou banco local):

```env
# Exemplo Neon:
DB_URL=jdbc:postgresql://ep-solitary-pond-b4wz8490-pooler.c-6.us-east-2.aws.neon.tech:5432/neondb?sslmode=require&channel_binding=require
DB_USERNAME=neondb_owner
DB_PASSWORD=npg_KSMlq8QfRXe7
```

### 3. Suba os containers

**Apenas o backend** (se usar Neon):
```powershell
docker-compose up --build -d
```

**Com PostgreSQL local** (descomente o serviço `db` no `docker-compose.yml`):
```powershell
docker-compose up --build -d
```

O backend ficará disponível em: `http://localhost:8080`

---

## 📱 Conectando ao App Android

No projeto Android (`AppFinances`), edite:

`app/src/main/res/values/strings.xml`

```xml
<!-- URL do seu backend. Use o IP da máquina ou domínio onde o Docker está rodando. -->
<string name="api_base_url">http://SEU_IP_OU_DOMINIO:8080/api/</string>
```

**Exemplos:**
- Emulador Android no mesmo PC: `http://10.0.2.2:8080/api/`
- Dispositivo físico na mesma rede: `http://192.168.x.x:8080/api/`
- Produção: `https://api.seudominio.com/api/`

> ⚠️ Se usar HTTP em produção, configure `network_security_config.xml` no Android ou use HTTPS.

---

## 📋 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/auth/login` | Login (email, senha) |
| `POST` | `/api/auth/cadastro` | Cadastro (nome, email, senha) |
| `GET` | `/api/lancamentos?usuarioId=&mesAno=&busca=` | Listar lançamentos |
| `POST` | `/api/lancamentos` | Inserir lançamento |
| `DELETE` | `/api/lancamentos/{id}` | Excluir lançamento |
| `GET` | `/api/faturas?usuarioId=` | Listar faturas |
| `POST` | `/api/faturas` | Inserir fatura |
| `POST` | `/api/faturas/{id}/pagar?valorPago=` | Pagar fatura |
| `PUT` | `/api/faturas/{id}/valor-total?valorTotal=` | Editar valor total da fatura |
| `GET` | `/api/contas?usuarioId=` | Listar contas bancárias |
| `POST` | `/api/contas` | Inserir conta bancária |

---

## 🔐 Banco de dados

O backend cria as tabelas automaticamente via Hibernate (`ddl-auto=update`).

Tabelas criadas:
- `usuarios`
- `lancamentos`
- `faturas`
- `contas_bancarias`

---

## 🛠 Comandos úteis

```powershell
# Ver logs do backend
docker-compose logs -f backend

# Parar containers
docker-compose down

# Rebuild após alterar código
docker-compose up --build -d

# Acessar terminal dentro do container
docker exec -it appfinances-backend sh
```

---

## ⚠️ Segurança em produção

- Altere `spring.jpa.show-sql=false` no `application.properties`.
- Use HTTPS (certificado SSL) no domínio da API.
- Não comite o `.env` com senhas reais.
- Considere adicionar Spring Security + JWT para autenticação por token (atualmente usa login por email/senha simples para facilitar o início).
