package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Agendamento {

    private String id_cliente;
    private String id_profissional;
    private Date dataHora;
    private String linkVideoconferencia;
    private Integer id;

    public Agendamento(Integer id) {
        this.id = id;
    }

    public Agendamento(String id_cliente, String id_profissional, Date dataHora, Integer id) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.dataHora = dataHora;
    }

    public Agendamento(String id_cliente, String id_profissional, Date dataHora, String linkVideoconferencia, Integer id) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.dataHora = dataHora;
        this.linkVideoconferencia = linkVideoconferencia;
        this.id = id;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(String id_profissional) {
        this.id_profissional = id_profissional;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public String getLinkVideoconferencia() {
        return linkVideoconferencia;
    }

    public Integer getId() {
        return id;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public void setLinkVideoconferencia(String linkVideoconferencia) {
        this.linkVideoconferencia = linkVideoconferencia;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
