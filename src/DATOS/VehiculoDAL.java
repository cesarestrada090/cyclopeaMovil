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


    public boolean registrarDatosVehiculo(Vehiculo v)
    {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cn=(Connection) Conexion.obtenerConexionMySQL("Localhost","restfullcyclopea","root","123456");
//            String sentencia="insert into vehiculo("
//                    + "STR_PLACA,STR_CATEGORIA,STR_MOTOR,STR_SERIE,NUM_ANIO,NUM_EJES,NUM_RUEDAS,NUM_ASIENTOS,NUM_PSJR,"
//                    + "NUM_LARGO,NUM_ANCHO,NUM_ALTO,NUM_PNETO,NUM_CUTIL,NUM_PBRUTO,NUM_PUERTAS,"
//                    + "NUM_SALIDAS,NUM_CILINDROS,STR_COLOR,STR_ID_COMBUSTIBLE,STR_ID_MARCA,STR_ID_MODELO,STR_ID_CARROCERIA)"
//                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            String sentencia="insert into vehiculo("
                    + "STR_PLACA,STR_MOTOR,STR_SERIE,NUM_ANIO,NUM_EJES,NUM_RUEDAS,NUM_ASIENTOS,NUM_PSJR,"
                    + "NUM_LARGO,NUM_ANCHO,NUM_ALTO,NUM_PNETO,NUM_CUTIL,NUM_PBRUTO,"
                    + "NUM_CILINDROS,STR_COLOR,STR_ID_COMBUSTIBLE,STR_ID_MARCA,STR_ID_MODELO,STR_ID_CARROCERIA, INT_IDCERTIFICADO)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            ps=(PreparedStatement) cn.prepareStatement(sentencia);
            ps.setString(1,v.getPlaca());
//            ps.setString(2,v.getCategoria());
            ps.setString(2,v.getMotor());
            ps.setString(3,v.getSerie());
            ps.setInt(4,v.getAnio());
            ps.setInt(5,v.getnEjes());
            ps.setInt(6,v.getNumRuedas());
            ps.setInt(7,v.getNumAsientos());
            ps.setInt(8,v.getNumPasajeros());
            ps.setDouble(9,v.getLargo());
            ps.setDouble(10,v.getAncho());
            ps.setDouble(11,v.getAlto());
            ps.setDouble(12,v.getPesoNeto());
            ps.setDouble(13,v.getCargaUtil());
            ps.setDouble(14,v.getPesoBruto());
            //ps.setDouble(14,4); //NRO DE PUERTAS
////            ps.setInt(16,v.getNumPuertas());
////            ps.setInt(17,v.getNumSalidas());
            ps.setInt(15,v.getNumCilindros());
            ps.setString(16,v.getColor());
            ps.setString(17,v.getIdCombustible());
            ps.setString(18,v.getIdMarca());
            ps.setString(19,v.getIdModelo());
            ps.setString(20,v.getIdCarroceria());
            ps.setInt(21,v.getIdCertificado());
            
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
