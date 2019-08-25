package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome, cpf, curso_periodo, senha, ddd_telefone, email;
	private boolean nivel_acesso;
	private int pontuacao;
	
	@ManyToOne
	private Universidade universidade = new Universidade();
	
	@ManyToMany(mappedBy="participantes")
	private List<Equipe> equipes = new LinkedList<Equipe>();

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {		
		this.cpf = cpf;
	}

	public String getCurso_periodo() {
		return curso_periodo;
	}

	public void setCurso_periodo(String curso_periodo) {
		this.curso_periodo = curso_periodo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDdd_telefone() {
		return ddd_telefone;
	}

	public void setDdd_telefone(String ddd_telefone) {
		this.ddd_telefone = ddd_telefone;
	}

	public boolean isNivel_acesso() {
		return nivel_acesso;
	}

	public void setNivel_acesso(boolean nivel_acesso) {
		this.nivel_acesso = nivel_acesso;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

}
