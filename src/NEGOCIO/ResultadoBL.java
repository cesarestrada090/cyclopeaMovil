/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.CertificadoDAL;
import DATOS.ObservacionDAL;
import DATOS.ResultadoDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Certificado;
import ENTIDADES.Observacion;
import ENTIDADES.Resultado;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class ResultadoBL {
  
    public boolean registrarResultado(Resultado v)
    {
          if(new ResultadoDAL().registrarDatosResultado(v))
              return true;
          else
              return false;
    }

}
