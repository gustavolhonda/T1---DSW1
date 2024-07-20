package br.ufscar.dc.dsw.domain;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String papel; // CLIENTE, PROFISSIONAL, ADMIN

    public Usuario(Long id, String nome, String email, String senha, String cpf, String papel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.papel = papel;
    }

    public Usuario(String nome, String email, String senha, String cpf, String papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.papel = papel;
    }

    public Usuario() {
        //TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }


}
