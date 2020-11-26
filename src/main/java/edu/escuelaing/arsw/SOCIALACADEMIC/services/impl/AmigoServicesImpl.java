package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Amigo;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyAmigosPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.AmigosServices;

public class AmigoServicesImpl implements AmigosServices {
	@Autowired
	@Qualifier("socialAcademyAmigosPersistence")
	private SocialAcademyAmigosPersistence socialAcademyAmigos;
	
	@Override
	@Transactional
	public void saveAmigo(Amigo amigo) {
		socialAcademyAmigos.save(amigo);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Amigo> findAll() {
		return (List<Amigo>) socialAcademyAmigos.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Amigo findChatById(int id) {
		return socialAcademyAmigos.findById(id).orElse(null);
	}
}
