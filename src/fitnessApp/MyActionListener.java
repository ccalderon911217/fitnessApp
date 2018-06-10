package fitnessApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class MyActionListener
  implements ActionListener
{
  private int i;
  private JLabel jlabelincline;
  private JLabel jlabeljogger;
  private JLabel jlabelwalker;
  private JLabel jlabelrunner;
  private String inclineValue;
  private String joggerValue;
  private String walkerValue;
  private String runnerValue;
  
  public MyActionListener(int i, JLabel jlabelincline, JLabel jlabeljogger, JLabel jlabelwalker, JLabel jlabelrunner, String inclineValue, String joggerValue, String walkerValue, String runnerValue)
  {
    this.i = i;
    this.jlabelincline = jlabelincline;
    this.jlabeljogger = jlabeljogger;
    this.jlabelwalker = jlabelwalker;
    this.jlabelrunner = jlabelrunner;
    this.inclineValue = inclineValue;
    this.joggerValue = joggerValue;
    this.walkerValue = walkerValue;
    this.runnerValue = runnerValue;
  }
  
  public void actionPerformed(ActionEvent e)
  {
    this.jlabelincline.setText(this.inclineValue);
    this.jlabeljogger.setText(this.joggerValue);
    this.jlabelwalker.setText(this.walkerValue);
    this.jlabelrunner.setText(this.runnerValue);
  }
}
