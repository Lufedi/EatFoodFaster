/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.persistance;

import edu.eci.cosw.proyecto_eff.model.Cliente;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author FabianAndres
 */
public interface ProductoRepository extends CrudRepository<Producto,ProductoId> {
    @Query("from Producto c where c.descripcion like :nombre")
    public List<Producto> search(@Param("nombre") String nombre);
    
    
    
    
    @Query("select  p  from Producto  p  INNER JOIN p.sucursales f where "
            + " f.plazoletaComidas.id.idPlazoletaComidas = :centrocomercial and "
            + " f.plazoletaComidas.id.ciudad = :ciudad and"
            + " p.descripcion like :nombre")
    public List<Producto> searchPorPlazoletaComidas(
            @Param("nombre") String nombre , 
            @Param("ciudad") String ciudad , 
            @Param("centrocomercial") String centrocomercial);
            
            
}
