package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class HomeBean {

	@Inject
	private LivroDAO dao = new LivroDAO();

	public List<Livro> ultimosLancamentos() {
		return dao.ultimosLancamentos();
	}
	
	public List<Livro> demaisLivros() {
		return dao.demaisLivros();
	}
}
