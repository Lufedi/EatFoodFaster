/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.components.CompraEvaluator;
import edu.eci.cosw.proyecto_eff.components.InformacionCompra;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PedidoProducto;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
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
    private PedidoRepository pr;
    
    @Autowired
    private ClienteRepository cr;
    
    @Autowired
    private CompraEvaluator ce;
    
    /**
     * @Obj : guardar un pedido de un usuario
     * @param p: Pedido que incluye los productos del carrito de compras  
     */
    public void registrarPago(InformacionCompra ic, String cliente){
        /*Cliente c = cr.findOne(cliente);
        ArrayList<Producto> productos = ic.getProductos();
        Hashtable<String, Pedido> pedidos = new Hashtable<>();
        for (int i = 0; i < productos.size(); i++) {
            Sucursal s = productos.get(i).getSucursales();
            Pedido p = null;
            String key = s.getFranquicias().getIdFranquicia()+
                    s.getPlazoletaComidas().getId().getIdPlazoletaComidas()+
                    s.getPlazoletaComidas().getId().getCiudad();
            if(!pedidos.containsKey(key)){
                p = new Pedido(c, true, false, "no enviado");
            }else{
                p = pedidos.get(key);
            }
            p.getPedidosProductoses().add(new PedidoProducto(p, productos.get(i)));
            pedidos.put(key, p);
        }*/
    }
    
    
    private int getTotalPedido(Pedido p){
        int res=0;
        return res;
    }
    
    
    
    
    
}
