package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Mensaje;

public interface SocialAcademyMensajePersistence extends CrudRepository<Mensaje, Integer>{
}
