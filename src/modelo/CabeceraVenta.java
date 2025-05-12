
package modelo;

/**
 *
 * @author kzamb
 */
public class CabeceraVenta {
    //atributos
    private int idCabeceraVenta;
    private int idCliente;
    private String fechaVenta;
    private double valorPagar;
    private int estado;
    
    //constructor
    public CabeceraVenta(){
        this.idCabeceraVenta = 0;
        this.idCliente = 0;
        this.valorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }
    
    //constructor sobrecargado

    public CabeceraVenta(int idCabeceraVenta, int idCliente, String fechaVenta, double valorPagar, int estado) {
        this.idCabeceraVenta = idCabeceraVenta;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.valorPagar = valorPagar;
        this.estado = estado;
    }
    
    // get and select

    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    //toString

    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraVenta=" + idCabeceraVenta + ", idCliente=" + idCliente + ", fechaVenta=" + fechaVenta + ", valorPagar=" + valorPagar + ", estado=" + estado + '}';
    }

    public void setIdCabeceraventa(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
