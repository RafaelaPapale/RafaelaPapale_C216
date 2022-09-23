package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.model.Curso;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class WebClientGetCursoPeloId {
    public static void main(String[] args) {
        try {
            Mono<Curso> cursoMono = WebClient.create("http://localhost:8080")
                    .get()
                    .uri("curso/1")
                    .retrieve()
                    .bodyToMono(Curso.class);

            cursoMono.subscribe();

            Curso curso = cursoMono.block();

            System.out.println("Curso: " + curso);
        }catch (WebClientResponseException e){
            System.out.println("Erro aconteceu: ");
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage());
        }
    }
}
