package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;


import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;
import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/socialacedemy")
public class SocialAcademyApiController {

    @Autowired
    @Qualifier("socialAcademyServiceImpl")
    SocialAcademyService sas;

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody String[] datosUsuario) {
        sas.actualizarDatosBasicos(datosUsuario, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id){
        return new ResponseEntity<>(sas.findUsuarioById(id), HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResponseEntity<?> uploadImagenUsuario(@RequestParam MultipartFile imagenUsuario, @RequestParam int idUsuario){
    	try {
			sas.uploadImagenPerfil(idUsuario, imagenUsuario);
			return new ResponseEntity<>( HttpStatus.ACCEPTED);
		} catch (IOException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
    	
    }
}
