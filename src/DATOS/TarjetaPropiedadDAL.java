/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
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

    public boolean registrarDatosCertificado(Certificado v) {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "restfullcyclopea", "root", "123456");
            String sentencia = "insert into certificado(STR_TPDOCTRANSP,STR_NUMDOCTRANSP,STR_RZTRANSP,STR_TPDOCEVAL,STR_NUMDOCEVAL,STR_CLASAUTOR,INT_RESULTADO,STR_VIGENCIA,DTE_FECINSPECCION,"
                    + "DTE_FECVENCIMIENTO,STR_CDENTIDADCERT,STR_CDLOCAL,STR_UBIGEO)"
                    + ""
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);
            ps.setString(1, v.getTipoDocTransp());
            ps.setString(2, v.getNumDocTransp());
            ps.setString(3, v.getRazonTransp());
            ps.setString(4, v.getTipoDocEvaluar());
            ps.setString(5, v.getNumDocEvaluar());
            ps.setString(6, v.getClaseAutorizacion());
            ps.setInt(7, v.getResultado());
            ps.setString(8, v.getVigencia());
            ps.setObject(9, v.getFecInspeccion());
            ps.setObject(10, v.getFecVencimiento());
            ps.setString(11, v.getcIdentidadCert());
            ps.setString(12, v.getCodLocal());
            ps.setString(13, v.getUbigeo());
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
