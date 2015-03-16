/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.test.logic;

import edu.eci.cosw.proyecto_eff.logic.LogicaProducto;
import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.CategoriaRepository;
import edu.eci.cosw.proyecto_eff.persistance.FranquiciaRepository;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import edu.eci.cosw.proyecto_eff.persistance.PlazoletaComidaRepository;
import edu.eci.cosw.proyecto_eff.persistance.ProductoRepository;
import edu.eci.cosw.proyecto_eff.persistance.SucursalRepository;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
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
public class TestProducto {
    
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
    
    
    
    
     @Before  
    public void setUp()
    {
 
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
        producto = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 10000, true, "perro sencillo en combo", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "perro ranchero en combo", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 18000, true, "perro doble  salchicha alemana combo", new Float(2.0));
        pr.save(producto);
        
        
        //C.C BIMA
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. BIMA", "Bogotá")
                , 2, 2, 2, 'N', 2, 2, 2, 'W'  , 8);
        pcr.save(plazoletaComida);
        franquicia = new  Franquicia("Taco bell", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "2222");
        sr.save(sucursal);
        categoria = new Categoria("Burrito");
        cr.save(categoria);
        producto = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 9000, false, "burrito mixto grande", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 13000, true, "burrito mixto grande combo", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "burrito pollo combo especial", new Float(2.0));
        pr.save(producto);
        franquicia = new  Franquicia("Q-bano", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "3333");
        sr.save(sucursal);
        categoria = new Categoria("Sandwich");
        cr.save(categoria);
        producto = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 7000, false, "Sandwich de atun sencillo", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 11000, true, "Sandwich de atun en combo", 0);
        pr.save(producto);
       producto = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 8500, false, "Sandwich BBQ ", new Float(2.0));
        pr.save(producto);
        
        
         //C.C Unicentro
        plazoletaComida = new PlazoletaComida(new PlazoletaComidaId("C.C. Unicentro", "Bogotá")
                , 3, 3,3, 'S', 3, 3, 3, 'W' , 9);
        pcr.save(plazoletaComida);
        franquicia = new  Franquicia("Mr. Lee", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "4444");
        sr.save(sucursal);
        categoria = new Categoria("Sushi");
        cr.save(categoria);
        producto = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 24000, true, "Ojo de tigre en combo", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 16000, true, "Clasico x 12 rollos ", 0);
        pr.save(producto);
        franquicia = new  Franquicia("See", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "5555");
        sr.save(sucursal);
        categoria = new Categoria("Cazuela de mariscos");
        cr.save(categoria);
        producto = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 32000, true, "Cazuela de mariscos en combo especial", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 8500, true, "Cazuela pequeña en  combo",0);
        pr.save(producto);
        
        franquicia = new  Franquicia("Jenos pizza", new Float(1.3));
        fr.save(franquicia);
        sucursal = new Sucursal(franquicia, plazoletaComida, "6666");
        sr.save(sucursal);
        categoria = new Categoria("Pizza");
        cr.save(categoria);
        producto = new Producto(new ProductoId("1", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "Pizza Napolitana en combo", 0);
        pr.save(producto);
        producto = new Producto(new ProductoId("2", sucursal.getIdSucursales()),
                categoria, sucursal, 15000, true, "Pizza mexicana en combo",0);
        pr.save(producto);
        producto = new Producto(new ProductoId("3", sucursal.getIdSucursales()),
                categoria, sucursal, 7800, true, "Sandwich italiano",0);
        pr.save(producto);
        
    
    }
    
    /*
    Obj :  Borra rla base de datos antes de cada test
    pre: están definidos los repositorios
    */
    @After
    public void tearDown(){
        pedr.deleteAll();
        pr.deleteAll();
        sr.deleteAll();
        fr.deleteAll();
        pcr.deleteAll();
        cr.deleteAll();
    }
    
   
    
    @Test
    public void testConsultarProductosPorNombreGlobal(){
       
       List<Producto> resultSet =  productoCtrl.consultarProductosPorNombre("Sandwich");
       for(Producto c :  resultSet)
           System.out.println(c.getDescripcion());
        assertEquals("Cantidad de personas con el apellido reportadas" , 4 ,resultSet.size() );
        
    }
    
    @Test
    public void testConsularProductosPorNombreEnPlazoletaDeComidas(){
        //realizar operación de persistencia
        //se agregan los datos de persistencia para las pruebas
      
       String   productoId =  "perro";
        String   cc  = "C.C. Santa fe";
        String   ciudad = "Bogotá";
        List<Producto> resultSet = 
                productoCtrl.consultarProductosPorPlazoletaDeComidas(
                        cc, ciudad, productoId);
        assertEquals(3 , resultSet.size());
    } 
    
     @Test
    public void testConsultarProductosPorFranquicia(){
        
        String   productoId =  "Cazuela";
        String   franquicia =  "See";
        List<Producto> resultSet = 
            productoCtrl.consultarProductoPorFranquicia(franquicia, productoId);
        assertEquals(2 , resultSet.size());
    }
}
