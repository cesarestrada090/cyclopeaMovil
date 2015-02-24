/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.CertificadoDAL;
import DATOS.UsuarioDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Certificado;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class CertificadoBL {
  
    public boolean registrarCertificado(Certificado v)
    {
          if(new CertificadoDAL().registrarDatosCertificado(v))
              return true;
          else
              return false;
    }
    public int obtenerIdCertificado()
    {
         return new CertificadoDAL().obtenerNumeroRegistroCertificado();
          
    }
    
    public Object[][] listarCertificados(String placa)
    {
        return new CertificadoDAL().listarCertificados(placa);
    }


}
