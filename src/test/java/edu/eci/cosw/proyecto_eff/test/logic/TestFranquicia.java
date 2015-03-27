/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;

import edu.eci.cosw.proyecto_eff.logic.LogicaFranquicia;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.FranquiciaRepository;
import edu.eci.cosw.proyecto_eff.persistance.PlazoletaComidaRepository;
import edu.eci.cosw.proyecto_eff.persistance.SucursalRepository;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jennibarajas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})

public class TestFranquicia {

    @Autowired
    PlazoletaComidaRepository pcr;
        
    @Autowired
    SucursalRepository sr;
    
    @Autowired
    FranquiciaRepository  fr;
    
    @Autowired
    LogicaFranquicia lf;
    
    @Test
    public void testBuscarSucursalesPorFranquicia(){
        PlazoletaComida plazoletaComida;
        Franquicia franquicia;
        Sucursal sucursal;
        
        
        //C.C Santa fe 
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá") , 1, 1, 1, 'W', 1, 1, 1, 'N' , 5);
        pcr.save(plazoletaComida);
        franquicia = new  Franquicia("Mc Donalds", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "1111");
        sr.save(sucursal);
        
        //C.C BIMA
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. BIMA", "Bogotá"), 2, 2, 2, 'N', 2, 2, 2, 'W'  , 8);
        pcr.save(plazoletaComida);
        sucursal = new Sucursal(franquicia, plazoletaComida, "2222");
        sr.save(sucursal);

        //C.C Unicentro
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Unicentro", "Bogotá"), 3, 3,3, 'S', 3, 3, 3, 'W' , 9);
        pcr.save(plazoletaComida);
        sucursal = new Sucursal(franquicia, plazoletaComida, "4444");
        sr.save(sucursal);
 
        List<Sucursal> ls= lf.buscarSucursalesPorFranquicia(franquicia);
        assertEquals(ls.size(),3);
    }
    
}
