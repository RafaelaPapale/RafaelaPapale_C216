package br.inatel.labs.labrest.server.controller;

import br.inatel.labs.labrest.server.model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursoControllerTestJava {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void deveListarCursos(){
        webTestClient.get()
                .uri("/curso")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .returnResult();
    }

    @Test
    void dadoCursoIdValido_quandoGetCursoPeloId_entaoRespondeComCursoValido(){
        Long cursoIdValido = 1L;

        Curso cursoRespondido = webTestClient.get()
                .uri("/curso/" + cursoIdValido)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Curso.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(cursoRespondido);
        assertEquals(cursoRespondido.getId(),cursoIdValido);

	assertThat(cursoRespondido).isNotNull();
	assertThat(cursoIdValido).isEqualTo(cursoRespondido.getId());	
    }

    @Test
    void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFound(){
        Long cursoIdInvalido = 99L;

        webTestClient.get()
                .uri("/curso/" + cursoIdInvalido)
                .exchange()
                .expectStatus().isNotFound();
    }

    // DESAFIO PASSADO EM SALA DE AULA
    @Test
    void dadoNovoCursoValido_quandoPostCurso_entaoResponseComStatusCreated(){
        Curso novoCurso = new Curso();
        novoCurso.setDescricao("REST com Spring Boot e Spring WebFlux");
        novoCurso.setCargaHoraria(120);

        Curso cursoRespondido = webTestClient.post()
                .uri("/curso")
                .bodyValue(novoCurso)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Curso.class)
                .returnResult().getResponseBody();

        assertThat(cursoRespondido).isNotNull();
	assertThat(cursoRespondido.getId()).isNotNull();
    }

    @Test
    void dadoCursoValido_quandoPutCurso_entaoResponseComStatusAccepted() {
        Curso cursoExistente = new Curso(1L, "REST e Spring Boot e Spring WebFlux", 120);

         webTestClient
                .put()
                .uri("/curso")
                .bodyValue(cursoExistente)
                .exchange()
                .expectStatus().isAccepted();
    }

    @Test
	void dadoCursoValido_quandoDeleteCursoPeloId_entaoResponseComStatusDeleted() {
		Long cursoIdValidoRemover = 1L;
	
		Curso cursoRespondido = webTestClient.delete()
			.uri("/curso/" + cursoIdValidoRemover)
			.exchange()
			.expectStatus()
				.isNoContent()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(cursoRespondido).isNull();
	}

    @Test
    void dadoCursoInvalido_quandoDeleteCursoPeloId_respostaComStatusNotFound(){
        Long cursoIdInvalidoRemover = 99L;

        webTestClient
                .delete()
                .uri("/curso/" + cursoIdInvalidoRemover)
                .exchange()
                .expectStatus().isNotFound();
    }
}
