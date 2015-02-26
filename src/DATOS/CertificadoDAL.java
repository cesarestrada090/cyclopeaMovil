/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
import ENTIDADES.Vehiculo;
import PRESENTACION.frmInicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
   
    
     public List obtenerListaModelo()
    {
        
        List valoresCombo= new ArrayList<String>();
        
        try{
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st=cn.createStatement();
            rs=st.executeQuery("select distinct nombre from codigos where tipo='MKC' and nombre!='' order by nombre ;");
            int i=0;
            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }
    public List obtenerListaMarca()
    {
        
        List valoresCombo= new ArrayList<String>();
        try{
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st=cn.createStatement();
            rs=st.executeQuery("select distinct nombre from codigos where tipo='MKA' order by nombre;");
            int i=0;
            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }
    
    public List obtenerListaCombustible()
    {
        
        List valoresCombo= new ArrayList<String>();
        try{
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st=cn.createStatement();
            rs=st.executeQuery("select distinct nombre from codigos where tipo='COM' order by nombre ;");
            int i=0;
            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }
    
    public List obtenerListaCarroceria()
    {
        
        List valoresCombo= new ArrayList<String>();
        try{
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st=cn.createStatement();
            rs=st.executeQuery("select distinct nombre from codigos where tipo='CAR' order by nombre;");

            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }
    
    public List obtenerListaCategorias()
    {
        
        List valoresCombo= new ArrayList<String>();
        try{
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st=cn.createStatement();
            rs=st.executeQuery("select distinct nombre from codigos where tipo='CAT' order by nombre;");

            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }
    
public Object[][] listarAllCertificados()
    {        Object[][] Certificados=null;
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL listarAllCertificados()}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);
            
            Certificados= new Object[count][50];
            int i=0;
            while(rs.next()){

                Certificados[i][0]=rs.getString(1);
                Certificados[i][1]=rs.getString(2);
                Certificados[i][2]=rs.getString(3);
                Certificados[i][3]=rs.getString(4);
                Certificados[i][4]=rs.getString(5);
                Certificados[i][5]=rs.getString(6);
                Certificados[i][6]=rs.getString(7);
                Certificados[i][7]=rs.getString(8);
                Certificados[i][8]=rs.getString(9);
                Certificados[i][9]=rs.getString(10);
                Certificados[i][10]=rs.getString(11);
                Certificados[i][11]=rs.getString(12);
                Certificados[i][12]=rs.getString(13);
                Certificados[i][13]=rs.getString(14);
                Certificados[i][14]=rs.getString(15);
                Certificados[i][15]=rs.getString(16);
                Certificados[i][16]=rs.getString(17);
                Certificados[i][17]=rs.getString(18);
                Certificados[i][18]=rs.getString(19);
                Certificados[i][19]=rs.getString(20);
                Certificados[i][20]=rs.getString(21);
                Certificados[i][21]=rs.getString(22);
                Certificados[i][22]=rs.getString(23);
                Certificados[i][23]=rs.getString(24);
                Certificados[i][24]=rs.getString(25);
                Certificados[i][25]=rs.getString(26);
                Certificados[i][26]=rs.getString(27);
                Certificados[i][27]=rs.getString(28);
                Certificados[i][28]=rs.getString(29);
                Certificados[i][29]=rs.getString(30);
                Certificados[i][30]=rs.getString(31);
                Certificados[i][31]=rs.getString(32);
                Certificados[i][32]=rs.getString(33);
                Certificados[i][33]=rs.getString(34);
                Certificados[i][34]=rs.getString(35);
                Certificados[i][35]=rs.getString(36);
                Certificados[i][36]=rs.getString(37);
                Certificados[i][37]=rs.getString(38);
                Certificados[i][38]=rs.getString(39);
                Certificados[i][39]=rs.getString(40);
                Certificados[i][40]=rs.getString(41);
                Certificados[i][41]=rs.getString(42);                
                Certificados[i][42]=rs.getString(43);
                Certificados[i][43]=rs.getString(44);
                Certificados[i][44]=rs.getString(45);
                Certificados[i][45]=rs.getString(46);
                Certificados[i][46]=rs.getString(47);
                Certificados[i][47]=rs.getString(48);
                Certificados[i][48]=rs.getString(49);
                Certificados[i][49]=rs.getString(50);
                
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
