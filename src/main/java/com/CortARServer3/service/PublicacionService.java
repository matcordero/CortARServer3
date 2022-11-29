package com.CortARServer3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.CortARServer3.entity.Publicacion;
import com.CortARServer3.entity.UsuariosEntity;
import com.CortARServer3.entity.enums.Zonas;


public interface PublicacionService {
	public Iterable<Publicacion> findAll();
	
	public List<Publicacion> findAll2();
	
	public Page<Publicacion> findAll(Pageable pageable);
	
	public Optional<Publicacion> findById(Integer codigo);

	public Publicacion save(Publicacion publicacion);
	
	public void deleteById(Integer codigo);
	
	public void deleteByPublicacion(Publicacion publicacion);
	
	public List<Publicacion> findByUsuario(UsuariosEntity usuario);
	
	public List<Publicacion> findByZona(Zonas zona);
}
