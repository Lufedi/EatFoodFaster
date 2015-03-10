/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.logic.LogicaPedido;
import edu.eci.cosw.proyecto_eff.logic.LogicaProducto;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pipe
 */
@RestController
@RequestMapping("/productos")
public class ProductosRestController {
    
    @Autowired
    LogicaProducto lp;
    
    @RequestMapping(value="/{sucursal}",method = RequestMethod.GET)        
    public List<Producto> consultarPedidosPorSucursal(@PathVariable int sucursal) throws ResourceNotFoundException  {       
        return lp.consultarProductosPorSucursal(sucursal);
        
        
    }
    
    @RequestMapping(value="/{nombre}",method = RequestMethod.GET)        
    public List<Producto> consultarPedidosPorNombre(@PathVariable String nombre) throws ResourceNotFoundException  {       
        return lp.consultarProductosPorNombre(nombre);
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)        
    public Pedido consultarPedidosPorId(@PathVariable int id) throws ResourceNotFoundException  {       
        return lp.consultarProductoPorId(id);
    }
    
    
}
