/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.components;

/**
 *
 * @author fercho
 */
public interface CompraEvaluator {

    /**
     * @obj: Efectuar el pago de un pedido de una sucursal usando un tipo de pago a través de un tercero
     * @param sucursal: Nombre de la sucursal donde se hace la compra
     * @param total: Valor total a pagar
     * @param tipoDePago: Tipo de pago seleccionado
     * @return true si el pago fue realizado correctamente, false en caso contrario
     */
    public boolean ejecutarCompra(String opcionPago, String nombreCliente, String apellidoCliente, String numeroTarjeta, int mesExpiracion, int anioExpiracion, int codigoSeguridad, double totalCompra);

}
