package com.CortARServer3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CortARServer3.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
