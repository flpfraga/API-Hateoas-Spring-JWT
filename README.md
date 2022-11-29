API Java desenvolvida com Spring Boot que gerencia endpoints para filmes e usuários.

Versão:
  Java: 17
  Spring Boot: 2.7.4

Descrição:

Este sistema é uma API padrão RestFull Hateoas que gerencia endpoints para fornecer ao um usuário um catálogo de filmes por arquivo JSON. Gerencia também usuários com permissões
administrativas e comuns, validando as chamadas desses usuários através de autenticação.

Tecnologias:

Java;
Spring Boot
Spring Data JPA & Hibernate
Spring Security com autenticação JWT e token Auth0 no formado Bearer
Banco de dados MySql
Model Maper para conversão de entidades
Migrations com Flyway
OpenAPI com Swagger
Container com Docker
Princípios do SOLID

Funcionamento:
Autenticação:
	Autenticar usuário cadastrado e gerar token formato Bearer: POST/api/auth/v1 - passar em formato JSON parametro "userName" e "password"

Usuário comum:
	Cadastro de usuário novo: POST/api/user/v1 - passar em formato JSON parametro "userName", "password" e "fullName" (fullName opcional)
	Update de usuário: PUT/api/user/v1/{id} - passar em formato JSON parametro "userName", "password" e "fullName" (fullName opcional)
	Desativar usuário (exclusão lógica): PATCH/api/user/v1/desable{id}
	Adicionar Voto para um filme: PATCH/api/user/v1/vote/{id} - informar parâmetro movie_id e vote

Usuário Administrativo:
	Buscar todos usuários ativos: GET/api/admin/v1/users_active
	Cadastro de usuário novo: POST/api/admin/v1 - passar em formato JSON parametro "userName", "password" e "fullName" (fullName opcional)
	Update de usuário: PUT/api/admin/v1/{id} - passar em formato JSON parametro "userName", "password" e "fullName" (fullName opcional)
	Desativar usuário (exclusão lógica): PATCH/api/admin/v1/desable/{id}
	Criar um novo filme: POST/api/admin/v1/movie - passar em formato JSON parametro "title", "directo", "genre", "details", "actor"[]
	Update de filme: PUT/api/admin/v1/movie/id/{id} - passar em formato JSON parametro "title", "directo", "genre", "details", "actor"[]

Consultas de filmes:
	Todos filmes ordenado pelo nome: GET/api/movie/v1 - opção de paginação que ocorrerá caso seja passado o parâmetro page
	Todos filmes ordenado pelo nota: GET/api/movie/v1/top_rated - opção de paginação que ocorrerá caso seja passado o parâmetro page
	Filmes filtrados pelo diretor: GET/api/movie/v1/director - deverá ser informado parâmetros da busca e opção de paginação que ocorrerá caso seja passado o parâmetro page
	Filmes filtrados pelo nome: GET/api/movie/v1/title - deverá ser informado parâmetros da busca e opção de paginação que ocorrerá caso seja passado o parâmetro page	
	Filmes filtrados pelo genero: GET/api/movie/v1/genre - deverá ser informado parâmetros da busca e opção de paginação que ocorrerá caso seja passado o parâmetro page

Comnado para container (docker). Na pasta ./empresa-java: docker compose up -d --build

[![Docker Hub Repo](https://img.shields.io/docker/pulls/felipefragaff
/ioasys-api.svg)](https://hub.docker.com/repository/docker/felipefragaff/ioasys-api)
