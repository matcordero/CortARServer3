package com.CortARServer3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CortARServer3.entity.UsuariosEntity;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, String> {
	List<UsuariosEntity> findByNick(String nick);

}
