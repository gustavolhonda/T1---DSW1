package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;

public class Agendamento {

    private String id_cliente;
    private String id_profissional;
    private Date data;
    private Time hora;
    private String linkVideoconferencia;
    private Integer id;

    public Agendamento(Integer id) {
        this.id = id;
    }

    public Agendamento(String id_cliente, String id_profissional, Date data, Time hora, Integer id) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.data = data;
        this.hora = hora;
    }

    public Agendamento(String id_cliente, String id_profissional, Date data, Time hora, String linkVideoconferencia, Integer id) {
        this.id_cliente = id_cliente;
        this.id_profissional = id_profissional;
        this.data = data;
        this.hora = hora;
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

    public Date getData() {
        return data;
    }

     public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getLinkVideoconferencia() {
        return linkVideoconferencia;
    }

    public Integer getId() {
        return id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setLinkVideoconferencia(String linkVideoconferencia) {
        this.linkVideoconferencia = linkVideoconferencia;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
