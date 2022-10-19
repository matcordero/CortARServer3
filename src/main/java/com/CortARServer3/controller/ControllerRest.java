package com.CortARServer3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CortARServer3.entity.LoginDTO;
import com.CortARServer3.entity.UsuariosEntity;
import com.CortARServer3.service.UsuarioServiceImplementado;
import com.CortARServer3.service.usuarioService;

@RestController
@RequestMapping("/Seminario/Controller")
public class ControllerRest {
	
	@Autowired
	private usuarioService usuarios;

	@GetMapping(value = "/login")
    public ResponseEntity<UsuariosEntity> Login(@RequestParam("username") String username,@RequestParam("password") String password){
		UsuariosEntity usuario = this.usuarios.login(new LoginDTO(username,password));

        if(usuario != null){
            return new ResponseEntity<UsuariosEntity>(usuario, HttpStatus.OK);
        } else {
            System.out.println("Paso por aca");
            return ResponseEntity.notFound().build();
        }
    }
	@GetMapping(value = "/GetAllUsuarios")
	public ResponseEntity<?> getAllRegistros(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarios.findAll());
	}
}
