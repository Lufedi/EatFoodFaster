/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.persistance;

import edu.eci.cosw.proyecto_eff.model.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author FabianAndres
 */
public interface PedidoRepository extends CrudRepository<Pedido,Integer>{
    
    @Query("select p from Pedido p INNER JOIN p.pedidosProductoses as pe INNER JOIN pe.productos as pr INNER JOIN pr.sucursales as s where s.idSucursales=:ln")
    public List<Pedido> searchBySucursal(@Param("ln") int searchTerm);
    
}
