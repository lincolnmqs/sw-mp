package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.Dao;
import model.Assunto;

@ManagedBean
@ViewScoped
public class AssuntoController {
	
	private Assunto assunto = new Assunto();
	
	public void gravar(){
		Dao<Assunto> dao = new Dao<Assunto>(Assunto.class);
		
		if(this.assunto.getId() == null) dao.adiciona(assunto);
		else dao.atualiza(this.assunto);
		
		assunto = new Assunto();
	}
	
	public List<Assunto> getTodosAssuntos(){
		return new Dao<Assunto>(Assunto.class).listaTodos();
	}

	public void remover(Assunto a){
		try {
			new Dao<Assunto>(Assunto.class).remove(a.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("remover",  new FacesMessage("Impossível Remover: Assunto associado a Feedback."));
	        return;
		}
	}
	
	public void carregar(Assunto a){
		this.assunto = a;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	
	
	
}
