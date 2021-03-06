/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class LogicaProducto {
    
    
    @Autowired
    ProductoRepository pr;
    
    /**
     * @Obj: consultar los productos por el nombre
     * @param s: nombre del producto a buscar
     * @return listado de productos de diferentes franquicias cuyo nombre coincide con la palabra usada 
     */
    public List<Producto> consultarProductosPorNombre(String nombre){
       return pr.search("%"+nombre+"%");
        //return pr.searchP();
    }
    
 
     /**
     * @Obj: Consultar productos por el nombre en una plazoleta de comidas 
     * @param centroComercial :  id centro comercia l , nombre :  cadena del producto a buscar
     * @return Pedido que se identifica con el id
     * @Throws ResourceNotFoundException
     */
    public List<Producto> consultarProductosPorPlazoletaDeComidas(String centrocomercial ,  String ciudad, String nombre) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return pr.searchPorPlazoletaComidas("%"+nombre+"%", ciudad, centrocomercial);
    }
    
    /**
     * Obj: Consultar producto por nombre en una franquicia especifica 
     * @param franquicia  franquicia de la que se quiere consultar
     * @param nombre nombre del producto a buscar
     * @return lista de productos que coinciden con el nombre en la franquicia destino
     */
    public List<Producto> consultarProductoPorFranquicia( String franquicia  ,  String nombre){
        return  pr.searchPorFranquicia("%"+nombre+"%", franquicia);
    }
    
    
    
    /**
     * Localizar producto por descripcion
     */
    public Producto obtenerProducto(String descripcion){
        Producto p =  null;
        ArrayList<Producto> productos =  (ArrayList<Producto>)pr.findAll();
        for(Producto prod : productos){
            if(prod.getDescripcion().equals(descripcion))
                p =  prod;             
        }
        return p;
    }
}
