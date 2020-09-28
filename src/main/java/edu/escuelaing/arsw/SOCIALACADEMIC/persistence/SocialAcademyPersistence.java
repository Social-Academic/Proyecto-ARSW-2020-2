package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;

public interface SocialAcademyPersistence extends CrudRepository<Usuario, Integer> {

	// public void actualizarInfoBasica( String nombre, String apellido, String
	// fecha, String genero, String ciudad, String pais, String acerca);
}
