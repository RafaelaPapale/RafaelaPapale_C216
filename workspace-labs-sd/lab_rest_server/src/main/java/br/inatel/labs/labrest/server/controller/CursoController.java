package br.inatel.labs.labrest.server.controller;

import java.util.List;
import java.util.Optional;

import br.inatel.labs.labrest.server.exception.CursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.inatel.labs.labrest.server.model.Curso;
import br.inatel.labs.labrest.server.service.CursoService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/curso")
public class CursoController {
	@Autowired
	private CursoService servico;
	
	@RequestMapping
	public List<Curso> listar(){
		return servico.pesquisarCurso();
	}

	@GetMapping("/{id}")
	public Curso buscar(@PathVariable("id") Long cursoId){
		Optional<Curso> opCurso = servico.buscarCursoPeloId(cursoId);
		if(opCurso.isPresent())
			return opCurso.get();
		else{
			throw new CursoNaoEncontradoException(cursoId);
		}
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Curso criar(@RequestBody Curso curso){
		return servico.criarCurso(curso);
	}

	@PutMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void atualizar(@RequestBody Curso curso){
		servico.atualizarCurso(curso);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("id") Long cursoIdRemover){
		Optional<Curso> opCurso = servico.buscarCursoPeloId(cursoId_ParaRemover);
		if(opCurso.isEmpty()){
			throw new CursoNaoEncontradoException(cursoId_ParaRemover);
		}else{
			Curso cursoASerRemovido = opCurso.get();
			servico.removerCursoPeloId(cursoASerRemovido);
		}
	}

	@GetMapping("/pesquisa")
	public List<Curso> listarPeloFragmentoDaDescricao(@RequestParam("descricao") String fragDescricao){
		return servico.pesquisarCursoPeloFragmentoDescricao(fragDescricao);
	}

}
