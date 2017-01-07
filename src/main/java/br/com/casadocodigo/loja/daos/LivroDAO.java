package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.Livro;

public class LivroDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void salvar(Livro livro) {
		em.merge(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distinct(l) from Livro l join fetch l.autores";
		return em.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from Livro l order by l.id desc";
		return em.createQuery(jpql, Livro.class).setMaxResults(5)
				.getResultList();
	}
	
	public List<Livro> demaisLivros() {
        String jpql = "select l from Livro l order by l.id desc";
        return em.createQuery(jpql, Livro.class)
                .setFirstResult(0)
        		.setMaxResults(5)
                .getResultList();
    }
	
	public Livro buscarPorId(Long id) {
	    String jpql = "select l from Livro l join fetch l.autores "
	            + "where l.id = :id";
	    return em.createQuery(jpql, Livro.class)
	            .setParameter("id", id)
	            .getSingleResult();
	}
}
