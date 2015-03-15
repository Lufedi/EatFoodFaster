/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.persistance;

import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author FabianAndres
 */
public interface PlazoletaComidaRepository extends CrudRepository<PlazoletaComida,PlazoletaComidaId>{
    
    //las sucursales de una plazoleta de comida 
    /**
     * @param plazoletaid
     * @return
     */
    @Query("Select s from Sucursal s inner join s.plazoletaComidas as pc where pc.id.idPlazoletaComidas like :id")
    public List<Sucursal> search(@Param("id") String plazoletaid);
}
