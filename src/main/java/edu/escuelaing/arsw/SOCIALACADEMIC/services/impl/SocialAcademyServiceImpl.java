package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;

@Service
public class SocialAcademyServiceImpl implements SocialAcademyService {

	@Autowired
	@Qualifier("socialAcademyPersistence")
	private SocialAcademyPersistence sas;

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

}
