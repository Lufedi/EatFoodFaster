/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.PlazoletaComidaRepository;
import java.util.AbstractList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class LogicaPlazoleta {
    @Autowired 
    PlazoletaComidaRepository pcr;

    /**
     * @param pcId
     * @return 
    * @Obj: buscar las sucursales de cada franquicia que está en la plazoleta de comidas
    * @param pcId: Nombre de la plazoleta de comida donde buscara sus franquicias
    * @return: listado de sucursales de franquicias ubicados en la plazoleta de comidas
    */
    public List<Sucursal> buscarSucursalesPorPlazoleta(String pcId){
        List<Sucursal> ls=null;
        List<PlazoletaComida> plazoletas =(List<PlazoletaComida>) pcr.findAll();
        for(PlazoletaComida p : plazoletas){
            if(p.getId().getIdPlazoletaComidas().equalsIgnoreCase(pcId))
                ls=pcr.search(pcId);
        }
        return ls;
    }   
}
