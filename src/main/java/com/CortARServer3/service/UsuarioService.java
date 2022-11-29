package com.CortARServer3.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.CortARServer3.entity.Usuario;

public interface UsuarioService {
	public Iterable<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Optional<Usuario> findById(String mail);

	public Usuario save(Usuario usuario);
	
	public void deleteById(String mail);
	
	public void deleteByUsuario(Usuario usuario);
}
