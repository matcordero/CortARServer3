package com.CortARServer3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.CortARServer3.entity.Comentario;
import com.CortARServer3.entity.UsuariosEntity;
import com.CortARServer3.repository.ComentarioRepository;

@Service
public class ComentarioServiceImplementado implements ComentarioService{

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Override
	public Iterable<Comentario> findAll() {
		return comentarioRepository.findAll();
	}

	@Override
	public Page<Comentario> findAll(Pageable pageable) {
		return comentarioRepository.findAll(pageable);
	}

	@Override
	public Optional<Comentario> findById(Integer codigo) {
		return comentarioRepository.findById(codigo);
	}

	@Override
	public Comentario save(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}

	@Override
	public void deleteById(Integer id) {
		comentarioRepository.deleteById(id);
	}

	@Override
	public void deleteByComentario(Comentario comentario) {
		comentarioRepository.delete(comentario);
		
	}


}
