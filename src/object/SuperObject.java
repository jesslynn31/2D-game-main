package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0; 
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        
        int screenX = worldX - gp.cameraOffsetX;
        int screenY = worldY - gp.cameraOffsetY;
     
        if(worldX + gp.tileSize > gp.cameraOffsetX && 
           worldX - gp.tileSize < gp.cameraOffsetX + gp.screenWidth &&
           worldY + gp.tileSize > gp.cameraOffsetY && 
           worldY - gp.tileSize < gp.cameraOffsetY + gp.screenHeight) {
            
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}