/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.CertificadoDAL;
import DATOS.TarjetaPropiedadDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Certificado;
import ENTIDADES.TarjetaPropiedad;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class TarjetaPropiedadBL {
  
    public boolean registrarTarjetaPropiedad(TarjetaPropiedad v)
    {
          if(new TarjetaPropiedadDAL().registrarDatosTarjeta(v))
              return true;
          else
              return false;
    }

}
