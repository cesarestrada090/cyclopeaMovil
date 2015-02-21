/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
import ENTIDADES.TarjetaPropiedad;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class TarjetaPropiedadDAL {

    Connection cn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public boolean registrarDatosTarjeta(TarjetaPropiedad v) {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "restfullcyclopea", "root", "123456");
            String sentencia = "insert into tarjetapropiedad(placa,ntarjeta,razon1,domicilio,idclase,idmarca,fabricacion,"
                    + "idmodelo,version,idcombustible,idcarroceria,ejes,colores,nmotor,cilindros,nserie,vin,ruedas,pasajeros,asientos,"
                    + "peso_seco,peso_bruto,longitud,altura,ancho,carga_util,estado,fecha,nruedas)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);

            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getnTarjeta());
            ps.setString(3, v.getNombrePropietario());
            ps.setString(4, v.getDomicilio());
            ps.setString(5, "");
            ps.setString(6, v.getIdMarca());
            ps.setInt(7, v.getFabricacion());
            ps.setString(8, v.getIdModelo());
            ps.setString(9, v.getVersion());
            ps.setString(10, v.getIdCombustible());
            ps.setString(11, v.getIdCarroceria());
            ps.setInt(12, v.getEjes());
            ps.setString(13, v.getColores());
            ps.setObject(14, v.getnMotor());
            ps.setInt(15, v.getCilindros());
            ps.setString(16, v.getnSerie());
            ps.setString(17, v.getVin());
            ps.setObject(18, v.getRuedas());
            ps.setInt(19, v.getPasajeros());
            ps.setInt(20, v.getAsientos());
            ps.setDouble(21, v.getPesoSeco());
            ps.setDouble(22, v.getPesoBruto());
            ps.setDouble(23, v.getLongitud());
            ps.setDouble(24, v.getAltura());
            ps.setDouble(25, v.getAncho());
            ps.setDouble(26, v.getCargaUtil());
            ps.setString(27, v.getEstado());
            ps.setObject(28, v.getFecha());
            ps.setInt(29, v.getnRuedas());
            ps.executeUpdate();
            return true;
            //"Un usuario ya ha sido registrado con la ubicación seleccionada"
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Datos Registrados con éxito", 0);
            return false;
        } finally {
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
