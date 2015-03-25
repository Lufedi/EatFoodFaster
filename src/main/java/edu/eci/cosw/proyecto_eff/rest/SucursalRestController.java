/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.logic.LogicaSucursal;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    
     @RequestMapping(value="/notificacion/pedidolisto/",method = RequestMethod.PUT   )        
    public ResponseEntity<?> notificarPedidoListo(@PathVariable Pedido p) throws ResourceNotFoundException  {       
        try{ 
          ls.notificarPedidoListo(p);  
        }catch(OperationFailedException e){
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
         return new ResponseEntity<>(HttpStatus.ACCEPTED);
        
    }
    
}
