package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Cidade;
import model.Estado;

public class UniversidadeDao {
	
	public List<Cidade> listaCidadesPorEstado(Estado e) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT c FROM Cidade c WHERE c.estado = :pEstado";
		
		TypedQuery<Cidade> query = em.createQuery(jpql, Cidade.class);
		query.setParameter("pEstado", e);
		
		List<Cidade> cidades = query.getResultList();
		
		em.close();
		
		return cidades;
	}

}
