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
}
