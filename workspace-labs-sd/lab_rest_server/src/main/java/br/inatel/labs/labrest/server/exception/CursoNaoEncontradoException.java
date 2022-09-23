package br.inatel.labs.labrest.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception: curso não encontrado pelo ID
 *
 * @author Rafaela Papale
 * @since 22 set 2022
 */
public class CursoNaoEncontradoException extends ResponseStatusException {
    public CursoNaoEncontradoException(Long cursoId) {
        super(HttpStatus.NOT_FOUND, String.format("Nenhum curso encontrado com id %s", cursoId));
    }
}
