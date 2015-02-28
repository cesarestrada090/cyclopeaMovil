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

//import ENTIDADES.Usuario;
//import NEGOCIO.UsuarioBL;
import ENTIDADES.*;
import NEGOCIO.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 *
 * @author pc
 */
public class RegistrarCertificado extends javax.swing.JInternalFrame {

    List arrayObservaciones = new ArrayList<String>();
    boolean eficienciaServicio;
    boolean eficienciaEstacionamiento;
    boolean suspensionIzqDel = true;
    boolean suspensionDerDel = true;
    boolean suspensionDesvDel = true;
    boolean suspensionIzqPost = true;
    boolean suspensionDerPost = true;
    boolean suspensionDesvPost = true;
    boolean sonometroResult = true;

    /**
     * Creates new form RegistrarUsuario
     */
    public RegistrarCertificado() {
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

        jTextField177.getDocument().addDocumentListener(new SuspensionDelanteraIzq());
        jTextField178.getDocument().addDocumentListener(new SuspensionDelanteraDer());
        jTextField179.getDocument().addDocumentListener(new SuspensionDelanteraDesv());
        
        jTextField181.getDocument().addDocumentListener(new SuspensionPosteriorIzq());
        jTextField182.getDocument().addDocumentListener(new SuspensionPosteriorDer());
        jTextField183.getDocument().addDocumentListener(new SuspensionPosteriorDesv());
        
        jTextField194.getDocument().addDocumentListener(new SonometroResultado());
        CertificadoBL b = new CertificadoBL();
        int size;
        List listaModelos = b.obtenerListaModelo();
        for (int i = 0; i < listaModelos.size(); i++) {
            jComboBox10.addItem((String) listaModelos.get(i));
        }

        List listaCombustibles = b.obtenerListaCombustible();
        size = listaCombustibles.size();
        for (int i = 0; i < size; i++) {
            jComboBox13.addItem((String) listaCombustibles.get(i));
        }

        List listaCarrocerias = b.obtenerListaCarroceria();
        size = listaCarrocerias.size();
        for (int i = 0; i < size; i++) {
            jComboBox12.addItem((String) listaCarrocerias.get(i));
        }

        List listaMarcas = b.obtenerListaMarca();
        size = listaMarcas.size();
        for (int i = 0; i < size; i++) {
            jComboBox9.addItem((String) listaMarcas.get(i));
        }

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

    public void limpiarUsuarios() {
        jTextField4.setText("");
//        jPasswordField1.setText("");

    }

    public void habilitarUsuarios(boolean b) {
        jTextField4.setEnabled(b);
//        jPasswordField1.setEnabled(b);
//        jButton2.setEnabled(true);
//        jButton1.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jComboBox9 = new javax.swing.JComboBox();
        jComboBox10 = new javax.swing.JComboBox();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
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
        jTextField164 = new javax.swing.JTextField();
        jTextField165 = new javax.swing.JTextField();
        jTextField166 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField167 = new javax.swing.JTextField();
        jTextField168 = new javax.swing.JTextField();
        jTextField169 = new javax.swing.JTextField();
        jTextField170 = new javax.swing.JTextField();
        jTextField171 = new javax.swing.JTextField();
        jTextField172 = new javax.swing.JTextField();
        jTextField173 = new javax.swing.JTextField();
        jTextField174 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jTextField177 = new javax.swing.JTextField();
        jTextField178 = new javax.swing.JTextField();
        jTextField179 = new javax.swing.JTextField();
        jTextField180 = new javax.swing.JTextField();
        jTextField181 = new javax.swing.JTextField();
        jTextField182 = new javax.swing.JTextField();
        jTextField183 = new javax.swing.JTextField();
        jTextField184 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jComboBox7 = new javax.swing.JComboBox();
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
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField157 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setTitle("CYCLOPEA | CERTIFICADO DE INSPECCIÓN TÉCNICA VEHICULAR");
        setAutoscrolls(true);
        setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/logo.JPG"))); // NOI18N

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel88.setText("3. Categoría:");

        jLabel89.setText("4. Marca:");

        jLabel90.setText("5. Modelo:");

        jLabel91.setText("6. Año de Fabricación:");

        jLabel92.setText("2. Placa:");

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

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setText("Tipo de Inspección: ");
        jLabel94.setToolTipText("");

        jLabel95.setText("INSPECCIÓN TÉCNICA ORDINARIA");

        jLabel96.setText("7. Kilometraje:");

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

        jTextField113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField113ActionPerformed(evt);
            }
        });

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

        jTextField119.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField120.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField121.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField123.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

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

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983" }));

        jLabel146.setText("01 CYCLOPEA MÓVIL");

        jLabel147.setText("MIXTA");

        jLabel148.setText("Fecha de Inspección");

        jTextField52.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField52KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField52KeyTyped(evt);
            }
        });

        jTextField53.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jTextField54.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField119KeyTyped(evt);
            }
        });

        jLabel149.setText("Nro de Tarjeta de Propiedad");

        jTextField55.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField55KeyTyped(evt);
            }
        });

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
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
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField84)
                                                .addComponent(jComboBox9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jComboBox10, 0, 139, Short.MAX_VALUE)
                                                .addComponent(jComboBox11, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField83, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                                .addComponent(jComboBox14, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jTextField82, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel100)
                                            .addComponent(jLabel101)
                                            .addComponent(jLabel99)
                                            .addComponent(jLabel98)
                                            .addComponent(jLabel97)
                                            .addComponent(jLabel102))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField112, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField113, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jTextField114, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(56, 56, 56)))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jTextField115, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(56, 56, 56)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel108)
                                            .addComponent(jLabel107)
                                            .addComponent(jLabel106)
                                            .addComponent(jLabel105)
                                            .addComponent(jLabel104)
                                            .addComponent(jLabel103)
                                            .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel55))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel148)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 388, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addContainerGap(244, Short.MAX_VALUE))
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
                        .addComponent(jLabel95)
                        .addComponent(jLabel148))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField121, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel90)
                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        jTextField38.setText("                     ");
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
        jTextField40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField40ActionPerformed(evt);
            }
        });
        jTextField40.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField40FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField40FocusLost(evt);
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
        });

        jTextField41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField41.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField42.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField43.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField44.setToolTipText("");
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

        jTextField61.setName("d10"); // NOI18N
        jTextField61.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField62.setName("d9"); // NOI18N
        jTextField62.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField63.setName("d8"); // NOI18N
        jTextField63.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField64.setName("d7"); // NOI18N
        jTextField64.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField65.setName("d6"); // NOI18N
        jTextField65.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField66.setName("izq6"); // NOI18N
        jTextField66.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField67.setName("izq7"); // NOI18N
        jTextField67.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField68.setName("izq8"); // NOI18N
        jTextField68.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField69.setText("                     ");
        jTextField69.setName("izq9"); // NOI18N
        jTextField69.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField70.setName("izq10"); // NOI18N
        jTextField70.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField71.setToolTipText("");
        jTextField71.setName("d20"); // NOI18N
        jTextField71.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField72.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField73.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField74.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField75.setText("                   ");
        jTextField75.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField76.setEnabled(false);

        jTextField77.setEnabled(false);

        jTextField78.setEnabled(false);

        jTextField79.setEnabled(false);

        jTextField80.setEnabled(false);

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

        jTextField92.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField93.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField94.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField95.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

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
        jTextField97.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField98.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField99.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField101.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Desequilibrio (%)");
        jLabel78.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField102.setText("                   ");
        jTextField102.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField103.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField104.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField105.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jTextField106.setToolTipText("");
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

        jTextField116.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField61KeyTyped(evt);
            }
        });

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Eficiencia (%)");
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
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(jTextField69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField74)
                                    .addComponent(jTextField75, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                    .addComponent(jTextField73, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField72, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField71))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField76, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField78, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField79, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField101, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                                .addComponent(jTextField93, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jTextField96, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField92, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                                        .addComponent(jTextField95))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(1, 1, 1)))))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField102, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField103, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField104, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField107, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField108, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField109, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField111, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField110, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField116, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel80))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField100, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                            .addComponent(jTextField99))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField106, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField105, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, 0, Short.MAX_VALUE)))))
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
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel41)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51))
                        .addGap(28, 28, 28)
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
                            .addComponent(jLabel53))))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(jLabel67)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
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
                            .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(jLabel60)
                            .addComponent(jLabel75))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78)
                            .addComponent(jLabel79)
                            .addComponent(jLabel80)
                            .addComponent(jLabel81))
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
                                .addGap(0, 4, Short.MAX_VALUE)))))
                .addContainerGap(40, Short.MAX_VALUE))
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

        jTabbedPane1.addTab("PRUEBA DE FRENOS", jPanel1);

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

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Resultado");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField10.setEnabled(false);

        jTextField11.setEnabled(false);

        jTextField13.setEnabled(false);

        jTextField14.setEnabled(false);

        jTextField15.setEnabled(false);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Medida Obtenida (mm)");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Resultado");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField21.setEnabled(false);

        jTextField22.setEnabled(false);

        jTextField23.setEnabled(false);

        jTextField24.setEnabled(false);

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

        jTextField159.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField160.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField161.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField162.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField163.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField165.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

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

        jTextField168.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jTextField171.setEnabled(false);

        jTextField172.setEnabled(false);

        jTextField173.setEnabled(false);

        jTextField174.setEnabled(false);

        jLabel34.setBackground(new java.awt.Color(0, 153, 51));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("PRUEBA DE ALINEAMIENTO");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("PRUEBA DE LUCES");
        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField6)
                                        .addComponent(jTextField7)
                                        .addComponent(jTextField8)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField10)
                            .addComponent(jTextField11)
                            .addComponent(jTextField13)
                            .addComponent(jTextField14)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(jTextField21))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField18)
                                            .addComponent(jTextField19)
                                            .addComponent(jTextField20)
                                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(49, 49, 49)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField23, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField24)
                                            .addComponent(jTextField25, javax.swing.GroupLayout.Alignment.TRAILING))))))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField169)
                                    .addComponent(jTextField170, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                        .addComponent(jLabel32))
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
                                            .addComponent(jTextField164)
                                            .addComponent(jTextField167, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField171)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField172)
                            .addComponent(jTextField173)
                            .addComponent(jTextField174, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(702, 702, 702))
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
                                .addComponent(jTextField167, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField171, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(jTextField160, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField164, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField168, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField172, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField173, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jTextField161, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField165, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField169, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jTextField162, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField166, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField170, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField174, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PRUEBA DE ALINEAMIENTO - PRUEBA DE LUCES", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("SUSPENSIÓN");
        jLabel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Delantera %");
        jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Posterior %");
        jLabel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel39.setText("Izq");

        jLabel40.setText("Der");

        jLabel122.setText("Desv");

        jLabel123.setText("Resultado");

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel124.setText("Resultado Final");
        jLabel124.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel125.setText("Izq");

        jLabel126.setText("Der");

        jLabel127.setText("Desv.");

        jLabel128.setText("Resultado");

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("Resultado Final");
        jLabel129.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField177.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField177ActionPerformed(evt);
            }
        });
        jTextField177.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jTextField178.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jTextField179.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jTextField180.setEnabled(false);
        jTextField180.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jTextField181.setToolTipText("");
        jTextField181.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jTextField182.setToolTipText("");
        jTextField182.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jTextField183.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jTextField184.setEnabled(false);
        jTextField184.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField184KeyTyped(evt);
            }
        });

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox8.setEnabled(false);

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox7.setEnabled(false);
        jDesktopPane1.add(jComboBox7);
        jComboBox7.setBounds(0, 0, 200, 20);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel124, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel123)
                                        .addComponent(jLabel122)
                                        .addComponent(jLabel40)
                                        .addComponent(jLabel39))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField179)
                                        .addComponent(jTextField180, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                        .addComponent(jTextField178)))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField177, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel125)
                                    .addComponent(jLabel126)
                                    .addComponent(jLabel127)
                                    .addComponent(jLabel128))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField184, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField183, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField182, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField181, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel129, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(577, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel125)
                    .addComponent(jTextField177, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField181, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel126)
                    .addComponent(jTextField178, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField182, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel122)
                    .addComponent(jLabel127)
                    .addComponent(jTextField179, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField183, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel123)
                    .addComponent(jLabel128)
                    .addComponent(jTextField180, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField184, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel124)
                    .addComponent(jLabel129))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox8)
                    .addComponent(jDesktopPane1))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SUSPENSIÓN", jPanel7);

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

        jTextField185.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField186.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

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

        jTextField188.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField189.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

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

        jTextField191.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

        jTextField192.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField187KeyTyped(evt);
            }
        });

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
                .addContainerGap(329, Short.MAX_VALUE))
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
                .addContainerGap(330, Short.MAX_VALUE))
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
                .addContainerGap(554, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel143)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("EMISIONES SONORAS", jPanel8);

        jTabbedPane3.addTab("III. RESULTADOS OBTENIDOS", jTabbedPane1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Resultado de la Inspección");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Vigencia del Certificado");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Fecha de la próxima inspección");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField157.setText("12");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "APROBADO", "DESAPROBADO" }));
        jComboBox3.setEnabled(false);

        jDateChooser2.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(88, 88, 88)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jTextField157))
                .addGap(88, 88, 88)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(600, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField157, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(406, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("V. RESULTADO DE LA INSPECCIÓN TÉCNICA VEHICULAR", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1340, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("IV. OBSERVACIONES DETECTADAS", jPanel4);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("INSPECCIÓN TÉCNICA VEHICULAR");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/Iconos/saveas.png"))); // NOI18N
        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(538, 538, 538))
            .addGroup(layout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jLabel4)
                .addContainerGap(601, Short.MAX_VALUE))
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("jfrmRegistrarUsuario");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // BOTON GRABAR
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        resultado = validarVacios();
        if (resultado) {

            // CARACTERÍSTICAS DEL VEHÍCULO 
            TarjetaPropiedad objTarjetaP = new TarjetaPropiedad();
            objTarjetaP.setIdTarjeta(jTextField55.getText());
            objTarjetaP.setAltura(Double.parseDouble(jTextField54.getText()));
            objTarjetaP.setAncho(Double.parseDouble(jTextField53.getText()));
            objTarjetaP.setLongitud(Double.parseDouble(jTextField123.getText()));
            objTarjetaP.setAsientos(Integer.parseInt(jTextField124.getText()));
            objTarjetaP.setCargaUtil(Double.parseDouble(jTextField120.getText()));
            objTarjetaP.setIdCategoria(String.valueOf(jComboBox14.getSelectedItem()));
            objTarjetaP.setColores(jTextField122.getText());
            objTarjetaP.setPlaca(jTextField83.getText());
            objTarjetaP.setEjes(Integer.parseInt(jTextField115.getText()));
            objTarjetaP.setnSerie(jTextField112.getText());
            objTarjetaP.setFabricacion(Integer.parseInt(String.valueOf(jComboBox11.getSelectedItem())));
            objTarjetaP.setnMotor(jTextField113.getText());
            objTarjetaP.setIdCarroceria(String.valueOf(jComboBox12.getSelectedItem()));
            objTarjetaP.setPesoSeco(Double.parseDouble(jTextField121.getText()));
            objTarjetaP.setPesoBruto(Double.parseDouble(jTextField119.getText()));
            objTarjetaP.setIdCombustible(String.valueOf(jComboBox13.getSelectedItem()));
            objTarjetaP.setIdMarca((String.valueOf(jComboBox9.getSelectedItem())));
            objTarjetaP.setIdModelo(String.valueOf(jComboBox10.getSelectedIndex()));
            objTarjetaP.setKilometraje(Double.parseDouble(jTextField84.getText()));
            objTarjetaP.setNombrePropietario(jTextField82.getText());
            objTarjetaP.setIdMarcaCarroceria(jComboBox15.getSelectedIndex());
            objTarjetaP.setnRuedas(Integer.parseInt(jTextField51.getText()));
            objTarjetaP.setnTarjeta(jTextField55.getText());

            int idTarjeta;
            TarjetaPropiedadBL objTPBL = new TarjetaPropiedadBL();
            objTPBL.registrarTarjetaPropiedad(objTarjetaP);
            idTarjeta = objTPBL.obtenerTarjetaPropiedad();

            //CERTIFICADO
            Certificado objCertificado = new Certificado();
            objCertificado.setNumDocEvaluar(jTextField55.getText());
            objCertificado.setTipoDocEvaluar("1");
            objCertificado.setNumDocTransp(jTextField28.getText());
            objCertificado.setTipoDocTransp(String.valueOf(jComboBox15.getSelectedIndex() + 1));
            objCertificado.setCodLocal("Desconocido");
            objCertificado.setFecInspeccion(jDateChooser1.getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(jDateChooser1.getDate()); // Configuramos la fecha que se recibe
            calendar.add(Calendar.MONTH, Integer.parseInt(jTextField157.getText()));  // numero de días a añadir, o restar en caso de días<0
            objCertificado.setFecVencimiento(calendar.getTime()); // Fecha de la próxima inspección
            objCertificado.setResultado(jComboBox3.getSelectedIndex());
            objCertificado.setVigencia(jTextField157.getText());
            objCertificado.setIdTarjeta(idTarjeta);

            int idCertificado;
            CertificadoBL objCertBL = new CertificadoBL();
            objCertBL.registrarCertificado(objCertificado);
            idCertificado = objCertBL.obtenerIdCertificado();

            //RESULTADOS - PRUEBA DE FRENOS
            Frenometro objFrenometro1 = new Frenometro();

            objFrenometro1.setnEjes(1);
            objFrenometro1.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
            objFrenometro1.setEficienciaEmergencia(Double.parseDouble(jTextField116.getText()));
            objFrenometro1.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
            objFrenometro1.setFuerzaEstDer(Double.parseDouble(jTextField65.getText()));
            objFrenometro1.setFuerzaEstIzq(Double.parseDouble(jTextField66.getText()));
            objFrenometro1.setFuerzaServicioDer(Double.parseDouble(jTextField30.getText()));
            objFrenometro1.setFuerzaServicioIzq(Double.parseDouble(jTextField31.getText()));
            objFrenometro1.setFuerzaEmerDer(Double.parseDouble(jTextField94.getText()));
            objFrenometro1.setFuerzaEmerIzq(Double.parseDouble(jTextField92.getText()));
            objFrenometro1.setPesoServicio(Double.parseDouble(jTextField2.getText()));
            objFrenometro1.setResultadoEjeServicio(jTextField45.getText());
            objFrenometro1.setResultadoEjeEmergencia(jTextField76.getText());
            objFrenometro1.setResultadoEjeEstacionamiento(jTextField107.getText());
            objFrenometro1.setResultadoGlobalEmergencia(String.valueOf(jComboBox6.getSelectedItem()));
            objFrenometro1.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
            objFrenometro1.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
            objFrenometro1.setDesequilibrioServicio(Double.parseDouble(jTextField40.getText()));
            objFrenometro1.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField75.getText()));
            objFrenometro1.setDesequilibrioEmergencia(Double.parseDouble(jTextField102.getText()));
            objFrenometro1.setIdCerticado(idCertificado);

            FrenometroBL objFrenometroBL = new FrenometroBL();
            objFrenometroBL.registrarFrenometro(objFrenometro1);

            try {
                Frenometro objFrenometro2 = new Frenometro();
                objFrenometro2.setIdCerticado(idCertificado);
                objFrenometro2.setnEjes(2);
                objFrenometro2.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                objFrenometro2.setEficienciaEmergencia(Double.parseDouble(jTextField116.getText()));
                objFrenometro2.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                objFrenometro2.setFuerzaEstDer(Double.parseDouble(jTextField64.getText()));
                objFrenometro2.setFuerzaEstIzq(Double.parseDouble(jTextField67.getText()));
                objFrenometro2.setFuerzaServicioDer(Double.parseDouble(jTextField32.getText()));
                objFrenometro2.setFuerzaServicioIzq(Double.parseDouble(jTextField36.getText()));
                objFrenometro2.setFuerzaEmerDer(Double.parseDouble(jTextField93.getText()));
                objFrenometro2.setFuerzaEmerIzq(Double.parseDouble(jTextField95.getText()));
                objFrenometro2.setPesoServicio(Double.parseDouble(jTextField3.getText()));
                objFrenometro2.setResultadoEjeServicio(jTextField46.getText());
                objFrenometro2.setResultadoEjeEmergencia(jTextField77.getText());
                objFrenometro2.setResultadoEjeEstacionamiento(jTextField108.getText());
                objFrenometro2.setResultadoGlobalEmergencia(String.valueOf(jComboBox6.getSelectedItem()));
                objFrenometro2.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro2.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                objFrenometro2.setDesequilibrioServicio(Double.parseDouble(jTextField41.getText()));
                objFrenometro2.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField74.getText()));
                objFrenometro2.setDesequilibrioEmergencia(Double.parseDouble(jTextField103.getText()));

                objFrenometroBL.registrarFrenometro(objFrenometro2);
            } catch (NumberFormatException e) {
            }
            Frenometro objFrenometro3 = new Frenometro();

            try {
                objFrenometro3.setIdCerticado(idCertificado);
                objFrenometro3.setnEjes(3);
                objFrenometro3.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                objFrenometro3.setEficienciaEmergencia(Double.parseDouble(jTextField116.getText()));
                objFrenometro3.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                objFrenometro3.setFuerzaEstDer(Double.parseDouble(jTextField63.getText()));
                objFrenometro3.setFuerzaEstIzq(Double.parseDouble(jTextField68.getText()));
                objFrenometro3.setFuerzaServicioDer(Double.parseDouble(jTextField33.getText()));
                objFrenometro3.setFuerzaServicioIzq(Double.parseDouble(jTextField37.getText()));
                objFrenometro3.setFuerzaEmerDer(Double.parseDouble(jTextField101.getText()));
                objFrenometro3.setFuerzaEmerIzq(Double.parseDouble(jTextField96.getText()));
                objFrenometro3.setPesoServicio(Double.parseDouble(jTextField4.getText()));
                objFrenometro3.setResultadoEjeServicio(jTextField47.getText());
                objFrenometro3.setResultadoEjeEmergencia(jTextField78.getText());
                objFrenometro3.setResultadoEjeEstacionamiento(jTextField109.getText());
                objFrenometro3.setResultadoGlobalEmergencia(String.valueOf(jComboBox6.getSelectedItem()));
                objFrenometro3.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro3.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                objFrenometro3.setDesequilibrioServicio(Double.parseDouble(jTextField42.getText()));
                objFrenometro3.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField73.getText()));
                objFrenometro3.setDesequilibrioEmergencia(Double.parseDouble(jTextField104.getText()));

                objFrenometroBL.registrarFrenometro(objFrenometro3);
            } catch (NumberFormatException e) {
            }
            try {
                Frenometro objFrenometro4 = new Frenometro();
                objFrenometro4.setIdCerticado(idCertificado);
                objFrenometro4.setnEjes(4);
                objFrenometro4.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                objFrenometro4.setEficienciaEmergencia(Double.parseDouble(jTextField116.getText()));
                objFrenometro4.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                objFrenometro4.setFuerzaEstDer(Double.parseDouble(jTextField62.getText()));
                objFrenometro4.setFuerzaEstIzq(Double.parseDouble(jTextField69.getText()));
                objFrenometro4.setFuerzaServicioDer(Double.parseDouble(jTextField34.getText()));
                objFrenometro4.setFuerzaServicioIzq(Double.parseDouble(jTextField38.getText()));
                objFrenometro4.setFuerzaEmerDer(Double.parseDouble(jTextField100.getText()));
                objFrenometro4.setFuerzaEmerIzq(Double.parseDouble(jTextField97.getText()));
                objFrenometro4.setPesoServicio(Double.parseDouble(jTextField1.getText()));
                objFrenometro4.setResultadoEjeServicio(jTextField48.getText());
                objFrenometro4.setResultadoEjeEmergencia(jTextField79.getText());
                objFrenometro4.setResultadoEjeEstacionamiento(jTextField110.getText());
                objFrenometro4.setResultadoGlobalEmergencia(String.valueOf(jComboBox6.getSelectedItem()));
                objFrenometro4.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro4.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                objFrenometro4.setDesequilibrioServicio(Double.parseDouble(jTextField43.getText()));
                objFrenometro4.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField72.getText()));
                objFrenometro4.setDesequilibrioEmergencia(Double.parseDouble(jTextField105.getText()));

                objFrenometroBL.registrarFrenometro(objFrenometro4);
            } catch (NumberFormatException e) {
            }

            try {
                Frenometro objFrenometro5 = new Frenometro();
                objFrenometro5.setnEjes(5);
                objFrenometro5.setIdCerticado(idCertificado);
                objFrenometro5.setEficienciaEst(Double.parseDouble(jTextField85.getText()));
                objFrenometro5.setEficienciaEmergencia(Double.parseDouble(jTextField116.getText()));
                objFrenometro5.setEficienciaServicio(Double.parseDouble(jTextField50.getText()));
                objFrenometro5.setFuerzaEstDer(Double.parseDouble(jTextField61.getText()));
                objFrenometro5.setFuerzaEstIzq(Double.parseDouble(jTextField70.getText()));
                objFrenometro5.setFuerzaServicioDer(Double.parseDouble(jTextField35.getText()));
                objFrenometro5.setFuerzaServicioIzq(Double.parseDouble(jTextField39.getText()));
                objFrenometro5.setFuerzaEmerDer(Double.parseDouble(jTextField99.getText()));
                objFrenometro5.setFuerzaEmerIzq(Double.parseDouble(jTextField98.getText()));
                objFrenometro5.setPesoServicio(Double.parseDouble(jTextField12.getText()));
                objFrenometro5.setResultadoEjeServicio(jTextField49.getText());
                objFrenometro5.setResultadoEjeEmergencia(jTextField80.getText());
                objFrenometro5.setResultadoEjeEstacionamiento(jTextField111.getText());
                objFrenometro5.setResultadoGlobalEmergencia(String.valueOf(jComboBox6.getSelectedItem()));
                objFrenometro5.setResultadoGlobalEstacionamiento(String.valueOf(jComboBox5.getSelectedItem()));
                objFrenometro5.setResultadoGlobalServicio(String.valueOf(jComboBox4.getSelectedItem()));
                objFrenometro5.setDesequilibrioServicio(Double.parseDouble(jTextField44.getText()));
                objFrenometro5.setDesequilibrioEstacionamiento(Double.parseDouble(jTextField71.getText()));
                objFrenometro5.setDesequilibrioEmergencia(Double.parseDouble(jTextField106.getText()));

                objFrenometroBL.registrarFrenometro(objFrenometro5);
            } catch (NumberFormatException e) {
            }

            // DATOS DE LOS EQUIPOS
            try {
                EquipoCertificado objEquipos = new EquipoCertificado();
                objEquipos.setIdCertificado(idCertificado);
                objEquipos.setNumAlineador(jComboBox15.getSelectedIndex());
                objEquipos.setNumAnalizador(jComboBox16.getSelectedIndex());
                objEquipos.setNumBancoSuspension(jComboBox17.getSelectedIndex());
                objEquipos.setNumFrenometro(jComboBox18.getSelectedIndex());
                objEquipos.setNumRegloscopio(jComboBox19.getSelectedIndex());

                EquipoCertificadoBL objEquipoBL = new EquipoCertificadoBL();
                objEquipoBL.registrarEquipoCertificado(objEquipos);
            } catch (NumberFormatException e) {
            }
            // RESULTADOS - PRUEBA DE ALINEAMIENTO
            try {
                Alineador objAlineador1 = new Alineador();
                objAlineador1.setIdCertificado(idCertificado);
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
                objAlineador2.setIdCertificado(idCertificado);
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
                objAlineador3.setIdCertificado(idCertificado);
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
                objAlineador4.setIdCertificado(idCertificado);
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
                objAlineador5.setIdCertificado(idCertificado);
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
                objLuzBaja.setIdCertificado(idCertificado);
                objLuzBaja.setTipoLuz(1);
                objLuzBaja.setMedidaDerLuz(Double.parseDouble(jTextField159.getText()));
                objLuzBaja.setMedidaIzqLuz(Double.parseDouble(jTextField163.getText()));
                objLuzBaja.setAlineamientoLuz(jTextField167.getText());
                objLuzBaja.setResultadoLuz(jTextField171.getText());

                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzBaja);
            } catch (NumberFormatException e) {
            }

            try {
                Luxometro objLuzAlta = new Luxometro();
                objLuzAlta.setTipoLuz(2);
                objLuzAlta.setIdCertificado(idCertificado);
                objLuzAlta.setMedidaDerLuz(Double.parseDouble(jTextField160.getText()));
                objLuzAlta.setMedidaIzqLuz(Double.parseDouble(jTextField168.getText()));
                objLuzAlta.setAlineamientoLuz(jTextField164.getText());
                objLuzAlta.setResultadoLuz(jTextField172.getText());
                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzAlta);
            } catch (NumberFormatException e) {
            }

            try {
                Luxometro objLuzAltaAd = new Luxometro();
                objLuzAltaAd.setTipoLuz(3);
                objLuzAltaAd.setIdCertificado(idCertificado);
                objLuzAltaAd.setMedidaDerLuz(Double.parseDouble(jTextField161.getText()));
                objLuzAltaAd.setMedidaIzqLuz(Double.parseDouble(jTextField165.getText()));
                objLuzAltaAd.setAlineamientoLuz(jTextField169.getText());
                objLuzAltaAd.setResultadoLuz(jTextField173.getText());
                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzAltaAd);
            } catch (NumberFormatException e) {
            }

            try {
                Luxometro objLuzNeblineras = new Luxometro();
                objLuzNeblineras.setTipoLuz(4);
                objLuzNeblineras.setIdCertificado(idCertificado);
                objLuzNeblineras.setMedidaDerLuz(Double.parseDouble(jTextField162.getText()));
                objLuzNeblineras.setMedidaIzqLuz(Double.parseDouble(jTextField166.getText()));
                objLuzNeblineras.setAlineamientoLuz(jTextField169.getText());
                objLuzNeblineras.setResultadoLuz(jTextField174.getText());
                LuxometroBL objLuxBL = new LuxometroBL();
                objLuxBL.registrarLuxometro(objLuzNeblineras);
            } catch (NumberFormatException e) {
            }
            //// PRUEBA DE SUSPENSIÓN
            try {
                Suspension objSuspension = new Suspension();
                objSuspension.setIdCertificado(idCertificado);
                objSuspension.setDelanteraIzq(Double.parseDouble(jTextField177.getText()));
                objSuspension.setDelanteraDer(Double.parseDouble(jTextField178.getText()));
                objSuspension.setDelanteraDesv(Double.parseDouble(jTextField179.getText()));
                objSuspension.setDelanteraResult(jTextField180.getText());
                objSuspension.setDelanteraResultFinal(String.valueOf(jComboBox7.getSelectedItem()));
                objSuspension.setPosteriorIzq(Double.parseDouble(jTextField181.getText()));
                objSuspension.setPosteriorDer(Double.parseDouble(jTextField182.getText()));
                objSuspension.setPosteriorDesv(Double.parseDouble(jTextField183.getText()));
                objSuspension.setPosteriorResult(jTextField184.getText());
                objSuspension.setPosteriorResultFinal(String.valueOf(jComboBox8.getSelectedItem()));

                SuspensionBL objSusBL = new SuspensionBL();
                objSusBL.registrarSuspension(objSuspension);
            } catch (NumberFormatException e) {
            }
            ////EMISIÓN DE GASES
            try {
                Gasometro e = new Gasometro();

                double tAceite = Double.parseDouble(jTextField185.getText());
                double Rpm = Double.parseDouble(jTextField186.getText());
                double Opacidad = Double.parseDouble(jTextField187.getText());

                double COralenti = Double.parseDouble(jTextField188.getText());
                double COCO2ralenti = Double.parseDouble(jTextField189.getText());
                double HCralenti = Double.parseDouble(jTextField190.getText());

                double COAcel = Double.parseDouble(jTextField181.getText());
                double COCO2Acel = Double.parseDouble(jTextField181.getText());
                double HCAcel = Double.parseDouble(jTextField181.getText());

                Integer EmiGResultFinal = jComboBox1.getSelectedIndex();
                e.settAceite(tAceite);
                e.setRpm(Rpm);
                e.setOpacidad(Opacidad);
                e.setCoRalent(COralenti);
                e.setCoco2Ralenti(COCO2ralenti);
                e.setHcRalentippm(HCralenti);
                e.setCoAcel(COAcel);
                e.setCoCo2Acel(COCO2Acel);
                e.setHcAcel(HCAcel);
                e.setResultado(EmiGResultFinal.toString());
                GasometroDL g = new GasometroDL();
                g.registrarGasometro(e);

                ////EMISIONES SONORAS
                Sonometro s = new Sonometro();

                double Sonometro = Double.parseDouble(jTextField194.getText());
                Integer EmiSResultado = jComboBox2.getSelectedIndex();

                s.setIdCertificado(idCertificado);
                s.setSonometroValor(Sonometro);
                s.setResultado(EmiSResultado.toString());
                SonometroBL sb = new SonometroBL();
                sb.registrarSonometro(s);
            } catch (NumberFormatException e) {
            }
            ////OBSERVACIONES DETECTADAS

            ///RESULTADO DE LA INSPECCIÓN TÉCNICA VEHICULAR
            //int ResultInsp=jComboBox3.getSelectedIndex();     
            //String Vigencia=jTextField195.getText();
            //String fechaVig=jTextField195.getText();                    
