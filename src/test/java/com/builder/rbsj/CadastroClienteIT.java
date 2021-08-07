package com.builder.rbsj;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroClienteIT {
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setup(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/v1/clientes";
	}

	@Test
	void deveRetornarStatus200_QuandoConsultarClientes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveConter2Clientes_QuandoConsultarClientes() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("_embedded.clientes.nome", hasItems("Rui Junior", "Lilian Silva"))
			.body("_embedded.clientes", hasSize(2));
	}
}