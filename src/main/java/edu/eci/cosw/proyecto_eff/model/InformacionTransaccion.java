/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.model;

import java.util.ArrayList;

/**
 *
 * @author fercho
 */
public class InformacionTransaccion {
    private String Resultado;
    private int codTransaccion;
    private ArrayList<Integer> pedidos;

    public InformacionTransaccion() {
    }
    

    public InformacionTransaccion(String Resultado, int codTransaccion, ArrayList<Integer> pedidos) {
        this.Resultado = Resultado;
        this.codTransaccion = codTransaccion;
        this.pedidos = pedidos;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String Resultado) {
        this.Resultado = Resultado;
    }

    public int getCodTransaccion() {
        return codTransaccion;
    }

    public void setCodTransaccion(int codTransaccion) {
        this.codTransaccion = codTransaccion;
    }

    public ArrayList<Integer> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Integer> pedidos) {
        this.pedidos = pedidos;
    }
    
}