//            JOptionPane.showMessageDialog(null, modelo, "CAMPOS VACÍOS", 0);
//        }else{
            JOptionPane.showMessageDialog(null, "Registro guardado correctamente", "REGISTRO CERTIFICADO", 0);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    public boolean validarVacios() {

        //Tarjeta de Propiedad
        if (jTextField55.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el número de tarjeta", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField82.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del titular", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField83.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese la placa del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField84.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el kilometraje del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField112.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el número de Serie", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField113.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el número de motor", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField55.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese la carrocería del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField115.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el número de ejes", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField51.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el número de ruedas", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField124.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el número de asientos", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField124.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el número de pasajeros", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField123.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el largo del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField53.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el ancho del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField54.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el alto del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField54.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el alto del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField28.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el alto del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }

        if (jTextField122.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el/los color(es) del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField121.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el peso neto del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField119.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese el peso bruto del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        if (jTextField120.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ingrese la carga útil del vehículo", "CAMPOS VACÍOS", 0);
            return resultado;
        }
        
        if (jTextField194.getText().trim().equals("")) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Complete los campos en emisiones sonoras", "CAMPOS VACÍOS", 0);
            return resultado;
        }


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

        /* primer freno Emergencia */
        if (!jTextField94.getText().trim().equals("") && !jTextField92.getText().trim().equals("")
                && !jTextField102.getText().trim().equals("") && !jTextField92.getText().trim().equals("")
                && !jTextField116.getText().trim().equals("")) {
            frenoEmergenciaCompleto = true;
        }

        /* Segundo freno Emergencia */
        if (!jTextField93.getText().trim().equals("") && !jTextField95.getText().trim().equals("")
                && !jTextField103.getText().trim().equals("") && !jTextField95.getText().trim().equals("")
                && !jTextField116.getText().trim().equals("")) {
            frenoEmergenciaCompleto = true;
        }

        /* Tercer freno Emergencia */
        if (!jTextField101.getText().trim().equals("") && !jTextField96.getText().trim().equals("")
                && !jTextField104.getText().trim().equals("") && !jTextField96.getText().trim().equals("")
                && !jTextField116.getText().trim().equals("")) {
            frenoEmergenciaCompleto = true;
        }

        /* Cuarto freno Emergencia */
        if (!jTextField100.getText().trim().equals("") && !jTextField97.getText().trim().equals("")
                && !jTextField105.getText().trim().equals("") && !jTextField97.getText().trim().equals("")
                && !jTextField116.getText().trim().equals("")) {
            frenoEmergenciaCompleto = true;
        }

        /* Quinto freno Emergencia */
        if (!jTextField99.getText().trim().equals("") && !jTextField98.getText().trim().equals("")
                && !jTextField106.getText().trim().equals("") && !jTextField98.getText().trim().equals("")
                && !jTextField116.getText().trim().equals("")) {
            frenoEmergenciaCompleto = true;
        }



        /* validar Suspension */
        if (!jTextField177.getText().trim().equals("") && !jTextField178.getText().trim().equals("")
                && !jTextField179.getText().trim().equals("") && !jTextField181.getText().trim().equals("")
                && !jTextField182.getText().trim().equals("") && !jTextField183.getText().trim().equals("")) {
            suspensionCompleto = true;
        }

        /* validar Emision Gas  */
        if (!jTextField185.getText().trim().equals("") && !jTextField186.getText().trim().equals("")
                && !jTextField187.getText().trim().equals("") && !jTextField188.getText().trim().equals("")
                && !jTextField189.getText().trim().equals("") && !jTextField190.getText().trim().equals("")
                && !jTextField191.getText().trim().equals("") && !jTextField192.getText().trim().equals("")
                && !jTextField193.getText().trim().equals("")) {
            gasometroCompleto = true;
        }

        /* validar Sonometro  */
        if (jTextField194.getText().trim().equals("")) {
            sonometroCompleto = true;
        }




        /* Luces Bajas */
        if (!jTextField159.getText().trim().equals("") && !jTextField163.getText().trim().equals("")
                && !jTextField167.getText().trim().equals("") && !jTextField163.getText().trim().equals("")) {
            luxometroCompleto = true;
        }

        /* Luces Altas */
        if (!jTextField160.getText().trim().equals("") && !jTextField168.getText().trim().equals("")
                && !jTextField164.getText().trim().equals("") && !jTextField168.getText().trim().equals("")) {
            luxometroCompleto = true;
        }

        /* Luces Altas Adicionales */
        if (!jTextField160.getText().trim().equals("") && !jTextField168.getText().trim().equals("")
                && !jTextField164.getText().trim().equals("") && !jTextField168.getText().trim().equals("")) {
            luxometroCompleto = true;
        }

        /* Luces Neblineras */
        if (!jTextField162.getText().trim().equals("") && !jTextField166.getText().trim().equals("")
                && !jTextField170.getText().trim().equals("") && !jTextField166.getText().trim().equals("")) {
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



        if (frenoServicioCompleto
                && frenoEstacionamientoCompleto
                && sonometroCompleto
                && gasometroCompleto
                && suspensionCompleto
                && frenoEmergenciaCompleto
                && alineadorCompleto
                && luxometroCompleto) {
            return true;
        } else {
            if (!frenoServicioCompleto) {
                JOptionPane.showMessageDialog(null, "Complete frenos de Servicio", "CAMPOS VACÍOS", 0);

            }
            if (!frenoEstacionamientoCompleto) {
                JOptionPane.showMessageDialog(null, "Complete frenos de Estacionamiento", "CAMPOS VACÍOS", 0);

            }
            if (!frenoEmergenciaCompleto) {
                JOptionPane.showMessageDialog(null, "Complete frenos de Emergencia", "CAMPOS VACÍOS", 0);

            }

            if (!sonometroCompleto) {
                JOptionPane.showMessageDialog(null, "Complete campos de Emisión Sonora", "CAMPOS VACÍOS", 0);

            }

            if (!suspensionCompleto) {
                JOptionPane.showMessageDialog(null, "Complete campos de Suspensión", "CAMPOS VACÍOS", 0);

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

    private void jTextField184KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField184KeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField184KeyTyped

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
                int valor = Integer.parseInt(x);
                if (valor <= 14) {
                    jTextField45.setText("A");
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField45.setText("A");
                    // LEVE D 1.7 
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField45.setText("D");
                    // GRAVE 1.6
                }
                if (valor >= 31) {
                    jTextField45.setText("D");
                    // MUY GRAVE 1.5
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {

            String x = jTextField40.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);
                if (valor <= 14) {
                    jTextField45.setText("A");
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField45.setText("A");
                    // LEVE D 1.7 
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField45.setText("D");
                    // GRAVE 1.6
                }
                if (valor >= 31) {
                    jTextField45.setText("D");
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
                int valor = Integer.parseInt(x);
                if (valor <= 14) {
                    jTextField46.setText("A");
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField46.setText("A");

                    //LEVE 1.10
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField46.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField46.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField41.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);
                if (valor <= 14) {
                    jTextField46.setText("A");
                }
                if (valor >= 15 && valor <= 20) {
                    jTextField46.setText("A");

                    //LEVE 1.10
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField46.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField46.setText("D");
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
                int valor = Integer.parseInt(x);
                if (valor <= 20) {
                    jTextField47.setText("A");
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField47.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField47.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField42.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);
                if (valor <= 20) {
                    jTextField47.setText("A");
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField47.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField47.setText("D");
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
                int valor = Integer.parseInt(x);
                if (valor <= 20) {
                    jTextField48.setText("A");
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField48.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField48.setText("D");
                    // MUY GRAVE 1.8
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField43.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);
                if (valor <= 20) {
                    jTextField48.setText("A");
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField48.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField48.setText("D");
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
                int valor = Integer.parseInt(x);
                if (valor <= 20) {
                    jTextField49.setText("A");

                }
                if (valor >= 21 && valor <= 30) {
                    jTextField49.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField49.setText("D");
                    // MUY GRAVE 1.8
                }
                if (valor <= 20) {
                    jTextField49.setText("A");
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField44.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);

                if (valor <= 20) {
                    jTextField49.setText("A");
                }
                if (valor >= 21 && valor <= 30) {
                    jTextField49.setText("D");
                    //GRAVE 1.9
                }
                if (valor >= 31) {
                    jTextField49.setText("D");
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
                int valor = Integer.parseInt(x);
                if (valor < 20) {
                    eficienciaServicio = false;
                    //Muy Grave 1.4
                }
                if (valor >= 20 && valor <= 29) {
                    eficienciaServicio = false;
                    //GRAVE 1.3
                }
                if (valor >= 30 && valor <= 50) {
                    eficienciaServicio = true;
                    // LEVE 1.2
                }
                if (valor <= 20) {
                    eficienciaServicio = true;
                    jTextField49.setText("A");
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField50.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);
                if (valor < 20) {
                    eficienciaServicio = false;
                    //Muy Grave 1.4
                }
                if (valor >= 20 && valor <= 29) {
                    eficienciaServicio = false;
                    //GRAVE 1.3
                }
                if (valor >= 30 && valor <= 50) {
                    eficienciaServicio = true;
                    // LEVE 1.2
                }
                if (valor <= 20) {
                    eficienciaServicio = true;
                    jTextField49.setText("A");
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
                int valor = Integer.parseInt(x);
                if (valor >= 15) {
                    eficienciaEstacionamiento = true;
                    //Muy Grave 1.4
                }
                if (valor >= 6 && valor <= 14) {
                    eficienciaEstacionamiento = false;
                    //GRAVE 1.3
                }
                if (valor <= 5) {
                    eficienciaEstacionamiento = false;
                    // LEVE 1.2
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField85.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);
                if (valor >= 15) {
                    eficienciaEstacionamiento = true;
                    //Muy Grave 1.4
                }
                if (valor >= 6 && valor <= 14) {
                    eficienciaEstacionamiento = false;
                    //GRAVE 1.3
                }
                if (valor <= 5) {
                    eficienciaEstacionamiento = false;
                    // LEVE 1.2
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class AlineamientoDesviacionEje1 implements DocumentListener {

        String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField5.getText();
            if (!x.equals("")) {
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                int valor = Integer.parseInt(x);
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
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField16.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField21.setText("D");
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
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField17.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField22.setText("D");
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
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField18.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField23.setText("D");
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
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField19.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField24.setText("D");
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
                }
            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField20.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 1.9 && valor >= 0) {
                    jTextField25.setText("D");
                }
            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class SuspensionDelanteraIzq implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField177.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionIzqDel = false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionIzqDel = false;

                }
                if (valor >= 41) {
                    suspensionIzqDel = true;

                }

                if (suspensionIzqDel && suspensionDerDel && suspensionDesvDel) {
                    jComboBox7.setSelectedIndex(0);
                    jTextField180.setText("A");
                } else {
                    jComboBox7.setSelectedIndex(1);
                    jTextField180.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField177.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionIzqDel = false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionIzqDel = false;

                }
                if (valor >= 41) {
                    suspensionIzqDel = true;

                }

                if (suspensionIzqDel && suspensionDerDel && suspensionDesvDel) {
                    jComboBox7.setSelectedIndex(0);
                    jTextField180.setText("A");
                } else {
                    jComboBox7.setSelectedIndex(1);
                    jTextField180.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class SuspensionDelanteraDer implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField178.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDerDel = false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDerDel = false;

                }
                if (valor >= 41) {
                    suspensionDerDel = true;

                }

                if (suspensionIzqDel && suspensionDerDel && suspensionDesvDel) {
                    jComboBox7.setSelectedIndex(0);
                    jTextField180.setText("A");
                } else {
                    jComboBox7.setSelectedIndex(1);
                    jTextField180.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField178.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDerDel = false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDerDel = false;

                }
                if (valor >= 41) {
                    suspensionDerDel = true;

                }

                if (suspensionIzqDel && suspensionDerDel && suspensionDesvDel) {
                    jComboBox7.setSelectedIndex(0);
                    jTextField180.setText("A");
                } else {
                    jComboBox7.setSelectedIndex(1);
                    jTextField180.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class SuspensionDelanteraDesv implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField179.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDesvDel = false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDesvDel = false;

                }
                if (valor >= 41) {
                    suspensionDesvDel = true;

                }

                if (suspensionIzqDel && suspensionDerDel && suspensionDesvDel) {
                    jComboBox7.setSelectedIndex(0);
                    jTextField180.setText("A");
                } else {
                    jComboBox7.setSelectedIndex(1);
                    jTextField180.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField179.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDesvDel = false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDesvDel = false;

                }
                if (valor >= 41) {
                    suspensionDesvDel = true;

                }

                if (suspensionIzqDel && suspensionDerDel && suspensionDesvDel) {
                    jComboBox7.setSelectedIndex(0);
                    jTextField180.setText("A");

                } else {
                    jComboBox7.setSelectedIndex(1);
                    jTextField180.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }
    
    
    
    
    
    
    
    
    class SuspensionPosteriorIzq implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField181.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionIzqPost=false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionIzqPost = false;

                }
                if (valor >= 41) {
                    suspensionIzqPost = true;

                }

                if (suspensionIzqPost && suspensionDerPost && suspensionDesvPost) {
                    jComboBox8.setSelectedIndex(0);
                    jTextField184.setText("A");
                } else {
                    jComboBox8.setSelectedIndex(1);
                    jTextField184.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField181.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionIzqPost=false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionIzqPost = false;

                }
                if (valor >= 41) {
                    suspensionIzqPost = true;

                }

                if (suspensionIzqPost && suspensionDerPost && suspensionDesvPost) {
                    jComboBox8.setSelectedIndex(0);
                    jTextField184.setText("A");
                } else {
                    jComboBox8.setSelectedIndex(1);
                    jTextField184.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class SuspensionPosteriorDer implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField182.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDerPost=false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDerPost = false;

                }
                if (valor >= 41) {
                    suspensionDerPost = true;

                }

                if (suspensionIzqPost && suspensionDerPost && suspensionDesvPost) {
                    jComboBox8.setSelectedIndex(0);
                    jTextField184.setText("A");
                } else {
                    jComboBox8.setSelectedIndex(1);
                    jTextField184.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField182.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDerPost=false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDerPost = false;

                }
                if (valor >= 41) {
                    suspensionDerPost = true;

                }

                if (suspensionIzqPost && suspensionDerPost && suspensionDesvPost) {
                    jComboBox8.setSelectedIndex(0);
                    jTextField184.setText("A");
                } else {
                    jComboBox8.setSelectedIndex(1);
                    jTextField184.setText("D");
                }

            }
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }

    class SuspensionPosteriorDesv implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            String x = jTextField183.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDesvPost=false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDesvPost = false;

                }
                if (valor >= 41) {
                    suspensionDesvPost = true;

                }

                if (suspensionIzqPost && suspensionDerPost && suspensionDesvPost) {
                    jComboBox8.setSelectedIndex(0);
                    jTextField184.setText("A");
                } else {
                    jComboBox8.setSelectedIndex(1);
                    jTextField184.setText("D");
                }

            }

        }

        public void removeUpdate(DocumentEvent e) {
            String x = jTextField183.getText();
            if (!x.equals("")) {
                double valor = Double.parseDouble(x);
                if (valor <= 29 && valor >= 0) {
                    suspensionDesvPost = false;

                }
                if (valor <= 40 && valor >= 30) {
                    suspensionDesvPost = false;

                }
                if (valor >= 41) {
                    suspensionDesvPost = true;

                }

                if (suspensionIzqDel && suspensionDerDel && suspensionDesvDel) {
                    jComboBox8.setSelectedIndex(0);
                    jTextField184.setText("A");

                } else {
                    jComboBox8.setSelectedIndex(1);
                    jTextField184.setText("D");
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

                }
                else{
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

                }
                else{
                    sonometroResult = false;
                    jComboBox2.setSelectedIndex(1);
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
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (k > 47 && k < 58) {//Si el caracter ingresado es una letra
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "No puede ingresar numeros!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
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

    private void jTextField177ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField177ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField177ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JComboBox jComboBox22;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JDesktopPane jDesktopPane1;
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
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
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
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
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
    private javax.swing.JTextField jTextField119;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField120;
    private javax.swing.JTextField jTextField121;
    private javax.swing.JTextField jTextField122;
    private javax.swing.JTextField jTextField123;
    private javax.swing.JTextField jTextField124;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField157;
    private javax.swing.JTextField jTextField159;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField160;
    private javax.swing.JTextField jTextField161;
    private javax.swing.JTextField jTextField162;
    private javax.swing.JTextField jTextField163;
    private javax.swing.JTextField jTextField164;
    private javax.swing.JTextField jTextField165;
    private javax.swing.JTextField jTextField166;
    private javax.swing.JTextField jTextField167;
    private javax.swing.JTextField jTextField168;
    private javax.swing.JTextField jTextField169;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField170;
    private javax.swing.JTextField jTextField171;
    private javax.swing.JTextField jTextField172;
    private javax.swing.JTextField jTextField173;
    private javax.swing.JTextField jTextField174;
    private javax.swing.JTextField jTextField177;
    private javax.swing.JTextField jTextField178;
    private javax.swing.JTextField jTextField179;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField180;
    private javax.swing.JTextField jTextField181;
    private javax.swing.JTextField jTextField182;
    private javax.swing.JTextField jTextField183;
    private javax.swing.JTextField jTextField184;
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
    private javax.swing.JTextField jTextField28;
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
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField83;
    private javax.swing.JTextField jTextField84;
    private javax.swing.JTextField jTextField85;
    private javax.swing.JTextField jTextField9;
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
