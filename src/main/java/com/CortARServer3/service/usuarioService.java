package com.CortARServer3.service;

import java.util.List;

import com.CortARServer3.entity.LoginDTO;
import com.CortARServer3.entity.UsuariosEntity;

public interface usuarioService {
	UsuariosEntity login(LoginDTO login);
	
	List<UsuariosEntity> findAll();
}
