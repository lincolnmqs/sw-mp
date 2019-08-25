package controller;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.Dao;
import model.Usuario;
import utils.Utils;
import model.Universidade;

@ManagedBean
@ViewScoped
public class UsuarioController {
	
	private Usuario usuario = new Usuario();
	private Usuario usu = new Usuario();
	
	private Usuario auxusu = new Usuario();
	
	private Integer universidadeId;
	private Boolean controle = true;
	private String confirmarSenha, auxSenha;
	
	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public Boolean getControle() {
		return controle;
	}

	public void setControle(Boolean controle) {
		this.controle = controle;
	}
	
	public void mudaControle(){
		this.controle = true;
	}
	
	public void alterarControle(){
		this.controle = ! this.controle;
	}

	public String getAuxSenha() {
		return auxSenha;
	}

	public void setAuxSenha(String auxSenha) {
		this.auxSenha = auxSenha;
	}

	public void gravar() throws IOException{
		Dao<Usuario> dao = new Dao<Usuario>(Usuario.class);
		Universidade u = new Dao<Universidade>(Universidade.class).listaPorId(universidadeId);
		
		this.usuario.setUniversidade(u);
		
		System.out.println(this.usuario.getSenha() + " " + this.confirmarSenha);
		
		if(this.usuario.getSenha().equals(this.confirmarSenha)){
		
			if(this.usuario.getId() == null){
				this.usuario.setSenha(Utils.toMD5(this.usuario.getSenha()));
				this.usuario.setNivel_acesso(false);
				
				dao.adiciona(usuario);
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				
				if(new LoginController().getUsu() == null) facesContext.getExternalContext().redirect("login.xhtml");
			}
			else{
				if(!auxSenha.equals(this.usuario.getSenha())) this.usuario.setSenha(Utils.toMD5(this.usuario.getSenha()));
				
				FacesContext context = FacesContext.getCurrentInstance();
				
				Usuario usu = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
				
				dao.atualiza(this.usuario);
				
				this.controle = true;
			}
			
			usuario = new Usuario();
			universidadeId = null;
			confirmarSenha = null;
		}
	}
	
	public void limpar(){
		usuario = new Usuario();
		universidadeId = null;
		confirmarSenha = null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<Usuario> getTodosUsuarios(){
		return new Dao<Usuario>(Usuario.class).listaTodos();
	}
	
	public List<Universidade> getTodasUniversidades(){
		return new Dao<Universidade>(Universidade.class).listaTodos();
	}
		
	public Integer getUniversidadeId() {
		return universidadeId;
	}

	public void setUniversidadeId(Integer universidadeId) {
		this.universidadeId = universidadeId;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void remover(Usuario u){
		new Dao<Usuario>(Usuario.class).remove(u.getId());
	}
	
	public void carregar(Usuario u){
		System.out.println(">>>>>>>>>>>>>" + u.getNome());
		
		if(u != null){
			this.usuario = new Dao<Usuario>(Usuario.class).listaPorId(u.getId());
			this.auxSenha = u.getSenha();
			this.confirmarSenha = this.usuario.getSenha();
			this.universidadeId = usuario.getUniversidade().getId();
			this.auxusu = this.usuario;
		}
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	public void altera(){
		this.controle = !this.controle; 
	}

	public Usuario getAuxusu() {
		return auxusu;
	}

	public void setAuxusu(Usuario auxusu) {
		this.auxusu = auxusu;
	}

}
