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
  
    public boolean registrarGasometro(Gasometro v)
    {
          if(new GasometroDAL().registrarDatosGasometro(v))
              return true;
          else
              return false;
    }

}
