package edu.eci.cosw.proyecto_eff.rest;


import edu.eci.cosw.proyecto_eff.logic.LogicaPago;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.persistance.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fercho
 */
@RestController
@RequestMapping("/pagos")
public class PagosRestController {
    
    @Autowired
    LogicaPago lp;
    
    @Autowired
    ProductoRepository pr;
    
    @RequestMapping(value="/ejemplo",method = RequestMethod.GET) 
    public Producto[] consultarCliente()throws ResourceNotFoundException{
        return new Producto[]{pr.findOne(new ProductoId("1", 14))};    
    }
    
    @RequestMapping(value="/{correo}/metodoPago/{metodoPago}",method = RequestMethod.POST)   
    public ResponseEntity<?> persist(@PathVariable("correo") String correo, @PathVariable("metodoPago") String metodoPago, @RequestBody Producto[] prods) throws OperationFailedException {
        boolean ok = lp.registrarPago(prods, correo, metodoPago);
        if(!ok){
            throw new OperationFailedException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
     }
    
}
