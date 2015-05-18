package edu.eci.cosw.proyecto_eff.rest;


import edu.eci.cosw.proyecto_eff.logic.LogicaPago;
import edu.eci.cosw.proyecto_eff.model.InformacionCompra;
import edu.eci.cosw.proyecto_eff.model.InformacionTransaccion;
import edu.eci.cosw.proyecto_eff.model.Pago;
import java.util.List;
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
    
    @RequestMapping(value="/",method = RequestMethod.GET)        
    public List<Pago> consultarPagos() throws ResourceNotFoundException  {       
        return lp.getPagos();
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)        
    public Pago consultarPagosPorId(@PathVariable int id) throws ResourceNotFoundException  {       
        return lp.getPagoById(id);
    }
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public InformacionTransaccion guardarPago(@RequestBody InformacionCompra ic) throws OperationFailedException {
        return lp.registrarPago(ic);
    }
    
}
