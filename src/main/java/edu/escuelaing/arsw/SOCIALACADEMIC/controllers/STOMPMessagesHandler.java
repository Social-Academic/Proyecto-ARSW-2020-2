package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Chat;
import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.impl.ChatServicesImpl;

@Controller
public class STOMPMessagesHandler {
	  @Autowired
	    SimpMessagingTemplate msgt;

	    @MessageMapping("/enviarMensaje.{idChat}")
	    public void handleBuyEvent(String mensaje, @DestinationVariable int idChat) throws Exception {
	        System.out.println("Nuevo asiento recibido mensaje: \n"+ mensaje);
	        msgt.convertAndSend("/topic/enviarMensaje."+idChat, mensaje);
	    }
}
