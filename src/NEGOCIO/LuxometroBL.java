/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.LuxometroDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Luxometro;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class LuxometroBL {
  
    public boolean registrarLuxometro(Luxometro v)
    {
          if(new LuxometroDAL().registrarDatosLuxometro(v))
              return true;
          else
              return false;
    }

}
