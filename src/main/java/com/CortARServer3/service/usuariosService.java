package com.CortARServer3.service;

import java.util.List;
import java.util.Optional;

import com.CortARServer3.entity.LoginDTO;
import com.CortARServer3.entity.UsuariosEntity;

public interface usuariosService {
	Optional<UsuariosEntity> findById(String correo);
	
	UsuariosEntity login(LoginDTO login);
	
	List<UsuariosEntity> findAll();
	
	UsuariosEntity save(UsuariosEntity usuariosEntity);
}
