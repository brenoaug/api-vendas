---
description: Endpoints para geração de relatórios e análises de vendas
---

# Relatórios e Análises

## Visão Geral

O Hanami oferece endpoints dedicados para geração de relatórios completos e análises demográficas dos dados de vendas. Você pode exportar relatórios em diferentes formatos e obter insights sobre o perfil dos clientes.

## Endpoints Disponíveis

### 1. Download de Relatório Completo

Gera e faz o download de um relatório completo de análise de vendas.

**Endpoint:** `GET /hanami/reports/download`

**URL Completa:** `http://localhost:8080/hanami/reports/download`

#### Formatos Disponíveis

| Formato | Descrição |
|---------|-----------|
| `json` | Relatório estruturado em JSON para integração com outras aplicações |
| `pdf` | Relatório visual em PDF com tabelas e gráficos formatados |

#### Query Parameters

| Parâmetro | Tipo | Obrigatório | Descrição | Exemplo |
|-----------|------|-------------|-----------|---------|
| `format` | string | Sim | Formato do relatório: `json` ou `pdf` | `pdf` |

#### Exemplos de Requisição

##### Via cURL (JSON)

```bash
curl -X GET "http://localhost:8080/hanami/reports/download?format=json" \
  -H "Accept: application/json" \
  -o relatorio.json
```

##### Via cURL (PDF)

```bash
curl -X GET "http://localhost:8080/hanami/reports/download?format=pdf" \
  -H "Accept: application/pdf" \
  -o relatorio.pdf
```

##### Via Navegador

```
http://localhost:8080/hanami/reports/download?format=pdf
```

O navegador iniciará automaticamente o download do arquivo.

##### Via JavaScript (Fetch API)

```javascript
// Download do relatório em PDF
fetch('http://localhost:8080/hanami/reports/download?format=pdf')
  .then(response => response.blob())
  .then(blob => {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'relatorio-vendas.pdf';
    document.body.appendChild(a);
    a.click();
    a.remove();
  });

// Download do relatório em JSON
fetch('http://localhost:8080/hanami/reports/download?format=json')
  .then(response => response.json())
  .then(data => {
    console.log('Relatório:', data);
    // Processar dados...
  });
```

#### Respostas

##### 200 - Relatório Gerado com Sucesso

**Content-Type:** 
- `application/json` (formato json)
- `application/pdf` (formato pdf)

**Response Body:** Arquivo binário (PDF) ou JSON estruturado

**Headers:**
```
Content-Disposition: attachment; filename="relatorio-vendas.pdf"
Content-Type: application/pdf
```

##### 400 - Formato Inválido

Retornado quando o parâmetro `format` não é `json` ou `pdf`.

**Response Body:**
```json
{
  "timestamp": "2026-01-29T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Formato inválido. Use 'json' ou 'pdf'",
  "path": "/hanami/reports/download"
}
```

##### 500 - Erro ao Gerar Relatório

Erro interno ao processar ou gerar o relatório.

**Response Body:**
```json
{
  "timestamp": "2026-01-29T10:30:00.000+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Erro ao gerar relatório: [detalhes do erro]",
  "path": "/hanami/reports/download"
}
```

#### Estrutura do Relatório JSON

Exemplo de resposta quando `format=json`:

```json
{
  "dataGeracao": "2026-01-29T10:30:00",
  "periodo": {
    "dataInicio": "2025-01-01",
    "dataFim": "2026-01-29"
  },
  "resumo": {
    "totalVendas": 1500,
    "receitaTotal": 450000.00,
    "ticketMedio": 300.00,
    "produtoMaisVendido": "Notebook Dell"
  },
  "distribuicaoClientes": {
    "porGenero": [
      {
        "genero": "Feminino",
        "quantidade": 750,
        "percentual": 50.0
      },
      {
        "genero": "Masculino",
        "quantidade": 700,
        "percentual": 46.67
      },
      {
        "genero": "Outro",
        "quantidade": 50,
        "percentual": 3.33
      }
    ],
    "porFaixaEtaria": [
      {
        "faixa": "18-25",
        "quantidade": 300,
        "percentual": 20.0
      },
      {
        "faixa": "26-35",
        "quantidade": 600,
        "percentual": 40.0
      },
      {
        "faixa": "36-50",
        "quantidade": 400,
        "percentual": 26.67
      },
      {
        "faixa": "51+",
        "quantidade": 200,
        "percentual": 13.33
      }
    ],
    "porCidade": [
      {
        "cidade": "São Paulo",
        "quantidade": 500,
        "percentual": 33.33
      },
      {
        "cidade": "Rio de Janeiro",
        "quantidade": 300,
        "percentual": 20.0
      }
    ]
  },
  "produtosMaisVendidos": [
    {
      "produto": "Notebook Dell",
      "quantidade": 150,
      "receita": 150000.00
    },
    {
      "produto": "Mouse Logitech",
      "quantidade": 300,
      "receita": 15000.00
    }
  ]
}
```

