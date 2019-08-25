package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Usuario;
import utils.Utils;

public class UsuarioDao {
	
	public Usuario buscarPorCpfESenha(String cpf, String senha) {
		Usuario usuario;
		
		String jpql = "SELECT DISTINCT u FROM Usuario u  WHERE u.cpf = :pCpf AND u.senha = :pSenha";
				
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pCpf", cpf);
		query.setParameter("pSenha", Utils.toMD5(senha));
		
		try {
			usuario = query.getSingleResult();
	    } catch (NoResultException ex) {
	        usuario = null;
	    }
		
		em.close();
		
		return usuario;
	}
	
}
