/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.EstadosPedido;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class LogicaSucursal {
    
    @Autowired
    PedidoRepository pr;
    
    /**
     * @Obj: Notificar al cliente que el pedido de una sucursal esta listo
     * @param p: Pedido del cliente
     * @param s: Sucursal de la franquicia donde se notifica
     * @return : true si la notificación fue enviada exitosamente, false en caso contrario
     */
    public boolean notificarCliente(Pedido p, Sucursal s){
        return false;
    }
    
    /**
     * 
     * @param p : pedido realizado por el cliente
     */
    public void recibirNotificacion(Pedido p){
        //Debo retornal algo
        p.setEnviadoAsucursal(true);
        p.setEstadoPedido(EstadosPedido.RECIBIDOENSUCURSAL);
        pr.save(p);
    }
    
}
