package edu.escuelaing.arsw.SOCIALACADEMIC.services;

import java.util.List;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Chat;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;

public interface ChatServices {

	void guardarMensaje(int id, Mensaje mensaje);

	List<Chat> findAll();

	Chat findChatById(int id);

	void saveChat(Chat chat);

	List<Mensaje> findAllMensaje();

	Mensaje findMensajeById(int id);

	void saveMensaje(Mensaje mensaje);

	Chat crearChat(int idUsuario, int idAmigo);


	Chat findChatByUsuarioAndAmigo(int usuario, int amigo);

}
