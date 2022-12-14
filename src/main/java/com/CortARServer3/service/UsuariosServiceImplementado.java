package com.CortARServer3.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CortARServer3.entity.LoginDTO;
import com.CortARServer3.entity.UsuariosEntity;
import com.CortARServer3.repository.UsuariosRepository;


@Service
public class UsuariosServiceImplementado implements usuariosService{
	
	@Autowired
	private UsuariosRepository usuariosRepository;

	public UsuariosEntity login(LoginDTO login) {
        if (login.getUsername().contains("@")) {
            //UsuariosEntity usuario = usuariosRepository.getByMail(login.getUsername());
            Optional<UsuariosEntity> oUsuarioEntity = usuariosRepository.findById(login.getUsername());

            if (oUsuarioEntity.isPresent() && oUsuarioEntity.get().getContraseña().equals(login.getPassword())) {
                return oUsuarioEntity.get();
            } else {
                return null;
            }
        } else {
            UsuariosEntity usuario = usuariosRepository.findByNick(login.getUsername()).get(0);
            if (!Objects.isNull(usuario) && usuario.getContraseña().equals(login.getPassword())) {
                return usuario;
            } else {
                return null;
            }
        }
    }

	@Override
	public List<UsuariosEntity> findAll() {
		return usuariosRepository.findAll();
	}

	@Override
	public Optional<UsuariosEntity> findById(String correo) {
		return usuariosRepository.findById(correo);
	}

	@Override
	public UsuariosEntity save(UsuariosEntity usuariosEntity) {
		return usuariosRepository.save(usuariosEntity);
	}

}
