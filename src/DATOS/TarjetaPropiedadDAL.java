/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import ENTIDADES.TarjetaPropiedad;
import PRESENTACION.frmInicio;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author pc
 */
public class TarjetaPropiedadDAL {

    Connection cn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public boolean registrarDatosTarjeta(TarjetaPropiedad v) {
        try {
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            //cn = (Connection) Conexion.obtenerConexionMySQL("localhost", "bdnuevamovil", "root", "123456");
            String sentencia = "insert into tarjetapropiedad(placa,ntarjeta,razon1,domicilio,idclase,idmarca,fabricacion,"
                    + "idmodelo,version,idcombustible,idcarroceria,ejes,colores,nmotor,cilindros,nserie,vin,ruedas,pasajeros,asientos,"
                    + "peso_seco,peso_bruto,longitud,altura,ancho,carga_util,estado,fecha,nruedas,kilometraje)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = (PreparedStatement) cn.prepareStatement(sentencia);

            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getnTarjeta());
            ps.setString(3, v.getNombrePropietario());
            ps.setString(4, v.getDomicilio());
            ps.setString(5, v.getIdCategoria());
            ps.setString(6, v.getIdMarca());
            ps.setInt(7, v.getFabricacion());
            ps.setString(8, v.getIdModelo());
            ps.setString(9, v.getVersion());
            ps.setString(10, v.getIdCombustible());
            ps.setString(11, v.getIdCarroceria());
            ps.setInt(12, v.getEjes());
            ps.setString(13, v.getColores());
            ps.setObject(14, v.getnMotor());
            ps.setInt(15, v.getCilindros());
            ps.setString(16, v.getnSerie());
            ps.setString(17, v.getVin());
            ps.setObject(18, v.getRuedas());
            ps.setInt(19, v.getPasajeros());
            ps.setInt(20, v.getAsientos());
            ps.setDouble(21, v.getPesoSeco());
            ps.setDouble(22, v.getPesoBruto());
            ps.setDouble(23, v.getLongitud());
            ps.setDouble(24, v.getAltura());
            ps.setDouble(25, v.getAncho());
            ps.setDouble(26, v.getCargaUtil());
            ps.setString(27, v.getEstado());
            ps.setObject(28, v.getFecha());
            ps.setInt(29, v.getnRuedas());
            ps.setDouble(30, v.getKilometraje());
            ps.executeUpdate();
            return true;
            //"Un usuario ya ha sido registrado con la ubicación seleccionada"
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Datos Registrados con éxito", 0);
            return false;
        } finally {
            try {
                cn.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int obtenerNumeroRegistroTarjeta()
    {
        try{
            cn=Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            st=cn.createStatement();
            rs=st.executeQuery("select max(id_tarjeta) from tarjetaPropiedad;");
            if (rs.next()) {
                Integer numero= rs.getInt(1);
                if(numero!=null){
                    return numero;
                }
                else
                    return 0;
            }
        }catch(SQLException ex){
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CallcenterDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    
    public Object[][] listarTarjetas()
    {        Object[][] Certificados=null;
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL listarTarjetas()}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);
            
            Certificados= new Object[count][52];
            int i=0;
            while(rs.next()){

                Certificados[i][0]=rs.getString(1);
                Certificados[i][1]=rs.getString(2);
                Certificados[i][2]=rs.getString(3);
                Certificados[i][3]=rs.getString(4);
                Certificados[i][4]=rs.getString(5);                
                i++;
            }
            return Certificados;
        } catch (Exception ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();                
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public TarjetaPropiedad obtenerTarjetaP(int idTarjeta) {
    {        TarjetaPropiedad tarjeta=new TarjetaPropiedad();
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL obtenerTarjeta("+idTarjeta+")}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);
            
            while(rs.next()){
                tarjeta.setIdTarjeta(rs.getString(1));
                tarjeta.setPlaca(rs.getString(2));
                tarjeta.setnTarjeta(rs.getString(3));
                tarjeta.setNombrePropietario(rs.getString(4));
                tarjeta.setIdCategoria(rs.getString(5));
                tarjeta.setIdMarca(rs.getString(6));
                tarjeta.setFabricacion(Integer.parseInt(rs.getString(7)));
                tarjeta.setIdModelo(rs.getString(8));
                tarjeta.setVersion(rs.getString(9));
                tarjeta.setIdCombustible(rs.getString(10));
                tarjeta.setIdCarroceria(rs.getString(11));
                tarjeta.setEjes(Integer.parseInt(rs.getString(12)));
                tarjeta.setColores(rs.getString(13));
                tarjeta.setnMotor(rs.getString(14));
                tarjeta.setCilindros(Integer.parseInt(rs.getString(15)));                
                tarjeta.setnSerie(rs.getString(16));
                tarjeta.setVin(rs.getString(17));
                tarjeta.setRuedas(Integer.parseInt(rs.getString(18)));
                tarjeta.setPasajeros(Integer.parseInt(rs.getString(19)));
                tarjeta.setAsientos(Integer.parseInt(rs.getString(20)));
                tarjeta.setPesoSeco(Double.parseDouble(rs.getString(21)));
                tarjeta.setPesoBruto(Double.parseDouble(rs.getString(22)));
                tarjeta.setLongitud(Double.parseDouble(rs.getString(23)));                
                tarjeta.setAltura(Double.parseDouble(rs.getString(24)));
                tarjeta.setAncho(Double.parseDouble(rs.getString(25)));
                tarjeta.setCargaUtil(rs.getDouble(26));
                tarjeta.setnRuedas(rs.getInt(27));
                tarjeta.setKilometraje(Double.parseDouble(rs.getString(28)));
            }
            
            return tarjeta;
            
        } catch (Exception ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();                
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    }

    public Object[][] listarAllObservaciones()
    {        Object[][] Certificados=null;
        try {
            cn= Conexion.obtenerConexionMySQL(frmInicio.n_servidor,frmInicio.n_baseDatos,frmInicio.n_usuario,frmInicio.n_contraseña);
            cs=cn.prepareCall("{CALL listarObservaciones()}");
            rs=cs.executeQuery();

            int current=rs.getRow(); rs.last();
            int count=rs.getRow(); rs.beforeFirst();
            rs.relative(current);
            
            Certificados= new Object[count][4];
            int i=0;
            while(rs.next()){

                Certificados[i][0]=rs.getString(1);
                Certificados[i][1]=rs.getString(2);
                Certificados[i][2]=rs.getString(3);
                Certificados[i][3]=rs.getString(4);                
                i++;
            }
            return Certificados;
        } catch (Exception ex) {
            showMessageDialog(null,ex.getMessage(),"Error",0);
        }
        finally{
            try {
                cn.close();                
                rs.close();
                cs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
/*
 *
 * DELIMITER $$

 DROP PROCEDURE IF EXISTS `BusquedaPlaca` $$
 CREATE DEFINER=`root`@`localhost` PROCEDURE `BusquedaPlaca`(in placa varchar(8))
 BEGIN
 set placa=concat(placa);

 select c.Call_Id as placa from callcenter c
 where c.Call_Id like placa;

 END $$

 DELIMITER ;
 */
