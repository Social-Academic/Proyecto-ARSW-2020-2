package edu.escuelaing.arsw.SOCIALACADEMIC.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "amigos")
public class Amigo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(unique = true)
	private int amigo;

	public int getAmigo() {
		return amigo;
	}

	public void setAmigo(int amigos) {
		this.amigo = amigos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
}
