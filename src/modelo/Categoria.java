package modelo;

/**
 *
 * @author Taszka
 */
public class Categoria {

    private int idCategoria;
    private String Descripción;
    private int estado;

    public Categoria() {
        this.idCategoria = 0;
        this.Descripción = "";
        this.estado = 0;
    }

    public Categoria(int idCategoria, String Descripción, int estado) {
        this.idCategoria = idCategoria;
        this.Descripción = Descripción;
        this.estado = estado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String Descripción) {
        this.Descripción = Descripción;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
