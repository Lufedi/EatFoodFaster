/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;
import edu.eci.cosw.proyecto_eff.logic.LogicaPago;
import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.CategoriaRepository;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
import edu.eci.cosw.proyecto_eff.persistance.FranquiciaRepository;
import edu.eci.cosw.proyecto_eff.persistance.PagoRepository;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import edu.eci.cosw.proyecto_eff.persistance.PlazoletaComidaRepository;
import edu.eci.cosw.proyecto_eff.persistance.ProductoRepository;
import edu.eci.cosw.proyecto_eff.persistance.SucursalRepository;
import java.util.ArrayList;
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
public class TestPago {
    
    @Autowired
    LogicaPago lp;
    
    @Autowired
    ClienteRepository cr;
    
    @Autowired
    CategoriaRepository cr1;
    
    @Autowired
    ProductoRepository pr;
    
    @Autowired
    PedidoRepository pr1;
    
    @Autowired
    PagoRepository pr2;
    
    @Autowired
    FranquiciaRepository fr;
    
    @Autowired
    PlazoletaComidaRepository pcr;
    
    @Autowired
    SucursalRepository sr;
    
    @Test
    public void testRegistrarPago(){
        /*PlazoletaComida pc = new PlazoletaComida(new PlazoletaComidaId("C.C. Unicentro", "Bogotá")
                , 3, 3,3, 'S', 3, 3, 3, 'W' , 9);
        pcr.save(pc);
        Franquicia f = new Franquicia("Burguer King", new Float(12.5));
        fr.save(f);
        Sucursal s = new Sucursal(f, pc, "12345");
        sr.save(s);
        Categoria c = new Categoria("comida rapida");
        cr1.save(c);
        Producto p = new Producto(new ProductoId("comida1", s.getIdSucursales()), c, s, 10000, false, "descripcion 1", new Float(0.0));
        pr.save(p);
        Cliente cliente = new Cliente("prueba@gmail.com", "123456", "fernando", "garcia", "3103100000");
        cr.save(cliente);
        ArrayList<Producto> l = new ArrayList<>();
        l.add(p);
        lp.registrarPago(l, cliente);
        assertEquals(true,true);*/
    }
}
