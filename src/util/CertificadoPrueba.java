/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.CertificadoDAL;
import DATOS.ObservacionDAL;
import ENTIDADES.Certificado;
import ENTIDADES.Observacion;
import java.util.Date;

/**
 *
 * @author rchuc
 */
public class CertificadoPrueba {

    public static void main(String[] args) {
        Certificado c = new Certificado();
        c.setTipoDocTransp("1");// 1 RUC 2 DNI 3 Carnet Extran 
        c.setClaseAutorizacion("M1");
        c.setTipoDocEvaluar("1");// 1 CITV, 2 Inspeccion tecnica
        c.setNumDocTransp("46541974"); 
        c.setCodLocal("01"); // 01 central, 02 planta movil
        c.setcIdentidadCert("00");// Aún no se tiene el codigo de la entidad, debe darlo el MTC
        c.setNumDocEvaluar("A651154");
        c.setRazonTransp("Empresa Orion");
        c.setResultado(1);
        c.setVigencia("12"); //meses
        c.setUbigeo("130100");  // codigo de Truj en el INEI
        Date d= new Date();
        c.setFecInspeccion(d);
        c.setFecVencimiento(d);
        
        
        
        CertificadoDAL data= new CertificadoDAL();
        System.out.print(data.registrarDatosCertificado(c)) ;
        System.out.print(data.obtenerNumeroRegistroCertificado()) ;
    }
}
