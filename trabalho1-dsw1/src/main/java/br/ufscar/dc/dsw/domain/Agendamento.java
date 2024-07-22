package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;

public class Agendamento {

    private Integer id_cliente;
    private Integer id_profissional;
    private String data;
    private String hora;
    private String linkVideoconferencia;
    private Integer id;

    public Agendamento(Integer id) {
        this.id = id;
    }

    public Agendamento(Integer id_cliente, Integer id_profissional, String data, String hora) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.data = data;
        this.hora = hora;
    }

    public Agendamento(Integer id_cliente, Integer id_profissional, String data, String hora, String linkVideoconferencia, Integer id) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.data = data;
        this.hora = hora;
        this.linkVideoconferencia = linkVideoconferencia;
        this.id = id;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(Integer id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getData() {
        return data;
    }

     public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLinkVideoconferencia() {
        return linkVideoconferencia;
    }

    public Integer getId() {
        return id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLinkVideoconferencia(String linkVideoconferencia) {
        this.linkVideoconferencia = linkVideoconferencia;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
