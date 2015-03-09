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
    
    @Query("from Pedido p where p.cliente.correoCliente like :ln")
    public List<Pedido> search(@Param("ln") String searchTerm);
}
