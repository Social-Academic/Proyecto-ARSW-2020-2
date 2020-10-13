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

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(length = 100, updatable = true)
	private String nombre;
	@Column(length = 100, updatable = true)
	private String apellido;
	@Column(length = 10, updatable = true)
	private String fecha;
	@Column(length = 15, updatable = true)
	private String genero;
	@Column(length = 100, updatable = true)
	private String ciudad;
	@Column(unique = true)
	private String pais;
	private String password;
	@Column(length = 100, unique = true, updatable = false)
	private String correo;
	@Column(length = 360, updatable = true)
	private String descripcion;
	@Column(length = 100, updatable = true)
	private String universidad;
	@Column(length = 10, updatable = true, name = "inicio_universidad")
	private String inicioUniversidad;
	@Column(length = 10, updatable = true, name = "fin_universidad")
	private String finUniversidad;
	@Column(name = "descripcion_universidad")
	private String descripcionUniversiad;
	private boolean graduado;
	@Column(length = 100, updatable = true)
	private String empresa;
	@Column(length = 100, updatable = true)
	private String cargo;
	@Column(length = 10, updatable = true, name = "inicio_Trabajo")
	private String inicioTrabajo;
	@Column(length = 10, updatable = true, name = "fin_Trabajo")
	private String finTrabajo;
	@Column(length = 100, updatable = true, name = "ciudad_Trabajo")
	private String ciudadTrabajo;
	@Column(name = "descripcion_trabajo")
	private String descripcionTrabajo;
	
	private String fotoPerfil;
	private String intereses;
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_USUARIO")
	private List<Amigo> amigos;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_publicaciones")

	private List<Publicacion> publicaciones;
	public Usuario() {
		
	}
	public Usuario(String nombre, String apellido, String correo, String passwd, String fecha, String genero, String ciudad) {
		this.nombre= nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.password = passwd;
		this.fecha = fecha; 
		this.genero = genero;
		this.ciudad = ciudad; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Amigo> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Amigo> amigos) {
		this.amigos = amigos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	private static final long serialVersionUID = 1L;

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getInicioUniversidad() {
		return inicioUniversidad;
	}

	public void setInicioUniversidad(String inicioUniversidad) {
		this.inicioUniversidad = inicioUniversidad;
	}

	public String getFinUniversidad() {
		return finUniversidad;
	}

	public void setFinUniversidad(String finUniversidad) {
		this.finUniversidad = finUniversidad;
	}

	public String getDescripcionUniversiad() {
		return descripcionUniversiad;
	}

	public void setDescripcionUniversiad(String descripcionUniversiad) {
		this.descripcionUniversiad = descripcionUniversiad;
	}

	public boolean isGraduado() {
		return graduado;
	}

	public void setGraduado(boolean graduado) {
		this.graduado = graduado;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getInicioTrabajo() {
		return inicioTrabajo;
	}

	public void setInicioTrabajo(String inicioTrabajo) {
		this.inicioTrabajo = inicioTrabajo;
	}

	public String getFinTrabajo() {
		return finTrabajo;
	}

	public void setFinTrabajo(String finTrabajo) {
		this.finTrabajo = finTrabajo;
	}

	public String getCiudadTrabajo() {
		return ciudadTrabajo;
	}

	public void setCiudadTrabajo(String ciudadTrabajo) {
		this.ciudadTrabajo = ciudadTrabajo;
	}

	public String getDescripcionTrabajo() {
		return descripcionTrabajo;
	}

	public void setDescripcionTrabajo(String descripcionTrabajo) {
		this.descripcionTrabajo = descripcionTrabajo;
	}
	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String interese) {
		this.intereses = interese;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
