package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//test
/**
 *
 * @author Taszka
 */
public class Conexion {
    //conexion local
    
    public static Connection conectar(){
        
        try {
            
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sivin", "root", "S1V1N234@");
            return cn;
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion local: " + e);
        }
        return null;
    }
            
}
