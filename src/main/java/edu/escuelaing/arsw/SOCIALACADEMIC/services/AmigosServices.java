package edu.escuelaing.arsw.SOCIALACADEMIC.services;

import java.util.List;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Amigo;

public interface AmigosServices {

	List<Amigo> findAll();

	Amigo findChatById(int id);

	void saveAmigo(Amigo amigo);

}
