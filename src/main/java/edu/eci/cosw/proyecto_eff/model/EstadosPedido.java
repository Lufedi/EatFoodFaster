/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.model;

/**
 *
 * @author 2094808
 */
public class EstadosPedido {
    public static final String LISTO =  "Pediod listo";
    public static final String NOTIFICADOACLIENTE =  "Notificado a cliente";
    public static final String RECIBIDOENSUCURSAL = "Pedido recibido en sucursal";
    public static final String ENVIADOASUCURSAL  = "Pedido enviado a sucursal";
    
    public static String estado;
    
    public EstadosPedido(String estado){this.estado  =  estado;}
}
