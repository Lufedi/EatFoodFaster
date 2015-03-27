package edu.eci.cosw.proyecto_eff.model;
// Generated Feb 16, 2015 2:07:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Categoria generated by hbm2java
 */
@Entity
@Table(name="Categorias")
public class Categoria  implements java.io.Serializable {


    private String idCategorias;
     

    public Categoria() {
    }

	
    public Categoria(String idCategorias) {
        this.idCategorias = idCategorias;
    }
   
   
    @Id 
    @Column(name="idCategorias", unique=true, nullable=false, length=70)
    public String getIdCategorias() {
        return this.idCategorias;
    }
    
    public void setIdCategorias(String idCategorias) {
        this.idCategorias = idCategorias;
    }

}


