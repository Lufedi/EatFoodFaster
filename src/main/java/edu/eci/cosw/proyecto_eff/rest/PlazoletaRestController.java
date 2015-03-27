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
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @RequestMapping(value="/",method = RequestMethod.GET) 
    public List<Sucursal> buscarSucursalesPorPlazoleta(@RequestBody PlazoletaComida pc)throws ResourceNotFoundException{
        return lp.buscarSucursalesPorPlazoleta(pc);
    }
}