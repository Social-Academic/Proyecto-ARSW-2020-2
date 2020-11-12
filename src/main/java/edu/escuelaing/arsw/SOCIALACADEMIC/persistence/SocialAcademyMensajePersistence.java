package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;

public interface SocialAcademyMensajePersistence extends CrudRepository<Mensaje, Integer>{

}
