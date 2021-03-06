/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarCertificado.java
 *
 * Created on 07/01/2013, 08:08:38 AM
 *
 */
package PRESENTACION;

import ENTIDADES.*;
import NEGOCIO.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author pc
 */
public class RegistrarCertificadoSinSuspension extends javax.swing.JInternalFrame {

    List arrayObservaciones = new ArrayList<Observacion>();
    boolean eficienciaServicio;
    boolean eficienciaEstacionamiento;
    boolean suspensionIzqDel = true;
    boolean suspensionDerDel = true;
    boolean suspensionDesvDel = true;
    boolean suspensionIzqPost = true;
    boolean suspensionDerPost = true;
    boolean suspensionDesvPost = true;
    boolean sonometroResult = true;

    public TarjetaPropiedad objTarjetaP; // = new TarjetaPropiedad();
    public int intIdTarjeta;

    private FileInputStream fisfoto1;
    private int longitudBytes1;
    private FileInputStream fisfoto2;
    private int longitudBytes2;
    private FileInputStream fisfoto3;
    private int longitudBytes3;

    private int ResultadoGeneral;

    private boolean ObsGravesMuyGraves = false;

    /**
     * Creates new form RegistrarUsuario
     */
    public RegistrarCertificadoSinSuspension() {
        initComponents();
        // **************************** PRIMER EJE FRENO SERVICIO
        jTextField40.getDocument().addDocumentListener(new PrimerEjeFrenometro());
        jTextField41.getDocument().addDocumentListener(new SegundoEjeFrenometro());
        jTextField42.getDocument().addDocumentListener(new TercerEjeFrenometro());
        jTextField43.getDocument().addDocumentListener(new CuartoEjeFrenometro());
        jTextField44.getDocument().addDocumentListener(new QuintoEjeFrenometro());

        jTextField5.getDocument().addDocumentListener(new AlineamientoDesviacionEje1());
        jTextField6.getDocument().addDocumentListener(new AlineamientoDesviacionEje2());
        jTextField7.getDocument().addDocumentListener(new AlineamientoDesviacionEje3());
        jTextField8.getDocument().addDocumentListener(new AlineamientoDesviacionEje4());
        jTextField9.getDocument().addDocumentListener(new AlineamientoDesviacionEje5());

        jTextField16.getDocument().addDocumentListener(new ProfNeumaticos1());
        jTextField17.getDocument().addDocumentListener(new ProfNeumaticos2());
        jTextField18.getDocument().addDocumentListener(new ProfNeumaticos3());
        jTextField19.getDocument().addDocumentListener(new ProfNeumaticos4());
        jTextField20.getDocument().addDocumentListener(new ProfNeumaticos5());

        //luces
        //luces bajas
        jTextField159.getDocument().addDocumentListener(new validarLuxometroBajas());
        jTextField163.getDocument().addDocumentListener(new validarLuxometroBajas());

        //luces bajas
        jTextField160.getDocument().addDocumentListener(new validarLuxometroAltas());
        jTextField168.getDocument().addDocumentListener(new validarLuxometroAltas());

        //luces Altas Adicionales
        jTextField161.getDocument().addDocumentListener(new validarLuxometroAltasAdicionales());
        jTextField165.getDocument().addDocumentListener(new validarLuxometroAltasAdicionales());

        jTextField162.getDocument().addDocumentListener(new validarLuxometroNeblineras());
        jTextField166.getDocument().addDocumentListener(new validarLuxometroNeblineras());

//        jTextField181.getDocument().addDocumentListener(new SuspensionPosteriorIzq());
//        jTextField182.getDocument().addDocumentListener(new SuspensionPosteriorDer());
//        jTextField183.getDocument().addDocumentListener(new SuspensionPosteriorDesv());
//
//        jTextField177.getDocument().addDocumentListener(new SuspensionDelanteraIzq());
//        jTextField178.getDocument().addDocumentListener(new SuspensionDelanteraDer());
//        jTextField179.getDocument().addDocumentListener(new SuspensionDelanteraDesv());
        // FUERZA DE FRENADO ESTACIONAMIENTO
        //EJE 1  
        jTextField65.getDocument().addDocumentListener(new Frenado01());
        jTextField66.getDocument().addDocumentListener(new Frenado01());
        //EJE 2
        jTextField64.getDocument().addDocumentListener(new Frenado02());
        jTextField67.getDocument().addDocumentListener(new Frenado02());
        //EJE 3
        jTextField63.getDocument().addDocumentListener(new Frenado03());
        jTextField68.getDocument().addDocumentListener(new Frenado03());
        //EJE 4
        jTextField62.getDocument().addDocumentListener(new Frenado04());
        jTextField69.getDocument().addDocumentListener(new Frenado04());
        //EJE 5
        jTextField61.getDocument().addDocumentListener(new Frenado05());
        jTextField70.getDocument().addDocumentListener(new Frenado05());

        // FUERZA DE FRENADO SERVICIO
        //EJE 1  
        jTextField30.getDocument().addDocumentListener(new FrenadoServicio01());
        jTextField31.getDocument().addDocumentListener(new FrenadoServicio01());
        //EJE 2
        jTextField32.getDocument().addDocumentListener(new FrenadoServicio02());
        jTextField36.getDocument().addDocumentListener(new FrenadoServicio02());
        //EJE 3
        jTextField33.getDocument().addDocumentListener(new FrenadoServicio03());
        jTextField37.getDocument().addDocumentListener(new FrenadoServicio03());
        //EJE 4
        jTextField34.getDocument().addDocumentListener(new FrenadoServicio04());
        jTextField38.getDocument().addDocumentListener(new FrenadoServicio04());
        //EJE 5
        jTextField35.getDocument().addDocumentListener(new FrenadoServicio05());
        jTextField39.getDocument().addDocumentListener(new FrenadoServicio05());

        //EFICIENCIA FRENO SERVICIO
        jTextField50.getDocument().addDocumentListener(new EficienciaFrenoServicio());
        //EFICIENCIA FRENO ESTACIONAMIENTO
        jTextField85.getDocument().addDocumentListener(new EficienciaFrenoEstacionamiento());

        //Validar frenometro
        jTextField185.getDocument().addDocumentListener(new validarGasometro());
        jTextField186.getDocument().addDocumentListener(new validarGasometro());
        jTextField187.getDocument().addDocumentListener(new validarGasometro());
        jTextField188.getDocument().addDocumentListener(new validarGasometro());
        jTextField189.getDocument().addDocumentListener(new validarGasometro());
        jTextField190.getDocument().addDocumentListener(new validarGasometro());
        jTextField191.getDocument().addDocumentListener(new validarGasometro());
        jTextField192.getDocument().addDocumentListener(new validarGasometro());
        jTextField193.getDocument().addDocumentListener(new validarGasometro());

        jTextField194.getDocument().addDocumentListener(new SonometroResultado());
        CertificadoBL b = new CertificadoBL();
        int size;
        List listaModelos = b.obtenerListaModelo();
//        for (int i = 0; i < listaModelos.size(); i++) {
//            jComboBox10.addItem((String) listaModelos.get(i));
//        }

//        List listaCombustibles = b.obtenerListaCombustible();
//        size = listaCombustibles.size();
//        for (int i = 0; i < size; i++) {
//            jComboBox13.addItem((String) listaCombustibles.get(i));
//        }
        List listaCarrocerias = b.obtenerListaCarroceria();
        size = listaCarrocerias.size();
        for (int i = 0; i < size; i++) {
            jComboBox12.addItem((String) listaCarrocerias.get(i));
        }

        List listaMarcas = b.obtenerListaMarca();
        size = listaMarcas.size();
////        for (int i = 0; i < size; i++) {
////            jComboBox9.addItem((String) listaMarcas.get(i));
////        }

        List listaCategorias = b.obtenerListaCategoria();
        size = listaCategorias.size();
        for (int i = 0; i < size; i++) {
            jComboBox14.addItem((String) listaCategorias.get(i));
        }

    }
    // public int permiso1;
    public String strMensajeValidacion;
    public Boolean resultado = true;
    private boolean frenoServicioCompleto;
    private boolean frenoEstacionamientoCompleto;
    private boolean frenoEmergenciaCompleto;
    private boolean suspensionCompleto;
    private boolean gasometroCompleto;
    private boolean sonometroCompleto;
    private boolean luxometroCompleto;
    private boolean alineadorCompleto;
    private boolean fotosCompleto;
    private boolean observacionesCompleto;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jTextField82 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jTextField83 = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jTextField84 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jTextField112 = new javax.swing.JTextField();
        jTextField113 = new javax.swing.JTextField();
        jTextField114 = new javax.swing.JTextField();
        jTextField115 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jTextField119 = new javax.swing.JTextField();
        jTextField120 = new javax.swing.JTextField();
        jTextField121 = new javax.swing.JTextField();
        jTextField122 = new javax.swing.JTextField();
        jTextField123 = new javax.swing.JTextField();
        jTextField124 = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jComboBox12 = new javax.swing.JComboBox();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel149 = new javax.swing.JLabel();
        jTextField55 = new javax.swing.JTextField();
        jComboBox14 = new javax.swing.JComboBox();
        jComboBox15 = new javax.swing.JComboBox();
        jComboBox16 = new javax.swing.JComboBox();
        jComboBox17 = new javax.swing.JComboBox();
        jComboBox18 = new javax.swing.JComboBox();
        jComboBox19 = new javax.swing.JComboBox();
        jTextField51 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox();
        jLabel55 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jTextField66 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jTextField68 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jTextField70 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jTextField72 = new javax.swing.JTextField();
        jTextField73 = new javax.swing.JTextField();
        jTextField74 = new javax.swing.JTextField();
        jTextField75 = new javax.swing.JTextField();
        jTextField76 = new javax.swing.JTextField();
        jTextField77 = new javax.swing.JTextField();
        jTextField78 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jTextField80 = new javax.swing.JTextField();
        jTextField85 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jTextField92 = new javax.swing.JTextField();
        jTextField93 = new javax.swing.JTextField();
        jTextField94 = new javax.swing.JTextField();
        jTextField95 = new javax.swing.JTextField();
        jTextField96 = new javax.swing.JTextField();
        jTextField97 = new javax.swing.JTextField();
        jTextField98 = new javax.swing.JTextField();
        jTextField99 = new javax.swing.JTextField();
        jTextField100 = new javax.swing.JTextField();
        jTextField101 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jTextField102 = new javax.swing.JTextField();
        jTextField103 = new javax.swing.JTextField();
        jTextField104 = new javax.swing.JTextField();
        jTextField105 = new javax.swing.JTextField();
        jTextField106 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jTextField107 = new javax.swing.JTextField();
        jTextField108 = new javax.swing.JTextField();
        jTextField109 = new javax.swing.JTextField();
        jTextField110 = new javax.swing.JTextField();
        jTextField111 = new javax.swing.JTextField();
        jTextField116 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jTextField185 = new javax.swing.JTextField();
        jTextField186 = new javax.swing.JTextField();
        jTextField187 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jTextField188 = new javax.swing.JTextField();
        jTextField189 = new javax.swing.JTextField();
        jTextField190 = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jTextField191 = new javax.swing.JTextField();
        jTextField192 = new javax.swing.JTextField();
        jTextField193 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jTextField194 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField159 = new javax.swing.JTextField();
        jTextField160 = new javax.swing.JTextField();
        jTextField161 = new javax.swing.JTextField();
        jTextField162 = new javax.swing.JTextField();
        jTextField163 = new javax.swing.JTextField();
        jTextField165 = new javax.swing.JTextField();
        jTextField166 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField168 = new javax.swing.JTextField();
        jTextField171 = new javax.swing.JTextField();
        jTextField172 = new javax.swing.JTextField();
        jTextField173 = new javax.swing.JTextField();
        jTextField174 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBox31 = new javax.swing.JComboBox();
        jComboBox32 = new javax.swing.JComboBox();
        jComboBox33 = new javax.swing.JComboBox();
        jComboBox34 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField157 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField81 = new javax.swing.JTextField();
        jTextField86 = new javax.swing.JTextField();
        jTextField88 = new javax.swing.JTextField();
        jTextField89 = new javax.swing.JTextField();
        jTextField91 = new javax.swing.JTextField();
        jTextField117 = new javax.swing.JTextField();
        jTextField125 = new javax.swing.JTextField();
        jTextField126 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jComboBox8 = new javax.swing.JComboBox();
        jComboBox9 = new javax.swing.JComboBox();
        jComboBox10 = new javax.swing.JComboBox();
        jComboBox21 = new javax.swing.JComboBox();
        jComboBox23 = new javax.swing.JComboBox();
        jTextField128 = new javax.swing.JTextField();
        jTextField127 = new javax.swing.JTextField();
        jTextField129 = new javax.swing.JTextField();
        jTextField130 = new javax.swing.JTextField();
        jComboBox24 = new javax.swing.JComboBox();
        jComboBox25 = new javax.swing.JComboBox();
        jTextField131 = new javax.swing.JTextField();
        jTextField132 = new javax.swing.JTextField();
        jComboBox26 = new javax.swing.JComboBox();
        jTextField133 = new javax.swing.JTextField();
        jTextField134 = new javax.swing.JTextField();
        jComboBox27 = new javax.swing.JComboBox();
        jTextField135 = new javax.swing.JTextField();
        jTextField136 = new javax.swing.JTextField();
        jComboBox28 = new javax.swing.JComboBox();
        jTextField137 = new javax.swing.JTextField();
        jTextField138 = new javax.swing.JTextField();
        jComboBox29 = new javax.swing.JComboBox();
        jTextField139 = new javax.swing.JTextField();
        jTextField140 = new javax.swing.JTextField();
        jComboBox30 = new javax.swing.JComboBox();
        jTextField141 = new javax.swing.JTextField();
        jTextField142 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jComboBox20 = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("CYCLOPEA | CERTIFICADO DE INSPECCIÓN TÉCNICA VEHICULAR");
        setAutoscrolls(true);
        setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/logo.JPG"))); // NOI18N
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("INSPECCIÓN TÉCNICA VEHICULAR");

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel88.setText("3. Categoría:");

        jLabel89.setText("4. Marca:");

        jLabel90.setText("5. Modelo:");

        jLabel91.setText("6. Año de Fabricación:");

        jLabel92.setText("2. Placa:");

