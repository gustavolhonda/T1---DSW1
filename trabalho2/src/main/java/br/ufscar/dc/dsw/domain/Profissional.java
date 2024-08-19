package br.ufscar.dc.dsw.domain;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
@PrimaryKeyJoinColumn(name = "id_profissional") 
public class Profissional extends Usuario {
	
	@NotBlank(message = "{NotBlank.profissional.especialidade}")
	@Column(length = 100)
	private String especialidade;

	@Column(columnDefinition = "TEXT")
	private String curriculo;

	@OneToMany(mappedBy = "profissional")
	private List<Agendamento> agendamentos;

	public String getEspecialidade() {
			return especialidade;
	}

	public void setEspecialidade(String especialidade) {
			this.especialidade = especialidade;
	}

	public String getCurriculo() {
			return curriculo;
	}

	public void setCurriculo(String curriculo) {
			this.curriculo = curriculo;
	}

	public List<Agendamento> getAgendamentos() {
			return agendamentos;
	}
}
