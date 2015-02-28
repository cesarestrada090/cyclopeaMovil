/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;

import ENTIDADES.*;
import PRESENTACION.frmInicio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KACESE
 */
public class webServiceDAL {
    Connection cn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public boolean registrarDatosResultado(Resultados v) {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "restfullcyclopea", "root", "123456");

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
    
    public boolean registrarDatosObservacion(Observacion v)
    {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "restfullcyclopea", "root", "123456");
            String sentencia="insert into observaciones(STR_TPDOCEVAL,STR_NUMDOCEVAL,STR_CDOBSDETEC,STR_DSOBSDETEC) values(?,?,?,?)";
            ps=(PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1,v.getIdCertificado());
            ps.setString(2,v.getCodigoObservacion());
            ps.setString(3,v.getInterpretacion());
            ps.setString(4,v.getCalificacion());

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
    
     public boolean registrarDatosCertificado(Certificado v)
    {
        try {
            //cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "restfullcyclopea", "root", "123456");
            String sentencia="insert into certificado(STR_TPDCOTRANSP,STR_NUMDOCTRANSP,STR_RZTRANSP,STR_TPDOCEVAL,STR_NUMDOCEVAL,STR_CLASAUTOR,INT_RESULTADO,STR_VIGENCIA, DTE_FECINSPECCION,DTE_FECVENCIMIENTO,STR_CDENTIDADCERT,STR_CDLOCAL,STR_UBIGEO) values(?,?,?,?)";
            ps=(PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1,v.getIdCertificado());
            ps.setString(2,v.getTipoDocTransp());
            ps.setString(3,v.getNumDocTransp());
            ps.setString(4,v.getRazonTransp());
            ps.setString(5,v.getTipoDocEvaluar());
            ps.setString(6,v.getNumDocEvaluar());
            ps.setString(7,v.getClaseAutorizacion());
            ps.setInt(8,v.getResultado());
            ps.setString(9,v.getVigencia());
            ps.setDate(10, (Date) v.getFecInspeccion());
            ps.setDate(11, (Date) v.getFecVencimiento());
            ps.setString(12,v.getcIdentidadCert());
            ps.setString(13,v.getCodLocal());
            ps.setString(13,v.getUbigeo());

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
