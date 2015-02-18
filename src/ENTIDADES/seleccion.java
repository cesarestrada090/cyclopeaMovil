/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDADES;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Personal
 */
public class seleccion {

public seleccion(){

}
public int select(String sColName, JTable ta)
{
int nColumns = 0, nLen = 0, indX = -1;
nLen = ta.getColumnCount();
for (int i = 0; i < nLen; i++){
if (sColName.equals(ta.getColumnName(i)) )
{
indX = i;
}
}
return indX;
}
public int removerfila ( DefaultTableModel model){
while (model.getRowCount()>0){
model.removeRow(0);
}
return 1;
}
}