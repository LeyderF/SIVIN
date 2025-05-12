package controlador;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import conexion.Conexion;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import modelo.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author Taszka
 */
public class Ctrl_Usuario {
    
    //guardar nuevo usuario
    public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_usuario values(?, ?, ?, ?, ?, ?, ?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getUsuario());
            consulta.setString(5, objeto.getPassword());
            consulta.setString(6, objeto.getTelefono());
            consulta.setInt(7, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }
        return respuesta;
    }

    //Para consultar si el usuario existe
    public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        String sql = "select usuario from tb_usuario where usuario = '" + usuario + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);
        }
        return respuesta;
    }
    
    
    //actualizar usuario
    public boolean actualizar(Usuario objeto, int idUsuario) {

        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_usuario set nombre = ?, apellido = ?, usuario = ?, password = ?, telefono = ?, estado = ? where idUsuario ='" + idUsuario + "'");

            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {

                respuesta = true;

            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e);
        }

        return respuesta;
    }

    //eliminar usuario
    public boolean eliminar(int idUsuario) {

        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_usuario where idUsuario ='" + idUsuario + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);
        }

        return respuesta;
    }

    //metodo para log in
    public boolean loginUser(Usuario objeto) {

        boolean respuesta = false;

        Connection cn = Conexion.conectar();
        String SQL = "select concat(nombre, \" \", apellido) as Nombres, usuario, password from tb_usuario \n"
                + "where usuario = '" + objeto.getUsuario() + "' and password = '" + objeto.getPassword() + "'";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Erorr al iniciar sesion");
            JOptionPane.showMessageDialog(null, "Erorr al iniciar sesion");
        }

        
        return respuesta;
    }

}
