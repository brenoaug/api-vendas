---
description: Modelos de dados (DTOs) utilizados pela API
---

# Modelos de Dados

## Visão Geral

Esta página documenta os principais modelos de dados (DTOs - Data Transfer Objects) utilizados pela API do Hanami. Estes objetos são retornados como resposta pelos endpoints da API.

---

## ImportacaoResponseDTO

Modelo de resposta para operações de importação de arquivos CSV.

### Propriedades

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `status` | string | Status da importação (ex: "success", "error") |
| `linhas_processadas` | integer (int32) | Número de linhas processadas do arquivo CSV |

### Exemplo

```json
{
  "status": "success",
  "linhas_processadas": 150
}
```

### Valores Possíveis para `status`

- `success` - Importação realizada com sucesso
- `error` - Erro durante a importação
- `partial` - Importação parcial (algumas linhas falharam)

---

## MetricasRegiaoDTO

Modelo contendo métricas agregadas por região.

### Propriedades

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `totalTransacoes` | integer (int64) | Total de transações realizadas na região |
| `receitaTotal` | number (double) | Receita total gerada na região |
| `quantidadeVendida` | integer (int32) | Quantidade total de produtos vendidos |
| `mediaValorTransacao` | number (double) | Valor médio por transação |

### Exemplo

```json
{
  "totalTransacoes": 1500,
  "receitaTotal": 450000.50,
  "quantidadeVendida": 3200,
  "mediaValorTransacao": 300.00
}
```

### Uso

Este modelo é tipicamente retornado em endpoints de análise regional, como:
- `/hanami/reports/metricas-por-regiao`
- Relatórios consolidados por região

---

## DistribuicaoClientesDTO

Modelo contendo distribuição demográfica dos clientes.

### Propriedades

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `por_genero` | object | Distribuição de clientes por gênero |
| `por_faixa_etaria` | object | Distribuição de clientes por faixa etária |
| `por_cidade` | object | Distribuição de clientes por cidade |

### Estrutura Detalhada

#### `por_genero`

Objeto mapeando gêneros para seus respectivos dados de distribuição.

```json
{
  "Feminino": {
    "contagem": 750,
    "percentual": 50.0
  },
  "Masculino": {
    "contagem": 700,
    "percentual": 46.67
  },
  "Outro": {
    "contagem": 50,
    "percentual": 3.33
  }
}
```

#### `por_faixa_etaria`

Objeto mapeando faixas etárias para seus respectivos dados de distribuição.

```json
{
  "18-25": {
    "contagem": 300,
    "percentual": 20.0
  },
  "26-35": {
    "contagem": 600,
    "percentual": 40.0
  },
  "36-50": {
    "contagem": 400,
    "percentual": 26.67
  },
  "51+": {
    "contagem": 200,
    "percentual": 13.33
  }
}
```

#### `por_cidade`

Objeto mapeando cidades para seus respectivos dados de distribuição.

```json
{
  "São Paulo": {
    "contagem": 500,
    "percentual": 33.33
  },
  "Rio de Janeiro": {
    "contagem": 300,
    "percentual": 20.0
  },
  "Belo Horizonte": {
    "contagem": 250,
    "percentual": 16.67
  },
  "Outras": {
    "contagem": 450,
    "percentual": 30.0
  }
}
```

### Exemplo Completo

```json
{
  "por_genero": {
    "Feminino": {
      "contagem": 750,
      "percentual": 50.0
    },
    "Masculino": {
      "contagem": 700,
      "percentual": 46.67
    },
    "Outro": {
      "contagem": 50,
      "percentual": 3.33
    }
  },
  "por_faixa_etaria": {
    "18-25": {
      "contagem": 300,
      "percentual": 20.0
    },
    "26-35": {
      "contagem": 600,
      "percentual": 40.0
    },
    "36-50": {
      "contagem": 400,
      "percentual": 26.67
    },
    "51+": {
      "contagem": 200,
      "percentual": 13.33
    }
  },
  "por_cidade": {
    "São Paulo": {
      "contagem": 500,
      "percentual": 33.33
    },
    "Rio de Janeiro": {
      "contagem": 300,
      "percentual": 20.0
    },
    "Belo Horizonte": {
      "contagem": 250,
      "percentual": 16.67
    },
    "Outras": {
      "contagem": 450,
      "percentual": 30.0
    }
  }
}
```

### Uso

Este modelo é retornado pelo endpoint:
- `GET /hanami/reports/customer-profile`

---

## ItemDistribuicaoDTO

Modelo representando um item individual em uma distribuição.

### Propriedades

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `contagem` | integer (int64) | Quantidade de ocorrências do item |
| `percentual` | number (double) | Percentual que o item representa do total |

### Exemplo

```json
{
  "contagem": 750,
  "percentual": 50.0
}
```

### Cálculo do Percentual

O percentual é calculado como:

```
percentual = (contagem / total) * 100
```

Onde `total` é a soma de todas as contagens na distribuição.

### Uso

Este modelo é utilizado como parte de outros modelos de distribuição, como:
- Valores dentro de `por_genero` em `DistribuicaoClientesDTO`
- Valores dentro de `por_faixa_etaria` em `DistribuicaoClientesDTO`
- Valores dentro de `por_cidade` em `DistribuicaoClientesDTO`

---

## Notas sobre Tipos de Dados

### Tipos Numéricos

| Tipo | Descrição | Exemplo |
|------|-----------|---------|
| `integer (int32)` | Número inteiro de 32 bits | `-2147483648` a `2147483647` |
| `integer (int64)` | Número inteiro de 64 bits | `-9223372036854775808` a `9223372036854775807` |
| `number (double)` | Número decimal de precisão dupla | `3.14159`, `1000.50` |

### Formato de Percentuais

Todos os percentuais são retornados como números decimais de 0 a 100 (não de 0 a 1).

**Exemplo:**
- 50% é representado como `50.0` (não `0.5`)
- 33.33% é representado como `33.33` (não `0.3333`)

---

## Relacionamento entre Modelos

```
DistribuicaoClientesDTO
├── por_genero: Map<String, ItemDistribuicaoDTO>
├── por_faixa_etaria: Map<String, ItemDistribuicaoDTO>
└── por_cidade: Map<String, ItemDistribuicaoDTO>
```

Cada propriedade de `DistribuicaoClientesDTO` é um mapa (objeto) onde:
- **Chave:** Nome da categoria (ex: "Feminino", "18-25", "São Paulo")
- **Valor:** Objeto do tipo `ItemDistribuicaoDTO`

---

## Links Relacionados

* [README Principal](../README.md) - Documentação principal do projeto
* [Upload de Arquivos CSV](upload-csv.md) - Importação de dados
* [Relatórios e Análises](relatorios-e-analises.md) - Endpoints de relatórios
* [Executando com Docker](executando-com-docker.md) - Guia de instalação
* [Swagger UI](http://localhost:8080/swagger-ui.html) - Interface interativa da API
* [OpenAPI Spec](http://localhost:8080/v3/api-docs) - Especificação OpenAPI completa
