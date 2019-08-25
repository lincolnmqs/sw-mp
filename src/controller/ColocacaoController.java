package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.Dao;
import model.Colocacao;

@ManagedBean
@ViewScoped
public class ColocacaoController {
	
	private Colocacao colocacao = new Colocacao();
	
	public void gravar(){
		Dao<Colocacao> dao = new Dao<Colocacao>(Colocacao.class);
		
		if(this.colocacao.getId() == null) dao.adiciona(colocacao);
		else dao.atualiza(this.colocacao);
		
		colocacao = new Colocacao();
	}
	
	public List<Colocacao> getTodasColocacoes(){
		return new Dao<Colocacao>(Colocacao.class).listaTodos();
	}

	public void remover(Colocacao c){
		try{
			new Dao<Colocacao>(Colocacao.class).remove(c.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("remover",  new FacesMessage("Impossível Remover: Colocação associada a Equipe."));
	        return;
		}
	}
	
	public void carregar(Colocacao c){
		this.colocacao = c;
	}

	public Colocacao getColocacao() {
		return colocacao;
	}

	public void setColocacao(Colocacao colocacao) {
		this.colocacao = colocacao;
	}

	
	
	
}
