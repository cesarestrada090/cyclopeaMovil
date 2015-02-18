/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */

public class Conexion {

     public static Connection obtenerConexionSQL(String Server,String baseDatos,String usuario, String contraseña)
    {
        String urlConexion = "jdbc:sqlserver://"+Server+";"+
                         "databaseName="+baseDatos+";user="+usuario+";password="+contraseña+";";
        Connection con = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(urlConexion);
            return con;
        }
        catch( SQLException exc){
            JOptionPane.showMessageDialog(null, "error al intentar conectarse\n"+exc.getMessage(),"ERROR AL CONECTARSE",2);
        }
        catch( ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null, "existen fallas en la conexión\n"+exc.getMessage(),"ERROR AL CONECTARSE",2);
        }
        return null;
    }

    public static Connection obtenerConexionMySQL(String Server,String baseDatos,String usuario, String contraseña)
    {
        Connection con = null;
        String url="jdbc:mysql://"+Server+":3306/"+baseDatos;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, contraseña);
        }catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"error al intentar conectarse:\n"+ ex.getMessage());
        }
        catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"error no se pudo cargar el Driver:\n"+ex.getMessage());
        }
        return con;
    }

    public static Connection obtenerConexionODBC(String usuario, String contraseña)
    {
        Connection cn= null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cn=DriverManager.getConnection("jdbc:odbc:HBT",usuario,contraseña);
            return cn;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "existen fallas en la conexión\n"+e.getMessage(),"ERROR AL CONECTARSE",2);
            return null;
        }
    }
}
