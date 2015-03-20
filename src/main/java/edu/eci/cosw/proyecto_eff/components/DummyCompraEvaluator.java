/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.components;

import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class DummyCompraEvaluator implements CompraEvaluator{

    @Override
    public boolean ejecutarCompra(String sucursal, float total, String tipoDePago) {
        return total%2==0;
    }

}
