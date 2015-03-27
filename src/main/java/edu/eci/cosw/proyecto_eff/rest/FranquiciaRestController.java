/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.rest;

import edu.eci.cosw.proyecto_eff.logic.LogicaFranquicia;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jennibarajas
 */
@RestController
@RequestMapping("/franquicia")
public class FranquiciaRestController {
    @Autowired
    LogicaFranquicia lf;
    
    @RequestMapping(value="/{fId}",method = RequestMethod.GET) 
    public List<Sucursal> buscarSucursalesPorFranquicia(@PathVariable String fId)throws ResourceNotFoundException{
        return lf.buscarSucursalesPorFranquicia(fId);
    }
    
}
