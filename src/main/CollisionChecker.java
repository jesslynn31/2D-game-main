package main;

import NPC.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {


        this.gp = gp; 


    }
   
        public void checkTile(Entity NPC) {
            
            int entityLeftWorldX = NPC.worldx + NPC.solidArea.x;
            int entityRightWorldX = NPC.worldx + NPC.solidArea.x + NPC.solidArea.width;
            int entityTopWorldY = NPC.worldy + NPC.solidArea.y;
            int entityBottomWorldY = NPC.worldy + NPC.solidArea.y + NPC.solidArea.height;
        
            int entityLeftCol = entityLeftWorldX / gp.tileSize;
            int entityRightCol = entityRightWorldX / gp.tileSize;
            int entityTopRow = entityTopWorldY / gp.tileSize;
            int entityBottomRow = entityBottomWorldY / gp.tileSize;
    
        int tileNum1, tileNum2;
    
        entityLeftCol = Math.max(0, Math.min(entityLeftCol, gp.maxWorldCol - 1));
        entityRightCol = Math.max(0, Math.min(entityRightCol, gp.maxWorldCol - 1));
        entityTopRow = Math.max(0, Math.min(entityTopRow, gp.maxWorldRow - 1));
        entityBottomRow = Math.max(0, Math.min(entityBottomRow, gp.maxWorldRow - 1));
    
        
        switch (NPC.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - NPC.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    NPC.CollisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + NPC.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    NPC.CollisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - NPC.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    NPC.CollisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + NPC.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    NPC.CollisionOn = true;
                }
                break;
            }
        }
    



    public int checkObject(Entity entity, boolean player) {
        int index = 9999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                entity.solidArea.x = entity.worldx + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldy + entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidAreaDefaultY;
                
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.CollisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.CollisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.CollisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.CollisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}

        