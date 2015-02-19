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
        obs.setNumDocEvaluar("46541974");
        obs.setTipoDocEval("1"); // 1: Certificado Inspeccion vehicular , 2: Informe Técnico
        obs.setCodigoObsDetec("23,25");
        obs.setDescObservacionDetec("Choque en la parte frontal");
        
        ObservacionDAL data= new ObservacionDAL();
        System.out.print(data.registrarDatosObservacion(obs)) ;
    }
}
