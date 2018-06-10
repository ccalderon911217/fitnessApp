package fitnessApp;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel
{
  private String inputFile;
  private JTable madeRoutine;
  
  public ReadExcel(String inputFile, JTable madeRoutine)
  {
    this.inputFile = inputFile;
    this.madeRoutine = madeRoutine;
  }
  
  public void read()
    throws IOException
  {
    File inputWorkbook = new File(this.inputFile);
    try
    {
      Workbook w = Workbook.getWorkbook(inputWorkbook);
      
      Sheet sheet = w.getSheet(0);
      for (int j = 0; j < sheet.getColumns(); j++) {
        for (int i = 1; i < sheet.getRows(); i++) {
          this.madeRoutine.getModel().setValueAt(sheet.getCell(j, i).getContents(), i - 1, j);
        }
      }
    }
    catch (BiffException ex)
    {
      Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
