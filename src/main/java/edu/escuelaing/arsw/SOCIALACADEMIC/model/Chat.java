package edu.escuelaing.arsw.SOCIALACADEMIC.model;

import java.awt.Image;
import java.util.List;

public class Chat {
	private Usuario destino;
	private Usuario receptor;
	private List<List<String>> historial;
	private List<Image> imagenes; 
	
	public Chat(Usuario destino, Usuario receptor, String mensaje) {
		this.destino= destino;
		this.receptor= receptor;
		historial = cargarHistorial();
		
	}
	public List<List<String>> cargarHistorial(){
		return null;
	}
	public List<Image> cargarImagenes(){
		return null;
	}
	public void nuevoMensaje(String mensaje) {
		
	}
	public void nuevoImagen(Image imagen) {
		
	}
}
