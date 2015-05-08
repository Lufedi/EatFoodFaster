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
    @Query("Select s from Sucursal s inner join s.plazoletaComidas as pc where pc.id.idPlazoletaComidas = :id")
    public List<Sucursal> search(@Param("id") String plazoletaid);
    
    /**
     * 
     * @param gradosLon
     * @param minutosLon
     * @param segundosLon
     * @param orienteacionLon
     * @param gradosLat
     * @param minutosLat
     * @param segundosLat
     * @param orientacionLat
     * @return 
     */
    @Query("from PlazoletaComida where gradosLon=:gradosLon AND "
                + "minutosLon=:minutosLon AND segundosLon - radio <=:segundosLon AND "
                + "segundosLon + radio >=:segundosLon AND orientacionLon=:orientacionLon AND "
                + "gradosLat=:gradosLat AND minutosLat=:minutosLat "
                + "AND segundosLat - radio <=:segundosLat AND segundosLat + radio >=:segundosLat "
                + "AND orientacionLat=:orientacionLat")
    public List<PlazoletaComida> buscarPorPosicion(@Param("gradosLon") float gradosLon,
                                                   @Param("minutosLon") float minutosLon,
                                                   @Param("segundosLon") float segundosLon,
                                                   @Param("orientacionLon") char orienteacionLon,
                                                   @Param("gradosLat") float gradosLat,
                                                   @Param("minutosLat") float minutosLat,
                                                   @Param("segundosLat") float segundosLat,
                                                   @Param("orientacionLat") char orientacionLat);
}
