# Parking Spot Control

Esta é uma API desenvolvida para o gerenciamento de vagas de estacionamento. Com ela, é possível criar, atualizar, deletar e pesquisar vagas individualmente por ID, bem como listar todas as vagas já registradas.

## Tecnologias Utilizadas:

A API foi desenvolvida utilizando as seguintes tecnologias:

- Java
- Spring Boot
- JPA / Hibernate
- Swagger
- Maven
- PostgreSQL


## Explorar a Documentação da API
A API possui documentação gerada automaticamente utilizando o Swagger. Você pode acessar a documentação em http://localhost:8080/swagger-ui.html após a aplicação ser iniciada.

## Testar os Endpoints
Após a aplicação ser iniciada, você pode testar os diferentes endpoints utilizando uma ferramenta como Postman

![image](https://github.com/IgorWolf99/ParkingSpotControl/assets/116234237/48afa678-654a-44e7-8143-913f19bf26af)

## Como Utilizar
Para começar a utilizar a API Parking Spot Control, siga as instruções abaixo:

### Pré-requisitos:

- Certifique-se de ter o Java JDK instalado em sua máquina.
- Certifique-se de ter o Apache Maven instalado em sua máquina.
- Você precisa ter um banco de dados PostgreSQL configurado e acessível.

### Clonar o Repositório:
- git clone https://github.com/seu-usuario/parking-spot-control.git
cd parking-spot-control

### Configuração do Banco de Dados:
- Edite o arquivo application.properties localizado em src/main/resources e ajuste as configurações do banco de dados conforme necessário.

### Executar a Aplicação:
Execute o seguinte comando para compilar e iniciar a aplicação:
- mvn spring-boot:run
