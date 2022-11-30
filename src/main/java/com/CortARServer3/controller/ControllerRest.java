package com.CortARServer3.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.CortARServer3.entity.Comentario;
import com.CortARServer3.entity.LoginDTO;
import com.CortARServer3.entity.Publicacion;
import com.CortARServer3.entity.Usuario;
import com.CortARServer3.entity.UsuariosEntity;
import com.CortARServer3.entity.enums.Zonas;
import com.CortARServer3.service.CloudinaryService;
import com.CortARServer3.service.ComentarioService;
import com.CortARServer3.service.PublicacionService;
import com.CortARServer3.service.UsuarioService;
import com.CortARServer3.service.UsuariosServiceImplementado;
import com.CortARServer3.service.usuariosService;
import com.CortARServer3.view.PublicacionView;

@RestController
@RequestMapping("/Seminario/Controller")
public class ControllerRest {
	
	@Autowired
	private usuariosService usuarios;
	@Autowired
	private PublicacionService publicacionService;
	@Autowired
	private ComentarioService comentarioService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CloudinaryService cloudinaryService;
	
	class datos{
		private Object objeto;
		private int status;
		public datos(Object objeto, int status) {
			super();
			this.objeto = objeto;
			this.status = status;
		}
		public Object getObjeto() {
			return objeto;
		}
		public void setObjeto(Object objeto) {
			this.objeto = objeto;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
		
	}
	
	
	//Cloudinary-------------------------
	
