package edu.escuelaing.arsw.SOCIALACADEMIC.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.LoginPersistence;


@Service
public class LoginServicesimpl  implements UserDetailsService	 {
	
	@Autowired
	private LoginPersistence usuarioDao;
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByCorreo(username);
		if (usuario == null) {
			System.out.println("error en el Login: el usuario no existe");
			throw new UsernameNotFoundException("error en el Login: el usuario no existe"+username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("admin"));
		authorities.add(new SimpleGrantedAuthority("Ususario"));
		return new User(usuario.getCorreo(), usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
	}

}
