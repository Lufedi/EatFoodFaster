/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.persistance.FranquiciaRepository;
import edu.eci.cosw.proyecto_eff.persistance.PlazoletaComidaRepository;
import edu.eci.cosw.proyecto_eff.persistance.ProductoRepository;
import edu.eci.cosw.proyecto_eff.persistance.SucursalRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author monitor
 */
public class LogicaSincronizador {
    
    private String uri  =  "https://servicioscosw2015.herokuapp.com/rest/FRANQUICIA/sincronizar/";
    @Autowired
    ProductoRepository pr;
     
    @Autowired 
    PlazoletaComidaRepository pcr;
    
    @Autowired
    SucursalRepository sr;
    
    @Autowired
    FranquiciaRepository fr;
    
    
    public void sincronizar(ArrayList<Franquicia> franquicias){
        String rest =  new String(uri);
        for(Franquicia franquicia  : franquicias){
            rest +=  franquicia.getIdFranquicia();
            /*
            */
            RestTemplate rt = new RestTemplate();
            try {
                FranquiciaSync franquiciaSync =  rt.getForObject(new URI(rest), FranquiciaSync.class);
            } catch (URISyntaxException ex) {
                Logger.getLogger(LogicaSincronizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void adapterFranquicia(FranquiciaSync fs){
        
        
    }
}

class FranquiciaSync{
    
    public String nombreFranquicia;
    public ArrayList<SucursalSync> sucursales;
    
}
class SucursalSync{
    public String nombreSucursal;
    public ArrayList<ProductoSync>  productos;
}
class ProductoSync{
    public String nombreProducto;
    public String descripcion;
    public int precio;
}