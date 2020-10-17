package edu.escuelaing.arsw.SOCIALACADEMIC.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.*;

//falta
@Entity
@Table(name = "Publicaciones")
public class Publicacion implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(updatable = false)
	private int idusuario;

	@Column(length = 360, updatable = true)
	private String contenido;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_REACCION")
	private List<Reaccion> reacciones = new CopyOnWriteArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_COMENTARIOSPublicacion")
	private List<Comentario> comentarios  = new CopyOnWriteArrayList<>();

	@Column(updatable = false)
	private String fechaPublicacion;


	public Publicacion() {
	}

	public Publicacion(int id, String publicacion) {
		this.idusuario = id;
		this.contenido= publicacion;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:MM");
		fechaPublicacion = formatter.format(date);
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getId() {
		return id;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public List<Reaccion> getReacciones() {
		return reacciones;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
}
