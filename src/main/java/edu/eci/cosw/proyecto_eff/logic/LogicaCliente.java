/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jennibarajas
 */
@Service
public class LogicaCliente {
    
    @Autowired
    ClienteRepository cr;
    
    /**
     * @Obj : guardar un usuario de tipo cliente
     * @param c: El cliente nuevo que se desea registrar en la aplicacion  
     */
    public void registrarCliente(Cliente c){
       cr.save(c);
    }
   
    /**
     * @Obj : Consulta un cliente en especifico segun su "usuario" que es su correo
     * @param correoCliente: El correo del cliente que se desea buscar
     */
    public Cliente consultarCliente(String correoCliente){
        return cr.findOne(correoCliente);
    }
    
    /**
     * @Obj : Modificar alguna informacion del cliente
     * @param cl: El cliente que se desea modificar, con los camios realizados
     */
    public void modificarCliente(Cliente cl){
        Cliente clienteViejo = cr.findOne(cl.getCorreoCliente());
        
        clienteViejo.setApellido(cl.getApellido());
        clienteViejo.setCelular(cl.getCelular());
        clienteViejo.setContrasena(cl.getContrasena());
        clienteViejo.setCorreoCliente(cl.getCorreoCliente());
        clienteViejo.setNombre(cl.getNombre());
        
        cr.save(clienteViejo);

    }
}
