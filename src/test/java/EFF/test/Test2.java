/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EFF.test;


import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
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
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author FabianAndres
 */
public class Test2 {
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
     * El objetivo de esta prueba es que se puede identificar el centro comercial
     * a partir de coordenadas geográficas (grados, minutos, segundos).
     * 
     */
    @Test
    public void segundoTest(){
        Transaction tx=session.beginTransaction();
        PlazoletaComida plazoleta = new PlazoletaComida(new PlazoletaComidaId("C.C. Santa fe", "Bogotá"), 40, 40, 35, 'W', 40, 40, 65, 'N',5);
        session.save(plazoleta);
        
        PlazoletaComida plazoletaAux = identificarPlazoleta(session, 40, 40, 32, 'W', 40, 40, 63, 'N');
        
        Assert.assertEquals(plazoleta, plazoletaAux);
        
        tx.commit();
    }
    
    public PlazoletaComida identificarPlazoleta(Session s, float gradosLon, float minutosLon, 
            float segundosLon, char orientacionLon, float gradosLat, 
            float minutosLat, float segundosLat, char orientacionLat){
        
        Query q = s.createQuery("from PlazoletaComida where gradosLon=:gradosLon AND "
                + "minutosLon=:minutosLon AND segundosLon - radio <=:segundosLon AND "
                + "segundosLon + radio >=:segundosLon AND orientacionLon=:orienteacionLon AND "
                + "gradosLat=:gradosLat AND minutosLat=:minutosLat "
                + "AND segundosLat - radio <=:segundosLat AND segundosLat + radio >=:segundosLat "
                + "AND orientacionLat=:orienteacionLat");
        q.setFloat("gradosLon", gradosLon);
        q.setFloat("minutosLon", minutosLon);
        q.setFloat("segundosLon", segundosLon);
        q.setCharacter("orienteacionLon", orientacionLon);
        
        q.setFloat("gradosLat", gradosLat);
        q.setFloat("minutosLat", minutosLat);
        q.setFloat("segundosLat", segundosLat);
        q.setCharacter("orienteacionLat", orientacionLat);
        List<PlazoletaComida> list = q.list();
        
        return list.get(0);
    
    }
    
    
    
}
