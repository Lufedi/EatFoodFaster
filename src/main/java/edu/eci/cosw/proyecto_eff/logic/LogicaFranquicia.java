/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import edu.eci.cosw.proyecto_eff.persistance.FranquiciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fercho
 */
@Service
public class LogicaFranquicia {
    
    @Autowired
    private FranquiciaRepository fr;
    
    /**
     * @Obj: Listar las sucursales disponibles de una franquicia
     * @param fId: nombre de la franquicia a la que se le buscará sus sucursales
     * @return : lista de sucursales que pertenecen a la franquicia
     */
    
    public List<Sucursal> buscarSucursalesPorFranquicia(String fId){
        List<Sucursal> ls=null;
        List<Franquicia> franquicias =(List<Franquicia>) fr.findAll();
        for(Franquicia f : franquicias){
            if(f.getIdFranquicia().equalsIgnoreCase(fId))
                ls=fr.search(fId);
        }
        return ls;
    }

    public List<Franquicia> obtenerFranquicias() {
        return (List<Franquicia>) fr.findAll();
    }
}
