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
import edu.eci.cosw.proyecto_eff.model.InformacionCompra;
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
import java.util.List;
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
        
        Franquicia f = new Franquicia("Pizza 1969", new Float(10.0));
        fr.save(f);
        Sucursal s = new Sucursal(f, pc, "12345");
        sr.save(s);
        Categoria c = new Categoria("comida rapida");
        cr1.save(c);
        Producto p1 = new Producto(new ProductoId("pizza de carne", s.getIdSucursales()), c, s, 10000, false, "pizza con trozos de carne", new Float(0.0));
        pr.save(p1);
        
        Franquicia f1 = new Franquicia("PPC", new Float(10.0));
        fr.save(f1);
        Sucursal s1 = new Sucursal(f1, pc, "54321");
        sr.save(s1);
        Producto p2 = new Producto(new ProductoId("hamburguesa", s1.getIdSucursales()), c, s1, 12000, false, "hamburguesa con carne de res", new Float(0.0));
        pr.save(p2);
        Producto p3 = new Producto(new ProductoId("Pollo asado", s1.getIdSucursales()), c, s1, 15000, false, "pollo asado", new Float(0.0));
        pr.save(p3);
        
        ProductoId[] productoIds = new ProductoId[]{p1.getId(), p2.getId(), p3.getId()};
        InformacionCompra ic = new InformacionCompra(cliente.getCorreoCliente(), "master card", cliente.getNombre(), cliente.getApellido(), "123 456 789", 1, 2018, 1234, productoIds);
        
        boolean ok = lp.registrarPago(ic);
        List<Pedido> l = pr2.searchPedidosDeCliente(cliente.getCorreoCliente());
        assertEquals(l.size(),2);
        List<Double> l2 = pr2.searchPagosDeCliente(cliente.getCorreoCliente());
        assertEquals(l2.get(0),new Double(
        ((p1.getPrecio()-(p1.getPrecio()*p1.getPorcentajeDescuento()/100))*(1-(f.getPorcentajeAcordado()/100)))+
        (((p2.getPrecio()-(p2.getPrecio()*p2.getPorcentajeDescuento()/100))+(p3.getPrecio()-(p3.getPrecio()*p3.getPorcentajeDescuento()/100)))*(1-(f1.getPorcentajeAcordado()/100)))
        ));
    }
    
    @Test
    public void testRegistrarPagoCuentaNoValida(){
        Cliente cliente = new Cliente("prueba@hotmail.com", "123456", "fernando", "garcia", "3103100000");
        cr.save(cliente);
        
        PlazoletaComida pc = new PlazoletaComida(new PlazoletaComidaId("C.C. Unicentro", "Bogotá")
                , 3, 3,3, 'S', 3, 3, 3, 'W' , 9);
        pcr.save(pc);
        
        Franquicia f = new Franquicia("Pizza Hut", new Float(12.5));
        fr.save(f);
        Sucursal s = new Sucursal(f, pc, "12345");
        sr.save(s);
        Categoria c = new Categoria("comidas rapidas");
        cr1.save(c);
        Producto p1 = new Producto(new ProductoId("pizza de pollo", s.getIdSucursales()), c, s, 10000, false, "pizza con trozos de carne", new Float(3.0));
        pr.save(p1);
        
        Franquicia f1 = new Franquicia("PPC", new Float(1.3));
        fr.save(f1);
        Sucursal s1 = new Sucursal(f1, pc, "54321");
        sr.save(s1);
        Producto p2 = new Producto(new ProductoId("hamburguesa de pollo", s1.getIdSucursales()), c, s1, 12000, false, "hamburguesa con carne de pollo", new Float(2.0));
        pr.save(p2);
        Producto p3 = new Producto(new ProductoId("Pollo broaster", s1.getIdSucursales()), c, s1, 15000, false, "pollo asado", new Float(1.0));
        pr.save(p3);
        
        ProductoId[] productoIds = new ProductoId[]{p1.getId(), p2.getId(), p3.getId()};
        InformacionCompra ic = new InformacionCompra(cliente.getCorreoCliente(), "master card", cliente.getNombre(), cliente.getApellido(), "123 456 789", 1, 2018, 1234, productoIds);
        boolean ok = lp.registrarPago(ic);
        List<Pedido> l = pr2.searchPedidosDeCliente(cliente.getCorreoCliente());
        assertEquals(l.size(),0);
    }
}
