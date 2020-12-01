package edu.escuelaing.arsw.SOCIALACADEMIC.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "comentarios")
public class Comentario  implements Serializable {


	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(updatable = false)
    private int idusuario;

    @Column(updatable = true)
    private String contenido;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_REACCION_COMENTARIO")
    private List<Reaccion> reacciones ;

    public Comentario(String contenido, int idU) {
        this.contenido = contenido;
        this.idusuario = idU;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Comentario() {

    }
    public int getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    private static final long serialVersionUID = 1L;
}
