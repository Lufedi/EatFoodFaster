/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.components.CompraEvaluator;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Pago;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PedidoProducto;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
import edu.eci.cosw.proyecto_eff.persistance.PagoRepository;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class LogicaPago {
    
    @Autowired
    private PagoRepository pr;
    
    @Autowired
    private ClienteRepository cr;
    
    @Autowired
    private CompraEvaluator ce;
    
    @Autowired
    private PedidoRepository pr1;
    
    /**
     * @param ic
     * @param cliente
     * @throws edu.eci.cosw.proyecto_eff.rest.OperationFailedException
     * @Obj : guardar un pedido de un usuario  
     */
    public boolean registrarPago(Producto[] prods, String cliente, String metodoPago) {
        Cliente c = cr.findOne(cliente);
        Producto[] productos = prods;
        Hashtable<String, Pedido> pedidos = new Hashtable<>();
        for (int i = 0; i < productos.length; i++) {
            Sucursal s = productos[i].getSucursales();
            Pedido p = null;
            String key = s.getFranquicias().getIdFranquicia()+
                    s.getPlazoletaComidas().getId().getIdPlazoletaComidas()+
                    s.getPlazoletaComidas().getId().getCiudad();
            if(!pedidos.containsKey(key)){
                p = new Pedido(c, false, false, "no enviado");
            }else{
                p = pedidos.get(key);
            }
            
            p.getPedidosProductoses().add(new PedidoProducto(p, productos[i]));
            pedidos.put(key, p);
        }
        boolean ok = true;
        for(String key: pedidos.keySet()){
            ok = ok && ce.ejecutarCompra(key, getTotalPedido(pedidos.get(key)), metodoPago);
        }
        if(ok){
            for(String key: pedidos.keySet()){
                pr1.save(pedidos.get(key));
                Pago pago = new Pago(pedidos.get(key), new Date(System.currentTimeMillis()), getTotalPedido(pedidos.get(key)), metodoPago);
                pr.save(pago);
            }
        }
        return ok;
    }
    
    
    private float getTotalPedido(Pedido p){
        float res=0;
        Set<PedidoProducto> productos = p.getPedidosProductoses();
        Iterator it = productos.iterator();
        PedidoProducto pp = null;
        while(it.hasNext()){
            pp =((PedidoProducto)it.next());
            res+= (pp.getProductos().getPrecio())*(1-(pp.getProductos().getPorcentajeDescuento()/100));
        }
        res=res*(1-(pp.getProductos().getSucursales().getFranquicias().getPorcentajeAcordado()/100));
        return res;
    }
    
    
    
    
    
}
