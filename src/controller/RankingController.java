package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.RankingDao;
import model.Universidade;
import model.Usuario;


@ManagedBean
@ViewScoped
public class RankingController {
	
	int contador = 1;
	
	public List <Usuario> getRankingUsuario(){
		return new RankingDao<Usuario>(Usuario.class).listaPorRanking();
	}
	
	public List <Universidade> getRankingUniversidade(){
		return new RankingDao<Universidade>(Universidade.class).listaPorRanking();
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

}