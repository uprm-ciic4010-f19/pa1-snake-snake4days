package Game.Entities.Static;

import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Pear {

    private Handler handler;

    public int xCoord;
    public int yCoord;
    public static boolean appleState = true;

    public Pear(Handler handler,int x, int y){
        this.handler=handler;
        this.xCoord=x;
        this.yCoord=y;
    }

}