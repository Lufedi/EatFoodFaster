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
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fercho
 * 
 */
public class Test1 {
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
     * Registro de un pedido que involucre platos de diferentes franquicias dentro de un centro comercial.
     */
    @Test
    public void sampleTest(){
        Transaction tx=session.beginTransaction();
        PlazoletaComida plazoleta = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá"), 1, 1, 1, 'W', 1, 1, 1, 'N',1);
        session.save(plazoleta);
        
        Franquicia franquicia = new  Franquicia("Burguer King", new Float(12.5));
        session.save(franquicia);
        Sucursal sucursal = new Sucursal(franquicia, plazoleta, "12345");
        session.save(sucursal);
        Categoria categoria = new Categoria("hamburguesa");
        session.save(categoria);
        Producto producto = new Producto(new ProductoId("45", sucursal.getIdSucursales()), categoria, sucursal, 10000, true, "hamburguesa doble carne y papas", 0);
        session.save(producto);
        
        Franquicia franquicia2 = new  Franquicia("Hamburguesa el corral", new Float(15));
        session.save(franquicia2);
        Sucursal sucursal2 = new Sucursal(franquicia2, plazoleta, "12345");
        session.save(sucursal2);
        Producto producto2 = new Producto(new ProductoId("45", sucursal2.getIdSucursales()), categoria, sucursal2, 15000, false, "hamburguesa doble carne", 10);
        session.save(producto2);
        
        Cliente cliente = new Cliente("ferchogarc010@gmail.com", "67890", "fercho", "garcia", "3103078766");
        session.save(cliente);
        Pedido pedido = new Pedido(cliente, false, false, "en espera");
        session.save(pedido);
        PedidoProducto pedidoProducto = new PedidoProducto(pedido, producto);
        session.save(pedidoProducto);
        PedidoProducto pedidoProducto2 = new PedidoProducto(pedido, producto2);
        session.save(pedidoProducto2);
        
        Query q = session.createQuery("select pp.productos from Pedido as p inner join p.pedidosProductoses as pp where p.clientes.correoCliente= :correo");
        q.setString("correo", cliente.getCorreoCliente());
        List<Producto> l = q.list();
        
        assertEquals("el pedido no fue registrado exitosamente",l.size(), 2);
        tx.commit();        
    }
    
    
    
    
}
