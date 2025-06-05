package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Cliente;
import modelo.Proveedor;

/**
 *
 * @
 */
public class Ctrl_Proveedor {

    /**
     * **************************************************
     * metodo para guardar un nuevo proveedor
     * **************************************************
     */
    public boolean guardar(Proveedor objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_proveedor values(?,?,?,?,?,?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getCedula());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar proveedor: " + e);
        }
        return respuesta;
    }

    /**
     * ********************************************************************
     * metodo para consultar si el producto ya esta registrado en la BBDD
     * ********************************************************************
     */
    public boolean existeCliente(String cedula) {
        boolean respuesta = false;
        String sql = "select cedula from tb_cliente where cedula = '" + cedula + "';";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e);
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para actualizar un proveedor
     * **************************************************
     */
    public boolean actualizar(Proveedor objeto, int idProveedor) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_proveedor set nombre=?, cedula = ?, telefono= ?, direccion = ?, estado = ? where idProveedor ='" + idProveedor + "'");      
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getCedula());
            consulta.setString(3, objeto.getTelefono());
            consulta.setString(4, objeto.getDireccion());
            consulta.setInt(5, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e);
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para eliminar un cliente
     * **************************************************
     */
    public boolean eliminar(int idProveedor) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_proveedor where idProveedor ='" + idProveedor + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor: " + e);
        }
        return respuesta;
    }

}
