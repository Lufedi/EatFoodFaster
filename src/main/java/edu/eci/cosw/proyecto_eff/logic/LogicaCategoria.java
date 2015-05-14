/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.persistance.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lufedi
 */
@Service
public class LogicaCategoria {
    
    @Autowired
    CategoriaRepository cr;
    
    
    public Categoria obtenerCategoria(String nombreCategoria){
        
        return cr.findOne(nombreCategoria);
    }
    
}
