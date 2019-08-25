package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Evento;

public class EventoDao {
	
	public Evento listaPorId(Evento e) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT e FROM Evento e LEFT JOIN FETCH e.premiacoes WHERE e.id = :pId";
		
		TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);
		query.setParameter("pId", e.getId());
		
		e = query.getSingleResult();
		
		em.close();
		
		return e;
	}
	
	public List<Evento> listaDosEventosAtivos() {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT e FROM Evento e LEFT JOIN FETCH e.premiacoes WHERE e.aberto = :pAberto";
		
		TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);
		query.setParameter("pAberto", true);
		
		List<Evento> evento = query.getResultList();
		
		em.close();
		
		return evento;
	}

}
