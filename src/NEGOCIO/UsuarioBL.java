/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;
import DATOS.UsuarioDAL;
import ENTIDADES.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class UsuarioBL {
    public int obtenerNumeroRegistro()
    {
        int nregistro= new UsuarioDAL().obtenerNumeroRegistroUsuario();
        if(nregistro!=0)
            return nregistro+1;
        else
            return 1;
    }
    public boolean registrarUsuario(Usuario usu)
    {
          if(new UsuarioDAL().registrarDatosUsuario(usu))
              return true;
          else
              return false;
    }
   
    public Object[][] listarUsuarios()
    {
        return new UsuarioDAL().ListarUsuarios();
    }


    public int listarBusquedaUsuarios(String usuario,String contrasenia)
    {
        return new UsuarioDAL().BusquedaUsuarios(usuario, contrasenia);
    }
    
    public ArrayList<Usuario> listaUsuario()
    {
        return new UsuarioDAL().listarUsuarios();
    }
    public boolean modificarUsuario(Usuario usu)
    {
        if(new UsuarioDAL().ModificarDatosUsuario(usu))
            return true;
        else
            return false;
    }
    public boolean eliminarUsuario(String usu)
    {
        if(new UsuarioDAL().EliminarUsuario(usu))
            return true;
        else
            return false;
    }
}
