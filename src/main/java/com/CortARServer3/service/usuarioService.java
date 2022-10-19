package com.CortARServer3.service;

import com.CortARServer3.entity.LoginDTO;
import com.CortARServer3.entity.UsuariosEntity;

public interface usuarioService {
	UsuariosEntity login(LoginDTO login);
}
