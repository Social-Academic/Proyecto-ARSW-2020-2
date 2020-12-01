package edu.escuelaing.arsw.SOCIALACADEMIC.services;

import java.io.IOException;
import java.util.List;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Amigo;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Comentario;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Publicacion;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.impl.UsuarioServicesException;


public interface SocialAcademyService {

    List<Usuario> findAll();
    Usuario findUsuarioById(int id);
    void saveUsuario(Usuario usuario);
    void actualizarDatosBasicos(String[] datosUsuario, int id);
    void uploadImagenPerfil(int id, MultipartFile imagenUsuario) throws IOException;
	void agregarUsuario(String[] datos);
	void actualizarDatosUniversidad(String[] datosUsuario, int id);
	void actualizarDatosTrabajo(String[] datosUsuario, int id);
	void actualizarInterese(String datosUsuario, int id);
    void agregarPublicacion(int id,String contenido);
    Publicacion findPublicacionById(int id);
    void savePublicacion(Publicacion publicacion);
    List<Publicacion> getPublicaciones(int id);
    void agregarComentario(int id,int idPublicacion,String contenido);
	Usuario findusuarioByCorreo(String correo);
	void setPassword(int id, String[] datos) throws UsuarioServicesException;
	List<List<String>> getAllAmigos(int idUsuario);
	List<List<String>> ObtenerChats(int id);
    void agregarReaccion( int idPublicacion, String reaccion);
    int obtnerReaccionBien(int idUsuario, int idPublicacion);
    int obtnerReaccionMal(int idUsuario, int idPublicacion);
    List<Comentario> ontenerComentarios(int idUPublicacion, int idP);
}
