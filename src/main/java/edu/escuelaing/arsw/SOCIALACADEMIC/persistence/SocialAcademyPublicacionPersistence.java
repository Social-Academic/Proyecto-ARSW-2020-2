package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Publicacion;
import org.springframework.data.repository.CrudRepository;

public interface SocialAcademyPublicacionPersistence extends CrudRepository<Publicacion, Integer> {
}
