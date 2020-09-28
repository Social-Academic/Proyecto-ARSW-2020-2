package edu.escuelaing.arsw.SOCIALACADEMIC.services;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.List;


public interface SocialAcademyService {

    List<Usuario> findAll();
    Usuario findUsuarioById(int id);
    void saveUsuario(Usuario usuario);
    void actualizarDatosBasicos(Usuario usuario, int id);
    void uploadImagenPerfil(int id, MultipartFile imagenUsuario) throws IOException;

}
