package edu.escuelaing.arsw.SOCIALACADEMIC.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje")
public class Mensaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String mensaje;
	private String fecha;
	private int propietario;
	private int idChat;
	public Mensaje() {
		
	}
	public Mensaje(String mensaje, int propietario, int idChat) {
		this.mensaje = mensaje;
		Date date = new Date();
		this.idChat = idChat;
		this.propietario = propietario;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:MM");
		fecha = formatter.format(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getPropietario() {
		return propietario;
	}

	public void setPropietario(int propietario) {
		this.propietario = propietario;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
