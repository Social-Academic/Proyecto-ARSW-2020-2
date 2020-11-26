package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Chat;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyChatPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyMensajePersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyUsuarioPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.ChatServices;

@Service
public class ChatServicesImpl implements ChatServices {
	@Autowired
	@Qualifier("socialAcademyMensajePersistence")
	private SocialAcademyMensajePersistence SocialAcademiMensaje;
	@Autowired
	@Qualifier("socialAcademyChatPersistence")
	private SocialAcademyChatPersistence SocialAcademiChat;

	@Override
	public void guardarMensaje(int idChat, Mensaje newmensaje) {
		saveMensaje(newmensaje);
	}

	@Override
	public Chat crearChat(int idUsuario, int idAmigo) {
		Chat chat;
		if (findChatByUsuarioAndAmigo(idUsuario, idAmigo) != null) {
			chat = new Chat(idUsuario, idAmigo);
			saveChat(chat);
		} else {
			chat = findChatByUsuarioAndAmigo(idUsuario, idAmigo);
		}
		return chat;

	}

	@Override
	public List<List<String>> obtenerMensajes(int idChat, int idUsuario) {
		List<List<String>> mensajesFin = new ArrayList<List<String>>();
		Chat chat = findChatById(idChat);
		List<Mensaje> infoMensaje = chat.getMensajes();
		for (int i = 0; i < infoMensaje.size(); i++) {
			List<String> mensaje = new ArrayList<String>();
			mensaje.add(infoMensaje.get(i).getFecha());
			mensaje.add(infoMensaje.get(i).getMensaje());
			System.out.println(infoMensaje.get(i).getMensaje());
			if(infoMensaje.get(i).getPropietario() == idUsuario) {
				mensaje.add("derecha");
			}else if(infoMensaje.get(i).getPropietario() != idUsuario) {
				mensaje.add("izquierda");
			}
			
		}
		;
		return mensajesFin;
	}

	@Override
	@Transactional(readOnly = true)
	public Chat findChatByUsuarioAndAmigo(int usuario, int amigo) {
		return SocialAcademiChat.findChatByUsuarioAndAmigo(usuario, amigo);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Chat> findAll() {
		return (List<Chat>) SocialAcademiChat.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Chat findChatById(int id) {
		return SocialAcademiChat.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveChat(Chat chat) {
		SocialAcademiChat.save(chat);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Mensaje> findAllMensaje() {
		return (List<Mensaje>) SocialAcademiMensaje.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Mensaje findMensajeById(int id) {
		return SocialAcademiMensaje.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveMensaje(Mensaje mensaje) {
		SocialAcademiMensaje.save(mensaje);
	}
}
