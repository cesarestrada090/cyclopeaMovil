/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.SonometroDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Observacion;
import ENTIDADES.Sonometro;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class ObservacionDAL {
  
    public boolean registrarObservacion(Observacion v)
    {
          if(new ObservacionDAL().registrarObservacion(v))
              return true;
          else
              return false;
    }

}
