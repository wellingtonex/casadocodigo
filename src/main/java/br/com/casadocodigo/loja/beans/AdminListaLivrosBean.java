package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class AdminListaLivrosBean {
	
	@Inject
	private LivroDAO livroDAO;

	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		this.livros = livroDAO.listar();
		return this.livros;
	}
}
