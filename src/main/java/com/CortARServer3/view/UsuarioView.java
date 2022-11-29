package com.CortARServer3.view;

import javax.persistence.Column;

public class UsuarioView {
	private String mail;

	private String nombre;
	
	private String key;

	private String tipografia;

	private float tamañoFuente;

	public UsuarioView(String mail, String nombre, String key, String tipografia, float tamañoFuente) {
		super();
		this.mail = mail;
		this.nombre = nombre;
		this.key = key;
		this.tipografia = tipografia;
		this.tamañoFuente = tamañoFuente;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTipografia() {
		return tipografia;
	}

	public void setTipografia(String tipografia) {
		this.tipografia = tipografia;
	}

	public float getTamañoFuente() {
		return tamañoFuente;
	}

	public void setTamañoFuente(float tamañoFuente) {
		this.tamañoFuente = tamañoFuente;
	}
	
	
	
}
