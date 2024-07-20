package br.ufscar.dc.dsw.domain;

public class Cliente extends Usuario {
    private String telefone;
    private String sexo;
    private String dataNasc;

    public Cliente(Integer id, String nome, String email, String senha, String cpf, String papel, String telefone, String sexo, String dataNasc) {
        super(id, nome, email, senha, cpf, papel);
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
    }
    
    public Cliente(String nome, String email, String senha, String cpf, String papel, String telefone, String sexo, String dataNasc) {
        super(nome, email, senha, cpf, papel);
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
    }

    public Cliente(Integer id) {
        super(id);
    }
    
    public Cliente() {
    }

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

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

}
