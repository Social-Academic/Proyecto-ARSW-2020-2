package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Publicacion;

public interface SocialAcademyPublicacionPersistence extends CrudRepository<Publicacion, Integer> {
	
}
