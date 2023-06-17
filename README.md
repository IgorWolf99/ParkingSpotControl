# Parking Spot Control

Esta é uma API desenvolvida para o gerenciamento de vagas de estacionamento. Com ela, é possível criar, atualizar, deletar e pesquisar vagas individualmente por ID, bem como listar todas as vagas já registradas. Além disso, a API implementa paginação para facilitar a visualização dos registros.

## Tecnologias Utilizadas

A API foi desenvolvida utilizando as seguintes tecnologias:

- Java
- Spring Boot
- JPA / Hibernate
- Maven
- PostgreSQL

## Endpoints

A API oferece os seguintes endpoints:

- `POST /api/parking-spot`: Cria uma nova vaga de estacionamento.
- `PUT /api/parking-spot/{id}`: Atualiza os dados de uma vaga existente.
- `DELETE /api/parking-spot/{id}`: Deleta uma vaga de estacionamento.
- `GET /api/parking-spot/{id}`: Retorna os detalhes de uma vaga específica.
- `GET /api/parkingspots`: Retorna uma lista com todas as vagas de estacionamento.
- `GET /api/parking-spot?page={pageNumber}&size={pageSize}&sort={sortProperty}`: Retorna uma página específica de vagas de estacionamento, com paginação e ordenação.

![image](https://github.com/IgorWolf99/ParkingSpotControl/assets/116234237/44430a70-eb26-496f-9e23-269d0f27eacd)
       
       
