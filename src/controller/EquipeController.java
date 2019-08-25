package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.Dao;
import dao.EquipeDao;
import model.Equipe;
import model.Evento;
import model.Usuario;
import model.Colocacao;

@ManagedBean
@ViewScoped
public class EquipeController {
	private Equipe equipe = new Equipe();
	private Integer eventoId, colocacaoId;
	private Integer participanteId1 = new LoginController().getUsu().getId(), participanteId2, participanteId3;
	private boolean controlador = true;

	public void limpar(){
		
		if(!this.controlador) this.controlador = !this.controlador;
		
		equipe = new Equipe();
		eventoId = colocacaoId = participanteId1 = participanteId2 = participanteId3 = null;
	}
	
	public String gravar() throws IOException {
		
		Dao<Usuario> dao = new Dao<Usuario>(Usuario.class);
		
		List<Usuario> lista = new LinkedList<Usuario>();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		if(participanteId1 != null) lista.add(dao.listaPorId(participanteId1));
		if(participanteId2 != null) lista.add(dao.listaPorId(participanteId2));
		if(participanteId3 != null) lista.add(dao.listaPorId(participanteId3));
		
		if(participanteId1 == participanteId2 || participanteId1 == participanteId3 || participanteId2 == participanteId3){
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			facesContext.addMessage(null, new FacesMessage("Usuários Repetidos!"));
			return "inscricao?faces-redirect=true";
		}
		
		equipe.setParticipantes(lista);
		
		Usuario u = (Usuario) facesContext.getExternalContext().getSessionMap().get("usuarioLogado");
		
		equipe.setUniversidade(u.getUniversidade());
		
		if(facesContext.getExternalContext().getSessionMap().get("evento") != null) equipe.setEvento((Evento) facesContext.getExternalContext().getSessionMap().get("evento"));
		else equipe.setEvento(new Dao<Evento>(Evento.class).listaPorId(this.eventoId));
		
		if (equipe.getId() == null) {
			equipe.setColocacao(null);
			equipe.setPontuacao(0);
			
			new Dao<Equipe>(Equipe.class).adiciona(equipe);
			
		} else {
			this.controlador = false;
			this.equipe.setColocacao(new Dao<Colocacao>(Colocacao.class).listaPorId(this.colocacaoId));
			
			new Dao<Equipe>(Equipe.class).atualiza(equipe);
		}
		
		facesContext.getExternalContext().getSessionMap().put("evento", null);

		equipe = new Equipe();
		
		colocacaoId = eventoId = participanteId1 = participanteId2 = participanteId3 = null;
		
		return "index?faces-redirect=true";
	}
	
	public List<Equipe> getTodasEquipesUsuario(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(u.getId() == 0) return new LinkedList<Equipe>(); 
		
		Usuario usuario = new EquipeDao().listaEquipesUsuario(u.getId());
		
		if(usuario == null) return new LinkedList<Equipe>(); 
		else return usuario.getEquipes();	
		
		/*return new Dao<Equipe>(Equipe.class).listaTodos();*/
	}

	public List<Equipe> getTodasEquipes() {
		return new Dao<Equipe>(Equipe.class).listaTodos();
	}

	public List<Usuario> getTodosUsuarios() {
		return new EquipeDao().todosUsuarios();
	}

	public void carregar(Equipe e) {
		
		if(this.controlador) this.controlador = !this.controlador;
		
		equipe = new EquipeDao().listaPorId(e);
		
		int tam = equipe.getParticipantes().size();
		
		participanteId1 = (tam >= 1) ? equipe.getParticipantes().get(0).getId() : 0;
		participanteId2 = (tam >= 2) ? equipe.getParticipantes().get(1).getId() : 0;
		participanteId3 = (tam == 3) ? equipe.getParticipantes().get(2).getId() : 0;
		
		this.eventoId = equipe.getEvento().getId();
		this.colocacaoId = (equipe.getColocacao() == null)? 0 : equipe.getColocacao().getId();
		
	}	
	
	public void usar(Equipe e) {
		
		if(this.controlador) this.controlador = !this.controlador;
		
		equipe = new EquipeDao().listaPorId(e);
		
		int tam = equipe.getParticipantes().size();
		
		participanteId1 = (tam >= 1) ? equipe.getParticipantes().get(0).getId() : 0;
		participanteId2 = (tam >= 2) ? equipe.getParticipantes().get(1).getId() : 0;
		participanteId3 = (tam == 3) ? equipe.getParticipantes().get(2).getId() : 0;
		
		this.eventoId = equipe.getEvento().getId();
		
		this.equipe.setDataCriacao(Calendar.getInstance());
		this.equipe.setId(null);
		
	}	

	public void remover(Equipe e) {
		new Dao<Equipe>(Equipe.class).remove(e.getId());
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Integer getEventoId() {
		return eventoId;
	}

	public void setEventoId(Integer eventoId) {
		this.eventoId = eventoId;
	}

	public Integer getColocacaoId() {
		return colocacaoId;
	}

	public void setColocacaoId(Integer colocacaoId) {
		this.colocacaoId = colocacaoId;
	}

	public Integer getParticipanteId1() {
		return participanteId1;
	}

	public void setParticipanteId1(Integer participanteId1) {
		this.participanteId1 = participanteId1;
	}

	public Integer getParticipanteId2() {
		return participanteId2;
	}

	public void setParticipanteId2(Integer participanteId2) {
		this.participanteId2 = participanteId2;
	}

	public Integer getParticipanteId3() {
		return participanteId3;
	}

	public void setParticipanteId3(Integer participanteId3) {
		this.participanteId3 = participanteId3;
	}

	public boolean isControlador() {
		return controlador;
	}

	public void setControlador(boolean controlador) {
		this.controlador = controlador;
	}
	
}
