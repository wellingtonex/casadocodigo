package br.com.casadocodigo.loja.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import br.com.casadocodigo.loja.daos.AutorDAO;
import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.infra.FileServerSave;
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

	@Inject
	private FacesContext context;
	
	private Part capaLivro;
	
	private List<Autor> autores;
	
	
	@PostConstruct
	public void init() {
		this.autores = autorDao.listar();		
	}

	public String salvar() {
		
		String relativePath = FileServerSave.fileSave(capaLivro, "livros");
		 
		livro.setCapaPath(relativePath);
		
		livroDao.salvar(livro);
		livro = new Livro();
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
		return autores;
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}
}
