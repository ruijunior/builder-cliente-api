package com.builder.rbsj;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.builder.rbsj.domain.model.Cliente;
import com.builder.rbsj.domain.repository.ClienteRepository;
import com.builder.rbsj.util.DatabaseCleaner;
import com.builder.rbsj.util.ResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroClienteIT {

	private static final int CLIENTE_ID_INEXISTENTE = 1000;
	
	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private ClienteRepository repository;

	private int quantidadeClientesCadastrados;
	private String jsonCliente;

	private Cliente cliente2;
	
	@BeforeEach
	public void setup(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/v1/clientes";

		jsonCliente = ResourceUtils.getContentFromResource(
				"/json/cliente.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	private void prepararDados() {
		var cliente1 = new Cliente();
		cliente1.setNome("Diane Senna");
		cliente1.setCelular("(65)99990-4985");
		cliente1.setEmail("diane@gmail.com");
		cliente1.setNascimento(LocalDate.of(1993, 06, 14));
		repository.save(cliente1);

		cliente2 = new Cliente();
		cliente2.setNome("Lilian Silva");
		cliente2.setCelular("(65)99990-45814");
		cliente2.setEmail("lilian@gmail.com");
		cliente2.setNascimento(LocalDate.of(1983, 06, 27));
		repository.save(cliente2);

		quantidadeClientesCadastrados = (int) repository.count();
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
	void deveRetornarQuantidadeDeClientes_QuandoConsultarClientes() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("_embedded.clientes.nome", hasSize(quantidadeClientesCadastrados));
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCliente() {
		given()
			.body(jsonCliente)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarClienteExistente() {
		given()
			.pathParam("id", cliente2.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cliente2.getNome()));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarClienteInexistente() {
		given()
			.pathParam("id", CLIENTE_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}