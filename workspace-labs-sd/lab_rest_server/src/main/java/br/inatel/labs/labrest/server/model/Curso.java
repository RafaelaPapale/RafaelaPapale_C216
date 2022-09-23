package br.inatel.labs.labrest.server.model;

import java.util.Objects;

public class Curso {
	private Long id;
	private String descricao;
	private Integer cargaHoraria;
	
	public Curso(long id, String descricao, Integer cargaHoraria) {
		this.id = id;
		this.descricao = descricao;
		this.cargaHoraria = cargaHoraria;
	}
	
	public Curso() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return id == other.id;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
