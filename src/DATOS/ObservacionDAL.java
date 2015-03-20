/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
import ENTIDADES.Observacion;
import ENTIDADES.Vehiculo;
import PRESENTACION.frmInicio;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author pc
 */
public class ObservacionDAL {

    Connection cn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public boolean registrarDatosObservacion(Observacion v) {
        try {
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            //cn=(Connection) Conexion.obtenerConexionMySQL("Localhost","bdnuevamovil","root","123456");
            String sentencia = "insert into observacion(idCertificado,codigoObservacion,interpretacion,calificacion) values(?,?,?,?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1, v.getIdCertificado());
            ps.setString(2, v.getCodigoObservacion());
            ps.setString(3, v.getInterpretacion());
            ps.setString(4, v.getCalificacion());

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
                System.out.print(ex.getMessage());
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String obtenerDescripcion(String codigo) {
        try {
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            st = cn.createStatement();
            rs = st.executeQuery("select nombre from defectos where id='" + codigo + "' limit 1;");

            if (rs.next()) {
                String descripcion = rs.getString(1);
                if (descripcion != null) {
                    return descripcion;
                } else {
                    return "No Conocida";
                }
            }
        } catch (SQLException ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
            try {
                cn.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CallcenterDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "No Conocida";
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
