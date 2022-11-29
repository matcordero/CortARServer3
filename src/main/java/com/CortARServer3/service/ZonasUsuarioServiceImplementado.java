package com.CortARServer3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.CortARServer3.entity.Usuario;
import com.CortARServer3.entity.ZonasUsuario;
import com.CortARServer3.repository.ZonasUsuarioRepository;

@Service
public class ZonasUsuarioServiceImplementado implements ZonasUsuarioService {

	@Autowired
	ZonasUsuarioRepository zonasUsuarioRepository;
	
	@Override
	public Iterable<ZonasUsuario> findAll() {
		return zonasUsuarioRepository.findAll();
	}

	@Override
	public Page<ZonasUsuario> findAll(Pageable pageable) {
		return zonasUsuarioRepository.findAll(pageable);
	}

	@Override
	public ZonasUsuario save(ZonasUsuario zonasUsuario) {
		return zonasUsuarioRepository.save(zonasUsuario);
	}

	@Override
	public void deleteByZonaUsuario(ZonasUsuario zonaUsuario) {
		zonasUsuarioRepository.delete(zonaUsuario);
	}

	@Override
	public List<ZonasUsuario> findByUsuario(Usuario usuario) {
		return zonasUsuarioRepository.findByUsuario(usuario);
	}

}
