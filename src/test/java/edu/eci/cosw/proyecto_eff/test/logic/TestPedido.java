/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;

import edu.eci.cosw.proyecto_eff.logic.LogicaPedido;
import edu.eci.cosw.proyecto_eff.logic.LogicaSucursal;
import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Estado;
import edu.eci.cosw.proyecto_eff.model.EstadosPedido;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PedidoProducto;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.CategoriaRepository;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
import edu.eci.cosw.proyecto_eff.persistance.FranquiciaRepository;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import edu.eci.cosw.proyecto_eff.persistance.PlazoletaComidaRepository;
import edu.eci.cosw.proyecto_eff.persistance.ProductoRepository;
import edu.eci.cosw.proyecto_eff.persistance.SucursalRepository;
import edu.eci.cosw.proyecto_eff.rest.ResourceNotFoundException;
import java.util.List;
import java.util.Set;
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
public class TestPedido {
    @Autowired
    LogicaPedido lp;
    
    @Autowired
    LogicaSucursal ls;
    
    @Autowired
    ClienteRepository cr;
    
    @Autowired
    PedidoRepository pedr;
    
    @Autowired
    ProductoRepository pr;
    
    @Autowired
    PlazoletaComidaRepository pcr;
    

    
    @Autowired
    SucursalRepository sr;
    
    @Autowired
    FranquiciaRepository  fr;
    
    @Autowired
    CategoriaRepository catr;
    
    @Test
    public void testConsultarPedidoPorSucursal(){
        //deleteInfo();
         Cliente cliente= new Cliente("fan@hot.com", "123456", "Fabian", "Alvarez", "310582");
        cr.save(cliente);
        Pedido ped= new Pedido(cliente,false, false, "Pedido sin todavia enviarse a la sucursal");
        pedr.save(ped);
        
        
        PlazoletaComida plazoletaComida;
        Franquicia franquicia;
        Sucursal sucursal;
        Categoria categoria;
        
        //C.C Santa fe 
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá") , 1, 1, 1, 'W', 1, 1, 1, 'N' , 5);
        pcr.save(plazoletaComida);
        franquicia = new  Franquicia("Mc Donalds", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "1111");
        sr.save(sucursal);
        categoria = new Categoria("Perro Caliente");
        catr.save(categoria);
        Producto producto1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 10000, true, "perro sencillo en combo", 0 , "url");
        pr.save(producto1);
        Producto producto2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "perro ranchero en combo", 0, "url");
        pr.save(producto2);
        Producto producto3 = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 18000, true, "perro doble  salchicha alemana combo", new Float(2.0), "url");
        pr.save(producto3);
        
        ped.getPedidosProductoses().add(new PedidoProducto(ped, producto1) );
        ped.getPedidosProductoses().add(new PedidoProducto(ped, producto3) );
        pedr.save(ped);
        
        Pedido ped1= new Pedido(cliente, false, false,"esperando");
        ped1.getPedidosProductoses().add(new PedidoProducto(ped1, producto2));
        pedr.save(ped1);
        
        
        List<Pedido> list1 = lp.consultarPedidosPorSucursal(sucursal.getIdSucursales());
        Pedido p = list1.get(0);
        Pedido p1 = list1.get(1);
        
       
        assertEquals(ped.getIdPedidos(),p.getIdPedidos());
        assertEquals(ped1.getIdPedidos(),p1.getIdPedidos());
        //deleteInfo();
        
    }
    
    @Test
    public void testConsultarPedidoPorSucursalConYSinNotificar() throws ResourceNotFoundException{
        //deleteInfo();
        Cliente cliente= new Cliente("fan@hot.com", "123456", "Fabian", "Alvarez", "310582");
        cr.save(cliente);
        Pedido ped= new Pedido(cliente,false, false, "Pedido sin todavia enviarse a la sucursal");
        pedr.save(ped);
        
        
        PlazoletaComida plazoletaComida;
        Franquicia franquicia;
        Sucursal sucursal;
        Categoria categoria;
        
        //C.C Santa fe 
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá") , 1, 1, 1, 'W', 1, 1, 1, 'N' , 5);
        pcr.save(plazoletaComida);
        franquicia = new  Franquicia("Mc Donalds", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "1111");
        sr.save(sucursal);
        categoria = new Categoria("Perro Caliente");
        catr.save(categoria);
        Producto producto1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 10000, true, "perro sencillo en combo", 0, "url");
        pr.save(producto1);
        Producto producto2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "perro ranchero en combo", 0, "url");
        pr.save(producto2);
        Producto producto3 = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 18000, true, "perro doble  salchicha alemana combo", new Float(2.0), "url");
        pr.save(producto3);
        
        ped.getPedidosProductoses().add(new PedidoProducto(ped, producto1) );
        ped.getPedidosProductoses().add(new PedidoProducto(ped, producto3) );
        pedr.save(ped);
        
        Pedido ped1= new Pedido(cliente, false, false,"esperando");
        ped1.getPedidosProductoses().add(new PedidoProducto(ped1, producto2));
        pedr.save(ped1);
        
        Estado e =  new Estado();
        e.setEstado(EstadosPedido.RECIBIDOENSUCURSAL);
        ls.recibirNotificacion(ped1.getIdPedidos(),e);
        
        List<Pedido> list1 = lp.consultarPedidosPorSucursalNotificados(sucursal.getIdSucursales());
        List<Pedido> list2 = lp.consultarPedidosPorSucursalSinNotificar(sucursal.getIdSucursales());
        
        System.out.println(list1.size());
        System.out.println(list2.size());
        Pedido p = list1.get(0);
        Pedido p1 = list2.get(0);
        
        
        assertEquals(ped1.getIdPedidos(),p.getIdPedidos());
        assertEquals(ped.getIdPedidos(),p1.getIdPedidos());
        //deleteInfo();
        
    }
    @Test
    public void consultarPedidos(){
        //deleteInfo();
        Cliente cliente= new Cliente("fand@hot.com", "1234567", "Fabian", "Alvarez", "3105821116");
        cr.save(cliente);
        Pedido ped= new Pedido(cliente,false, false, "Pedido  todavia enviarse a la sucursal");
        pedr.save(ped);
        Pedido ped1= new Pedido(cliente, false, false,"esperando.");
        pedr.save(ped1);
        Pedido ped2= new Pedido(cliente, false, false,"esperando..");
        pedr.save(ped2);
        
        List<Pedido> list1 = lp.consultarPedidos(); 
        assertEquals(ped.getIdPedidos(),list1.get(list1.size()-3).getIdPedidos());
        assertEquals(ped1.getIdPedidos(),list1.get(list1.size()-2).getIdPedidos());
        assertEquals(ped2.getIdPedidos(),list1.get(list1.size()-1).getIdPedidos());
        
    }
    
     public void deleteInfo(){
        pedr.deleteAll();
        pr.deleteAll();
        sr.deleteAll();
        fr.deleteAll();
        pcr.deleteAll();
        cr.deleteAll();
        catr.deleteAll();
    }
    
}
