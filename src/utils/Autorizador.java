package utils;

import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import dao.Dao;
import dao.EquipeDao;
import model.Equipe;
import model.Evento;
import model.Universidade;
import model.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// Obtém contexto da aplicação
		FacesContext context = event.getFacesContext();
		// Obtém o nome da página que está sendo chamada
		String nomePagina = context.getViewRoot().getViewId();

		// System.out.println(nomePagina);

		// se for a página de login, o usuário pode acessar
		// if ("/login.xhtml".equals(nomePagina)) {
		// return;
		// }

		if(nomePagina.equals("/ranking.xhtml")){
			List<Usuario> usus = new Dao<Usuario>(Usuario.class).listaTodos();
			
			for(Usuario usu : usus){
				if(!usu.isNivel_acesso()){
					int pontos = 0;
					
					Usuario u = new EquipeDao().listaEquipesUsuario(usu.getId());
				
					for(Equipe e : u.getEquipes()){
						pontos += e.getPontuacao();
					}
					
					u.setPontuacao(pontos);
					
					new Dao<Usuario>(Usuario.class).atualiza(u);
				}
			}
			
			List<Universidade> universidades = new Dao<Universidade>(Universidade.class).listaTodos();
			
			for(Universidade universidade : universidades){
				List<Equipe> equipes = new EquipeDao().listaEquipesUniversidade(universidade);
				
				int pontos = 0;
				
				for(Equipe equipe : equipes){
					pontos += equipe.getPontuacao();
				}
				
				universidade.setPontuacao(pontos);
				
				new Dao<Universidade>(Universidade.class).atualiza(universidade);; 
			}
		}
		
		// Obtém usuário da sessão
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		Evento evento = (Evento) context.getExternalContext().getSessionMap().get("evento");

		// se há usuário logado, ele pode acessar as páginas
		if (usuarioLogado != null) {
			
			if (nomePagina.equals("/login.xhtml")){
				NavigationHandler handler = context.getApplication().getNavigationHandler();
				handler.handleNavigation(context, null, "/index?faces-redirect=true");
				context.renderResponse();

				return;
			}
			
			if (evento == null && nomePagina.equals("/inscricao.xhtml")){
				NavigationHandler handler = context.getApplication().getNavigationHandler();
				handler.handleNavigation(context, null, "/index?faces-redirect=true");
				context.renderResponse();

				return;
			}
			
			if (usuarioLogado.isNivel_acesso() == false && 
					(nomePagina.equals("/assunto.xhtml")      || nomePagina.equals("/colocacao.xhtml") ||
					 nomePagina.equals("/editorial.xhtml")    || nomePagina.equals("/equipe.xhtml") ||
					 nomePagina.equals("/evento.xhtml")       || nomePagina.equals("/feedbacks.xhtml") ||
					 nomePagina.equals("/problema.xhtml")     || nomePagina.equals("/prova.xhtml") ||
					 nomePagina.equals("/universidade.xhtml") || nomePagina.equals("/usuario.xhtml") || 
					 nomePagina.equals("/cadastro.xhtml") || nomePagina.equals("/mensagens.xhtml"))) {
				
				NavigationHandler handler = context.getApplication().getNavigationHandler();
				handler.handleNavigation(context, null, "/index?faces-redirect=true");
				context.renderResponse();

				return;
			}
			
			if (usuarioLogado.isNivel_acesso() && 
					(nomePagina.equals("/inscricao.xhtml") || nomePagina.equals("/cadastro.xhtml"))) {
				
				NavigationHandler handler = context.getApplication().getNavigationHandler();
				handler.handleNavigation(context, null, "/index?faces-redirect=true");
				context.renderResponse();

				return;
			}

		}

		if (usuarioLogado == null && 
				(!nomePagina.equals("/index.xhtml") && !nomePagina.equals("/maratonas.xhtml") &&
				 !nomePagina.equals("/ranking.xhtml") && !nomePagina.equals("/cadastro.xhtml") && 
				 !nomePagina.equals("/login.xhtml"))) {
				
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "/login?faces-redirect=true");
			context.renderResponse();

			return;

		}

		// // se não há, o usuário é redirecionado para o login
		// NavigationHandler handler =
		// context.getApplication().getNavigationHandler();
		// handler.handleNavigation(context, null,
		// "/login?faces-redirect=true");
		// context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; // o autorizador será executado na fase
										// restore_view
	}

}
