/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Estado;
import edu.eci.cosw.proyecto_eff.model.EstadosPedido;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import edu.eci.cosw.proyecto_eff.rest.OperationFailedException;
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

    public List<Pedido> consultarPedidos() {
       //return pr.search(c.getCorreoCliente());
        return (List<Pedido>)pr.findAll();
    }
    
    public Pedido consultarPedidoPorId(int id) {
       //return pr.search(c.getCorreoCliente());
        return pr.findOne(id);
    }
    
    
    public List<Pedido> consultarPedidosPorSucursal(int idSucursal) {
       //return pr.search(c.getCorreoCliente());
        return pr.searchBySucursal(idSucursal);
        //return null;
    }
    public List<Pedido> consultarPedidosPorSucursalSinNotificar(int idSucursal) {
       //return pr.search(c.getCorreoCliente());
        return pr.searchBySucursalSinNotificar(idSucursal);
        //return null;
    }
    public List<Pedido> consultarPedidosPorSucursalNotificados(int idSucursal) {
       //return pr.search(c.getCorreoCliente());
        return pr.searchBySucursalNotificados(idSucursal);
        //return null;
    }

    public Estado consultarEstadoPedido(int idPedido) {
        Pedido p = pr.findOne(idPedido);
        return new Estado((p != null)?p.getEstadoPedido():"null");
        
    }


    
    
}
