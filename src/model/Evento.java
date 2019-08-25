package model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome, descricao, descricaoOrganizadores, imagemLogo, imagemBanner, emailContato, telefoneContato;
	private Boolean aberto;

	@Temporal(TemporalType.DATE)
	private Calendar dataEvento = Calendar.getInstance();
	
	@Temporal(TemporalType.DATE)
	private Calendar dataFinalInscricao = Calendar.getInstance();

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true) // cascade ALL: ao inserir, alterar e remover cascade, alterando os itens relacionados 
	private List<Ranking> premiacoes = new LinkedList<Ranking>();
	
	@ManyToOne
	private Universidade universidade = new Universidade();
	
	@OneToOne
	private Prova prova = new Prova();
	
	@ManyToOne
	private Usuario usuario = new Usuario();
	
	public void add(Ranking prem) {
		this.premiacoes.add(prem);
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoOrganizadores() {
		return descricaoOrganizadores;
	}

	public void setDescricaoOrganizadores(String descricaoOrganizadores) {
		this.descricaoOrganizadores = descricaoOrganizadores;
	}

	public Boolean getAberto() {
		return aberto;
	}

	public void setAberto(Boolean aberto) {
		this.aberto = aberto;
	}

	public Calendar getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Calendar dataEvento) {
		this.dataEvento = dataEvento;
	}

	public List<Ranking> getPremiacoes() {
		return premiacoes;
	}

	public void setPremiacoes(List<Ranking> premiacoes) {
		this.premiacoes = premiacoes;
	}

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataFinalInscricao() {
		return dataFinalInscricao;
	}

	public void setDataFinalInscricao(Calendar dataFinalInscricao) {
		this.dataFinalInscricao = dataFinalInscricao;
	}

	public String getImagemLogo() {
		return imagemLogo;
	}

	public void setImagemLogo(String imagemLogo) {
		this.imagemLogo = imagemLogo;
	}

	public String getImagemBanner() {
		return imagemBanner;
	}

	public void setImagemBanner(String imagemBanner) {
		this.imagemBanner = imagemBanner;
	}

	public String getEmailContato() {
		return emailContato;
	}

	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}
}
