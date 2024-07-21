package br.ufscar.dc.dsw.domain;

public class Profissional extends Usuario {

    private String especialidade;

    public Profissional(Integer id, String nome, String email, String senha, String cpf, String papel, String especialidade) {
        super(id, nome, email, senha, cpf, papel);
        this.especialidade = especialidade;
    }
    public Profissional(String nome, String email, String senha, String cpf, String papel, String especialidade) {
        super(nome, email, senha, cpf, papel);
        this.especialidade = especialidade;
    }


    public Profissional(Integer id) {
        super(id);
    }
    
    public Profissional() {

    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
