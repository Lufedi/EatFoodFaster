/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eff.main;

import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Pago;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PedidoProducto;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Pipe
 */
public class IndexarDatos {
    
    
    public static void main(String[] args){
         Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
        configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        
        
        Session session=sessionFactory.openSession();        
        Transaction tx=session.beginTransaction();

        //TRANSACCION CON HIBERNATE

    
           //realizar operación de persistencia
        PlazoletaComida plazoletaComida;
        Franquicia franquicia;
        Sucursal sucursal;
        Categoria categoria;
        
        //C.C Santa fe 
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá")
                , 1, 1, 1, 'W', 1, 1, 1, 'N',1);
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
                , 2, 2, 2, 'N', 2, 2, 2, 'W',2);
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
        Producto burro2  = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
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
                , 3, 3,3, 'S', 3, 3, 3, 'W',3);
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
        
        
        /* Cliente jenni =  new  Cliente("jennibarajas@gmail.com" ,  "comunismo"  , "Jenni" , "Barajas" , "321505481");
        session.save(jenni);
        PedidoProducto pedidoProducto;
        Producto burro1 = (Producto)session.load(Producto.class, new ProductoId("1" ,2 ));
        Pedido pedido = new Pedido(jenni, false, false, "en espera");
        session.save(pedido);
        pedidoProducto = new PedidoProducto(pedido, burro1);
        session.save(pedidoProducto);
        Producto burro2 = (Producto)session.load(Producto.class, new ProductoId("1" ,2 ));
        pedidoProducto = new PedidoProducto(pedido, burro2);
        session.save(pedidoProducto);
        Producto burro3 = (Producto)session.load(Producto.class, new ProductoId("1" ,2 ));
        pedidoProducto = new PedidoProducto(pedido, burro3);
        session.save(pedidoProducto);
        */
       
        
        
        tx.commit();
        session.close();
    }
    
}
