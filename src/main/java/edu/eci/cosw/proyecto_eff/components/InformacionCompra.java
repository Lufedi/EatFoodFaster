/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.components;

import edu.eci.cosw.proyecto_eff.model.Producto;


/**
 *
 * @author fercho
 */
public class InformacionCompra {
    
    private String metodoDepago;
    private String cuenta;
    private int mes;
    private int anio;
    private int codigoSeguridad;
    private Producto[] productos;

    public InformacionCompra(String metodoDepago, String cuenta, int mes, int anio, int codigoSeguridad, Producto[] productos) {
        this.metodoDepago = metodoDepago;
        this.cuenta = cuenta;
        this.mes = mes;
        this.anio = anio;
        this.codigoSeguridad = codigoSeguridad;
        this.productos = productos;
    }
    
    
    
    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the codigoSeguridad
     */
    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    /**
     * @param codigoSeguridad the codigoSeguridad to set
     */
    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    /**
     * @return the productos
     */
    public Producto[] getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    /**
     * @return the metodoDepago
     */
    public String getMetodoDepago() {
        return metodoDepago;
    }

    /**
     * @param metodoDepago the metodoDepago to set
     */
    public void setMetodoDepago(String metodoDepago) {
        this.metodoDepago = metodoDepago;
    }

}
