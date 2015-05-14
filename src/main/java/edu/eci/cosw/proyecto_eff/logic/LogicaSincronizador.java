/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.proyecto_eff.logic;

import edu.eci.cosw.proyecto_eff.model.Categoria;
import edu.eci.cosw.proyecto_eff.model.Franquicia;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComida;
import edu.eci.cosw.proyecto_eff.model.PlazoletaComidaId;
import edu.eci.cosw.proyecto_eff.model.Producto;
import edu.eci.cosw.proyecto_eff.model.ProductoId;
import edu.eci.cosw.proyecto_eff.model.Sucursal;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Lufedi
 */
@Service
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
    
    @Autowired
    LogicaSucursal ls;
    
    @Autowired
    LogicaProducto lp;
    
    /**
     * Metodo de sincronizacion con las franquicias que se han inscrito al servicio
     * de EFF
     * PRE: Las franquicias y sus sucursales deben estar debidamente registradas en el 
     * sistema de EFF
     *
     * @param franquicias lista de franquicias a sincronizar
     * 
     * POS: Se han registrado las actualizaciones de los productos con EFF
     */
    public void sincronizar(Franquicia franquicia){
        String rest =  new String(uri);
     
            rest +=  franquicia.getIdFranquicia();
            /*
            */
            RestTemplate rt = new RestTemplate();
            try {  
                FranquiciaSync franquiciaSync =  rt.getForObject(new URI(rest), FranquiciaSync.class);
                this.adapterFranquicia(franquiciaSync);
            } catch (URISyntaxException ex) {
                Logger.getLogger(LogicaSincronizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    public void adapterFranquicia(FranquiciaSync fs){
        Sucursal sucsel =  null;
        PlazoletaComida pz  = null;
        Franquicia frasel = fr.findOne(fs.nombreFranquicia);
        if(frasel == null){
            Logger.getLogger(LogicaSincronizador.class.getName()).log(Level.WARNING,
                    "La franquicia " + fs.nombreFranquicia + "no se encuentra registrada en la base de datos"
                            + "por favor agregarla para continuar con el proceso de sincronizacion");
            System.out.println( "La franquicia " + fs.nombreFranquicia + "no se encuentra registrada en la base de datos"
                            + "por favor agregarla para continuar con el proceso de sincronizacion");
        }else{
            
            for(SucursalSync sucSync : fs.sucursales){
                pz = pcr.findOne(new PlazoletaComidaId(sucSync.nombreSucursal, "Bogota"));
                if( pz == null){
                    System.out.println("se ha crado una nueva franquicia");
                    pz =  new PlazoletaComida(new PlazoletaComidaId(sucSync.nombreSucursal ,  "Bogota"));
                    pcr.save(pz);
                }
                //Busca la sucursal que pertenezca a la franquicia en la plazoleta de comidas nombresucursal
                sucsel = ls.obtenerSucursal(fs.nombreFranquicia, sucSync.nombreSucursal);
               if(sucsel == null){
                       System.out.println("se ha crado una nueva sucursal en  " + fs.nombreFranquicia + " " + sucSync.nombreSucursal);
                       sucsel = new Sucursal(frasel, pz);
                       sr.save(sucsel);
               }
               
               //Actualizando productos
               int size =  (int)pr.count();
               Producto p;
               Categoria c;
               for(ProductoSync proSync : sucSync.productos){
                   p = lp.obtenerProducto(proSync.nombreProducto);
                   c = lc.obtenerCateogia(proSync.descripcion);
                   
                   if(p == null){
                        p =  new Producto(new ProductoId((size++)+""
                                , sucsel.getIdSucursales()), new Categoria(proSync.descripcion), sucsel,
                                proSync.precio, false, proSync.nombreProducto,
                                0, proSync.imagen);
                        
                        System.out.println("se ha agregado el producto "  + proSync.nombreProducto);
                   }else{
                       p.setDescripcion(proSync.nombreProducto);
                       p.setPrecio(proSync.precio);
                       p.setUrlImagen(proSync.imagen);
                       p.setCategorias(new Categoria(proSync.descripcion));
                       System.out.println("se ha actualizado el producto " + proSync.nombreProducto );
                   }
                    pr.save(p);
               } 
               
            }
        }
        
        
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
    public String  imagen;
}