package edu.eci.cosw.proyecto_eff.model;
// Generated Feb 16, 2015 2:07:09 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * PedidoProducto generated by hbm2java
 */
@Entity
@Table(name="Pedidos_Productos")
public class PedidoProducto  implements java.io.Serializable {


     private Integer idPedidoProductos;
     private Pedido pedidos;
     private Producto productos;

    public PedidoProducto() {
    }

    public PedidoProducto(Pedido pedidos, Producto productos) {
       this.pedidos = pedidos;
       this.productos = productos;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id_Pedido_Productos", unique=true, nullable=false)
    public Integer getIdPedidoProductos() {
        return this.idPedidoProductos;
    }
    
    public void setIdPedidoProductos(Integer idPedidoProductos) {
        this.idPedidoProductos = idPedidoProductos;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Pedidos_idPedidos", nullable=false)
    @Fetch (FetchMode.JOIN)
    public Pedido getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Pedido pedidos) {
        this.pedidos = pedidos;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="Productos_idProductos", referencedColumnName="idProductos", nullable=false), 
        @JoinColumn(name="Productos_Sucursales_idSucursales", referencedColumnName="Sucursales_idSucursales", nullable=false) } )
    @Fetch (FetchMode.JOIN)
    public Producto getProductos() {
        return this.productos;
    }
    
    public void setProductos(Producto productos) {
        this.productos = productos;
    }




}


