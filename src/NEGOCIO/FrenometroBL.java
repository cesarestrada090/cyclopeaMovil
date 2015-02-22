/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.CertificadoDAL;
import DATOS.FrenometroDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Certificado;
import ENTIDADES.Frenometro;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class FrenometroBL {
  
    public boolean registrarFrenometro(Frenometro v)
    {
          if(new FrenometroDAL().registrarDatosFrenometro(v))
              return true;
          else
              return false;
    }

}
