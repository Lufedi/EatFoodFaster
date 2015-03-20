/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.components;

import edu.eci.cosw.proyecto_eff.model.Producto;
import java.util.List;

/**
 *
 * @author fercho
 */
public interface synchronizerFranquicia {
    
    /**
     * @obj: sincronizar la lista de productos que ofrece una franquicia
     * @param franquicia: nombre de la franquicia con la que se quiere sincronizar
     * @return lista de produtos de la franquicia
     */
    public List<Producto> sincronizarConFranquicia(String franquicia);

}
