/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.logic.LogicaPlazoleta;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author jennibarajas
 */

@RestController
@RequestMapping("/plazoleta")
public class PlazoletaRestController {
    @Autowired
    LogicaPlazoleta lp;
    
    @RequestMapping(value="/{pcId}",method = RequestMethod.GET) 
    public List<Sucursal> buscarSucursalesPorPlazoleta(@PathVariable String pcId)throws ResourceNotFoundException{
        return lp.buscarSucursalesPorPlazoleta(pcId);
    }
    
    
    @RequestMapping(method = RequestMethod.GET) 
    public List<PlazoletaComida> obtenerPlazoletasDeComidas()throws ResourceNotFoundException{
        return lp.obtenerPlazoletasDeComidas();
    }
    
     @RequestMapping(value="/{gradosLon}/{minutosLon}/{segundosLon}/{orientacionLon}/{gradosLat}/{minutosLat}/{segundosLat}/{orientacionLat}" ,method = RequestMethod.GET) 
    public PlazoletaComida obtenerPlazoletasDeComidasPorPosicion(@PathVariable float gradosLon,
                                                   @PathVariable float minutosLon,
                                                   @PathVariable float segundosLon,
                                                   @PathVariable char orientacionLon,
                                                   @PathVariable float gradosLat,
                                                   @PathVariable float minutosLat,
                                                   @PathVariable float segundosLat,
                                                   @PathVariable char orientacionLat)throws ResourceNotFoundException{
        
        PlazoletaComida pc= lp.obtenerPlazoletaPosicion(gradosLon, minutosLon, segundosLon, orientacionLon, gradosLat, minutosLat, segundosLat, orientacionLat);
        return pc;
    }
    
}
