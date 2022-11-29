package com.CortARServer3.view;

import java.util.List;

public class UsuarioPublicacionesView {
	private String mail;

	private String nombre;
	
	private List<PublicacionView> publicaciones;

	public UsuarioPublicacionesView(String mail, String nombre, List<PublicacionView> publicaciones) {
		super();
		this.mail = mail;
		this.nombre = nombre;
		this.publicaciones = publicaciones;
	}

	public String getMail() {
		return mail;
	}

	public void setCorreo(String correo) {
		this.mail = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNick(String nick) {
		this.nombre = nick;
	}

	public List<PublicacionView> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<PublicacionView> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	

}