---

### 2. Perfil Demográfico dos Clientes

Retorna a distribuição dos clientes por gênero, faixa etária e cidade.

**Endpoint:** `GET /hanami/reports/customer-profile`

**URL Completa:** `http://localhost:8080/hanami/reports/customer-profile`

#### Exemplos de Requisição

##### Via cURL

```bash
curl -X GET "http://localhost:8080/hanami/reports/customer-profile" \
  -H "Accept: application/json"
```

##### Via Navegador

```
http://localhost:8080/hanami/reports/customer-profile
```

##### Via JavaScript (Fetch API)

```javascript
fetch('http://localhost:8080/hanami/reports/customer-profile')
  .then(response => response.json())
  .then(data => {
    console.log('Perfil dos Clientes:', data);
    // Renderizar gráficos ou tabelas
  })
  .catch(error => console.error('Erro:', error));
```

##### Via Axios (JavaScript)

```javascript
import axios from 'axios';

axios.get('http://localhost:8080/hanami/reports/customer-profile')
  .then(response => {
    const perfil = response.data;
    // Processar dados demográficos
  })
  .catch(error => {
    console.error('Erro ao buscar perfil:', error);
  });
```

#### Respostas

##### 200 - Perfil Demográfico Calculado com Sucesso

**Content-Type:** `application/json`

**Response Body:**

```json
{
  "totalClientes": 1500,
  "distribuicaoPorGenero": [
    {
      "genero": "Feminino",
      "quantidade": 750,
      "percentual": 50.0
    },
    {
      "genero": "Masculino",
      "quantidade": 700,
      "percentual": 46.67
    },
    {
      "genero": "Outro",
      "quantidade": 50,
      "percentual": 3.33
    }
  ],
  "distribuicaoPorFaixaEtaria": [
    {
      "faixaEtaria": "18-25",
      "quantidade": 300,
      "percentual": 20.0,
      "idadeMinima": 18,
      "idadeMaxima": 25
    },
    {
      "faixaEtaria": "26-35",
      "quantidade": 600,
      "percentual": 40.0,
      "idadeMinima": 26,
      "idadeMaxima": 35
    },
    {
      "faixaEtaria": "36-50",
      "quantidade": 400,
      "percentual": 26.67,
      "idadeMinima": 36,
      "idadeMaxima": 50
    },
    {
      "faixaEtaria": "51+",
      "quantidade": 200,
      "percentual": 13.33,
      "idadeMinima": 51,
      "idadeMaxima": null
    }
  ],
  "distribuicaoPorCidade": [
    {
      "cidade": "São Paulo",
      "estado": "SP",
      "quantidade": 500,
      "percentual": 33.33
    },
    {
      "cidade": "Rio de Janeiro",
      "estado": "RJ",
      "quantidade": 300,
      "percentual": 20.0
    },
    {
      "cidade": "Belo Horizonte",
      "estado": "MG",
      "quantidade": 250,
      "percentual": 16.67
    },
    {
      "cidade": "Brasília",
      "estado": "DF",
      "quantidade": 200,
      "percentual": 13.33
    },
    {
      "cidade": "Outras",
      "estado": null,
      "quantidade": 250,
      "percentual": 16.67
    }
  ],
  "insights": {
    "generoMaisFrequente": "Feminino",
    "faixaEtariaDominante": "26-35",
    "cidadeComMaisClientes": "São Paulo",
    "idadeMedia": 35.5
  }
}
```

##### 500 - Erro ao Calcular Perfil

**Response Body:**
```json
{
  "timestamp": "2026-01-29T10:30:00.000+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Erro ao calcular perfil demográfico",
  "path": "/hanami/reports/customer-profile"
}
```

---

## Casos de Uso

### Análise de Mercado

Use o perfil demográfico para entender seu público-alvo:

```javascript
// Buscar perfil demográfico
const perfil = await fetch('http://localhost:8080/hanami/reports/customer-profile')
  .then(r => r.json());

// Analisar gênero predominante
const generoDominante = perfil.distribuicaoPorGenero[0];
console.log(`Gênero mais frequente: ${generoDominante.genero} (${generoDominante.percentual}%)`);

// Identificar faixa etária principal
const faixaPrincipal = perfil.distribuicaoPorFaixaEtaria
  .sort((a, b) => b.quantidade - a.quantidade)[0];
console.log(`Faixa etária dominante: ${faixaPrincipal.faixaEtaria}`);
```

### Exportação de Relatórios Periódicos

Automatize a geração de relatórios mensais:

