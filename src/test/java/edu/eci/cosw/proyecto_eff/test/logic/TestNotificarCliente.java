/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;

import edu.eci.cosw.proyecto_eff.logic.LogicaPedido;
import edu.eci.cosw.proyecto_eff.logic.LogicaProducto;
import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Cliente;
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
import edu.eci.cosw.proyecto_eff.rest.OperationFailedException;
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
 * @author luisfediaz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
public class TestNotificarCliente {
    
    @Autowired
    ClienteRepository  clr;
    
    @Autowired
    LogicaProducto  productoCtrl;
    
    @Autowired
    ProductoRepository pr;
    
    @Autowired
    PlazoletaComidaRepository pcr;
    
    @Autowired
    PedidoRepository pedr;
    
    @Autowired
    SucursalRepository sr;
    
    @Autowired
    FranquiciaRepository  fr;
    
    @Autowired
    CategoriaRepository cr;
    
    @Autowired
    LogicaPedido lp;
    
    @Before
    public void preparar(){
        this.borrarDatos();
        //realizar operación de persistencia
        //se agregan los datos de persistencia para las pruebas
        PlazoletaComida plazoletaComida;
        Franquicia franquicia;
        Sucursal sucursal;
        Producto producto;
        Categoria categoria;
        
        //C.C Santa fe 
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá") , 1, 1, 1, 'W', 1, 1, 1, 'N' , 5);
        pcr.save(plazoletaComida);
        franquicia = new  Franquicia("Mc Donalds", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "1111");
        sr.save(sucursal);
        categoria = new Categoria("Perro Caliente");
        cr.save(categoria);
        Producto producto1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 10000, true, "perro sencillo en combo", 0);
        pr.save(producto1);
        Producto producto2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "perro ranchero en combo", 0);
        pr.save(producto2);
        Producto producto3 = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 18000, true, "perro doble  salchicha alemana combo", new Float(2.0));
        pr.save(producto3);
        
        
        Cliente fercho = new Cliente("ferchogarc010@gmail.com", "67890", "fercho", "garcia", "3103078766");
        clr.save(fercho);
        Cliente felipe = new Cliente("pipexir@gmail.com", "themercenary", "Felipe", "Diaz", "3193387106");
        clr.save(felipe);
        Cliente jenni = new Cliente("jennibarajas@gmail.com" , "comunismo" , "Jenni" , "Barajas" , "321505481");
        clr.save(jenni);
      
        Pedido pedido = new Pedido(fercho, false, false, EstadosPedido.ENVIADOASUCURSAL);
        pedido.getPedidosProductoses().add(new PedidoProducto(pedido, producto1) );
        pedido.getPedidosProductoses().add(new PedidoProducto(pedido, producto2) );
        pedido.getPedidosProductoses().add(new PedidoProducto(pedido, producto3) );
        pedr.save(pedido);
        

    }
    
    @After
    public void tearDown(){
        this.borrarDatos();
    }
    
     public void borrarDatos(){
        pedr.deleteAll();
        pr.deleteAll();
        sr.deleteAll();
        fr.deleteAll();
        pcr.deleteAll();
        cr.deleteAll();
    }
    @Test
    /*
    PRE :  El pedido ha sido enviado y procesado en la sucursal y está listo para ser notificado al cliente
    */
    public void testNotificarClientePedidoListo() throws OperationFailedException{
        //Actualizar estado del pedido a listo
        int idPedido = pedr.findOne(1).getIdPedidos();
        
        lp.notificarPedidoListo(idPedido);
        
        //Verificar estado del pedido
        Pedido p =  pedr.findOne(idPedido);
        assertEquals(true ,  p.isNotificadoAcliente());
        assertEquals(EstadosPedido.NOTIFICADOACLIENTE ,  p.getEstadoPedido());
    }
}
