package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;

public interface LoginPersistence extends CrudRepository<Usuario, Integer> {

	public Usuario findByCorreo(String correo);
}
