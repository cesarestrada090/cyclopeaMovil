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
import ENTIDADES.Sonometro;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class SonometroBL {
  
    public boolean registrarSonometro(Sonometro v)
    {
          if(new SonometroDAL().registrarDatosObservacion(v))
              return true;
          else
              return false;
    }

}
