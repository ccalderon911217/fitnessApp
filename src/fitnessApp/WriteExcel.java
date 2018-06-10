package fitnessApp;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcel
{
  private String inputFile;
  private JTable madeRoutine;
  
  public WriteExcel(String inputFile, JTable madeRoutine)
  {
    this.inputFile = inputFile;
    this.madeRoutine = madeRoutine;
  }
  
  public void write()
    throws IOException, WriteException
  {
    File file = new File(this.inputFile + ".xls");
    WorkbookSettings wbSettings = new WorkbookSettings();
    
    wbSettings.setLocale(new Locale("en", "EN"));
    
    WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Routine", 0);
    WritableSheet excelSheet = workbook.getSheet(0);
    createLabel(excelSheet);
    createContent(excelSheet);
    
    workbook.write();
    workbook.close();
  }
  
  private void createLabel(WritableSheet sheet)
    throws WriteException
  {
    WritableCellFormat headerFormat = new WritableCellFormat();
    headerFormat.setAlignment(Alignment.CENTRE);
    headerFormat.setBackground(Colour.TAN);
    sheet.addCell(new Label(0, 0, "Secs", headerFormat));
    sheet.addCell(new Label(1, 0, "Incline", headerFormat));
    sheet.addCell(new Label(2, 0, "Jogger", headerFormat));
    sheet.addCell(new Label(3, 0, "Runner", headerFormat));
    sheet.addCell(new Label(4, 0, "Walker", headerFormat));
  }
  
  private void createContent(WritableSheet sheet)
    throws WriteException, RowsExceededException
  {
    for (int i = 0; i < this.madeRoutine.getModel().getRowCount(); i++) {
      for (int j = 0; j < this.madeRoutine.getModel().getColumnCount(); j++) {
        if (this.madeRoutine.getModel().getValueAt(i, j) != null) {
          sheet.addCell(new Label(j, i + 1, this.madeRoutine.getModel().getValueAt(i, j).toString()));
        }
      }
    }
  }
}
