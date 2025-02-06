package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Key extends SuperObject {
    
    public Obj_Key(){
        name = "Key";
        try{

        image = ImageIO.read(new File("./res/objects/Object/key.png"));
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
