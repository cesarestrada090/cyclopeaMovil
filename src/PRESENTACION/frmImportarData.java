/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImportarBD.java
 *
 * Created on 10/01/2013, 11:36:20 AM
 */
package PRESENTACION;

import ENTIDADES.*;
import NEGOCIO.PersonaBL;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author pc
 */
public class frmImportarData extends javax.swing.JInternalFrame {

    /**
     * Creates new form ImportarBD
     */
    public frmImportarData() {
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

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setFrameIcon(null);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Botones/actualizar.png"))); // NOI18N
        jButton1.setText("ACTUALIZAR BASE DE DATOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "RUC", "Nombre", "Teléfono", "Placa", "Marca", "AnioFabricacion", "Modelo", "TipoServicio", "FechaVencimiento"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        DefaultTableModel dtm;
        ArrayList datos_recibidos = new ArrayList();

        JFileChooser fileChooser = new JFileChooser(".");
        int status = fileChooser.showOpenDialog(null); //fileChooser

        if (status == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();

//            leerExcel l = new leerExcel();
            String direccion = selectedFile.getParent() + "/" + selectedFile.getName();

            try {
                FileInputStream fileInputStream = new FileInputStream(direccion);
                HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
                HSSFSheet worksheet = workbook.getSheet("Certificados Emitidos");

                Iterator rowIterator = worksheet.rowIterator();

                List lstCertificados = new ArrayList<Certificado>();
                List lstTarjetasP = new ArrayList<TarjetaPropiedad>();
                List lstResultados=new ArrayList<Resultado>(); 
                List lstObservaciones = new ArrayList<Observacion>();
                
                Certificado objCertificado = new Certificado();
                TarjetaPropiedad objTarjetaP =new TarjetaPropiedad();
                Resultado objResultado = new Resultado();
                
                while (rowIterator.hasNext()) {
                    HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                    Iterator iterator = hssfRow.cellIterator();
                    List cellTempList = new ArrayList();
                    while (iterator.hasNext()) {
                        HSSFCell hssfCell = (HSSFCell) iterator.next();
                        cellTempList.add(hssfCell);
                    }
                    int cont = cellTempList.size();
                    
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");                    

                    int i = 0;
                    objCertificado.setIdCertificado(Integer.parseInt(cellTempList.get(i+1).toString()));
                    objCertificado.setTipoDocTransp(cellTempList.get(i+2).toString());
                    objCertificado.setNumDocEvaluar(cellTempList.get(i+3).toString());
                    objCertificado.setClaseAutorizacion(cellTempList.get(i+4).toString());
                    objCertificado.setResultado(Integer.parseInt(cellTempList.get(i+5).toString()));
                    objCertificado.setVigencia(cellTempList.get(i+6).toString());
                    try {
                        if (cellTempList.get(i+7).toString().equals("-")){
                            objCertificado.setFecInspeccion(formatter.parse("00/00/0000"));
                        }else{
                            objCertificado.setFecInspeccion(formatter.parse(cellTempList.get(i+7).toString()));
                        }
                        if (cellTempList.get(i+8).toString().equals("-")){
                            objCertificado.setFecVencimiento(formatter.parse("00/00/0000"));
                        }else{
                            objCertificado.setFecVencimiento(formatter.parse(cellTempList.get(i+8).toString()));
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(frmImportarData.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                    objCertificado.setcIdentidadCert(cellTempList.get(i+9).toString());
                    objCertificado.setCodLocal(cellTempList.get(i+10).toString());                    
                    objCertificado.setUbigeo(cellTempList.get(i+11).toString());
                    objCertificado.setIdTarjeta(Integer.parseInt(cellTempList.get(i+12).toString()));                    

                    lstCertificados.add(objCertificado);
                    
                    objTarjetaP.setIdTarjeta(cellTempList.get(i+12).toString());
                    objTarjetaP.setPlaca(cellTempList.get(i+13).toString());
                    objTarjetaP.setnTarjeta(cellTempList.get(i+14).toString());
                    objTarjetaP.setNombrePropietario(cellTempList.get(i+15).toString());
                    objTarjetaP.setDomicilio(cellTempList.get(i+16).toString());
                    objTarjetaP.setVersion(cellTempList.get(i+17).toString()); //Clase
                    objTarjetaP.setIdMarca(cellTempList.get(i+18).toString());
                    objTarjetaP.setFabricacion(Integer.parseInt(cellTempList.get(i+19).toString()));
                    objTarjetaP.setIdModelo(cellTempList.get(i+20).toString());
                    objTarjetaP.setVersion(cellTempList.get(i+21).toString());
                    objTarjetaP.setIdCombustible(cellTempList.get(i+22).toString());
                    objTarjetaP.setIdCarroceria(cellTempList.get(i+23).toString());
                    objTarjetaP.setEjes(Integer.parseInt(cellTempList.get(i+24).toString()));
                    objTarjetaP.setColores(cellTempList.get(i+25).toString());
                    objTarjetaP.setnMotor(cellTempList.get(i+26).toString());
                    objTarjetaP.setCilindros(Integer.parseInt(cellTempList.get(i+27).toString()));
                    objTarjetaP.setnSerie(cellTempList.get(i+28).toString());
                    objTarjetaP.setVin(cellTempList.get(i+29).toString());
                    objTarjetaP.setPasajeros(Integer.parseInt(cellTempList.get(i+30).toString()));
                    objTarjetaP.setAsientos(Integer.parseInt(cellTempList.get(i+31).toString()));
                    objTarjetaP.setPesoSeco(Double.parseDouble(cellTempList.get(i+32).toString()));
                    objTarjetaP.setPesoBruto(Double.parseDouble(cellTempList.get(i+33).toString()));
                    objTarjetaP.setLongitud(Double.parseDouble(cellTempList.get(i+34).toString()));
                    objTarjetaP.setAltura(Double.parseDouble(cellTempList.get(i+35).toString()));
                    objTarjetaP.setAncho(Double.parseDouble(cellTempList.get(i+36).toString()));
                    objTarjetaP.setCargaUtil(Double.parseDouble(cellTempList.get(i+37).toString()));
                    objTarjetaP.setEstado(cellTempList.get(i+38).toString());
                    try {
                        if (cellTempList.get(i+39).toString().equals("-")){
                            objTarjetaP.setFecha(formatter.parse("00/00/0000"));
                        }else{
                            objTarjetaP.setFecha(formatter.parse(cellTempList.get(i+39).toString()));
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(frmImportarData.class.getName()).log(Level.SEVERE, null, ex);
                    }                         
                    objTarjetaP.setRuedas(Integer.parseInt(cellTempList.get(i+40).toString()));
                    objTarjetaP.setKilometraje(Double.parseDouble(cellTempList.get(i+40).toString()));
                    
                    lstTarjetasP.add(objTarjetaP);
                    
                    objResultado.setPruebaAli(Integer.parseInt(cellTempList.get(i+41).toString()));
                    objResultado.setProfNeuma(Integer.parseInt(cellTempList.get(i+42).toString()));
                    objResultado.setPruebaLuces(Integer.parseInt(cellTempList.get(i+43).toString()));
                    objResultado.setSuspension(Integer.parseInt(cellTempList.get(i+44).toString()));
                    objResultado.setEmiGases(Integer.parseInt(cellTempList.get(i+45).toString()));
                    objResultado.setFrenoServicioM(Integer.parseInt(cellTempList.get(i+46).toString()));
                    objResultado.setFrenoEstacion(Integer.parseInt(cellTempList.get(i+47).toString()));
                    objResultado.setFrenoEmergencia(Integer.parseInt(cellTempList.get(i+48).toString()));
                    objResultado.setDistanciaEjes(Double.parseDouble(cellTempList.get(i+49).toString()));
                    objResultado.setNumPisos(Integer.parseInt(cellTempList.get(i+50).toString()));
                    
                    lstResultados.add(objResultado);
                }

                HSSFRow row1 = worksheet.getRow(1);

                HSSFCell cellA1 = row1.getCell((short) 0);
                String a1Val = cellA1.getStringCellValue();
                HSSFCell cellB1 = row1.getCell((short) 1);
                String b1Val = cellB1.getStringCellValue();
                HSSFCell cellC1 = row1.getCell((short) 2);
                String c1Val = cellC1.getStringCellValue();
                HSSFCell cellD1 = row1.getCell((short) 3);
                String d1Val = cellD1.getStringCellValue();

                HSSFSheet worksheet2 = workbook.getSheet("Observaciones");

                HSSFRow row2 = worksheet2.getRow(1);

                HSSFCell cellA2 = row2.getCell((short) 0);
                String a2Val = cellA2.getStringCellValue();
                HSSFCell cellB2 = row2.getCell((short) 1);
                String b2Val = cellB2.getStringCellValue();
                HSSFCell cellC2 = row2.getCell((short) 2);
                String c2Val = cellC2.getStringCellValue();
                HSSFCell cellD2 = row2.getCell((short) 3);
                String d2Val = cellD2.getStringCellValue();

                System.out.println("A2: " + a1Val);
                System.out.println("B2: " + b1Val);
                System.out.println("C2: " + c1Val);
                System.out.println("D2: " + d1Val);

                System.out.println("A2: " + a2Val);
                System.out.println("B2: " + b2Val);
                System.out.println("C2: " + c2Val);
                System.out.println("D2: " + d2Val);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (status == JFileChooser.CANCEL_OPTION) {
            System.out.println("cancele");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        int filas = dtm.getRowCount();
        JOptionPane.showMessageDialog(null, "Numero de Registros: " + filas);
        String nombre = "";
        String a[];

        a = new String[10];

        // Recorrer la tabla y Guardar cada fila en un arreglo
        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < 10; j++) {
                nombre = jTable1.getValueAt(i, j).toString();
                a[j] = nombre;
            }

            boolean x = (a[2].contains("'"));

            int idPersona;

            //if(a[2].compareTo("REPRESENTACIONES LARRY'S EIRL")==0 || a[2].compareTo("ABCD'NEGOCIOS S.A.C.")==0 || a[2].compareTo("CONSTRUCTOR'S HOME S.A.C.")==0 || a[2].compareTo("D'ANGELO PARDO JOSE CARLOS")==0 || a[2].compareTo("D'ANGLES HURTADO LUCIO WILMER")==0 || a[2].compareTo("AGRICOLA E INVERSIONES 'L & F' S.A.C.")==0 || a[2].compareTo("D'ANGELO DE YTZKOVICH GLORIA")==0 || a[2].compareTo("D'BALL EIRL")==0 || a[2].compareTo("D'ICLASSIC SRL.")==0 || a[2].compareTo("D'MIX CAR SERVICE SOCIEDAD COMERCIAL DE RESPONSABILIDAD LIMITADA")==0 || a[2].compareTo("D'IONS SRLTDA")==0 || a[2].compareTo("EMPRESA DE TRANSPORTES ROYAL PALACE'S SA")==0)
            if (x) {
                idPersona = 0;
            } else {
                idPersona = new PersonaBL().listarBusqueda(a[2]);
            }

            //JOptionPane.showMessageDialog(null, "idPersona: "+idPersona);
            if (idPersona == 0) {
                idPersona = new PersonaBL().obtenerNumeroRegistro();
                if (new PersonaBL().registrarPersona(new Persona(idPersona, a[0], a[1], a[2], "", a[3], "", ""))) {
                    jButton2.setEnabled(false);
                }
            } else {
                new PersonaBL().ActualizarDatos(a[3], idPersona);
            }

//           int idVehiculo=new VehiculoBL().listarBusquedaPlaca(a[4]);
            //JOptionPane.showMessageDialog(null, "idVehiculo: "+idVehiculo);
//           if(idVehiculo==0)
//           {
//                idVehiculo=new VehiculoBL().obtenerNumeroRegistro();
//                if(new VehiculoBL().registrarVehiculo(new Vehiculo(idVehiculo,a[4], a[6], a[5], a[7], a[8], idPersona, 0)))
//                {
//                    jButton2.setEnabled(false);
//                }
//           }
            String n = a[9].substring(0, 10);
            //JOptionPane.showMessageDialog(null, n);
            String dia = n.substring(0, 2);
            //JOptionPane.showMessageDialog(null, dia);
            String mes = n.substring(3, 5);
            //JOptionPane.showMessageDialog(null, mes);
            String anio = n.substring(6, 10);
            //JOptionPane.showMessageDialog(null, anio);
            String fecha = anio + "-" + mes + "-" + dia;
            //JOptionPane.showMessageDialog(null, fecha);

            int idRevision = 0;//new RevisionBL().listarBusquedaFecha(fecha);

            //JOptionPane.showMessageDialog(null, "idRevision: "+idRevision);
            if (idRevision == 0) {
//                idRevision=new RevisionBL().obtenerNumeroRegistro();
//                if(new RevisionDAL().registrarDatosRevision(new Revision(idRevision, fecha, idVehiculo)))
//            {
//                jButton2.setEnabled(false);
//            }
            }

        }
        JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
