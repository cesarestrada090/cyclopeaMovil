/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.Certificado;
import ENTIDADES.Fotografias;
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
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            
            String sentencia = "insert into certificado(idCertificado," //1
                    + "tipoDocTransp,numDocTransp,tipoDocEvaluar,numDocEvaluar,"  //4
                    + "claseAutorizacion,resultado,vigencia,fecInspeccion,fecVencimiento," //5
                    + "cIdentidadCert,codLocal,ubigeo,idTarjeta," //4
                    + "texto,titulo,claseAut,idInforme,idExpediente,numCertificado,numInforme,numExpediente)" //8
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            ps = (PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1, v.getIdCertificado());
            ps.setString(2, v.getTipoDocTransp());
            ps.setString(3, v.getNumDocTransp());
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
            ps.setInt(14, v.getIdTarjeta());
            ps.setString(15, v.getTexto());
            ps.setString(16, v.getTitulo());
            ps.setString(17, v.getClaseAut());
            ps.setInt(18, v.getIdInforme());
            ps.setInt(19, v.getIdExpediente());
            ps.setString(20, v.getNumCertificado());
            ps.setString(21, v.getNumInforme());
            ps.setString(22, v.getNumExpediente());
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

    public int obtenerNumeroRegistroCertificado() {
        try {
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st = cn.createStatement();
            rs = st.executeQuery("select max(idCertificado) from certificado;");
            if (rs.next()) {
                Integer numero = rs.getInt(1);
                if (numero != null) {
                    return numero;
                } else {
                    return 0;
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
        return 0;
    }

    public Object[][] listarCertificados(String placa) {
        Object[][] Certificados = null;
        try {
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            cs = cn.prepareCall("{CALL listarCerticados('" + placa + "')}");
            rs = cs.executeQuery();

            int current = rs.getRow();
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();
            rs.relative(current);

            Certificados = new Object[count][5];
            int i = 0;
            while (rs.next()) {

                Certificados[i][0] = rs.getString(1);
                Certificados[i][1] = rs.getString(2);
                Certificados[i][2] = rs.getString(3);
                Certificados[i][3] = rs.getString(4);
                Certificados[i][4] = rs.getString(5);
                i++;
            }
            return Certificados;
        } catch (Exception ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
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

    public List obtenerListaModelo() {

        List valoresCombo = new ArrayList<String>();

        try {
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st = cn.createStatement();
            rs = st.executeQuery("select distinct nombre from codigos where tipo='MKC' and nombre!='' order by nombre ;");
            int i = 0;
            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }

    public List obtenerListaMarca() {

        List valoresCombo = new ArrayList<String>();
        try {
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st = cn.createStatement();
            rs = st.executeQuery("select distinct nombre from codigos where tipo='MKA' order by nombre;");
            int i = 0;
            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }

    public List obtenerListaCombustible() {

        List valoresCombo = new ArrayList<String>();
        try {
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st = cn.createStatement();
            rs = st.executeQuery("select distinct nombre from codigos where tipo='COM' order by nombre ;");
            int i = 0;
            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }

    public List obtenerListaCarroceria() {

        List valoresCombo = new ArrayList<String>();
        try {
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st = cn.createStatement();
            rs = st.executeQuery("select distinct nombre from codigos where tipo='CAR' order by nombre;");

            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }

    public List obtenerListaCategorias() {

        List valoresCombo = new ArrayList<String>();
        try {
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st = cn.createStatement();
            rs = st.executeQuery("select distinct nombre from codigos where tipo='CAT' order by nombre;");

            while (rs.next()) {
                valoresCombo.add(rs.getString(1));
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
        return valoresCombo;
    }

    public Object[][] listarAllCertificados() {
        Object[][] Certificados = null;
        try {
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            cs = cn.prepareCall("{CALL listarAllCertificados()}");
            rs = cs.executeQuery();

            int current = rs.getRow();
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();
            rs.relative(current);

            Certificados = new Object[count][52];
            int i = 0;
            while (rs.next()) {

                Certificados[i][0] = rs.getString(1);
                Certificados[i][1] = rs.getString(2);
                Certificados[i][2] = rs.getString(3);
                Certificados[i][3] = rs.getString(4);
                Certificados[i][4] = rs.getString(5);
                Certificados[i][5] = rs.getString(6);
                Certificados[i][6] = rs.getString(7);
                Certificados[i][7] = rs.getString(8);
                Certificados[i][8] = rs.getString(9);
                Certificados[i][9] = rs.getString(10);
                Certificados[i][10] = rs.getString(11);
                Certificados[i][11] = rs.getString(12);
                Certificados[i][12] = rs.getString(13);
                Certificados[i][13] = rs.getString(14);
                Certificados[i][14] = rs.getString(15);
                Certificados[i][15] = rs.getString(16);
                Certificados[i][16] = rs.getString(17);
                Certificados[i][17] = rs.getString(18);
                Certificados[i][18] = rs.getString(19);
                Certificados[i][19] = rs.getString(20);
                Certificados[i][20] = rs.getString(21);
                Certificados[i][21] = rs.getString(22);
                Certificados[i][22] = rs.getString(23);
                Certificados[i][23] = rs.getString(24);
                Certificados[i][24] = rs.getString(25);
                Certificados[i][25] = rs.getString(26);
                Certificados[i][26] = rs.getString(27);
                Certificados[i][27] = rs.getString(28);
                Certificados[i][28] = rs.getString(29);
                Certificados[i][29] = rs.getString(30);
                Certificados[i][30] = rs.getString(31);
                Certificados[i][31] = rs.getString(32);
                Certificados[i][32] = rs.getString(33);
                Certificados[i][33] = rs.getString(34);
                Certificados[i][34] = rs.getString(35);
                Certificados[i][35] = rs.getString(36);
                Certificados[i][36] = rs.getString(37);
                Certificados[i][37] = rs.getString(38);
                Certificados[i][38] = rs.getString(39);
                Certificados[i][39] = rs.getString(40);
                Certificados[i][40] = rs.getString(41);
                Certificados[i][41] = rs.getString(42);
                Certificados[i][42] = rs.getString(43);
                Certificados[i][43] = rs.getString(44);
                Certificados[i][44] = rs.getString(45);
                Certificados[i][45] = rs.getString(46);
                Certificados[i][46] = rs.getString(47);
                Certificados[i][47] = rs.getString(48);
                Certificados[i][48] = rs.getString(49);
                Certificados[i][49] = rs.getString(50);
                Certificados[i][50] = rs.getString(51);
                Certificados[i][51] = rs.getString(52);
//                if (rs.getBinaryStream(53)!=null){
//                    Certificados[i][52] = rs.getBinaryStream(53);
//                }else{
//                    Certificados[i][52] = "SI";
//                }
                //Certificados[i][52] = rs.getBinaryStream(53);
                i++;
            }
            return Certificados;
        } catch (Exception ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
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

    public Object[][] listarAllObservaciones() {
        Object[][] Certificados = null;
        try {
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            cs = cn.prepareCall("{CALL listarObservaciones()}");
            rs = cs.executeQuery();

            int current = rs.getRow();
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();
            rs.relative(current);

            Certificados = new Object[count][4];
            int i = 0;
            while (rs.next()) {

                Certificados[i][0] = rs.getString(1);
                Certificados[i][1] = rs.getString(2);
                Certificados[i][2] = rs.getString(3);
                Certificados[i][3] = rs.getString(4);
                i++;
            }
            return Certificados;
        } catch (Exception ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
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

    public boolean grabarFotografias(Fotografias v) {
        try {
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            //cn = (Connection) Conexion.obtenerConexionMySQL("Localhost", "bdnuevamovil", "root", "123456");

            String sentencia = "INSERT INTO fotografia" + "(STR_TPDOCEVAL, STR_NUMDOCEVAL, BIN_FOTOGRAFIA, BIN_FOTOGRAFIA2, BIN_FOTOGRAFIA3) VALUES(?, ?, ?, ?, ?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);
            ps.setInt(1, v.getTipoDocEval());
            ps.setInt(2, v.getNumDocEval());
            if (v.getFoto1() != null) {
                ps.setBinaryStream(3, v.getFoto1());
            }else{
                ps.setBinaryStream(3, null);
            }
            if (v.getFoto2() != null) {
                ps.setBinaryStream(4, v.getFoto2());
            }
            else{
                ps.setBinaryStream(4, null);
            }
            if (v.getFoto3() != null) {
                ps.setBinaryStream(5, v.getFoto3());
            }
            else{
                 ps.setBinaryStream(5, null);
            }
            
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
    
    public Certificado obtenerIds() {

        Certificado objCertificado = new Certificado();
        try {
            cn = Conexion.obtenerConexionMySQL(frmInicio.n_servidor, frmInicio.n_baseDatos, frmInicio.n_usuario, frmInicio.n_contraseña);
            cs = cn.prepareCall("{CALL ObtenerIds()}");
            rs = cs.executeQuery();

            int current = rs.getRow();
            rs.last();
            rs.beforeFirst();
            rs.relative(current);


            while (rs.next()) {
                objCertificado.setIdCertificado(rs.getInt(1));
                objCertificado.setIdInforme(rs.getInt(2));
                objCertificado.setIdExpediente(rs.getInt(3));
            }
            return objCertificado;
            
        } catch (SQLException ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
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
    
    public int obtenerResultadoCertificado(int idInforme) {
        try {
            cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            st = cn.createStatement();
            rs = st.executeQuery("SELECT resultado FROM certificado t\n" +                                
                                "where idInforme='" + idInforme + "';");
            if (rs.next()) {
                Integer numero = rs.getInt(1);
                if (numero != null) {
                    return numero;
                } else {
                    return 0;
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
        return 0;
    }

}
