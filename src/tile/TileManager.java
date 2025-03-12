package tile;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;



import javax.imageio.ImageIO;


import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class TileManager {

    GamePanel gp;
    KeyHandler keyH;
    public Tile[] tile;
    public int mapTileNum[][];

 public TileManager(GamePanel gp){
     
     this.gp = gp;
     this.keyH = gp.keyH;

    tile = new Tile[10];
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];


    getTileImage();
    loadMap("./res/maps/world01.txt");
    
}


public void getTileImage(){
// me when learning how to implement map parts
    try {

        tile[0] = new Tile();
        tile[0].image = ImageIO.read(new File("./res/player/images/tiles/grass.png"));

        tile[1] = new Tile();
        tile[1].image = ImageIO.read(new File("./res/player/images/tiles/wall.png"));
        tile[1].collision = true;

        tile[2] = new Tile();
        tile[2].image = ImageIO.read(new File("./res/player/images/tiles/water.png"));
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].image = ImageIO.read(new File("./res/player/images/tiles/earth.png"));

        tile[4] = new Tile();
        tile[4].image = ImageIO.read(new File("./res/player/images/tiles/tree.png"));
        tile[4].collision = true; 

        tile[5] = new Tile();
        tile[5].image = ImageIO.read(new File("./res/player/images/tiles/sand.png"));

        }catch(IOException e){

        e.printStackTrace();
        }



        }   

        public void draw(Graphics2D g2) {
            int worldCol = 0;
            int worldRow = 0;
        
            boolean followPlayer = gp.keyH.followPlayer; 
        
            int offsetX, offsetY;
        
            if (followPlayer) {
                offsetX = gp.player.worldx - (gp.screenWidth / 2);
                offsetY = gp.player.worldy - (gp.screenHeight / 2);
            } else {
    
                int edgeThreshold = 100; 
                offsetX = gp.cameraOffsetX; 
                offsetY = gp.cameraOffsetY;
        
               
                int playerScreenX = gp.player.worldx - offsetX;
                if (playerScreenX < edgeThreshold) {
                    offsetX = gp.player.worldx - edgeThreshold;
                } else if (playerScreenX > gp.screenWidth - edgeThreshold) {
                    offsetX = gp.player.worldx - (gp.screenWidth - edgeThreshold);
                }
        
          
                int playerScreenY = gp.player.worldy - offsetY;
                if (playerScreenY < edgeThreshold) {
                    offsetY = gp.player.worldy - edgeThreshold;
                } else if (playerScreenY > gp.screenHeight - edgeThreshold) {
                    offsetY = gp.player.worldy - (gp.screenHeight - edgeThreshold);
                }
            }
        
           
            offsetX = Math.max(0, Math.min(offsetX, gp.maxWorldCol * gp.tileSize - gp.screenWidth));
            offsetY = Math.max(0, Math.min(offsetY, gp.maxWorldRow * gp.tileSize - gp.screenHeight));
        
            gp.cameraOffsetX = offsetX;
            gp.cameraOffsetY = offsetY;

            while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
                int tileNum = mapTileNum[worldCol][worldRow];
        
                if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null) {
                    worldCol++;
                    if (worldCol == gp.maxWorldCol) {
                        worldCol = 0;
                        worldRow++;
                    }
                    continue;
                }
        
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
        
                int screenX = worldX - offsetX;
                int screenY = worldY - offsetY;
        
                if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
                    screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
        
                    if (tile[tileNum].image == null) {
                        System.out.println("Error: Tile image is broken for tile " + tileNum);
                    } else {
                        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                }
        
                worldCol++;
        
                if (worldCol == gp.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
            }
        }
        
        
        
        

public void loadMap(String FilePath){
    File file = new File(FilePath);
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        int col = 0;
        int row = 0;
      
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            String line = br.readLine();

            while(col < gp.maxWorldCol) {
                String[] numbers = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;

                col++;
            }

            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

        br.close();
         
    } catch (IOException e) {
        e.printStackTrace();
        }
    
    }
}

    


