package com.CortARServer3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CortARServer3.entity.Publicacion;
import com.CortARServer3.entity.UsuariosEntity;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer>{
	List<Publicacion> findByUsuario(UsuariosEntity usuario);
}
