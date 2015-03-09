/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FabianAndres
 */
@Service
public class LogicaPedido {
    
    @Autowired
    PedidoRepository pr;

    /*public List<Pedido> consultarPedidosCliente(Cliente c) {
       return pr.search(c.getCorreoCliente());
    }*/
    
}
