package com.CortARServer3.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.CortARServer3.entity.enums.Zonas;

@Entity(name ="ZonasUsuario")
@Table(name = "zonasUsuario", schema = "uadeseminario", catalog = "")
public class ZonasUsuario {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name="mail")
	@Basic
	private Usuario usuario;
	
	@Basic
	@Column(nullable = false, name="zona")
	private String zona;

	public ZonasUsuario(Usuario usuario, String zona) {
		this.usuario = usuario;
		this.zona = zona;
	}

	public ZonasUsuario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
	
	

}
