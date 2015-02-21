/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import DATOS.CertificadoDAL;
import DATOS.ObservacionDAL;
import DATOS.TarjetaPropiedadDAL;
import ENTIDADES.Certificado;
import ENTIDADES.Observacion;
import ENTIDADES.TarjetaPropiedad;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author rchuc
 */
public class TarjetaPropiedadPrueba {

    public static void main(String[] args) {
        TarjetaPropiedad c = new TarjetaPropiedad();
        c.setAltura(2.0);
        c.setAncho(2.3);
        c.setAsientos(3);
        c.setCargaUtil(200);
        c.setCilindros(3);
        c.setColores("Amarillo y rojo");
        c.setDomicilio("Mi cada");
        c.setEjes(3);
        c.setEstado("1");
        c.setFabricacion(1992);
        c.setPlaca("ss-ssa");
        c.setnTarjeta("4558a58a");
        c.setIdCategoria("ss");
        c.setIdMarca("sadsa");
        c.setFecha(new Date());
        TarjetaPropiedadDAL data= new TarjetaPropiedadDAL();
        System.out.print(data.registrarDatosTarjeta(c));
    }
}
