/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.util.List;

/**
 *
 * @author fercho
 */
public class LogicaProducto {
    
    /**
     * @Obj: consultar los productos por suscursal
     * @param s: Sucursal de una franquicia
     * @return : listado de productos de una sucursal
     */
    public List<Producto> consultarProductosPorSucursal(Sucursal s){
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
    
}
