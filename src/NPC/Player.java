package NPC;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler KeyH;
    int hasKey = 0;

    public final int screenX; 
    public final int screenY; 

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.KeyH = keyH;
    

        screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
        screenY = (gp.screenWidth / 2) - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldx = gp.tileSize * 23;
        worldy = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        
    }
    public void update(){

        if(KeyH.upPressed == true || KeyH.downPressed == true ||
        KeyH.leftPressed == true || KeyH.rightPressed == true){

        if(KeyH.upPressed == true){
            direction = "up";
        
        }
        
        else if(KeyH.downPressed == true){
            direction = "down";
          
        }
        else if(KeyH.leftPressed == true){
            direction = "left";
          
        }
        else if(KeyH.rightPressed == true){
            direction = "right";
         
        }
        
        //checking collision
        CollisionOn = false;
        gp.cChecker.checkTile(this);

        //check object collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        if(CollisionOn == false){
            switch(direction){
                case "up":
                worldy -= speed;
                break;
                case "down":
                worldy += speed;
                break;
                case "left":
                worldx -= speed;
                break;
                case "right":
                worldx += speed;
                break;
            }
        }

        spriteCounter++;
    if (spriteCounter > 12) {  
    spriteNum = (spriteNum == 1) ? 2 : 1;
    spriteCounter = 0;
}
}
    }

    public void pickUpObject(int i){

        if(i != 9999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key:" + hasKey);
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;

            }
        }

    }

    public void draw(Graphics2D g2){
    
       // g2.setColor(Color.white);
                     // player position     width  height
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    
        BufferedImage image = null;

        switch(direction){
        case "up":
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
                }
                break;
        case "down":
            if(spriteNum == 1){
                image = down1;
                
            }
            if(spriteNum == 2){
                image = down2;
                
            }
            break;
        case "left":
            if(spriteNum == 1){
                image = left1; 
                
            }
            if(spriteNum == 2){
                image = left2;
            }
            break;
        case "right":
            if(spriteNum == 1){
                image = right1; 
                
            }
            if(spriteNum == 2){
                image = right2;
            }
            break;
        }
        g2.drawImage(image,screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


    

    public void getPlayerImage(){

        try {
            
            up1 = ImageIO.read(new File("./res/player/images/boy_up_1.png"));
            up2 = ImageIO.read(new File("./res/player/images/boy_up_2.png"));
            down1 = ImageIO.read(new File("./res/player/images/boy_down_1.png"));
            down2 = ImageIO.read(new File("./res/player/images/boy_down_2.png"));
            left1 = ImageIO.read(new File("./res/player/images/boy_left_1.png"));
            left2 = ImageIO.read(new File("./res/player/images/boy_left_2.png"));;
            right1 = ImageIO.read(new File("./res/player/images/boy_right_1.png"));
            right2 = ImageIO.read(new File("./res/player/images/boy_right_2.png"));
            


        }catch (IOException e){
            e.printStackTrace();
        }
        }
    }

