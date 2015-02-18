/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;
import PRESENTACION.frmInicio;
import ENTIDADES.exportarexcel;
import java.sql.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */

public class exportarexcelDAL {
    Connection cn=null;
    Statement st= null;
    PreparedStatement ps=null;
    ResultSet rs= null;
    CallableStatement cs= null;

    public int obtenerNumeroRegistroUsuario()
    {
        try{
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            st=cn.createStatement();
            rs=st.executeQuery("select max(usu_id) from usuario;");
            while (rs.next()) {
                String numero= rs.getString(1);
                if(numero!=null){
                    return Integer.parseInt(numero);
                }
                else
                    return 0;
            }
        }catch(SQLException ex){
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(exportarexcelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    public Object[][] ListarUsuarios()
    {
        Object Usuarios[][]=null;
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL listarUsuarios()}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);
            
            Usuarios= new Object[count][7];
            int i=0;
            while(rs.next()){

                Usuarios[i][0]=rs.getInt(1);
                Usuarios[i][1]=rs.getString(2);
                Usuarios[i][2]=rs.getString(3);
                Usuarios[i][3]=rs.getString(4);
                Usuarios[i][4]=rs.getString(5);
                Usuarios[i][5]=rs.getString(6);
                Usuarios[i][6]=rs.getString(7);
                i++;
            }
            return Usuarios;
        } catch (Exception ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();                
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(exportarexcelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public boolean ListarBusquedaUsuarios(int codigo)
    {
        Object Usuarios[][]=null;
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL BusquedaUsuarioCodigo("+codigo+")}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Usuarios= new Object[count][7];
            int i=0;
            while(rs.next()){

                Usuarios[i][0]=rs.getInt(1);
                Usuarios[i][1]=rs.getString(2);
                Usuarios[i][2]=rs.getString(3);
                Usuarios[i][3]=rs.getString(4);
                Usuarios[i][4]=rs.getString(5);
                Usuarios[i][5]=rs.getString(6);
                Usuarios[i][6]=rs.getString(7);
                i++;
            }
            return true;
        } catch (Exception ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(exportarexcelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean ListarBusquedaPersonas(int codigo)
    {
        Object Personas[][]=null;
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL BusquedaPersonaCodigo("+codigo+")}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);

            Personas= new Object[count][8];
            int i=0;
            while(rs.next()){

                Personas[i][0]=rs.getInt(1);
                Personas[i][1]=rs.getString(2);
                Personas[i][2]=rs.getString(3);
                Personas[i][3]=rs.getString(4);
                Personas[i][4]=rs.getString(5);
                Personas[i][5]=rs.getString(6);
                Personas[i][6]=rs.getString(7);
                Personas[i][7]=rs.getInt(7);
                i++;
            }
            return true;
        } catch (Exception ex) {
            return false;
            //showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(exportarexcelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public ArrayList<exportarexcel> listarUsuarios()
    {
        ArrayList<exportarexcel> ListaUsuarios = new ArrayList<exportarexcel>();
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            st=cn.createStatement();
            String consulta="select * from usuario order by usu_id asc;";
            rs=st.executeQuery(consulta);
            while(rs.next()){
                ListaUsuarios.add(new exportarexcel(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            return ListaUsuarios;
        } catch (Exception ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(exportarexcelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public boolean registrarDatosUsuario(exportarexcel u)
    {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="insert into usuario values(?,?,?)";
            ps=cn.prepareStatement(sentencia);
            ps.setInt(1,u.getId());
            ps.setString(2,u.getCodigo());
            ps.setString(3,u.getNombres());
            ps.executeUpdate();
            return true;
            //"Un usuario ya ha sido registrado con la ubicación seleccionada"
        } catch (SQLException ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
            return false;
        }
        finally{
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(exportarexcelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public boolean ModificarDatosUsuario(exportarexcel u)
    {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="update usuario set usu_codigo=?,usu_nombre=?,ub_id=? where usu_id=?";
            ps=cn.prepareStatement(sentencia);
            ps.setString(1,u.getCodigo());
            ps.setString(2,u.getNombres());
            ps.setInt(5,u.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
            return false;
        }
        finally{
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(exportarexcelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
