package br.ufscar.dc.dsw.domain;

public class Profissional {

	private Integer id;
	private String email;
	private String senha;
	private String cpf;
  private String nome;
  private String especialidade;

	public Profissional(Integer id) {
		this.id = id;
	}

	public Profissional(String email, String senha, String cpf, String nome, String especialidade) {
		super();
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.nome = nome;
        this.especialidade = especialidade;
	}

	public Profissional(Integer id, String email, String senha, String cpf, String nome, String especialidade) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.nome = nome;
    this.especialidade = especialidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}