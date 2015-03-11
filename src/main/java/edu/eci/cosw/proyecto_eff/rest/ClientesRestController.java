/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.logic.LogicaCliente;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jennibarajas
 */

@RestController
@RequestMapping("/clientes")
public class ClientesRestController {
    @Autowired
    LogicaCliente lc;
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public void registrarCliente (@PathVariable Cliente c) throws OperationFailedException{
        lc.registrarCliente(c);
    }
    
    @RequestMapping(value="/{correo}",method = RequestMethod.GET) 
    public Cliente consultarCliente(@PathVariable String correo)throws ResourceNotFoundException{
        return lc.consultarCliente(correo);    
    }
    
    @RequestMapping(value="/",method = RequestMethod.PUT)
    public void modificarCliente (@PathVariable Cliente c) throws OperationFailedException{
        lc.modificarCliente(c);
    }
}
