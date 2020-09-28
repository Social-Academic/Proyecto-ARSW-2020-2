package edu.escuelaing.arsw.SOCIALACADEMIC.services;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;


public interface SocialAcademyService {

    List<Usuario> findAll();
    Usuario findUsuarioById(int id);
    void saveUsuario(Usuario usuario);

}
