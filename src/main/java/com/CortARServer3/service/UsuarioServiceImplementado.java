package com.CortARServer3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.CortARServer3.entity.Usuario;
import com.CortARServer3.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplementado implements UsuarioService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Optional<Usuario> findById(String mail) {
		return usuarioRepository.findById(mail);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void deleteById(String mail) {
		usuarioRepository.deleteById(mail);
	}

	@Override
	public void deleteByUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
}
