package com.CortARServer3.view;


import java.time.LocalDateTime;
import java.util.List;



public class PublicacionView {
	private Integer idPublicacion;
	private UsuarioView usuario;
	private String texto;
	private String foto;
	private LocalDateTime fecha;
	private Integer like;
	private List<ComentarioView> comentarios;
	
	public PublicacionView(Integer idPublicacion, UsuarioView usuario, String texto, String foto, LocalDateTime fecha,
			Integer like, List<ComentarioView> comentarios) {
		super();
		this.idPublicacion = idPublicacion;
		this.usuario = usuario;
		this.texto = texto;
		this.foto = foto;
		this.fecha = fecha;
		this.like = like;
		this.comentarios = comentarios;
	}

	public Integer getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public UsuarioView getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioView usuario) {
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

	public List<ComentarioView> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioView> comentarios) {
		this.comentarios = comentarios;
	}
	
	
}
