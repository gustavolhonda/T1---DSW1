package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
@PrimaryKeyJoinColumn(name = "id_cliente") 
public class Cliente extends Usuario {

	@NotBlank(message = "{NotBlank.cliente.telefone}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String telefone;

	@NotBlank(message = "{NotBlank.cliente.sexo}")
	@Size(max = 1)
	@Column(nullable = false, length = 1)
	private String sexo;
    
	@NotNull(message = "{NotNull.cliente.dataNasc}")
	@Column(nullable = false)
	private String dataNasc;

	@OneToMany(mappedBy = "cliente")
	private List<Agendamento> agendamentos;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNasce() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
}
