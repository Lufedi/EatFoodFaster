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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
   
    
    @RequestMapping(value="/{franquicia}/{producto}",method = RequestMethod.GET)        
    public List<Producto> consultarPedidosPorSucursal(@PathVariable String franquicia ,  @PathVariable String producto) throws ResourceNotFoundException  {       
        return lp.consultarProductoPorFranquicia(franquicia, producto);
    }
    
    @RequestMapping(value="/{nombre}",method = RequestMethod.GET)        
    public List<Producto> consultarPedidosPorNombre(@PathVariable String nombre) throws ResourceNotFoundException  {       
        return lp.consultarProductosPorNombre(nombre);
    }
    
    @RequestMapping(value="/id/{id}",method = RequestMethod.GET)        
    public Pedido consultarPedidosPorId(@PathVariable int id) throws ResourceNotFoundException  {       
        return lp.getProducto(id);
    }
    
    @RequestMapping(value="/{centrocomercial}/{ciudad}/{sucursal}/{nombre}",method = RequestMethod.GET)        
    public List<Producto> consultarPedidosPorNombreEnPlazoletaComidas(
            @PathVariable String  centrocomercial, 
            @PathVariable String ciudad ,
            @PathVariable String  nombre) throws ResourceNotFoundException  {       
       return lp.consultarProductosPorNombre(centrocomercial  , ciudad  ,   nombre);
    }
    
  /*@RequestMapping(value="/echo/{input}",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaX(@PathVariable String input) {       
        return new ResponseEntity<>("REST API working. Echo:"+input,HttpStatus.ACCEPTED);
    }*/
}
