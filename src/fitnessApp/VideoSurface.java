package fitnessApp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

class VideoSurface
  extends JPanel
{
  private BufferedImage image;
  
  public VideoSurface(BufferedImage image, int width, int height)
  {
    setOpaque(true);
    setPreferredSize(new Dimension(width, height));
    setMinimumSize(new Dimension(width, height));
    setMaximumSize(new Dimension(width, height));
    this.image = image;
  }
  
  protected void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;
    g2.drawImage(this.image, null, 0, 0);
  }
}
