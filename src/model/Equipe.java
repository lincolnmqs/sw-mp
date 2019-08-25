package model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Equipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private Boolean nivel;
	private Integer pontuacao;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataCriacao = Calendar.getInstance();
	
	@ManyToMany
	private List<Usuario> participantes = new LinkedList<Usuario>();
	
	@ManyToOne
	private Evento evento = new Evento();
	
	@ManyToOne
	private Colocacao colocacao = new Colocacao();
	
	@ManyToOne
	private Universidade universidade = new Universidade();

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

	public Boolean getNivel() {
		return nivel;
	}

	public void setNivel(Boolean nivel) {
		this.nivel = nivel;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Usuario> participantes) {
		this.participantes = participantes;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Colocacao getColocacao() {
		return colocacao;
	}

	public void setColocacao(Colocacao colocacao) {
		this.colocacao = colocacao;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}
	
}
