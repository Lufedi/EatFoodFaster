/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.PlazoletaComidaRepository;
import edu.eci.cosw.proyecto_eff.rest.ResourceNotFoundException;
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

    public List<PlazoletaComida> obtenerPlazoletasDeComidas() {
        return (List<PlazoletaComida>) pcr.findAll();
    }
    
    public PlazoletaComida obtenerPlazoletaPosicion(float gradosLon, float minutosLon, 
            float segundosLon, char orientacionLon, float gradosLat, 
            float minutosLat, float segundosLat, char orientacionLat) throws ResourceNotFoundException{
        PlazoletaComida pc=null;
        List<PlazoletaComida> list=pcr.buscarPorPosicion(gradosLon, minutosLon, segundosLon, orientacionLon, 
                gradosLat, minutosLat, segundosLat, orientacionLat);
        if(list.isEmpty())
            throw new ResourceNotFoundException();
        return list.get(0);
    }
    
    
}
