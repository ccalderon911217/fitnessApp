package fitnessApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class CountdownActionListener
  implements ActionListener
{
  private int i;
  private JLabel jlabelincline;
  private String inclineValue;
  private int secs;
  
  public CountdownActionListener(int i, JLabel jlabelincline, String inclineValue)
  {
    this.i = i;
    this.jlabelincline = jlabelincline;
    this.inclineValue = inclineValue;
    this.secs = Integer.valueOf(inclineValue).intValue();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (this.secs >= 0)
    {
      if (this.secs <= 3) {
        this.jlabelincline.setForeground(Color.ORANGE);
      } else {
        this.jlabelincline.setForeground(Color.WHITE);
      }
      this.jlabelincline.setText("  : " + String.valueOf(this.secs));
      this.secs -= 1;
    }
    else
    {
      try
      {
        finalize();
      }
      catch (Throwable ex)
      {
        Logger.getLogger(CountdownActionListener.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