	@PostMapping(value = "/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException{
		Map result = cloudinaryService.upload(multipartFile);
		System.out.println(result.get("url"));
		System.out.println(result.get("public_id"));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(result); 
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException{
		Map result = cloudinaryService.delete(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(result); 
	}	
	
	
	//Usuarios-----------------------------------------------------
	@GetMapping(value = "/Login")
    public ResponseEntity<?> login(@RequestParam("mail") String mail,@RequestParam("contrasena") String contrasena){
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent()) {
			Usuario usuarioActual = oUsuario.get();
			if(usuarioActual.getContraseña() == contrasena.hashCode()) {
				UUID randomUUID = UUID.randomUUID();
				String ramdomStr = randomUUID.toString().replace("-", "");
				usuarioActual.setKey(ramdomStr);
				usuarioService.save(usuarioActual);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new datos(usuarioActual.toView(),202));
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario o Contraseña Incorrecto");
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario o Contraseña Incorrecto");
		}
    }
	
	@PostMapping(value = "/CrearUsuario")
	public ResponseEntity<?> postCrearUsuario(@RequestParam("mail") String mail,@RequestParam("contrasena") String contrasena,@RequestParam("nombre") String nombre){
		if(mail.equals("") || contrasena.equals("") || nombre.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Usuario ya existe");
		}
		System.out.print(contrasena);
		int ContraEncriptada = contrasena.hashCode();
		Usuario usuario = new Usuario(mail,nombre,ContraEncriptada);
		usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario Creado con Exito");
	}
	
	@GetMapping(value = "/GetInformationUsuario")
    public ResponseEntity<?> getInformationUsuario(@RequestParam("mail") String mail,@RequestParam("key") String key){
		if(mail.equals("") || key.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent()) {
			Usuario usuarioActual = oUsuario.get();
			if(usuarioActual.getKey().equals(key)) {
				return ResponseEntity.status(HttpStatus.OK).body(usuarioActual);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
    }
	
	@PutMapping(value = "/PutCambiarTipografia")
    public ResponseEntity<?> getCambiarTipografia(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("tipografia") String tipografia){
		if(mail.equals("") || key.equals("") || tipografia.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent() && oUsuario.get().getKey().equals(key)) {
			Usuario usuarioActual = oUsuario.get();
			usuarioActual.setTipografia(tipografia);
			usuarioService.save(usuarioActual);
			return ResponseEntity.status(HttpStatus.OK).body("Tipografia Cambiada");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
    }
	
	@PutMapping(value = "/PutCambiarTamanoLetra")
    public ResponseEntity<?> getCambiarTamañoLetra(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("tamanoLetra") float tamanoLetra){
		if(mail.equals("") || key.equals("") || tamanoLetra==0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent() && oUsuario.get().getKey().equals(key)) {
			Usuario usuarioActual = oUsuario.get();
			usuarioActual.setTamanoFuente(tamanoLetra);
			usuarioService.save(usuarioActual);
			return ResponseEntity.status(HttpStatus.OK).body("Tamaño Letra Cambiada");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
    }
	
	@PutMapping(value = "/PutCambiarNombre")
    public ResponseEntity<?> getCambiarNombre(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("nombre") String nombre){
		if(mail.equals("") || key.equals("") || nombre.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent() && oUsuario.get().getKey().equals(key)) {
			Usuario usuarioActual = oUsuario.get();
			usuarioActual.setNombre(nombre);
			usuarioService.save(usuarioActual);
			return ResponseEntity.status(HttpStatus.OK).body("Nombre cambiado");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
    }
	
	@PutMapping(value = "/PutCambiarContraseña")
    public ResponseEntity<?> getCambiarTamañoLetra(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("contrasenaVieja") String contrasenaVieja,@RequestParam("contrasenaNueva") String contrasenaNueva){
		if(mail.equals("") || key.equals("") || contrasenaVieja.equals("") || contrasenaNueva.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent() && oUsuario.get().getKey().equals(key)) {
			Usuario usuarioActual = oUsuario.get();
			if(usuarioActual.getContraseña() == contrasenaVieja.hashCode()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contraseña vieja Incorrecta");
			}
			usuarioActual.setContraseña(contrasenaNueva.hashCode());
			usuarioService.save(usuarioActual);
			return ResponseEntity.status(HttpStatus.OK).body("Contraseña Cambiada");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
    }
	
	@PutMapping(value = "/PutCambiarFotoPerfil")
    public ResponseEntity<?> getCambiarFotoPerfil(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam MultipartFile multipartFile) throws IOException{
		if(mail.equals("") || key.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (oUsuario.isPresent() && oUsuario.get().getKey().equals(key)) {
			Usuario usuarioActual = oUsuario.get();
			Map result = cloudinaryService.upload(multipartFile);
			usuarioActual.setFotoPerfil(result.get("url").toString());
			usuarioActual.setIdFoto(result.get("public_id").toString());
			return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioActual));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
    }
	
	
	
	//Publicaciones-----------------------------------------------------
	@GetMapping(value = "/GetZonas")
	public ResponseEntity<?> getZonas(){
		return ResponseEntity.status(HttpStatus.OK).body(Zonas.list());
	}
	
	@GetMapping(value = "/GetPublicacionesByZona/{zona}")
	public ResponseEntity<?> getPublicacionesByZona(@PathVariable String zona){
		if(zona.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		List<PublicacionView> pub = publicacionService.findByZona(zona).stream().map(x -> x.toView()).toList();
		return ResponseEntity.status(HttpStatus.OK).body(pub);
	}
	
	
	@PostMapping(value = "/PostPublicacion")
	public ResponseEntity<?> postPublicaion(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("texto") String texto,@RequestParam("zona") String zona){
		if(mail.equals("") || key.equals("") || texto.equals("") || zona.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		if (!Zonas.list().contains(zona)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zona Invalida");
		}
		Usuario usuario = oUsuario.get();
		Publicacion publicacion = new Publicacion(usuario, texto,zona,"","");
		return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion).toView());
	}
	
	@PostMapping(value = "/PostPublicacionFoto")
	public ResponseEntity<?> postPublicaionFoto(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("texto") String texto,@RequestParam("zona") String zona,@RequestParam MultipartFile multipartFile) throws IOException{
		if(mail.equals("") || key.equals("") || texto.equals("") || zona.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		if (!Zonas.list().contains(zona)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zona Invalida");
		}
		Usuario usuario = oUsuario.get();
		Map result = cloudinaryService.upload(multipartFile);
		Publicacion publicacion = new Publicacion(usuario, texto,zona,result.get("url").toString(),result.get("public_id").toString());
		return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion).toView());
	}
	
	@GetMapping(value ="/GetAllPublicaciones")
	public ResponseEntity<?> getALLPublicaciones(@RequestParam("mail") String mail,@RequestParam("key") String key){
		if(mail.equals("") || key.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		List<Publicacion> publicaciones = publicacionService.findAll2();
		List<PublicacionView> pubViews = publicaciones.stream().map(x -> x.toView()).toList();
		return ResponseEntity.status(HttpStatus.OK).body(pubViews);
	}
	
	@GetMapping(value = "/GetPublicacionesByUsuario/{correo}")
	public ResponseEntity<?> getPublicacionesByUsuario(@PathVariable String correo,@RequestParam("mail") String mail,@RequestParam("key") String key){
		if(mail.equals("") || key.equals("") || correo.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		Optional<Usuario> oUsuarioPublicacion = usuarioService.findById(correo);
		if (oUsuarioPublicacion.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(oUsuarioPublicacion.get().toViewPublicacion());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este Usuario No Existe");
	}
	
	@DeleteMapping(value = "/DeletePublicacion/{id}")
	public ResponseEntity<?> deletePublicacion(@PathVariable Integer id,@RequestParam("mail") String mail,@RequestParam("key") String key){
		if(mail.equals("") || key.equals("") || id<0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
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
	public ResponseEntity<?> actualizarLikes(@RequestParam("id") Integer id,@RequestParam("likes") Integer likes,@RequestParam("mail") String mail,@RequestParam("key") String key){
		if(mail.equals("") || key.equals("") || likes==0 || id<0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		Optional<Publicacion> oPublicacion = publicacionService.findById(id);
		if(oPublicacion.isPresent()) {
			Publicacion publicacion=oPublicacion.get();
			publicacion.modificarLike(likes);
			return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion).toView());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publicacion No Existia");
	}
	
	//Comentarios-----------------------------------------------------
	
	@PostMapping(value = "/PostComentario")
	public ResponseEntity<?> postComentario(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("texto") String texto,@RequestParam("idPublicacion") Integer idPublicacion){
		if(mail.equals("") || key.equals("") || texto.equals("") || idPublicacion<0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		Optional<Publicacion> oPublicacion = publicacionService.findById(idPublicacion);
		if (oPublicacion.isPresent()) {
			Usuario usuario = oUsuario.get();
			Publicacion publicacion = oPublicacion.get();
			Comentario comentario = new Comentario(publicacion,usuario,texto,"");
			publicacion.addComentario(comentario);
			return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion).toView());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publicacion No Existia");
	}
	
	@PostMapping(value = "/PostComentarioFoto")
	public ResponseEntity<?> postComentarioFoto(@RequestParam("mail") String mail,@RequestParam("key") String key,@RequestParam("texto") String texto,@RequestParam("idPublicacion") Integer idPublicacion,@RequestParam MultipartFile multipartFile) throws IOException{
		if(mail.equals("") || key.equals("") || texto.equals("") || idPublicacion<0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		Optional<Publicacion> oPublicacion = publicacionService.findById(idPublicacion);
		if (oPublicacion.isPresent()) {
			Usuario usuario = oUsuario.get();
			Publicacion publicacion = oPublicacion.get();
			Map result = cloudinaryService.upload(multipartFile);
			Comentario comentario = new Comentario(publicacion,usuario,texto,result.get("url").toString(),result.get("public_id").toString());
			publicacion.addComentario(comentario);
			return ResponseEntity.status(HttpStatus.OK).body(publicacionService.save(publicacion).toView());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publicacion No Existia");
	}
	
	@DeleteMapping(value = "/DeleteComentario/{id}")
	public ResponseEntity<?> deleteComentario(@PathVariable Integer id,@RequestParam("mail") String mail,@RequestParam("key") String key){
		if(mail.equals("") || key.equals("") || id<0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos Vacios");
		}
		Optional<Usuario> oUsuario = usuarioService.findById(mail);
		if (!oUsuario.isPresent() || !oUsuario.get().getKey().equals(key)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fallo de Validacion");
		}
		Optional<Comentario> oComentario = comentarioService.findById(id);
		if(oComentario.isPresent()) {
			Comentario comentario = oComentario.get();
			Usuario usuario = oUsuario.get();
			if (!comentario.getUsuario().equals(usuario)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comentario no Perteneciente a Este Usuario");
			}
			Publicacion publicacion=comentario.getPublicacion();
			comentario.eliminar();
			publicacionService.save(publicacion);
			//comentarioService.deleteById(id);
			comentarioService.deleteByComentario(comentario);
			return ResponseEntity.status(HttpStatus.OK).body("Comentario Eliminado");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comentario No Existia");
	}
}
