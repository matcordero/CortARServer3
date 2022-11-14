package com.CortARServer3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CortARServer3.entity.Comentario;
import com.CortARServer3.entity.LoginDTO;
import com.CortARServer3.entity.Publicacion;
import com.CortARServer3.entity.UsuariosEntity;
import com.CortARServer3.service.ComentarioService;
import com.CortARServer3.service.PublicacionService;
import com.CortARServer3.service.UsuarioServiceImplementado;
import com.CortARServer3.service.usuarioService;

@RestController
@RequestMapping("/Seminario/Controller")
public class ControllerRest {
	
	@Autowired
	private usuarioService usuarios;
	@Autowired
	private PublicacionService publicacionService;
	@Autowired
	private ComentarioService comentarioService;

	
	//Usuarios-----------------------------------------------------
	@GetMapping(value = "/login")
    public ResponseEntity<?> Login(@RequestParam("username") String username,@RequestParam("password") String password){
		UsuariosEntity usuario = this.usuarios.login(new LoginDTO(username,password));

        if(usuario != null){
        	return ResponseEntity.status(HttpStatus.OK).body(usuario.toView());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping(value = "/GetUsuario")
	public ResponseEntity<?> getUsuario(@RequestParam("correo") String correo){
		Optional<UsuariosEntity> oUsuario = usuarios.findById(correo);
		if (oUsuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(oUsuario.get().toViewPublicacion());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/GetAllUsuarios")
	public ResponseEntity<?> getAllUsuarios(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarios.findAll());
	}
	
	@PostMapping(value = "/PostCrearUsuario")
	public ResponseEntity<?> postCrearUsuario(@RequestParam("correo") String correo,@RequestParam("contrasena") String contrasena,@RequestParam("nick") String nick){
		Optional<UsuariosEntity> oUsuario = usuarios.findById(correo);
		if (oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarios.save(new UsuariosEntity(correo,contrasena,nick)));
	}
	
	//Publicaciones-----------------------------------------------------
	
	@PostMapping(value = "/PostPublicacion")
	public ResponseEntity<?> postPublicaion(@RequestParam("texto") String texto,@RequestParam("correo") String correo){
		Optional<UsuariosEntity> oUsuario = usuarios.findById(correo);
		if (oUsuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(new Publicacion(oUsuario.get(),texto)));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping(value ="/GetAllPublicaciones")
	public ResponseEntity<?> getALLPublicaciones(){
		return ResponseEntity.status(HttpStatus.OK).body(publicacionService.findAll());
	}
	
	@GetMapping(value = "/GetPublicacionesByUsuario/{correo}")
	public ResponseEntity<?> getPublicacionesByUsuario(@PathVariable String correo){
		Optional<UsuariosEntity> oUsuario = usuarios.findById(correo);
		if (oUsuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(oUsuario.get().toViewPublicacion());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/DeletePublicacion/{id}")
	public ResponseEntity<?> deletePublicacion(@PathVariable Integer id){
		Optional<Publicacion> oPublicacion = publicacionService.findById(id);
		if(oPublicacion.isPresent()) {
			Publicacion publicacion=oPublicacion.get();
			publicacion.getUsuario().eliminarPublicacion(publicacion);
			publicacionService.deleteByPublicacion(publicacion);
			return ResponseEntity.status(HttpStatus.OK).body("Publicacion Eliminado");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publicacion No Existia");
	}
	
	@PutMapping(value = "/ActualizarLikes")
	public ResponseEntity<?> actualizarLikes(@RequestParam("id") Integer id,@RequestParam("likes") Integer likes){
		Optional<Publicacion> oPublicacion = publicacionService.findById(id);
		if(oPublicacion.isPresent()) {
			Publicacion publicacion=oPublicacion.get();
			publicacion.modificarLike(likes);
			return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publicacion No Existia");
	}
	
	//Comentarios-----------------------------------------------------
	
	@PostMapping(value = "/PostComentario")
	public ResponseEntity<?> postComentario(@RequestParam("texto") String texto,@RequestParam("correo") String correo,@RequestParam("idPublicacion") Integer idPublicacion){
		Optional<UsuariosEntity> oUsuario = usuarios.findById(correo);
		Optional<Publicacion> oPublicacion = publicacionService.findById(idPublicacion);
		if (oUsuario.isPresent() && oPublicacion.isPresent()) {
			UsuariosEntity usuario = oUsuario.get();
			Publicacion publicacion = oPublicacion.get();
			Comentario comentario = new Comentario(publicacion,usuario,texto,"");
			publicacion.addComentario(comentario);
			return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/DeleteComentario/{id}")
	public ResponseEntity<?> deleteComentario(@PathVariable Integer id){
		Optional<Comentario> oComentario = comentarioService.findById(id);
		if(oComentario.isPresent()) {
			Publicacion publicacion=oComentario.get().getPublicacion();
			oComentario.get().eliminar();
			publicacionService.save(publicacion);
			//comentarioService.deleteById(id);
			comentarioService.deleteByComentario(oComentario.get());
			return ResponseEntity.status(HttpStatus.OK).body("Comentario Eliminado");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comentario No Existia");
	}
}
