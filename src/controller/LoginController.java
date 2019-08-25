package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDao;
import model.Usuario;

@ManagedBean
@ViewScoped
public class LoginController {
	
	private Usuario usuario = new Usuario();
	
	public void verificaLogado() throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(context.getExternalContext().getSessionMap().get("usuarioLogado") == null){		
			FacesContext facesContext = FacesContext.getCurrentInstance();
			
			facesContext.getExternalContext().redirect("login.xhtml");
		}
	}
	
	public Usuario getUsu(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		return (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
	}
	
	public String logar(){
		
		usuario = new UsuarioDao().buscarPorCpfESenha(usuario.getCpf(), usuario.getSenha());
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(this.usuario != null) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			
			if(context.getExternalContext().getSessionMap().get("evento") != null) return "inscricao?faces-redirect=true";
			else return "index?faces-redirect=true";
		}
		else{			
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Login e/ou senha incorretos"));
			
			return "login?faces-redirect=true";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public String deslogar(){
		FacesContext context = FacesContext.getCurrentInstance();
	//	context.getExternalContext().getSessionMap().remove("usuarioLogado");
	//	return "login?faces-redirect=true";
		
		context.getExternalContext().getSessionMap().clear();
		this.usuario = null;
		return "login?faces-redirect=true";
	}
	
	public void preenche(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
	}
}
