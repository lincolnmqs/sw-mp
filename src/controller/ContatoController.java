package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.Dao;
import model.Contato;

@ManagedBean
@ViewScoped
public class ContatoController {
	
	private Contato contato = new Contato();
	
	public void limpar(){
		contato = new Contato();
	}
	
	public void gravar(){
		Dao<Contato> dao = new Dao<Contato>(Contato.class);
		
		if(this.contato.getId() == null) dao.adiciona(contato);
		else dao.atualiza(this.contato);
		
		contato = new Contato();
	}

	public Contato getContato() {
		return contato;
	}
	
	public List<Contato> getTodosContatos(){
		return new Dao<Contato>(Contato.class).listaTodos();
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public void remover(Contato c){
		new Dao<Contato>(Contato.class).remove(c.getId());
	}
	
	public void carregar(Contato c){
		this.contato = c;
	}
	
}