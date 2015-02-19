/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.ObservacionDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Observacion;
import ENTIDADES.Vehiculo;

/**
 *
 * @author rchuc
 */
public class VehiculoPrueba {

    public static void main(String[] args) {
        Vehiculo obs = new Vehiculo();
        obs.setNumSalidas(2);
        obs.setPesoBruto(1000);
        obs.setPesoNeto(800);
        obs.setPlaca("A1-3122");
        obs.setSerie("1222");
        obs.setnEjes(2);
        obs.setAlto(2.32);
        obs.setAncho(1.3);
        obs.setAnio(2005);
        obs.setCargaUtil(300.2);
        obs.setCategoria("M1");
        obs.setColor("Rojo");
        obs.setIdCarroceria("1");
        obs.setIdCombustible(null);
        obs.setIdMarca(null);
        obs.setIdModelo(null);
        obs.setLargo(2.2);
        obs.setMotor("2.2L");
        obs.setNumAsientos(3);
        obs.setNumCilindros(4);
        obs.setNumPasajeros(4);
        obs.setNumPuertas(5);
        obs.setNumRuedas(4);
        
        VehiculoDAL data= new VehiculoDAL();
        System.out.print(data.registrarDatosVehiculo(obs)) ;
    }
}
