package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SocialAcademyServiceImpl implements SocialAcademyService{

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
	public void actualizarDatosBasicos(Usuario usuario, int id) {
		Usuario temp = findUsuarioById(id);
        temp.setNombre(usuario.getNombre());
        temp.setApellido(usuario.getApellido());
        temp.setFecha(usuario.getFecha());
        temp.setGenero(usuario.getGenero());
        temp.setCiudad(usuario.getCiudad());
        temp.setPais(usuario.getPais());
        temp.setDescripcion(usuario.getDescripcion());
        saveUsuario(temp);
	}

	@Override
	public void uploadImagenPerfil(int id, MultipartFile imagenUsuario) throws IOException {
		Usuario usuarioTemp = findUsuarioById(id);
		if(!(imagenUsuario.isEmpty())){
			String nombreArchivo = imagenUsuario.getOriginalFilename();
			Path rutaGuardar = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			Files.copy(imagenUsuario.getInputStream(), rutaGuardar);
			usuarioTemp.setFotoPerfil(nombreArchivo);
			saveUsuario(usuarioTemp);
		}
		
	}
	
    
}
