package edu.escuelaing.arsw.SOCIALACADEMIC.services;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.persistence.SocialAcademyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocialAcademyServiceImpl implements SocialAcademyService{

    @Autowired
    @Qualifier("socialAcademyPersistence")
    private SocialAcademyPersistence sas;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) sas.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findUsuarioById(int id) {
        return sas.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveUsuario(Usuario usuario) {
        sas.save(usuario);
    }
}
