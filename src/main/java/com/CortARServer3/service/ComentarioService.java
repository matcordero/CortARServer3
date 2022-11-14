package com.CortARServer3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.CortARServer3.entity.Comentario;
import com.CortARServer3.entity.UsuariosEntity;

public interface ComentarioService {
	public Iterable<Comentario> findAll();
	
	public Page<Comentario> findAll(Pageable pageable);
	
	public Optional<Comentario> findById(Integer codigo);

	public Comentario save(Comentario publicacion);
	
	public void deleteById(Integer codigo);
	
	public void deleteByComentario(Comentario comentario);
	
}
