/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PedidoProducto;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class LogicaPago {
    
    @Autowired
    PedidoRepository pr;
    
    /**
     * @Obj : guardar un pedido de un usuario
     * @param p: Pedido que incluye los productos del carrito de compras  
     */
    public void registrarPago(ArrayList<Producto> productos, Cliente c){
        /*Hashtable<String, Pedido> pedidos = new Hashtable<>();
        for (int i = 0; i < productos.size(); i++) {
            Sucursal s = productos.get(i).getSucursales();
            Pedido p = null;
            String key = s.getFranquicias().getIdFranquicia()+
                    s.getPlazoletaComidas().getId().getIdPlazoletaComidas()+
                    s.getPlazoletaComidas().getId().getCiudad();
            if(!pedidos.containsKey(key)){
                p = new Pedido(c, true, false, "no enviado");
                pr.save(p);
            }else{
                p = pedidos.get(key);
            }
            p.getPedidosProductoses().add(new PedidoProducto(p, productos.get(i)));
            pr.save(p);
            pedidos.put(key, p);
        }*/
    }
    
    
    
    
    
}
