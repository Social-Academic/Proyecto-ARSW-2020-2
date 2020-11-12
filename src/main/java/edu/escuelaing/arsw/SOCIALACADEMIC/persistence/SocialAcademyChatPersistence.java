package edu.escuelaing.arsw.SOCIALACADEMIC.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Chat;

public interface SocialAcademyChatPersistence extends CrudRepository<Chat, Integer>  {

}
