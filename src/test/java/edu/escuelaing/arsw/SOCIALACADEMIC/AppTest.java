package edu.escuelaing.arsw.SOCIALACADEMIC;

import edu.escuelaing.arsw.SOCIALACADEMIC.model.Usuario;

import edu.escuelaing.arsw.SOCIALACADEMIC.services.SocialAcademyService;

//import jdk.nashorn.internal.objects.NativeJSON;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    /*
    private static final NativeJSON JSON = null;
    @Autowired
    @Qualifier("socialAcademyServiceImpl")
    SocialAcademyService sas;
    @Test
    public  void findAllTest(){

        List<Usuario> temp = sas.findAll();
        assertEquals(temp.size(),1);
    }

    @Test
    public void findByIdTest(){
        Usuario temp = sas.findUsuarioById(0);
        assertEquals(temp.getNombre(),"JOSE LUIS GOMEZ");
    }
    /*
    @Test
    public void updateUsuarioTest(){


        String[] datos = new String[]{"JOSE LUIS GOMEZ", "Perez", "2020-09-15", "Femenino", "Pekin", "China", "Estudiante de sistemas"} ;
        sas.actualizarDatosBasicos(datos,0);
        //Usuario temp = sas.findUsuarioById(0);
        //assertEquals(temp.getGenero(),"Femenino");
    }

     */
    /*
    @Test
    public void notFoundById(){
        Usuario temp = sas.findUsuarioById(1000);
        assertNull(temp);
    }
    */


}