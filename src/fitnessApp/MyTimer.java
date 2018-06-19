package fitnessApp;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyTimer
  extends JPanel
{
  /*
  private static final int MAX_DRAW_PROGRESS = 100;
  private static final int FRACTION_OF_OUTER_RING = 5;
  protected int strokeSize = 1;
  protected Color shadowColor = Color.black;
  protected boolean shady = false;
  protected boolean highQuality = true;
  protected Dimension arcs = new Dimension(180, 180);
  */
  protected JLabel jlabel6;
  private int progress = 0;
  
  
  public MyTimer(JLabel jlabel6)
  {
    this.jlabel6 = jlabel6;
    //setOpaque(false);
    
    super.setLayout(new BorderLayout());
    super.add(jlabel6, "East");
  }
  
  /*
  protected void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;
    if (this.highQuality) {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    g2.setColor(new Color(0, 0, 0, 65));
    
    g2.setStroke(new BasicStroke(this.strokeSize));
    if (this.progress <= 100)
    {
      int progressToDraw = Math.min(this.progress, 100);
      
      g2.setColor(Color.RED);
      int angle = -(int)(progressToDraw / 100.0F * 360.0F);
      g.fillArc(0, 0, getWidth(), getHeight(), 90, angle);
      
      g2.setColor(new Color(0, 0, 0, 65));
      g.fillArc(getWidth() / 5 / 2, getHeight() / 5 / 2, 
        getWidth() * 4 / 5, 
        
        getHeight() * 4 / 5, 90, angle);
      
      g.drawOval(0, 0, getWidth(), getHeight());
      
      g.drawOval(getWidth() / 5 / 2, getHeight() / 5 / 2, 
        getWidth() * 4 / 5, 
        
        getHeight() * 4 / 5);
    }
  }
  
  public Dimension getPreferredSize()
  {
    return new Dimension(180, 180);
  }
  
  public Dimension getMinimumSize()
  {
    return new Dimension(50, 50);
  }
  */
  
  public void setProgress(int progress)
  {
    this.progress = progress;
    //repaint();
  }
  
  public int getProgress()
  {
    return this.progress;
  }
  
  public void incrementOverTime(int timeToUpdate, int delay)
  {
    final Timer timer = new Timer(timeToUpdate, null);
    timer.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        MyTimer.this.setProgress(progress + 1);
//        if (MyTimer.this.progress >= 99)
//        {
//          timer.stop();
//          MyTimer.this.progress = 0;
//        }
      }
    });
    timer.setInitialDelay(delay);
    
    timer.start();
  }
}
