/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;
import edu.eci.cosw.proyecto_eff.components.InformacionCompra;
import edu.eci.cosw.proyecto_eff.logic.LogicaPago;
import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Pago;
import edu.eci.cosw.proyecto_eff.model.Pedido;
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
import edu.eci.cosw.proyecto_eff.rest.OperationFailedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
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
    
    @Before
    public void setup(){
         pr2.deleteAll();
        pr1.deleteAll();
        pr.deleteAll();
        sr.deleteAll();
        cr.deleteAll();
        cr1.deleteAll();
        pcr.deleteAll();
        fr.deleteAll();
        
    }
    
    @After
    public void tearDown(){
        pr2.deleteAll();
        pr1.deleteAll();
        pr.deleteAll();
        sr.deleteAll();
        cr.deleteAll();
        cr1.deleteAll();
        pcr.deleteAll();
        fr.deleteAll();
        
    }
    
    
    @Test
    public void testRegistrarPago(){
        Cliente cliente = new Cliente("prueba@gmail.com", "123456", "fernando", "garcia", "3103100000");
        cr.save(cliente);
        
        PlazoletaComida pc = new PlazoletaComida(new PlazoletaComidaId("C.C. Unicentro de occidente", "Bogotá")
                , 3, 3,3, 'S', 3, 3, 3, 'W' , 9);
        pcr.save(pc);
        
        Franquicia f = new Franquicia("Pizza 1969", new Float(12.5));
        fr.save(f);
        Sucursal s = new Sucursal(f, pc, "12345");
        sr.save(s);
        Categoria c = new Categoria("comida rapida");
        cr1.save(c);
        Producto p1 = new Producto(new ProductoId("pizza de carne", s.getIdSucursales()), c, s, 10000, false, "pizza con trozos de carne", new Float(3.0));
        pr.save(p1);
        
        Franquicia f1 = new Franquicia("PPC", new Float(1.3));
        fr.save(f1);
        Sucursal s1 = new Sucursal(f1, pc, "54321");
        sr.save(s1);
        Producto p2 = new Producto(new ProductoId("hamburguesa", s1.getIdSucursales()), c, s1, 12000, false, "hamburguesa con carne de res", new Float(2.0));
        pr.save(p2);
        Producto p3 = new Producto(new ProductoId("Pollo asado", s1.getIdSucursales()), c, s1, 15000, false, "pollo asado", new Float(1.0));
        pr.save(p3);
        
        
        Producto[] productos = new Producto[]{p1, p2, p3};
        InformacionCompra ic = new InformacionCompra("tarjeta debito", "4000 0012 3456 7890", 1, 2000, 1234, productos);
        boolean ok = lp.registrarPago(ic, cliente.getCorreoCliente());
        List<Pedido> l = pr2.searchPedidosDeCliente(cliente.getCorreoCliente());
        assertEquals(l.size(),2);
        List<Double> l2 = pr2.searchPagosDeCliente(cliente.getCorreoCliente());
        assertEquals(l2.get(0),new Double(
        ((p1.getPrecio()-(p1.getPrecio()*p1.getPorcentajeDescuento()/100))*(1-(f.getPorcentajeAcordado()/100)))+
        (((p2.getPrecio()-(p2.getPrecio()*p2.getPorcentajeDescuento()/100))+(p3.getPrecio()-(p3.getPrecio()*p3.getPorcentajeDescuento()/100)))*(1-(f1.getPorcentajeAcordado()/100)))
        ));
    }
}
