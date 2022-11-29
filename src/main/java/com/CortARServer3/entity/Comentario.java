package com.CortARServer3.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.CortARServer3.view.ComentarioView;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comentario", schema = "uadeseminario", catalog = "")
public class Comentario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idComentario")
	private Integer idComentario;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "idPublicacion")
	@Cascade(CascadeType.ALL)
	private Publicacion publicacion;
	
	@ManyToOne
	@JoinColumn(name = "mail")
	private Usuario usuario;
	
	@Column(name = "texto")
	private String texto;
	
	@Column(name = "foto")
	private String foto;
	
	@Column(name = "fecha")
	private LocalDateTime fecha;
	
	@Column(name = "`like`")
	private Integer like;

	public Comentario() {
		
	}
	
	public Comentario(Publicacion publicacion, Usuario usuario, String texto, String foto) {
		super();
		this.publicacion = publicacion;
		this.usuario = usuario;
		this.texto = texto;
		this.foto = foto;
		this.fecha = LocalDateTime.now();
		this.like = 0;
	}
	
	public void eliminar() {
		this.publicacion.eliminarComentario(this);
		this.publicacion= null;
	}
	
	public void modificarLike(Integer like) {
		this.like=this.like+like;
	}

	
	public ComentarioView toView() {
		return new ComentarioView(idComentario,usuario.getNombre(),texto,foto,fecha,like);
	}
	
	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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
