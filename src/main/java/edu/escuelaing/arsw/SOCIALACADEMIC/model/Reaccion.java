package edu.escuelaing.arsw.SOCIALACADEMIC.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reacciones")
public class Reaccion  implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 360, updatable = true)
    private int tipo;

    
	private static final long serialVersionUID = 1L;


}
