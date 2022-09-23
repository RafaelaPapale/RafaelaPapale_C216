package br.inatel.labs.labrest.server.service;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.inatel.labs.labrest.server.model.Curso;

/**
 *
 *
 * @author Rafaela Papale
 * @since 23 set 2022
 */
@Service
public class CursoService {
	
	private List<Curso> listaDeCursos = new ArrayList<>();
	
	@PostConstruct
	public void setup() {
		listaDeCursos.add(new Curso(1L, "Curso com Spring Boot", 20));
		listaDeCursos.add(new Curso(2L, "Programacao Java 11", 80));
		listaDeCursos.add(new Curso(3L, "Dominando a IDE IntelliJ", 120));
	}
	
	public List<Curso> pesquisarCurso(){
		return listaDeCursos;
	}

	public Optional<Curso> buscarCursoPeloId(Long cursoId){
		return listaDeCursos.stream().filter(c -> c.getId().equals(cursoId)).findFirst();
	}

	public Curso criarCurso(Curso curso){
		curso.setId(new Random().nextLong());
		listaDeCursos.add(curso);
		return curso;
	}

	public void atualizarCurso(Curso curso){
		listaDeCursos.remove(curso);
		listaDeCursos.add(curso);
	}

	public void removerCursoPeloId(Curso cursoASerRemovido){
		listaDeCursos.remove(cursoASerRemovido);
	}

	/**
	 * Pesquisa cursos pelo fragmento da descrição ignorando espaços em branco e caixa
	 *
	 * @param fragDescricao
	 * @return
	 */
	public List<Curso> pesquisarCursoPeloFragmentoDescricao(String fragDescricao){
		if(Objects.isNull(fragDescricao) || fragDescricao.isBlank()){
			return List.of();
		}
		return this.listaDeCursos.stream()
				.filter(c -> c.getDescricao().trim().toLowerCase().contains(fragDescricao.trim().toLowerCase()))
				.collect(Collectors.toList());
	}

}
