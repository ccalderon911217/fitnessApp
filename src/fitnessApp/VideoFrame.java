package fitnessApp;

import com.sun.jna.NativeLibrary;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.RenderCallback;
import uk.co.caprica.vlcj.player.direct.RenderCallbackAdapter;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class VideoFrame
  extends JFrame
{
  private final DirectMediaPlayerComponent mediaPlayerComponent;
  private int width;
  private int height;
  private NewJDialogSchedule jDialoogSchedule;
  private final JPanel videoSurface;
  private final BufferedImage image;
  private int FONTSIZE = 30;
  private JLabel jLabelGoal;
  private JLabel jLabelCountdownValue;
  private JLabel jLabelLogo;
  private JLabel firstScreen;
  private String MAN = "resources/man.png";
  private String LOGO = "resources/lastlogo.jpg";
  private String NUMBERS_FONT = "resources/digital-7.ttf";
  private URL MANURL;
  private URL LOGOURL;
  private URL FONTURL;
  private CircleProgressBar circleBar;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabelInclineValue;
  private JLabel jLabelJoggerValue;
  private JLabel jLabelRunnerValue;
  private JLabel jLabelWalkerValue;
  private JMenu jMenu1;
  private JMenu jMenu2;
  private JMenuBar jMenuBar1;
  private JMenuItem jMenuItemPlayVideo;
  private JMenuItem jMenuItemaSchedule;
  private JPanel jPanelContainer;
  private JPanel jPanelIncline;
  private JPanel jPanelJogger;
  private JPanel jPanelRunner;
  private JPanel jPanelWalker;
  private JPanel timer;
  private JProgressBar jProgressBarTotal;
  private JSeparator jSeparatorIncline;
  private JSeparator jSeparatorJogger;
  private JSeparator jSeparatorRunner;
  private JSeparator jSeparatorWalker;
  
  public VideoFrame()
    throws FontFormatException
  {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    this.width = gd.getDisplayMode().getWidth();
    this.height = gd.getDisplayMode().getHeight();
    this.jDialoogSchedule = new NewJDialogSchedule(this, false);
    
    this.MANURL = ClassLoader.getSystemResource(this.MAN);
    this.LOGOURL = ClassLoader.getSystemResource(this.LOGO);
    this.FONTURL = ClassLoader.getSystemResource(this.NUMBERS_FONT);
    
    this.image = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(this.width, this.height);
    
    this.videoSurface = new VideoSurface(this.image, this.width, this.height);
    BufferFormatCallback bufferFormatCallback = new BufferFormatCallback()
    {
      public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight)
      {
        return new RV32BufferFormat(VideoFrame.this.width, VideoFrame.this.height);
      }
    };
    this.mediaPlayerComponent = new DirectMediaPlayerComponent(bufferFormatCallback)
    {
      protected RenderCallback onGetRenderCallback()
      {
        return new TutorialRenderCallbackAdapter();
      }
    };
    setResizable(false);
    try
    {
      init();
    }
    catch (IOException ex)
    {
      Logger.getLogger(VideoFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void init()
    throws IOException, FontFormatException
  {
    setBounds(0, 0, this.width, this.height);
    //InputStream myStream = new BufferedInputStream(new FileInputStream("digital-7.ttf"));
    Font font = Font.createFont(0, getClass().getClassLoader().getResourceAsStream(this.NUMBERS_FONT));
    font = font.deriveFont(1, 60.0F);
    
    this.jPanelContainer = new JPanel();
    this.jPanelIncline = new JPanel();
    this.jLabel1 = new JLabel();
    this.jLabel1.setForeground(Color.RED);
    this.jLabel1.setFont(new Font("Helvetica", 1, this.FONTSIZE));
    this.jLabelInclineValue = new JLabel();
    this.jLabelInclineValue.setForeground(Color.WHITE);
    this.jLabelInclineValue.setFont(font);
    this.jLabelInclineValue.setBorder(new EmptyBorder(0, 20, 20, 20));
    this.jSeparatorIncline = new JSeparator();
    
    this.jPanelRunner = new JPanel();
    this.jLabel4 = new JLabel();
    this.jLabel4.setForeground(Color.RED);
    this.jLabel4.setFont(new Font("Helvetica", 1, this.FONTSIZE));
    this.jLabelRunnerValue = new JLabel();
    this.jLabelRunnerValue.setForeground(Color.WHITE);
    this.jLabelRunnerValue.setFont(font);
    this.jLabelRunnerValue.setBorder(new EmptyBorder(0, 20, 20, 20));
    this.jSeparatorRunner = new JSeparator();
    
    this.jPanelJogger = new JPanel();
    this.jLabel3 = new JLabel();
    this.jLabel3.setForeground(Color.RED);
    this.jLabel3.setFont(new Font("Helvetica", 1, this.FONTSIZE));
    this.jLabelJoggerValue = new JLabel();
    this.jLabelJoggerValue.setForeground(Color.WHITE);
    this.jLabelJoggerValue.setFont(font);
    this.jLabelJoggerValue.setBorder(new EmptyBorder(0, 20, 20, 20));
    this.jSeparatorJogger = new JSeparator();
    
    this.jPanelWalker = new JPanel();
    this.jLabel2 = new JLabel();
    this.jLabel2.setForeground(Color.RED);
    this.jLabel2.setFont(new Font("Helvetica", 1, this.FONTSIZE));
    this.jLabelWalkerValue = new JLabel();
    this.jLabelWalkerValue.setForeground(Color.WHITE);
    this.jLabelWalkerValue.setFont(font);
    this.jLabelWalkerValue.setBorder(new EmptyBorder(0, 20, 20, 20));
    this.jSeparatorWalker = new JSeparator();
    
    this.jMenuBar1 = new JMenuBar();
    this.jMenu1 = new JMenu();
    this.jMenuItemaSchedule = new JMenuItem();
    this.jMenu2 = new JMenu();
    this.jMenuItemPlayVideo = new JMenuItem();
    this.jProgressBarTotal = new JProgressBar();
    
    this.jLabelCountdownValue = new JLabel();
    this.jLabelCountdownValue.setForeground(Color.WHITE);
    this.jLabelCountdownValue.setFont(font);
    this.jMenuItemPlayVideo.setEnabled(false);
    setDefaultCloseOperation(3);
    
    this.jLabel1.setText("INCLINE");
    
    this.jLabelInclineValue.setText("jLabel2");
    
    GroupLayout jPanelInclineLayout = new GroupLayout(this.jPanelIncline);
    this.jPanelIncline.setLayout(jPanelInclineLayout);
    jPanelInclineLayout.setHorizontalGroup(jPanelInclineLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelInclineLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelInclineLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelInclineValue)
      .addComponent(this.jLabel1))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorIncline));
    
    jPanelInclineLayout.setVerticalGroup(jPanelInclineLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelInclineLayout.createSequentialGroup()
      .addComponent(this.jLabel1)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.jSeparatorIncline, -2, 10, -2)
      .addGap(10, 10, 10)
      .addComponent(this.jLabelInclineValue)
      .addGap(0, 44, 32767)));
    
    this.jLabel4.setText("Option 3");
    
    this.jLabelRunnerValue.setText("jLabel2");
    
    GroupLayout jPanelRunnerLayout = new GroupLayout(this.jPanelRunner);
    this.jPanelRunner.setLayout(jPanelRunnerLayout);
    jPanelRunnerLayout.setHorizontalGroup(jPanelRunnerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelRunnerLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelRunnerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelRunnerValue)
      .addComponent(this.jLabel4))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorRunner));
    
    jPanelRunnerLayout.setVerticalGroup(jPanelRunnerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelRunnerLayout.createSequentialGroup()
      .addComponent(this.jLabel4)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.jSeparatorRunner, -2, 10, -2)
      .addGap(10, 10, 10)
      .addComponent(this.jLabelRunnerValue)
      .addGap(0, 0, 32767)));
    
    this.jLabel3.setText("Option 2");
    
    this.jLabelJoggerValue.setText("jLabel2");
    
    GroupLayout jPanelJoggerLayout = new GroupLayout(this.jPanelJogger);
    this.jPanelJogger.setLayout(jPanelJoggerLayout);
    jPanelJoggerLayout.setHorizontalGroup(jPanelJoggerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelJoggerLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelJoggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelJoggerValue)
      .addComponent(this.jLabel3))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorJogger));
    
    jPanelJoggerLayout.setVerticalGroup(jPanelJoggerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelJoggerLayout.createSequentialGroup()
      .addComponent(this.jLabel3)
      .addGap(4, 4, 4)
      .addComponent(this.jSeparatorJogger, -2, 10, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jLabelJoggerValue)
      .addGap(0, 42, 32767)));
    
    this.jLabel2.setText("Option 1");
    
    this.jLabelWalkerValue.setText("jLabel2");
    
    GroupLayout jPanelWalkerLayout = new GroupLayout(this.jPanelWalker);
    this.jPanelWalker.setLayout(jPanelWalkerLayout);
    jPanelWalkerLayout.setHorizontalGroup(jPanelWalkerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelWalkerLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelWalkerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelWalkerValue)
      .addComponent(this.jLabel2))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorWalker));
    
    jPanelWalkerLayout.setVerticalGroup(jPanelWalkerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelWalkerLayout.createSequentialGroup()
      .addComponent(this.jLabel2)
      .addGap(4, 4, 4)
      .addComponent(this.jSeparatorWalker, -2, 10, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jLabelWalkerValue)
      .addGap(0, 42, 32767)));
    
    GroupLayout jPanelContainerLayout = new GroupLayout(this.jPanelContainer);
    this.jPanelContainer.setLayout(jPanelContainerLayout);
    jPanelContainerLayout.setHorizontalGroup(jPanelContainerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelContainerLayout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jPanelIncline, -2, -1, -2)
      .addGap(77, 77, 77)
      .addComponent(this.jPanelWalker, -2, -1, -2)
      .addGap(60, 60, 60)
      .addComponent(this.jPanelJogger, -2, -1, -2)
      .addGap(80, 80, 80)
      .addComponent(this.jPanelRunner, -2, -1, -2)
      .addContainerGap(49, 32767)));
    
    jPanelContainerLayout.setVerticalGroup(jPanelContainerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelContainerLayout.createSequentialGroup()
      .addContainerGap()
      .addGroup(jPanelContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jPanelIncline, -2, 102, -2)
      .addComponent(this.jPanelWalker, -2, -1, -2)
      .addGroup(jPanelContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
      .addComponent(this.jPanelRunner, GroupLayout.Alignment.LEADING, -1, -1, 32767)
      .addComponent(this.jPanelJogger, GroupLayout.Alignment.LEADING, -1, -1, 32767)))
      .addGap(0, 14, 32767)));
    
    this.jMenu1.setText("Schedule");
    
    this.jMenuItemaSchedule.setText("Manage Routine Schedule");
    this.jMenuItemaSchedule.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        VideoFrame.this.jMenuItemaScheduleActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItemaSchedule);
    
    this.jMenuBar1.add(this.jMenu1);
    
    this.jMenu2.setText("Video");
    
    this.jMenuItemPlayVideo.setText("Play Video");
    this.jMenuItemPlayVideo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        VideoFrame.this.jMenuItemPlayVideoActionPerformed(evt);
      }
    });
    this.jMenu2.add(this.jMenuItemPlayVideo);
    
    this.jMenuBar1.add(this.jMenu2);
    
    setJMenuBar(this.jMenuBar1);
    
    add(this.videoSurface);
    
    BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(this.MAN));
    
    ImageIcon icon = new ImageIcon(img);
    
    this.jLabelGoal = new JLabel(icon);
    
    this.circleBar = new CircleProgressBar(getjLabelCountdownValue());
    this.circleBar.setPreferredSize(new Dimension(180, 180));
    
    GridBagLayout videoLayout = new GridBagLayout();
    
    this.videoSurface.setLayout(videoLayout);
    GridBagConstraints c = new GridBagConstraints();
    
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.weightx = 1.0D;
    c.weighty = 0.0D;
    c.anchor = 17;
    
    this.videoSurface.add(this.circleBar, c);
    
    c.gridx = 5;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.weightx = 0.0D;
    c.weighty = 0.0D;
    c.anchor = 14;
    
    //this.videoSurface.add(this.jLabelGoal, c);
    
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 5;
    c.gridy = 1;
    c.weightx = 0.0D;
    c.weighty = 0.0D;
    this.jProgressBarTotal.setOrientation(1);
    this.jProgressBarTotal.setMaximum(100);
    this.jProgressBarTotal.setMinimum(0);
    this.jProgressBarTotal.setValue(0);
    this.jProgressBarTotal.setOpaque(false);
    this.jProgressBarTotal.setPreferredSize(new Dimension(40, 420));
    
    c.anchor = 13;
    c.insets = new Insets(0, 0, 0, 40);
    
    //this.videoSurface.add(this.jProgressBarTotal, c);
    
    c.ipady = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 0;
    c.gridy = 2;
    c.weightx = 1.0D;
    c.weighty = 1.0D;
    c.fill = 2;
    c.anchor = 16;
    this.jPanelIncline.setPreferredSize(new Dimension(140, 120));
    c.insets = new Insets(0, 0, 0, 20);
    this.videoSurface.add(this.jPanelIncline, c);
    
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 2;
    c.gridy = 2;
    c.weightx = 1.0D;
    c.weighty = 1.0D;
    c.fill = 2;
    c.anchor = 16;
    this.jPanelWalker.setPreferredSize(new Dimension(140, 120));
    c.insets = new Insets(0, 0, 0, 0);
    this.videoSurface.add(this.jPanelWalker, c);
    
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 3;
    c.gridy = 2;
    c.weightx = 1.0D;
    c.weighty = 1.0D;
    c.fill = 2;
    c.anchor = 16;
    this.jPanelJogger.setPreferredSize(new Dimension(140, 120));
    this.videoSurface.add(this.jPanelJogger, c);
    
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 4;
    c.gridy = 2;
    c.weightx = 1.0D;
    c.weighty = 1.0D;
    c.fill = 2;
    c.anchor = 16;
    this.jPanelRunner.setPreferredSize(new Dimension(140, 120));
    this.videoSurface.add(this.jPanelRunner, c);
    
    BufferedImage img1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(this.LOGO));
    
    ImageIcon icon1 = new ImageIcon(img1);
    this.jLabelLogo = new JLabel(icon1);
    this.jLabelLogo.setVisible(false);
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 5;
    c.gridy = 0;
    c.weightx = 0.0D;
    c.weighty = 0.0D;
    c.fill = 1;
    c.anchor = 17;
    this.videoSurface.add(this.jLabelLogo, c);
    
    BufferedImage img2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(this.LOGO));
    
    ImageIcon icon2 = new ImageIcon(img2);
    
    this.firstScreen = new JLabel(icon2);
    
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 1;
    c.gridy = 0;
    c.fill = 1;
    c.anchor = 10;
    this.videoSurface.add(this.firstScreen, c);
    this.firstScreen.setVisible(true);
    
    this.jPanelContainer.setVisible(true);
    
    this.jPanelContainer.setOpaque(false);
    this.jPanelIncline.setBackground(new Color(0, 0, 0, 125));
    this.jPanelIncline.setBorder(new LineBorder(Color.RED, 2, false));
    this.jPanelJogger.setBackground(new Color(0, 0, 0, 125));
    this.jPanelJogger.setBorder(new LineBorder(Color.RED, 2, false));
    this.jPanelRunner.setBackground(new Color(0, 0, 0, 125));
    this.jPanelRunner.setBorder(new LineBorder(Color.RED, 2, false));
    
    this.jPanelWalker.setBorder(new LineBorder(Color.RED, 2, false));
    this.jPanelWalker.setBackground(new Color(0, 0, 0, 125));
    this.jPanelIncline.setVisible(false);
    this.jPanelJogger.setVisible(false);
    this.jPanelRunner.setVisible(false);
    this.jPanelWalker.setVisible(false);
    this.jProgressBarTotal.setVisible(false);
    this.jLabelGoal.setVisible(false);
    this.circleBar.setVisible(false);
    
    pack();
  }
  
  private void initComponents()
  {
    this.jPanelContainer = new JPanel();
    this.jPanelIncline = new JPanel();
    this.jLabel1 = new JLabel();
    this.jLabelInclineValue = new JLabel();
    this.jSeparatorIncline = new JSeparator();
    this.jPanelRunner = new JPanel();
    this.jLabel4 = new JLabel();
    this.jLabelRunnerValue = new JLabel();
    this.jSeparatorRunner = new JSeparator();
    this.jPanelWalker = new JPanel();
    this.jLabel2 = new JLabel();
    this.jLabelWalkerValue = new JLabel();
    this.jSeparatorWalker = new JSeparator();
    this.jPanelJogger = new JPanel();
    this.jLabel3 = new JLabel();
    this.jLabelJoggerValue = new JLabel();
    this.jSeparatorJogger = new JSeparator();
    this.jProgressBarTotal = new JProgressBar();
    this.jMenuBar1 = new JMenuBar();
    this.jMenu1 = new JMenu();
    this.jMenuItemaSchedule = new JMenuItem();
    this.jMenu2 = new JMenu();
    this.jMenuItemPlayVideo = new JMenuItem();
    
    setDefaultCloseOperation(3);
    
    this.jPanelIncline.setPreferredSize(new Dimension(100, 100));
    
    this.jLabel1.setText("Incline");
    
    this.jLabelInclineValue.setText("jLabel2");
    
    GroupLayout jPanelInclineLayout = new GroupLayout(this.jPanelIncline);
    this.jPanelIncline.setLayout(jPanelInclineLayout);
    jPanelInclineLayout.setHorizontalGroup(jPanelInclineLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelInclineLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelInclineLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelInclineValue)
      .addComponent(this.jLabel1))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorIncline));
    
    jPanelInclineLayout.setVerticalGroup(jPanelInclineLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelInclineLayout.createSequentialGroup()
      .addComponent(this.jLabel1)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.jSeparatorIncline, -2, 10, -2)
      .addGap(10, 10, 10)
      .addComponent(this.jLabelInclineValue)
      .addGap(0, 44, 32767)));
    
    this.jPanelRunner.setPreferredSize(new Dimension(100, 100));
    
    this.jLabel4.setText("Runner");
    
    this.jLabelRunnerValue.setText("jLabel2");
    
    GroupLayout jPanelRunnerLayout = new GroupLayout(this.jPanelRunner);
    this.jPanelRunner.setLayout(jPanelRunnerLayout);
    jPanelRunnerLayout.setHorizontalGroup(jPanelRunnerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelRunnerLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelRunnerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelRunnerValue)
      .addComponent(this.jLabel4))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorRunner));
    
    jPanelRunnerLayout.setVerticalGroup(jPanelRunnerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelRunnerLayout.createSequentialGroup()
      .addComponent(this.jLabel4)
      .addGap(4, 4, 4)
      .addComponent(this.jSeparatorRunner, -2, 10, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jLabelRunnerValue)
      .addGap(0, 42, 32767)));
    
    this.jPanelWalker.setPreferredSize(new Dimension(100, 100));
    
    this.jLabel2.setText("Walker");
    
    this.jLabelWalkerValue.setText("jLabel2");
    
    GroupLayout jPanelWalkerLayout = new GroupLayout(this.jPanelWalker);
    this.jPanelWalker.setLayout(jPanelWalkerLayout);
    jPanelWalkerLayout.setHorizontalGroup(jPanelWalkerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelWalkerLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelWalkerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelWalkerValue)
      .addComponent(this.jLabel2))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorWalker));
    
    jPanelWalkerLayout.setVerticalGroup(jPanelWalkerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelWalkerLayout.createSequentialGroup()
      .addComponent(this.jLabel2)
      .addGap(4, 4, 4)
      .addComponent(this.jSeparatorWalker, -2, 10, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jLabelWalkerValue)
      .addGap(0, 42, 32767)));
    
    this.jPanelJogger.setPreferredSize(new Dimension(100, 100));
    
    this.jLabel3.setText("Jogger");
    
    this.jLabelJoggerValue.setText("jLabel2");
    
    GroupLayout jPanelJoggerLayout = new GroupLayout(this.jPanelJogger);
    this.jPanelJogger.setLayout(jPanelJoggerLayout);
    jPanelJoggerLayout.setHorizontalGroup(jPanelJoggerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelJoggerLayout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanelJoggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabelJoggerValue)
      .addComponent(this.jLabel3))
      .addContainerGap(27, 32767))
      .addComponent(this.jSeparatorJogger));
    
    jPanelJoggerLayout.setVerticalGroup(jPanelJoggerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelJoggerLayout.createSequentialGroup()
      .addComponent(this.jLabel3)
      .addGap(4, 4, 4)
      .addComponent(this.jSeparatorJogger, -2, 10, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jLabelJoggerValue)
      .addGap(0, 42, 32767)));
    
    GroupLayout jPanelContainerLayout = new GroupLayout(this.jPanelContainer);
    this.jPanelContainer.setLayout(jPanelContainerLayout);
    jPanelContainerLayout.setHorizontalGroup(jPanelContainerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelContainerLayout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jPanelIncline, -2, -1, -2)
      .addGap(77, 77, 77)
      .addComponent(this.jPanelWalker, -2, -1, -2)
      .addGap(60, 60, 60)
      .addComponent(this.jPanelJogger, -2, -1, -2)
      .addGap(80, 80, 80)
      .addComponent(this.jPanelRunner, -2, -1, -2)
      .addContainerGap(49, 32767)));
    
    jPanelContainerLayout.setVerticalGroup(jPanelContainerLayout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanelContainerLayout.createSequentialGroup()
      .addContainerGap()
      .addGroup(jPanelContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jPanelIncline, -2, 102, -2)
      .addComponent(this.jPanelWalker, -2, -1, -2)
      .addGroup(jPanelContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
      .addComponent(this.jPanelRunner, GroupLayout.Alignment.LEADING, -1, -1, 32767)
      .addComponent(this.jPanelJogger, GroupLayout.Alignment.LEADING, -1, -1, 32767)))
      .addGap(0, 14, 32767)));
    
    this.jMenu1.setText("Schedule");
    
    this.jMenuItemaSchedule.setText("Manage Routine Schedule");
    this.jMenuItemaSchedule.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        VideoFrame.this.jMenuItemaScheduleActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItemaSchedule);
    
    this.jMenuBar1.add(this.jMenu1);
    
    this.jMenu2.setText("Video");
    
    this.jMenuItemPlayVideo.setText("Play Video");
    this.jMenuItemPlayVideo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        VideoFrame.this.jMenuItemPlayVideoActionPerformed(evt);
      }
    });
    this.jMenu2.add(this.jMenuItemPlayVideo);
    
    this.jMenuBar1.add(this.jMenu2);
    
    setJMenuBar(this.jMenuBar1);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap(-1, 32767)
      .addComponent(this.jProgressBarTotal, -2, -1, -2)
      .addGap(295, 295, 295))
      .addGroup(layout.createSequentialGroup()
      .addGap(139, 139, 139)
      .addComponent(this.jPanelContainer, -2, -1, -2)
      .addContainerGap(130, 32767)));
    
    layout.setVerticalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jProgressBarTotal, -2, -1, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 116, 32767)
      .addComponent(this.jPanelContainer, -2, -1, -2)
      .addContainerGap()));
    
    pack();
  }
  
  private void jMenuItemaScheduleActionPerformed(ActionEvent evt)
  {
    this.jDialoogSchedule.setVisible(true);
    this.jMenuItemPlayVideo.setEnabled(true);
  }
  
  private void jMenuItemPlayVideoActionPerformed(ActionEvent evt)
  {
    JFileChooser fileChooser = new JFileChooser();
    
    int result = fileChooser.showOpenDialog(null);
    if (result == 0)
    {
      String path = Paths.get(fileChooser.getSelectedFile().toString(), new String[0]).toUri().toString();
      getjMenuBar1().setVisible(false);
      String[] options = { " :live-caching=200" };
      this.mediaPlayerComponent.getMediaPlayer().playMedia(path, options);
      
      this.firstScreen.setVisible(false);
      this.jPanelIncline.setVisible(true);
      this.jPanelJogger.setVisible(true);
      this.jPanelRunner.setVisible(true);
      this.jPanelWalker.setVisible(true);
      //this.jProgressBarTotal.setVisible(true);
      //this.jLabelGoal.setVisible(true);
      this.jLabelLogo.setVisible(true);
      this.circleBar.setVisible(true);
      
      GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
      
      device.setFullScreenWindow(this);
      
      //populateProgressBar();
      changeValues();
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
      Logger.getLogger(VideoFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
      Logger.getLogger(VideoFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      Logger.getLogger(VideoFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (UnsupportedLookAndFeelException ex)
    {
      Logger.getLogger(VideoFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    new NativeDiscovery().discover();
    
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          new VideoFrame().setVisible(true);
        }
        catch (FontFormatException ex)
        {
          Logger.getLogger(VideoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
  }
  
  private void changeValues()
  {
    TableModel tm = this.jDialoogSchedule.getjTable1().getModel();
    
    Timer countDownTimer = new Timer(950, new CountdownActionListener(0, this.jLabelCountdownValue, tm.getValueAt(0, 0).toString()));
    countDownTimer.setInitialDelay(0);
    countDownTimer.start();
    
    Timer tempTimer = new Timer(1, new MyActionListener(0, this.jLabelInclineValue, this.jLabelJoggerValue, this.jLabelWalkerValue, this.jLabelRunnerValue, tm.getValueAt(0, 1).toString(), tm.getValueAt(0, 3).toString(), tm.getValueAt(0, 2).toString(), tm.getValueAt(0, 4).toString()));
    tempTimer.setInitialDelay(0);
    tempTimer.setRepeats(false);
    tempTimer.start();
    this.jLabelInclineValue.setText(tm.getValueAt(0, 1).toString());
    this.jLabelJoggerValue.setText(tm.getValueAt(0, 3).toString());
    this.jLabelWalkerValue.setText(tm.getValueAt(0, 2).toString());
    this.jLabelRunnerValue.setText(tm.getValueAt(0, 4).toString());
    int totalWaiting = Integer.valueOf(tm.getValueAt(0, 0).toString()).intValue() * 1000;
    
    this.circleBar.incrementOverTime(Integer.valueOf(tm.getValueAt(0, 0).toString()).intValue() * 10, 0);
    for (int i = 1; i < this.jDialoogSchedule.getjTable1().getModel().getRowCount(); i++) {
      if (tm.getValueAt(i, 0) != null)
      {
        tempTimer = new Timer(1, new MyActionListener(i, this.jLabelInclineValue, this.jLabelJoggerValue, this.jLabelWalkerValue, this.jLabelRunnerValue, tm.getValueAt(i, 1).toString(), tm.getValueAt(i, 3).toString(), tm.getValueAt(i, 2).toString(), tm.getValueAt(i, 4).toString()));
        tempTimer.setInitialDelay(totalWaiting);
        tempTimer.setRepeats(false);
        tempTimer.start();
        
        countDownTimer = new Timer(950, new CountdownActionListener(i, this.jLabelCountdownValue, tm.getValueAt(i, 0).toString()));
        
        countDownTimer.setInitialDelay(totalWaiting);
        
        countDownTimer.start();
        
        int secs = Integer.valueOf(tm.getValueAt(i, 0).toString()).intValue();
        this.circleBar.incrementOverTime(secs * 10, totalWaiting);
        
        totalWaiting += Integer.valueOf(tm.getValueAt(i, 0).toString()).intValue() * 1000;
      }
    }
  }
  
  public NewJDialogSchedule getjDialoogSchedule()
  {
    return this.jDialoogSchedule;
  }
  
  public void setjDialoogSchedule(NewJDialogSchedule jDialoogSchedule)
  {
    this.jDialoogSchedule = jDialoogSchedule;
  }
  
  public JLabel getjLabel1()
  {
    return this.jLabel1;
  }
  
  public void setjLabel1(JLabel jLabel1)
  {
    this.jLabel1 = jLabel1;
  }
  
  public JLabel getjLabel2()
  {
    return this.jLabel2;
  }
  
  public void setjLabel2(JLabel jLabel2)
  {
    this.jLabel2 = jLabel2;
  }
  
  public JLabel getjLabel3()
  {
    return this.jLabel3;
  }
  
  public void setjLabel3(JLabel jLabel3)
  {
    this.jLabel3 = jLabel3;
  }
  
  public JLabel getjLabel4()
  {
    return this.jLabel4;
  }
  
  public void setjLabel4(JLabel jLabel4)
  {
    this.jLabel4 = jLabel4;
  }
  
  public JLabel getjLabelInclineValue()
  {
    return this.jLabelInclineValue;
  }
  
  public void setjLabelInclineValue(JLabel jLabelInclineValue)
  {
    this.jLabelInclineValue = jLabelInclineValue;
  }
  
  public JLabel getjLabelJoggerValue()
  {
    return this.jLabelJoggerValue;
  }
  
  public void setjLabelJoggerValue(JLabel jLabelJoggerValue)
  {
    this.jLabelJoggerValue = jLabelJoggerValue;
  }
  
  public JLabel getjLabelRunnerValue()
  {
    return this.jLabelRunnerValue;
  }
  
  public void setjLabelRunnerValue(JLabel jLabelRunnerValue)
  {
    this.jLabelRunnerValue = jLabelRunnerValue;
  }
  
  public JLabel getjLabelWalkerValue()
  {
    return this.jLabelWalkerValue;
  }
  
  public void setjLabelWalkerValue(JLabel jLabelWalkerValue)
  {
    this.jLabelWalkerValue = jLabelWalkerValue;
  }
  
  public JMenu getjMenu1()
  {
    return this.jMenu1;
  }
  
  public void setjMenu1(JMenu jMenu1)
  {
    this.jMenu1 = jMenu1;
  }
  
  public JMenu getjMenu2()
  {
    return this.jMenu2;
  }
  
  public void setjMenu2(JMenu jMenu2)
  {
    this.jMenu2 = jMenu2;
  }
  
  public JMenuBar getjMenuBar1()
  {
    return this.jMenuBar1;
  }
  
  public void setjMenuBar1(JMenuBar jMenuBar1)
  {
    this.jMenuBar1 = jMenuBar1;
  }
  
  public JMenuItem getjMenuItemPlayVideo()
  {
    return this.jMenuItemPlayVideo;
  }
  
  public void setjMenuItemPlayVideo(JMenuItem jMenuItemPlayVideo)
  {
    this.jMenuItemPlayVideo = jMenuItemPlayVideo;
  }
  
  public JMenuItem getjMenuItemaSchedule()
  {
    return this.jMenuItemaSchedule;
  }
  
  public void setjMenuItemaSchedule(JMenuItem jMenuItemaSchedule)
  {
    this.jMenuItemaSchedule = jMenuItemaSchedule;
  }
  
  public JPanel getjPanelContainer()
  {
    return this.jPanelContainer;
  }
  
  public void setjPanelContainer(JPanel jPanelContainer)
  {
    this.jPanelContainer = jPanelContainer;
  }
  
  public JPanel getjPanelIncline()
  {
    return this.jPanelIncline;
  }
  
  public void setjPanelIncline(JPanel jPanelIncline)
  {
    this.jPanelIncline = jPanelIncline;
  }
  
  public JPanel getjPanelJogger()
  {
    return this.jPanelJogger;
  }
  
  public void setjPanelJogger(JPanel jPanelJogger)
  {
    this.jPanelJogger = jPanelJogger;
  }
  
  public JPanel getjPanelRunner()
  {
    return this.jPanelRunner;
  }
  
  public void setjPanelRunner(JPanel jPanelRunner)
  {
    this.jPanelRunner = jPanelRunner;
  }
  
  public JPanel getjPanelWalker()
  {
    return this.jPanelWalker;
  }
  
  public void setjPanelWalker(JPanel jPanelWalker)
  {
    this.jPanelWalker = jPanelWalker;
  }
  
  public JLabel getjLabelCountdownValue()
  {
    return this.jLabelCountdownValue;
  }
  
  public void setjLabelCountdownValue(JLabel jLabelCountdownValue)
  {
    this.jLabelCountdownValue = jLabelCountdownValue;
  }
  
  private void populateProgressBar()
  {
    ActionListener listener = new ActionListener()
    {
      int counter = 0;
      
      public void actionPerformed(ActionEvent ae)
      {
        this.counter += 1;
        VideoFrame.this.jProgressBarTotal.setValue(this.counter);
      }
    };
    this.mediaPlayerComponent.getMediaPlayer().parseMedia();
    long duration = this.mediaPlayerComponent.getMediaPlayer().getLength();
    while (duration <= 0L)
    {
      this.mediaPlayerComponent.getMediaPlayer().parseMedia();
      duration = this.mediaPlayerComponent.getMediaPlayer().getLength();
    }
    System.out.println("Duration = " + duration);
    Timer timer = new Timer((int)(duration / 100L), listener);
    timer.start();
  }
  
  private class TutorialRenderCallbackAdapter
    extends RenderCallbackAdapter
  {
    private TutorialRenderCallbackAdapter()
    {
      super(new int[width * height]);
    }
    
    protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer)
    {
      VideoFrame.this.image.setRGB(0, 0, VideoFrame.this.width, VideoFrame.this.height, rgbBuffer, 0, VideoFrame.this.width);
      VideoFrame.this.videoSurface.repaint();
    }
  }
}
