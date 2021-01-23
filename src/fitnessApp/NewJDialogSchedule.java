package fitnessApp;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import jxl.write.WriteException;

public class NewJDialogSchedule
  extends JDialog
{
  private JMenu jMenu1;
  private JMenuBar jMenuBar1;
  private JMenuItem jMenuItemExportRoutine;
  private JMenuItem jMenuItemImportRoutine;
  private JScrollPane jScrollPane1;
  private JTable jTable1;
  
  public NewJDialogSchedule(Frame parent, boolean modal)
  {
    super(parent, modal);
    initComponents();
  }
  
  private void initComponents()
  {
    this.jScrollPane1 = new JScrollPane();
    this.jTable1 = new JTable();
    this.jMenuBar1 = new JMenuBar();
    this.jMenu1 = new JMenu();
    this.jMenuItemImportRoutine = new JMenuItem();
    this.jMenuItemExportRoutine = new JMenuItem();
    
    setDefaultCloseOperation(2);
    
    this.jTable1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null } }, new String[] { "Secs", "INCLINE", "Option 1", "Option 2", "Option 3" }));
    
    this.jScrollPane1.setViewportView(this.jTable1);
    
    this.jMenu1.setText("Manage Routines");
    
    this.jMenuItemImportRoutine.setText("Import Routine");
    this.jMenuItemImportRoutine.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        NewJDialogSchedule.this.jMenuItemImportRoutineActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItemImportRoutine);
    
    this.jMenuItemExportRoutine.setText("Export Routine");
    this.jMenuItemExportRoutine.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        NewJDialogSchedule.this.jMenuItemExportRoutineActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItemExportRoutine);
    
    this.jMenuBar1.add(this.jMenu1);
    
    setJMenuBar(this.jMenuBar1);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jScrollPane1, -1, 534, 32767));
    
    layout.setVerticalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addComponent(this.jScrollPane1, -2, 339, -2)
      .addGap(0, 0, 32767)));
    
    pack();
  }
  
  private void jMenuItemImportRoutineActionPerformed(ActionEvent evt)
  {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);
    if (result == 0)
    {
      File file = fileChooser.getSelectedFile();
      ReadExcel exportExcel = new ReadExcel(file.getAbsolutePath(), this.jTable1);
      try
      {
        exportExcel.read();
      }
      catch (IOException ex)
      {
        Logger.getLogger(NewJDialogSchedule.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  private void jMenuItemExportRoutineActionPerformed(ActionEvent evt)
  {
    JFileChooser fileChooser = new JFileChooser();
    
    int result = fileChooser.showSaveDialog(null);
    if (result == 0)
    {
      File file = fileChooser.getSelectedFile();
      WriteExcel importToExcel = new WriteExcel(file.getAbsolutePath(), this.jTable1);
      try
      {
        importToExcel.write();
      }
      catch (IOException ex)
      {
        Logger.getLogger(NewJDialogSchedule.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (WriteException ex)
      {
        Logger.getLogger(NewJDialogSchedule.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public static void main(String[] args)
  {
    try
    {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName()))
        {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (ClassNotFoundException ex)
    {
      Logger.getLogger(NewJDialogSchedule.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
      Logger.getLogger(NewJDialogSchedule.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      Logger.getLogger(NewJDialogSchedule.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (UnsupportedLookAndFeelException ex)
    {
      Logger.getLogger(NewJDialogSchedule.class.getName()).log(Level.SEVERE, null, ex);
    }
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        NewJDialogSchedule dialog = new NewJDialogSchedule(new JFrame(), true);
        dialog.addWindowListener(new WindowAdapter()
        {
          public void windowClosing(WindowEvent e)
          {
            System.exit(0);
          }
        });
        dialog.setVisible(true);
      }
    });
  }
  
  public JScrollPane getjScrollPane1()
  {
    return this.jScrollPane1;
  }
  
  public void setjScrollPane1(JScrollPane jScrollPane1)
  {
    this.jScrollPane1 = jScrollPane1;
  }
  
  public JTable getjTable1()
  {
    return this.jTable1;
  }
  
  public void setjTable1(JTable jTable1)
  {
    this.jTable1 = jTable1;
  }
}
