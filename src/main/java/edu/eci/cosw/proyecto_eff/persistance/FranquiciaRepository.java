/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.persistance;

import edu.eci.cosw.proyecto_eff.model.Franquicia;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author FabianAndres
 */
public interface FranquiciaRepository extends CrudRepository<Franquicia,String>{
    
}
