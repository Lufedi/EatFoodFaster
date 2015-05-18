package cosw.eci.eatfoodfaster;

/**
 * Created by fercho on 5/14/15.
 */
public class Product {
    private String idProducto;
    private int idSucursal;
    private String franquicia;
    private String descripcion;
    private double precio;
    private String urlImagen;

    public Product(String idProducto, int idSucursal,String franquicia, String descripcion, double precio, String urlImagen) {
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        this.franquicia=franquicia;
        this.descripcion=descripcion;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

}
