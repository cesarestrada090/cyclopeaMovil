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
    public int obtenerNumeroRegistro()
    {
        int nregistro= new VehiculoDAL().obtenerNumeroRegistroUsuario();
        if(nregistro!=0)
            return nregistro+1;
        else
            return 1;
    }
    public boolean registrarVehiculo(Vehiculo v)
    {
          if(new VehiculoDAL().registrarDatosVehiculo(v))
              return true;
          else
              return false;
    }
    public boolean ModificarVehiculo(int idVehiculo)
    {
          if(new VehiculoDAL().ModificarDatosVehiculo(idVehiculo))
              return true;
          else
              return false;
    }
    public int listarBusquedaPlaca(String placa)
    {
        return new VehiculoDAL().BusquedaPlacas(placa);
    }
}
