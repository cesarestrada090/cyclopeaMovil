/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DATOS.CertificadoDAL;
import ENTIDADES.*;
import java.util.List;

/**
 *
 * @author pc
 */
public class CertificadoBL {

    public boolean registrarCertificado(Certificado v) {
        if (new CertificadoDAL().registrarDatosCertificado(v)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean registrarFotografia(Fotografias v) {
        if (new CertificadoDAL().grabarFotografias(v)) {
            return true;
        } else {
            return false;
        }
    }

    public int obtenerIdCertificado() {
        return new CertificadoDAL().obtenerNumeroRegistroCertificado();

    }

    public Object[][] listarCertificados(String placa) {
        return new CertificadoDAL().listarCertificados(placa);
    }
    
    public Object[][] listarAllCertificados() {
        return new CertificadoDAL().listarAllCertificados();
    }
    
    public Object[][] listarAllObservaciones() {
        return new CertificadoDAL().listarAllObservaciones();
    }

    public List obtenerListaModelo() {
        return new CertificadoDAL().obtenerListaModelo();
    }

    public List obtenerListaMarca() {
        return new CertificadoDAL().obtenerListaMarca();
    }

    public List obtenerListaCombustible() {
        return new CertificadoDAL().obtenerListaCombustible();
    }
    
    public List obtenerListaCarroceria() {
        return new CertificadoDAL().obtenerListaCarroceria();
    }
    public List obtenerListaCategoria() {
        return new CertificadoDAL().obtenerListaCategorias();
    }
    public Certificado obtenerIds() {
        return new CertificadoDAL().obtenerIds();
    }
    
    public int obtenerResultadoCertificado(int idInforme) {
        return new CertificadoDAL().obtenerResultadoCertificado(idInforme);
    }
    
    public boolean anularCertificado(int idInforme) {
        return new CertificadoDAL().anularCertificado(idInforme);
    }
}
