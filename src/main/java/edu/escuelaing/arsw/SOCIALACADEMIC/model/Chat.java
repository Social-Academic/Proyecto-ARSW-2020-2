package edu.escuelaing.arsw.SOCIALACADEMIC.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//falta
@Entity
@Table(name = "chats")
public class Chat implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(unique = true)
	private int amigo;
	@Column(unique = true)
	private int usuario;
	@Column(length = 10, updatable = false)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idChat")
	private List<Mensaje> mensajes;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "chat")
	private List<Propietarios> idChat;
	
	
	public Chat() {
	}
	public Chat(int amigo, int usuario) {
		this.amigo = amigo;
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmigo() {
		return amigo;
	}
	public void setAmigo(int amigo) {
		this.amigo = amigo;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public List<Propietarios> getIdChat() {
		return idChat;
	}
	public void setIdChat(List<Propietarios> idChat) {
		this.idChat = idChat;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
