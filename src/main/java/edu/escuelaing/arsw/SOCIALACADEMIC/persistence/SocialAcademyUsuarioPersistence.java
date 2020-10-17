package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;

public interface SocialAcademyUsuarioPersistence extends CrudRepository<Usuario, Integer> {

}
