/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.GasometroDAL;
import DATOS.SonometroDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Gasometro;
import ENTIDADES.Sonometro;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class GasometroDL {
  
    public boolean registrarGasometroDiesel(Gasometro v)
    {
          if(new GasometroDAL().registrarDatosGasometroDiesel(v))
              return true;
          else
              return false;
    }
    
    public boolean registrarGasometroGasolina(Gasometro v)
    {
          if(new GasometroDAL().registrarDatosGasometroGasolina(v))
              return true;
          else
              return false;
    }

}
