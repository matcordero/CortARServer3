package com.CortARServer3.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.CortARServer3.view.ComentarioView;
import com.CortARServer3.view.PublicacionView;
import com.CortARServer3.view.UsuarioPublicacionesView;
import com.CortARServer3.view.UsuarioView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cuentas", schema = "uadeseminario", catalog = "")
public class UsuariosEntity {

    @Id
    @Column(name = "correo")
	private String correo;

	@Basic
	@Column(nullable = false)
	private String nick;
	
	@JsonIgnore
	@Column(nullable = false, name = "contrasena")
	private String contraseña;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<Publicacion> publicaciones = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<ZonasUsuario> zonas = new ArrayList<>();
	
	public UsuariosEntity(String correo, String contraseña, String nick) {
		super();
		this.correo = correo;
		this.contraseña = contraseña;
		this.nick = nick;
	}

	public void eliminarPublicacion(Publicacion pub) {
		this.publicaciones.remove(pub);
	}
	
	public UsuariosEntity() {
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	/*
	public UsuarioView toView() {
		return new UsuarioView(correo,nick);
	}
	
	public UsuarioPublicacionesView toViewPublicacion() {
		List<PublicacionView> publicacionesView = publicaciones.stream().map(x -> x.toView()).collect(Collectors.toList());
		return new UsuarioPublicacionesView(correo,nick,publicacionesView);
	}*/
}
