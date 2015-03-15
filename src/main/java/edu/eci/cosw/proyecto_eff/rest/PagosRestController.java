/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.components.InformacionCompra;
import edu.eci.cosw.proyecto_eff.logic.LogicaPago;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Producto;
import java.util.ArrayList;
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
        lp.registrarPago(ic, Correo);
        return new ResponseEntity<>(HttpStatus.CREATED);
     }
    
}
