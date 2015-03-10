package edu.eci.cosw.proyecto_eff.model;
// Generated Feb 16, 2015 2:07:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Producto generated by hbm2java
 */
@Entity
@Table(name="Productos")
public class Producto  implements java.io.Serializable {


     private ProductoId id;
     private Categoria categorias;
     private Sucursal sucursales;
     private float precio;
     private boolean esCombo;
     private String descripcion;
     private float porcentajeDescuento;
     private Set<PedidoProducto> pedidosProductoses = new HashSet<PedidoProducto>(0);

    public Producto() {
    }

	
    public Producto(ProductoId id,Categoria categorias, Sucursal sucursales, float precio, boolean esCombo, String descripcion, float porcentajeDescuento) {
        this.id = id;
        this.categorias = categorias;
        this.sucursales = sucursales;
        this.precio = precio;
        this.esCombo = esCombo;
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
    }
    public Producto(ProductoId id,Categoria categorias, Sucursal sucursales, float precio, boolean esCombo, String descripcion, float porcentajeDescuento, Set<PedidoProducto> pedidosProductoses) {
       this.id = id;
       this.categorias = categorias;
       this.sucursales = sucursales;
       this.precio = precio;
       this.esCombo = esCombo;
       this.descripcion = descripcion;
       this.porcentajeDescuento = porcentajeDescuento;
       this.pedidosProductoses = pedidosProductoses;
    }
   
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="idProductos", column=@Column(name="idProductos", nullable=false, length=45) ), 
        @AttributeOverride(name="sucursalesIdSucursales", column=@Column(name="Sucursales_idSucursales", nullable=false) ) } )
    public ProductoId getId() {
        return this.id;
    }
    
    public void setId(ProductoId id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Categorias_idCategorias", nullable=false)
    public Categoria getCategorias() {
        return this.categorias;
    }
    
    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Sucursales_idSucursales", nullable=false, insertable=false, updatable=false)
    public Sucursal getSucursales() {
        return this.sucursales;
    }
    
    public void setSucursales(Sucursal sucursales) {
        this.sucursales = sucursales;
    }

    
    @Column(name="Precio", nullable=false, precision=12, scale=0)
    public float getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    
    @Column(name="esCombo", nullable=false)
    public boolean isEsCombo() {
        return this.esCombo;
    }
    
    public void setEsCombo(boolean esCombo) {
        this.esCombo = esCombo;
    }

    
    @Column(name="descripcion", nullable=false, length=200)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="porcentaje_descuento", nullable=false, precision=12, scale=0)
    public float getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }
    
    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="productos",cascade = CascadeType.ALL)
    public Set<PedidoProducto> getPedidosProductoses() {
        return this.pedidosProductoses;
    }
    
    public void setPedidosProductoses(Set<PedidoProducto> pedidosProductoses) {
        this.pedidosProductoses = pedidosProductoses;
    }




}


