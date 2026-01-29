# Hanami - API de Análise de Vendas

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-green?logo=springboot&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-3.9.6-blue?logo=apachemaven&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-85EA2D?logo=swagger&logoColor=black) ![License](https://img.shields.io/badge/License-MIT-green)

### Visão geral

O **Hanami** é uma API REST em Spring Boot. Ela processa CSVs de vendas. Ela calcula métricas e gera relatórios.

O nome "Hanami" (花見) significa "observar as flores". Ele representa extrair insights dos dados.

### Quickstart (Docker)

#### 1) Clonar o repositório

```bash
git clone https://github.com/brenoaug/hanami-backend
cd hanami-backend
```

#### 2) (Opcional) Customizar variáveis do MySQL

Você pode rodar sem `.env`. O `docker-compose.yml` tem valores padrão.

> **💡 Dica:** Crie um `.env` **somente** se quiser mudar usuário, senha, banco ou root password. Não commite esse arquivo.

```env
MYSQL_ROOT_PASSWORD=root_password
MYSQL_DATABASE=hanami_db
MYSQL_USER=hanami_user
MYSQL_PASSWORD=hanami_password
```

#### 3) Subir a stack

```bash
docker compose up -d --build
```

#### 4) Abrir a API e o Swagger

* API: `http://localhost:8080`
* Swagger:
  * `http://localhost:8080/swagger-ui.html`
  * `http://localhost:8080/swagger-ui/index.html`

### Problema que resolve

CSV de vendas costuma virar análise manual. Isso dá retrabalho e dá erro. O Hanami automatiza importação, validação e agregações.

### Funcionalidades

* **Upload de arquivos CSV** com dados de vendas, clientes, produtos e vendedores
* **Validação automática** da estrutura e integridade dos dados do arquivo
* **Persistência de dados** em banco MySQL
* **Cálculo automático** de receita líquida, custo total e lucro bruto
* **Relatórios agregados** de vendas por produto
* **Resumo executivo** das vendas com métricas estratégicas
* **Análise de desempenho** por região geográfica
* **Perfil demográfico** dos clientes
* **Download de relatórios completos** em formato JSON e PDF com gráficos
* **Documentação interativa** via Swagger UI
* **Containerização** com Docker e Docker Compose
* **Logs detalhados** com rotação automática

### Documentação

* [Executando com Docker](readme/executando-com-docker.md) - Guia completo de instalação e execução
* [Upload de Arquivos CSV](readme/upload-csv.md) - Especificação do upload e formato de dados
* [Relatórios e Análises](readme/relatorios-e-analises.md) - Endpoints de geração de relatórios
* [Modelos de Dados](readme/modelos.md) - DTOs e estruturas de resposta da API

### Arquitetura e stack

#### Principais tecnologias

* **Java 21**
* **Spring Boot 3.4.1**
* **Maven 3.9.6**
* **MySQL 8.0**
* **Spring Data JPA / Hibernate**
* **Jackson Dataformat CSV**
* **SpringDoc OpenAPI (Swagger)**
* **SLF4J / Logback**
* **OpenPDF 1.3.30** (PDF)
* **JFreeChart 1.5.3** (gráficos)
* **Docker & Docker Compose**

<details>

<summary>Estrutura de pacotes (visão rápida)</summary>

A aplicação está organizada em pastas que refletem as responsabilidades de cada parte do sistema:

```
com.recode.hanami
├── config/              # Configurações da aplicação (ex: OpenAPI/Swagger)
│   └── OpenApiConfig.java
│
├── controller/          # Camada de apresentação (REST Controllers)
│   ├── docs/                       # Interfaces de documentação (OpenAPI)
│   │   ├── CsvControllerOpenApi.java
│   │   └── ReportsControllerOpenApi.java
│   ├── CsvController.java        # Upload de arquivos CSV
│   └── ReportsController.java    # Geração de relatórios
│
├── dto/                 # Data Transfer Objects (DTOs)
│   ├── DadosArquivoDTO.java
│   ├── ImportacaoResponseDTO.java
│   ├── MetricasFinanceirasDTO.java
│   ├── AnaliseProdutoDTO.java
│   ├── ResumoVendasDTO.java
│   ├── RelatorioCompletoDTO.java
│   ├── MetricasRegiaoDTO.java
│   ├── DistribuicaoClientesDTO.java
│   └── ItemDistribuicaoDTO.java
│
├── entities/            # Entidades JPA (modelo de dados)
│   ├── Venda.java
│   ├── Cliente.java
│   ├── Produto.java
│   └── Vendedor.java
│
├── exception/          # Exceções e tratamento de erros
│   ├── handler/                    # Global Exception Handler
│   │   ├── ErrorType.java
│   │   └── GlobalExceptionHandler.java
│   ├── ArquivoInvalidoException.java
│   └── DadosInvalidosException.java
│
├── repository/         # Camada de acesso a dados (JPA Repositories)
│   ├── VendaRepository.java
│   ├── ClienteRepository.java
│   ├── ProdutoRepository.java
│   └── VendedorRepository.java
│
├── service/             # Camada de lógica de negócio
│   ├── CsvService.java                     # Conversão CSV → JSON
│   ├── ProcessamentoVendasService.java     # Processamento e persistência
│   ├── CalculadoraMetricasService.java     # Cálculos financeiros
│   ├── CalculosDemografiaRegiao.java       # Métricas regionais e demográficas
│   ├── RelatorioService.java               # Geração de relatórios completos
│   └── PdfService.java                     # Geração de PDFs com gráficos
│
├── util/               # Classes utilitárias
│   ├── DownloadArquivoUtil.java
│   └── TratamentoDadosUtil.java
│
└── validation/         # Validadores customizados
    ├── FormatoRelatorioValidator.java
    ├── SortByValidator.java
    └── UploadArquivoValidator.java
```

</details>

### Pré-requisitos

Antes de começar, certifique-se de ter instalado:

#### Docker

* **Docker Desktop** - [Download aqui](https://www.docker.com/products/docker-desktop/)
* **Docker Compose** (já incluído no Docker Desktop)

### Verificar Instalação:

```bash
docker --version
docker compose version
```

> 📘 **Para instruções detalhadas de instalação e execução, consulte:** [Executando com Docker](readme/executando-com-docker.md)

### Swagger (OpenAPI)

A API possui documentação interativa gerada automaticamente pelo **SpringDoc OpenAPI (Swagger)**.

#### Como acessar

1. **Certifique-se de que a aplicação está rodando via Docker**
2.  **Acesse o Swagger UI no navegador:**

    ```
    http://localhost:8080/swagger-ui.html
    ```
3. **Explore os endpoints disponíveis:**
   * Você verá todos os endpoints organizados por controllers
   * Cada endpoint mostra os parâmetros, tipos de dados e exemplos de resposta
   * Documentação completa com descrições, schemas e códigos de status HTTP
4. **Teste diretamente pelo Swagger:**
   * Clique em um endpoint para expandir
   * Clique no botão **"Try it out"**
   * Preencha os parâmetros necessários
   * Clique em **"Execute"**
   * Veja a resposta em tempo real

**Dica:** O Swagger UI substitui completamente a necessidade de ferramentas como Postman ou Insomnia para testes da API!

#### Endpoints

Base URL: `http://localhost:8080`

Prefixo: `/hanami`

**CSV**

* `POST /hanami/upload-file` - Upload e processamento de arquivo CSV

**Reports**

* `GET /hanami/reports/financial-metrics` - Métricas financeiras consolidadas
* `GET /hanami/reports/product-analysis` - Análise agregada por produto
* `GET /hanami/reports/sales-summary` - Resumo executivo de vendas
* `GET /hanami/reports/regional-performance` - Desempenho por região geográfica
* `GET /hanami/reports/customer-profile` - Perfil demográfico dos clientes
* `GET /hanami/reports/download` - Download de relatório completo (JSON/PDF)

> 📘 **Para especificações detalhadas dos endpoints, consulte:**
> - [Upload de Arquivos CSV](readme/upload-csv.md)
> - [Relatórios e Análises](readme/relatorios-e-analises.md)
> - [Modelos de Dados](readme/modelos.md)

### API Reference

#### Padrão de Respostas de Erro

```json
{
  "timestamp": "2026-01-29T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Detalhe do erro",
  "path": "/hanami/upload-file"
}
```

#### Upload de CSV

Para fazer upload de arquivos CSV com dados de vendas:

**Endpoint:** `POST /hanami/upload-file`

**Content-Type:** `multipart/form-data`

**Exemplo (curl):**
```bash
curl -X POST http://localhost:8080/hanami/upload-file \
  -F "file=@vendas.csv"
```

> 📘 **Documentação completa:** [Upload de Arquivos CSV](readme/upload-csv.md)

#### Relatórios

A API oferece diversos endpoints para geração de relatórios e análises:

- **Métricas Financeiras** - Receita, custos e lucro consolidados
- **Análise por Produto** - Vendas agregadas por produto
- **Resumo de Vendas** - Ticket médio, canais e formas de pagamento
- **Desempenho Regional** - Métricas por região geográfica
- **Perfil Demográfico** - Distribuição de clientes
- **Download de Relatórios** - Exportação em JSON ou PDF

> 📘 **Documentação completa:** [Relatórios e Análises](readme/relatorios-e-analises.md)

#### Modelos de Dados

Para entender as estruturas de resposta (DTOs) da API:

> 📘 **Documentação completa:** [Modelos de Dados](readme/modelos.md)

### Formato do CSV

O arquivo CSV deve conter 25 colunas obrigatórias com dados de vendas, clientes, produtos e vendedores.

**Exemplo básico:**

```csv
id_transacao,data_venda,valor_final,subtotal,desconto_percent,canal_venda,forma_pagamento,cliente_id,nome_cliente,idade_cliente,genero_cliente,cidade_cliente,estado_cliente,renda_estimada,produto_id,nome_produto,categoria,marca,preco_unitario,quantidade,margem_lucro,regiao,status_entrega,tempo_entrega_dias,vendedor_id
TRX001,2024-01-15,2500.00,2800.00,10.71,Online,Cartão de Crédito,CLI001,João Silva,35,M,São Paulo,SP,5000.00,PRD001,Notebook Dell,Eletrônicos,Dell,2500.00,1,25.5,Sudeste,Entregue,5,VND001
```

**Regras principais:**

* Datas em `YYYY-MM-DD`
* Decimais com ponto (`2500.00`)
* Estado como sigla (`SP`, `RJ`)
* Região como nome (`Norte`, `Sudeste`, etc.)

> 📘 **Especificação completa do formato:** [Upload de Arquivos CSV](readme/upload-csv.md)

### Acessando o MySQL

#### Usando Docker Compose

Quando estiver rodando via Compose:

```bash
# Acessar o MySQL no serviço do Compose
docker compose exec hanami-db mysql -u hanami_user -p

# Quando solicitado, digite a senha configurada (padrão: hanami_password)
```

**Comandos úteis SQL:**

```sql
USE hanami_db;

SHOW TABLES;

SELECT COUNT(*) FROM vendas;
SELECT COUNT(*) FROM clientes;
SELECT COUNT(*) FROM produtos;
SELECT COUNT(*) FROM vendedores;

-- Ver primeiras 10 vendas
SELECT * FROM vendas LIMIT 10;
```

<details>

<summary>Usando MySQL local (opcional)</summary>

Se você instalou o MySQL localmente, pode usar qualquer cliente MySQL:

* **MySQL Workbench** - Interface gráfica
* **DBeaver** - Cliente universal
*   **Linha de comando:**

    ```bash
    mysql -h localhost -P 3306 -u hanami_user -p hanami_db
    ```

**Informações de Conexão:**

* **Host:** localhost
* **Porta:** 3306
* **Banco de Dados:** hanami\_db
* **Usuário:** hanami\_user
* **Senha:** `hanami_password` (padrão no Compose) ou a do seu `.env`

### Tabelas Disponíveis:

* `vendas` - Dados de transações de vendas
* `clientes` - Informações dos clientes
* `produtos` - Catálogo de produtos
* `vendedores` - Dados dos vendedores

</details>

### Troubleshooting

#### Problemas Comuns

**A aplicação não inicia**

```bash
# Verifique os logs
docker compose logs hanami

# Certifique-se de que o MySQL está saudável
docker compose ps
```

**Porta 8080 já em uso**

```bash
# Windows - Encontre e mate o processo
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Ou altere a porta no docker-compose.yml
ports:
  - "8081:8080"
```

**Rebuild após mudanças no código**

```bash
docker compose down
docker compose up -d --build
```

> 📘 **Para troubleshooting completo, consulte:** [Executando com Docker](readme/executando-com-docker.md)

### Logs

Os logs da aplicação são configurados para facilitar debugging e monitoramento:

#### Localização

* **Console:** Saída padrão durante a execução
* **Arquivo:** `logs/app.log`

#### Características

* **Rotação automática:** Arquivos são rotacionados a cada 100MB
* **Histórico:** Mantém os últimos 7 arquivos de log
* **Níveis configurados:**
  * `INFO` - Informações gerais da aplicação
  * `DEBUG` - Detalhes da camada de negócio (`com.recode.hanami`)
  * `DEBUG` - Queries SQL executadas

#### Formato

```
2026-01-05 14:30:25 [main] INFO  c.r.h.ApiVendasApplication - Starting ApiVendasApplication
2026-01-05 14:30:26 [http-nio-8080-exec-1] DEBUG c.r.h.c.CsvController - Arquivo convertido com sucesso
```

### Contato

Projeto desenvolvido por **Breno Augusto** como parte do portfólio técnico em parceria com a **Recode**.

### Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE).

Este projeto foi desenvolvido para fins educacionais e de demonstração de habilidades técnicas.
