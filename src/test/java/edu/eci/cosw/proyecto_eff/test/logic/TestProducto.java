/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author FabianAndres
 */
public class TestProducto {
    
    
    @Before
    public void datos(){
        /*
         //realizar operación de persistencia
        //se agregan los datos de persistencia para las pruebas
        PlazoletaComida plazoletaComida;
        Franquicia franquicia;
        Sucursal sucursal;
        Producto producto;
        Categoria categoria;
        
        //C.C Santa fe 
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá")
                , 1, 1, 1, 'W', 1, 1, 1, 'N');
        session.save(plazoletaComida);
        franquicia = new  Franquicia("Mc Donalds", new Float(1.3));
        session.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "1111");
        session.save(sucursal);
        categoria = new Categoria("Perro Caliente");
        session.save(categoria);
        Producto perro1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 10000, true, "perro sencillo en combo", 0);
        session.save(perro1);
        Producto perro2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "perro ranchero en combo", 0);
        session.save(perro2);
        Producto perro3 = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 18000, true, "perro doble  salchicha alemana combo", new Float(2.0));
        session.save(perro3);
        
        
        //C.C BIMA
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. BIMA", "Bogotá")
                , 2, 2, 2, 'N', 2, 2, 2, 'W');
        session.save(plazoletaComida);
        franquicia = new  Franquicia("Taco bell", new Float(1.3));
        session.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "2222");
        session.save(sucursal);
        categoria = new Categoria("Burrito");
        session.save(categoria);
        Producto burro1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 9000, false, "burrito mixto grande", 0);
        session.save(burro1);
        Producto burro2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 13000, true, "burrito mixto grande combo", 0);
        session.save(burro2);
        Producto burro3 = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "burrito pollo combo especial", new Float(2.0));
        session.save(burro3);
        franquicia = new  Franquicia("Q-bano", new Float(1.3));
        session.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "3333");
        session.save(sucursal);
        categoria = new Categoria("Sandwich");
        session.save(categoria);
        Producto sandw1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 7000, false, "Sandwich de atun sencillo", 0);
        session.save(sandw1);
        Producto sandw2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 11000, true, "Sandwich de atun en combo", 0);
        session.save(sandw2);
        Producto sandw3 = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 8500, false, "Sandwich BBQ ", new Float(2.0));
        session.save(sandw3);
        
        
         //C.C Unicentro
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Unicentro", "Bogotá")
                , 3, 3,3, 'S', 3, 3, 3, 'W');
        session.save(plazoletaComida);
        franquicia = new  Franquicia("Mr. Lee", new Float(1.3));
        session.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "4444");
        session.save(sucursal);
        categoria = new Categoria("Sushi");
        session.save(categoria);
        Producto sushi1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 24000, true, "Ojo de tigre en combo", 0);
        session.save(sushi1);
        Producto sushi2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 16000, true, "Clasico x 12 rollos ", 0);
        session.save(sushi2);
        franquicia = new  Franquicia("See", new Float(1.3));
        session.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "5555");
        session.save(sucursal);
        categoria = new Categoria("Cazuela de mariscos");
        session.save(categoria);
        Producto cazuela1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 32000, true, "Cazuela de mariscos en combo especial", 0);
        session.save(cazuela1);
        Producto cazuela2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 8500, true, "Cazuela pequeña en  combo",0);
        session.save(cazuela2);
        
        franquicia = new  Franquicia("Jenos pizza", new Float(1.3));
        session.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "6666");
        session.save(sucursal);
        categoria = new Categoria("Pizza");
        session.save(categoria);
        Producto pizza1 = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "Pizza Napolitana en combo", 0);
        session.save(pizza1);
        Producto pizza2 = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "Pizza mexicana en combo",0);
        session.save(pizza2);
        
     
        
        
        Cliente fercho = new Cliente("ferchogarc010@gmail.com", "67890", "fercho", "garcia", "3103078766");
        session.save(fercho);
        Cliente felipe = new Cliente("pipexir@gmail.com", "themercenary", "Felipe", "Diaz", "3193387106");
        session.save(felipe);
        Cliente jenni =  new  Cliente("jennibarajas@gmail.com" ,  "comunismo"  , "Jenni" , "Barajas" , "321505481");
        session.save(jenni);
        
        PedidoProducto pedidoProducto;
        Pedido pedido = new Pedido(fercho, false, false, "en espera");
        session.save(pedido);
         pedidoProducto = new PedidoProducto(pedido, pizza1);
        session.save(pedidoProducto);
         pedidoProducto = new PedidoProducto(pedido, pizza2);
        session.save(pedidoProducto);
        
        
        pedido = new Pedido(felipe, false, false, "en espera");
        session.save(pedido);
        pedidoProducto = new PedidoProducto(pedido, cazuela1);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, cazuela2);
        session.save(pedidoProducto);
        
        
        pedido = new Pedido(felipe, false, false, "en espera");
        session.save(pedido);
        pedidoProducto = new PedidoProducto(pedido, sushi1);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, sushi2);
        session.save(pedidoProducto);
        
        pedido = new Pedido(fercho, false, false, "en espera");
        session.save(pedido);
        pedidoProducto = new PedidoProducto(pedido, sandw1);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, sandw2);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, sandw3);
        session.save(pedidoProducto);
        
        pedido = new Pedido(fercho, false, false, "en espera");
        session.save(pedido);
        pedidoProducto = new PedidoProducto(pedido, perro1);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, perro2);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, perro3);
        session.save(pedidoProducto);

        pedido = new Pedido(jenni, false, false, "en espera");
        session.save(pedido);
        pedidoProducto = new PedidoProducto(pedido, burro1);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, burro2);
        session.save(pedidoProducto);
        pedidoProducto = new PedidoProducto(pedido, burro3);
        session.save(pedidoProducto);
        
        
        
        */
    }
    @Test
    public void testConsultarProductosPorFranquicia(){
        assertEquals(true,true);
    }
    
    @Test
    public void testConsultarProductosPorNombre(){
        assertEquals(true,true);
    }
    
    @Test
    public void consultarProductoPorSucursal(){
        
    }
}
