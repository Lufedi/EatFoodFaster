package cosw.eci.eatfoodfaster;

/**
 * Created by fercho on 5/14/15.
 */
public class Product {
    private String idProducto;
    private String idFranquicia;
    private double precio;
    private String urlImagen;

    public Product(String idProducto, String idFranquicia, double precio, String urlImagen) {
        this.idProducto = idProducto;
        this.idFranquicia = idFranquicia;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdFranquicia() {
        return idFranquicia;
    }

    public void setIdFranquicia(String idFranquicia) {
        this.idFranquicia = idFranquicia;
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

    @Override
    public String toString() {
        return this.idProducto;
    }
}