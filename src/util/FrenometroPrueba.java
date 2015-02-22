/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.CertificadoDAL;
import DATOS.FrenometroDAL;
import DATOS.ObservacionDAL;
import DATOS.TarjetaPropiedadDAL;
import ENTIDADES.Certificado;
import ENTIDADES.Frenometro;
import ENTIDADES.Observacion;
import ENTIDADES.TarjetaPropiedad;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author rchuc
 */
public class FrenometroPrueba {

    public static void main(String[] args) {
        Frenometro c = new Frenometro();
       c.setDesequilibrioEmergencia(2.3);
       c.setDesequilibrioEstacionamiento(2);
       c.setDesequilibrioServicio(4);
       c.setEficienciaEmergencia(4);
       c.setEficienciaEst(5);
       c.setEficienciaServicio(6);
       c.setFuerzaEmerDer(5);
       c.setFuerzaEmerIzq(54);
       c.setFuerzaEstDer(21);
       c.setFuerzaEstIzq(22);
       c.setFuerzaServicioDer(32);
       c.setFuerzaServicioIzq(39);
       c.setIdEsqueda(22);
       c.setIdFrenometro(3);
       c.setPesoServicio(329);
       c.setResultadoEjeEmergencia("1");
       c.setResultadoEjeEstacionamiento("1");
       c.setResultadoEjeServicio("1");
       c.setResultadoGlobalEmergencia("Aprobado");
       c.setResultadoGlobalEstacionamiento("Aprobado");
       c.setResultadoGlobalServicio("Aprobado");
       c.setnEjes(4);
        
        FrenometroDAL data= new FrenometroDAL();
        System.out.print(data.registrarDatosFrenometro(c));
    }
}
