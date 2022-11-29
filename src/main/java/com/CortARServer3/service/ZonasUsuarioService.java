package com.CortARServer3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.CortARServer3.entity.Usuario;
import com.CortARServer3.entity.ZonasUsuario;

public interface ZonasUsuarioService {
	public Iterable<ZonasUsuario> findAll();
	
	public Page<ZonasUsuario> findAll(Pageable pageable);

	public ZonasUsuario save(ZonasUsuario zonasUsuario);
	
	public void deleteByZonaUsuario(ZonasUsuario zonaUsuario);
	
	public List<ZonasUsuario> findByUsuario(Usuario usuario);

}
