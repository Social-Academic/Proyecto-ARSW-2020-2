package edu.escuelaing.arsw.SOCIALACADEMIC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;

import java.io.Serializable;

@Entity
@Table(name = "intereses")
public class Interes implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(unique = true)
	private String interes;
	public Interes() {
		
	}
	public Interes(String newInteres) {
		interes = newInteres; 
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInteres() {
		return interes;
	}

	public void setAmigo(String interes) {
		this.interes = interes;
	}
}
