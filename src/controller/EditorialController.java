package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.Dao;
import model.Problema;
import model.Editorial;
import model.Usuario;

@ManagedBean
@ViewScoped
public class EditorialController {
	
	private Editorial editorial = new Editorial();
	private Integer problemaId, usuarioId;
	
	public void gravar(Usuario u){
		Dao<Editorial> dao = new Dao<Editorial>(Editorial.class);
		Problema p = new Dao<Problema>(Problema.class).listaPorId(problemaId);
		
		this.editorial.setProblema(p);
		this.editorial.setUsuario(u);
		
		if(this.editorial.getId() == null) dao.adiciona(editorial);
		else dao.atualiza(this.editorial);
		
		editorial = new Editorial();
		problemaId = usuarioId = null;
	}

	public Editorial getEditorial() {
		return editorial;
	}
	
	public List<Editorial> getTodosEditorial(){
		return new Dao<Editorial>(Editorial.class).listaTodos();
	}
	
	public List<Usuario> getTodosUsuarios(){
		return new Dao<Usuario>(Usuario.class).listaTodos();
	}
	
	public List<Problema> getTodosProblemas(){
		return new Dao<Problema>(Problema.class).listaTodos();
	}
		
	public Integer getProblemaId() {
		return problemaId;
	}

	public void setProblemaId(Integer problemaId) {
		this.problemaId = problemaId;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public void remover(Editorial e){
		new Dao<Editorial>(Editorial.class).remove(e.getId());
	}
	
	public void carregar(Editorial e){
		this.editorial = e;
		this.problemaId = editorial.getProblema().getId();
		this.usuarioId = editorial.getUsuario().getId();
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}