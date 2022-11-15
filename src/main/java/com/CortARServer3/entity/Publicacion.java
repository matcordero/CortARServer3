package com.CortARServer3.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.CortARServer3.view.ComentarioView;
import com.CortARServer3.view.PublicacionView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "publicacion", schema = "uadeseminario", catalog = "")
public class Publicacion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idpublicaciones")
	private Integer idPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "correo")
	private UsuariosEntity usuario;
	
	@Column(name = "texto")
	private String texto;
	
	@Column(name = "foto")
	private String foto;
	
	@Column(name = "zona")
	private String zona;
	
	@Column(name = "fecha")
	private LocalDateTime fecha;
	
	@Column(name = "`like`")
	private Integer like;

	
	@OneToMany(mappedBy = "publicacion")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Publicacion() {
		
	}

	public Publicacion(UsuariosEntity usuario, String texto) {
		super();
		this.usuario = usuario;
		this.texto = texto;
		this.fecha = LocalDateTime.now();
		this.foto = "";
		this.like = 0;
	}

	public void eliminarComentario(Comentario comentario) {
		comentarios.remove(comentario);
	}
	
	public void modificarLike(Integer like) {
		this.like=this.like+like;
	}

	public void addComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}
	
	public PublicacionView toView() {
		List<ComentarioView> comentariosView = comentarios.stream().map(x -> x.toView()).collect(Collectors.toList());
		return new PublicacionView(idPublicacion,usuario.toView(),texto,foto,fecha,like,comentariosView);
	}
	
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public UsuariosEntity getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuariosEntity usuario) {
		this.usuario = usuario;
	}


	public Integer getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
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

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
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
