package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Chat;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyChatPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyMensajePersistence;
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
		
		Chat chat = new Chat(idUsuario, idAmigo);
		saveChat(chat);
		return chat; 
		
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
