package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;


import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/socialacedemy")
public class SocialAcademyApiController {

    @Autowired
    @Qualifier("socialAcademyServiceImpl")
    SocialAcademyService sas;

    @RequestMapping(method= RequestMethod.PUT, value="/{id}")
    public ResponseEntity<?> actualizarFuncion(@PathVariable int id, @RequestBody Usuario usuario) {
        Usuario temp = sas.findUsuarioById(id);
        temp.setNombre(usuario.getNombre());
        temp.setApellido(usuario.getApellido());
        temp.setFecha(usuario.getFecha());
        temp.setGenero(usuario.getGenero());
        temp.setCiudad(usuario.getCiudad());
        temp.setPais(usuario.getPais());
        temp.setDescripcion(usuario.getDescripcion());
        sas.saveUsuario(temp);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Usuario obtenerUsuario(@PathVariable int id){
        return sas.findUsuarioById(id);
    }
}
