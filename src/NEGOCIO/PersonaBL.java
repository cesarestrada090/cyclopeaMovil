/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NEGOCIO;

import DATOS.PersonaDAL;
import ENTIDADES.Persona;

/**
 *
 * @author pc
 */
public class PersonaBL {
    public int obtenerNumeroRegistro()
    {
        int nregistro= new PersonaDAL().obtenerNumeroRegistroPersona();
        if(nregistro!=0)
            return nregistro+1;
        else
            return 1;
    }
    public boolean registrarPersona(Persona per)
    {
          if(new PersonaDAL().registrarDatosPersona(per))
              return true;
          else
              return false;
    }
    
    public Object[][] listarVencimientos(String x1,String x2)
    {
        return new PersonaDAL().ListarVencimientos(x1,x2);
    }

    public Object[][] listarCorreos(String x1)
    {
        return new PersonaDAL().ListarCorreos(x1);
    }
    
    public Object[][] listarRellamadas(String x1)
    {
        return new PersonaDAL().ListarRellamadas(x1);
    }

    public Object[][] listarRellamadasTipo(String x1,String servicio)
    {
        return new PersonaDAL().ListarRellamadasTipo(x1,servicio);
    }

    public Object[][] listarVencimientosTipo(String x1,String x2,String servicio)
    {
        return new PersonaDAL().ListarVencimientosTipo(x1,x2,servicio);
    }

    public Object[][] ListarRellamadas()
    {
        return new PersonaDAL().ListarRellamadas();
    }

    public int listarBusqueda(String nombre)
    {
        return new PersonaDAL().BusquedaNombre(nombre);
    }

    public void ActualizarDatos(String telefono,int idPersona)
    {
        new PersonaDAL().ActualizarDatos(telefono, idPersona);
    }

    public void ActualizarDatosPlaca(String comentarios,String citv,int idVehiculo)
    {
        new PersonaDAL().ActualizarDatosPlaca(comentarios,citv, idVehiculo);
    }

    public void Actualizar(String telefono,String celular,String correo,int idPersona)
    {
        new PersonaDAL().Actualizar(telefono,celular,correo, idPersona);
    }
}
