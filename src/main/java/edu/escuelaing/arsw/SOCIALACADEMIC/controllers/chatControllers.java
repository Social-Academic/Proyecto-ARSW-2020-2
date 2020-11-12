package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;

@Controller
public class chatControllers {
	@MessageMapping("/mensaje")
	public Mensaje recibirMensaje(Mensaje mensaje) {
		
		return mensaje; 
	}
}
