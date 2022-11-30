package com.CortARServer3.view;


import java.time.LocalDateTime;
import java.util.List;



public class PublicacionView {
	private Integer idPublicacion;
	private String nombre;
	private String texto;
	private String foto;
	private LocalDateTime fecha;
	private Integer like;
	private String zona;
	private List<ComentarioView> comentarios;
	
	public PublicacionView(Integer idPublicacion, String nombre, String texto, String foto, LocalDateTime fecha,
			Integer like,String zona, List<ComentarioView> comentarios) {
		super();
		this.idPublicacion = idPublicacion;
		this.nombre = nombre;
		this.texto = texto;
		this.foto = foto;
		this.fecha = fecha;
		this.like = like;
		this.zona = zona;
		this.comentarios = comentarios;
	}

	public Integer getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}



	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
