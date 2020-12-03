package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyChatPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyPublicacionPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyUsuarioPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;

@Service
public class SocialAcademyServiceImpl implements SocialAcademyService {

	@Autowired
	@Qualifier("socialAcademyUsuarioPersistence")
	private SocialAcademyUsuarioPersistence sas;

	@Autowired
	@Qualifier("socialAcademyPublicacionPersistence")
	private SocialAcademyPublicacionPersistence spp;
	@Autowired
	@Qualifier("socialAcademyChatPersistence")
	private SocialAcademyChatPersistence chatServices;

	@Autowired
	private BCryptPasswordEncoder passworEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) sas.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(int id) {
		return sas.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario usuario) {
		sas.save(usuario);
	}

	@Override
	@Transactional
	public Publicacion findPublicacionById(int id) {
		return spp.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void savePublicacion(Publicacion publicacion) {
		spp.save(publicacion);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findusuarioByCorreo(String correo) {
		return sas.findByCorreo(correo);
	}

	@Override
	public void actualizarDatosBasicos(String[] datosUsuario, int id) {
		Usuario temp = findUsuarioById(id);
		if (datosUsuario[0] != "") {
			temp.setNombre(datosUsuario[0]);
		}
		if (datosUsuario[1] != "") {
			temp.setApellido(datosUsuario[1]);
		}
		if (datosUsuario[2] != "") {
			temp.setFecha(datosUsuario[2]);
		}
		if (datosUsuario[3] != "") {
			temp.setGenero(datosUsuario[3]);
		}
		if (datosUsuario[4] != "") {
			temp.setCiudad(datosUsuario[4]);
		}
		if (datosUsuario[5] != "") {
			temp.setPais(datosUsuario[5]);
		}
		if (datosUsuario[6] != "") {
			temp.setDescripcion(datosUsuario[6]);
		}
		saveUsuario(temp);
	}

	@Override
	public void actualizarDatosUniversidad(String[] datosUsuario, int id) {
		Usuario usuarioTemporal = findUsuarioById(id);
		if (!datosUsuario[0].equals("")) {
			usuarioTemporal.setUniversidad(datosUsuario[0]);
		}
		if (!datosUsuario[1].equals("")) {
			usuarioTemporal.setInicioUniversidad(datosUsuario[1]);
		}
		if (!datosUsuario[2].equals("")) {
			usuarioTemporal.setFinUniversidad(datosUsuario[2]);
		}
		if (!datosUsuario[3].equals("")) {
			usuarioTemporal.setDescripcionUniversiad(datosUsuario[3]);
		}
		saveUsuario(usuarioTemporal);
	}

	@Override
	public void actualizarDatosTrabajo(String[] datosUsuario, int id) {
		System.out.println("llego");
		Usuario usuarioTemporal = findUsuarioById(id);
		if (!datosUsuario[0].equals("")) {
			usuarioTemporal.setEmpresa(datosUsuario[0]);
		}
		if (!datosUsuario[1].equals("")) {
			usuarioTemporal.setCargo(datosUsuario[1]);
		}
		if (!datosUsuario[2].equals("")) {
			usuarioTemporal.setInicioTrabajo(datosUsuario[2]);
		}
		if (!datosUsuario[3].equals("")) {
			usuarioTemporal.setFinTrabajo(datosUsuario[3]);
		}
		if (!datosUsuario[4].equals("")) {
			usuarioTemporal.setCiudadTrabajo(datosUsuario[4]);
		}
		if (!datosUsuario[5].equals("")) {
			usuarioTemporal.setDescripcionTrabajo(datosUsuario[5]);
		}
		saveUsuario(usuarioTemporal);
	}

	@Override
	public void actualizarInterese(String datosUsuario, int id) {
		Usuario usuarioTemporal = findUsuarioById(id);
		if (!datosUsuario.equals("")) {
			List<Interes> intereses = usuarioTemporal.getIntereses();
			Interes newInteres = new Interes(datosUsuario);
			intereses.add(newInteres);
			// usuarioTemporal.setIntereses(newInteres);

		}
		saveUsuario(usuarioTemporal);
	}

	@Override
	public void uploadImagenPerfil(int id, MultipartFile imagenUsuario) throws IOException {
		Usuario usuarioTemp = findUsuarioById(id);
		if (!(imagenUsuario.isEmpty())) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + imagenUsuario.getOriginalFilename();
			Path rutaGuardar = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			Files.copy(imagenUsuario.getInputStream(), rutaGuardar);
			usuarioTemp.setFotoPerfil(nombreArchivo);
			saveUsuario(usuarioTemp);
		}

	}

	@Override
	public void agregarUsuario(String[] datos) {
		System.out.println(datos[3]);
		String password = passworEncoder.encode(datos[3]);
		Usuario newUsuario = new Usuario(datos[0], datos[1], datos[2], password, datos[4], datos[5], datos[6], true);
		saveUsuario(newUsuario);
	}

	@Override
	public void agregarPublicacion(int id, String contenido) {
		Usuario usuarioTemp = findUsuarioById(id);
		Publicacion newPublication = new Publicacion(id, contenido);
		usuarioTemp.getPublicaciones().add(newPublication);
		saveUsuario(usuarioTemp);

	}

	@Override
	public void agregarComentario(int id, int idPublicacion, String contenido) {
		// Guardamos el comentario en la persona que lo hace
		Usuario usuarioTemp1 = findUsuarioById(id);
		Publicacion publicacionTemp = findPublicacionById(idPublicacion);
		Comentario newComentario = new Comentario(contenido,id);
		usuarioTemp1.getComentarios().add(newComentario);
		saveUsuario(usuarioTemp1);

		int usuario = publicacionTemp.getIdusuario();
		Usuario usuarioTemp2 = findUsuarioById(usuario);
		for (Publicacion i : usuarioTemp2.getPublicaciones()) {
			if (i.getId() == idPublicacion) {
				i.getComentarios().add(newComentario);
				saveUsuario(usuarioTemp2);
				break;
			}
		}

	}

	@Override
	public List<Publicacion> getPublicaciones(int id) {
		Usuario temp = findUsuarioById(id);
		List<Publicacion> temp2 = new CopyOnWriteArrayList<>();
		for (int i = temp.getPublicaciones().size() - 1; i >= 0; i--) {
			temp2.add(temp.getPublicaciones().get(i));
		}
		return temp2;
	}

	@Override
	public void setPassword(int id, String[] datos) throws UsuarioServicesException {
		Usuario temp = findUsuarioById(id);
		if (temp.getPassword().equals(datos[0])) {
			temp.setPassword(datos[1]);
		} else {
			throw new UsuarioServicesException("La anterior contraseña no coincide");
		}
	}

	@Override
	public List<List<String>> getAllAmigos(int idUsuario){

		Usuario temp = findUsuarioById(idUsuario);
		List<Amigo> amigos = temp.getAmigos();
		List<List<String>> amigosFn = new ArrayList<List<String>>(); 
		
		for(int i =0; i < amigos.size();i++) {
			List<String> infoAmigos = new ArrayList<String>();
			Usuario usuario = findUsuarioById(amigos.get(i).getAmigo());
			infoAmigos.add(usuario.getNombre());
			infoAmigos.add(usuario.getApellido());
			if(usuario.getCargo() == null) {
				infoAmigos.add(" ");
			}else {
				infoAmigos.add(usuario.getCargo());
			}
			infoAmigos.add(Integer.toString(usuario.getId()));
			amigosFn.add(infoAmigos);
		}
		return amigosFn; 
	}
	@Override
	public List<List<String>> ObtenerChats(int id){
		List<List<String>> chats = new ArrayList<List<String>>();
		
		Usuario usuario = findUsuarioById(id);
		List<Propietarios> infoAmistades = usuario.getChats();
		for (int i = 0; i < infoAmistades.size(); i++) {
			Chat chat = findChatById(infoAmistades.get(i).getChat());
			List<String> infoChats = new ArrayList<String>();
			infoChats.add(Integer.toString(chat.getId()));
			if (chat.getAmigo() == id) {
				Usuario amigo = findUsuarioById(chat.getUsuario());
				infoChats.add(amigo.getNombre()); 
				infoChats.add(amigo.getApellido());
			}else {
				infoChats.add(findUsuarioById(chat.getAmigo()).getNombre());
				infoChats.add(findUsuarioById(chat.getAmigo()).getApellido());
			}
			chats.add(infoChats);
			
		}
		return chats; 
	}
	@Override
	public void agregarReaccion( int idPublicacion, String reaccion) {

		Publicacion publicacionTemp = findPublicacionById(idPublicacion);
		Reaccion newReaccion = new Reaccion(Integer.parseInt(reaccion));
		int usuario = publicacionTemp.getIdusuario();
		Usuario usuarioTemp = findUsuarioById(usuario);
		for(Publicacion i: usuarioTemp.getPublicaciones()){
			if (i.getId() == idPublicacion){
				i.getReacciones().add(newReaccion);
				saveUsuario(usuarioTemp);
				break;
			}
		}
	}

	@Override
	public int obtnerReaccionBien(int idUsuario, int idPublicacion) {
		Publicacion publicacionTemp = findPublicacionById(idPublicacion);
		List<Reaccion> tempReacciones = publicacionTemp.getReacciones();
		int totalBien = 1;

		for (Reaccion i:tempReacciones){
			if(i.getTipo() == 1) {
				totalBien += 1;
			}
		}
		return totalBien;
	}
	@Override
	public int obtnerReaccionMal(int idUsuario, int idPublicacion) {
		Publicacion publicacionTemp = findPublicacionById(idPublicacion);
		List<Reaccion> tempReacciones = publicacionTemp.getReacciones();
		int totalMal = 1;

		for (Reaccion i:tempReacciones){
			if(i.getTipo() == 0) {
				totalMal += 1;
			}
		}
		return totalMal;
	}

	@Override
	public List<Comentario> ontenerComentarios(int idUPublicacion, int idP) {
		Usuario temp = findUsuarioById(idUPublicacion);
		Publicacion tempPublicaciones = temp.getPublicaciones().get(idP-1);
		return tempPublicaciones.getComentarios();
	}
	@Override
	@Transactional(readOnly = true)
	public Chat findChatById(int id) {
		return chatServices.findById(id).orElse(null);
	}


}
