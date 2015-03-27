/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.persistance;

import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author FabianAndres
 */
public interface FranquiciaRepository extends CrudRepository<Franquicia,String>{
    //las sucursales de una plazoleta de comida 
    /**
     * @param franquiciaid
     * @return
     */
    @Query("from Sucursal s inner join s.franquicias as f where f.idFranquicia like :id")
    public List<Sucursal> search(@Param("id") String franquiciaid);
    
}
