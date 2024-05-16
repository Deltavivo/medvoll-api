<h1 align="center"> API Med Voll </h1>

![Linguagem usada](https://img.shields.io/badge/JAVA:-17-005100?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-spring_security-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-spring_validation-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-spring_jpa-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-spring_devtools-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-javajwt-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-lombok-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-flyway-3c0064?style=for-the-badge)
![Biblioteca usada](https://img.shields.io/badge/Biblioteca:-mysql-3c0064?style=for-the-badge)

## Descrição do projeto:

<p align="justify">
API com CRUD de medicos e pacientes, garantindo segurança através da authenticação e autorização com token JWT. Gravação dos dados em base mysql e versionamento da base de dados utilizando flyway.
</p>

## Como funciona:



## Temas encontrados no projeto

- Desenvolvimento de uma API Rest
- CRUD (Create, Read, Update e Delete)
- Validações
- Paginação e ordenação
- Boas práticas Rest
- Tratamento de erros
- Controle de acesso com JWT
- Funcionam os Filters em uma requisição;
- Utilizar a biblioteca Auth0 java-jwt para realizar a validação dos tokens recebidos na API
- Realizar o processo de autenticação da requisição, utilizando a classe SecurityContextHolder, do Spring
- Liberar e restringir requisições, de acordo com a URL e o verbo do protocolo HTTP
- Documentação da API (SpringDoc)
- Testes automatizados
- Implementacao de consulta JPQL (Java Persistence Query Language)
- Utilizacao dos princípios SOLID para deixar o código da funcionalidade de agendamento mais fácil de entender, evoluir e testar


## Ponto alto, recordar como funciona o Java Web para intersecção das chamadas utilizando filtros:

![Filters]("https://imgur.com/a/q1i0OQ3")

## Técnicas e tecnologias utilizadas:

- ``InteliJ IDEA;``
- ``Spring Boot;``
- ``Spring Security;``
- ``Spring Validation;``
- ``Spring JPA;``
- ``Spring DevTools;``
- ``Java JWT;``
- ``Lombok;``
- ``Flyway;``
- ``MySql;``


## Alternativa caso queira utilizar empacotamento do tipo war na aplicacao

- Incluir a tag

~~~
<packaging>war</packaging>
~~~

- Incluir a dependencia

~~~
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-tomcat</artifactId>
<scope>provided</scope>
</dependency>
~~~

- Sobreescrever o metodo principal

~~~
@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {

@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
return application.sources(ApiApplication.class);
}

public static void main(String[] args) {
SpringApplication.run(ApiApplication.class, args);
}

}
~~~

## Utilizar Native Image com Spring Boot 3 ( como  Micronaut e Quarkus utilizam de gerar um binario da aplicacao)

- Incluir dependencia

~~~
<plugin>
  <groupId>org.graalvm.buildtools</groupId>
  <artifactId>native-maven-plugin</artifactId>
</plugin>
~~~

- Após isso, a geração da imagem deve ser feita via terminal, com o seguinte comando Maven sendo executado no diretório raiz do projeto:

~~~
./mvnw -Pnative native:compile
~~~

- **** IMPORTANTE ****

Atenção! Para executar o comando anterior e gerar a imagem nativa do projeto, é necessário que você tenha instalado em seu computador o GraalVM (máquina virtual Java com suporte ao recurso de Native Image) em uma versão igual ou superior a 22.3.
