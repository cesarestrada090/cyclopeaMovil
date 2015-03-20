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
    
    public int obtenerTarjetaPropiedad()
    {
         return new TarjetaPropiedadDAL().obtenerNumeroRegistroTarjeta();          
    }
    
    public Object[][] listarTarjetas() {
        return new TarjetaPropiedadDAL().listarTarjetas();
    }
    
    public TarjetaPropiedad obtenerTarjetaP(int idTarjeta) {
        return new TarjetaPropiedadDAL().obtenerTarjetaP(idTarjeta);
    }
    
    public boolean actualizarTarjetaPropiedad(int id) {
        return new TarjetaPropiedadDAL().actualizarTarjetaPropiedad(id);
    }
    
    public int obtenerTipoServicio(int idCertificado) {
        return new TarjetaPropiedadDAL().obtenerTipoServicio(idCertificado);

    }

}
