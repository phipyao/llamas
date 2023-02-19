import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.Timer;

public class Display extends JPanel
{
   private Entity pen = new Entity();
   private BufferedImage[] background = pen.drawBackground();
   private BufferedImage[] playerR = pen.drawPlayerR();
   private BufferedImage[] player = pen.drawPlayer();
   private BufferedImage[] playerL = pen.drawPlayerL();
   private BufferedImage[] enemy1 = pen.drawEnemy1();   
   private Rectangle2D.Double[] hitbox;
   private Rectangle2D.Double playerHitbox;
   private int[] x, y, yInc, fps, delay;
   private double[] inc;
   private int spriteSheets, xPlayer, yPlayer, numSprites, score, highScore;
   private double speed;
   private boolean start, goRight, goLeft, life;
      
   public Display(int maxEnemies)
   {
      start = false;
      numSprites = maxEnemies;
      highScore = 0;
      startGame();
      addKeyListener(new Keyboard());
      Timer t = new Timer(10, new Clock());
      t.start();
   }
   
   private class Keyboard implements KeyListener
   {     
      public void keyPressed(KeyEvent e)
      {
         int key = e.getKeyCode();
         switch(key)
         {
            case KeyEvent.VK_RIGHT:
               goRight = true;
               break;
            case KeyEvent.VK_LEFT:
               goLeft = true;
               break;
            case KeyEvent.VK_SPACE:
                 
            case KeyEvent.VK_ENTER:
               start = true;
               startGame();
         }
         repaint();
      }      
      public void keyReleased(KeyEvent e)
      {
         int key = e.getKeyCode();
         switch(key)
         {
            case KeyEvent.VK_RIGHT:
               goRight = false;
               break;
            case KeyEvent.VK_LEFT:
               goLeft = false;
               break;  
         }
         repaint();
      }      
      public void keyTyped(KeyEvent e)
      {   	
      } 
   }
	
   private class Clock implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(goRight && xPlayer < 892)
            xPlayer += 2;
         if(goLeft && xPlayer > 0)
            xPlayer -= 2;
         if(life)
         {
            score++;
            speed += 0.002;
            inc[0] += 0.2;
            inc[1] += 0.2;
            fps[0] = (int)(inc[0] % 6);
            fps[1] = (int)(inc[1] % 12);
            for(int i = 0; i < numSprites; i++)
            {            
               y[i] = (int)(speed * inc[1] + 540) % delay[i]; 
               hitbox[i] = new Rectangle2D.Double(x[i] + 17, y[i] + 17, 31, 29);
               playerHitbox = new Rectangle2D.Double(xPlayer + 17, yPlayer + 17, 31, 29);
               if(playerHitbox.intersects(hitbox[i].getBounds2D()))
               {
                  life = false;
               }
            }
            repaint();
         }
      }
   }
   
   private void startGame()
   {
      life = true;
      score = 0;
      speed = 5;
      spriteSheets = 2;
      xPlayer = 217;
      yPlayer = 425;
      playerHitbox = new Rectangle2D.Double(xPlayer + 17, yPlayer + 42, 31, 54);
      hitbox = new Rectangle2D.Double[numSprites];
      x = new int[numSprites];
      y = new int[numSprites];
      yInc = new int[numSprites];      
      delay = new int[numSprites];
      fps = new int[numSprites];
      inc = new double[spriteSheets];
      for(int i = 0; i < spriteSheets; i++)
      {
         fps[i] = 0;
         inc[i] = 0;
      }
      for(int i = 0; i < numSprites; i++)
      {         
         x[i] = (i * 48);
         y[i] = 0;
         delay[i] = (int)(Math.random() * numSprites * 100 + 500);
         hitbox[i] = new Rectangle2D.Double(x[i] - 16, y[i] - 16, 32, 32);         
      }
   }
   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      super.paintComponent(g2);
      if(score>3000)
         g2.drawImage(background[2], 0, 0, null);
      else if(score>1500)
         g2.drawImage(background[1], 0, 0, null);
      else
         g2.drawImage(background[0], 0, 0, null);
   
      g2.setColor(Color.white);
      if(start)
      {  
         if(life)
         {
            g2.drawString("Score: " + score, 0, 10);
            if(goRight)
               g2.drawImage(playerR[fps[0]], xPlayer, yPlayer, null);
            else if(goLeft)
               g2.drawImage(playerL[fps[0]], xPlayer, yPlayer, null);
            else
               g2.drawImage(player[fps[0]], xPlayer, yPlayer, null);
         
            for(int i = 0; i < numSprites; i++)
            {
               g2.drawImage(enemy1[fps[1]], x[i], y[i], null);
            }
         }
         else
         {
            if(highScore < score)
               highScore = score;
            g2.setFont(new Font("Serif", Font.BOLD, 50)); 
            g2.drawString("You Lost!" , 300, 125);
            g2.drawString("Your Score: " + score, 300, 200);
            g2.drawString("High Score: " + highScore, 300, 275);
            g2.drawString("'Enter' to Restart", 300, 350);
         }
      }
      else
      {
         g2.setFont(new Font("Serif", Font.BOLD, 50)); 
         g2.drawString("'L/R' Keys to Move" , 300, 125);
         g2.drawString("'Enter' to Begin" , 300, 200);
         g2.setFont(new Font("Serif", Font.ITALIC, 100));
         g2.drawString("Don't Get Hit", 220, 340);
      }
   }
}