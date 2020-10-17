package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.LoginPersistence;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyPersistence;

@Service
public class LoginServicesimpl  implements UserDetailsService	 {
	@Autowired
	private LoginPersistence usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByCorreo(username);
		return null;
	}

}
