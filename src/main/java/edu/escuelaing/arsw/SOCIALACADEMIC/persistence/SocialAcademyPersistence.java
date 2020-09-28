package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


public interface SocialAcademyPersistence extends CrudRepository<Usuario,Integer> {


    //public void actualizarInfoBasica( String  nombre, String apellido, String fecha, String genero, String ciudad, String pais, String acerca);
}
