/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jfrmInicio.java
 *
 * Created on 07/01/2013, 10:50:54 AM
 */
package PRESENTACION;

import NEGOCIO.CertificadoBL;
import java.awt.Dimension;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/*Librerías para trabajar con archivos excel*/
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author pc
 */
public class jfrmInicio extends javax.swing.JFrame {

    /**
     * Creates new form jfrmInicio
     */
    int password;

    public jfrmInicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane1.setAutoscrolls(true);

        jMenuBar1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jMenuBar1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/archive.png"))); // NOI18N
        jMenu1.setText("Archivo");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/bd import.png"))); // NOI18N
        jMenuItem8.setText("Exportar Data");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/bd import.png"))); // NOI18N
        jMenuItem4.setText("Generar Backup");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/duplicados.png"))); // NOI18N
        jMenuItem9.setText("Importar Data");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/clos.png"))); // NOI18N
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/users.png"))); // NOI18N
        jMenu2.setText("Usuarios");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/agregarusuario.png"))); // NOI18N
        jMenuItem2.setText("Registro Usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenuItem2.getAccessibleContext().setAccessibleName("");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/lista.png"))); // NOI18N
        jMenuItem5.setText("Listar Usuarios");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/delete user.png"))); // NOI18N
        jMenuItem6.setText("Eliminar Usuario");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/funciones.png"))); // NOI18N
        jMenu3.setText("Operaciones");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/nuevo registro.png"))); // NOI18N
        jMenuItem10.setText("Registrar Tarjeta de Propiedad");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/duplicados.png"))); // NOI18N
        jMenuItem11.setText("Tarjetas Preregistradas");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/clos.png"))); // NOI18N
        jMenuItem12.setText("Anular Certificado");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/reportes.png"))); // NOI18N
        jMenu4.setMnemonic('R');
        jMenu4.setText("Reportes");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/lista.png"))); // NOI18N
        jMenuItem7.setText("Certificados");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/manual.png"))); // NOI18N
        jMenu6.setText("Herramientas");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/calculadora.png"))); // NOI18N
        jMenuItem3.setText("Calculadora");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem3);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );

        jDesktopPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
                
        frmInicio inicio = new frmInicio();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        dispose();

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
        jfrmInicio.setDefaultLookAndFeelDecorated(false);
    }//GEN-LAST:event_formComponentHidden

    private void jMenuBar1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jMenuBar1AncestorAdded
        // TODO add your handling code here:
        
