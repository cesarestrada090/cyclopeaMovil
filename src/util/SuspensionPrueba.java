/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.LuxometroDAL;
import DATOS.ObservacionDAL;
import DATOS.SuspensionDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Luxometro;
import ENTIDADES.Observacion;
import ENTIDADES.Suspension;
import java.util.Date;

/**
 *
 * @author rchuc
 */
public class SuspensionPrueba {

    public static void main(String[] args) {
        Suspension c = new Suspension();
       c.setDelanteraDer(2.3);
       c.setDelanteraDesv(3.2);
       c.setDelanteraIzq(4.2);
       c.setDelanteraResult("1");
       c.setDelanteraResultFinal("32");
       c.setIdCertificado(2);
       c.setPosteriorDer(4.3);
       c.setPosteriorDesv(3.5);
       c.setPosteriorIzq(3.6);
       c.setPosteriorResult("1");
       c.setPosteriorResultFinal("32");
        
        
        
        SuspensionDAL data= new SuspensionDAL();
        System.out.print(data.registrarDatosSuspension(c)) ;
    }
}
