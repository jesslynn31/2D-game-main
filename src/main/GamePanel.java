package main;

import javax.swing.JPanel;

import NPC.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16; // 16x16 titles (default size
    final int scale = 3; // gonna look 48x48
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; 
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //world settings

    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50; 
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow; 


    KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public 
    
    
   
    int FPS = 60;

    public TileManager tileM = new TileManager(this);

    

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){

        aSetter.setObject();
      
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval; 


        // FPS !!!! 
        while(gameThread != null){
           
            // Long currentTime = System.currentTimeMillis();

            
            //System.out.println("The game loop is running");
            // 1. update: update info such as character positions

            update();

            // 2. DRAW: draw the screen with the updated information

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        

    }

    public void update(){
       player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // must use super bcuz parent
        //tile
        tileM.draw(g2);

        //object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        
        
        //player
        player.draw(g2);
        player.update();

        
        g2.dispose();

    }
    
}
