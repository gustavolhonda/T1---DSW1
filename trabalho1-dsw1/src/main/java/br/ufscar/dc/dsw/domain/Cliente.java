package br.ufscar.dc.dsw.domain;

public class Cliente {

    private Integer id;
    private String email;
    private String senha;
    private String cpf;
    private String nome;
    private String telefone;
    private String sexo;
    private String dataNasc;


    public Cliente(Integer id, String email, String senha, String cpf, String nome, String telefone, String sexo,
        String dataNasc) {
      this.id = id;
      this.email = email;
      this.senha = senha;
      this.cpf = cpf;
      this.nome = nome;
      this.telefone = telefone;
      this.sexo = sexo;
      this.dataNasc = dataNasc;
    }

    public Cliente(String email, String senha, String cpf, String nome, String telefone, String sexo, String dataNasc) {
      this.email = email;
      this.senha = senha;
      this.cpf = cpf;
      this.nome = nome;
      this.telefone = telefone;
      this.sexo = sexo;
      this.dataNasc = dataNasc;
    }

    public String getTelefone() {
      return telefone;
    }

    public void setTelefone(String telefone) {
      this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

}
