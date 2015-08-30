/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import PRESENTACION.frmInicio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Reporte {
    
    public Reporte() {
    }
    
    public void mostrarCertificado(String fecha)
    {
        
       Connection cnn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña); 
        try{
            
            String archivo=System.getProperty("user.dir")+"/src/REPORTES/rptCertificado.jasper";
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
            }
            catch(JRException ex)
            {
                    System.out.println("Error cargando archivo jasperReport:"+ex.getMessage());
                    System.exit(3);
            }
            Map parametro=new HashMap();
            int idCertificado=Integer.parseInt(fecha);
            parametro.put("idCertificado", idCertificado);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parametro,cnn);
            JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
            jasperViewer.setTitle("Certificado de Inspección Técnica Vehicular");
            jasperViewer.setVisible(true);
        }catch(Exception ex)
        {
            System.out.println("Mensaje de Error:"+ex.getMessage());
        }
        finally{
            try {
                cnn.close();
            } catch (SQLException ex) {
            }
        }
    }
    
    public void mostrarCertificadoParticular(String fecha)
    {
       Connection cnn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña); 
        try{
            
            String archivo=System.getProperty("user.dir")+"/src/REPORTES/rptCertificadoParticular.jasper";
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
            }
            catch(JRException ex)
            {
                    System.out.println("Error cargando archivo jasperReport:"+ex.getMessage());
                    System.exit(3);
            }
            Map parametro=new HashMap();
            int idCertificado=Integer.parseInt(fecha);
            parametro.put("idCertificado", idCertificado);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parametro,cnn);
            JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
            jasperViewer.setTitle("Certificado de Inspección Técnica Vehicular");
            jasperViewer.setVisible(true);
        }catch(Exception ex)
        {
            System.out.println("Mensaje de Error:"+ex.getMessage());
        }
        finally{
            try {
                cnn.close();
            } catch (SQLException ex) {
            }
        }
    }
    
    public void mostrarInformeTecnico(String fecha)
    {
       Connection cnn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña); 
        try{
            
            String archivo=System.getProperty("user.dir")+"/src/REPORTES/rptInformeTecnico.jasper";
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
            }
            catch(JRException ex)
            {
                    System.out.println("Error cargando archivo jasperReport:"+ex.getMessage());
                    System.exit(3);
            }
            Map parametro=new HashMap();
            int idCertificado=Integer.parseInt(fecha);
            parametro.put("idCertificado", idCertificado);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parametro,cnn);
            JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
            jasperViewer.setTitle("Informe de Inspección Técnica Vehicular");
            jasperViewer.setVisible(true);
        }catch(Exception ex)
        {
            System.out.println("Mensaje de Error:"+ex.getMessage());
        }
        finally{
            try {
                cnn.close();
            } catch (SQLException ex) {
            }
        }
    }


    public void mostrarReporteVencimientos(String f)
    {
       Connection cnn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña); 
        try{

            //String archivo=getClass().getResource("/Reportes/report1.jasper").toString();
            String archivo=System.getProperty("user.dir")+"/src/REPORTES/RVencimientos.jasper";
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
            parametro.put("f", f);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parametro,cnn);
            JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
            jasperViewer.setTitle("Lista de Vencimientos");
            jasperViewer.setVisible(true);
        }catch(Exception ex)
        {
            System.out.println("Mensaje de Error:"+ex.getMessage());
        }
        finally{
            try {
                cnn.close();
            } catch (SQLException ex) {
            }
        }
    }
   
   
}
