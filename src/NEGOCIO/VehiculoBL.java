/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.VehiculoDAL;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class VehiculoBL {
  
    public boolean registrarVehiculo(Vehiculo v)
    {
          if(new VehiculoDAL().registrarDatosVehiculo(v))
              return true;
          else
              return false;
    }

}
