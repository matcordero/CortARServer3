package com.CortARServer3.view;

import java.time.LocalDateTime;


public class ComentarioView {
	private Integer idComentario;

	private String usuario;

	private String texto;

	private String foto;

	private LocalDateTime fecha;

	private Integer like;

	public ComentarioView(Integer idComentario, String usuario, String texto, String foto, LocalDateTime fecha,
			Integer like) {
		super();
		this.idComentario = idComentario;
		this.usuario = usuario;
		this.texto = texto;
		this.foto = foto;
		this.fecha = fecha;
		this.like = like;
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}
	
	
}
