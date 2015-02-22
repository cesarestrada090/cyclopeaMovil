/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.ObservacionDAL;
import ENTIDADES.Observacion;

/**
 *
 * @author rchuc
 */
public class ObservacionPrueba {

    public static void main(String[] args) {
        Observacion obs = new Observacion();
        obs.setCodigoObservacion("46541974");
        obs.setIdCertificado(1); // 1: Certificado Inspeccion vehicular , 2: Informe Técnico
        obs.setInterpretacion("23,25");
        obs.setCalificacion("Choque en la parte frontal");
        
        ObservacionDAL data= new ObservacionDAL();
        System.out.print(data.registrarDatosObservacion(obs)) ;
    }
}
