/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.EquipoCertificado;
import ENTIDADES.Observacion;
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

public class EquipoCertificadoDAL {
    Connection cn=null;
    Statement st= null;
    PreparedStatement ps=null;
    ResultSet rs= null;
    CallableStatement cs= null;

    public boolean registrarDatosEquipoCertificado(EquipoCertificado v)
    {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);

            cn=(Connection) Conexion.obtenerConexionMySQL("Localhost","bdnuevamovil","root","123456");
            String sentencia="insert into certificadoequipo(idCertificado,frenometroNumero,alineadorNumero,analizadorNumero,luxometroNumero,bancoSuspension)"
                    + " values(?,?,?,?,?,?)";
            ps=(PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1,v.getIdCertificado());
            ps.setInt(2,v.getNumFrenometro());
            ps.setInt(3,v.getNumAlineador());
            ps.setInt(4,v.getNumAnalizador());
            ps.setInt(5,v.getNumRegloscopio());
            ps.setInt(6,v.getNumBancoSuspension());

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
                System.out.print(ex.getMessage());
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
