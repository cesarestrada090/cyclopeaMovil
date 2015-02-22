/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;
import ENTIDADES.Certificado;
import ENTIDADES.Gasometro;
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

public class GasometroDAL {
    Connection cn=null;
    Statement st= null;
    PreparedStatement ps=null;
    ResultSet rs= null;
    CallableStatement cs= null;

    public boolean registrarDatosGasometro(Gasometro v)
    {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cn=(Connection) Conexion.obtenerConexionMySQL("Localhost","bdnuevamovil","root","123456");
            String sentencia="insert into gasometro(idCertificado,tipoEmision,tAceite,rpm,opacidad,coRalent,coco2Ralenti,hcRalentippm,coAcel,coCo2Acel,HCAcel,Resultado) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=(PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1,v.getIdCertificado());
            ps.setString(2,v.getTipoEmision());
            ps.setDouble(3,v.gettAceite());
            ps.setDouble(4,v.getRpm());
            ps.setDouble(5,v.getOpacidad());
            ps.setDouble(6,v.getCoRalent());
            ps.setDouble(7,v.getCoco2Ralenti());
            ps.setDouble(8,v.getHcRalentippm());
            ps.setDouble(9,v.getCoAcel());
            ps.setDouble(10,v.getCoCo2Acel());
            ps.setDouble(11,v.getHcAcel());
            ps.setString(12,v.getResultado());

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
