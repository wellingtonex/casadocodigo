package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.casadocodigo.loja.daos.AutorDAO;
import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	@Inject
	private LivroDAO livroDao;

	@Inject
	private AutorDAO autorDao;

	private List<Long> autoresId = new ArrayList<>();
	
	@Inject
	private FacesContext context;

	public String salvar() {

		for (Long autorId : autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}		
		livroDao.salvar(livro);
		livro = new Livro();
		autoresId.clear();
 		context.getExternalContext().getFlash().setKeepMessages(true);
 		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso."));
		return "/livros/lista?faces-redirect=true";
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public List<Long> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Long> autoresId) {
		this.autoresId = autoresId;
	}

}
