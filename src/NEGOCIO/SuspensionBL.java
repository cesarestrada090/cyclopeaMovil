/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.SuspensionDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Suspension;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class SuspensionBL {
  
    public boolean registrarSuspension(Suspension v)
    {
          if(new SuspensionDAL().registrarDatosSuspension(v))
              return true;
          else
              return false;
    }

}
