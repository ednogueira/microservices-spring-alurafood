# microservices-spring-alurafood
Este é um projeto de exemplo de um sistema de pedidos de comida, implementado com arquitetura de microservices, utilizando Spring Cloud e Docker. O projeto foi desenvolvido como parte do curso [Microservices with Spring Cloud](https://www.alura.com.br/curso-online-microservices-spring-cloud) oferecido pela Alura.

## Tecnologias Utilizadas

O projeto utiliza as seguintes tecnologias:

```markdown
* [![Java Version](https://img.shields.io/badge/Java-17-blue)](https://www.java.com/)
* [![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.0.6-green)](https://spring.io/projects/spring-boot)
* [![Spring Cloud Version](https://img.shields.io/badge/Spring%20Cloud-2020.0.3-green)](https://spring.io/projects/spring-cloud)
* [![Docker Version](https://img.shields.io/badge/Docker-23.0.5-blue)](https://www.docker.com/)
* [![Docker Compose Version](https://img.shields.io/badge/Docker%20Compose-2.0.0-blue)](https://docs.docker.com/compose/)
* [![Flyway Version](https://img.shields.io/badge/Flyway-8.0.7-blue)](https://flywaydb.org/)
* [![ModelMapper Version](https://img.shields.io/badge/ModelMapper-3.1.1-blue)](http://modelmapper.org/)
* [![MySql Version](https://img.shields.io/badge/MySql-8-blue)](https://www.mysql.com/)
* [![Lombok Version](https://img.shields.io/badge/Lombok-1.18.20-green)](https://projectlombok.org/)
* [![Spring Cloud Eureka Server Version](https://img.shields.io/badge/Spring%20Cloud%20Eureka%20Server-2020.0.3-green)](https://spring.io/projects/spring-cloud)
* [![Spring Cloud Eureka Client Version](https://img.shields.io/badge/Spring%20Cloud%20Eureka%20Client-2020.0.3-green)](https://spring.io/projects/spring-cloud)
* [![Spring Cloud Openfeign Version](https://img.shields.io/badge/Spring%20Cloud%20Openfeign-3.0.7-green)](https://spring.io/projects/spring-cloud)
* [![Resilience4j Version](https://img.shields.io/badge/Resilience4j-2.02-green)](https://resilience4j.readme.io/)
```

## Arquitetura de Microservices

O projeto utiliza a arquitetura de microservices, com os seguintes serviços:

* **server**: servidor de descoberta de serviços, para que os serviços possam se comunicar entre si
* **gateway**: serviço responsável por fazer o roteamento das requisições entre os serviços
* **pedidos**: serviço responsável por gerenciar os pedidos
* **pagamentos**: serviço responsável por gerenciar os pagamentos

![Arquitetura de Microservices](/images/arquitetura.png)

## Banco de Dados

O projeto utiliza o MySQL como banco de dados. Cada serviço possui seu próprio banco de dados.

## Configuração Docker e Docker Compose

O projeto utiliza o Docker e o Docker Compose para facilitar o processo de configuração e execução do projeto. Para executar o projeto, é necessário ter o Docker e o Docker Compose instalados em sua máquina.

O arquivo `docker-compose.yml` na raiz do projeto contém a definição dos serviços e suas dependências. O arquivo também configura as variáveis de ambiente necessárias para cada serviço.

## Executando o Projeto com Docker Compose

Para executar o projeto com o Docker Compose, execute os seguintes comandos na raiz do projeto:

    docker-compose build
    docker-compose up

Os comandos acima irão baixar as imagens necessárias, criar os containers e iniciar os serviços. Após a execução dos comandos, os serviços estarão disponíveis nos seguintes endereços:

* **Discovery Server - Eureka**: http://localhost:8761
* **Gateway**: http://localhost:8082
* **Pagamentos***: http://localhost:8082/pagamentos-ms/pagamentos
* **Pedidos**: http://localhost:8082/pedidos-ms/pedidos

## Postman Collection

A coleção alurafood na raíz do projeto contém as seguintes requisições:
* **pagamentos**: lista todos os pagamentos cadastrados.
* **pagamentos-por-id**: retorna um pagamento específico, com base em seu ID.
* **criar-pagamento**: cria um novo pagamento.
* **atualizar-pagamento**: atualiza um pagamento existente.
* **deletar-pagamento**: deleta um pagamento existente.
* **confirmar-pagamento**: confirma um pagamento existente.
* **pedidos**: lista todos os pedidos cadastrados.
* **pedidos-por-id**: retorna um pedido específico, com base em seu ID.
* **criar-pedido**: cria um novo pedido.
* **atualizar-status-pedido**: atualiza o status de um pedido existente.
* **porta-pedidos-ms**: retorna qual a porta de origem do serviço retornado da requisição.
