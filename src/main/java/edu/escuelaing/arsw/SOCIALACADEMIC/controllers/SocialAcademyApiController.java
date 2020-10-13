package edu.escuelaing.arsw.SOCIALACADEMIC.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;

@RestController
@RequestMapping(value = "/usuarios")

public class SocialAcademyApiController {

    @Autowired
    @Qualifier("socialAcademyServiceImpl")
    SocialAcademyService sas;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id){
        return new ResponseEntity<>(sas.findUsuarioById(id), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value="/informacionBasica/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody String[] datosUsuario) {
        sas.actualizarDatosBasicos(datosUsuario, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.PUT, value= "/informacionUniversidad/{id}")
    public ResponseEntity<?> actualizarInformacionUni(@PathVariable int id, @RequestBody String[] datosUsuario){
    	sas.actualizarDatosUniversidad(datosUsuario, id);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.PUT, value= "/informacionTrabajo/{id}")
    public ResponseEntity<?> actualizarInformacionTra(@PathVariable int id, @RequestBody String[] datosUsuario){
    	sas.actualizarDatosTrabajo(datosUsuario, id);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.PUT, value= "/intereses/{id}")
    public ResponseEntity<?> actualizarIntereses(@PathVariable int id, @RequestBody String[] datosUsuario){
    	sas.actualizarInterese(datosUsuario, id);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.POST, value ="/uploadImage")
    public ResponseEntity<?> uploadImagenUsuario(@RequestParam("imagenUsuario") MultipartFile imagenUsuario, @RequestParam("idUsuario") int idUsuario){
    	try {
            System.out.println("entre a guardar la imagen");
			sas.uploadImagenPerfil(idUsuario, imagenUsuario);
			return new ResponseEntity<>( HttpStatus.ACCEPTED);
		} catch (IOException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> crearUsuario(@RequestBody String[] datosUsuario){
    	sas.agregarUsuario(datosUsuario);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
