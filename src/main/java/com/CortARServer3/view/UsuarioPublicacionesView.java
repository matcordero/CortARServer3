package com.CortARServer3.view;

import java.util.List;

public class UsuarioPublicacionesView {
	private String correo;

	private String nick;
	
	private List<PublicacionView> publicaciones;

	public UsuarioPublicacionesView(String correo, String nick, List<PublicacionView> publicaciones) {
		super();
		this.correo = correo;
		this.nick = nick;
		this.publicaciones = publicaciones;
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

	public List<PublicacionView> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<PublicacionView> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	

}
