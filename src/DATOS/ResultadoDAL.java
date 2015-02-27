/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
import ENTIDADES.Resultados;
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
public class ResultadoDAL {

    Connection cn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public boolean registrarDatosResultado(Resultados v) {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            //cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "restfullcyclopea", "root", "123456");

            String sentencia = "insert into resultados(IDCERTIFICADO,PRUEALI,PROFNEUMA,PRUEBLUCES,SUSPENSION,EMIGASES,FRESERV,FREESTAC,FREEMER,"
                    + "DISEJES,PISOS,OBSERVACIONES)"
                    + ""
                    + " values(?,?,?,?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1, v.getIdCertificado());
            ps.setInt(2, v.getPruebaAli());
            ps.setInt(3, v.getProfNeuma());
            ps.setInt(4, v.getPruebLuces());
            ps.setInt(5, v.getSuspension());
            ps.setInt(6, v.getEmigases());
            ps.setInt(7, v.getFreServ());
            ps.setInt(8, v.getFreeEstac());
            ps.setInt(9, v.getFreeEmer());
            ps.setDouble(10, v.getDisEjes());
            ps.setInt(11, v.getPisos());
            ps.setString(12, v.getObservacion());
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
