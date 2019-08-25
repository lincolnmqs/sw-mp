package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.Dao;
import dao.FeedbackDao;
import model.Assunto;
import model.Feedback;
import model.Usuario;

@ManagedBean
@ViewScoped
public class FeedbackController {
	
	private Feedback feedback = new Feedback();
	private Integer assuntoId;
	
	public List<Feedback> carregarPeloUsu(Usuario u){
		return new FeedbackDao().listaFeedback(u); 
	}
	
	public void limpar(){
		feedback = new Feedback();
		assuntoId = null;
	}
	
	public void gravar(Usuario u){
		Dao<Feedback> dao = new Dao<Feedback>(Feedback.class);
		Assunto a = new Dao<Assunto>(Assunto.class).listaPorId(assuntoId);
		
		this.feedback.setAssunto(a);
		this.feedback.setUsuario(u);
		
		if(this.feedback.getId() == null) dao.adiciona(feedback);
		else dao.atualiza(this.feedback);
		
		feedback = new Feedback();
		assuntoId = null;
	}

	public Feedback getFeedback() {
		return feedback;
	}
	
	public List<Feedback> getTodosFeedbacks(){
		return new Dao<Feedback>(Feedback.class).listaTodos();
	}
	
	public List<Assunto> getTodosAssuntos(){
		return new Dao<Assunto>(Assunto.class).listaTodos();
	}
		
	public Integer getAssuntoId() {
		return assuntoId;
	}

	public void setAssuntoId(Integer assuntoId) {
		this.assuntoId = assuntoId;
	}

	public void setFeedback(Feedback Feedback) {
		this.feedback = Feedback;
	}

	public void remover(Feedback f){
		new Dao<Feedback>(Feedback.class).remove(f.getId());
	}
	
	public void carregar(Feedback f){
		this.feedback = f;
		this.assuntoId = feedback.getAssunto().getId();
	}
	
}