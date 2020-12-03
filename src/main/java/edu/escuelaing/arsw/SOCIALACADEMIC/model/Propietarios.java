package edu.escuelaing.arsw.SOCIALACADEMIC.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "propietario")
public class Propietarios {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private int usuario;
	private int chat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getChat() {
		return chat;
	}
	public void setChat(int chat) {
		this.chat = chat;
	}
}
