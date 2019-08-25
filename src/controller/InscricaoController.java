package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.EquipeDao;
import dao.EventoDao;
import model.Equipe;
import model.Evento;
import model.Usuario;

@ManagedBean
@ViewScoped
public class InscricaoController {
	
	private Evento evento = new Evento();

	public String inscreverNoEvento(Evento e) throws IOException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		facesContext.getExternalContext().getSessionMap().put("evento", e);
		
		Usuario usuLogado = (Usuario) facesContext.getExternalContext().getSessionMap().get("usuarioLogado");
		if(usuLogado == null) return "login?faces-redirect=true";
		
		Usuario usuario = new EquipeDao().listaEquipesUsuario(usuLogado.getId());
		
		for(Equipe equi : usuario.getEquipes()){
			if(equi.getEvento().getNome().equals(e.getNome())){
				facesContext.getExternalContext().getFlash().setKeepMessages(true);
				facesContext.addMessage(null, new FacesMessage("Usuário já inscrito no evento!"));
				return "maratonas?faces-redirect=true";
			}
		}
		
		if(facesContext.getExternalContext().getSessionMap().get("usuarioLogado") == null) 
			return "login?faces-redirect=true";
		else return "inscricao?faces-redirect=true";
	}
	
	public void evento(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		this.evento = new EventoDao().listaPorId((Evento) facesContext.getExternalContext().getSessionMap().get("evento"));
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
