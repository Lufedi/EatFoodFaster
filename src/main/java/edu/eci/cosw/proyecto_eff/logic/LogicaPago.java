/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.components.CompraEvaluator;
import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.EstadosPedido;
import edu.eci.cosw.proyecto_eff.model.InformacionCompra;
import edu.eci.cosw.proyecto_eff.model.Pago;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.PedidoProducto;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.ClienteRepository;
import edu.eci.cosw.proyecto_eff.persistance.PagoRepository;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import edu.eci.cosw.proyecto_eff.persistance.ProductoRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
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
    private PagoRepository pagoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private CompraEvaluator compraEvaluator;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Pago> getPagos(){
        ArrayList<Pago> list = new ArrayList();
        Iterable<Pago> it = pagoRepository.findAll();
        for (Pago item : it) {
            list.add(item);
        }
        return list;
    }
    
    public Pago getPagoById(int id){
        return pagoRepository.findOne(id);
    }
    
    /**
     * @param ic
     * @param cliente
     * @throws edu.eci.cosw.proyecto_eff.rest.OperationFailedException
     * @Obj : guardar un pedido de un usuario  
     */
    public boolean registrarPago(InformacionCompra ic) {
        Cliente c = clienteRepository.findOne(ic.getCorreoUsuario());
        Hashtable<String, Pedido> pedidos = new Hashtable<>();
        ProductoId[] idProductos = ic.getIdProductos();
        for (int i = 0; i < idProductos.length; i++) {
            Producto prod = productoRepository.findOne(idProductos[i]);
            Sucursal s = prod.getSucursales();
            Pedido p = null;
            String key = s.getFranquicias().getIdFranquicia()+
                    s.getPlazoletaComidas().getId().getIdPlazoletaComidas()+
                    s.getPlazoletaComidas().getId().getCiudad();
            if(!pedidos.containsKey(key)){
                p = new Pedido(c, false, false, EstadosPedido.ENVIADOASUCURSAL);
            }else{
                p = pedidos.get(key);
            }
            p.getPedidosProductoses().add(new PedidoProducto(p, prod));
            pedidos.put(key, p);
        }
        boolean ok = true;
        for(String key: pedidos.keySet()){
            ok = ok && compraEvaluator.ejecutarCompra(ic.getOpcionPago(), ic.getNombreCliente(), ic.getApellidoCliente(), ic.getNumeroTarjeta(), ic.getMesExpiracion(), ic.getAnioExpiracion(), ic.getCodigoSeguridad(), getTotalPedido(pedidos.get(key)));
        }
        if(ok){
            for(String key: pedidos.keySet()){
                pedidoRepository.save(pedidos.get(key));
                Pago pago = new Pago(pedidos.get(key), new Date(System.currentTimeMillis()), getTotalPedido(pedidos.get(key)), ic.getOpcionPago());
                pagoRepository.save(pago);
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
