/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.CertificadoDAL;
import DATOS.ObservacionDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Certificado;
import ENTIDADES.Observacion;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class ObservacionBL {
  
    public boolean registrarObservacion(Observacion v)
    {
          if(new ObservacionDAL().registrarDatosObservacion(v))
              return true;
          else
              return false;
    }

}
