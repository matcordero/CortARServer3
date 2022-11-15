package com.CortARServer3.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.CortARServer3.entity.enums.Zonas;

@Entity
@Table(name = "zonasUsuario", schema = "uadeseminario", catalog = "")
public class ZonasUsuario {
	
	@ManyToOne
	@JoinColumn(name="correo")
	private UsuariosEntity usuario;
	
	@Basic
	@Column(nullable = false, name="zona")
	private Zonas zona;

}
