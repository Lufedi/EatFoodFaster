/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EFF.test;

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
import java.util.Calendar;
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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jennibarajas
 */
public class Test3y5 {
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
     Realización de las pruebas,mirando la persistencia de estas en la base de datos. 
     */
    @Test
    public void sampleTest(){
        Transaction tx=session.beginTransaction();
        
        // Creación de datos para realizar la consulta
        PlazoletaComida plazoleta = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá"), 1, 1, 1, 'W', 1, 1, 1, 'N',1);
        session.save(plazoleta);
        
        Franquicia franquicia = new  Franquicia("Burguer King", new Float(12.5));
        session.save(franquicia);
        Sucursal sucursal = new Sucursal(franquicia, plazoleta, "12345");
        session.save(sucursal);
        Categoria categoria = new Categoria("hamburguesa");
        session.save(categoria);
        Categoria categoria2 = new Categoria("Combo");
        session.save(categoria2);
        Categoria categoria3 = new Categoria("helado");
        session.save(categoria3);
        Producto producto = new Producto(new ProductoId("45", sucursal.getIdSucursales()), categoria, sucursal, 12500, true, "hamburguesa doble carne y papas", 0);
        session.save(producto);
        Producto producto2 = new Producto(new ProductoId("46", sucursal.getIdSucursales()), categoria2, sucursal, 10000, true, "combo de 10000", 0);
        session.save(producto2);
        Producto producto3 = new Producto(new ProductoId("47", sucursal.getIdSucursales()), categoria3, sucursal, 8000, true, "pastel helado", 0);
        session.save(producto3);
        
        Franquicia franquicia2 = new  Franquicia("Hamburguesas el corral", new Float(15));
        session.save(franquicia2);
        Sucursal sucursal2 = new Sucursal(franquicia2, plazoleta, "12345");
        session.save(sucursal2);
        Producto p1 = new Producto(new ProductoId("C45", sucursal2.getIdSucursales()), categoria, sucursal2, 12000, false, "Corralisima", 0);
        session.save(p1);
        Producto p2 = new Producto(new ProductoId("C46", sucursal2.getIdSucursales()), categoria, sucursal2, 15000, false, "Corral doble carne", 0);
        session.save(p2);
        Producto p3 = new Producto(new ProductoId("C47", sucursal2.getIdSucursales()), categoria, sucursal2, 15000, false, "todoterreno", 0);
        session.save(p3);
        
         //Genera Cliente 
        
        Cliente cliente = new Cliente("olivajx@hotmail.com", "sora", "Jennifer", "Barajas", "3102303936");
        session.save(cliente);
        Cliente cliente2 = new Cliente("mariaOliveros@gmail.com", "1235", "Maria Teresa", "Oliveros", "3118756238");
        session.save(cliente);
        Cliente cliente3 = new Cliente("Euclides@yahoo.com", "orion12", "Euclides", "Rey", "3152362993");
        session.save(cliente);
        
        
       //Genera Pedidos
        
        Pedido pedido = new Pedido(cliente3, false, false, "en espera");
        session.save(pedido);
        Pedido pedido2 = new Pedido(cliente, false, false, "en espera");
        session.save(pedido2);
        Pedido pedido3= new Pedido(cliente2, false, false, "en espera");
        session.save(pedido3);
        Pedido pedido4 = new Pedido(cliente3, false, false, "en espera");
        session.save(pedido4);
        Pedido pedido5 = new Pedido(cliente2, false, false, "en espera");
        session.save(pedido5);
        Pedido pedido6 = new Pedido(cliente, false, false, "en espera");
        session.save(pedido6);
        
        
        PedidoProducto pedidoProducto = new PedidoProducto(pedido, producto);
        session.save(pedidoProducto);
        PedidoProducto pedidoProducto2 = new PedidoProducto(pedido, producto2);
        session.save(pedidoProducto2);
        
        PedidoProducto pedidoProducto3 = new PedidoProducto(pedido2, p2);
        session.save(pedidoProducto3);
        PedidoProducto pedidoProducto4 = new PedidoProducto(pedido2, p3);
        session.save(pedidoProducto4);
        
        PedidoProducto pedidoProducto5 = new PedidoProducto(pedido3, producto3);
        session.save(pedidoProducto5);
        PedidoProducto pedidoProducto6 = new PedidoProducto(pedido3, p1);
        session.save(pedidoProducto6);
        
        PedidoProducto pedidoProducto7 = new PedidoProducto(pedido4, producto2);
        session.save(pedidoProducto7);
        PedidoProducto pedidoProducto8 = new PedidoProducto(pedido4, p2);
        session.save(pedidoProducto8);
        
        PedidoProducto pedidoProducto9= new PedidoProducto(pedido5, producto);
        session.save(pedidoProducto9);
        PedidoProducto pedidoProducto10 = new PedidoProducto(pedido5, producto);
        session.save(pedidoProducto10);
        
        PedidoProducto pedidoProducto11= new PedidoProducto(pedido6, p3);
        session.save(pedidoProducto11);
        PedidoProducto pedidoProducto12= new PedidoProducto(pedido6, p3);
        session.save(pedidoProducto12);
        
       
        //Generate a date 
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2015, Calendar.JANUARY, 9, 7, 11, 11); 
        
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2015, Calendar.JANUARY, 11, 3, 06, 11);
        
        Calendar cal3 = Calendar.getInstance();
        cal3.set(2015, Calendar.JANUARY, 10, 11, 10, 12);
        
        Calendar cal4 = Calendar.getInstance();
        cal4.set(2015, Calendar.JANUARY, 10, 9, 12, 55);
        
        Calendar cal5 = Calendar.getInstance();
        cal5.set(2015, Calendar.JANUARY, 11, 8, 30, 12); 
        
        Calendar cal6 = Calendar.getInstance();
        cal6.set(2015, Calendar.JANUARY, 9, 12, 00, 23); 
        
        //Registro de Pagos 
        
        Pago pago= new Pago(pedido, new Date (cal1.getTimeInMillis()), 22500, "Visa");
        session.save(pago);
        Pago pago2 = new Pago(pedido2, new Date(cal2.getTimeInMillis()),27000 , "Mastercard");
        session.save(pago2);
        Pago pago3= new Pago(pedido3, new Date (cal3.getTimeInMillis()), 20000, "Debito Maestro");
        session.save(pago3);
        Pago pago4= new Pago(pedido4, new Date (cal4.getTimeInMillis()), 25000, "PayPal");
        session.save(pago4);
        Pago pago5= new Pago(pedido5, new Date (cal5.getTimeInMillis()), 25000, "Visa");
        session.save(pago5);
        Pago pago6= new Pago(pedido6, new Date (cal6.getTimeInMillis()), 30000, "DinersClub");
        session.save(pago6);
       
         //Cálculo del valor total pagado a una franquicia en un intervalo de fechas.
        Query q= session.createQuery("select sum(p.monto) from Pago p inner join p.pedidos as ped inner join ped.pedidosProductoses as pp inner join pp.productos as prod inner join prod.sucursales as s inner join s.franquicias as f Where '2014-01-09'<=p.fechaPago AND p.fechaPago<= '2016-01-11' AND f.idFranquicia='Burguer King'");

        List<Double> l = q.list();
        System.out.println(l.toString());
        Assert.assertEquals(l.get(0),new Double(140000.0));
        
        
       //Detalle de las transacciones realizadas con una franquicia en un intervalo de fechas.
        Query q1=session.createQuery("select prod.id, prod.descripcion, p.monto from Pago p inner join p.pedidos as ped inner join ped.pedidosProductoses as pp inner join pp.productos as prod inner join prod.sucursales as s inner join s.franquicias as f Where '2014-01-09'<=p.fechaPago AND p.fechaPago<= '2016-01-11' AND f.idFranquicia='Burguer King'");
        List<Object []> l1= q1.list();
        System.out.println(l1.toString());
        Assert.assertEquals(l1.size(),6);
        
        tx.commit();        
    }
}
