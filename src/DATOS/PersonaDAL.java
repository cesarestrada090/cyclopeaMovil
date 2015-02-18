/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;

import ENTIDADES.Persona;
import PRESENTACION.frmInicio;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class PersonaDAL {
    Connection cn=null;
    Statement st= null;
    PreparedStatement ps=null;
    ResultSet rs= null;
    CallableStatement cs= null;

    public int obtenerNumeroRegistroPersona()
    {
        try{
            cn=(Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            st=(Statement) cn.createStatement();
            rs=st.executeQuery("select max(Per_Id) from Persona;");
            while (rs.next()) {
                String numero= rs.getString(1);
                if(numero!=null){
                    return Integer.parseInt(numero);
                }
                else
                    return 0;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public boolean registrarDatosPersona(Persona p)
    {
        try {
            cn=(Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="insert into persona values(?,?,?,?,?,?,?,?)";
            ps=(PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1,p.getId());
            ps.setString(2,p.getDNI());
            ps.setString(3,p.getRUC());
            ps.setString(4,p.getRazon());
            ps.setString(5,p.getDireccion());
            ps.setString(6,p.getTelefono());
            ps.setString(7,p.getCelular());
            ps.setString(8,p.getCorreo());
            ps.executeUpdate();
            return true;
            //"Un usuario ya ha sido registrado con la ubicación seleccionada"
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
            return false;
        }
        finally{
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Object[][] ListarVencimientos(String x1,String x2)
    {
        Object Usuarios[][]=null;
        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL listarVencimientos('"+x1+"','"+x2+"')}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Usuarios= new Object[count][8];
            int i=0;
            while(rs.next()){

                Usuarios[i][0]=rs.getString(1);
                Usuarios[i][1]=rs.getString(2);
                Usuarios[i][2]=rs.getString(3);
                Usuarios[i][3]=rs.getString(4);
                Usuarios[i][4]=rs.getString(5);
                Usuarios[i][5]=rs.getString(6);
                Usuarios[i][6]=rs.getString(7);
                Usuarios[i][7]=rs.getString(8);
                i++;
            }
            return Usuarios;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Object[][] ListarCorreos(String x1)
    {
        Object Usuarios[][]=null;
        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL listarCorreosVencimientos('"+x1+"')}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Usuarios= new Object[count][1];
            int i=0;
            while(rs.next()){

                Usuarios[i][0]=rs.getString(1);
                i++;
            }
            return Usuarios;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Object[][] ListarRellamadas(String x1)
    {
        Object Usuarios[][]=null;
        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL listarRellamadasDia('"+x1+"')}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Usuarios= new Object[count][12];
            int i=0;
            while(rs.next()){

                Usuarios[i][0]=rs.getString(1);
                Usuarios[i][1]=rs.getString(2);
                Usuarios[i][2]=rs.getString(3);
                Usuarios[i][3]=rs.getString(4);
                Usuarios[i][4]=rs.getString(5);
                Usuarios[i][5]=rs.getString(6);
                Usuarios[i][6]=rs.getString(7);
                Usuarios[i][7]=rs.getString(8);
                Usuarios[i][8]=rs.getString(9);
                Usuarios[i][9]=rs.getString(10);
                Usuarios[i][10]=rs.getString(11);
                Usuarios[i][11]=rs.getString(12);
                i++;
            }
            return Usuarios;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Object[][] ListarVencimientosTipo(String x1,String x2,String servicio)
    {
        Object Usuarios[][]=null;
        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL listarVencimientosTipo('"+x1+"','"+x2+"','"+servicio+"')}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Usuarios= new Object[count][8];
            int i=0;
            while(rs.next()){
                Usuarios[i][0]=rs.getString(1);
                Usuarios[i][1]=rs.getString(2);
                Usuarios[i][2]=rs.getString(3);
                Usuarios[i][3]=rs.getString(4);
                Usuarios[i][4]=rs.getString(5);
                Usuarios[i][5]=rs.getString(6);
                Usuarios[i][6]=rs.getString(7);
                Usuarios[i][7]=rs.getString(8);
                i++;
            }
            return Usuarios;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Object[][] ListarRellamadasTipo(String x1,String servicio)
    {
        Object Usuarios[][]=null;
        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL listarRellamadasObs('"+x1+"','"+servicio+"')}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Usuarios= new Object[count][12];
            int i=0;
            while(rs.next()){
                Usuarios[i][0]=rs.getString(1);
                Usuarios[i][1]=rs.getString(2);
                Usuarios[i][2]=rs.getString(3);
                Usuarios[i][3]=rs.getString(4);
                Usuarios[i][4]=rs.getString(5);
                Usuarios[i][5]=rs.getString(6);
                Usuarios[i][6]=rs.getString(7);
                Usuarios[i][7]=rs.getString(8);
                Usuarios[i][8]=rs.getString(9);
                Usuarios[i][9]=rs.getString(10);
                Usuarios[i][10]=rs.getString(11);
                Usuarios[i][11]=rs.getString(12);
                i++;
            }
            return Usuarios;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Object[][] ListarRellamadas()
    {
        Object Usuarios[][]=null;
        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL listarRellamadas()}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Usuarios= new Object[count][10];
            int i=0;
            while(rs.next()){
                Usuarios[i][0]=rs.getString(1);
                Usuarios[i][1]=rs.getString(2);
                Usuarios[i][2]=rs.getString(3);
                Usuarios[i][3]=rs.getString(4);
                Usuarios[i][4]=rs.getString(5);
                Usuarios[i][5]=rs.getString(6);
                Usuarios[i][6]=rs.getString(7);
                Usuarios[i][7]=rs.getString(8);
                Usuarios[i][8]=rs.getString(9);
                Usuarios[i][9]=rs.getString(10);
                i++;
            }
            return Usuarios;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int BusquedaNombre(String nombre)
    {

        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL BusquedaNombre('"+nombre+"')}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            rs.relative(current);
            int Per_Id=rs.getInt(1);
            return Per_Id;

        } catch (Exception ex) {
           //JOptionPane.showMessageDialog(null, ex);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public void ActualizarDatos(String telefono, int idPersona)
    {

        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL ActualizarDatos('"+telefono+"',"+idPersona+")}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            rs.relative(current);

        } catch (Exception ex) {
          // JOptionPane.showMessageDialog(null, ex);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ActualizarDatosPlaca(String comentario, String citv, int idPersona)
    {

        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL ActualizarCall('"+comentario+"','"+citv+"',"+idPersona+")}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            rs.relative(current);

        } catch (Exception ex) {
          //  JOptionPane.showMessageDialog(null, ex);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     public void Actualizar(String telefono,String celular,String correo,int idPersona)
    {

        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL ActualizarPersona('"+telefono+"','"+celular+"','"+correo+"',"+idPersona+")}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            rs.relative(current);

        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
