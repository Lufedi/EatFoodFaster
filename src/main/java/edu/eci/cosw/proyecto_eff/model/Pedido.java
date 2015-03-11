package edu.eci.cosw.proyecto_eff.model;
// Generated Feb 16, 2015 2:07:09 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Pedido generated by hbm2java
 */
@Entity
@Table(name="Pedidos")
public class Pedido  implements java.io.Serializable {


    private Integer idPedidos;
    private Cliente clientes;
    private boolean enviadoAsucursal;
    private boolean notificadoAcliente;
    private String estadoPedido;
    private Set<PedidoProducto> pedidosProductoses = new HashSet<PedidoProducto>(0);

    public Pedido() {
    }

	
    public Pedido(Cliente clientes, boolean enviadoAsucursal, boolean notificadoAcliente, String estadoPedido) {
        this.clientes = clientes;
        this.enviadoAsucursal = enviadoAsucursal;
        this.notificadoAcliente = notificadoAcliente;
        this.estadoPedido = estadoPedido;
    }
    public Pedido(Cliente clientes, boolean enviadoAsucursal, boolean notificadoAcliente, String estadoPedido, Set<PedidoProducto> pedidosProductoses) {
       this.clientes = clientes;
       this.enviadoAsucursal = enviadoAsucursal;
       this.notificadoAcliente = notificadoAcliente;
       this.estadoPedido = estadoPedido;
       this.pedidosProductoses = pedidosProductoses;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="idPedidos", unique=true, nullable=false)
    public Integer getIdPedidos() {
        return this.idPedidos;
    }
    
    public void setIdPedidos(Integer idPedidos) {
        this.idPedidos = idPedidos;
    }

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="Clientes_correo_cliente", nullable=false)
    //@Fetch (FetchMode.JOIN)
    public Cliente getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    
    @Column(name="enviadoASucursal", nullable=false)
    public boolean isEnviadoAsucursal() {
        return this.enviadoAsucursal;
    }
    
    public void setEnviadoAsucursal(boolean enviadoAsucursal) {
        this.enviadoAsucursal = enviadoAsucursal;
    }

    
    @Column(name="NotificadoACliente", nullable=false)
    public boolean isNotificadoAcliente() {
        return this.notificadoAcliente;
    }
    
    public void setNotificadoAcliente(boolean notificadoAcliente) {
        this.notificadoAcliente = notificadoAcliente;
    }

    
    @Column(name="estadoPedido", nullable=false, length=45)
    public String getEstadoPedido() {
        return this.estadoPedido;
    }
    
    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="pedidos")
    @Fetch (FetchMode.JOIN)
    public Set<PedidoProducto> getPedidosProductoses() {
        return this.pedidosProductoses;
    }
    
    public void setPedidosProductoses(Set<PedidoProducto> pedidosProductoses) {
        this.pedidosProductoses = pedidosProductoses;
    }
}