```javascript
// Gerar relatório PDF mensal
async function gerarRelatorioMensal() {
  const response = await fetch(
    'http://localhost:8080/hanami/reports/download?format=pdf'
  );
  
  const blob = await response.blob();
  const dataAtual = new Date().toISOString().split('T')[0];
  
  // Salvar arquivo
  const url = window.URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url;
  a.download = `relatorio-vendas-${dataAtual}.pdf`;
  a.click();
  
  console.log('Relatório mensal gerado com sucesso!');
}
```

### Dashboard Executivo

Integre os dados em um dashboard:

```javascript
async function carregarDashboard() {
  // Buscar perfil demográfico
  const perfil = await fetch('http://localhost:8080/hanami/reports/customer-profile')
    .then(r => r.json());
  
  // Renderizar gráfico de pizza (gênero)
  renderizarGraficoPizza('grafico-genero', perfil.distribuicaoPorGenero);
  
  // Renderizar gráfico de barras (faixa etária)
  renderizarGraficoBarras('grafico-idade', perfil.distribuicaoPorFaixaEtaria);
  
  // Renderizar mapa (cidades)
  renderizarMapa('mapa-cidades', perfil.distribuicaoPorCidade);
  
  // Exibir KPIs
  document.getElementById('total-clientes').textContent = perfil.totalClientes;
  document.getElementById('idade-media').textContent = perfil.insights.idadeMedia;
}
```

---

## Boas Práticas

### 1. Cache de Relatórios

> **💡 Dica:** Relatórios podem ser custosos computacionalmente. Considere implementar cache no frontend para evitar requisições desnecessárias.

```javascript
// Exemplo de cache simples
const CACHE_DURACAO = 5 * 60 * 1000; // 5 minutos

let perfilCache = null;
let perfilTimestamp = null;

async function obterPerfilComCache() {
  const agora = Date.now();
  
  if (perfilCache && (agora - perfilTimestamp) < CACHE_DURACAO) {
    return perfilCache;
  }
  
  perfilCache = await fetch('http://localhost:8080/hanami/reports/customer-profile')
    .then(r => r.json());
  perfilTimestamp = agora;
  
  return perfilCache;
}
```

### 2. Tratamento de Erros

Sempre trate possíveis erros nas requisições:

```javascript
async function baixarRelatorioComTratamento(formato) {
  try {
    const response = await fetch(
      `http://localhost:8080/hanami/reports/download?format=${formato}`
    );
    
    if (!response.ok) {
      const erro = await response.json();
      throw new Error(erro.message || 'Erro ao gerar relatório');
    }
    
    const blob = await response.blob();
    // Processar download...
    
  } catch (error) {
    console.error('Erro ao baixar relatório:', error);
    alert(`Não foi possível gerar o relatório: ${error.message}`);
  }
}
```

### 3. Validação de Formato

Valide o formato antes de fazer a requisição:

```javascript
function validarFormato(formato) {
  const formatosValidos = ['json', 'pdf'];
  
  if (!formatosValidos.includes(formato)) {
    throw new Error(`Formato inválido: ${formato}. Use 'json' ou 'pdf'.`);
  }
  
  return formato;
}

// Uso
try {
  const formato = validarFormato(inputUsuario);
  await baixarRelatorio(formato);
} catch (error) {
  console.error(error.message);
}
```

---

## Troubleshooting

### Erro 400 - Formato Inválido

**Problema:** O parâmetro `format` não está correto.

**Solução:**
```bash
# ❌ Errado
curl "http://localhost:8080/hanami/reports/download?format=excel"

# ✅ Correto
curl "http://localhost:8080/hanami/reports/download?format=pdf"
```

### Erro 500 - Erro ao Gerar Relatório

**Possíveis causas:**
1. Banco de dados sem vendas cadastradas
2. Erro na geração do PDF
3. Memória insuficiente para processar grandes volumes

**Soluções:**
1. Verifique se há dados no banco
2. Consulte os logs da aplicação: `docker compose logs -f hanami`
3. Aumente a memória JVM no Dockerfile

### Download não Inicia no Navegador

**Problema:** O navegador não baixa o arquivo automaticamente.

**Solução:** Verifique se o header `Content-Disposition` está presente na resposta:

```javascript
fetch('http://localhost:8080/hanami/reports/download?format=pdf')
  .then(response => {
    console.log('Headers:', response.headers);
    return response.blob();
  })
  .then(blob => {
    // Forçar download manualmente
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'relatorio.pdf';
    document.body.appendChild(a);
    a.click();
    a.remove();
    window.URL.revokeObjectURL(url);
  });
```

---

## Links Relacionados

* [README Principal](../README.md) - Documentação principal do projeto
* [Executando com Docker](executando-com-docker.md) - Guia de instalação via Docker
* [Swagger UI](http://localhost:8080/swagger-ui.html) - Interface interativa da API
* [OpenAPI Spec](http://localhost:8080/v3/api-docs) - Especificação OpenAPI
