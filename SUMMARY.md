# Table of contents

* [Hanami - API de Análise de Vendas](README.md)
  * [Executando com Docker](readme/executando-com-docker.md)
  * ```yaml
    type: builtin:openapi
    props:
      models: true
      downloadLink: true
    dependencies:
      spec:
        ref:
          kind: openapi
          spec: hanami-api
    ```
