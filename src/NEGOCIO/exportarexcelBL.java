/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;
import DATOS.exportarexcelDAL;
import ENTIDADES.exportarexcel;
import java.util.ArrayList;

/**
 *
 * @author PC
 */

public class exportarexcelBL {
    public int obtenerNumeroRegistro()
    {
        int nregistro= new exportarexcelDAL().obtenerNumeroRegistroUsuario();
        if(nregistro!=0)
            return nregistro+1;
        else
            return 1;
    }
    public boolean registrarUsuario(exportarexcel usu)
    {
          if(new exportarexcelDAL().registrarDatosUsuario(usu))
              return true;
          else
              return false;
    }
    public Object[][] listarUsuarios()
    {
        return new exportarexcelDAL().ListarUsuarios();
    }
    public boolean listarBusquedaUsuarios(int codigo)
    {
        return new exportarexcelDAL().ListarBusquedaUsuarios(codigo);
    }
    public boolean listarBusquedaPersonas(int codigo)
    {
        return new exportarexcelDAL().ListarBusquedaPersonas(codigo);
    }
    public ArrayList<exportarexcel> listaUsuario()
    {
        return new exportarexcelDAL().listarUsuarios();
    }
    public boolean modificarUsuario(exportarexcel usu)
    {
        if(new exportarexcelDAL().ModificarDatosUsuario(usu))
            return true;
        else
            return false;
    }
}
