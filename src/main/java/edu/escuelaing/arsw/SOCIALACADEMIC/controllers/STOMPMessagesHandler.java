package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.ChatServices;

@Controller
public class STOMPMessagesHandler {
	  @Autowired
	  SimpMessagingTemplate msgt;
	  
	  @Autowired
	  @Qualifier("chatServicesImpl")
	  ChatServices chatServices;

	    @MessageMapping("/enviarMensaje.{idChat}")
	    @SendTo("")
	    public void handleBuyEvent(Mensaje mensaje, @DestinationVariable int idChat){
	    	System.out.println("holaaaaaaa");
	    	System.out.println(idChat);
	        System.out.println("Nuevo asiento recibido mensaje: "+ mensaje.getMensaje());
	        Mensaje newMensaje = new Mensaje(mensaje.getMensaje(), mensaje.getPropietario(), idChat);
	        chatServices.guardarMensaje(idChat, newMensaje);
	        List<List<String>> mensajes = new ArrayList<List<String>>();
	        List<String> gMensaje = new ArrayList<String>();
	        Date date = new Date();
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:MM");
	        gMensaje.add(formatter.format(date));
	        gMensaje.add(mensaje.getMensaje());
	        gMensaje.add("derecha");
	        gMensaje.add(Integer.toString(mensaje.getPropietario()));
	        mensajes.add(gMensaje);
	        msgt.convertAndSend("/topic/enviarMensaje."+idChat, mensajes);
	    }
}
