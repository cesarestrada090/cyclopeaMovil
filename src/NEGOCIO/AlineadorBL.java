/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class AlineadorBL {
  
    public boolean registrarAlineador(Alineador v)
    {
          if(new AlineadorDAL().registrarDatosAlineador(v))
              return true;
          else
              return false;
    }

}
