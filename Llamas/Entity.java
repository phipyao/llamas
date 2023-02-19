import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Entity
{
   private int width, height, amtSprites;
   
   public BufferedImage[] drawPlayerR()
   {
      BufferedImage[] sprites = null;
      try
      {
         BufferedImage playerSpriteSheetR = ImageIO.read(new File("Llama.png"));
         width = 64;
         height = 64;
         amtSprites = 6;
         sprites = new BufferedImage[amtSprites];
         for(int i = 0; i < amtSprites; i++)
         {
            sprites[i] = playerSpriteSheetR.getSubimage(i * width, 0, width, height);			    
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return sprites;
   }

   public BufferedImage[] drawPlayer()
   {
      BufferedImage[] sprites = null;
      try
      {
         BufferedImage playerSpriteSheetL = ImageIO.read(new File("Llama.png"));
         width = 64;
         height = 64;
         amtSprites = 6;
         sprites = new BufferedImage[amtSprites];
         for(int i = 0; i < amtSprites; i++)
         {
            sprites[i] = playerSpriteSheetL.getSubimage(i * width + 384, 0, width, height);			    
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return sprites;
   }
   
   public BufferedImage[] drawPlayerL()
   {
      BufferedImage[] sprites = null;
      try
      {
         BufferedImage playerSpriteSheetL = ImageIO.read(new File("Llama.png"));
         width = 64;
         height = 64;
         amtSprites = 6;
         sprites = new BufferedImage[amtSprites];
         for(int i = 0; i < amtSprites; i++)
         {
            sprites[i] = playerSpriteSheetL.getSubimage(i * width + 768, 0, width, height);			    
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return sprites;
   }
   
   public BufferedImage[] drawEnemy1()
   {
      BufferedImage[] sprites = null;
      try
      {
         BufferedImage enemySpriteSheet = ImageIO.read(new File("Crab Minion.png"));
         width = 64;
         height = 64;
         amtSprites = 12;
         sprites = new BufferedImage[amtSprites];
         for(int i = 0; i < amtSprites; i++)
         {
            sprites[i] = enemySpriteSheet.getSubimage(i * width, 0, width, height);			    
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return sprites;
   }

   public BufferedImage[] drawBackground()
   {
      BufferedImage[] sprites = null;
      try
      {
         BufferedImage backgroundSpriteSheet = ImageIO.read(new File("Background.png"));
         width = 960;
         height = 540;
         amtSprites = 3;
         sprites = new BufferedImage[amtSprites];
         for(int i = 0; i < amtSprites; i++)
         {
            sprites[i] = backgroundSpriteSheet.getSubimage(i * width, 0, width, height);			    
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return sprites;
   }
}