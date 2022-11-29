package com.CortARServer3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.CortARServer3.entity.Publicacion;
import com.CortARServer3.entity.UsuariosEntity;
import com.CortARServer3.entity.enums.Zonas;
import com.CortARServer3.repository.PublicacionRepository;

@Service
public class PublicacionServiceImplementado implements PublicacionService{

	@Autowired
	private PublicacionRepository publicacionRepository;
	
	@Override
	public Iterable<Publicacion> findAll() {
		return publicacionRepository.findAll();
	}

	@Override
	public Page<Publicacion> findAll(Pageable pageable) {
		return publicacionRepository.findAll(pageable);
	}

	@Override
	public Optional<Publicacion> findById(Integer codigo) {
		return publicacionRepository.findById(codigo);
	}

	@Override
	public Publicacion save(Publicacion publicacion) {
		return publicacionRepository.save(publicacion);
	}

	@Override
	public void deleteById(Integer codigo) {
		publicacionRepository.deleteById(codigo);
	}

	@Override
	public List<Publicacion> findByUsuario(UsuariosEntity usuario) {
		return publicacionRepository.findByUsuario(usuario);
	}

	@Override
	public void deleteByPublicacion(Publicacion publicacion) {
		publicacionRepository.delete(publicacion);
		
	}

	@Override
	public List<Publicacion> findByZona(Zonas zona) {
		return publicacionRepository.findByZona(zona);
	}

	@Override
	public List<Publicacion> findAll2() {
		return publicacionRepository.findAll();
	}

}
