package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Door extends SuperObject {



    public Obj_Door(){
        name = "Door";
        try{

        image = ImageIO.read(new File("./res/objects/Object/door.png"));
        
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true; 
    }
}

    

