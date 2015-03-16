/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;

import edu.eci.cosw.proyecto_eff.logic.LogicaPedido;
import edu.eci.cosw.proyecto_eff.logic.LogicaSucursal;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author FabianAndres
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})

public class TestSucursal {
    
    @Autowired
    LogicaSucursal ls;
    
    @Autowired
    LogicaPedido lp;
    
    @Autowired
    ClienteRepository cr;
    
    @Autowired
    PedidoRepository pr;
    
    @Test
    public void testNotificarCliente(){
        assertEquals(true,true);
    }
    
    @Test
    public void testRecibirNotificacion(){
        
        Cliente c= new Cliente("fan@hot.com", "123456", "Fabian", "Alvarez", "310582");
        cr.save(c);
        Pedido p= new Pedido(c,false, false, "Pedido sin todavia enviarse a la sucursal");
        pr.save(p);
        Franquicia f = new Franquicia("El Corral");
        PlazoletaComida pc= new PlazoletaComida(new PlazoletaComidaId("CC Santafe", "Bogota"));
        Sucursal s= new Sucursal(f, pc, "12345");
        ls.recibirNotificacion(p);
        
        
        Pedido p1 = lp.consultarPedidoPorId(p.getIdPedidos());
        assertEquals(true,p1.isEnviadoAsucursal());
         
       
                
    }
    
}
