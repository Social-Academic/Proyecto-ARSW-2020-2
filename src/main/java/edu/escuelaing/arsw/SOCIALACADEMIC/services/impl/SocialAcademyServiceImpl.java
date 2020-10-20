package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Comentario;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Interes;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Publicacion;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
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
	public Publicacion findPublicacionById(int id){return spp.findById(id).orElse(null);}

	@Override
	@Transactional
	public void savePublicacion(Publicacion publicacion){spp.save(publicacion); }
	
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
		if(!datosUsuario[0].equals("")) {
			usuarioTemporal.setUniversidad(datosUsuario[0]);
		}
		if(!datosUsuario[1].equals("")) {
			usuarioTemporal.setInicioUniversidad(datosUsuario[1]);
		}
		if(!datosUsuario[2].equals("")) {
			usuarioTemporal.setFinUniversidad(datosUsuario[2]);
		}
		if(!datosUsuario[3].equals("")) {
			usuarioTemporal.setDescripcionUniversiad(datosUsuario[3]);
		}
		saveUsuario(usuarioTemporal);
	}
	@Override
	public void actualizarDatosTrabajo(String[] datosUsuario, int id) {
		System.out.println("llego");
		Usuario usuarioTemporal = findUsuarioById(id);
		if(!datosUsuario[0].equals("")) {
			usuarioTemporal.setEmpresa(datosUsuario[0]);
		}
		if(!datosUsuario[1].equals("")) {
			usuarioTemporal.setCargo(datosUsuario[1]);
		}
		if(!datosUsuario[2].equals("")) {
			usuarioTemporal.setInicioTrabajo(datosUsuario[2]);
		}
		if(!datosUsuario[3].equals("")) {
			usuarioTemporal.setFinTrabajo(datosUsuario[3]);
		}
		if(!datosUsuario[4].equals("")) {
			usuarioTemporal.setCiudadTrabajo(datosUsuario[4]);
		}
		if(!datosUsuario[5].equals("")) {
			usuarioTemporal.setDescripcionTrabajo(datosUsuario[5]);
		}
		saveUsuario(usuarioTemporal);
	}
	@Override
	public void actualizarInterese(String datosUsuario, int id){
		Usuario usuarioTemporal = findUsuarioById(id);
		if(!datosUsuario.equals("")) {
			List<Interes> intereses = usuarioTemporal.getIntereses();
			Interes newInteres = new Interes(datosUsuario); 
			intereses.add(newInteres);
			//usuarioTemporal.setIntereses(newInteres);
			
		}
		saveUsuario(usuarioTemporal);
	}
	@Override
	public void uploadImagenPerfil(int id, MultipartFile imagenUsuario) throws IOException {
		Usuario usuarioTemp = findUsuarioById(id);
		if (!(imagenUsuario.isEmpty())) {
			String nombreArchivo = UUID.randomUUID().toString()+"_"+imagenUsuario.getOriginalFilename();
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
	public void agregarPublicacion(int id,String contenido){
		Usuario usuarioTemp = findUsuarioById(id);
		Publicacion newPublication = new Publicacion(id,contenido);
		usuarioTemp.getPublicaciones().add(newPublication);
		saveUsuario(usuarioTemp);

	}
	@Override
	public void agregarComentario(int id, int idPublicacion,String contenido){
		//Guardamos el comentario en la persona que lo hace
		Usuario usuarioTemp1 = findUsuarioById(id);
		Publicacion publicacionTemp = findPublicacionById(idPublicacion);
		Comentario newComentario = new Comentario(contenido);
		usuarioTemp1.getComentarios().add(newComentario);
		saveUsuario(usuarioTemp1);


		int usuario = publicacionTemp.getIdusuario();
		Usuario usuarioTemp2 = findUsuarioById(usuario);
		for(Publicacion i: usuarioTemp2.getPublicaciones()){
			if (i.getId() == idPublicacion){
				i.getComentarios().add(newComentario);
				saveUsuario(usuarioTemp2);
				break;
			}
		}

	}
	@Override
	public List<Publicacion> getPublicaciones(int id){
		Usuario temp = findUsuarioById(id);
		List<Publicacion> temp2= new CopyOnWriteArrayList<>();
		for (int i = temp.getPublicaciones().size() - 1; i >= 0; i--) {
			temp2.add(temp.getPublicaciones().get(i));
		}
		return temp2;
	}
	@Override
	public void setPassword(int id, String[] datos) throws UsuarioServicesException {
		Usuario temp = findUsuarioById(id);
		if(temp.getPassword().equals(datos[0])) {
			temp.setPassword(datos[1]);
		}else {
			throw new UsuarioServicesException("La anterior contraseña no coincide");
		}
	}

}
