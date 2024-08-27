package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;

public class Agendamento {

    private Integer id_cliente;
    private Integer id_profissional;
    private String data_hora;
    private String linkVideoconferencia;
    private Integer id;

    public Agendamento(Integer id) {
        this.id = id;
    }

    public Agendamento(Integer id_cliente, Integer id_profissional, String data_hora) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.data_hora = data_hora;
        this.linkVideoconferencia = "https://videoconferencia.example.com/" + new java.util.Random().nextInt(1000000);
    }

    public Agendamento(Integer id_cliente, Integer id_profissional, String data_hora, String linkVideoconferencia, Integer id) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.data_hora = data_hora;
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
        return data_hora;
    }

    public String getLinkVideoconferencia() {
        return linkVideoconferencia;
    }

    public Integer getId() {
        return id;
    }

    public void setData(String data_hora) {
        this.data_hora = data_hora;
    }

    public void setLinkVideoconferencia(String linkVideoconferencia) {
        this.linkVideoconferencia = linkVideoconferencia;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
