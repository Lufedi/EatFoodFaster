/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.persistance;

import edu.eci.cosw.proyecto_eff.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author FabianAndres
 */
public interface ClienteRepository extends CrudRepository<Cliente,String>{
    /*@Query("from Cliente c where c.correoCliente = :correo")
    public List<Cliente> search(@Param("correo") String correo);*/
}
