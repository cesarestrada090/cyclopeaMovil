/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DATOS.AlineadorDAL;
import ENTIDADES.Alineador;

/**
 *
 * @author pc
 */
public class AlineadorBL {

    public boolean registrarAlineador(Alineador v) {
        if (new AlineadorDAL().registrarDatosAlineador(v)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean registrarDatosSinAlineamiento(Alineador v) {
        if (new AlineadorDAL().registrarDatosSinAlineamiento(v)) {
            return true;
        } else {
            return false;
        }
    }
}
