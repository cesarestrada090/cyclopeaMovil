/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
import ENTIDADES.Frenometro;
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
public class FrenometroDAL {

    Connection cn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public boolean registrarDatosFrenometro(Frenometro v) {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            //cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "bdnuevamovil", "root", "123456");

            String sentencia =
                    "insert into frenometro("
                    + "idEje,pesoServicio,fuerzaDerServicio,fuerzaIzqServicio,desequilibrioServicio,resultadoEjeServicio," //6
                    + "eficienciaServicio,resultadoServicio,fuerzaDerEstacionamiento," //3
                    + "fuerzaIzqEstacionamiento,desequilibrioEstacionamiento,resultadoEjeEstacionamiento," //3
                    + "eficienciaEstacionamiento,resultadoEstacionamiento,fuerzaDerEmergencia,fuerzaIzqEmergencia,desequilibrioEmergencia," //5
                    + "resultadoEjeEmergencia,eficienciaEmergencia,resultadoEmergencia,idCertificado)"//3
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1, v.getnEjes());
            ps.setDouble(2, v.getPesoServicio());
            ps.setDouble(3, v.getFuerzaServicioDer());
            ps.setDouble(4, v.getFuerzaServicioIzq());
            ps.setDouble(5, v.getDesequilibrioServicio());
            ps.setString(6, v.getResultadoEjeServicio());

            ps.setDouble(7, v.getEficienciaServicio());
            ps.setString(8, v.getResultadoGlobalServicio());
            ps.setDouble(9, v.getFuerzaEstDer());
            
            ps.setDouble(10, v.getFuerzaEstIzq());
            ps.setDouble(11, v.getDesequilibrioEstacionamiento());
            ps.setString(12, v.getResultadoEjeEstacionamiento());
            
            ps.setDouble(13, v.getEficienciaEst());
            ps.setString(14, v.getResultadoGlobalEstacionamiento());
            ps.setDouble(15, v.getFuerzaEmerDer());
            ps.setDouble(16, v.getFuerzaEmerIzq());
            ps.setDouble(17, v.getDesequilibrioEmergencia());
            
            ps.setString(18, v.getResultadoEjeEmergencia());
            ps.setDouble(19, v.getEficienciaEmergencia());
            ps.setString(20, v.getResultadoGlobalEmergencia());
            ps.setInt(21, v.getIdCerticado());
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
