package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Agendamento")
public class Agendamento extends AbstractEntity<Long> {

	@NotNull (message = "{NotNull.agendamento.data}")
	@Column(nullable = false, length = 30)
	private String data_hora;
	
	@Column(nullable = false, length = 50)
	private String linkVideoconferencia;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@NotNull 
	@ManyToOne
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;

	public String getData_hora() {
		return data_hora;
	}

	public void setData_hora(String data_hora) {
		this.data_hora = data_hora;
	}

	public String getLinkVideoconferencia() {
		return linkVideoconferencia;
	}

	public void setLinkVideoconferencia(String linkVideoconferencia) {
		this.linkVideoconferencia = linkVideoconferencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
}
