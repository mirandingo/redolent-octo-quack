import java.awt.*;
import javax.swing.*;

public class PacMine extends JFrame {
  private int duration;
  public PacMine(int d) {
    duration = d;
  }

 
  public void showSplash() {
    JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.white);

    
    int width = 511;
    int height = 739;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = 0;
    int y = 0;
    setBounds(x,y,width,height);


    JLabel label = new JLabel(new ImageIcon("splash.png"));
    
    content.add(label, BorderLayout.CENTER);
    


    setVisible(true);

    
   
  }

  public void showSplashAndExit() {
    showSplash();
    try{
      Thread.sleep(this.duration);
    }
    catch(InterruptedException ex){
      System.out.println(ex);
    }
    this.setVisible(false);
  }

  public static void main(String[] args) {
    
    PacMine splash = new PacMine(2500);
    
    splash.showSplashAndExit();
    new PacMan();
  }
}