        jTextField82.setEnabled(false);
        jTextField82.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextField82InputMethodTextChanged(evt);
            }
        });
        jTextField82.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField82KeyTyped(evt);
            }
        });

        jLabel93.setText("1. Titular o Propietario:");

        jTextField83.setEnabled(false);

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setText("Tipo de Inspección: ");
        jLabel94.setToolTipText("");

        jLabel95.setText("INSPECCIÓN TÉCNICA ORDINARIA");

        jLabel96.setText("7. Kilometraje:");

        jTextField84.setEnabled(false);
        jTextField84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField84ActionPerformed(evt);
            }
        });
        jTextField84.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField84KeyTyped(evt);
            }
        });

        jLabel97.setText("8. Combustible: ");

        jLabel98.setText("9. VIN / N° de Serie:");

        jLabel99.setText("10. N° de Motor:");

        jLabel100.setText("11. Carrocería:");

        jLabel101.setText("12. Marca de Carrocería:");

        jLabel102.setText("13. N° ejes / N° Ruedas:");

        jTextField112.setEnabled(false);

        jTextField113.setEnabled(false);
        jTextField113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField113ActionPerformed(evt);
            }
        });

        jTextField114.setEnabled(false);

        jTextField115.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField115.setEnabled(false);
        jTextField115.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField115KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField115KeyTyped(evt);
            }
        });

        jLabel103.setText("14. N° Asientos / Pasajeros:");

        jLabel104.setText("15. Largo / Ancho / Alto (m):");

        jLabel105.setText("16. Color (es):");

        jLabel106.setText("17. Peso Neto (Kg.):");

        jLabel107.setText("18. Peso Bruto Vehicular (Kg.):");

        jLabel108.setText("19. Carga Util (Kg.):");

        jTextField119.setEnabled(false);
        jTextField119.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField120.setEnabled(false);
        jTextField120.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField121.setEnabled(false);
        jTextField121.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField122.setEnabled(false);

        jTextField123.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField123.setEnabled(false);
        jTextField123.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField124.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField124.setEnabled(false);
        jTextField124.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField124KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField124KeyTyped(evt);
            }
        });

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel109.setText("  II. DATOS DE LOS EQUIPOS");

        jLabel110.setText("CITV:");

        jLabel111.setText("Línea:");

        jLabel112.setText("Frenómetro N°");

        jLabel113.setText("Alineador N°");

        jLabel114.setText("Analizador u Opacímetro N°");

        jLabel115.setText("Regloscopio o Luxómetro N°");

        jLabel116.setText("Banco de Suspensión N°");

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900", "1899", "1898", "1897", "1896", "1895", "1894", "1893", "1892", "1891", "1890", "1889", "1888", "1887", "1886", "1885", "1884", "1883", "1882", "1881", "1880", "1879", "1878", "1877", "1876", "1875", "1874", "1873", "1872", "1871", "1870", "1869", "1868", "1867", "1866", "1865", "1864", "1863", "1862", "1861", "1860", "1859", "1858", "1857", "1856", "1855", "1854", "1853", "1852", "1851", "1850", "1849", "1848", "1847", "1846", "1845", "1844", "1843", "1842", "1841", "1840", "1839", "1838", "1837", "1836", "1835", "1834", "1833", "1832", "1831", "1830", "1829", "1828", "1827", "1826", "1825", "1824", "1823", "1822", "1821", "1820", "1819", "1818", "1817", "1816", "1815", "1814", "1813", "1812", "1811", "1810", "1809", "1808", "1807", "1806", "1805", "1804", "1803", "1802", "1801", "1800", "1799", "1798", "1797", "1796", "1795", "1794", "1793", "1792", "1791", "1790", "1789", "1788", "1787", "1786", "1785", "1784", "1783", "1782", "1781", "1780", "1779", "1778", "1777", "1776", "1775", "1774", "1773", "1772", "1771", "1770", "1769", "1768", "1767", "1766", "1765", "1764", "1763", "1762", "1761", "1760", "1759", "1758", "1757", "1756", "1755", "1754", "1753", "1752", "1751", "1750", "1749", "1748", "1747", "1746", "1745", "1744", "1743", "1742", "1741", "1740", "1739", "1738", "1737", "1736", "1735", "1734", "1733", "1732", "1731", "1730", "1729", "1728", "1727", "1726", "1725", "1724", "1723", "1722", "1721", "1720", "1719", "1718", "1717", "1716", "1715", "1714", "1713", "1712", "1711", "1710", "1709", "1708", "1707", "1706", "1705", "1704", "1703", "1702", "1701", "1700", "1699", "1698", "1697", "1696", "1695", "1694", "1693", "1692", "1691", "1690", "1689", "1688", "1687", "1686", "1685", "1684", "1683", "1682", "1681", "1680", "1679", "1678", "1677", "1676", "1675", "1674", "1673", "1672", "1671", "1670", "1669", "1668", "1667", "1666", "1665", "1664", "1663", "1662", "1661", "1660", "1659", "1658", "1657", "1656", "1655", "1654", "1653", "1652", "1651", "1650", "1649", "1648", "1647", "1646", "1645", "1644", "1643", "1642", "1641", "1640", "1639", "1638", "1637", "1636", "1635", "1634", "1633", "1632", "1631", "1630", "1629", "1628", "1627", "1626", "1625", "1624", "1623", "1622", "1621", "1620", "1619", "1618", "1617", "1616", "1615", "1614", "1613", "1612", "1611", "1610", "1609", "1608", "1607", "1606", "1605", "1604", "1603", "1602", "1601", "1600", "1599", "1598", "1597", "1596", "1595", "1594", "1593", "1592", "1591", "1590", "1589", "1588", "1587", "1586", "1585", "1584", "1583", "1582", "1581", "1580", "1579", "1578", "1577", "1576", "1575", "1574", "1573", "1572", "1571", "1570", "1569", "1568", "1567", "1566", "1565", "1564", "1563", "1562", "1561", "1560", "1559", "1558", "1557", "1556", "1555", "1554", "1553", "1552", "1551", "1550", "1549", "1548", "1547", "1546", "1545", "1544", "1543", "1542", "1541", "1540", "1539", "1538", "1537", "1536", "1535", "1534", "1533", "1532", "1531", "1530", "1529", "1528", "1527", "1526", "1525", "1524", "1523", "1522", "1521", "1520", "1519", "1518", "1517", "1516", "1515", "1514", "1513", "1512", "1511", "1510", "1509", "1508", "1507", "1506", "1505", "1504", "1503", "1502", "1501", "1500", "1499", "1498", "1497", "1496", "1495", "1494", "1493", "1492", "1491", "1490", "1489", "1488", "1487", "1486", "1485", "1484", "1483", "1482", "1481", "1480", "1479", "1478", "1477", "1476", "1475", "1474", "1473", "1472", "1471", "1470", "1469", "1468", "1467", "1466", "1465", "1464", "1463", "1462", "1461", "1460", "1459", "1458", "1457", "1456", "1455", "1454", "1453", "1452", "1451", "1450", "1449", "1448", "1447", "1446", "1445", "1444", "1443", "1442", "1441", "1440", "1439", "1438", "1437", "1436", "1435", "1434", "1433", "1432", "1431", "1430", "1429", "1428", "1427", "1426", "1425", "1424", "1423", "1422", "1421", "1420", "1419", "1418", "1417", "1416", "1415", "1414", "1413", "1412", "1411", "1410", "1409", "1408", "1407", "1406", "1405", "1404", "1403", "1402", "1401", "1400", "1399", "1398", "1397", "1396", "1395", "1394", "1393", "1392", "1391", "1390", "1389", "1388", "1387", "1386", "1385", "1384", "1383", "1382", "1381", "1380", "1379", "1378", "1377", "1376", "1375", "1374", "1373", "1372", "1371", "1370", "1369", "1368", "1367", "1366", "1365", "1364", "1363", "1362", "1361", "1360", "1359", "1358", "1357", "1356", "1355", "1354", "1353", "1352", "1351", "1350", "1349", "1348", "1347", "1346", "1345", "1344", "1343", "1342", "1341", "1340", "1339", "1338", "1337", "1336", "1335", "1334", "1333", "1332", "1331", "1330", "1329", "1328", "1327", "1326", "1325", "1324", "1323", "1322", "1321", "1320", "1319", "1318", "1317", "1316", "1315", "1314", "1313", "1312", "1311", "1310", "1309", "1308", "1307", "1306", "1305", "1304", "1303", "1302", "1301", "1300", "1299", "1298", "1297", "1296", "1295", "1294", "1293", "1292", "1291", "1290", "1289", "1288", "1287", "1286", "1285", "1284", "1283", "1282", "1281", "1280", "1279", "1278", "1277", "1276", "1275", "1274", "1273", "1272", "1271", "1270", "1269", "1268", "1267", "1266", "1265", "1264", "1263", "1262", "1261", "1260", "1259", "1258", "1257", "1256", "1255", "1254", "1253", "1252", "1251", "1250", "1249", "1248", "1247", "1246", "1245", "1244", "1243", "1242", "1241", "1240", "1239", "1238", "1237", "1236", "1235", "1234", "1233", "1232", "1231", "1230", "1229", "1228", "1227", "1226", "1225", "1224", "1223", "1222", "1221", "1220", "1219", "1218", "1217", "1216", "1215", "1214", "1213", "1212", "1211", "1210", "1209", "1208", "1207", "1206", "1205", "1204", "1203", "1202", "1201", "1200", "1199", "1198", "1197", "1196", "1195", "1194", "1193", "1192", "1191", "1190", "1189", "1188", "1187", "1186", "1185", "1184", "1183", "1182", "1181", "1180", "1179", "1178", "1177", "1176", "1175", "1174", "1173", "1172", "1171", "1170", "1169", "1168", "1167", "1166", "1165", "1164", "1163", "1162", "1161", "1160", "1159", "1158", "1157", "1156", "1155", "1154", "1153", "1152", "1151", "1150", "1149", "1148", "1147", "1146", "1145", "1144", "1143", "1142", "1141", "1140", "1139", "1138", "1137", "1136", "1135", "1134", "1133", "1132", "1131", "1130", "1129", "1128", "1127", "1126", "1125", "1124", "1123", "1122", "1121", "1120", "1119", "1118", "1117", "1116", "1115", "1114", "1113", "1112", "1111", "1110", "1109", "1108", "1107", "1106", "1105", "1104", "1103", "1102", "1101", "1100", "1099", "1098", "1097", "1096", "1095", "1094", "1093", "1092", "1091", "1090", "1089", "1088", "1087", "1086", "1085", "1084", "1083", "1082", "1081", "1080", "1079", "1078", "1077", "1076", "1075", "1074", "1073", "1072", "1071", "1070", "1069", "1068", "1067", "1066", "1065", "1064", "1063", "1062", "1061", "1060", "1059", "1058", "1057", "1056", "1055", "1054", "1053", "1052", "1051", "1050", "1049", "1048", "1047", "1046", "1045", "1044", "1043", "1042", "1041", "1040", "1039", "1038", "1037", "1036", "1035", "1034", "1033", "1032", "1031", "1030", "1029", "1028", "1027", "1026", "1025", "1024", "1023", "1022", "1021", "1020", "1019", "1018", "1017", "1016", "1015", "1014", "1013", "1012", "1011", "1010", "1009", "1008", "1007", "1006", "1005", "1004", "1003", "1002", "1001", "1000" }));
        jComboBox11.setEnabled(false);

        jLabel146.setText("01 CYCLOPEA MÓVIL");

        jLabel147.setText("MIXTA");

        jTextField52.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField52.setEnabled(false);
        jTextField52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField52ActionPerformed(evt);
            }
        });
        jTextField52.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField52KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField52KeyTyped(evt);
            }
        });

        jTextField53.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField53.setEnabled(false);
        jTextField53.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField54.setEnabled(false);
        jTextField54.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jComboBox12.setEnabled(false);

        jComboBox13.setEditable(true);
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DIESEL", "BI-COMBUSTIBLE", "GLP", "GASOLINA", "PETRÓLEO", "DUAL", "GNV", "BI-COMBUSTIBLE GLP", "BI-COMB.GLP", "GASOLINA/GLP", "GASOL./GLP", "GASOLINA/GNV", "GASOL./GNV", "BI-COMBUSTIBLE GNV", "BI-COMB./GNV.", "BI-COMBUSTIBLE GNL", "DUAL GLP", "DUAL GNV", "DUAL GNL", "GNL", "ETANOL", "HIBRIDO (GASOLINA/BATERIAS)", "HIBRIDO (GASOL/BATERIAS)", "HIBRIDO (DIESEL BATERIAS)", "HIBRIDO (PETRÓLEO BATERIAS)", "SIN VALOR", "------" }));
        jComboBox13.setEnabled(false);

        jLabel149.setText("Nro de Tarjeta de Propiedad");

        jTextField55.setEnabled(false);
        jTextField55.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField55KeyTyped(evt);
            }
        });

        jComboBox14.setEnabled(false);

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox15.setSelectedIndex(2);

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox16.setSelectedIndex(1);

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox17.setSelectedIndex(4);

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox18.setSelectedIndex(3);

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jTextField51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField51.setEnabled(false);
        jTextField51.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField51KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField51KeyTyped(evt);
            }
        });

        jLabel20.setText("Tipo Documento");

        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "RUC", "DNI", "C.E" }));

        jLabel55.setText("Numero Documento");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89)
                            .addComponent(jLabel93)
                            .addComponent(jLabel92)
                            .addComponent(jLabel91)
                            .addComponent(jLabel90)
                            .addComponent(jLabel96)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel147)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel112)
                                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel113))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel114)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel115))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField82, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField57, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField56, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField84, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox11, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField83, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                        .addComponent(jComboBox14, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel100)
                                    .addComponent(jLabel101)
                                    .addComponent(jLabel99)
                                    .addComponent(jLabel98)
                                    .addComponent(jLabel97)
                                    .addComponent(jLabel102))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox13, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField112)
                                    .addComponent(jTextField113)
                                    .addComponent(jComboBox12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField114)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField115, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox22, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel108)
                                    .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel106)
                                    .addComponent(jLabel105)
                                    .addComponent(jLabel104)
                                    .addComponent(jLabel103)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel116)
                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField124, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField123, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField122)
                    .addComponent(jTextField121)
                    .addComponent(jTextField119)
                    .addComponent(jTextField120)
                    .addComponent(jTextField55)
                    .addComponent(jTextField28))
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel111)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel110)
                                .addGap(85, 85, 85)
                                .addComponent(jLabel146)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel94)
                        .addComponent(jLabel95))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel149)
                        .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel93)
                        .addComponent(jTextField82)
                        .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel92)
                            .addComponent(jTextField83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel88)
                            .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField121, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel90)
                                .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel96))
                            .addComponent(jTextField84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel97)
                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103)
                            .addComponent(jTextField124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel98)
                            .addComponent(jLabel104)
                            .addComponent(jTextField123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99)
                            .addComponent(jLabel105)
                            .addComponent(jTextField122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel100)
                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel101)
                            .addComponent(jLabel107)
                            .addComponent(jTextField119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel102)
                                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel108)))))
                .addGap(27, 27, 27)
                .addComponent(jLabel109)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(jLabel112)
                    .addComponent(jLabel113)
                    .addComponent(jLabel115)
                    .addComponent(jLabel146)
                    .addComponent(jLabel114)
                    .addComponent(jLabel116))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(jLabel147)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane3.addTab("I. CARACTERÍSTICAS DEL VEHÍCULO", jPanel3);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setAutoscrolls(true);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setName("p14"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setName("p11"); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setName("p12"); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setName("p13"); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FRENO DE SERVICIO");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setMaximumSize(new java.awt.Dimension(163, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(163, 16));
        jLabel2.setPreferredSize(new java.awt.Dimension(163, 16));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("FRENO DE ESTACIONAMIENTO");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("FRENO DE EMERGENCIA");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setName("p15"); // NOI18N
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ejes");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Peso (Kg)");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel42.setText("1°");

        jLabel43.setText("2°");

        jLabel44.setText("3°");

        jLabel45.setText("4°");

        jLabel46.setText("5°");

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Fuerza de Frenado (kN)");
        jLabel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Desequilibrio (%)");
        jLabel48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Resultado");
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Eficiencia (%)");
        jLabel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Resultado");
        jLabel51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Der");
        jLabel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Izq");
        jLabel53.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField30.setName("d1"); // NOI18N
        jTextField30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField31.setName("izq1"); // NOI18N
        jTextField31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField32.setName("d2"); // NOI18N
        jTextField32.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField33.setName("d3"); // NOI18N
        jTextField33.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField34.setName("d4"); // NOI18N
        jTextField34.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField35.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField35.setName("d5"); // NOI18N
        jTextField35.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField36.setName("izq2"); // NOI18N
        jTextField36.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField37.setName("izq3"); // NOI18N
        jTextField37.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField38.setName("izq4"); // NOI18N
        jTextField38.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField39.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField39.setName("izq15"); // NOI18N
        jTextField39.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField40.setToolTipText("");
        jTextField40.setEnabled(false);
        jTextField40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField40ActionPerformed(evt);
            }
        });
        jTextField40.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField40FocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField40FocusGained(evt);
            }
        });
        jTextField40.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextField40InputMethodTextChanged(evt);
            }
        });
        jTextField40.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField40KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField40KeyTyped(evt);
            }
        });

        jTextField41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField41.setEnabled(false);
        jTextField41.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField42.setEnabled(false);
        jTextField42.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField43.setEnabled(false);
        jTextField43.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField44.setToolTipText("");
        jTextField44.setEnabled(false);
        jTextField44.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField45.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField45.setEnabled(false);

        jTextField46.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField46.setEnabled(false);

        jTextField47.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField47.setEnabled(false);

        jTextField48.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField48.setEnabled(false);

        jTextField49.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField49.setEnabled(false);

        jTextField50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Ejes");
        jLabel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Fuerza de Frenado (kN)");
        jLabel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Desequilibrio (%)");
        jLabel57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Resultado");
        jLabel58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Eficiencia (%)");
        jLabel59.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Resultado");
        jLabel60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Der");
        jLabel61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField61.setName("d10"); // NOI18N
        jTextField61.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField62.setName("d9"); // NOI18N
        jTextField62.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField63.setName("d8"); // NOI18N
        jTextField63.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField64.setName("d7"); // NOI18N
        jTextField64.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField65.setName("d6"); // NOI18N
        jTextField65.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField66.setName("izq6"); // NOI18N
        jTextField66.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField67.setName("izq7"); // NOI18N
        jTextField67.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField68.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField68.setName("izq8"); // NOI18N
        jTextField68.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField69.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField69.setName("izq9"); // NOI18N
        jTextField69.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField70.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField70.setName("izq10"); // NOI18N
        jTextField70.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField71.setToolTipText("");
        jTextField71.setEnabled(false);
        jTextField71.setName("d20"); // NOI18N
        jTextField71.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField72.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField72.setEnabled(false);
        jTextField72.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField73.setEnabled(false);
        jTextField73.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField74.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField74.setEnabled(false);
        jTextField74.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField75.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField75.setEnabled(false);
        jTextField75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField75ActionPerformed(evt);
            }
        });
        jTextField75.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField76.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField76ActionPerformed(evt);
            }
        });
        jTextField76.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField78KeyTyped(evt);
            }
        });

        jTextField77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField77.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField78KeyTyped(evt);
            }
        });

        jTextField78.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField78.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField78KeyTyped(evt);
            }
        });

        jTextField79.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField79.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField78KeyTyped(evt);
            }
        });

        jTextField80.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField80.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField78KeyTyped(evt);
            }
        });

        jTextField85.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField85.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel62.setText("1°");

        jLabel63.setText("2°");

        jLabel64.setText("3°");

        jLabel65.setText("4°");

        jLabel66.setText("5°");

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Izq");
        jLabel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Fuerza de Frenado (kN)");
        jLabel75.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Der");
        jLabel76.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Izq");
        jLabel77.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField92.setEnabled(false);
        jTextField92.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField93.setEnabled(false);
        jTextField93.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField94.setEnabled(false);
        jTextField94.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField95.setEnabled(false);
        jTextField95.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField96.setEnabled(false);
        jTextField96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField96ActionPerformed(evt);
            }
        });
        jTextField96.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField97.setText("                     ");
        jTextField97.setEnabled(false);
        jTextField97.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField98.setEnabled(false);
        jTextField98.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField99.setEnabled(false);
        jTextField99.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField100.setEnabled(false);
        jTextField100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField101.setEnabled(false);
        jTextField101.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Desequilibrio (%)");
        jLabel78.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField102.setText("                   ");
        jTextField102.setEnabled(false);
        jTextField102.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField103.setEnabled(false);
        jTextField103.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField104.setEnabled(false);
        jTextField104.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField105.setEnabled(false);
        jTextField105.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField106.setToolTipText("");
        jTextField106.setEnabled(false);
        jTextField106.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("Resultado");
        jLabel79.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField107.setEnabled(false);

        jTextField108.setEnabled(false);

        jTextField109.setEnabled(false);

        jTextField110.setEnabled(false);

        jTextField111.setEnabled(false);

        jTextField116.setEnabled(false);
        jTextField116.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Eficiencia \n(%)");
        jLabel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("Resultado");
        jLabel81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox4.setEnabled(false);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox5.setEnabled(false);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox6.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField65, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                    .addComponent(jTextField64)
                                    .addComponent(jTextField63)
                                    .addComponent(jTextField62)
                                    .addComponent(jTextField61)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField66, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                                .addComponent(jTextField67)
                                                .addComponent(jTextField68))
                                            .addComponent(jTextField70)
                                            .addComponent(jTextField69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField74)
                                .addComponent(jTextField75, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addComponent(jTextField73, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField72, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField71))
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField78, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField79, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField85))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField100, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(jTextField99, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField106, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField105, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField101, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                                .addComponent(jTextField93, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jTextField96, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField92, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                                        .addComponent(jTextField95, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(1, 1, 1))))))
                                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField102, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField103, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField104, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField107, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField108, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField109, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField111, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField110, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField116, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jComboBox4, 0, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel43)
                                            .addComponent(jLabel42)
                                            .addComponent(jLabel44)
                                            .addComponent(jLabel45)
                                            .addComponent(jLabel46))))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField31, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                            .addComponent(jTextField36, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                            .addComponent(jTextField37, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                            .addComponent(jTextField38, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                            .addComponent(jTextField39, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField41)
                                        .addComponent(jTextField40)
                                        .addComponent(jTextField42)
                                        .addComponent(jTextField43)
                                        .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44)
                            .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)
                            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53)))
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel77)))
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField85)
                            .addComponent(jComboBox5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTextField116, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 4, Short.MAX_VALUE))))
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel56)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel61)
                                            .addComponent(jLabel67))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62)
                            .addComponent(jTextField75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63)
                            .addComponent(jTextField74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64)
                            .addComponent(jTextField73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65)
                            .addComponent(jTextField72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66)
                            .addComponent(jTextField71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );

        jTextField1.getAccessibleContext().setAccessibleName("p14");
        jTextField2.getAccessibleContext().setAccessibleName("p11");
        jTextField3.getAccessibleContext().setAccessibleName("p12");
        jTextField4.getAccessibleContext().setAccessibleName("p13");
        jTextField12.getAccessibleContext().setAccessibleName("p15");
        jTextField30.getAccessibleContext().setAccessibleName("d1");
        jTextField31.getAccessibleContext().setAccessibleName("izq1");
        jTextField31.getAccessibleContext().setAccessibleDescription("");
        jTextField32.getAccessibleContext().setAccessibleName("d2");
        jTextField33.getAccessibleContext().setAccessibleName("d3");
        jTextField34.getAccessibleContext().setAccessibleName("d4");
        jTextField35.getAccessibleContext().setAccessibleName("d5");
        jTextField36.getAccessibleContext().setAccessibleName("izq2");
        jTextField37.getAccessibleContext().setAccessibleName("izq3");
        jTextField38.getAccessibleContext().setAccessibleName("izq4");
        jTextField39.getAccessibleContext().setAccessibleName("izq15");
        jTextField40.getAccessibleContext().setAccessibleName("de11");
        jTextField41.getAccessibleContext().setAccessibleName("dd12");
        jTextField42.getAccessibleContext().setAccessibleName("d13");
        jTextField42.getAccessibleContext().setAccessibleDescription("");
        jTextField43.getAccessibleContext().setAccessibleName("d14");
        jTextField44.getAccessibleContext().setAccessibleName("d15");
        jTextField61.getAccessibleContext().setAccessibleName("d10");
        jTextField62.getAccessibleContext().setAccessibleName("d9");
        jTextField63.getAccessibleContext().setAccessibleName("d8");
        jTextField64.getAccessibleContext().setAccessibleName("d7");
        jTextField65.getAccessibleContext().setAccessibleName("d6");
        jTextField66.getAccessibleContext().setAccessibleName("izq6");
        jTextField67.getAccessibleContext().setAccessibleName("izq7");
        jTextField68.getAccessibleContext().setAccessibleName("izq8");
        jTextField69.getAccessibleContext().setAccessibleName("izq9");
        jTextField70.getAccessibleContext().setAccessibleName("izq10");
        jTextField71.getAccessibleContext().setAccessibleName("d20");
        jTextField72.getAccessibleContext().setAccessibleName("d19");
        jTextField73.getAccessibleContext().setAccessibleName("d18");
        jTextField74.getAccessibleContext().setAccessibleName("d17");
        jTextField75.getAccessibleContext().setAccessibleName("d16");

        jTabbedPane1.addTab("FRENOS", jPanel1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel130.setText("T Aceite (C°)");
        jLabel130.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel131.setText("RPM");
        jLabel131.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel132.setText("Opacidad (m - 1)");
        jLabel132.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel133.setText("Límite (m - 1)");
        jLabel133.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("EMISIÓN DE GASES");
        jLabel134.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField185.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField185.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField186.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField186.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField187.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField187.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jLabel135.setText("CO Ralenti (%)");
        jLabel135.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel136.setText("CO + CO2 Ralenti (%)");
        jLabel136.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel137.setText("HC Ralenti (ppm)");
        jLabel137.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField188.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField188.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField189.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField189.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField190.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField190.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jLabel138.setText("CO Acel (%)");
        jLabel138.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel139.setText("CO + CO2 Acel (%)");
        jLabel139.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel140.setText("HC Acel (ppm)");
        jLabel140.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField191.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField191.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField192.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField192.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField193.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField193.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setText("Resultado");
        jLabel141.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox1.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jLabel133, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel132, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jLabel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel130, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField185, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jTextField186)
                    .addComponent(jTextField187))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField188, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jTextField189)
                    .addComponent(jTextField190))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel139, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(jLabel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField191, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jTextField192)
                    .addComponent(jTextField193))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 130, Short.MAX_VALUE))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel130)
                            .addComponent(jTextField185, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel135)
                            .addComponent(jTextField188, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel138)
                            .addComponent(jTextField191, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel131)
                            .addComponent(jTextField186, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel136)
                            .addComponent(jTextField189, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel139)
                            .addComponent(jTextField192, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel132)
                    .addComponent(jLabel133)
                    .addComponent(jTextField187, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel137)
                    .addComponent(jTextField190, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel140)
                    .addComponent(jTextField193, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(318, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("EMISIÓN DE GASES", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel142.setText("EMISIONES SONORAS");
        jLabel142.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel143.setText("Límite 86 dB");
        jLabel143.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel144.setText("Sonómetro (dB)");
        jLabel144.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel145.setText("Resultado");
        jLabel145.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField194.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox2.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField194, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, 0, 139, Short.MAX_VALUE)
                    .addComponent(jLabel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(465, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel144, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(jLabel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField194, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel143)
                .addContainerGap(309, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("EMISIONES SONORAS", jPanel8);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ejes");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setRequestFocusEnabled(false);

        jLabel6.setText("1°");

        jLabel7.setText("2°");

        jLabel8.setText("3°");

        jLabel9.setText("4°");

        jLabel10.setText("5°");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Desviación (m/Km)");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel11.setPreferredSize(new java.awt.Dimension(22, 16));

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Resultado");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setEnabled(false);

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setEnabled(false);

        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setEnabled(false);

        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setEnabled(false);

        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setEnabled(false);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Medida Obtenida (mm)");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Resultado");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField21.setEnabled(false);

        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setEnabled(false);

        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setEnabled(false);

        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField24.setEnabled(false);

        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setEnabled(false);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("PROF. DE NEUMÁTICOS");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Tipo de Luz");
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Medida obtenida (Lux o C)");
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Der");
        jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Izq");
        jLabel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setText("Altas");

        jLabel28.setText("Bajas");

        jLabel29.setText("Altas adicionales");

        jLabel30.setText("Neblineras");

        jLabel31.setText("Indicar la desviación de la Luz IZQ - /DER + /INFERIOR - / SUPERIOR +");

        jTextField159.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField159.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField160.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField160.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField161.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField161.setEnabled(false);
        jTextField161.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField162.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField162.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField163.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField163.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField165.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField165.setEnabled(false);
        jTextField165.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField166.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField166.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Alineamiento");
        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Resultado");
        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField168.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField168.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField171.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField171.setEnabled(false);

        jTextField172.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField172.setEnabled(false);

        jTextField173.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField173.setEnabled(false);

        jTextField174.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField174.setEnabled(false);

        jLabel34.setBackground(new java.awt.Color(0, 153, 51));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("PRUEBA DE ALINEAMIENTO");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("PRUEBA DE LUCES");
        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DER+/IZQ+", "DER-/IZQ+", "DER-/IZQ-", "IZQ+/DER-", "IZQ-/DER+", "CEN+/CEN+" }));

        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DER+/IZQ+", "DER-/IZQ+", "DER-/IZQ-", "IZQ+/DER-", "IZQ-/DER+", "CEN+/CEN+" }));

        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DER+/IZQ+", "DER-/IZQ+", "DER-/IZQ-", "IZQ+/DER-", "IZQ-/DER+", "CEN+/CEN+" }));
        jComboBox33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox33ActionPerformed(evt);
            }
        });

        jComboBox34.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DER+/IZQ+", "DER-/IZQ+", "DER-/IZQ-", "IZQ+/DER-", "IZQ-/DER+", "CEN+/CEN+" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel6))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField10)
                            .addComponent(jTextField11)
                            .addComponent(jTextField13)
                            .addComponent(jTextField14)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField16)
                                            .addComponent(jTextField20))
                                        .addGap(32, 32, 32))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(13, 13, 13)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField21)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField23, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField22)
                                    .addComponent(jTextField25))))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField161, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField162, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField166)
                                    .addComponent(jTextField165, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox33, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox34, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField159, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField160, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField168)
                                            .addComponent(jTextField163, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox31, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox32, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField171, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField172, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField173, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField174, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(618, 618, 618))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField159, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField163, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField171, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(jTextField160, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField168, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField172, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField173, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jTextField161, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField165, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField174, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel30)
                                .addComponent(jTextField162, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField166, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel15)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ALINEAMIENTO - LUCES", jPanel2);

        jTabbedPane3.addTab("III. RESULTADOS OBTENIDOS", jTabbedPane1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Vigencia del Certificado");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField157.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField157.setText("12");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jTextField157))
                .addContainerGap(784, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jTextField157, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(394, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("IV. RESULTADO DE LA INSPECCIÓN TÉCNICA VEHICULAR", jPanel5);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setText("Frénometro 1");

        jLabel19.setText("Frenómetro 2");

        jLabel68.setText("Frenómetro 3");
        jLabel68.setToolTipText("");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField27))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(595, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(296, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("V. FOTOGRAFÍAS", jPanel9);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTextField58.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("CÓDIGO");

        jTextField81.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField88.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField91.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField125.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("INTERPRETACIÓN DE DEFECTOS");

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("CALIFICACIÓN");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jTextField127.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField129.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jTextField132.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jTextField134.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jTextField136.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jTextField138.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jTextField140.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LEVE", "GRAVE", "MUY GRAVE" }));

        jTextField142.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField142, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField141))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField140, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField139))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField138, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField137))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField136, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField135))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField134, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField132, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField131))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField129, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField130))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField127, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField128))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField125, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField126))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField91, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField117))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField88, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField89))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField81, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField86))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(jTextField58, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField59)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel37)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(65, 65, 65))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap()))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField132, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField140, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField142, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("VI. OBSERVACIONES ADICIONALES", jPanel4);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/saveas.png"))); // NOI18N
        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Turístico", "Mercancías en General", "Mercancías Peligrosas", "Transporte de Personal", "Transporte Público de Personas", "Transporte Privado de Mercancías", "Taxi", "Particular", "Estudiantes", "Transporte Privado de Personas" }));
        jComboBox20.setEnabled(false);

        jLabel69.setText("Tipo de Servicio:");

        jLabel17.setText("Ámbito :");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Provincial", "Regional", "Nacional" }));
        jComboBox3.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(568, 568, 568))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton4)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("jfrmRegistrarUsuario");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // GENERAR NUMERO DE CERTIFICADO, INFORME Y TRAMITE
    int idCertificado;
    int idInforme;
    int idExpediente;
    Calendar c = Calendar.getInstance();
    String annio = Integer.toString(c.get(Calendar.YEAR));

    private void ObtenerIds() {
        CertificadoBL objCertBL = new CertificadoBL();
        Certificado objCertificado = new Certificado();
        objCertificado = objCertBL.obtenerIds();
        idCertificado = objCertificado.getIdCertificado() + 1;
        idInforme = objCertificado.getIdInforme() + 1;
        idExpediente = objCertificado.getIdExpediente() + 1;
    }

        private String generarCodigoCertificado() {
        String numCertificado = "SA-59-";
        int diferencia = 6 - Integer.toString(idCertificado).length();
        if ((diferencia) > 0) {
//            if (diferencia == 7) {
//                numCertificado = "0000000";
//            }
//            if (diferencia == 6) {
//                numCertificado = "000000";
//            }
//            if (diferencia == 5) {
//                numCertificado = "00000";
//            }
//            if (diferencia == 4) {
//                numCertificado = "0000";
//            }
//            if (diferencia == 3) {
//                numCertificado = "000";
//            }
            if (diferencia == 2) {
                numCertificado = numCertificado + "00";
            }
            if (diferencia == 1) {
                numCertificado = numCertificado + "0";
            }
        }
        numCertificado = numCertificado + idCertificado;
        //numCertificado = numCertificado + "-" + annio;
        return numCertificado;
    }

    private String generarCodigoInforme() {
        String numInforme = "";
        int diferencia = 6 - Integer.toString(idInforme).length();
        numInforme = "10-";
        if ((diferencia) > 0) {
            if (diferencia == 5) {
                numInforme = numInforme + "00000";
            }
            if (diferencia == 4) {
                numInforme = numInforme + "0000";
            }
            if (diferencia == 3) {
                numInforme = numInforme + "000";
            }
            if (diferencia == 2) {
                numInforme = numInforme + "00";
            }
            if (diferencia == 1) {
                numInforme = numInforme + "0";
            }
        }
        numInforme = numInforme + idInforme;
        return numInforme;
    }

    private String generarCodigoExpediente() {
        String numExpediente = "LIMCER";
        int diferencia = 9 - Integer.toString(idExpediente).length();
        if ((diferencia) > 0) {
            if (diferencia == 8) {
                numExpediente = numExpediente + "00000000";
            }
            if (diferencia == 7) {
                numExpediente = numExpediente + "0000000";
            }
            if (diferencia == 6) {
                numExpediente = numExpediente + "000000";
            }
            if (diferencia == 5) {
                numExpediente = numExpediente + "00000";
            }
            if (diferencia == 4) {
                numExpediente = numExpediente + "0000";
            }
            if (diferencia == 3) {
                numExpediente = numExpediente + "000";
            }
            if (diferencia == 2) {
                numExpediente = numExpediente + "00";
            }
            if (diferencia == 1) {
                numExpediente = numExpediente + "0";
            }
        }
        numExpediente = numExpediente + idExpediente;
        return numExpediente;
    }

    private Certificado ObtenerCertificado() {
        calcularObservaciones();
        //CERTIFICADO
        Certificado objCertificado = new Certificado();
        objCertificado.setNumDocEvaluar(jTextField55.getText());
        objCertificado.setTipoDocEvaluar("1");
        objCertificado.setNumDocTransp(jTextField28.getText());
        objCertificado.setTipoDocTransp(String.valueOf(jComboBox15.getSelectedIndex() + 1));
        objCertificado.setCodLocal("00");
        objCertificado.setFecInspeccion(objTarjetaP.getFecha());
        Calendar calendar = Calendar.getInstance();
//            calendar.setTime(jDateChooser1.getDate()); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, Integer.parseInt(jTextField157.getText()));  // numero de días a añadir, o restar en caso de días<0
        objCertificado.setFecVencimiento(calendar.getTime()); // Fecha de la próxima inspección

//        if (jComboBox3.getSelectedIndex() == 0) {
//            objCertificado.setResultado(1);
//        } else {
        ObtenerResultado();
        //ResultadoGeneral = 1;

        if (ObsGravesMuyGraves) {
            ResultadoGeneral = 0;
        }

        objCertificado.setResultado(ResultadoGeneral);
//        }

        objCertificado.setVigencia(jTextField157.getText());
        objCertificado.setIdTarjeta(intIdTarjeta);
        if (ResultadoGeneral == 1) {
            objCertificado.setIdCertificado(idCertificado);
        } else {
            objCertificado.setIdCertificado(0);
        }

        objCertificado.setIdInforme(idInforme);
        objCertificado.setIdExpediente(idExpediente);
        objCertificado.setNumCertificado(generarCodigoCertificado());
        objCertificado.setNumInforme(generarCodigoInforme());
        objCertificado.setNumExpediente(generarCodigoExpediente());

        int tipoServicio = jComboBox20.getSelectedIndex();

        String ambito = " ";

        if (objTarjetaP.getAmbito() == 1) {
            ambito = "PROVINCIAL";
        }
        if (objTarjetaP.getAmbito() == 2) {
            ambito = "REGIONAL";
        }
        if (objTarjetaP.getAmbito() == 3) {
            ambito = "NACIONAL";
        }

        if (tipoServicio == 0) { //Interprovincial turístico estudiantes
            objCertificado.setClaseAut("MODALIDAD: TURISTICO DE AMBITO " + ambito);
            objCertificado.setTitulo("CERTIFICACION TECNICA COMPLEMENTARIA PARA EL SERVICIO DE TRANSPORTE PUBLICO DE PERSONAS BAJO LA MODALIDAD DE "
                    + "TRANSPORTE ESPECIAL");
            objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección destinado al servicio de "
                    + "transporte público de personas bajo la modalidad  de transporte especial de   TURISTICO de AMBITO " + ambito + "  ha aprobado la "
                    + "Inspección Técnica Vehicular Complementaria al haberse verificado que se encuentra en buenas condiciones técnicas y mecánicas "
                    + "de funcionamiento, que fue diseñado originalmente de fábrica para el transporte de personas, que cumple con las condiciones y "
                    + "características técnicas establecidas en el Reglamento Nacional de Vehículos aprobado por Decreto Supremo Nº 058-2003-MTC, en "
                    + "los artículos 19º y 23º del Reglamento Nacional de Administración de Transportes aprobado por Decreto Supremo Nº 017-2009-MTC "
                    + "y en la normatividad emitida por la Autoridad competente, según consta en el informe de Inspección Técnica Vehicular "
                    + "Nº " + objCertificado.getNumInforme() + " del Expediente Interno Nº " + objCertificado.getNumExpediente() + ".");
        } else {
            if (tipoServicio == 1) { //Mercancías en general
                objCertificado.setClaseAut("SERVICIO DE TRANSPORTE DE MERCANCIAS EN GENERAL DE AMBITO  NACIONAL");
                objCertificado.setTitulo("CERTIFICACIÓN TÉCNICA COMPLEMENTARIA PARA EL SERVICIO DE TRANSPORTE PÚBLICO DE MERCANCÍAS GENERALES O "
                        + "ESPECIALES NO CONSIDERADAS COMO MATERIALES O RESIDUOS PELIGROSOS");

                objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección destinado al servicio de "
                        + "transporte público de mercancías generales o especiales no consideradas como materiales o residuos peligrosos, ha aprobado "
                        + "la Inspección Técnica Vehicular complementaria al haberse verificado que se encuentra en buenas condiciones técnicas y "
                        + "mecánicas de funcionamiento, que cumple con las condiciones y características técnicas establecidas en el Reglamento "
                        + "nacional de Vehículos aprobado por Decreto Supremo Nº 058-2003-MTC y en los artículos 19º y 21º  del Reglamento Nacional "
                        + "de Administración de Transportes aprobado por Decreto Supremo Nº 017-2009-MTC, según consta en el Informe de Inspección "
                        + "Técnica Vehicular Nº " + objCertificado.getNumInforme() + " del Expediente Interno Nº " + objCertificado.getNumExpediente() + ".");
            } else {
                if (tipoServicio == 2) { //Mercancías peligrosas
                    objCertificado.setClaseAut("");
                    objCertificado.setTitulo("CERTIFICACION TECNICA COMPLEMENTARIA PARA EL SERVICIO DE TRANSPORTE DE MATERIALES Y RESIDUOS PELIGROSOS");
                    objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección destinado al servicio "
                            + "de transporte de materiales y residuos peligrosos ha aprobado la Inspección Técnica Vehicular Complementaria al "
                            + "haberse verificado que se encuentra en buenas condiciones técnicas y mecánicas de funcionamiento y cumple con las "
                            + "condiciones y características técnicas establecidas en el artículo 19º del Reglamento Nacional de Vehículos aprobado "
                            + "por Decreto Supremo Nº 058-2003-MTC , según consta en el Informe de Inspección Técnica Vehicular NºNº " + objCertificado.getNumInforme()
                            + " del Expediente Interno Nº " + objCertificado.getNumExpediente() + ".");
                } else {
                    if (tipoServicio == 3) { //Transporte de personal
                        objCertificado.setClaseAut("MODALIDAD: TRABAJADORES DE ÁMBITO "+ ambito);
                        objCertificado.setTitulo("CERTIFICACION TECNICA COMPLEMENTARIA PARA EL SERVICIO DE TRANSPORTE PUBLICO DE PERSONAS BAJO LA "
                                + "MODALIDAD DE TRANSPORTE ESPECIAL");
                        objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección destinado al "
                                + "servicio de transporte de personas bajo la modalidad de transporte especial de TRABAJADORES de AMBITO "+ambito
                                + " ha aprobado la inspección Técnica Vehicular al haberse verificado que se encuentra en buenas condiciones técnicas "
                                + "y mecánicas de funcionamiento que fue diseñado originalmente de fábrica para el tranporte de persona que cumple "
                                + "con las condiciones y características técnicas establecidas en el Reglamento Nacional de Vehículos aprobado por "
                                + "Decreto Supremo Nº058-2003-MTC, en los artículos 19º y 23º del Reglamento Nacional de Adminitración de Transporte "
                                + "aprobado por Decreto Supremo Nº017-2009-MTC y en la normatividad emitida por la Autoridad competente, según consta "
                                + "en el Informe de Inspección Técnica Vehicular Nº " + objCertificado.getNumInforme() + " del Expediente Interno "
                                + "Nº " + objCertificado.getNumExpediente() + ".");
                    } else {
                        if (tipoServicio == 4) { //Transporte público
                            objCertificado.setClaseAut("ÁMBITO "+ ambito);
                            objCertificado.setTitulo("CERTIFICACIÓN TÉCNICA COMPLEMENTARIA PARA EL SERVICIO DE TRANSPORTE PÚBLICO DE PERSONAS BAJO "
                                    + "LA MODALIDAD DE TRANSPORTE REGULAR");
                            objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección destinado al "
                                    + "servicio de transporte público de personas bajo la modalidad de Transporte regular de AMBITO "+ ambito + "  ha "
                                    + "aprobado la Inspección Técnica Vehicular complementaria al haberse verificado que se encuentra en buenas "
                                    + "condiciones técnicas y mecánicas de funcionamiento, que fue diseñado originalmente de fábrica para el "
                                    + "transporte de personas, que cumple con las condiciones y características técnicas establecidas en el "
                                    + "Reglamento Nacional de Vehículos aprobado por Decreto Supremo Nº 058-2003-MTC y los artículos 19º , 20º "
                                    + "del Reglamento Nacional de Administración de Transportes aprobado por Decreto Supremo Nº 017-2009-MTC y "
                                    + "en la normatividad emitida por la Autoridad competente, según consta en el Informe de Inspección Técnica "
                                    + "Vehicular Nº " + objCertificado.getNumInforme() + " del Expediente Interno Nº " + objCertificado.getNumExpediente() + ".");
                        } else {
                            if (tipoServicio == 5) { //Transporte privado de mercancías
                                objCertificado.setClaseAut("TRANSPORTE PRIVADO DE MERCANCÍAS DE ÁMBITO NACIONAL");
                                objCertificado.setTitulo("CERTIFICACIÓN TÉCNICA COMPLEMENTARIA PARA EL SERVICIO DE TRANSPORTE PRIVADO DE MERCANCÍAS");
                                objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección destinado "
                                        + "al servicio de transporte privado de mercancias, ha aprobado la Inspección Técnica Vehicular complementaria "
                                        + "al haberse verificado que se encuentra en buenas condiciones técnicas y mecánicas de funcionamiento, "
                                        + "que cumple con las condiciones y características técnicas establecidas en el Reglamento nacional de "
                                        + "Vehículos aprobado por Decreto Supremo Nº 058-2003-MTC y en los artículos 19º y 24º  del Reglamento "
                                        + "Nacional de Administración de Transportes aprobado por Decreto Supremo Nº 017-2009-MTC y en la normatividad "
                                        + "emitida por la Autoridad competente, según consta en el Informe de Inspección Técnica Vehicular Nº " + objCertificado.getNumInforme()
                                        + " del Expediente Interno Nº " + objCertificado.getNumExpediente() + ".");
                            } else {
                                if (tipoServicio == 6) { //Taxi
                                    objCertificado.setTitulo("CERTIFICACION TÉCNICA COMPLEMENTARIA PARA EL SERVICIO ESPECIAL DE TRANSPORTE PÚBLICO "
                                            + "DE PERSONAS EN TAXI");
                                    objCertificado.setClaseAut("SERVICIO DE TAXI");
                                    objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección "
                                            + "destinado al servicio especial de transporte público de personas en taxi, ha aprobado la Inspección "
                                            + "Técnica Vehicular complementaria al haberse verificado que se encuentra en buenas condiciones técnicas "
                                            + "y mecánicas de funcionamiento, que fue diseñado originalmente de fábrica para el transporte de personas,"
                                            + " cumple con las condiciones y requisitos técnicos establecidos para dicho servicio en el artículo 25º "
                                            + "del Reglamento Nacional de Vehículos aprobado por Decreto Supremo Nº 058-2003-MTC, en el artículo 19º "
                                            + "del Reglamento Nacional de Administración de Transportes aprobado por Decreto Supremo Nº 017-2009-MTC "
                                            + "y en la normatividad emitida por la Autoridad competente;  según consta en el Informe de Inspección "
                                            + "Técnica Vehicular Nº " + objCertificado.getNumInforme() + " del Expediente Interno Nº " + objCertificado.getNumExpediente() + ".");
                                } else {

                                    if (tipoServicio == 8) { //estudiantes
                                        objCertificado.setTitulo("CERTIFICACION TÉCNICA COMPLEMENTARIA PARA EL SERVICIO DE TRANSPORTE PUBLICO DE PERSONAS BAJO LA MODALIDAD DE TRANSPORTE ESPECIAL");
                                        objCertificado.setClaseAut("MODALIDAD :  TRANSPORTE DE ESTUDIANTES DE ÁMBITO: " + ambito);
                                        objCertificado.setTexto("Mediante el presente documento se certifica que el vehículo materia de inspección "
                                                + "destinado al servicio especial de transporte público de personas, bajo la modalidad de TRANSPORTE DE ESTUDIANTES "
                                                + "DE ÁMBITO " + ambito + " ha aprobado la Inspección "
                                                + "Técnica Vehicular complementaria al haberse verificado que se encuentra en buenas condiciones técnicas "
                                                + "y mecánicas de funcionamiento, que fue diseñado originalmente de fábrica para el transporte de personas,"
                                                + " cumple con las condiciones y requisitos técnicos establecidos para dicho servicio en el artículo 25º "
                                                + "del Reglamento Nacional de Vehículos aprobado por Decreto Supremo Nº 058-2003-MTC, en el artículo 19º "
                                                + "del Reglamento Nacional de Administración de Transportes aprobado por Decreto Supremo Nº 017-2009-MTC "
                                                + "y en la normatividad emitida por la Autoridad competente;  según consta en el Informe de Inspección "
                                                + "Técnica Vehicular Nº " + objCertificado.getNumInforme() + " del Expediente Interno Nº " + objCertificado.getNumExpediente() + ".");

                                    } else {
                                        objCertificado.setTitulo("");
                                        objCertificado.setClaseAut("");
                                        objCertificado.setTexto("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return objCertificado;
    }

    private Resultados ObtenerResultado() {
        Resultados objR = new Resultados();

        objR.setIdCertificado(idInforme);
        //objR.setIdCertificado(idCertificado);'KCS 03.04.2015

        objR.setDisEjes(1);

        if (jComboBox6.getSelectedIndex() == 0) {
            objR.setFreServ(1);//
        } else {
            objR.setFreServ(0);//
        }

        if (jComboBox1.getSelectedIndex() == 0) {
            objR.setEmigases(1);//
        } else {
            objR.setEmigases(0);//
        }

        if (jComboBox4.getSelectedIndex() == 0) {
            objR.setFreeEmer(1);// 
        } else {
            objR.setFreeEmer(0);//
        }

        if (jComboBox5.getSelectedIndex() == 0) {
            objR.setFreeEstac(1);// 
        } else {
            objR.setFreeEstac(0);// 
        }

        objR.setObservacion("");
        objR.setPisos(0);

        int neumaticos;
        if (("A".equals(jTextField21.getText()) || "".equals(jTextField21.getText())) && ("A".equals(jTextField22.getText()) || "".equals(jTextField22.getText()))
                && ("A".equals(jTextField23.getText()) || "".equals(jTextField23.getText())) && ("A".equals(jTextField24.getText()) || "".equals(jTextField24.getText()))
                && ("A".equals(jTextField25.getText()) || "".equals(jTextField25.getText()))) {
            neumaticos = 1;
        } else {
            neumaticos = 0;
        }

        objR.setProfNeuma(neumaticos);

        int luces;
        if (("A".equals(jTextField171.getText()) || "".equals(jTextField171.getText())) && ("A".equals(jTextField172.getText()) || "".equals(jTextField172.getText()))
                && ("A".equals(jTextField173.getText()) || "".equals(jTextField173.getText())) && ("A".equals(jTextField174.getText()) || "".equals(jTextField174.getText()))) {
            luces = 1;
        } else {
            luces = 0;
        }

        objR.setPruebLuces(luces);

        int alineamiento;
        if (("A".equals(jTextField10.getText()) || "".equals(jTextField10.getText())) && ("A".equals(jTextField11.getText()) || "".equals(jTextField11.getText()))
                && ("A".equals(jTextField15.getText()) || "".equals(jTextField15.getText())) && ("A".equals(jTextField13.getText()) || "".equals(jTextField13.getText()))
                && ("A".equals(jTextField14.getText()) || "".equals(jTextField14.getText()))) {
            alineamiento = 1;
        } else {
            alineamiento = 0;
        }

        objR.setPruebaAli(alineamiento);

        if (objR.getDisEjes() == 1 && objR.getFreServ() == 1 && objR.getFreeEmer() == 1 && objR.getFreeEstac() == 1 && objR.getEmigases()== 1 
                && objR.getPruebaAli() == 1 && objR.getPruebLuces() == 1 && objR.getProfNeuma() == 1) {
            ResultadoGeneral = 1;
        } else {
            ResultadoGeneral = 0;
        }

        return objR;
    }

    // BOTON GRABAR
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        resultado = validarVacios();
        if (resultado) {
            jButton4.setEnabled(false);
            //GRABAR CERTIFICADO
            Certificado objCertificado = ObtenerCertificado();
            CertificadoBL objCertBL = new CertificadoBL();
            objCertBL.registrarCertificado(objCertificado);

            //GRABAR FOTOGRAFÍAS
            if (jComboBox20.getSelectedIndex() != 7) {
                Fotografias objFoto = new Fotografias();
                objFoto.setNumDocEval(idInforme);
                //objFoto.setNumDocEval(idCertificado); 'KCS 03.04.2015
                objFoto.setTipoDocEval(1);
                objFoto.setFoto1(fisfoto1);
                objFoto.setFoto2(fisfoto2);
                objFoto.setFoto3(fisfoto3);

                objCertBL.registrarFotografia(objFoto);
            }

            //GRABAR RESULTADOS GENERALES
            ResultadoBL resultBL = new ResultadoBL();
            resultBL.registrarResultado(ObtenerResultado());
            FrenometroBL objFrenometroBL = new FrenometroBL();
            //RESULTADOS - PRUEBA DE FRENOS
            try {
                Frenometro objFrenometro1 = new Frenometro();

                objFrenometro1.setnEjes(1);
                if (!jTextField85.getText().equals("")) {
                    objFrenometro1.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                }
                if (!jTextField50.getText().equals("")) {
                    objFrenometro1.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                }
                if (!jTextField65.getText().equals("")) {
                    objFrenometro1.setFuerzaEstDer(Double.parseDouble(jTextField65.getText()));
                }
                if (!jTextField66.getText().equals("")) {
                    objFrenometro1.setFuerzaEstIzq(Double.parseDouble(jTextField66.getText()));
                }
                if (!jTextField30.getText().equals("")) {
                    objFrenometro1.setFuerzaServicioDer(Double.parseDouble(jTextField30.getText()));
                }
                if (!jTextField31.getText().equals("")) {
                    objFrenometro1.setFuerzaServicioIzq(Double.parseDouble(jTextField31.getText()));
                }
                if (!jTextField2.getText().equals("")) {
                    objFrenometro1.setPesoServicio(Double.parseDouble(jTextField2.getText()));
                }
                objFrenometro1.setResultadoEjeServicio(jTextField45.getText());
                objFrenometro1.setResultadoEjeEstacionamiento(jTextField76.getText());
                objFrenometro1.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro1.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                if (!jTextField40.getText().equals("")) {
                    objFrenometro1.setDesequilibrioServicio(Double.parseDouble(jTextField40.getText()));
                }
                if (!jTextField75.getText().equals("")) {
                    objFrenometro1.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField75.getText()));
                }
                objFrenometro1.setIdCerticado(idInforme);
                //objFrenometro1.setIdCerticado(idCertificado); 'KCS 03.04.2015

                objFrenometroBL.registrarFrenometro(objFrenometro1);
            } catch (NumberFormatException e) {
            }
            try {
                Frenometro objFrenometro2 = new Frenometro();
                objFrenometro2.setIdCerticado(idInforme);
                //objFrenometro2.setIdCerticado(idCertificado); 'KCS 03.04.2015
                objFrenometro2.setnEjes(2);
                if (!jTextField85.getText().equals("")) {
                    objFrenometro2.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                }
                if (!jTextField50.getText().equals("")) {
                    objFrenometro2.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                }
                if (!jTextField64.getText().equals("")) {
                    objFrenometro2.setFuerzaEstDer(Double.parseDouble(jTextField64.getText()));
                }
                if (!jTextField67.getText().equals("")) {
                    objFrenometro2.setFuerzaEstIzq(Double.parseDouble(jTextField67.getText()));
                }
                if (!jTextField32.getText().equals("")) {
                    objFrenometro2.setFuerzaServicioDer(Double.parseDouble(jTextField32.getText()));
                }
                if (!jTextField36.getText().equals("")) {
                    objFrenometro2.setFuerzaServicioIzq(Double.parseDouble(jTextField36.getText()));
                }
                if (!jTextField3.getText().equals("")) {
                    objFrenometro2.setPesoServicio(Double.parseDouble(jTextField3.getText()));
                }
                objFrenometro2.setResultadoEjeServicio(jTextField46.getText());
                objFrenometro2.setResultadoEjeEstacionamiento(jTextField77.getText());
                objFrenometro2.setResultadoGlobalEmergencia(String.valueOf(jComboBox6.getSelectedItem()));
                objFrenometro2.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro2.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                if (!jTextField41.getText().equals("")) {
                    objFrenometro2.setDesequilibrioServicio(Double.parseDouble(jTextField41.getText()));
                }
                if (!jTextField74.getText().equals("")) {
                    objFrenometro2.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField74.getText()));
                }

                objFrenometroBL.registrarFrenometro(objFrenometro2);
            } catch (NumberFormatException e) {
            }
            Frenometro objFrenometro3 = new Frenometro();

            try {
                objFrenometro3.setIdCerticado(idInforme);
                //objFrenometro3.setIdCerticado(idCertificado); 'KCS 03.04.2015
                objFrenometro3.setnEjes(3);
                if (!jTextField85.getText().equals("")) {
                    objFrenometro3.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                }
                if (!jTextField50.getText().equals("")) {
                    objFrenometro3.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                }
                if (!jTextField63.getText().equals("")) {
                    objFrenometro3.setFuerzaEstDer(Double.parseDouble(jTextField63.getText()));
                }
                if (!jTextField68.getText().equals("")) {
                    objFrenometro3.setFuerzaEstIzq(Double.parseDouble(jTextField68.getText()));
                }
                if (!jTextField33.getText().equals("")) {
                    objFrenometro3.setFuerzaServicioDer(Double.parseDouble(jTextField33.getText()));
                }
                if (!jTextField37.getText().equals("")) {
                    objFrenometro3.setFuerzaServicioIzq(Double.parseDouble(jTextField37.getText()));
                }
                if (!jTextField4.getText().equals("")) {
                    objFrenometro3.setPesoServicio(Double.parseDouble(jTextField4.getText()));
                }
                objFrenometro3.setResultadoEjeServicio(jTextField47.getText());
                objFrenometro3.setResultadoEjeEstacionamiento(jTextField78.getText());
                objFrenometro3.setResultadoGlobalEmergencia(String.valueOf(jComboBox6.getSelectedItem()));
                objFrenometro3.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro3.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                if (!jTextField42.getText().equals("")) {
                    objFrenometro3.setDesequilibrioServicio(Double.parseDouble(jTextField42.getText()));
                }
                if (!jTextField73.getText().equals("")) {
                    objFrenometro3.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField73.getText()));
                }

                objFrenometroBL.registrarFrenometro(objFrenometro3);
            } catch (NumberFormatException e) {
            }
            try {
                Frenometro objFrenometro4 = new Frenometro();
                objFrenometro4.setIdCerticado(idInforme);
                //objFrenometro4.setIdCerticado(idCertificado); 'KCS 03.04.2015
                objFrenometro4.setnEjes(4);
                if (!jTextField85.getText().equals("")) {
                    objFrenometro4.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                }
                if (!jTextField50.getText().equals("")) {
                    objFrenometro4.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                }
                if (!jTextField62.getText().equals("")) {
                    objFrenometro4.setFuerzaEstDer(Double.parseDouble(jTextField62.getText()));
                }
                if (!jTextField69.getText().equals("")) {
                    objFrenometro4.setFuerzaEstIzq(Double.parseDouble(jTextField69.getText()));
                }
                if (!jTextField34.getText().equals("")) {
                    objFrenometro4.setFuerzaServicioDer(Double.parseDouble(jTextField34.getText()));
                }
                if (!jTextField38.getText().equals("")) {
                    objFrenometro4.setFuerzaServicioIzq(Double.parseDouble(jTextField38.getText()));
                }
                if (!jTextField1.getText().equals("")) {
                    objFrenometro4.setPesoServicio(Double.parseDouble(jTextField1.getText()));
                }
                objFrenometro4.setResultadoEjeServicio(jTextField48.getText());
                objFrenometro4.setResultadoEjeEstacionamiento(jTextField79.getText());
                objFrenometro4.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro4.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                if (!jTextField43.getText().equals("")) {
                    objFrenometro4.setDesequilibrioServicio(Double.parseDouble(jTextField43.getText()));
                }
                if (!jTextField72.getText().equals("")) {
                    objFrenometro4.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField72.getText()));
                }

                objFrenometroBL.registrarFrenometro(objFrenometro4);
            } catch (NumberFormatException e) {
            }

            try {
                Frenometro objFrenometro5 = new Frenometro();
                objFrenometro5.setnEjes(5);
                objFrenometro5.setIdCerticado(idInforme);
                //objFrenometro5.setIdCerticado(idCertificado); 'KCS 03.04.2015
                if (!jTextField85.getText().equals("")) {
                    objFrenometro5.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                }
                if (!jTextField50.getText().equals("")) {
                    objFrenometro5.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                }
                if (!jTextField61.getText().equals("")) {
                    objFrenometro5.setFuerzaEstDer(Double.parseDouble(jTextField61.getText()));
                }
                if (!jTextField70.getText().equals("")) {
                    objFrenometro5.setFuerzaEstIzq(Double.parseDouble(jTextField70.getText()));
                }
                if (!jTextField35.getText().equals("")) {
                    objFrenometro5.setFuerzaServicioDer(Double.parseDouble(jTextField35.getText()));
                }
                if (!jTextField39.getText().equals("")) {
                    objFrenometro5.setFuerzaServicioIzq(Double.parseDouble(jTextField39.getText()));
                }
                if (!jTextField12.getText().equals("")) {
                    objFrenometro5.setPesoServicio(Double.parseDouble(jTextField12.getText()));
                }
                objFrenometro5.setResultadoEjeServicio(jTextField49.getText());
                objFrenometro5.setResultadoEjeEstacionamiento(jTextField80.getText());
                objFrenometro5.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro5.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                if (!jTextField44.getText().equals("")) {
                    objFrenometro5.setDesequilibrioServicio(Double.parseDouble(jTextField44.getText()));
                }
                if (!jTextField71.getText().equals("")) {
                    objFrenometro5.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField71.getText()));
                }

                objFrenometroBL.registrarFrenometro(objFrenometro5);
            } catch (NumberFormatException e) {
            }

            // DATOS DE LOS EQUIPOS
            try {
                EquipoCertificado objEquipos = new EquipoCertificado();
                objEquipos.setIdCertificado(idInforme);
                //objEquipos.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objEquipos.setNumAlineador(jComboBox16.getSelectedIndex());
                objEquipos.setNumAnalizador(jComboBox17.getSelectedIndex());
                objEquipos.setNumBancoSuspension(jComboBox19.getSelectedIndex());
                objEquipos.setNumFrenometro(jComboBox15.getSelectedIndex());
                objEquipos.setNumRegloscopio(jComboBox18.getSelectedIndex());

                EquipoCertificadoBL objEquipoBL = new EquipoCertificadoBL();
                objEquipoBL.registrarEquipoCertificado(objEquipos);
            } catch (NumberFormatException e) {
            }
            // RESULTADOS - PRUEBA DE ALINEAMIENTO
            try {
                Alineador objAlineador1 = new Alineador();
                objAlineador1.setIdCertificado(idInforme);
                //objAlineador1.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objAlineador1.setDesviacionejealineamiento(Double.parseDouble(jTextField5.getText()));
                objAlineador1.setEje(1);
                objAlineador1.setMedidaejeneumatico(Double.parseDouble(jTextField16.getText()));
                objAlineador1.setResultadoejealineamiento(jTextField10.getText());
                objAlineador1.setResultadoejeneumatico(jTextField21.getText());

                AlineadorBL objAlinBL = new AlineadorBL();
                objAlinBL.registrarAlineador(objAlineador1);
            } catch (NumberFormatException e) {
            }
            try {
                Alineador objAlineador2 = new Alineador();
                objAlineador2.setIdCertificado(idInforme);
                //objAlineador2.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objAlineador2.setDesviacionejealineamiento(Double.parseDouble(jTextField6.getText()));
                objAlineador2.setEje(2);
                objAlineador2.setMedidaejeneumatico(Double.parseDouble(jTextField17.getText()));
                objAlineador2.setResultadoejealineamiento(jTextField11.getText());
                objAlineador2.setResultadoejeneumatico(jTextField22.getText());
                AlineadorBL objAlinBL = new AlineadorBL();
                objAlinBL.registrarAlineador(objAlineador2);
            } catch (NumberFormatException e) {
            }

            try {
                Alineador objAlineador3 = new Alineador();
                objAlineador3.setIdCertificado(idInforme);
                //objAlineador3.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objAlineador3.setDesviacionejealineamiento(Double.parseDouble(jTextField7.getText()));
                objAlineador3.setEje(3);
                objAlineador3.setMedidaejeneumatico(Double.parseDouble(jTextField18.getText()));
                objAlineador3.setResultadoejealineamiento(jTextField13.getText());
                objAlineador3.setResultadoejeneumatico(jTextField23.getText());
                AlineadorBL objAlinBL = new AlineadorBL();
                objAlinBL.registrarAlineador(objAlineador3);
            } catch (NumberFormatException e) {
            }
            try {
                Alineador objAlineador4 = new Alineador();
                objAlineador4.setIdCertificado(idInforme);
                //objAlineador4.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objAlineador4.setDesviacionejealineamiento(Double.parseDouble(jTextField8.getText()));
                objAlineador4.setEje(4);
                objAlineador4.setMedidaejeneumatico(Double.parseDouble(jTextField19.getText()));
                objAlineador4.setResultadoejealineamiento(jTextField14.getText());
                objAlineador4.setResultadoejeneumatico(jTextField24.getText());
                AlineadorBL objAlinBL = new AlineadorBL();
                objAlinBL.registrarAlineador(objAlineador4);
            } catch (NumberFormatException e) {
            }

            try {
                Alineador objAlineador5 = new Alineador();
                objAlineador5.setIdCertificado(idInforme);
                //objAlineador5.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objAlineador5.setDesviacionejealineamiento(Double.parseDouble(jTextField9.getText()));
                objAlineador5.setEje(5);
                objAlineador5.setMedidaejeneumatico(Double.parseDouble(jTextField20.getText()));
                objAlineador5.setResultadoejealineamiento(jTextField15.getText());
                objAlineador5.setResultadoejeneumatico(jTextField25.getText());
                AlineadorBL objAlinBL = new AlineadorBL();
                objAlinBL.registrarAlineador(objAlineador5);
            } catch (NumberFormatException e) {
            }
            //// PRUEBA DE LUCES
            try {
                Luxometro objLuzBaja = new Luxometro();
                objLuzBaja.setIdCertificado(idInforme);
                //objLuzBaja.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objLuzBaja.setTipoLuz(1);
                objLuzBaja.setMedidaDerLuz(Double.parseDouble(jTextField159.getText()));
                objLuzBaja.setMedidaIzqLuz(Double.parseDouble(jTextField163.getText()));
                objLuzBaja.setAlineamientoLuz(String.valueOf(jComboBox31.getSelectedItem()));
                objLuzBaja.setResultadoLuz(jTextField171.getText());

                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzBaja);
            } catch (NumberFormatException e) {
            }

            try {
                Luxometro objLuzAlta = new Luxometro();
                objLuzAlta.setTipoLuz(2);
                objLuzAlta.setIdCertificado(idInforme);
                //objLuzAlta.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objLuzAlta.setMedidaDerLuz(Double.parseDouble(jTextField160.getText()));
                objLuzAlta.setMedidaIzqLuz(Double.parseDouble(jTextField168.getText()));
                objLuzAlta.setAlineamientoLuz(String.valueOf(jComboBox32.getSelectedItem()));
                objLuzAlta.setResultadoLuz(jTextField172.getText());
                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzAlta);
            } catch (NumberFormatException e) {
            }

            try {
                Luxometro objLuzAltaAd = new Luxometro();
                objLuzAltaAd.setTipoLuz(3);
                objLuzAltaAd.setIdCertificado(idInforme);
                //objLuzAltaAd.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objLuzAltaAd.setMedidaDerLuz(Double.parseDouble(jTextField161.getText()));
                objLuzAltaAd.setMedidaIzqLuz(Double.parseDouble(jTextField165.getText()));
                objLuzAltaAd.setAlineamientoLuz(String.valueOf(jComboBox33.getSelectedItem()));
                objLuzAltaAd.setResultadoLuz(jTextField173.getText());
                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzAltaAd);
            } catch (NumberFormatException e) {
            }

            try {
                Luxometro objLuzNeblineras = new Luxometro();
                objLuzNeblineras.setTipoLuz(4);
                objLuzNeblineras.setIdCertificado(idInforme);
                //objLuzNeblineras.setIdCertificado(idCertificado); 'KCS 03.04.2015
                objLuzNeblineras.setMedidaDerLuz(Double.parseDouble(jTextField162.getText()));
                objLuzNeblineras.setMedidaIzqLuz(Double.parseDouble(jTextField166.getText()));
                objLuzNeblineras.setAlineamientoLuz(String.valueOf(jComboBox34.getSelectedItem()));
                objLuzNeblineras.setResultadoLuz(jTextField174.getText());
                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzNeblineras);
            } catch (NumberFormatException e) {
            }
            ////EMISIÓN DE GASES
            try {
                Gasometro e = new Gasometro();
                e.setIdCertificado(idInforme);
                //e.setIdCertificado(idCertificado); 'KCS 03.04.2015

                if (jComboBox13.getSelectedIndex() == 0 || jComboBox13.getSelectedIndex() == 4) {
                    double tAceite = Double.parseDouble(jTextField185.getText());
                    double Rpm = Double.parseDouble(jTextField186.getText());
                    double Opacidad = Double.parseDouble(jTextField187.getText());
                    e.settAceite(tAceite);
                    e.setRpm(Rpm);
                    e.setOpacidad(Opacidad);
                } else {
                    double COralenti = Double.parseDouble(jTextField188.getText());
                    double COCO2ralenti = Double.parseDouble(jTextField189.getText());
                    double HCralenti = Double.parseDouble(jTextField190.getText());
                    double COAcel = Double.parseDouble(jTextField191.getText());
                    double COCO2Acel = Double.parseDouble(jTextField192.getText());
                    double HCAcel = Double.parseDouble(jTextField193.getText());
                    double tAceite = Double.parseDouble(jTextField185.getText());
                    double Rpm = Double.parseDouble(jTextField186.getText());
                    e.setCoRalent(COralenti);
                    e.setCoco2Ralenti(COCO2ralenti);
                    e.setHcRalentippm(HCralenti);
                    e.setCoAcel(COAcel);
                    e.setCoCo2Acel(COCO2Acel);
                    e.setHcAcel(HCAcel);
                    e.settAceite(tAceite);
                    e.setRpm(Rpm);
                }

                Integer EmiGResultFinal = jComboBox1.getSelectedIndex();

                if (EmiGResultFinal == 0) {
                    e.setResultado("APROBADO");
                } else {
                    e.setResultado("DESAPROBADO");
                }

                GasometroDL g = new GasometroDL();

                if (jComboBox13.getSelectedIndex() == 0 || jComboBox13.getSelectedIndex() == 4) {
                    g.registrarGasometroDiesel(e);
                } else {
                    g.registrarGasometroGasolina(e);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                ////EMISIONES SONORAS
                Sonometro s = new Sonometro();

                double Sonometro = Double.parseDouble(jTextField194.getText());
                Integer EmiSResultado = jComboBox2.getSelectedIndex();

                if (EmiSResultado == 0) {
                    s.setResultado("APROBADO");
                } else {
                    s.setResultado("DESAPROBADO");
                }

                s.setIdCertificado(idInforme);
                s.setSonometroValor(Sonometro);

                SonometroBL sb = new SonometroBL();
                sb.registrarSonometro(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            ///
            //Agregando Observaciones
            //
            //calcularObservaciones();
            HashSet<Observacion> hashSet = new HashSet<Observacion>(arrayObservaciones);
            arrayObservaciones.clear();
            arrayObservaciones.addAll(hashSet);
            ObservacionBL b = new ObservacionBL();
//            for (int i = 0; i < arrayObservaciones.size(); i++) {
//                Observacion obsTemp = (Observacion) arrayObservaciones.get(i);
//                obsTemp.setIdCertificado(idInforme);
//                //obsTemp.setIdCertificado(idCertificado); 'KCS 03.04.2015
//                String descripcion = b.obtenerDescripcion(obsTemp.getCodigoObservacion());
//                obsTemp.setInterpretacion(descripcion);
//                b.registrarObservacion(obsTemp);
//            }

            for (int i = 0; i < arrayObservaciones.size(); i++) {
                Observacion obsTemp = (Observacion) arrayObservaciones.get(i);
                obsTemp.setIdCertificado(idInforme);
                //obsTemp.setIdCertificado(idCertificado); 'KCS 02.04.2015
                String descripcion = b.obtenerDescripcion(obsTemp.getCodigoObservacion());
                if (descripcion.equalsIgnoreCase("No Conocida")) {

                } else {
                    obsTemp.setInterpretacion(descripcion);
                }
                b.registrarObservacion(obsTemp);
            }

            JOptionPane.showMessageDialog(null, "Registro guardado correctamente", "REGISTRO CERTIFICADO", 1);
            //objTarjetaP.equals(c)
            new TarjetaPropiedadBL().actualizarTarjetaPropiedad(objTarjetaP.getIdTarjeta());

            dispose();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void calcularObservaciones() {

        //OBSERVACIONES FRENO DE SERVICIO
        String DesequilibrioPrimerEjeServicio = jTextField40.getText();
        String DesequilibrioSegundoEjeServicio = jTextField41.getText();
        String DesequilibrioTercerEjeServicio = jTextField42.getText();
        String DesequilibrioCuartoEjeServicio = jTextField43.getText();
        String DesequilibrioQuintoEjeServicio = jTextField44.getText();
        Observacion obs = new Observacion();
        if (!DesequilibrioPrimerEjeServicio.equals("")) {
            double valor = Double.parseDouble(DesequilibrioPrimerEjeServicio);
            if (valor <= 20 && valor >= 15) {
                obs.setCodigoObservacion("D.1.7");
                obs.setCalificacion("LEVE");
                arrayObservaciones.add(obs);
            }
            if (valor <= 30 && valor >= 21) {
                obs.setCodigoObservacion("D.1.6");
                obs.setCalificacion("GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }

            if (valor >= 30) {
                obs.setCodigoObservacion("D.1.5");
                obs.setCalificacion("MUY GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }
        }
        if (!DesequilibrioSegundoEjeServicio.equals("")) {
            double valor = Double.parseDouble(DesequilibrioSegundoEjeServicio);
            if (valor <= 20 && valor >= 15) {
                obs.setCodigoObservacion("D.1.10");
                obs.setCalificacion("LEVE");
                arrayObservaciones.add(obs);
            }
            if (valor <= 30 && valor >= 21) {
                obs.setCodigoObservacion("D.1.9");
                obs.setCalificacion("GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }

            if (valor >= 30) {
                obs.setCodigoObservacion("D.1.8");
                obs.setCalificacion("MUY GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }
        }
        if (!DesequilibrioTercerEjeServicio.equals("")) {
            double valor = Double.parseDouble(DesequilibrioTercerEjeServicio);

            if (valor <= 30 && valor >= 21) {
                obs.setCodigoObservacion("D.1.9");
                obs.setCalificacion("GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }

            if (valor >= 30) {
                obs.setCodigoObservacion("D.1.8");
                obs.setCalificacion("MUY GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }
        }
        if (!DesequilibrioCuartoEjeServicio.equals("")) {
            double valor = Double.parseDouble(DesequilibrioCuartoEjeServicio);

            if (valor <= 30 && valor >= 21) {
                obs.setCodigoObservacion("D.1.9");
                obs.setCalificacion("GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }

            if (valor >= 30) {
                obs.setCodigoObservacion("D.1.8");
                obs.setCalificacion("MUY GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }
        }
        if (!DesequilibrioQuintoEjeServicio.equals("")) {
            double valor = Double.parseDouble(DesequilibrioQuintoEjeServicio);

            if (valor <= 30 && valor >= 21) {
                obs.setCodigoObservacion("D.1.9");
                obs.setCalificacion("GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }

            if (valor >= 30) {
                obs.setCodigoObservacion("D.1.8");
                obs.setCalificacion("MUY GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }
        }

        //OBSERVACIONES FRENO DE SERVICIO
        String eficienciaServicio = jTextField50.getText();
        if (!eficienciaServicio.equals("")) {
            double valor = Double.parseDouble(eficienciaServicio);
            if (valor >= 30 && valor <= 50) {
                obs.setCodigoObservacion("D.1.2");
                obs.setCalificacion("LEVE");
                arrayObservaciones.add(obs);
            }
            if (valor >= 20 && valor <= 29) {
                obs.setCodigoObservacion("D.1.3");
                obs.setCalificacion("GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }
            if (valor >= 0 && valor <= 20) {
                obs.setCodigoObservacion("D.1.4");
                obs.setCalificacion("MUY GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }

        }

        //OBSERVACIONES FRENO DE ESTACIONAMIENTO
        String eficienciaEstacionamiento = jTextField85.getText();
        if (!eficienciaEstacionamiento.equals("")) {
            double valor = Double.parseDouble(eficienciaEstacionamiento);
            //if (valor >= 5 && valor < 15) {
            if (valor < 15) {
                obs.setCodigoObservacion("D.1.11");
                obs.setCalificacion("GRAVE");
                arrayObservaciones.add(obs);
                ObsGravesMuyGraves = true;
            }
//            if (valor > 0 && valor < 5) {
//                obs.setCodigoObservacion("D.6.3");
//                obs.setCalificacion("MUY GRAVE");
//                arrayObservaciones.add(obs);
//                ObsGravesMuyGraves=true;
//            }
//            if (valor == 0) {
//                obs.setCodigoObservacion("D.6.3");
//                obs.setCalificacion("MUY GRAVE");
//                arrayObservaciones.add(obs);
//                ObsGravesMuyGraves=true;
//            }
        }

        // OBSERVACIONES I
        try {
            if (!jTextField58.getText().trim().equals("") && !jTextField59.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField58.getText().trim());
                obs1.setInterpretacion(jTextField59.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox7.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().trim().toUpperCase().equals("GRAVE") || obs1.getCalificacion().trim().toUpperCase().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }

        // OBSERVACIONES II
        try {
            if (!jTextField81.getText().trim().equals("") && !jTextField86.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField81.getText().trim());
                obs1.setInterpretacion(jTextField86.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox8.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().trim().toUpperCase().equals("GRAVE") || obs1.getCalificacion().trim().toUpperCase().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }

        // OBSERVACIONES III
        try {
            if (!jTextField88.getText().trim().equals("") && !jTextField89.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField88.getText().trim());
                obs1.setInterpretacion(jTextField89.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox9.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().trim().toUpperCase().equals("GRAVE") || obs1.getCalificacion().trim().toUpperCase().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }

        // OBSERVACIONES IV
        try {
            if (!jTextField91.getText().trim().equals("") && !jTextField117.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField91.getText().trim());
                obs1.setInterpretacion(jTextField117.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox10.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().trim().toUpperCase().equals("GRAVE") || obs1.getCalificacion().trim().toUpperCase().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }

        // OBSERVACIONES V
        try {
            if (!jTextField125.getText().trim().equals("") && !jTextField126.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField125.getText().trim());
                obs1.setInterpretacion(jTextField126.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox21.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().trim().toUpperCase().equals("GRAVE") || obs1.getCalificacion().trim().toUpperCase().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        
        //----------------------------------------------------------------------------------------------------------------
        
        // OBSERVACIONES VI
        try {
            if (!jTextField127.getText().trim().equals("") && !jTextField128.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField127.getText().trim());
                obs1.setInterpretacion(jTextField128.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox23.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        // OBSERVACIONES VII
        try {
            if (!jTextField129.getText().trim().equals("") && !jTextField130.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField129.getText().trim());
                obs1.setInterpretacion(jTextField130.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox24.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        // OBSERVACIONES VIII
        try {
            if (!jTextField132.getText().trim().equals("") && !jTextField131.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField132.getText().trim());
                obs1.setInterpretacion(jTextField131.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox25.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        // OBSERVACIONES IX
        try {
            if (!jTextField133.getText().trim().equals("") && !jTextField134.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField134.getText().trim());
                obs1.setInterpretacion(jTextField133.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox26.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        // OBSERVACIONES X
        try {
            if (!jTextField135.getText().trim().equals("") && !jTextField136.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField136.getText().trim());
                obs1.setInterpretacion(jTextField135.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox27.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        // OBSERVACIONES XI
        try {
            if (!jTextField137.getText().trim().equals("") && !jTextField138.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField138.getText().trim());
                obs1.setInterpretacion(jTextField137.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox28.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        // OBSERVACIONES XII
        try {
            if (!jTextField139.getText().trim().equals("") && !jTextField140.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField140.getText().trim());
                obs1.setInterpretacion(jTextField139.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox29.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }
        
        // OBSERVACIONES XIII
        try {
            if (!jTextField141.getText().trim().equals("") && !jTextField142.getText().trim().equals("")) {
                Observacion obs1 = new Observacion();
                obs1.setCodigoObservacion(jTextField142.getText().trim());
                obs1.setInterpretacion(jTextField141.getText().trim());
                obs1.setCalificacion(String.valueOf(jComboBox30.getSelectedItem()));
                arrayObservaciones.add(obs1);
                if (obs1.getCalificacion().equals("GRAVE") || obs1.getCalificacion().equals("MUY GRAVE")) {
                    ObsGravesMuyGraves = true;
                }
            }
        } catch (NumberFormatException e) {
        }

    }

    public boolean validarVacios() {

        /* primer freno servicio */
        if (!jTextField2.getText().trim().equals("")
                && !jTextField30.getText().trim().equals("") && !jTextField31.getText().trim().equals("")
                && !jTextField40.getText().trim().equals("") && !jTextField50.getText().trim().equals("")
                && !jTextField40.getText().trim().equals("")) {
            frenoServicioCompleto = true;
        }

        /* Segundo Freno Servicio */
        if (!jTextField3.getText().trim().equals("") && !jTextField32.getText().trim().equals("")
                && !jTextField36.getText().trim().equals("") && !jTextField41.getText().trim().equals("")
                && !jTextField36.getText().trim().equals("") && !jTextField50.getText().trim().equals("")) {
            frenoServicioCompleto = true;
        }

        /* Tercer Freno Servicio */
        if (!jTextField4.getText().trim().equals("") && !jTextField33.getText().trim().equals("")
                && !jTextField37.getText().trim().equals("") && !jTextField42.getText().trim().equals("")
                && !jTextField37.getText().trim().equals("") && !jTextField50.getText().trim().equals("")) {
            frenoServicioCompleto = true;
        }

        /* Cuarto Freno Servicio */
        if (!jTextField1.getText().trim().equals("") && !jTextField34.getText().trim().equals("")
                && !jTextField38.getText().trim().equals("") && !jTextField43.getText().trim().equals("")
                && !jTextField38.getText().trim().equals("") && !jTextField50.getText().trim().equals("")) {
            frenoServicioCompleto = true;
        }

        /* Quinto Freno Servicio */
        if (!jTextField12.getText().trim().equals("") && !jTextField35.getText().trim().equals("")
                && !jTextField39.getText().trim().equals("") && !jTextField44.getText().trim().equals("")
                && !jTextField39.getText().trim().equals("") && !jTextField50.getText().trim().equals("")) {
            frenoServicioCompleto = true;
        }

        /* primer freno Estacionamiento */
        if (!jTextField65.getText().trim().equals("") && !jTextField66.getText().trim().equals("")
                && !jTextField75.getText().trim().equals("") && !jTextField66.getText().trim().equals("")
                && !jTextField85.getText().trim().equals("")) {
            frenoEstacionamientoCompleto = true;
        }

        /* Segundo freno Estacionamiento */
        if (!jTextField64.getText().trim().equals("") && !jTextField67.getText().trim().equals("")
                && !jTextField74.getText().trim().equals("") && !jTextField67.getText().trim().equals("")
                && !jTextField85.getText().trim().equals("")) {
            frenoEstacionamientoCompleto = true;
        }

        /* Tercer freno Estacionamiento */
        if (!jTextField63.getText().trim().equals("") && !jTextField68.getText().trim().equals("")
                && !jTextField73.getText().trim().equals("") && !jTextField68.getText().trim().equals("")
                && !jTextField85.getText().trim().equals("")) {
            frenoEstacionamientoCompleto = true;
        }

        /* Cuarto freno Estacionamiento */
        if (!jTextField62.getText().trim().equals("") && !jTextField69.getText().trim().equals("")
                && !jTextField72.getText().trim().equals("") && !jTextField69.getText().trim().equals("")
                && !jTextField85.getText().trim().equals("")) {
            frenoEstacionamientoCompleto = true;
        }

        /* Quinto freno Estacionamiento */
        if (!jTextField61.getText().trim().equals("") && !jTextField70.getText().trim().equals("")
                && !jTextField71.getText().trim().equals("") && !jTextField70.getText().trim().equals("")
                && !jTextField85.getText().trim().equals("")) {
            frenoEstacionamientoCompleto = true;
        }

        /* validar Emision Gas  */
        if (jComboBox13.getSelectedIndex() == 0 || jComboBox13.getSelectedIndex() == 4) {
            if (!jTextField185.getText().trim().equals("") && !jTextField186.getText().trim().equals("")
                    && !jTextField187.getText().trim().equals("")) {
                gasometroCompleto = true;
            }
        } else {
            if (!jTextField188.getText().trim().equals("")
                    && !jTextField189.getText().trim().equals("") && !jTextField190.getText().trim().equals("")
                    && !jTextField191.getText().trim().equals("") && !jTextField192.getText().trim().equals("")
                    && !jTextField193.getText().trim().equals("")) {
                gasometroCompleto = true;

            }

        }

        /* validar Sonometro  */
        if (!jTextField194.getText().trim().equals("")) {
            sonometroCompleto = true;
        }

        /* Luces Bajas 
        !jTextField167.getText().trim().equals("") &&*/        
        if (!jTextField159.getText().trim().equals("") && !jTextField163.getText().trim().equals("")
                &&  !jTextField163.getText().trim().equals("")) {
            luxometroCompleto = true;
        }

        /* Luces Altas 
        !jTextField164.getText().trim().equals("") &&*/
        if (!jTextField160.getText().trim().equals("") && !jTextField168.getText().trim().equals("")
                &&  !jTextField168.getText().trim().equals("")) {
            luxometroCompleto = true;
        }

        /* Luces Altas Adicionales 
        !jTextField164.getText().trim().equals("") &&*/
        if (!jTextField160.getText().trim().equals("") && !jTextField168.getText().trim().equals("")
                &&  !jTextField168.getText().trim().equals("")) {
            luxometroCompleto = true;
        }

        /* Luces Neblineras 
        !jTextField170.getText().trim().equals("") &&*/
        if (!jTextField162.getText().trim().equals("") && !jTextField166.getText().trim().equals("")
                &&  !jTextField166.getText().trim().equals("")) {
            luxometroCompleto = true;
        }

        /* Alineamiento primer eje */
        if (!jTextField5.getText().trim().equals("") && !jTextField10.getText().trim().equals("")
                && !jTextField16.getText().trim().equals("") && !jTextField10.getText().trim().equals("")) {
            alineadorCompleto = true;
        }

        /* Alineamiento Segundo eje */
        if (!jTextField6.getText().trim().equals("") && !jTextField11.getText().trim().equals("")
                && !jTextField17.getText().trim().equals("") && !jTextField11.getText().trim().equals("")) {
            alineadorCompleto = true;
        }

        /* Alineamiento Tercer eje */
        if (!jTextField7.getText().trim().equals("") && !jTextField13.getText().trim().equals("")
                && !jTextField18.getText().trim().equals("") && !jTextField13.getText().trim().equals("")) {
            alineadorCompleto = true;
        }

        /* Alineamiento Cuarto eje */
        if (!jTextField8.getText().trim().equals("") && !jTextField14.getText().trim().equals("")
                && !jTextField19.getText().trim().equals("") && !jTextField14.getText().trim().equals("")) {
            alineadorCompleto = true;
        }

        /* Alineamiento Quinto eje */
        if (!jTextField9.getText().trim().equals("") && !jTextField15.getText().trim().equals("")
                && !jTextField20.getText().trim().equals("") && !jTextField15.getText().trim().equals("")) {
            alineadorCompleto = true;
        }

        if (jTextField28.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Complete Numero de Documento", "CAMPOS VACÍOS", 0);
            return false;
        }

        if (jComboBox20.getSelectedIndex() != 7) {
            if (!jTextField26.getText().trim().equals("") && !jTextField27.getText().trim().equals("")
                    && !jTextField29.getText().trim().equals("")) {
                fotosCompleto = true;
            }
        } else {
            fotosCompleto = true;
        }

        //Primera observaciones adicionales        
        if (!jTextField58.getText().trim().equals("") || !jTextField59.getText().trim().equals("")) {
            if (!jTextField58.getText().trim().equals("") && !jTextField59.getText().trim().equals("")) {
                observacionesCompleto = true;
            }
        } else {
            observacionesCompleto = true;
        }

        //Segunda fila de observaciones adicionales        
        if (!jTextField81.getText().trim().equals("")
                || !jTextField86.getText().trim().equals("")) {
            if (!jTextField81.getText().trim().equals("") && !jTextField86.getText().trim().equals("")) {
                observacionesCompleto = true;
            }
        } else {
            observacionesCompleto = true;
        }

        //Tercer observaciones adicionales        
        if (!jTextField88.getText().trim().equals("") || !jTextField89.getText().trim().equals("")) {
            if (!jTextField88.getText().trim().equals("") && !jTextField89.getText().trim().equals("")) {
                observacionesCompleto = true;
            }
        } else {
            observacionesCompleto = true;
        }

        //Cuarta fila de observaciones adicionales        
        if (!jTextField91.getText().trim().equals("") || !jTextField117.getText().trim().equals("")) {
            if (!jTextField91.getText().trim().equals("") && !jTextField117.getText().trim().equals("")) {
                observacionesCompleto = true;
            }
        } else {
            observacionesCompleto = true;
        }

        //Quinta fila de observaciones adicionales        
        if (!jTextField125.getText().trim().equals("") || !jTextField126.getText().trim().equals("")) {
            if (!jTextField125.getText().trim().equals("") && !jTextField126.getText().trim().equals("")) {
                observacionesCompleto = true;
            }
        } else {
            observacionesCompleto = true;
        }

        if (frenoServicioCompleto
                && frenoEstacionamientoCompleto
                && sonometroCompleto
                && gasometroCompleto
                && alineadorCompleto
                && luxometroCompleto
                && fotosCompleto
                && observacionesCompleto) {
            resultado = true;
            return true;
        } else {
            if (!frenoServicioCompleto) {
                JOptionPane.showMessageDialog(null, "Complete frenos de Servicio", "CAMPOS VACÍOS", 0);
            }
            if (!frenoEstacionamientoCompleto) {
                JOptionPane.showMessageDialog(null, "Complete frenos de Estacionamiento", "CAMPOS VACÍOS", 0);
            }
            if (!sonometroCompleto) {
                JOptionPane.showMessageDialog(null, "Complete campos de Emisión Sonora", "CAMPOS VACÍOS", 0);
            }
            if (!gasometroCompleto) {
                JOptionPane.showMessageDialog(null, "Complete campos de Emisión de Gases", "CAMPOS VACÍOS", 0);
            }
            if (!alineadorCompleto) {
                JOptionPane.showMessageDialog(null, "Complete campos de la Prueba de Alineamiento", "CAMPOS VACÍOS", 0);
            }

            if (!luxometroCompleto) {
                JOptionPane.showMessageDialog(null, "Complete campos de la Prueba de Luces", "CAMPOS VACÍOS", 0);
            }

            if (!fotosCompleto) {
                JOptionPane.showMessageDialog(null, "Adjunte todas las fotos", "CAMPOS VACÍOS", 0);
            }

            resultado = false;

            return false;

        }

    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField96ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField96ActionPerformed

    private void jTextField61KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField61KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField61KeyTyped

    private void jTextField18KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField18KeyTyped

    private void jTextField187KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField187KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField187KeyTyped

    private void jTextField40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField40ActionPerformed

    private void jTextField40InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextField40InputMethodTextChanged
        // TODO add your handling code here:        
    }//GEN-LAST:event_jTextField40InputMethodTextChanged

    private void jTextField40KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField40KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField40KeyPressed

    class PrimerEjeFrenometro implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField40.getText();

            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 14) {
                    jTextField45.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField45.setText("A");
                    consultarComboFrenoServicio();
                    // LEVE D 1.7 
                }
                if (valor >= 21 && valor <= 30) {

                    jTextField45.setText("D");

                    // GRAVE 1.6
                    consultarComboFrenoServicio();
                }
                if (valor >= 31) {
                    jTextField45.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.5
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {

            String x = jTextField40.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 14) {
                    jTextField45.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField45.setText("A");
                    consultarComboFrenoServicio();
                    // LEVE D 1.7 
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField45.setText("D");
                    consultarComboFrenoServicio();
                    // GRAVE 1.6
                }
                if (valor >= 31) {
                    jTextField45.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.5
                }
            }

        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class SegundoEjeFrenometro implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField41.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 14) {
                    jTextField46.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField46.setText("A");
                    consultarComboFrenoServicio();

                    //LEVE 1.10
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField46.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField46.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField41.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 14) {
                    jTextField46.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField46.setText("A");
                    consultarComboFrenoServicio();

                    //LEVE 1.10
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField46.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField46.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class TercerEjeFrenometro implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField42.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 20) {
                    jTextField47.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField47.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField47.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField42.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 20) {
                    jTextField47.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField47.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField47.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class CuartoEjeFrenometro implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField43.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 20) {
                    jTextField48.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField48.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField48.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField43.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 20) {
                    jTextField48.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField48.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField48.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class QuintoEjeFrenometro implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField44.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 20) {
                    jTextField49.setText("A");
                    consultarComboFrenoServicio();

                }
                if (valor >= 21 && valor <= 30) {
                    jTextField49.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField49.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
                if (valor <= 20) {
                    jTextField49.setText("A");
                    consultarComboFrenoServicio();
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField44.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);

                if (valor <= 20) {
                    jTextField49.setText("A");
                    consultarComboFrenoServicio();
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField49.setText("D");
                    consultarComboFrenoServicio();
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField49.setText("D");
                    consultarComboFrenoServicio();
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class EficienciaFrenoServicio implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField50.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor < 20) {
                    eficienciaServicio = false;
                    jComboBox6.setSelectedIndex(1);
                    //Muy Grave 1.4
                }
                if (valor >= 20 && valor <= 29) {
                    eficienciaServicio = false;
                    jComboBox6.setSelectedIndex(1);
                    //GRAVE 1.3
                }
                if (valor >= 30 && valor <= 50) {
                    eficienciaServicio = true;
                    if ((jTextField45.getText().equals("") || jTextField45.getText().equals("A"))
                            && (jTextField46.getText().equals("") || jTextField46.getText().equals("A"))
                            && (jTextField47.getText().equals("") || jTextField47.getText().equals("A"))
                            && (jTextField48.getText().equals("") || jTextField48.getText().equals("A"))
                            && (jTextField49.getText().equals("") || jTextField49.getText().equals("A"))) // LEVE 1.2
                    {
                        jComboBox6.setSelectedIndex(0);
                    } else {
                        jComboBox6.setSelectedIndex(1);
                    }
                }
                if (valor > 50) {
                    eficienciaServicio = true;
                    if ((jTextField45.getText().equals("") || jTextField45.getText().equals("A"))
                            && (jTextField46.getText().equals("") || jTextField46.getText().equals("A"))
                            && (jTextField47.getText().equals("") || jTextField47.getText().equals("A"))
                            && (jTextField48.getText().equals("") || jTextField48.getText().equals("A"))
                            && (jTextField49.getText().equals("") || jTextField49.getText().equals("A"))) // LEVE 1.2
                    {
                        jComboBox6.setSelectedIndex(0);
                    } else {
                        jComboBox6.setSelectedIndex(1);
                    }
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField50.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor < 20) {
                    eficienciaServicio = false;
                    jComboBox6.setSelectedIndex(1);
                    //Muy Grave 1.4
                }
                if (valor >= 20 && valor <= 29) {
                    eficienciaServicio = false;
                    jComboBox6.setSelectedIndex(1);
                    //GRAVE 1.3
                }
                if (valor >= 30 && valor <= 50) {
                    eficienciaServicio = true;
                    if ((jTextField45.getText().equals("") || jTextField45.getText().equals("A"))
                            && (jTextField46.getText().equals("") || jTextField46.getText().equals("A"))
                            && (jTextField47.getText().equals("") || jTextField47.getText().equals("A"))
                            && (jTextField48.getText().equals("") || jTextField48.getText().equals("A"))
                            && (jTextField49.getText().equals("") || jTextField49.getText().equals("A"))) // LEVE 1.2
                    {
                        jComboBox6.setSelectedIndex(0);
                    } else {
                        jComboBox6.setSelectedIndex(1);
                    }
                }
                if (valor >= 50) {
                    eficienciaServicio = true;
                    if ((jTextField45.getText().equals("") || jTextField45.getText().equals("A"))
                            && (jTextField46.getText().equals("") || jTextField46.getText().equals("A"))
                            && (jTextField47.getText().equals("") || jTextField47.getText().equals("A"))
                            && (jTextField48.getText().equals("") || jTextField48.getText().equals("A"))
                            && (jTextField49.getText().equals("") || jTextField49.getText().equals("A"))) // LEVE 1.2
                    {
                        jComboBox6.setSelectedIndex(0);
                    } else {
                        jComboBox6.setSelectedIndex(1);
                    }
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class EficienciaFrenoEstacionamiento implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField85.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor >= 15) {
                    eficienciaEstacionamiento = true;
                    jComboBox5.setSelectedIndex(0);
                    //Aprobado
                }
                if (valor >= 6 && valor <= 14) {
                    eficienciaEstacionamiento = false;
                    jComboBox5.setSelectedIndex(1);
                    //GRAVE D.1.11
                }
                if (valor <= 5 && valor > 0) {
                    eficienciaEstacionamiento = false;
                    jComboBox5.setSelectedIndex(1);
                    // Muy GRAVE D.6.3
                }
                if (valor == 0) {
                    jComboBox5.setSelectedIndex(1);
                    // Muy GRAVE D.6.3
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField85.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor >= 15) {
                    eficienciaEstacionamiento = true;
                    jComboBox5.setSelectedIndex(0);
                    //Aprobado
                }
                if (valor >= 6 && valor <= 14) {
                    eficienciaEstacionamiento = false;
                    jComboBox5.setSelectedIndex(1);
                    //GRAVE D.1.11
                }
                if (valor <= 5 && valor > 0) {
                    eficienciaEstacionamiento = false;
                    jComboBox5.setSelectedIndex(1);
                    // Muy GRAVE D.6.3
                }
                if (valor == 0) {
                    jComboBox5.setSelectedIndex(1);
                    // Muy GRAVE D.6.3
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    private void consultarComboFrenoServicio() {
        if ((jTextField45.getText().equals("") || jTextField45.getText().equals("A"))
                && (jTextField46.getText().equals("") || jTextField46.getText().equals("A"))
                && (jTextField47.getText().equals("") || jTextField47.getText().equals("A"))
                && (jTextField48.getText().equals("") || jTextField48.getText().equals("A"))
                && (jTextField49.getText().equals("") || jTextField49.getText().equals("A"))) // LEVE 1.2
        {
            String x = jTextField50.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor < 20) {
                    eficienciaServicio = false;
                    jComboBox6.setSelectedIndex(1);
                    //Muy Grave 1.4
                }
                if (valor >= 20 && valor <= 29) {
                    eficienciaServicio = false;
                    jComboBox6.setSelectedIndex(1);
                    //GRAVE 1.3
                }
                if (valor >= 30 && valor <= 50) {
                    eficienciaServicio = true;
                    if ((jTextField45.getText().equals("") || jTextField45.getText().equals("A"))
                            && (jTextField46.getText().equals("") || jTextField46.getText().equals("A"))
                            && (jTextField47.getText().equals("") || jTextField47.getText().equals("A"))
                            && (jTextField48.getText().equals("") || jTextField48.getText().equals("A"))
                            && (jTextField49.getText().equals("") || jTextField49.getText().equals("A"))) // LEVE 1.2
                    {
                        jComboBox6.setSelectedIndex(0);
                    } else {
                        jComboBox6.setSelectedIndex(1);
                    }
                }
                if (valor > 50) {
                    eficienciaServicio = true;
                    if ((jTextField45.getText().equals("") || jTextField45.getText().equals("A"))
                            && (jTextField46.getText().equals("") || jTextField46.getText().equals("A"))
                            && (jTextField47.getText().equals("") || jTextField47.getText().equals("A"))
                            && (jTextField48.getText().equals("") || jTextField48.getText().equals("A"))
                            && (jTextField49.getText().equals("") || jTextField49.getText().equals("A"))) // LEVE 1.2
                    {
                        jComboBox6.setSelectedIndex(0);
                    } else {
                        jComboBox6.setSelectedIndex(1);
                    }
                }
            }
        } else {
            jComboBox6.setSelectedIndex(1);
        }
    }

    class AlineamientoDesviacionEje1 implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField5.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField10.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField10.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField10.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField5.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField10.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField10.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField10.setText("D");
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class AlineamientoDesviacionEje2 implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField6.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField11.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField11.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField11.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField6.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField11.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField11.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField11.setText("D");
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class AlineamientoDesviacionEje3 implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField7.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField13.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField13.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField13.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField7.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField13.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField13.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField13.setText("D");
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class AlineamientoDesviacionEje4 implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField8.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField14.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField14.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField14.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField8.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField14.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField14.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField14.setText("D");
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class AlineamientoDesviacionEje5 implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField9.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField15.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField15.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField15.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField9.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 10 && valor >= 0) {
                    jTextField15.setText("A");
                }
                if (valor > 10 && valor <= 12) {
                    jTextField15.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 12) {
                    jTextField15.setText("D");
                    // MUY GRAVE 1.8
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class ProfNeumaticos1 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField16.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField21.setText("D");
                } else {
                    jTextField21.setText("A");
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField16.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField21.setText("D");
                } else {
                    jTextField21.setText("A");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class ProfNeumaticos2 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField17.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField22.setText("D");
                } else {
                    jTextField22.setText("A");
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField17.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField22.setText("D");
                } else {
                    jTextField22.setText("A");
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class ProfNeumaticos3 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField18.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField23.setText("D");
                } else {
                    jTextField23.setText("A");
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField18.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField23.setText("D");
                } else {
                    jTextField23.setText("A");
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class ProfNeumaticos4 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField19.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField24.setText("D");
                } else {
                    jTextField24.setText("A");
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField19.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField24.setText("D");
                } else {
                    jTextField24.setText("A");
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class ProfNeumaticos5 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField20.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField25.setText("D");
                } else {
                    jTextField25.setText("A");
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField20.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField25.setText("D");
                } else {
                    jTextField25.setText("A");
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    private double calcularMayor(double a, double b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    class Frenado01 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField65.getText();
            String y = jTextField66.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField75.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField65.getText();
            String y = jTextField66.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField75.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class Frenado02 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField64.getText();
            String y = jTextField67.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField74.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField64.getText();
            String y = jTextField67.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField74.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class Frenado03 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField63.getText();
            String y = jTextField68.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField73.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField63.getText();
            String y = jTextField68.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField73.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class Frenado04 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField62.getText();
            String y = jTextField69.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField72.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField62.getText();
            String y = jTextField69.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField72.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class Frenado05 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField61.getText();
            String y = jTextField70.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField71.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField61.getText();
            String y = jTextField70.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField71.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class FrenadoServicio01 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField30.getText();
            String y = jTextField31.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField40.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField30.getText();
            String y = jTextField31.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField40.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class FrenadoServicio02 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField32.getText();
            String y = jTextField36.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField41.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField32.getText();
            String y = jTextField36.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField41.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class FrenadoServicio03 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField33.getText();
            String y = jTextField37.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField42.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField33.getText();
            String y = jTextField37.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField42.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class FrenadoServicio04 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField34.getText();
            String y = jTextField38.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField43.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField34.getText();
            String y = jTextField38.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField43.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class FrenadoServicio05 implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField35.getText();
            String y = jTextField39.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField44.setText(String.valueOf((int) Math.round(resultado)));

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField35.getText();
            String y = jTextField39.getText();
            if (!x.equals("") && !y.equals("")) {
                double xd = Double.parseDouble(x);
                double yd = Double.parseDouble(y);

                double resta = xd - yd;
                double resultado = 100 * Math.abs(resta / calcularMayor(xd, yd));
                jTextField44.setText(String.valueOf((int) Math.round(resultado)));

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

////    class SuspensionDelanteraIzq implements DocumentListener {
////
////        public void insertUpdate(DocumentEvent e) {
////            //String x = jTextField177.getText();
//////            if (!x.equals("")) {
//////                double valor = Double.parseDouble(x);
//////                if (valor <= 29 && valor >= 0) {
//////                    suspensionIzqDel = false;
//////
//////                }
//////                if (valor <= 40 && valor >= 30) {
//////                    suspensionIzqDel = false;
//////
//////                }
//////                if (valor >= 41) {
//////                    suspensionIzqDel = true;
//////
//////                }
//////
//////                if (suspensionIzqDel && suspensionDerDel) {
//////                    jComboBox7.setSelectedIndex(0);
//////                    jTextField180.setText("A");
//////                } else {
//////                    jComboBox7.setSelectedIndex(1);
//////                    jTextField180.setText("D");
//////                }
//////
//////            }
////
////        }
////
//////        public void removeUpdate(DocumentEvent e) {
//////            String x = jTextField177.getText();
//////            if (!x.equals("")) {
//////                double valor = Double.parseDouble(x);
//////                if (valor <= 29 && valor >= 0) {
//////                    suspensionIzqDel = false;
//////
//////                }
//////                if (valor <= 40 && valor >= 30) {
//////                    suspensionIzqDel = false;
//////
//////                }
//////                if (valor >= 41) {
//////                    suspensionIzqDel = true;
//////
//////                }
//////
//////                if (suspensionIzqDel && suspensionDerDel) {
//////                    jComboBox7.setSelectedIndex(0);
//////                    jTextField180.setText("A");
//////                } else {
//////                    jComboBox7.setSelectedIndex(1);
//////                    jTextField180.setText("D");
//////                }
//////
//////            }
//////        }
////
////        public void changedUpdate(DocumentEvent e) {
////        }
////    }
////
////    class SuspensionDelanteraDer implements DocumentListener {
////
////        public void insertUpdate(DocumentEvent e) {
////            String x = jTextField178.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDerDel = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDerDel = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDerDel = true;
////
////                }
////
////                if (suspensionIzqDel && suspensionDerDel) {
////                    jComboBox7.setSelectedIndex(0);
////                    jTextField180.setText("A");
////                } else {
////                    jComboBox7.setSelectedIndex(1);
////                    jTextField180.setText("D");
////                }
////
////            }
////
////        }
////
////        public void removeUpdate(DocumentEvent e) {
////            String x = jTextField178.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDerDel = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDerDel = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDerDel = true;
////
////                }
////
////                if (suspensionIzqDel && suspensionDerDel) {
////                    jComboBox7.setSelectedIndex(0);
////                    jTextField180.setText("A");
////                } else {
////                    jComboBox7.setSelectedIndex(1);
////                    jTextField180.setText("D");
////                }
////
////            }
////        }
////
////        public void changedUpdate(DocumentEvent e) {
////        }
////    }
////
////    class SuspensionDelanteraDesv implements DocumentListener {
////
////        public void insertUpdate(DocumentEvent e) {
////            String x = jTextField179.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDesvDel = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDesvDel = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDesvDel = true;
////
////                }
////
////                if (suspensionIzqDel && suspensionDerDel) {
////                    jComboBox7.setSelectedIndex(0);
////                    jTextField180.setText("A");
////                } else {
////                    jComboBox7.setSelectedIndex(1);
////                    jTextField180.setText("D");
////                }
////
////            }
////
////        }
////
////        public void removeUpdate(DocumentEvent e) {
////            String x = jTextField179.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDesvDel = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDesvDel = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDesvDel = true;
////
////                }
////
////                if (suspensionIzqDel && suspensionDerDel) {
////                    jComboBox7.setSelectedIndex(0);
////                    jTextField180.setText("A");
////
////                } else {
////                    jComboBox7.setSelectedIndex(1);
////                    jTextField180.setText("D");
////                }
////
////            }
////        }
////
////        public void changedUpdate(DocumentEvent e) {
////        }
////    }
////
////    class SuspensionPosteriorIzq implements DocumentListener {
////
////        public void insertUpdate(DocumentEvent e) {
////            String x = jTextField181.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionIzqPost = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionIzqPost = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionIzqPost = true;
////
////                }
////
////                if (suspensionIzqPost && suspensionDerPost) {
////                    jComboBox8.setSelectedIndex(0);
////                    jTextField184.setText("A");
////                } else {
////                    jComboBox8.setSelectedIndex(1);
////                    jTextField184.setText("D");
////                }
////
////            }
////
////        }
////
////        public void removeUpdate(DocumentEvent e) {
////            String x = jTextField181.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionIzqPost = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionIzqPost = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionIzqPost = true;
////
////                }
////
////                if (suspensionIzqPost && suspensionDerPost) {
////                    jComboBox8.setSelectedIndex(0);
////                    jTextField184.setText("A");
////                } else {
////                    jComboBox8.setSelectedIndex(1);
////                    jTextField184.setText("D");
////                }
////
////            }
////        }
////
////        public void changedUpdate(DocumentEvent e) {
////        }
////    }
////
////    class SuspensionPosteriorDer implements DocumentListener {
////
////        public void insertUpdate(DocumentEvent e) {
////            String x = jTextField182.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDerPost = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDerPost = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDerPost = true;
////
////                }
////
////                if (suspensionIzqPost && suspensionDerPost) {
////                    jComboBox8.setSelectedIndex(0);
////                    jTextField184.setText("A");
////                } else {
////                    jComboBox8.setSelectedIndex(1);
////                    jTextField184.setText("D");
////                }
////
////            }
////
////        }
////
////        public void removeUpdate(DocumentEvent e) {
////            String x = jTextField182.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDerPost = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDerPost = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDerPost = true;
////
////                }
////
////                if (suspensionIzqPost && suspensionDerPost) {
////                    jComboBox8.setSelectedIndex(0);
////                    jTextField184.setText("A");
////                } else {
////                    jComboBox8.setSelectedIndex(1);
////                    jTextField184.setText("D");
////                }
////
////            }
////        }
////
////        public void changedUpdate(DocumentEvent e) {
////        }
////    }
////
////    class SuspensionPosteriorDesv implements DocumentListener {
////
////        public void insertUpdate(DocumentEvent e) {
////            String x = jTextField183.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDesvPost = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDesvPost = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDesvPost = true;
////
////                }
////
////                if (suspensionIzqPost && suspensionDerPost) {
////                    jComboBox8.setSelectedIndex(0);
////                    jTextField184.setText("A");
////                } else {
////                    jComboBox8.setSelectedIndex(1);
////                    jTextField184.setText("D");
////                }
////
////            }
////
////        }
////
////        public void removeUpdate(DocumentEvent e) {
////            String x = jTextField183.getText();
////            if (!x.equals("")) {
////                double valor = Double.parseDouble(x);
////                if (valor <= 29 && valor >= 0) {
////                    suspensionDesvPost = false;
////
////                }
////                if (valor <= 40 && valor >= 30) {
////                    suspensionDesvPost = false;
////
////                }
////                if (valor >= 41) {
////                    suspensionDesvPost = true;
////
////                }
////
////                if (suspensionIzqDel && suspensionDerDel) {
////                    jComboBox8.setSelectedIndex(0);
////                    jTextField184.setText("A");
////
////                } else {
////                    jComboBox8.setSelectedIndex(1);
////                    jTextField184.setText("D");
////                }
////
////            }
////        }
////
////        public void changedUpdate(DocumentEvent e) {
////        }
////    }
    class validarLuxometroBajas implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String der = jTextField159.getText();
            String izq = jTextField163.getText();
            //String ali = jTextField167.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer >= 4 && valorIzq >= 4) {
                    jTextField171.setText("A");

                } else {
                    jTextField171.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String der = jTextField159.getText();
            String izq = jTextField163.getText();
            //String ali = jTextField167.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer >= 4 && valorIzq >= 4) {
                    jTextField171.setText("A");

                } else {
                    jTextField171.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class validarLuxometroAltas implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String der = jTextField160.getText();
            String izq = jTextField168.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer <= 80 && valorDer >= 20 && valorIzq <= 80 && valorIzq >= 20) {
                    jTextField172.setText("A");

                } else {
                    jTextField172.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String der = jTextField160.getText();
            String izq = jTextField168.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer <= 80 && valorDer >= 20 && valorIzq <= 80 && valorIzq >= 20) {
                    jTextField172.setText("A");

                } else {
                    jTextField172.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class validarLuxometroAltasAdicionales implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String der = jTextField161.getText();
            String izq = jTextField165.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer <= 80 && valorDer >= 20 && valorIzq <= 80 && valorIzq >= 20) {
                    jTextField173.setText("A");

                } else {
                    jTextField173.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String der = jTextField161.getText();
            String izq = jTextField165.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer <= 80 && valorDer >= 20 && valorIzq <= 80 && valorIzq >= 20) {
                    jTextField173.setText("A");

                } else {
                    jTextField173.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class validarLuxometroNeblineras implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String der = jTextField162.getText();
            String izq = jTextField166.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer <= 80 && valorDer >= 20 && valorIzq <= 80 && valorIzq >= 20) {
                    jTextField174.setText("A");

                } else {
                    jTextField174.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String der = jTextField162.getText();
            String izq = jTextField166.getText();
            if (!der.equals("") && !izq.equals("")) {
                double valorDer = Double.parseDouble(der);
                double valorIzq = Double.parseDouble(izq);
                if (valorDer <= 80 && valorDer >= 20 && valorIzq <= 80 && valorIzq >= 20) {
                    jTextField174.setText("A");

                } else {
                    jTextField174.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class SonometroResultado implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField194.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 90 && valor >= 70) {
                    sonometroResult = true;
                    jComboBox2.setSelectedIndex(0);

                } else {
                    jComboBox2.setSelectedIndex(1);
                    sonometroResult = false;
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField194.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 90 && valor >= 70) {
                    sonometroResult = true;
                    jComboBox2.setSelectedIndex(0);

                } else {
                    sonometroResult = false;
                    jComboBox2.setSelectedIndex(1);
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class validarGasometro implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            double tAceite = 0, Rpm = 0, Opacidad = 0;
            if (jComboBox13.getSelectedIndex() == 0 || jComboBox13.getSelectedIndex() == 4) {
                if (!jTextField185.getText().equals("")) {
                    tAceite = Double.parseDouble(jTextField185.getText());
                }
                if (!jTextField186.getText().equals("")) {
                    Rpm = Double.parseDouble(jTextField186.getText());
                }
                if (!jTextField187.getText().equals("")) {
                    Opacidad = Double.parseDouble(jTextField187.getText());
                }

                //iniciamos la validacion para Diesel
                // Menor a 1995
                if (objTarjetaP.getFabricacion() <= 1995) {
                    if (Opacidad > 3 || Rpm < 2250 || Rpm > 2750 || tAceite < 70 || tAceite > 90) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 1995 && objTarjetaP.getFabricacion() <= 2002) {
                    if (Opacidad > 2.5 || Rpm < 2250 || Rpm > 2750 || tAceite < 70 || tAceite > 90) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 2002) {
                    if (Opacidad > 2.1 || Rpm < 2250 || Rpm > 2750 || tAceite < 70 || tAceite > 90) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }

            } else {
                double COralenti = 0, COCO2ralenti = 0, HCralenti = 0, COAcel = 0, COCO2Acel = 0, HCAcel = 0;
                if (!jTextField188.getText().equals("")) {
                    COralenti = Double.parseDouble(jTextField188.getText());
                }
                if (!jTextField189.getText().equals("")) {
                    COCO2ralenti = Double.parseDouble(jTextField189.getText());
                }
                if (!jTextField190.getText().equals("")) {
                    HCralenti = Double.parseDouble(jTextField190.getText());
                }
                if (!jTextField191.getText().equals("")) {
                    COAcel = Double.parseDouble(jTextField191.getText());
                }
                if (!jTextField192.getText().equals("")) {
                    COCO2Acel = Double.parseDouble(jTextField192.getText());
                }
                if (!jTextField193.getText().equals("")) {
                    HCAcel = Double.parseDouble(jTextField193.getText());
                }

                //iniciamos la validacion para Diesel
                // Menor a 1995
                if (objTarjetaP.getFabricacion() <= 1995) {
                    if (COralenti > 3 || HCralenti > 400 || HCAcel > 400 || COCO2ralenti <= 10 || COCO2Acel <= 10) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 1995 && objTarjetaP.getFabricacion() <= 2002) {
                    if (COralenti > 2.5 || HCralenti > 300 || HCAcel > 300 || COCO2ralenti <= 10 || COCO2Acel <= 10) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 2002) {
                    if (COralenti > 0.5 || HCralenti > 100 || HCAcel > 100 || COCO2ralenti <= 12 || COCO2Acel <= 12) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            double tAceite = 0, Rpm = 0, Opacidad = 0;
            if (jComboBox13.getSelectedIndex() == 0 || jComboBox13.getSelectedIndex() == 4) {
                if (!jTextField185.getText().equals("")) {
                    tAceite = Double.parseDouble(jTextField185.getText());
                }
                if (!jTextField186.getText().equals("")) {
                    Rpm = Double.parseDouble(jTextField186.getText());
                }
                if (!jTextField187.getText().equals("")) {
                    Opacidad = Double.parseDouble(jTextField187.getText());
                }

                //iniciamos la validacion para Diesel
                // Menor a 1995
                if (objTarjetaP.getFabricacion() <= 1995) {
                    if (Opacidad > 3 || Rpm < 2250 || Rpm > 2750 || tAceite < 70 || tAceite > 90) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 1995 && objTarjetaP.getFabricacion() <= 2002) {
                    if (Opacidad > 2.5 || Rpm < 2250 || Rpm > 2750 || tAceite < 70 || tAceite > 90) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 2002) {
                    if (Opacidad > 2.1 || Rpm < 2250 || Rpm > 2750 || tAceite < 70 || tAceite > 90) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }

            } else {
                double COralenti = 0, COCO2ralenti = 0, HCralenti = 0, COAcel = 0, COCO2Acel = 0, HCAcel = 0;
                if (!jTextField188.getText().equals("")) {
                    COralenti = Double.parseDouble(jTextField188.getText());
                }
                if (!jTextField189.getText().equals("")) {
                    COCO2ralenti = Double.parseDouble(jTextField189.getText());
                }
                if (!jTextField190.getText().equals("")) {
                    HCralenti = Double.parseDouble(jTextField190.getText());
                }
                if (!jTextField191.getText().equals("")) {
                    COAcel = Double.parseDouble(jTextField191.getText());
                }
                if (!jTextField192.getText().equals("")) {
                    COCO2Acel = Double.parseDouble(jTextField192.getText());
                }
                if (!jTextField193.getText().equals("")) {
                    HCAcel = Double.parseDouble(jTextField193.getText());
                }

                //iniciamos la validacion para Diesel
                // Menor a 1995
                if (objTarjetaP.getFabricacion() <= 1995) {
                    if (COralenti > 3 || HCralenti > 400 || HCAcel > 400 || COCO2ralenti > 10 || COCO2Acel > 10) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 1995 && objTarjetaP.getFabricacion() <= 2002) {
                    if (COralenti > 2.5 || HCralenti > 300 || HCAcel > 300 || COCO2ralenti > 10 || COCO2Acel > 10) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
                if (objTarjetaP.getFabricacion() > 2002) {
                    if (COralenti > 0.5 || HCralenti > 100 || HCAcel > 100 || COCO2ralenti > 12 || COCO2Acel > 12) {
                        jComboBox1.setSelectedIndex(1);
                    } else {
                        jComboBox1.setSelectedIndex(0);
                    }
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }


    private void jTextField40FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField40FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField40FocusGained

    private void jTextField40FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField40FocusLost
    }//GEN-LAST:event_jTextField40FocusLost

    private void jTextField51KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField51KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField51KeyTyped

    private void jTextField51KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField51KeyPressed
    }//GEN-LAST:event_jTextField51KeyPressed

    private void jTextField119KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField119KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField119KeyTyped

    private void jTextField52KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField52KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField52KeyTyped

    private void jTextField52KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField52KeyPressed
    }//GEN-LAST:event_jTextField52KeyPressed

    private void jTextField124KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField124KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField124KeyTyped

    private void jTextField124KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField124KeyPressed
    }//GEN-LAST:event_jTextField124KeyPressed

    private void jTextField115KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField115KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField115KeyTyped

    private void jTextField115KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField115KeyPressed
    }//GEN-LAST:event_jTextField115KeyPressed

    private void jTextField113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField113ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField113ActionPerformed

    private void jTextField84KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField84KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField84KeyTyped

    private void jTextField84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField84ActionPerformed

    //CAMPO NOMBRE DEL TITULAR
    private void jTextField82KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField82KeyTyped
        // TODO add your handling code here:
//        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
//        if (k > 47 && k < 58) {//Si el caracter ingresado es una letra
//            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
//            JOptionPane.showMessageDialog(null, "No puede ingresar numeros!!!", "Validando Datos",
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_jTextField82KeyTyped

    private void jTextField82InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextField82InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField82InputMethodTextChanged

    private void jTextField55KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField55KeyTyped
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField55KeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        // TODO add your handling code here:
        if (objTarjetaP != null) {
            jTextField82.setText(objTarjetaP.getNombrePropietario()); //
            jTextField83.setText(objTarjetaP.getPlaca()); // placa
            jTextField84.setText(String.valueOf(objTarjetaP.getKilometraje())); //kilometraje
            jTextField112.setText(objTarjetaP.getnSerie()); //nserie
            jTextField113.setText(objTarjetaP.getnMotor()); //nmotor
            jTextField114.setText(objTarjetaP.getIdMarcaCarroceria()); //Marca Carroceria
            jTextField115.setText(String.valueOf(objTarjetaP.getEjes())); //Nro Ejes
            jTextField51.setText(String.valueOf(objTarjetaP.getnRuedas())); //Nro Ruedas
            jTextField55.setText(objTarjetaP.getnTarjeta());
            jTextField52.setText(String.valueOf(objTarjetaP.getPasajeros()));
            jTextField124.setText(String.valueOf(objTarjetaP.getAsientos()));
            jTextField123.setText(String.valueOf(objTarjetaP.getLongitud()));
            jTextField53.setText(String.valueOf(objTarjetaP.getAncho()));
            jTextField54.setText(String.valueOf(objTarjetaP.getAltura()));
            jTextField122.setText(String.valueOf(objTarjetaP.getColores()));
            jTextField121.setText(String.valueOf(objTarjetaP.getPesoSeco()));
            jTextField119.setText(String.valueOf(objTarjetaP.getPesoBruto()));
            jTextField120.setText(String.valueOf(objTarjetaP.getCargaUtil()));

            jComboBox20.setSelectedIndex(Integer.parseInt(objTarjetaP.getTipoServicio())); //Tipo de Servicio
            jComboBox14.setSelectedItem(objTarjetaP.getIdCategoria()); //Categoría
            jTextField56.setText(objTarjetaP.getIdMarca()); //Marca
            jTextField57.setText(objTarjetaP.getIdModelo()); //Modelo
            //jComboBox10.setSelectedIndex(Integer.parseInt(objTarjetaP.getIdModelo())); //Modelo
            jComboBox11.setSelectedItem(String.valueOf(objTarjetaP.getFabricacion()));
            jComboBox13.setSelectedItem(objTarjetaP.getIdCombustible());//Combustible
            jComboBox12.setSelectedItem(objTarjetaP.getIdCarroceria()); //Carrocería
            jComboBox3.setSelectedIndex(objTarjetaP.getAmbito());

        }

        ObtenerIds();

        if (jComboBox13.getSelectedIndex() == 0 || jComboBox13.getSelectedIndex() == 4) {
            jTextField188.enable(false);
            jTextField189.enable(false);
            jTextField190.enable(false);
            jTextField191.enable(false);
            jTextField192.enable(false);
            jTextField193.enable(false);
        } else {
            jTextField187.enable(false);
        }


    }//GEN-LAST:event_formAncestorAdded

    //ADJUNTAR IMÁGENES
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                fisfoto1 = new FileInputStream(se.getSelectedFile());

                File fileDB = se.getSelectedFile();
                String PATH = fileDB.getAbsolutePath();
                jTextField26.setText(PATH);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(RegistrarCertificadoSinSuspension.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.longitudBytes1 = (int) se.getSelectedFile().length();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                fisfoto2 = new FileInputStream(se.getSelectedFile());

                File fileDB = se.getSelectedFile();
                String PATH = fileDB.getAbsolutePath();
                jTextField27.setText(PATH);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(RegistrarCertificadoSinSuspension.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.longitudBytes2 = (int) se.getSelectedFile().length();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                fisfoto3 = new FileInputStream(se.getSelectedFile());
                File fileDB = se.getSelectedFile();
                String PATH = fileDB.getAbsolutePath();
                jTextField29.setText(PATH);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RegistrarCertificadoSinSuspension.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.longitudBytes3 = (int) se.getSelectedFile().length();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField52ActionPerformed

    private void jTextField75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField75ActionPerformed

    private void jTextField40KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField40KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField40KeyTyped

    private void jTextField78KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField78KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k != 65 && k != 68) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Sólo son válidas las letras A y D mayúsculas!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField78KeyTyped

    private void jTextField76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField76ActionPerformed

    private void jComboBox33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox33ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox16;
    private javax.swing.JComboBox jComboBox17;
    private javax.swing.JComboBox jComboBox18;
    private javax.swing.JComboBox jComboBox19;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox20;
    private javax.swing.JComboBox jComboBox21;
    private javax.swing.JComboBox jComboBox22;
    private javax.swing.JComboBox jComboBox23;
    private javax.swing.JComboBox jComboBox24;
    private javax.swing.JComboBox jComboBox25;
    private javax.swing.JComboBox jComboBox26;
    private javax.swing.JComboBox jComboBox27;
    private javax.swing.JComboBox jComboBox28;
    private javax.swing.JComboBox jComboBox29;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox30;
    private javax.swing.JComboBox jComboBox31;
    private javax.swing.JComboBox jComboBox32;
    private javax.swing.JComboBox jComboBox33;
    private javax.swing.JComboBox jComboBox34;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField100;
    private javax.swing.JTextField jTextField101;
    private javax.swing.JTextField jTextField102;
    private javax.swing.JTextField jTextField103;
    private javax.swing.JTextField jTextField104;
    private javax.swing.JTextField jTextField105;
    private javax.swing.JTextField jTextField106;
    private javax.swing.JTextField jTextField107;
    private javax.swing.JTextField jTextField108;
    private javax.swing.JTextField jTextField109;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField110;
    private javax.swing.JTextField jTextField111;
    private javax.swing.JTextField jTextField112;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JTextField jTextField114;
    private javax.swing.JTextField jTextField115;
    private javax.swing.JTextField jTextField116;
    private javax.swing.JTextField jTextField117;
    private javax.swing.JTextField jTextField119;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField120;
    private javax.swing.JTextField jTextField121;
    private javax.swing.JTextField jTextField122;
    private javax.swing.JTextField jTextField123;
    private javax.swing.JTextField jTextField124;
    private javax.swing.JTextField jTextField125;
    private javax.swing.JTextField jTextField126;
    private javax.swing.JTextField jTextField127;
    private javax.swing.JTextField jTextField128;
    private javax.swing.JTextField jTextField129;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField130;
    private javax.swing.JTextField jTextField131;
    private javax.swing.JTextField jTextField132;
    private javax.swing.JTextField jTextField133;
    private javax.swing.JTextField jTextField134;
    private javax.swing.JTextField jTextField135;
    private javax.swing.JTextField jTextField136;
    private javax.swing.JTextField jTextField137;
    private javax.swing.JTextField jTextField138;
    private javax.swing.JTextField jTextField139;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField140;
    private javax.swing.JTextField jTextField141;
    private javax.swing.JTextField jTextField142;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField157;
    private javax.swing.JTextField jTextField159;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField160;
    private javax.swing.JTextField jTextField161;
    private javax.swing.JTextField jTextField162;
    private javax.swing.JTextField jTextField163;
    private javax.swing.JTextField jTextField165;
    private javax.swing.JTextField jTextField166;
    private javax.swing.JTextField jTextField168;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField171;
    private javax.swing.JTextField jTextField172;
    private javax.swing.JTextField jTextField173;
    private javax.swing.JTextField jTextField174;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField185;
    private javax.swing.JTextField jTextField186;
    private javax.swing.JTextField jTextField187;
    private javax.swing.JTextField jTextField188;
    private javax.swing.JTextField jTextField189;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField190;
    private javax.swing.JTextField jTextField191;
    private javax.swing.JTextField jTextField192;
    private javax.swing.JTextField jTextField193;
    private javax.swing.JTextField jTextField194;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField78;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField81;
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField83;
    private javax.swing.JTextField jTextField84;
    private javax.swing.JTextField jTextField85;
    private javax.swing.JTextField jTextField86;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField89;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextField91;
    private javax.swing.JTextField jTextField92;
    private javax.swing.JTextField jTextField93;
    private javax.swing.JTextField jTextField94;
    private javax.swing.JTextField jTextField95;
    private javax.swing.JTextField jTextField96;
    private javax.swing.JTextField jTextField97;
    private javax.swing.JTextField jTextField98;
    private javax.swing.JTextField jTextField99;
    // End of variables declaration//GEN-END:variables
}
