# Hanami - api-vendas

Guia rápido para instalar dependências e rodar o projeto.

## Requisitos
- Java 21
- Maven 3.8+ (ou compatível)
- IntelliJ IDEA (opcional)

## Instalação
1. Verifique a versão do Java:
   `java -version`  
   Certifique\-se de usar Java 21.

2. Baixe as dependências e compile:
   `mvn clean install`

## Rodando a aplicação
- Pelo Maven:
  `mvn spring-boot:run`

- Gerando o jar e executando:
  `mvn -DskipTests package`  
  `java -jar target/hanami-0.0.1-SNAPSHOT.jar`

## Usando a IDE (IntelliJ)
- Configure o Project SDK para Java 21.
- Execute a classe principal do Spring Boot (a classe com o método `main` em `src/main/java`).
- O `spring-boot-devtools` está incluído para reinício automático em desenvolvimento.

## Observações
- Dependências de documentação OpenAPI estão no `pom.xml` (springdoc).
- O plugin `spring-boot-docker-compose` está declarado como opcional/runtime.