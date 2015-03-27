/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.model;

/**
 *
 * @author fercho
 */
public class InformacionCompra {
    
    private String correoUsuario;
    private String opcionPago;
    private String nombreCliente;
    private String apellidoCliente;
    private String numeroTarjeta;
    private int mesExperacion;
    private int anioExpiracion;
    private int codigoSeguridad;
    private ProductoId[] idProductos;

    public InformacionCompra(String correoUsuario, String opcionPago, String nombreCliente, String apellidoCliente, String numeroTarjeta, int mesExperacion, int anioExpiracion, int codigoSeguridad, ProductoId[] idProductos) {
        this.correoUsuario = correoUsuario;
        this.opcionPago = opcionPago;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.numeroTarjeta = numeroTarjeta;
        this.mesExperacion = mesExperacion;
        this.anioExpiracion = anioExpiracion;
        this.codigoSeguridad = codigoSeguridad;
        this.idProductos = idProductos;
    }

    /**
     * @return the correoUsuario
     */
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    /**
     * @param correoUsuario the correoUsuario to set
     */
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    /**
     * @return the opcionPago
     */
    public String getOpcionPago() {
        return opcionPago;
    }

    /**
     * @param opcionPago the opcionPago to set
     */
    public void setOpcionPago(String opcionPago) {
        this.opcionPago = opcionPago;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the apellidoCliente
     */
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    /**
     * @param apellidoCliente the apellidoCliente to set
     */
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    /**
     * @return the numeroTarjeta
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the mesExperacion
     */
    public int getMesExperacion() {
        return mesExperacion;
    }

    /**
     * @param mesExperacion the mesExperacion to set
     */
    public void setMesExperacion(int mesExperacion) {
        this.mesExperacion = mesExperacion;
    }

    /**
     * @return the anioExpiracion
     */
    public int getAnioExpiracion() {
        return anioExpiracion;
    }

    /**
     * @param anioExpiracion the anioExpiracion to set
     */
    public void setAnioExpiracion(int anioExpiracion) {
        this.anioExpiracion = anioExpiracion;
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
     * @return the idProductos
     */
    public ProductoId[] getIdProductos() {
        return idProductos;
    }

    /**
     * @param idProductos the idProductos to set
     */
    public void setIdProductos(ProductoId[] idProductos) {
        this.idProductos = idProductos;
    }

    
}
