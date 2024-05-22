# Projeto TestePicPay

Este projeto é uma aplicação Java Spring Boot que implementa um sistema de transações entre usuários, de acordo com o teste técnico proposto pela PicPay em https://github.com/PicPay/picpay-desafio-backend.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- Docker

## Estrutura do Projeto

O projeto é estruturado em pacotes, com cada pacote contendo classes relacionadas a um aspecto específico da aplicação.

- `com.testepicpay.config`: Contém classes de configuração, incluindo `ControllerExceptionHandler` que lida com exceções lançadas pelos controladores.
- `com.testepicpay.services`: Contém classes de serviço, incluindo `TransactionService` que lida com a criação de transações.
- `com.testepicpay.dtos`: Contém classes DTO (Data Transfer Object) usadas para transferir dados entre processos.
- `com.testepicpay.domain`: Contém classes de domínio que representam entidades no banco de dados.
- `com.testepicpay.repositories`: Contém interfaces de repositório usadas para interagir com o banco de dados.

## Executando o Projeto

O projeto é empacotado como um arquivo JAR e pode ser construído e executado usando Docker. O arquivo `Dockerfile` na raiz do projeto descreve como construir a imagem Docker para a aplicação.

Para construir e executar o projeto usando Docker, execute os seguintes comandos:

```bash
docker build -t testepicpay .
docker run -p 8080:8080 testepicpay
```
Alternativamente, o projeto pode ser executado usando Docker Compose. O arquivo docker-compose.yml na raiz do projeto descreve como construir e executar a aplicação usando Docker Compose.  Para construir e executar o projeto usando Docker Compose, execute o seguinte comando:
```bash
docker compose up
```

##API


A aplicação expõe uma API REST que permite criar transações entre usuários. As transações são validadas antes de serem criadas, e se uma transação não for válida, a aplicação retornará um erro.