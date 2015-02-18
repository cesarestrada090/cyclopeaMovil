/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DATOS;
import ENTIDADES.callcenter;
import PRESENTACION.frmInicio;
import java.sql.*;
import static javax.swing.JOptionPane.showMessageDialog;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.util.JRLoader;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author PC
 */

public class CallcenterDAL {
    Connection cn=null;
    Statement st= null;
    PreparedStatement ps=null;
    ResultSet rs= null;
    CallableStatement cs= null;

    public int obtenerNumeroRegistroCall()
    {
        try{
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            st=cn.createStatement();
            rs=st.executeQuery("select max(Call_Id) from callcenter;");
            while (rs.next()) {
                String numero= rs.getString(1);
                if(numero!=null){
                    return Integer.parseInt(numero);
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
    
    public boolean registrarDatosCall(callcenter c)
    {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            String sentencia="insert into callcenter values(?,?,?,?,?,?,?)";
            ps=cn.prepareStatement(sentencia);
            ps.setInt(1,c.getId());
            ps.setString(2,c.getFecha());
            ps.setString(3,c.getObservacion());
            ps.setString(4,c.getIdusu());
            ps.setInt(5,c.getIdVehiculo());
            ps.setString(6,c.getComentario());
            ps.setString(7, c.getCitv());
            ps.executeUpdate();
            return true;
            //"Un usuario ya ha sido registrado con la ubicación seleccionada"
        } catch (SQLException ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
            return false;
        }
        finally{
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CallcenterDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    



    /*public void reporteCallCenter(String fecha)
    {
        cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
        try{

            //String archivo=getClass().getResource("/Reportes/report1.jasper").toString();
            String archivo=System.getProperty("user.dir")+"/src/REPORTES/RCallcenter.jasper";
            System.out.println("Cargando desde:"+archivo);
            if(archivo==null)
            {
                System.out.println("No se pudo cargar archivo");
                System.exit(2);
            }
            JasperReport jasperReport=null;
            try
            {
                jasperReport=(JasperReport) JRLoader.loadObject(archivo);
            }catch(JRException ex)
            {
                    System.out.println("Error cargando archivo jasperReport:"+ex.getMessage());
                    System.exit(3);
            }
            Map parametro=new HashMap();
            parametro.put("codigo", fecha);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parametro,cn);
            JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
            jasperViewer.setTitle("Lista de Productos");
            jasperViewer.setVisible(true);
        }catch(Exception ex)
        {
            System.out.println("Mensaje de Error:"+ex.getMessage());
        }
        finally{
            try {
                cn.close();
            } catch (SQLException ex) {
            }
        }
    }*/

}
