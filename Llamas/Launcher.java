import java.awt.*;
import javax.swing.*;
import java.io.File;

public class Launcher extends JFrame
{
   private Display centerPanel;
   
   public Launcher()
   {   
      Container cp = getContentPane();
      centerPanel = new Display(19);
      centerPanel.setFocusable(true);
      centerPanel.requestFocus();         
      cp.setLayout(new BorderLayout());
      cp.add(centerPanel, BorderLayout.CENTER);  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      
      setTitle("Llamas");
      setSize(960, 540);
      
      setLocationRelativeTo(null);
      setVisible(true);
   }	
   
   public static void main(String[] args) 
   {
      File file = new File("OpeningMusic.WAV");
      SoundLoader sound = new SoundLoader();
      sound.play("OpeningMusic.WAV");
      
      
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            @Override
            public void run()
            {						
               new Launcher();
            }
         });
   }
}