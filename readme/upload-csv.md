---
description: Endpoints para processamento e importação de dados de vendas via CSV
---

# Upload de Arquivos CSV

## Visão Geral

O Hanami permite importar dados de vendas através do upload de arquivos CSV. O sistema processa, valida e persiste automaticamente os dados no banco de dados, incluindo informações sobre vendas, clientes, produtos e vendedores.

## Endpoint de Upload

### Upload de Arquivo CSV com Dados de Vendas

Processa um arquivo CSV contendo dados completos de vendas.

**Endpoint:** `POST /hanami/upload-file`

**URL Completa:** `http://localhost:8080/hanami/upload-file`

**Content-Type:** `multipart/form-data`

#### Formato do Arquivo CSV

O arquivo deve conter as seguintes colunas obrigatórias (separadas por vírgula):

| Coluna | Descrição | Tipo | Exemplo |
|--------|-----------|------|---------|
| `id_transacao` | Identificador único da transação | String | `TRX001` |
| `data_venda` | Data da venda | Date (YYYY-MM-DD) | `2026-01-15` |
| `valor_final` | Valor total da venda | Decimal | `1500.00` |
| `subtotal` | Subtotal antes dos descontos | Decimal | `1600.00` |
| `desconto_percent` | Percentual de desconto | Decimal | `6.25` |
| `canal_venda` | Canal de venda | String | `Loja Física` |
| `forma_pagamento` | Forma de pagamento | String | `Cartão de Crédito` |
| `cliente_id` | ID do cliente | String | `CLI001` |
| `nome_cliente` | Nome completo do cliente | String | `Maria Silva` |
| `idade_cliente` | Idade do cliente | Integer | `35` |
| `genero_cliente` | Gênero do cliente | String | `Feminino` |
| `cidade_cliente` | Cidade do cliente | String | `São Paulo` |
| `estado_cliente` | Estado do cliente (UF) | String | `SP` |
| `renda_estimada` | Renda estimada do cliente | Decimal | `5000.00` |
| `produto_id` | ID do produto | String | `PROD001` |
| `nome_produto` | Nome do produto | String | `Notebook Dell` |
| `categoria` | Categoria do produto | String | `Informática` |
| `marca` | Marca do produto | String | `Dell` |
| `preco_unitario` | Preço unitário do produto | Decimal | `3000.00` |
| `quantidade` | Quantidade vendida | Integer | `2` |
| `margem_lucro` | Margem de lucro (%) | Decimal | `15.5` |
| `regiao` | Região da venda | String | `Sudeste` |
| `status_entrega` | Status da entrega | String | `Entregue` |
| `tempo_entrega_dias` | Tempo de entrega em dias | Integer | `5` |
| `vendedor_id` | ID do vendedor | String | `VEND001` |

#### Exemplo de Arquivo CSV

```csv
id_transacao,data_venda,valor_final,subtotal,desconto_percent,canal_venda,forma_pagamento,cliente_id,nome_cliente,idade_cliente,genero_cliente,cidade_cliente,estado_cliente,renda_estimada,produto_id,nome_produto,categoria,marca,preco_unitario,quantidade,margem_lucro,regiao,status_entrega,tempo_entrega_dias,vendedor_id
TRX001,2026-01-15,1500.00,1600.00,6.25,Loja Física,Cartão de Crédito,CLI001,Maria Silva,35,Feminino,São Paulo,SP,5000.00,PROD001,Notebook Dell,Informática,Dell,3000.00,2,15.5,Sudeste,Entregue,5,VEND001
TRX002,2026-01-16,250.00,280.00,10.71,E-commerce,PIX,CLI002,João Santos,28,Masculino,Rio de Janeiro,RJ,3500.00,PROD002,Mouse Logitech,Informática,Logitech,50.00,5,20.0,Sudeste,Em Trânsito,3,VEND002
TRX003,2026-01-17,3200.00,3200.00,0.00,App Mobile,Boleto,CLI003,Ana Costa,42,Feminino,Belo Horizonte,MG,8000.00,PROD003,iPhone 15,Celulares,Apple,3200.00,1,25.0,Sudeste,Pendente,0,VEND001
```

#### Body Parameters

| Parâmetro | Tipo | Obrigatório | Descrição |
|-----------|------|-------------|-----------|
| `file` | File (binary) | Sim | Arquivo CSV com dados de vendas |

#### Respostas

##### 200 - Arquivo Processado com Sucesso

**Content-Type:** `application/json`

**Response Body:**

```json
{
  "status": "success",
  "message": "Arquivo processado com sucesso",
  "registrosProcessados": 150,
  "detalhes": {
    "vendasCriadas": 150,
    "clientesNovos": 45,
    "produtosNovos": 12,
    "vendedoresNovos": 5,
    "tempoProcessamento": "2.5s"
  },
  "timestamp": "2026-01-29T10:30:00.000+00:00"
}
```

##### 400 - Nenhum Arquivo Enviado ou Arquivo Vazio

**Content-Type:** `application/json`

**Response Body:**

```json
{
  "timestamp": "2026-01-29T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Nenhum arquivo foi enviado",
  "path": "/hanami/upload-file"
}
```

