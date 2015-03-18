package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.components.InformacionCompra;
import edu.eci.cosw.proyecto_eff.logic.LogicaPago;
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
 * @author fercho
 */
@RestController
@RequestMapping("/pagos")
public class PagosRestController {
    
    @Autowired
    LogicaPago lp;
    
    @RequestMapping(value="/{correo}",method = RequestMethod.POST)   
    public ResponseEntity<?> persist(@PathVariable String Correo,@RequestBody InformacionCompra ic) throws OperationFailedException {
        boolean ok = lp.registrarPago(ic, Correo);
        if(!ok){
            throw new OperationFailedException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
     }
    
}