//        if(password==2)
//        {
//            jMenuItem12.setVisible(false);
//            jMenuItem2.setVisible(false);
//            jMenuItem14.setVisible(false);
//        }
//        else
//        {
//            jMenuItem13.setVisible(false);            
//        }        
    }//GEN-LAST:event_jMenuBar1AncestorAdded

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("calc");
            p.waitFor();
        } catch (IOException ioe) {
        } catch (InterruptedException ie) {
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        RegistrarUsuario ru = new RegistrarUsuario();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = ru.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (ru.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(ru);
            ru.setLocation(width, height);
            ru.show();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:}
        ListarUsuarios vv = new ListarUsuarios();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        EliminarUsuario vv = new EliminarUsuario();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    //Reporte Certificados
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        ListarCertificados vv = new ListarCertificados();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        FileOutputStream archivo = null;
        try {
            // TODO add your handling code here:
            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat hour = new SimpleDateFormat("HHmmss");
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
            String fecha=hour.format(now).toString()+date.format(now).toString();

            String rutaArchivo = System.getProperty("user.home") + "/CertificadosEmitidos"+fecha+".xls";
            /*Se crea el objeto de tipo File con la ruta del archivo*/
            File archivoXLS = new File(rutaArchivo);
            /*Si el archivo existe se elimina*/
            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            /*Se crea el archivo*/
            archivoXLS.createNewFile();
            /*Se crea el libro de excel usando el objeto de tipo Workbook*/
            Workbook libro = new HSSFWorkbook();
            archivo = new FileOutputStream(archivoXLS);
            
            /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
            Sheet hoja = libro.createSheet("Certificados Emitidos");

            /*Obtener listado de Certificados de la base de datos*/
            Object listaCertificados[][] = new CertificadoBL().listarAllCertificados();

            Row filaEncabezado = hoja.createRow(0);
            Cell celdaEncabezado = filaEncabezado.createCell(0);
            celdaEncabezado.setCellValue("idcertificado");
            celdaEncabezado = filaEncabezado.createCell(1);
            celdaEncabezado.setCellValue("tipoDocTransp");
            celdaEncabezado = filaEncabezado.createCell(2);
            celdaEncabezado.setCellValue("numDocEvaluar");
            celdaEncabezado = filaEncabezado.createCell(3);
            celdaEncabezado.setCellValue("claseAutorizacion");
            celdaEncabezado = filaEncabezado.createCell(4);
            celdaEncabezado.setCellValue("resultado");
            celdaEncabezado = filaEncabezado.createCell(5);
            celdaEncabezado.setCellValue("vigencia");
            celdaEncabezado = filaEncabezado.createCell(6);
            celdaEncabezado.setCellValue("fecInspeccion");
            celdaEncabezado = filaEncabezado.createCell(7);
            celdaEncabezado.setCellValue("fecVencimiento");
            celdaEncabezado = filaEncabezado.createCell(8);
            celdaEncabezado.setCellValue("cIdentidadCert");
            celdaEncabezado = filaEncabezado.createCell(9);
            celdaEncabezado.setCellValue("codLocal");
            celdaEncabezado = filaEncabezado.createCell(10);
            celdaEncabezado.setCellValue("ubigeo");
            celdaEncabezado = filaEncabezado.createCell(11);
            celdaEncabezado.setCellValue("idTarjeta");
            celdaEncabezado = filaEncabezado.createCell(12);
            celdaEncabezado.setCellValue("placa");
            celdaEncabezado = filaEncabezado.createCell(13);
            celdaEncabezado.setCellValue("ntarjeta");
            celdaEncabezado = filaEncabezado.createCell(14);
            celdaEncabezado.setCellValue("RazonSocial");
            celdaEncabezado = filaEncabezado.createCell(15);
            celdaEncabezado.setCellValue("domicilio");
            celdaEncabezado = filaEncabezado.createCell(16);
            celdaEncabezado.setCellValue("idclase");
            celdaEncabezado = filaEncabezado.createCell(17);
            celdaEncabezado.setCellValue("idmarca");
            celdaEncabezado = filaEncabezado.createCell(18);
            celdaEncabezado.setCellValue("fabricacion");
            celdaEncabezado = filaEncabezado.createCell(19);
            celdaEncabezado.setCellValue("idmodelo");
            celdaEncabezado = filaEncabezado.createCell(20);
            celdaEncabezado.setCellValue("version");
            celdaEncabezado = filaEncabezado.createCell(21);
            celdaEncabezado.setCellValue("idcombustible");
            celdaEncabezado = filaEncabezado.createCell(22);
            celdaEncabezado.setCellValue("idcarroceria");
            celdaEncabezado = filaEncabezado.createCell(23);
            celdaEncabezado.setCellValue("ejes");
            celdaEncabezado = filaEncabezado.createCell(24);
            celdaEncabezado.setCellValue("colores");
            celdaEncabezado = filaEncabezado.createCell(25);
            celdaEncabezado.setCellValue("nmotor");
            celdaEncabezado = filaEncabezado.createCell(26);
            celdaEncabezado.setCellValue("cilindros");
            celdaEncabezado = filaEncabezado.createCell(27);
            celdaEncabezado.setCellValue("nserie");
            celdaEncabezado = filaEncabezado.createCell(28);
            celdaEncabezado.setCellValue("vin");
            celdaEncabezado = filaEncabezado.createCell(29);
            celdaEncabezado.setCellValue("ruedas");
            celdaEncabezado = filaEncabezado.createCell(30);
            celdaEncabezado.setCellValue("pasajeros");
            celdaEncabezado = filaEncabezado.createCell(31);
            celdaEncabezado.setCellValue("asientos");
            celdaEncabezado = filaEncabezado.createCell(32);
            celdaEncabezado.setCellValue("peso_seco");
            celdaEncabezado = filaEncabezado.createCell(33);
            celdaEncabezado.setCellValue("peso_bruto");
            celdaEncabezado = filaEncabezado.createCell(34);
            celdaEncabezado.setCellValue("longitud");
            celdaEncabezado = filaEncabezado.createCell(35);
            celdaEncabezado.setCellValue("altura");
            celdaEncabezado = filaEncabezado.createCell(36);
            celdaEncabezado.setCellValue("ancho");
            celdaEncabezado = filaEncabezado.createCell(37);
            celdaEncabezado.setCellValue("carga_util");
            celdaEncabezado = filaEncabezado.createCell(38);
            celdaEncabezado.setCellValue("estado");
            celdaEncabezado = filaEncabezado.createCell(39);
            celdaEncabezado.setCellValue("fecha");
            celdaEncabezado = filaEncabezado.createCell(40);
            celdaEncabezado.setCellValue("nruedas");
            celdaEncabezado = filaEncabezado.createCell(41);
            celdaEncabezado.setCellValue("kilometraje");
            celdaEncabezado = filaEncabezado.createCell(42);            
            celdaEncabezado.setCellValue("PRUEALI");
            celdaEncabezado = filaEncabezado.createCell(43);
            celdaEncabezado.setCellValue("PROFNEUMA");
            celdaEncabezado = filaEncabezado.createCell(44);
            celdaEncabezado.setCellValue("PRUEBLUCES");
            celdaEncabezado = filaEncabezado.createCell(45);
            celdaEncabezado.setCellValue("SUSPENSION");
            celdaEncabezado = filaEncabezado.createCell(46);
            celdaEncabezado.setCellValue("EMIGASES");
            celdaEncabezado = filaEncabezado.createCell(47);
            celdaEncabezado.setCellValue("FRESERV");
            celdaEncabezado = filaEncabezado.createCell(48);
            celdaEncabezado.setCellValue("FREESTAC");
            celdaEncabezado = filaEncabezado.createCell(49);
            celdaEncabezado.setCellValue("FREEMER");
            celdaEncabezado = filaEncabezado.createCell(50);
            celdaEncabezado.setCellValue("DISEJES");
            celdaEncabezado = filaEncabezado.createCell(51);
            celdaEncabezado.setCellValue("PISOS");
//            celdaEncabezado = filaEncabezado.createCell(52);
//            celdaEncabezado.setCellValue("FOTOGRAFIA");

            /*Hacemos un ciclo para inicializar los valores de 10 filas de celdas*/
            for (int f = 1; f <= listaCertificados.length; f++) {
                /*La clase Row nos permitirá crear las filas*/
                Row fila = hoja.createRow(f);

                /*Cada fila tendrá 52 celdas de datos*/
                for (int c = 0; c < 52; c++) {
                    Cell celda = fila.createCell(c);
                    celda.setCellValue(listaCertificados[f - 1][c].toString());
//                    }
                }
            }   
            /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
            Sheet hoja2 = libro.createSheet("Observaciones");

            /*Obtener listado de Certificados de la base de datos*/
            Object listaObservaciones[][] = new CertificadoBL().listarAllObservaciones();

            Row filaEncabezado2 = hoja2.createRow(0);
            Cell celdaEncabezado2 = filaEncabezado2.createCell(0);
            celdaEncabezado2.setCellValue("idCertificado");
            celdaEncabezado2 = filaEncabezado2.createCell(1);
            celdaEncabezado2.setCellValue("codigoObservacion");
            celdaEncabezado2 = filaEncabezado2.createCell(2);
            celdaEncabezado2.setCellValue("interpretacion");
            celdaEncabezado2 = filaEncabezado2.createCell(3);
            celdaEncabezado2.setCellValue("calificacion");
            

            /*Hacemos un ciclo para inicializar los valores de f filas de celdas*/
            for (int f = 1; f <= listaObservaciones.length; f++) {
                /*La clase Row nos permitirá crear las filas*/
                Row fila = hoja2.createRow(f);

                /*Cada fila tendrá 4 celdas de datos*/
                for (int c = 0; c < 4; c++) {
                    Cell celda = fila.createCell(c);
                    celda.setCellValue(listaObservaciones[f - 1][c].toString());
//                    }
                }
            }   
            /*Escribimos en el libro*/ 
            libro.write(archivo);
            /*Cerramos el flujo de datos*/
            archivo.close();
            /*Y abrimos el archivo con la clase Desktop*/
            Desktop.getDesktop().open(archivoXLS);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(jfrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(jfrmInicio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(jfrmInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
         frmImportarData vv = new frmImportarData();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        RegistrarTarjeta vv = new RegistrarTarjeta();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        ListarTarjetasPreregistradas vv = new ListarTarjetasPreregistradas();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        BackupRestoreMovil vv = new BackupRestoreMovil();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
        ListarCertificadosAnulacion vv = new ListarCertificadosAnulacion();

        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension jInternalFrameSize = vv.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        boolean mostrar = true;
        for (int a = 0; a < jDesktopPane1.getComponentCount(); a++) {
            if (vv.getClass().isInstance(jDesktopPane1.getComponent(a))) {
                mostrar = true;
            } else {
                mostrar = false;
            }
        }
        if (mostrar) {
            jDesktopPane1.add(vv);
            vv.setLocation(width, height);
            vv.show();
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //  new jfrmInicio().setVisible(true);
                final jfrmInicio frame = new jfrmInicio();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setDefaultCloseOperation(jfrmInicio.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    public final javax.swing.JMenuItem jMenuItem2 = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables

}
