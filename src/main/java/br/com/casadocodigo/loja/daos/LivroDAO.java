package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.Livro;

//quando o bean morre ele mata a instancia do dao tambem
@Stateful
public class LivroDAO {

	//a opção extended só está disponival para @Stateful
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
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
	    return em.find(Livro.class, id);
	}
	
	//traz tudo, não faz proveito do lazzy
//	public Livro buscarPorId(Long id) {
//	    String jpql = "select l from Livro l join fetch l.autores "
//	            + "where l.id = :id";
//	    return em.createQuery(jpql, Livro.class)
//	            .setParameter("id", id)
//	            .getSingleResult();
//	}
}
