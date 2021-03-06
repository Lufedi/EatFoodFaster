/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Estado;
import edu.eci.cosw.proyecto_eff.model.EstadosPedido;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.PedidoRepository;
import edu.eci.cosw.proyecto_eff.persistance.SucursalRepository;
import edu.eci.cosw.proyecto_eff.rest.OperationFailedException;
import edu.eci.cosw.proyecto_eff.rest.ResourceNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
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
    
    @Autowired
    SucursalRepository sr;
    
    /**
     * @param e estado
     * @Obj: Notificar al cliente que el pedido de una sucursal esta listo
     * @param idPedido: id  del Pedido del cliente
     * 
     * @throws OperationFailedException 
     */
    public void notificarPedidoListo(int idPedido , Estado e)  throws OperationFailedException{
        //pr.notificarPedidoListo(idPedido);
        Pedido p  = pr.findOne(idPedido);
        p.setNotificadoAcliente(true);
        p.setEstadoPedido(e.getEstado());
        pr.save(p);
    }
    
    /**
     * 
     * 
     * @param idPedido: Id del pedido al cual se le cambiara el estado de que ya
     * le fue notificado a la sucursal
     * @param e: Estado
     * @throws edu.eci.cosw.proyecto_eff.rest.ResourceNotFoundException
     */
    public void recibirNotificacion(int idPedido, Estado e) throws ResourceNotFoundException{
        //Debo retornal algo
        Pedido p  = pr.findOne(idPedido);
        p.setEnviadoAsucursal(true);
        p.setEstadoPedido(e.getEstado());
        pr.save(p);
    }

    public  boolean validarEstado(Estado e) throws IllegalArgumentException, IllegalAccessException{
       EstadosPedido ep =  new EstadosPedido();
       Field[] a = EstadosPedido.class.getFields();
       String temp;
       for(Field s :  a){
           temp   = (String) s.get(ep);
           if( temp !=  null && temp.equals(e.getEstado())) return true;
       } 
       return false;
    }
  
    /*public static void main(String[] args) throws OperationFailedException, IllegalArgumentException, IllegalAccessException{
          Estado e =  new Estado();
        e.setEstado(EstadosPedido.NOTIFICADOACLIENTE);
        boolean validarEstado = validarEstado(e);
        
    }*/
    public List<Sucursal> consultarSucursales(){
        return (List<Sucursal>)sr.findAll();
    }
    
    /**
     * Obtener sucursal por franquicia y plazoleta de comidas
     */
    public Sucursal obtenerSucursal(String franquiciaId , String plazoletaId){
        Sucursal sucsel = null;
        for(Sucursal s :  sr.findAll()){
            if(s.getFranquicias().getIdFranquicia().equals(franquiciaId)
            && s.getPlazoletaComidas().getId().getIdPlazoletaComidas().equals(plazoletaId))
            sucsel =  s;
        }
        return sucsel;
    }
    
}
