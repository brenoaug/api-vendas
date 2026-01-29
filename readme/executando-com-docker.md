
# Executando com Docker

### Quando usar este guia

Use este guia se você quer mais detalhes que o **Quickstart**. Ele assume Docker Compose.

### Pré-requisitos

* Docker Desktop instalado.
* `docker compose` funcionando.

Verifique:

```bash
docker --version
docker compose version
```

### Subindo a aplicação

#### 1) Clonar o repositório

```bash
git clone https://github.com/brenoaug/hanami-backend
cd hanami-backend
```

#### 2) (Opcional) Criar `.env`

Use apenas se quiser sobrescrever os defaults do `docker-compose.yml`.

```env
MYSQL_ROOT_PASSWORD=root_password
MYSQL_DATABASE=hanami_db
MYSQL_USER=hanami_user
MYSQL_PASSWORD=hanami_password
```

> **⚠️ Aviso:** Não commite `.env`.

#### 3) Subir containers

```bash
docker compose up -d --build
```

O que acontece:

* Sobe o MySQL (`hanami-db`).
* Constrói e sobe a API (`hanami`).
* Cria rede e volume (`mysql_data`).

#### 4) Verificar saúde

```bash
docker compose ps
```

Você deve ver:

* `hanami-db` como `healthy`.
* `hanami` como `running`.

#### 5) Ver logs

```bash
# Logs da API
docker compose logs -f hanami

# Logs do MySQL
docker compose logs -f hanami-db
```

### URLs importantes

* API: `http://localhost:8080`
* Swagger:
  * `http://localhost:8080/swagger-ui.html`
  * `http://localhost:8080/swagger-ui/index.html`

### Parar, reiniciar e rebuild

#### Parar sem apagar dados

```bash
docker compose down
```

#### Subir de novo

```bash
docker compose up -d
```

#### Rebuild da API (mudou código)

```bash
docker compose down
docker compose up -d --build
```

#### Reset completo (apaga dados do MySQL)

```bash
docker compose down -v
```

> **⚠️ Perigo:** `down -v` remove o volume `mysql_data`. Você perde o banco.

### Portas

#### API (8080) já está em uso

No `docker-compose.yml`, troque:

```yaml
ports:
  - "8081:8080"
```

Acesse em `http://localhost:8081`.

#### MySQL (3306) já está em uso

Troque:

```yaml
ports:
  - "3307:3306"
```

Conecte via `localhost:3307`.

### Acessar o MySQL

```bash
docker compose exec hanami-db mysql -u hanami_user -p
```

Senha padrão:

* `hanami_password` (se você não criou `.env`).

Comandos úteis:

```sql
USE hanami_db;
SHOW TABLES;
SELECT COUNT(*) FROM vendas;
```

### Usar imagem do Docker Hub (sem build local)

* Imagem: https://hub.docker.com/repository/docker/brenoaug/hanami/general

```bash
docker pull brenoaug/hanami:latest
```

Depois, no `docker-compose.yml`:

* Remova `build:` do serviço `hanami`.
* Use `image: brenoaug/hanami:latest`.

### Troubleshooting rápido

#### A API sobe antes do MySQL

O compose já usa `depends_on` com `condition: service_healthy`. Se mesmo assim falhar, veja:

```bash
docker compose logs hanami-db
docker compose logs hanami
```

#### `.env` não está sendo lido

```bash
ls -la .env  # Linux/Mac
cat .env

docker compose down
docker compose up -d
```
