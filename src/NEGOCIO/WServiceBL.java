/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.webServiceDAL;
import ENTIDADES.*;

/**
 *
 * @author KACESE
 */
public class WServiceBL {
    public boolean registrarResultado(Resultados v)
    {
          if(new webServiceDAL().registrarDatosResultado(v))
              return true;
          else
              return false;
    }
    
    public boolean registrarObservacion(Observacion v)
    {
          if(new webServiceDAL().registrarDatosObservacion(v))
              return true;
          else
              return false;
    }
    
    public boolean registrarCertificado(Certificado v)
    {
          if(new webServiceDAL().registrarDatosCertificado(v))
              return true;
          else
              return false;
    }
    
     public int existeCertificado(int intIdCertificado)
    {
         return new webServiceDAL().existeCertificado(intIdCertificado);
          
    }
    
}
