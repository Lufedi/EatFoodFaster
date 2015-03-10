/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class LogicaProducto {
    
    /**
     * @Obj: consultar los productos por suscursal
     * @param s: Sucursal de una franquicia
     * @return : listado de productos de una sucursal
     */
    public List<Producto> consultarProductosPorSucursal(int sucursal){
        return null;
    }
    
    /**
     * @Obj: consultar los productos por el nombre
     * @param s: nombre del producto a buscar
     * @return listado de productos de diferentes franquicias cuyo nombre coincide con la palabra usada 
     */
    public List<Producto> consultarProductosPorNombre(String s){
        return null;
    }
    
    
     
    /**
     * @Obj: Consultar productos por el id
     * @param id: id del producto a buscar
     * @return Pedido que se identifica con el id
     * @Throws ResourceNotFoundException
     */
    public Pedido consultarProductoPorId(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

     /**
     * @Obj: Consultar productos por el nombre en un centro comercial
     * @param centroComercial :  id centro comercia l , nombre :  cadena del producto a buscar
     * @return Pedido que se identifica con el id
     * @Throws ResourceNotFoundException
     */
    public List<Producto> consultarProductosPorNombre(int plazoletaComidas, String nombre) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    
     
    public List<Producto> consultarProductosPorNombre(
            String centrocomercial  , 
            String ciudad ,
            int idSucursal , 
            String nombre ){
        return null;
    }
}
