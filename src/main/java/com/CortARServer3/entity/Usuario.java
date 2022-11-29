package com.CortARServer3.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.CortARServer3.view.PublicacionView;
import com.CortARServer3.view.UsuarioPublicacionesView;
import com.CortARServer3.view.UsuarioView;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name ="Usuario")
@Table(name = "usuarios")
public class Usuario {
	
	@Id
    @Column(name = "mail")
	private String mail;

	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "fotoPerfil")
	private String fotoPerfil;
	
	@JsonIgnore
	@Column(name = "contrasena", nullable = false)
	private int contraseña;
	
	@Column(name = "keyValidate")
	private String keyValidate;
	
	@Column(name = "tipografia")
	private String tipografia;
	
	@Column(name = "tamanoFuente")
	private float tamanoFuente;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<Publicacion> publicaciones = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<ZonasUsuario> zonas = new ArrayList<>();
	
	public Usuario(String mail, String nombre, int contraseña) {
		super();
		this.mail = mail;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.tipografia = "Calibri";
		this.tamanoFuente = 12;
		this.keyValidate="";
	}
	
	public Usuario() {

	}
	
	public UsuarioPublicacionesView toViewPublicacion() {
		List<PublicacionView> publicacionesView = publicaciones.stream().map(x -> x.toView()).collect(Collectors.toList());
		return new UsuarioPublicacionesView(mail,nombre,publicacionesView);
	}
	
	public void eliminarPublicacion(Publicacion pub) {
		this.publicaciones.remove(pub);
	}
	
	public void addPublicacion (Publicacion publicacion) {
		publicaciones.add(publicacion);
	}
	
	public void deletePublicacion (Publicacion publicacion) {
		publicaciones.remove(publicacion);
	}

	public UsuarioView toView() {
		return new UsuarioView(mail,nombre,keyValidate,tipografia,tamanoFuente);
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public int getContraseña() {
		return contraseña;
	}

	public void setContraseña(int contraseña) {
		this.contraseña = contraseña;
	}

	public String getKey() {
		return keyValidate;
	}

	public void setKey(String key) {
		this.keyValidate = key;
	}

	public String getTipografia() {
		return tipografia;
	}

	public void setTipografia(String tipografia) {
		this.tipografia = tipografia;
	}

	public float getTamanoFuente() {
		return tamanoFuente;
	}

	public void setTamanoFuente(float tamañoFuente) {
		this.tamanoFuente = tamañoFuente;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public List<ZonasUsuario> getZonas() {
		return zonas;
	}

	public void setZonas(List<ZonasUsuario> zonas) {
		this.zonas = zonas;
	}

	
	
	
}
