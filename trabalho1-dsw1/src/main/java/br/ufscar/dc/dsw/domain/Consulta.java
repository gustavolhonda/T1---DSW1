package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Consulta {

    private String cpfCliente;
    private String cpfProfissional;
    private Date dataHora;
    private String linkVideoconferencia;
    private Integer id;

    public Consulta(Integer id) {
        this.id = id;
    }

    public Consulta(String cpfCliente, String cpfProfissional, Date dataHora, Integer id) {
        this.cpfCliente = cpfCliente;
        this.cpfProfissional = cpfProfissional;
        this.dataHora = dataHora;
    }

    public Consulta(String cpfCliente, String cpfProfissional, Date dataHora, String linkVideoconferencia) {
        this.cpfCliente = cpfCliente;
        this.cpfProfissional = cpfProfissional;
        this.dataHora = dataHora;
        this.linkVideoconferencia = linkVideoconferencia;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getCpfProfissional() {
        return cpfProfissional;
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

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setCpfProfissional(String cpfProfissional) {
        this.cpfProfissional = cpfProfissional;
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
