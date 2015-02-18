/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;
import ENTIDADES.Usuario;
import PRESENTACION.frmInicio;
import java.sql.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */

public class UsuarioDAL {
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
            rs=st.executeQuery("select max(idusuarios) from usuario;");
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
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
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
            
            Usuarios= new Object[count][2];
            int i=0;
            while(rs.next()){

                Usuarios[i][0]=rs.getString(1);
                Usuarios[i][1]=rs.getString(2);
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
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int BusquedaUsuarios(String usuario,String password)
    {
        
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL BusquedaUsuario('"+usuario+"','"+password+"')}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();            
            rs.relative(current);
            int tipoUsuario=rs.getInt(1);
            return tipoUsuario;

        } catch (Exception ex) {
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
        return 0;
    }

    
    public ArrayList<Usuario> listarUsuarios()
    {
        ArrayList<Usuario> ListaUsuarios = new ArrayList<Usuario>();
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            st=cn.createStatement();
            String consulta="select * from usuario order by usu_id asc;";
            rs=st.executeQuery(consulta);
            while(rs.next()){
                ListaUsuarios.add(new Usuario(rs.getString(1),rs.getString(2),rs.getInt(3)));
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
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public boolean registrarDatosUsuario(Usuario u)
    {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="insert into usuario values(?,?,?)";
            ps=cn.prepareStatement(sentencia);
            ps.setString(1,u.getUsuario());
            ps.setString(2,u.getContrasenia());
            ps.setInt(3,u.getTipo());
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
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public boolean ModificarDatosUsuario(Usuario u)
    {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="update usuario set usu_codigo=?,usu_nombre=?,ub_id=? where usu_id=?";
            ps=cn.prepareStatement(sentencia);
            ps.setString(1,u.getContrasenia());
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
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean EliminarUsuario(String codusu) {
         try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="delete from usuario where Usu_Id like '"+codusu+"'";
            ps=cn.prepareStatement(sentencia);
            ps.executeUpdate();
            return true;
            //"Un usuario ha sido eliminado de la base de datos"
        } catch (SQLException ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
            return false;
        }
        finally{
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