Ou quando o arquivo está vazio:

```json
{
  "timestamp": "2026-01-29T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "O arquivo enviado está vazio",
  "path": "/hanami/upload-file"
}
```

##### 422 - Erro de Validação dos Dados

**Content-Type:** `application/json`

**Response Body:**

```json
{
  "timestamp": "2026-01-29T10:30:00.000+00:00",
  "status": 422,
  "error": "Unprocessable Entity",
  "message": "Erro de validação nos dados do arquivo",
  "erros": [
    {
      "linha": 5,
      "campo": "valor_final",
      "mensagem": "Valor inválido: deve ser um número decimal positivo"
    },
    {
      "linha": 12,
      "campo": "data_venda",
      "mensagem": "Data inválida: formato esperado YYYY-MM-DD"
    },
    {
      "linha": 18,
      "campo": "idade_cliente",
      "mensagem": "Idade deve ser um número inteiro entre 0 e 150"
    }
  ],
  "path": "/hanami/upload-file"
}
```

##### 500 - Erro Interno do Servidor

**Content-Type:** `application/json`

**Response Body:**

```json
{
  "timestamp": "2026-01-29T10:30:00.000+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Erro ao processar arquivo: Falha na conexão com o banco de dados",
  "path": "/hanami/upload-file"
}
```

---

## Validações Aplicadas

O sistema aplica as seguintes validações nos dados do CSV:

### Campos Obrigatórios

Todos os campos do formato CSV são obrigatórios. Linhas com campos faltantes serão rejeitadas.

### Validações por Campo

| Campo | Validação |
|-------|-----------|
| `id_transacao` | Não vazio, único no arquivo |
| `data_venda` | Formato YYYY-MM-DD, data válida |
| `valor_final` | Número decimal positivo |
| `subtotal` | Número decimal positivo, maior ou igual ao `valor_final` |
| `desconto_percent` | Número decimal entre 0 e 100 |
| `canal_venda` | Não vazio |
| `forma_pagamento` | Não vazio |
| `cliente_id` | Não vazio |
| `nome_cliente` | Não vazio, mínimo 3 caracteres |
| `idade_cliente` | Número inteiro entre 0 e 150 |
| `genero_cliente` | Não vazio |
| `cidade_cliente` | Não vazio |
| `estado_cliente` | 2 caracteres (UF válida) |
| `renda_estimada` | Número decimal positivo ou zero |
| `produto_id` | Não vazio |
| `nome_produto` | Não vazio |
| `categoria` | Não vazio |
| `marca` | Não vazio |
| `preco_unitario` | Número decimal positivo |
| `quantidade` | Número inteiro positivo |
| `margem_lucro` | Número decimal (pode ser negativo) |
| `regiao` | Não vazio |
| `status_entrega` | Valores válidos: Pendente, Em Trânsito, Entregue, Cancelado |
| `tempo_entrega_dias` | Número inteiro não negativo |
| `vendedor_id` | Não vazio |

---

## Troubleshooting

### Erro 400 - Nenhum Arquivo Enviado

**Problema:** O FormData não contém o arquivo ou o nome do campo está incorreto.

**Solução:** Verifique se o campo do FormData se chama `file` (não `csv` ou outro nome).

### Erro 400 - Arquivo Vazio

**Problema:** O arquivo selecionado está vazio ou só contém o header.

**Solução:** Valide se o arquivo contém dados além do cabeçalho antes de enviar.

### Erro 422 - Validação de Dados

**Problema:** Os dados do CSV não estão no formato esperado.

**Causas comuns:**
1. Colunas faltando ou em ordem diferente
2. Formato de data incorreto (esperado: YYYY-MM-DD)
3. Valores numéricos com formatação incorreta
4. Caracteres especiais não tratados

**Solução:** Verifique se o CSV segue exatamente o formato especificado, com todas as 25 colunas na ordem correta.

### Erro 500 - Erro Interno

**Problema:** Erro no servidor durante processamento.

**Possíveis causas:**
1. Banco de dados indisponível
2. Memória insuficiente para processar arquivo grande
3. Erro de encoding do arquivo (deve ser UTF-8)

**Soluções:**
- Verifique os logs do servidor: `docker compose logs -f hanami`
- Reduza o tamanho do arquivo (divida em lotes menores)
- Garanta que o arquivo está em UTF-8

### Upload Muito Lento

**Problema:** Upload demora muito tempo.

**Soluções:**
1. Divida arquivos grandes em lotes menores (máximo 1000 linhas por arquivo)
2. Remova linhas duplicadas ou dados desnecessários
3. Verifique sua conexão de rede

---

## Links Relacionados

* [README Principal](../README.md) - Documentação principal do projeto
* [Executando com Docker](executando-com-docker.md) - Guia de instalação via Docker
* [Relatórios e Análises](relatorios-e-analises.md) - Geração de relatórios
* [Swagger UI](http://localhost:8080/swagger-ui.html) - Interface interativa da API
* [OpenAPI Spec](http://localhost:8080/v3/api-docs) - Especificação OpenAPI
