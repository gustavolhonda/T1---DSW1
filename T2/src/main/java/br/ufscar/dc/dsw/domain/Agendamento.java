package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Agendamento")
public class Agendamento extends AbstractEntity<Long> {

	@NotNull (message = "{NotNull.agendamento.data}")
	@Column(nullable = false, length = 30)
	private String dataHora;
	
	@Column(nullable = false, length = 50)
	private String linkVideoConferencia;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@NotNull 
	@ManyToOne
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getLinkVideoConferencia() {
		return linkVideoConferencia;
	}

	public void setLinkVideoConferencia(String linkVideoConferencia) {
		this.linkVideoConferencia = linkVideoConferencia;
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
