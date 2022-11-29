package com.CortARServer3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CortARServer3.entity.Usuario;
import com.CortARServer3.entity.ZonasUsuario;

@Repository
public interface ZonasUsuarioRepository extends JpaRepository<ZonasUsuario, Integer> {
	List<ZonasUsuario> findByUsuario(Usuario usuario);
}
