/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.logic.LogicaPedido;
import edu.eci.cosw.proyecto_eff.logic.LogicaSucursal;
import edu.eci.cosw.proyecto_eff.model.Estado;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FabianAndres
 */
@RestController
@RequestMapping("/pedidos")
public class PedidosRestController {
    
    @Autowired
    LogicaPedido lp;
    
    @Autowired
    LogicaSucursal ls;
    
    @RequestMapping(value="/sucursal/{idSucursal}",method = RequestMethod.GET)        
    public List<Pedido> consultarPedidosDeSucursal(@PathVariable int idSucursal) throws ResourceNotFoundException  {       
        return lp.consultarPedidosPorSucursal(idSucursal);
   
    }
    @RequestMapping(value="/sucursal/sinNotificar/{idSucursal}",method = RequestMethod.GET)        
    public List<Pedido> consultarPedidosDeSucursalSinNotificar(@PathVariable int idSucursal) throws ResourceNotFoundException  {       
        return lp.consultarPedidosPorSucursalSinNotificar(idSucursal);
   
    }
    @RequestMapping(value="/sucursal/notificados/{idSucursal}",method = RequestMethod.GET)        
    public List<Pedido> consultarPedidosDeSucursalNotificados(@PathVariable int idSucursal) throws ResourceNotFoundException  {       
        return lp.consultarPedidosPorSucursalNotificados(idSucursal);
    }
   
    @RequestMapping(value="/",method = RequestMethod.GET)        
    public List<Pedido> consultarPedidos() throws ResourceNotFoundException  {       
        return lp.consultarPedidos();   
    }
    
    @RequestMapping(value="/{idPedido}",method = RequestMethod.GET)        
    public Pedido consultarPedidosPorId(@PathVariable int idPedido) throws ResourceNotFoundException  {       
        return lp.consultarPedidoPorId(idPedido);  
    }
    
    @RequestMapping(value="/estado/{idPedido}" , method = RequestMethod.GET)
    public Estado consultarEstadoPedido(@PathVariable int idPedido) throws ResourceNotFoundException  {       
        return  lp.consultarEstadoPedido(idPedido);  
    }
   
    
    
}
