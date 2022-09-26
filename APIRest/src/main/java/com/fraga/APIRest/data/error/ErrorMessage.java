package com.fraga.APIRest.data.error;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private Integer status;
	private String messagem;
	
	public ErrorMessage(String titulo, Integer status, String messagem) {
		this.titulo = titulo;
		this.status = status;
		this.messagem = messagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessagem() {
		return messagem;
	}
	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}
	
	

}

