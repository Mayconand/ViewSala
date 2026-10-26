# ViewSala

Sistema para consultar e organizar a utilização de salas de aula.

O objetivo do projeto é permitir o cadastro de salas, professores, matérias e aulas, facilitando a visualização de qual aula está acontecendo em cada sala.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- JUnit
- Mockito
- Lombok

## Funcionalidades

### Salas

- Cadastrar sala
- Listar salas
- Buscar sala por ID
- Atualizar sala
- Excluir sala
- Impedir nomes duplicados

### Professores

- Cadastrar professor
- Listar professores
- Buscar professor por ID
- Atualizar professor
- Excluir professor
- Padronizar nomes completos

Professores podem possuir o mesmo nome, pois cada registro é identificado internamente por seu próprio ID.

### Em desenvolvimento

- Cadastro de matérias
- Cadastro e agendamento de aulas
- Associação entre aula, sala, professor e matéria
- Verificação de conflitos de horário
- Endpoints REST

## Estrutura do projeto

```text
src/main/java/br/com/lamit/viewsala
├── entity
├── exception
├── repository
├── service
└── util
```

O projeto está organizado em camadas:

- `entity`: entidades persistidas no banco de dados
- `repository`: comunicação com o PostgreSQL
- `service`: regras de negócio e validações
- `exception`: exceções personalizadas
- `util`: funções reutilizáveis

## Configuração do banco de dados

O arquivo real `application.properties` não é enviado ao GitHub porque pode conter credenciais.

Para configurar o projeto:

1. Copie o arquivo de exemplo.
2. Renomeie a cópia para `application.properties`.
3. Informe o endereço do banco, usuário e senha.
4. Crie o banco de dados `viewsala` no PostgreSQL.

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/viewsala
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## Executando o projeto

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

## Executando os testes

No Windows:

```powershell
.\mvnw.cmd test
```

No Linux ou macOS:

```bash
./mvnw test
```

## Status

Projeto em desenvolvimento, criado para praticar Java, Spring Boot, JPA, PostgreSQL, JUnit e Mockito.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.