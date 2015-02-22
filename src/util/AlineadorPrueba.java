/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.ObservacionDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Observacion;
import java.util.Date;

/**
 *
 * @author rchuc
 */
public class AlineadorPrueba {

    public static void main(String[] args) {
        Alineador c = new Alineador();
        c.setEje(4);
        c.setDesviacionejealineamiento(2.4);
        c.setIdCertificado(12);
        c.setMedidaejeneumatico(66);
        c.setResultadoejealineamiento("1");
        c.setResultadoejeneumatico("apribado");
        
        
        
        AlineadorDAL data= new AlineadorDAL();
        System.out.print(data.registrarDatosAlineador(c)) ;
    }
}
