
package edu.eci.cosw.proyecto_eff.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping("/test")
public class TestRestController {
    
    @RequestMapping(value="/echo/{input}",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaX(@PathVariable String input) {       
        return new ResponseEntity<>("REST API working. Echo:"+input,HttpStatus.ACCEPTED);
    }
    
     @RequestMapping(value="/ha/{input}/{dos}",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaY(@PathVariable("input") String input ,  
             @PathVariable("dos") String dos) {       
        return new ResponseEntity<>("REST API working. Echo:"+input + " " + dos,HttpStatus.ACCEPTED);
    }
    
    
    
}
