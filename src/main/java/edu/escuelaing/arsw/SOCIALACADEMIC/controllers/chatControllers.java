package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Chat;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.impl.ChatServicesImpl;

@Controller
public class chatControllers {
	@Autowired
	@Qualifier("chatServicesImpl")
	ChatServicesImpl chatServices;

	@MessageMapping("/mensaje.{idChat}")
	public Mensaje recibirMensaje(String infomensaje, @DestinationVariable int idChat) {
		Mensaje mensaje = new Mensaje(infomensaje);
		return mensaje;
	}
	@MessageMapping("/conectChat.{idUsuario}.{idAmigo}")
	public Chat conectChat(@DestinationVariable int usuario, @DestinationVariable int amigo) {
		Chat chat; 
		if (chatServices.findChatByUsuarioAndAmigo(usuario, amigo) == null) {
			chat = chatServices.crearChat(usuario, amigo);
		}else {
			chat = chatServices.findChatByUsuarioAndAmigo(usuario, amigo);
		}
		return chat; 
	}
}
