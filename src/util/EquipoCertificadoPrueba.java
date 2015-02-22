/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.AlineadorDAL;
import DATOS.CertificadoDAL;
import DATOS.EquipoCertificadoDAL;
import DATOS.ObservacionDAL;
import ENTIDADES.Alineador;
import ENTIDADES.Certificado;
import ENTIDADES.EquipoCertificado;
import ENTIDADES.Observacion;
import java.util.Date;

/**
 *
 * @author rchuc
 */
public class EquipoCertificadoPrueba {

    public static void main(String[] args) {
        EquipoCertificado c = new EquipoCertificado();
        c.setIdCertificado(2);
        c.setNumAlineador(3);
        c.setNumAnalizador(5);
        c.setNumBancoSuspension(32);
        c.setNumFrenometro(32);
        c.setNumRegloscopio(311);
        
        
        
        EquipoCertificadoDAL data= new EquipoCertificadoDAL();
        System.out.print(data.registrarDatosEquipoCertificado(c)) ;
    }
}
