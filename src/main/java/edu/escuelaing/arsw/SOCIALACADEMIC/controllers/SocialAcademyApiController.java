package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;

import java.io.IOException;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.ChatServices;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.impl.UsuarioServicesException;

@RestController
@RequestMapping(value = "/usuarios")

public class SocialAcademyApiController {

	@Autowired
	@Qualifier("socialAcademyServiceImpl")
	SocialAcademyService sas;

	@Autowired
	@Qualifier("chatServicesImpl")
	ChatServices chatServices;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> obtenerUsuario(@PathVariable int id) {
		return new ResponseEntity<>(sas.findUsuarioById(id), HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/correos/{correo}")
	public ResponseEntity<?> obtenerUsuarioCorreo(@PathVariable String correo) {
		return new ResponseEntity<>(sas.findusuarioByCorreo(correo), HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/cambioPassword/{id}")
	public ResponseEntity<?> actualizarPassword(@PathVariable int id, @RequestBody String[] datos) {
		try {
			sas.setPassword(id, datos);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (UsuarioServicesException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/informacionBasica/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody String[] datosUsuario) {
		sas.actualizarDatosBasicos(datosUsuario, id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/informacionUniversidad/{id}")
	public ResponseEntity<?> actualizarInformacionUni(@PathVariable int id, @RequestBody String[] datosUsuario) {
		sas.actualizarDatosUniversidad(datosUsuario, id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/informacionTrabajo/{id}")
	public ResponseEntity<?> actualizarInformacionTra(@PathVariable int id, @RequestBody String[] datosUsuario) {
		sas.actualizarDatosTrabajo(datosUsuario, id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/intereses/{id}")
	public ResponseEntity<?> actualizarIntereses(@PathVariable int id, @RequestBody String datosUsuario) {
		sas.actualizarInterese(datosUsuario, id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uploadImage")
	public ResponseEntity<?> uploadImagenUsuario(@RequestParam("imagenUsuario") MultipartFile imagenUsuario,
			@RequestParam("idUsuario") int idUsuario) {
		try {
			System.out.println("entre a guardar la imagen");
			sas.uploadImagenPerfil(idUsuario, imagenUsuario);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> crearUsuario(@RequestBody String[] datosUsuario) {
		sas.agregarUsuario(datosUsuario);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/publicaciones")
	public ResponseEntity<?> crearPublicacion(@RequestBody String publicacion, @PathVariable int id) {
		sas.agregarPublicacion(id, publicacion);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/publicaciones/{idPublicacion}/comentarios")
	public ResponseEntity<?> crearComentario(@RequestBody String comentario, @PathVariable int idPublicacion,
			@PathVariable int id) {
		sas.agregarComentario(id, idPublicacion, comentario);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/publicaciones")
	public ResponseEntity<?> obtenerPublicaciones(@PathVariable int id) {
		return new ResponseEntity<>(sas.getPublicaciones(id), HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/chats/new/{idUsuario}/{idAmigo}")
	public ResponseEntity<?> crearChat(@PathVariable int idUsuario, @PathVariable int idAmigo) {
		return new ResponseEntity<>(chatServices.crearChat(idUsuario, idAmigo), HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "{idUsuario}/chats/{idChat}")
	public ResponseEntity<?> guardarMensaje(@PathVariable int idChat,@PathVariable int idUsuario, @RequestBody String stringMensaje) {
		Mensaje mensaje = new Mensaje(stringMensaje, idUsuario);
		chatServices.guardarMensaje(idChat, mensaje);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{idUsuario}/amigos")
	public ResponseEntity<?> cargarAmistades(@PathVariable int idUsuario) {
		System.out.println("llego");
		return new ResponseEntity<>(sas.getAllAmigos(idUsuario), HttpStatus.ACCEPTED);
	}
	@RequestMapping(method = RequestMethod.GET, value = "{idUsuario}/chats")
	public ResponseEntity<?> ObtenerMensajes(@PathVariable int idUsuario) {
		return new ResponseEntity<>(sas.ObtenerChats(idUsuario), HttpStatus.ACCEPTED);
	}
	@RequestMapping(method = RequestMethod.GET, value = "{idUsuario}/chats/{idChat}/mensajes")
	public ResponseEntity<?> ObtenerMensajes(@PathVariable int idUsuario, @PathVariable int idChat) {
		return new ResponseEntity<>(chatServices.obtenerMensajes(idChat, idUsuario), HttpStatus.ACCEPTED);
	}
	@RequestMapping(method = RequestMethod.POST, value  = "/{id}/publicaciones/{idPublicacion}/reacciones")
	public ResponseEntity<?> crearReaccion(@RequestBody String reaccion, @PathVariable int idPublicacion, @PathVariable int id){

		sas.agregarReaccion(idPublicacion,reaccion);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	@RequestMapping(method = RequestMethod.GET, value  = "/{id}/publicaciones/{idPublicacion}/reacciones/buenas")
	public ResponseEntity<?> obtenerReaccionesBien(@PathVariable int idPublicacion, @PathVariable int id){
		return new ResponseEntity<>(sas.obtnerReaccionBien(id,idPublicacion),HttpStatus.ACCEPTED);
	}
	@RequestMapping(method = RequestMethod.GET, value  = "/{id}/publicaciones/{idPublicacion}/reacciones/malas")
	public ResponseEntity<?> obtenerReaccionesMal(@PathVariable int idPublicacion, @PathVariable int id){
		return new ResponseEntity<>(sas.obtnerReaccionMal(id,idPublicacion),HttpStatus.ACCEPTED);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{idUPublicacion}/publicaciones/{idP}/comentarios")
	public ResponseEntity<?> obtenerComentarios(@PathVariable int idUPublicacion, @PathVariable int idP) {

		return new ResponseEntity<>(sas.ontenerComentarios(idUPublicacion,idP),HttpStatus.ACCEPTED);
	}

}
