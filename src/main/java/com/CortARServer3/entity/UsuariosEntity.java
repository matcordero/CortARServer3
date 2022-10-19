package com.CortARServer3.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas", schema = "uadeseminario", catalog = "")
public class UsuariosEntity {

    @Id
    @Column(name = "correo")
	private String correo;

	@Basic
	@Column(nullable = false)
	private String nick;
	
	
	@Column(nullable = false, name = "contrasena")
	private String contraseña;
	
	
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



}
