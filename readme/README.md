# Hanami - API de Análise de Vendas

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk\&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-green?logo=springboot\&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-3.9.6-blue?logo=apachemaven\&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql\&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker\&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-85EA2D?logo=swagger\&logoColor=black) ![License](https://img.shields.io/badge/License-MIT-green)

### Visão geral

O **Hanami** é uma API REST em Spring Boot. Ela processa CSVs de vendas. Ela calcula métricas e gera relatórios.

O nome "Hanami" (花見) significa "observar as flores". Ele representa extrair insights dos dados.

### Quickstart (Docker)

{% stepper %}
{% step %}
### 1) Clonar o repositório

```bash
git clone https://github.com/brenoaug/hanami-backend
cd hanami-backend
```
{% endstep %}

{% step %}
### 2) (Opcional) Customizar variáveis do MySQL

Você pode rodar sem `.env`. O `docker-compose.yml` tem valores padrão.

{% hint style="info" %}
Crie um `.env` **somente** se quiser mudar usuário, senha, banco ou root password. Não commite esse arquivo.
{% endhint %}

```env
MYSQL_ROOT_PASSWORD=root_password
MYSQL_DATABASE=hanami_db
MYSQL_USER=hanami_user
MYSQL_PASSWORD=hanami_password
```
{% endstep %}

{% step %}
### 3) Subir a stack

```bash
docker compose up -d --build
```
{% endstep %}

{% step %}
### 4) Abrir a API e o Swagger

* API: `http://localhost:8080`
* Swagger:
  * `http://localhost:8080/swagger-ui.html`
  * `http://localhost:8080/swagger-ui/index.html`
{% endstep %}
{% endstepper %}

### Problema que resolve

CSV de vendas costuma virar análise manual. Isso dá retrabalho e dá erro. O Hanami automatiza importação, validação e agregações.

### Funcionalidades

* **Upload de arquivos CSV** com dados de vendas, clientes, produtos e vendedores
* **Validação automática** da estrutura e integridade dos dados do arquivo
* **Persistência de dados** em banco MySQL
* **Cálculo automático** de receita líquida, custo total e lucro bruto
* **Relatórios agregados** de vendas por produto (quantidade e total arrecadado)
* **Resumo executivo** das vendas (número total, ticket médio, canais e formas de pagamento)
* **Análise de desempenho** por região geográfica (estados e regiões brasileiras)
* **Perfil demográfico** dos clientes (gênero, faixa etária, cidade)
* **Download de relatórios completos** em formato JSON e PDF com gráficos
* **Documentação interativa** dos endpoints via Swagger UI
* **Containerização** com Docker e Docker Compose
* **Logs detalhados** de operações e erros com rotação automática

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
# Docker
docker --version
docker compose version
```

### Como rodar (detalhado)

Veja o guia dedicado de [execução com Docker](executando-com-docker.md).

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

### Padrão de erros

As respostas de erro variam por validação. Use isso como contrato base.

```json
{
  "timestamp": "2026-01-29T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Detalhe do erro",
  "path": "/hanami/upload-file"
}
```

{% hint style="info" %}
Se você já tem um payload oficial no `GlobalExceptionHandler`, vale alinhar o exemplo com ele.
{% endhint %}

### Endpoints (detalhado)

#### 1) Upload de CSV

**Endpoint:** `POST /hanami/upload-file`

**Descrição:** Recebe um arquivo CSV, valida sua estrutura e persiste os dados no banco MySQL.

**Content-Type:** `multipart/form-data`

**Parâmetros:**

* `file` (form-data) - Arquivo CSV contendo dados de vendas

**Exemplo de Requisição (curl):**

```bash
curl -X POST http://localhost:8080/hanami/upload-file \
  -F "file=@/caminho/para/arquivo.csv"
```

**Exemplo de Requisição (Swagger UI):**

1. Acesse http://localhost:8080/swagger-ui.html
2. Expanda `POST /hanami/upload-file`
3. Clique em "Try it out"
4. Clique em "Choose File" e selecione seu CSV
5. Clique em "Execute"

**Resposta de Sucesso (200):**

```json
{
  "status": "sucesso",
  "registrosProcessados": 10000
}
```

**Validações:**

* Verifica se o arquivo tem extensão `.csv`
* Valida se todas as colunas obrigatórias estão presentes
* Verifica integridade dos dados (tipos, formatos, valores nulos)

**Possíveis Erros:**

* **400 Bad Request** - Arquivo inválido ou dados inconsistentes
* **500 Internal Server Error** - Erro no processamento

#### 2) Métricas Financeiras

**Endpoint:** `GET /hanami/reports/financial-metrics`

**Descrição:** Retorna um resumo consolidado das principais métricas financeiras: receita líquida total, custo total operacional e lucro bruto.

**Exemplo de Requisição (curl):**

```bash
curl -X GET http://localhost:8080/hanami/reports/financial-metrics
```

**Resposta de Sucesso (200):**

```json
{
  "receita_liquida": 102614924.62,
  "custo_total": 86384699.09,
  "lucro_bruto": 16230225.53
}
```

**Cálculos:**

* `receita_liquida` = Soma de todos os `valor_final` das vendas
* `custo_total` = Soma dos custos estimados (baseado em `precoUnitario / (1 + margemLucro)`)
* `lucro_bruto` = `receita_liquida - custo_total` (calculado sem arredondamento intermediário)

**Nota Técnica:** O lucro bruto é calculado diretamente da diferença entre receita e custo total, evitando acúmulo de erros de arredondamento.

#### 3) Análise por Produto (agregada)

**Endpoint:** `GET /hanami/reports/product-analysis`

**Descrição:** Retorna uma análise agregada das vendas agrupadas por produto, somando a quantidade vendida e o total arrecadado de cada produto.

**Parâmetros de Query (opcionais):**

* `sort_by` (string): Critério de ordenação
  * `nome` (padrão) - Ordena alfabeticamente por nome do produto
  * `quantidade` - Ordena por quantidade total vendida (decrescente)
  * `total` - Ordena por receita total arrecadada (decrescente)

**Exemplo de Requisição (curl):**

```bash
# Ordenar por total arrecadado
curl -X GET "http://localhost:8080/hanami/reports/product-analysis?sort_by=total"

# Ordenar por quantidade vendida
curl -X GET "http://localhost:8080/hanami/reports/product-analysis?sort_by=quantidade"

# Ordenar por nome (padrão)
curl -X GET "http://localhost:8080/hanami/reports/product-analysis"
```

**Resposta de Sucesso (200):**

```json
[
  {
    "nome_produto": "webcam hd",
    "quantidade_vendida": 450,
    "total_arrecadado": 125450.75
  },
  {
    "nome_produto": "mouse logitech",
    "quantidade_vendida": 1200,
    "total_arrecadado": 84000.00
  }
]
```

**Funcionalidade:**

* Agrupa todas as vendas pelo nome do produto
* Soma as quantidades vendidas de cada produto
* Soma o valor total arrecadado por produto
* Retorna lista ordenada conforme parâmetro `sort_by`

#### 4) Resumo de Vendas

**Endpoint:** `GET /hanami/reports/sales-summary`

**Descrição:** Retorna um resumo executivo das vendas com métricas estratégicas: número total de vendas, valor médio por transação e análise dos canais de venda e formas de pagamento mais e menos utilizados.

**Parâmetros de Query (opcionais):**

* `start_date` (LocalDate) - Data inicial do período (formato: YYYY-MM-DD)
* `end_date` (LocalDate) - Data final do período (formato: YYYY-MM-DD)

**Exemplo de Requisição (curl):**

```bash
# Resumo completo (todas as vendas)
curl -X GET http://localhost:8080/hanami/reports/sales-summary

# Resumo com filtro de período
curl -X GET "http://localhost:8080/hanami/reports/sales-summary?start_date=2024-01-01&end_date=2024-12-31"
```

**Resposta de Sucesso (200):**

```json
{
  "numero_total_vendas": 10000,
  "valor_medio_por_transacao": 10261.49,
  "forma_pagamento_mais_utilizada": "Cartão de Crédito",
  "forma_pagamento_menos_utilizada": "Boleto",
  "canal_vendas_mais_utilizado": "E-commerce",
  "canal_vendas_menos_utilizado": "Telefone"
}
```

**Métricas Retornadas:**

* `numero_total_vendas` = Quantidade total de transações realizadas
* `valor_medio_por_transacao` = Ticket médio de venda (receita total / número de transações)
* `forma_pagamento_mais_utilizada` = Método de pagamento com maior número de transações
* `forma_pagamento_menos_utilizada` = Método de pagamento com menor número de transações
* `canal_vendas_mais_utilizado` = Canal que gerou mais vendas
* `canal_vendas_menos_utilizado` = Canal que gerou menos vendas

**Insights de Negócio:** Este endpoint fornece informações estratégicas para:

* Entender o ticket médio das vendas
* Identificar preferências de pagamento dos clientes
* Descobrir quais canais de venda são mais eficientes
* Tomar decisões sobre investimento em canais menos utilizados

#### 5) Desempenho por Região

**Endpoint:** `GET /hanami/reports/regional-performance`

**Descrição:** Retorna métricas de vendas agrupadas por região geográfica (regiões brasileiras) ou filtradas por estado específico. Para cada região/estado, são calculados: total de transações, receita total, quantidade de produtos vendidos e valor médio por transação.

**Parâmetros de Query (opcionais):**

* `estado` (string) - Sigla do estado para filtrar (ex: SP, RJ, MG)

**Exemplo de Requisição (curl):**

```bash
# Desempenho por todas as regiões
curl -X GET http://localhost:8080/hanami/reports/regional-performance

# Desempenho apenas de São Paulo
curl -X GET "http://localhost:8080/hanami/reports/regional-performance?estado=SP"
```

**Resposta de Sucesso (200):**

```json
{
  "Sudeste": {
    "totalTransacoes": 4523,
    "receitaTotal": 1250300.50,
    "quantidadeVendida": 8900,
    "mediaValorTransacao": 276.42
  },
  "Sul": {
    "totalTransacoes": 2156,
    "receitaTotal": 680200.00,
    "quantidadeVendida": 4350,
    "mediaValorTransacao": 315.52
  }
}
```

**Regiões Brasileiras Suportadas:**

* Norte
* Nordeste
* Centro-Oeste
* Sudeste
* Sul

#### 6) Perfil Demográfico dos Clientes

**Endpoint:** `GET /hanami/reports/customer-profile`

**Descrição:** Retorna a distribuição dos clientes por gênero, faixa etária e cidade. Para cada categoria, são apresentadas a contagem e o percentual do total.

**Exemplo de Requisição (curl):**

```bash
curl -X GET http://localhost:8080/hanami/reports/customer-profile
```

**Resposta de Sucesso (200):**

```json
{
  "por_genero": {
    "M": {
      "contagem": 5230,
      "percentual": 52.30
    },
    "F": {
      "contagem": 4770,
      "percentual": 47.70
    }
  },
  "por_faixa_etaria": {
    "26-35": {
      "contagem": 3200,
      "percentual": 32.00
    },
    "36-45": {
      "contagem": 2800,
      "percentual": 28.00
    }
  },
  "por_cidade": {
    "São Paulo": {
      "contagem": 2500,
      "percentual": 25.00
    }
  }
}
```

**Faixas Etárias:**

* 18-25, 26-35, 36-45, 46-55, 56-65, 66+

#### 7) Download de Relatório Completo

**Endpoint:** `GET /hanami/reports/download?format={json|pdf}`

**Descrição:** Faz o download de um relatório completo de análise de vendas nos formatos JSON ou PDF. O relatório inclui todas as métricas financeiras, análise de produtos, resumo de vendas e desempenho regional consolidados em um único arquivo.

**Parâmetros de Query (obrigatório):**

* `format` (string): Formato do relatório
  * `json` - Retorna arquivo `report.json` para download
  * `pdf` - Retorna arquivo `report.pdf` com tabelas e gráficos

**Exemplos de Requisição (curl):**

```bash
# Download em JSON
curl -X GET "http://localhost:8080/hanami/reports/download?format=json" \
  -o report.json

# Download em PDF
curl -X GET "http://localhost:8080/hanami/reports/download?format=pdf" \
  -o report.pdf
```

{% hint style="info" %}
Se quiser preservar o nome do arquivo do servidor, use `curl -OJ ...`.
{% endhint %}

**Exemplo de Requisição (Navegador):**

```
http://localhost:8080/hanami/reports/download?format=json
http://localhost:8080/hanami/reports/download?format=pdf
```

**Resposta de Sucesso (200) - JSON:**

```
Content-Type: application/json
Content-Disposition: attachment; filename="report.json"
```

```json
{
  "data_geracao": "2026-01-26T10:30:00",
  "metricas_financeiras": {
    "receita_liquida": 458900.75,
    "custo_total": 321230.50,
    "lucro_bruto": 137670.25
  },
  "analise_produtos": [
    {
      "nome_produto": "Notebook Dell",
      "quantidade_vendida": 125,
      "total_arrecadado": 400000.00
    }
  ],
  "resumo_vendas": {
    "numero_total_vendas": 356,
    "valor_medio_por_transacao": 690.45,
    "forma_pagamento_mais_utilizada": "Cartão de Crédito",
    "canal_vendas_mais_utilizado": "E-commerce"
  },
  "desempenho_regional": {
    "Sudeste": {
      "totalTransacoes": 4523,
      "receitaTotal": 1250300.50,
      "quantidadeVendida": 8900,
      "mediaValorTransacao": 276.42
    }
  }
}
```

**Resposta de Sucesso (200) - PDF:**

```
Content-Type: application/pdf
Content-Disposition: attachment; filename="report.pdf"
```

O PDF contém:

* **Título** com data e hora de geração
* **Tabela de Métricas Financeiras** (receita, custos, lucro)
* **Gráfico de Barras** com receita total por região (gerado com JFreeChart)
* **Tabela de Análise de Produtos** (top 10 produtos)
* **Tabela de Resumo de Vendas**
* **Tabela de Desempenho Regional Detalhado**

**Características do PDF:**

* Design profissional com cabeçalhos coloridos
* Formatação brasileira (R$ e dd/MM/yyyy HH:mm:ss)
* Gráfico visual de barras mostrando receita por região
* Tabelas bem formatadas e organizadas
* Gerado usando OpenPDF e JFreeChart

**Casos de Uso:**

* Exportar dados para análise offline
* Compartilhar relatórios com stakeholders
* Manter histórico de análises
* Apresentações executivas (formato PDF)
* Integração com outros sistemas (formato JSON)

### Formato do CSV

Seu arquivo CSV deve ter a seguinte estrutura com as colunas obrigatórias:

```csv
id_transacao,data_venda,valor_final,subtotal,desconto_percent,canal_venda,forma_pagamento,cliente_id,nome_cliente,idade_cliente,genero_cliente,cidade_cliente,estado_cliente,renda_estimada,produto_id,nome_produto,categoria,marca,preco_unitario,quantidade,margem_lucro,regiao,status_entrega,tempo_entrega_dias,vendedor_id
TRX001,2024-01-15,2500.00,2800.00,10.71,Online,Cartão de Crédito,CLI001,João Silva,35,M,São Paulo,SP,5000.00,PRD001,Notebook Dell,Eletrônicos,Dell,2500.00,1,25.5,Sudeste,Entregue,5,VND001
TRX002,2024-01-16,1200.00,1200.00,0.00,Loja Física,Dinheiro,CLI002,Maria Santos,28,F,Rio de Janeiro,RJ,4500.00,PRD002,Mouse Gamer,Periféricos,Logitech,1200.00,1,30.0,Sudeste,Entregue,3,VND002
```

#### Regras recomendadas (para evitar erro de parsing)

* Datas em `YYYY-MM-DD`.
* Decimais com ponto (`2500.00`).
* `estado_cliente` como sigla (`SP`, `RJ`, ...).
* `regiao` como nome (`Norte`, `Nordeste`, `Centro-Oeste`, `Sudeste`, `Sul`).
* Evite colunas extras.
* Evite colunas faltando.

**Colunas Obrigatórias:**

* `id_transacao` - Identificador único da transação
* `data_venda` - Data da venda (formato: YYYY-MM-DD)
* `valor_final` - Valor final da venda
* `subtotal` - Subtotal antes de descontos
* `desconto_percent` - Percentual de desconto aplicado
* `canal_venda` - Canal de vendas (Online, Loja Física, Telefone, etc.)
* `forma_pagamento` - Forma de pagamento (Cartão de Crédito, Boleto, PIX, etc.)
* `cliente_id` - ID do cliente
* `nome_cliente` - Nome completo do cliente
* `idade_cliente` - Idade do cliente
* `genero_cliente` - Gênero (M/F)
* `cidade_cliente` - Cidade do cliente
* `estado_cliente` - Estado (sigla: SP, RJ, MG, etc.)
* `renda_estimada` - Renda estimada do cliente
* `produto_id` - ID do produto
* `nome_produto` - Nome do produto
* `categoria` - Categoria do produto
* `marca` - Marca do produto
* `preco_unitario` - Preço unitário do produto
* `quantidade` - Quantidade vendida
* `margem_lucro` - Margem de lucro em percentual
* `regiao` - Região geográfica
* `status_entrega` - Status da entrega
* `tempo_entrega_dias` - Tempo de entrega em dias
* `vendedor_id` - ID do vendedor

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

#### Problemas com Docker

**`.env` não está sendo lido**

```bash
# Certifique-se de que o arquivo .env está na raiz do projeto
ls -la .env  # Linux/Mac
dir .env     # Windows

# Verifique se as variáveis estão corretas
cat .env     # Linux/Mac
type .env    # Windows

# Recrie os containers para aplicar as mudanças
docker compose down
docker compose up -d
```

**A aplicação não inicia**

```bash
# Verifique os logs
docker compose logs hanami

# Certifique-se de que o MySQL está saudável
docker compose ps
```

**Porta 8080 já em uso**

```bash
# Encontre o processo usando a porta (Windows)
netstat -ano | findstr :8080

# Mate o processo (substitua PID pelo número encontrado)
taskkill /PID <PID> /F

# Ou altere a porta no docker-compose.yml
ports:
  - "8081:8080"  # Acesse via localhost:8081
```

**Porta 3306 (MySQL) já em uso**

Se você já tem MySQL instalado localmente:

```yaml
# Altere no docker-compose.yml
ports:
  - "3307:3306"  # Use porta 3307 externamente
```

**Rebuild após mudanças no código**

```bash
docker compose down
docker compose up -d --build
```

**Reset completo**

```bash
# Remove containers, volumes e imagens
docker compose down -v
docker rmi hanami
docker compose up -d --build
```

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

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](../LICENSE).

Este projeto foi desenvolvido para fins educacionais e de demonstração de habilidades técnicas.
