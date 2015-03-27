/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.logic.LogicaSucursal;
import edu.eci.cosw.proyecto_eff.model.Estado;
import edu.eci.cosw.proyecto_eff.model.EstadosPedido;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.Producto;
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
 * @author luisfediaz
 */
@RestController
@RequestMapping("/notificacion")
public class SucursalRestController {
    @Autowired
    LogicaSucursal ls;
    //http://www.leveluplunch.com/java/tutorials/014-post-json-to-spring-rest-webservice/
     @RequestMapping(value="/pedido/listo/{id}",method = RequestMethod.PUT   )        
    public ResponseEntity<?> notificarPedidoListo(@PathVariable("id") int id  , 
            @RequestBody Estado estado) throws ResourceNotFoundException  {       
        try{ 
          ls.notificarPedidoListo(id  , estado);  
        }catch(OperationFailedException e){
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
         return new ResponseEntity<>("REST API "+ estado + "id " + id , HttpStatus.ACCEPTED);
        
    }
    
    @RequestMapping(value="/pedido/sucursal/{id}",method = RequestMethod.PUT)        
    public ResponseEntity<?> notificarPedidoASucursal(@PathVariable("id") int id  , 
            @RequestBody Estado estado) throws ResourceNotFoundException  {       
         
        ls.recibirNotificacion(id,estado);
        return new ResponseEntity<>("REST API "+ estado + "id " + id , HttpStatus.ACCEPTED);
    }
    //
    
}
  
