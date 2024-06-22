package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
/*
	Para fazer o Build do Projeto

	1) Botao M do Maven;
	2) Opcao: Lifecycle;
	3) Opcao: package;
	4) Botao direito: Run Maven Build;
	5) Sera gravado por exemplo: F:\Celio\Java\ProjIntelliJ\api\target\api-0.0.1-SNAPSHOT.jar
	6) Ir para o Terminal do IntelliJ;
	7) Verificar o local dos JAR: dir target/
	8) Certificar que a versao do Java: java -version
	9) Digitar a seguinte linha de comando:
	java -Dspring.profiles.active=prod -DDATASOURCE_URL=jdbc:mysql://localhost:3306/vollmed_api -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=root -jar target/api-0.0.1-SNAPSHOT.jar
	10) Realize o teste: http://localhost:8080/swagger-ui/index.html ou Postman

	Build com arquivo .war
	======================
	Projetos que utilizam o Spring Boot geralmente utilizam o formato jar para o empacotamento da aplicacao,
	conforme foi demonstrado ao longo desta aula. Entretanto, o Spring Boot fornece suporte para o empacotamento
	da aplicacao via formato war, que era bastante utilizado em aplicacoes Java antigamente.

	Caso voce queira que o build do projeto empacote a aplicacao em um arquivo no formato war, vai precisar
	realizar as seguintes alteracoes:

	1) Adicionar a tag <packaging>war</packaging> no arquivo pom.xml do projeto, devendo essa tag ser filha
	da tag raiz <project>:

	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  	<modelVersion>4.0.0</modelVersion>
  	<parent>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-parent</artifactId>
    	<version>3.0.0</version>
    	<relativePath/> <!-- lookup parent from repository -->
  	</parent>
  	<groupId>med.voll</groupId>
  	<artifactId>api</artifactId>
  	<version>0.0.1-SNAPSHOT</version>
  	<name>api</name>

  	<packaging>war</packaging>

	2) Ainda no arquivo pom.xml, adicionar a seguinte depend�ncia:

	<dependency>
	  <groupId>org.springframework.boot</groupId>
	  <artifactId>spring-boot-starter-tomcat</artifactId>
	  <scope>provided</scope>
	</dependency>

	3) Alterar a classe main do projeto (ApiApplication) para herdar da classe SpringBootServletInitializer,
	bem como sobrescrever o metodo configure:

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

	Pronto! Agora, ao realizar o build do projeto, sera gerado um arquivo com a extensao .war dentro
	do diretorio target, ao inves do arquivo com a extensao .jar.
	
 */
}
