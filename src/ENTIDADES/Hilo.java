/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

import PRESENTACION.frmInicio;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author PC
 */

public class Hilo extends Thread {
    JProgressBar progreso;
    JLabel ico;
    JLabel texto;
    JFrame jframeinicio;

    public Hilo(JProgressBar progreso,JLabel icono,JLabel texto,JFrame jf) {
        super();
        this.progreso = progreso;
        this.ico=icono;
        this.texto=texto;
        this.jframeinicio=jf;
    }
    @Override
    public void run()
    {
        for(int i=1;i<=100;i++)
        {
            progreso.setValue(i);
            pausa(25);
        }
       ejecutarInicioComponente();
    }
    public void pausa(int mlSeg)
    {
        try
        {
            Thread.sleep(mlSeg);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",0);
        }
    }
    public void ejecutarInicioComponente()
    {
        ico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/formulario/ok.png")));
        texto.setText("Componentes inicializados");
        showMessageDialog(null,"presione la tecla aceptar para ingresar..","BIENVENIDO",1);
        jframeinicio.dispose();
        new frmInicio().setVisible(true);
    }
}
