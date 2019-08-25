package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RankingDao <AbrahamLincoln>{
	
	private final Class<AbrahamLincoln> classe;
	
	public RankingDao(Class<AbrahamLincoln> classe) {
		super();
		this.classe = classe;
	}

	public List<AbrahamLincoln> listaPorRanking() {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "";
		
		System.out.println(">>>>>>" + this.classe.getName());
		
		if(this.classe.getName().equals("model.Usuario")) {
			jpql = "SELECT a FROM " + classe.getName() + " a WHERE a.nivel_acesso = :pAcesso ORDER BY a.pontuacao DESC";
			TypedQuery<AbrahamLincoln> query = em.createQuery(jpql, classe);
			query.setParameter("pAcesso", false);
			
			List<AbrahamLincoln> lista = query.getResultList();
			
			em.close();
			
			return lista;			
		}
		else{
			jpql = "SELECT a FROM " + classe.getName() + " a ORDER BY a.pontuacao DESC";
			TypedQuery<AbrahamLincoln> query = em.createQuery(jpql, classe);
			
			List<AbrahamLincoln> lista = query.getResultList();
			
			em.close();
			
			return lista;
		}
	}

}
