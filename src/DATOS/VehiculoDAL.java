/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;
import ENTIDADES.Vehiculo;
import PRESENTACION.frmInicio;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */

public class VehiculoDAL {
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
            rs=st.executeQuery("select max(Veh_Id) from vehiculo;");
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
                Logger.getLogger(VehiculoDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int BusquedaPlacas(String placa)
    {

        try {
            cn= (Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=(CallableStatement) cn.prepareCall("{CALL BusquedadePlacas('"+placa+"')}");
            rs=cs.executeQuery();
            int current=rs.getRow(); rs.last();
            rs.relative(current);
            int Veh_Id=rs.getInt(1);
            return Veh_Id;

        } catch (Exception ex) {
           //JOptionPane.showMessageDialog(null, ex);
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
        return 0;
    }


    public boolean registrarDatosVehiculo(Vehiculo v)
    {
        try {
            cn=(Connection) Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="insert into vehiculo values(?,?,?,?,?,?,?,?)";
            ps=(PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1,v.getIdVehiculo());
            ps.setString(2,v.getPlaca());
            ps.setString(3,v.getAnioFabricacion());
            ps.setString(4,v.getMarca());
            ps.setString(5,v.getModelo());
            ps.setString(6,v.getTiposervicio());
            ps.setInt(7,v.getIdPersona());
            ps.setInt(8,v.getEstado());
            ps.executeUpdate();
            return true;
            //"Un usuario ya ha sido registrado con la ubicación seleccionada"
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Datos Registrados con éxito",0);
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

    public int BusquedaPlaca(String usuario,String password)
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
            //JOptionPane.showMessageDialog(null, ex);
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

    public boolean ModificarDatosVehiculo(int idVehiculo)
    {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="update vehiculo set estado=1 where Veh_Id='"+idVehiculo+"'";
            ps=cn.prepareStatement(sentencia);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",0);
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



/*
 *
 * DELIMITER $$

DROP PROCEDURE IF EXISTS `BusquedaPlaca` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BusquedaPlaca`(in placa varchar(8))
BEGIN
set placa=concat(placa);

  select c.Call_Id as placa from callcenter c
  where c.Call_Id like placa;

END $$

DELIMITER ;
 */
