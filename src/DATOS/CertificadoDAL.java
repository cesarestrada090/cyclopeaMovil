/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
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
public class CertificadoDAL {

    Connection cn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public boolean registrarDatosCertificado(Certificado v) {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            //cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "bdnuevamovil", "root", "123456");
            
            String sentencia = "insert into certificado("
                    + "tipoDocTransp,numDocTransp,tipoDocEvaluar,numDocEvaluar,"
                    + "claseAutorizacion,resultado,vigencia,fecInspeccion,fecVencimiento,"
                    + "cIdentidadCert,codLocal,ubigeo,idTarjeta)"
                    + ""
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);
            ps.setString(1, v.getTipoDocTransp());
            ps.setString(2, v.getNumDocTransp());
            ps.setString(3, v.getTipoDocEvaluar());
            ps.setString(4, v.getNumDocEvaluar());
            ps.setString(5, v.getClaseAutorizacion());
            ps.setInt(6, v.getResultado());
            ps.setString(7, v.getVigencia());
            ps.setObject(8, v.getFecInspeccion());
            ps.setObject(9, v.getFecVencimiento());
            ps.setString(10, v.getcIdentidadCert());
            ps.setString(11, v.getCodLocal());
            ps.setString(12, v.getUbigeo());
            ps.setInt(13, v.getIdTarjeta());
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
    public int obtenerNumeroRegistroCertificado()
    {
        try{
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st=cn.createStatement();
            rs=st.executeQuery("select max(idCertificado) from certificado;");
            if (rs.next()) {
                Integer numero= rs.getInt(1);
                if(numero!=null){
                    return numero;
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
                Logger.getLogger(CallcenterDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    public Object[][] listarCertificados( String placa)
    {
        Object[][] Certificados=null;
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL listarCerticados('"+placa+"')}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);
            
            Certificados= new Object[count][5];
            int i=0;
            while(rs.next()){

                Certificados[i][0]=rs.getString(1);
                Certificados[i][1]=rs.getString(2);
                Certificados[i][2]=rs.getString(3);
                Certificados[i][3]=rs.getString(4);
                Certificados[i][4]=rs.getString(5);
                i++;
            }
            return Certificados;
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
