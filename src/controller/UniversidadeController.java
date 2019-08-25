package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.Dao;
import dao.UniversidadeDao;
import model.Cidade;
import model.Estado;
import model.Universidade;

@ManagedBean
@ViewScoped
public class UniversidadeController {
	
	private Universidade universidade = new Universidade();
	private Integer cidadeId, estadoId;
	
	public void limpar(){
		this.universidade = new Universidade();
		this.cidadeId = null;
	}
	
	public void gravar(){
		Dao<Universidade> dao = new Dao<Universidade>(Universidade.class);
		Cidade c = new Dao<Cidade>(Cidade.class).listaPorId(cidadeId);
		
		this.universidade.setCidade(c);
		
		if(this.universidade.getId() == null) dao.adiciona(universidade);
		else dao.atualiza(this.universidade);
		
		universidade = new Universidade();
		cidadeId = null;
	}

	public Universidade getUniversidade() {
		return universidade;
	}
	
	public List<Universidade> getTodasUniversidades(){
		return new Dao<Universidade>(Universidade.class).listaTodos();
	}
	
	public List<Cidade> getTodasCidades(){
		if(this.estadoId != null) {
			Estado e = new Dao<Estado>(Estado.class).listaPorId(estadoId);
			return new UniversidadeDao().listaCidadesPorEstado(e);
		}
		else return new Dao<Cidade>(Cidade.class).listaTodos();
	}
	
	public List<Estado> getTodasEstados(){
		return new Dao<Estado>(Estado.class).listaTodos();
	}
	
	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	public void remover(Universidade u){
		try{
			new Dao<Universidade>(Universidade.class).remove(u.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("remover",  new FacesMessage("Impossível Remover: Universidade associada a Usuário/Equipe."));
	        return;
		}
	}
	
	public void carregar(Universidade u){
		this.universidade = u;
		this.cidadeId = universidade.getCidade().getId();
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	
}
