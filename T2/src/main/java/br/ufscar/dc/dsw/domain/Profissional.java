package br.ufscar.dc.dsw.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
@PrimaryKeyJoinColumn(name = "id_profissional") 
@JsonIgnoreProperties(value = { "agendamentos", "data"})
public class Profissional extends Usuario {
	
	@NotBlank(message = "{NotBlank.profissional.especialidade}")
	@Column(length = 100)
	private String especialidade;

	@Column(columnDefinition = "TEXT")
	private String curriculo;

	@OneToMany(mappedBy = "profissional")
	private List<Agendamento> agendamentos;

	@Lob
	@Basic
	@Column(length=10485760) // 10MB
	private byte[] data;

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

	public void setData(byte[] data) {
			this.data = data;
	}

	public byte[] getData() {
		return data;
	}
}
