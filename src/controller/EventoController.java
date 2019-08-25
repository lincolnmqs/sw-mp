package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.Dao;
import dao.EventoDao;
import model.Colocacao;
import model.Evento;
import model.Prova;
import model.Ranking;
import model.Universidade;

@ManagedBean
@ViewScoped
public class EventoController {
	private Evento evento = new Evento();
	private String premiacao, classeMapa, classePremiacao, classeEmail, classeTelefone, classeDescricao;
	private Integer colocacaoId, universidadeId, provaId;
	int ind = 1;

	public void limpar(){
		evento = new Evento();
		colocacaoId = universidadeId = provaId = null;
	}
	
	public List<Colocacao> getTodasColocacoes(){
		return new Dao<Colocacao>(Colocacao.class).listaTodos();
	}
	
	public void gravarPremiacao(){
		Colocacao c = new Dao<Colocacao>(Colocacao.class).listaPorId(colocacaoId);
		
		Ranking rank = new Ranking();
		
		rank.setColocacao(c);
		rank.setEvento(evento);
		rank.setPremiacao(premiacao);
		
		evento.add(rank);
		
		premiacao = null;
		colocacaoId = null;
	}
	
	public List<Ranking> getPremiacoesdoEvento() {
		return evento.getPremiacoes();
	}
	
	public List<Ranking> premiacoesdoEvento(Evento eve) {
		return new EventoDao().listaPorId(eve).getPremiacoes();
	}
	
	public void removerPremiacao(Ranking rank){
		evento.getPremiacoes().remove(rank);
	}
	
	public void gravar(){
		evento.setUsuario(new LoginController().getUsu());
		evento.setUniversidade(new Dao<Universidade>(Universidade.class).listaPorId(universidadeId));
		evento.setProva(new Dao<Prova>(Prova.class).listaPorId(provaId));
		
		if (this.evento.getId() == null) {	
			new Dao<Evento>(Evento.class).adiciona(evento);
		} else {
			new Dao<Evento>(Evento.class).atualiza(evento);
		}
		
		this.evento = new Evento();
	}

	public List<Evento> getTodosEventos(){
		return new Dao<Evento>(Evento.class).listaTodos();
	}
	
	public List<Evento> getTodosEventosAtivos(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.getExternalContext().getSessionMap().put("evento", null);
		
		return new EventoDao().listaDosEventosAtivos();
	}
	
	public void remover(Evento e){
		new Dao<Evento>(Evento.class).remove(e.getId());
	}
	
	public void carregar(Evento e){
		evento = new EventoDao().listaPorId(e);
		provaId = evento.getProva().getId();
		universidadeId = evento.getUniversidade().getId();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getPremiacao() {
		return premiacao;
	}

	public void setPremiacao(String premiacao) {
		this.premiacao = premiacao;
	}

	public Integer getColocacaoId() {
		return colocacaoId;
	}

	public void setColocacaoId(Integer colocacaoId) {
		this.colocacaoId = colocacaoId;
	}

	public Integer getUniversidadeId() {
		return universidadeId;
	}

	public void setUniversidadeId(Integer universidadeId) {
		this.universidadeId = universidadeId;
	}

	public Integer getProvaId() {
		return provaId;
	}

	public void setProvaId(Integer provaId) {
		this.provaId = provaId;
	}

	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
	}

	public String getClasseMapa() {
		return classeMapa;
	}

	public void setClasseMapa(int classeMapa) {
		this.classeMapa = "collapseMapa" + classeMapa;
	}

	public String getClassePremiacao() {
		return classePremiacao;
	}

	public void setClassePremiacao(int classePremiacao) {
		this.classePremiacao = "collapsePremiacao" + classePremiacao;
	}

	public String getClasseEmail() {
		return classeEmail;
	}

	public void setClasseEmail(int classeEmail) {
		this.classeEmail = "collapseEmail" + classeEmail;
	}

	public String getClasseTelefone() {
		return classeTelefone;
	}

	public void setClasseTelefone(int classeTelefone) {
		this.classeTelefone = "collapseTelefone" + classeTelefone;
	}

	public String getClasseDescricao() {
		return classeDescricao;
	}

	public void setClasseDescricao(int classeDescricao) {
		this.classeDescricao = "collapseDescricao" + classeDescricao;
	}
	

}
