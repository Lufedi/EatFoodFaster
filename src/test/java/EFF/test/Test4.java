/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EFF.test;

import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PedidoProducto;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/*
Consulta 4 
Ranking de las plazoletas de comida (centros comerciales) de acuerdo a la 
utilización del servicio de pedidos en línea (ordenado por número de pedidos
realizados
Autor : Luis Felipe Diaz Chica
COSW - EFF
*/
/**
 *
 * @author Luis Felipe Diaz Chica
 * 
 */
public class Test4 {
    private SessionFactory sessionFactory;
    private Session session = null;
    
    /**
     * Operaciones que se realizan antes de ejecutar el banco de pruebas.
     * En este caso se crea una misma sesión que será usada en todas las
     * pruebas.
     */
    @Before
    public void setupSession() {
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate-inmemory.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
        configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session=sessionFactory.openSession();
        
    }
        
    /**
     * Operaciones que se realizan cuando finalice la ejecución de las pruebas.
     * En este caso se cierra la sesi_n y la f_brica de sesiones.
     */
    @After
    public void closeResources(){
        session.close();
        sessionFactory.close();
    }
 
    /**
     * El objetivo de esta prueba es que el mapeo permita hacer persistentes
     * productos, y luego consultar de forma consistente los mismos
     * Estado inicial: base de datos vacía.
     * Prueba: La consulta de la sumatora del precio de los productos debe
     * ser consistente con los precios de los productos ingresados.
     * 
     */
    @Test
    public void testRanking(){
        Transaction tx=session.beginTransaction();
        
        //realizar operación de persistencia
        //se agregan los datos de persistencia para las pruebas
        PlazoletaComida plazoletaComida;
        Franquicia franquicia;
        Sucursal sucursal;
        Producto producto;
        Categoria categoria;
        
        //C.C Santa fe 
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá"),
                1, 1, 1, 'W', 1, 1, 1, 'N',1);
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
        
        
        
        
        
        //realizar una consulta
        //Se ejecuta la consulta
        Query q ;
        q=session.createQuery("select  c.id  ,  count( distinct p.pedidos.id) as   k  "
                + "from PedidoProducto  p JOIN p.productos  o JOIN o.sucursales  s "
                + " JOIN s.plazoletaComidas as c  group by c.id order by k DESC");
       
        //Guardar registros en un arreglo de objetos ya que no es un 
        //objeto mapeado lo que se devuelve
        List<Object[]> res=q.list();
        
        
        
        //Se verifica el orden del ranking de mayor numero de pedidos a menor 
        //numero de pedidos 
        assertTrue((Long)res.get(0)[1] >= (Long)res.get(1)[1]);
        assertTrue((Long)res.get(1)[1] >= (Long)res.get(2)[1]);
        //Verificar cantidad de pedidos en cada fila
        assertTrue((Long)res.get(0)[1]==3);
        assertTrue((Long)res.get(1)[1]==2);
        assertTrue((Long)res.get(2)[1]==1);

        tx.commit();        
    }
    
    
    /*
    
select c.plazoletaComidas from PedidoProducto as p JOIN p.producto as o JOIN o.sucursal as s JOIN s.PlazoletaComidas as c

from PedidoProducto  p JOIN p.productos  o JOIN o.sucursales  s  JOIN s.plazoletaComidas as c

select  c.id from PedidoProducto  p JOIN p.productos  o JOIN o.sucursales  s  JOIN s.plazoletaComidas as c

select o.sucursales.plazoletaComidas.id from PedoProducto 




select p.productos.sucursales.plazoletaComidas.id from PedidoProducto  as p group by p.productos.sucursales



select  c.id, count(c) from PedidoProducto  p JOIN p.productos  o JOIN o.sucursales  s  JOIN s.plazoletaComidas as c group by c

    
select  count( distinct p.pedidos.id) , c.id  from PedidoProducto  p JOIN p.productos  o JOIN o.sucursales  s  JOIN s.plazoletaComidas as c  group by c.id 
    
0368930315
29340141073
    */
}
