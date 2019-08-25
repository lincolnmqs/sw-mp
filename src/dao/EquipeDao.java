package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controller.LoginController;
import model.Equipe;
import model.Universidade;
import model.Usuario;

public class EquipeDao {
	
	public Equipe listaPorId(Equipe e) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT e FROM Equipe e LEFT JOIN FETCH e.participantes WHERE e.id = :pId";
		
		TypedQuery<Equipe> query = em.createQuery(jpql, Equipe.class);
		query.setParameter("pId", e.getId());
		
		e = query.getSingleResult();
		
		em.close();
		
		return e;
	}
	
	public List<Usuario> todosUsuarios() {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT u FROM Usuario u WHERE u.nivel_acesso = :pAcesso AND u.universidade = :pUniversidade";
		
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pAcesso", false);
		query.setParameter("pUniversidade", new LoginController().getUsu().getUniversidade());
		
		List<Usuario> usuarios = query.getResultList();
		
		em.close();
		
		return usuarios;
	}
	
	public Usuario listaEquipesUsuario(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT u FROM Usuario u " + "LEFT JOIN FETCH u.equipes WHERE u.id = :pId";
		
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pId", id);
		
		Usuario usuario = query.getSingleResult();
		
		em.close();
		
		return usuario;
	}
	
	public List<Equipe> listaEquipesUniversidade(Universidade u) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT e FROM Equipe e WHERE e.universidade = :pUniversidade";
		
		TypedQuery<Equipe> query = em.createQuery(jpql, Equipe.class);
		query.setParameter("pUniversidade", u);
		
		List<Equipe> e = query.getResultList();
		
		em.close();
		
		return e;
	}

}
