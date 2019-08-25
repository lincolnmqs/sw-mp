package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Feedback;
import model.Usuario;

public class FeedbackDao {
	
	public List<Feedback> listaFeedback(Usuario u) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT f FROM Feedback f WHERE f.usuario = :pUsuario";
		
		TypedQuery<Feedback> query = em.createQuery(jpql, Feedback.class);
		query.setParameter("pUsuario", u);
		
		List<Feedback> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
}
