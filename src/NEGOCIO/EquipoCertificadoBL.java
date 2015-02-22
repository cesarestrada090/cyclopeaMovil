/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.EquipoCertificadoDAL;
import DATOS.LuxometroDAL;
import DATOS.VehiculoDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.EquipoCertificado;
import ENTIDADES.Luxometro;
import ENTIDADES.Vehiculo;

/**
 *
 * @author pc
 */
public class EquipoCertificadoBL {
  
    public boolean registrarEquipoCertificado(EquipoCertificado v)
    {
          if(new EquipoCertificadoDAL().registrarDatosEquipoCertificado(v))
              return true;
          else
              return false;
    }

}
