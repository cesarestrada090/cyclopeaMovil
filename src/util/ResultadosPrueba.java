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
        Resultados obs = new Resultados();
        obs.setDisEjes(12.33);
        obs.setEmigases(1); // 1 aprobado , 0 jalado
        obs.setFreeEmer(1); // 1 aprobado , 0 jalado 
        obs.setFreeEstac(1); // 1 aprobado , 0 jalado
        obs.setFreeEmer(2); // 1 aprobado , 0 jalado
        obs.setFreServ(3); // 1 aprobado , 0 jalado
        obs.setPisos(1); 
        obs.setObservacion("Todo Perfecto");
        obs.setProfNeuma(1); // 1 aprobado , 0 jalado
        obs.setPruebaAli(1); // 1 aprobado , 0 jalado
        obs.setSuspension(1); // 1 aprobado , 0 jalado

        
        ResultadoDAL data= new ResultadoDAL();
        System.out.print(data.registrarDatosResultado(obs)) ;
    }
}
