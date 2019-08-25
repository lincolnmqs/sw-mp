package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.Dao;
import model.Prova;

@ManagedBean
@ViewScoped
public class ProvaController {
	
	private Prova prova = new Prova();
	
	public void gravar(){
		Dao<Prova> dao = new Dao<Prova>(Prova.class);
		
		if(this.prova.getId() == null) dao.adiciona(prova);
		else dao.atualiza(this.prova);
		
		prova = new Prova();
	}
	
	public List<Prova> getTodasProvas(){
		return new Dao<Prova>(Prova.class).listaTodos();
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public void remover(Prova p){
		try{
			new Dao<Prova>(Prova.class).remove(p.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("remover",  new FacesMessage("Impossível Remover: Prova associada a Problema."));
	        return;
		}
	}
	
	public void carregar(Prova p){
		this.prova = p;
	}

	public Prova getProva() {
		return prova;
	}
	
}
