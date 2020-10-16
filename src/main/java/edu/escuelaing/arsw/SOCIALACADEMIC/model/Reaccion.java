package edu.escuelaing.arsw.SOCIALACADEMIC.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "reacciones")
public class Reaccion  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 360, updatable = true)
    private int tipo;




}
