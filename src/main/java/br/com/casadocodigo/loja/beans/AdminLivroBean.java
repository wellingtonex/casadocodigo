package br.com.casadocodigo.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivroBean {

	private Livro livro = new Livro();
	
	@Inject
	private LivroDao livroDao;
	
	public void salvar() {
		livroDao.salvar(livro);
		System.out.println("Livro" + livro +  "salvo com Sucesso!");
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
}
