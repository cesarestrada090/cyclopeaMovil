/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.LuxometroDAL;
import DATOS.ObservacionDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Luxometro;
import ENTIDADES.Observacion;
import java.util.Date;

/**
 *
 * @author rchuc
 */
public class LuxometroPrueba {

    public static void main(String[] args) {
        Luxometro c = new Luxometro();
        c.setAlineamientoLuz("1");
        c.setIdCertificado(1);
        c.setMedidaDerLuz(2.3);
        c.setMedidaIzqLuz(33);
        c.setResultadoLuz("aprobado");
        c.setTipoLuz(9);
        
        
        
        LuxometroDAL data= new LuxometroDAL();
        System.out.print(data.registrarDatosLuxometro(c)) ;
    }
}
