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
import edu.eci.cosw.proyecto_eff.model.InformacionTransaccion;
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
import edu.eci.cosw.proyecto_eff.rest.OperationFailedException;
import edu.eci.cosw.proyecto_eff.rest.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fercho
 */
@Service
public class LogicaPago {
    
    private final String urlPago = "http://eff.cloudhub.io/rest/PAYPAL/pago/tarjeta/%s/%s/%s/%d/%s/monto/%d/seguridad/%d/%s?api=api2";
    private final String urlReversoPago = "http://eff.cloudhub.io/rest/PAYPAL/Reverso/%d?api=api2";
    private final int idInscritoPasarela = 1;
    private final String nombreInscrito = "EatFoodFaster";
    private final int ERROR_TRANSACCION=0;
    
    @Autowired
    private PagoRepository pagoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
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
    public InformacionTransaccion registrarPago(InformacionCompra ic) throws OperationFailedException {
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
        ArrayList<InformacionTransaccion> responses = new ArrayList<>();
        RestTemplate rt = new RestTemplate();
        Enumeration<String> enumeration = pedidos.keys();
        InformacionTransaccion it = null;
        boolean ok = true;
        while(enumeration.hasMoreElements() && ok){
            String urlFormatted = String.format(urlPago, ic.getNumeroTarjeta(), ic.getNombreTarjeta(), ic.getTipoPago(), ic.getCodigoSeguridad(), ic.getCorreoUsuario(), getTotalPedido(pedidos.get(enumeration.nextElement())), idInscritoPasarela, nombreInscrito);
            ResponseEntity<Object> rit = rt.exchange(urlFormatted, HttpMethod.PUT, HttpEntity.EMPTY, Object.class);
            it = parseStringToInformacionTransaccion(rit.getBody().toString());
            if(it.getCodTransaccion()==ERROR_TRANSACCION){
                ok = false;
            }else{
                responses.add(it);
            }
        }
        if(ok){
            for(String key: pedidos.keySet()){
                pedidoRepository.save(pedidos.get(key));
                it.getPedidos().add(pedidos.get(key).getIdPedidos());
                Pago pago = new Pago(pedidos.get(key), new Date(System.currentTimeMillis()), getTotalPedido(pedidos.get(key)), ic.getTipoPago());
                pagoRepository.save(pago);
            }
        }else{
            for(int i=0;i<responses.size();i++){
                String urlFormatted = String.format(urlReversoPago, responses.get(i).getCodTransaccion());
                rt.postForEntity(urlFormatted, HttpEntity.EMPTY, Object.class);
            }
        }
        
        return it;
    }
    
    private InformacionTransaccion parseStringToInformacionTransaccion(String responseString){
        responseString = responseString.substring(1, responseString.length()-1);
        String[] partes = responseString.split(",");
        String[] resultado = partes[0].split("=");
        String[] codTransaccion = partes[1].split("=");
        InformacionTransaccion it = new InformacionTransaccion(resultado[1], Integer.parseInt(codTransaccion[1]), new ArrayList<Integer>());
        return it;
    }
    
    
    private int getTotalPedido(Pedido p){
        float res=0;
        Set<PedidoProducto> productos = p.getPedidosProductoses();
        Iterator it = productos.iterator();
        PedidoProducto pp = null;
        while(it.hasNext()){
            pp =((PedidoProducto)it.next());
            res+= (pp.getProductos().getPrecio())*(1-(pp.getProductos().getPorcentajeDescuento()/100));
        }
        res=res*(1-(pp.getProductos().getSucursales().getFranquicias().getPorcentajeAcordado()/100));
        return (int)res;
    }
    
    
    
    
    
}
