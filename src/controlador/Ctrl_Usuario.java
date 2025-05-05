package controlador;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import conexion.Conexion;
import javax.swing.JOptionPane;
import modelo.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author Taszka
 */
public class Ctrl_Usuario {

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
