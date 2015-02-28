/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.ObservacionDAL;
import DATOS.ResultadoDAL;
import ENTIDADES.Observacion;
import ENTIDADES.Resultados;

/**
 *
 * @author rchuc
 */
public class ResultadosPrueba {

    public static void main(String[] args) {
        Resultado obs = new Resultado();
        obs.setDistanciaEjes(12.33);
        obs.setEmiGases(1); // 1 aprobado , 0 jalado
        obs.setFrenoEmergencia(1); // 1 aprobado , 0 jalado 
        obs.setFrenoEstacion(1); // 1 aprobado , 0 jalado
        obs.setFrenoEmergencia(1); // 1 aprobado , 0 jalado
        obs.setFrenoServicioM(1); // 1 aprobado , 0 jalado
        obs.setNumPisos(1); 
        obs.setObservaciones("Todo Perfecto");
        obs.setProfNeuma(1); // 1 aprobado , 0 jalado
        obs.setPruebaAli(1); // 1 aprobado , 0 jalado
        obs.setPruebaLuces(1); // 1 aprobado , 0 jalado
        obs.setSuspension(1); // 1 aprobado , 0 jalado


        
        ResultadoDAL data= new ResultadoDAL();
       // System.out.print(data.registrarDatosResultado(obs)) ;
    }
}
