package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.Dao;
import model.Problema;
import model.Prova;

@ManagedBean
@ViewScoped
public class ProblemaController {
	
	private Problema problema = new Problema();
	private Integer provaId;
	
	public void limpar(){
		this.problema = new Problema();
		this.provaId = null;
	}
	
	public void gravar(){
		Dao<Problema> dao = new Dao<Problema>(Problema.class);
		Prova p = new Dao<Prova>(Prova.class).listaPorId(provaId);
		
		this.problema.setProva(p);
		
		if(this.problema.getId() == null) dao.adiciona(problema);
		else dao.atualiza(this.problema);
		
		problema = new Problema();
		provaId = null;
	}

	public Problema getProblemas() {
		return problema;
	}
	
	public List<Problema> getTodosProblemas(){
		return new Dao<Problema>(Problema.class).listaTodos();
	}
	
	public List<Prova> getTodasProvas(){
		return new Dao<Prova>(Prova.class).listaTodos();
	}
		
	public Integer getProvaId() {
		return provaId;
	}

	public void setProvaId(Integer provaId) {
		this.provaId = provaId;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public void remover(Problema p){
		new Dao<Problema>(Problema.class).remove(p.getId());
	}
	
	public void carregar(Problema p){
		this.problema = p;
		this.provaId = problema.getProva().getId();
	}

	public Problema getProblema() {
		return problema;
	}
	
}
