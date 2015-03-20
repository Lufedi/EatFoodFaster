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
    private Producto[] productos;

    public InformacionCompra(String metodoDepago, Producto[] productos) {
        this.metodoDepago = metodoDepago;
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

}
