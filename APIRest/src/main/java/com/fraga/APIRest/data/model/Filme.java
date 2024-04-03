package com.fraga.APIRest.data.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
public class Filme implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(nullable = false)
	private String titulo;

	private String diretor;

	private String genero;

	private Double mediaVotos;

	private Long contageVotos;

	private String detalhes;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "filme_ator", joinColumns = { @JoinColumn(name = "id_filme") }, inverseJoinColumns = {
			@JoinColumn(name = "id_ator") })
	private List<Ator> atores;

	public Filme() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Double getMediaVotos() {
		return mediaVotos;
	}

	public void setMediaVotos(Double mediaVotos) {
		this.mediaVotos = mediaVotos;
	}

	public Long getContageVotos() {
		return contageVotos;
	}

	public void setContageVotos(Long contageVotos) {
		this.contageVotos = contageVotos;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}
}
