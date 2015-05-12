/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;

import edu.eci.cosw.proyecto_eff.logic.LogicaCliente;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author lufedi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
public class TestSincronizacion {
    @Autowired
    LogicaCliente lc;
    
    @Autowired
    ClienteRepository cr;
    
   @Test
    public void testAgregarCliente(){
        Cliente cl;
        Cliente c= new Cliente("juanpa@hot.com", "retro", "Juan Pablo", "Lopez", "312493");
        cr.save(c);
        lc.registrarCliente(c);
        
        cl=lc.consultarCliente("juanpa@hot.com");
        assertEquals(c.getNombre()+c.getApellido(),cl.getNombre()+cl.getApellido());
    }
    
    @Test
    public void testModificarCliente(){
        Cliente cl;
        Cliente c= new Cliente("oliva@gmail.com", "1234", "Jenni", "Barajas", "310598");
        cr.save(c);
        c.setContrasena("4321");
        lc.modificarCliente(c);
        cl=lc.consultarCliente("oliva@gmail.com");
        assertEquals(c.getContrasena(),cl.getContrasena());
    }
}
