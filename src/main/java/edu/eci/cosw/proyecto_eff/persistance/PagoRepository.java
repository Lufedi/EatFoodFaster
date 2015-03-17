/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.persistance;

import edu.eci.cosw.proyecto_eff.model.Pago;
import edu.eci.cosw.proyecto_eff.model.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fercho
 */
public interface PagoRepository extends CrudRepository<Pago, Integer>{
    
    @Query("Select ped from Pago p inner join p.pedidos as ped where ped.clientes.correoCliente = :id")
    public List<Pedido> search(@Param("id") String correoCliente);
}
