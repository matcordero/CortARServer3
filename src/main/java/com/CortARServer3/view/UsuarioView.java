package com.CortARServer3.view;

public class UsuarioView {
	private String correo;

	private String nick;

	public UsuarioView(String correo, String nick) {
		super();
		this.correo = correo;
		this.nick = nick;
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
	
	
}
