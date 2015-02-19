///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package ENTIDADES;
//
//import com.csvreader.CsvReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import javax.swing.JOptionPane;
//
//
//public class leer_archivo {
//
//
//public ArrayList leer_archivo_exel(String archivo){
//
//ArrayList lista = new ArrayList();
//
//CsvReader reader = null;
//
//try {
//
//reader = new CsvReader(archivo,',');
//
//} catch (FileNotFoundException ex) {
//JOptionPane.showInputDialog("en leer archivo");
//}
//
//try {
//
//reader.readHeaders();
//
//} catch (IOException ex) {
//}
//
//try {
//
//Persona per;
//Vehiculo v;
//Revision rev;
//
//while (reader.readRecord())
//{
//    per=new Persona();
//    per.setDNI(reader.get("DNI"));
//    per.setRUC(reader.get("RUC"));
//    per.setRazon(reader.get("Nombre"));
//    per.setTelefono(reader.get("Telefono"));
//    v=new Vehiculo();
//    v.setPlaca(reader.get("Placa"));
//    v.setAnioFabricacion(reader.get("Afabricacion"));
//    v.setMarca(reader.get("Marca"));
//    v.setModelo(reader.get("Modelo"));
//    v.setTiposervicio(reader.get("Servicio"));
//
//    rev=new Revision();
//
//    rev.setFecha(reader.get("FechaVencimiento"));
//    
//    lista.add(per); // añadimos el objeto al arrayList
//    lista.add(v);
//    lista.add(rev);
//}
//
//} catch (IOException ex)
//{
//System.err.println(" en while readrecord ");
//
//}
//
//reader.close();
//
//return lista; // retorna una objeto de ArrayList
//}
//}
