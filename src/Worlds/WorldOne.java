package Worlds;

import Game.Entities.Static.Apple;
import Game.Entities.Static.Pear;
import Main.Handler;

import java.awt.*;
import java.util.Random;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class WorldOne extends WorldBase{

    public WorldOne (Handler handler) {
        super(handler);

        //has to be a number bigger than 20 and even
        GridWidthHeightPixelCount = 60;
        GridPixelsize = (780/GridWidthHeightPixelCount);
        playerLocation = new Boolean[GridWidthHeightPixelCount][GridWidthHeightPixelCount];
        appleLocation = new Boolean[GridWidthHeightPixelCount][GridWidthHeightPixelCount];
        pearLocation = new Boolean[GridWidthHeightPixelCount][GridWidthHeightPixelCount];

    }

    @Override
    public void tick() {
        super.tick();
        player.tick();
        if(!appleOnBoard){
            appleOnBoard=true;
            int appleX = new Random().nextInt(handler.getWorld().GridWidthHeightPixelCount-1);
            int appley = new Random().nextInt(handler.getWorld().GridWidthHeightPixelCount-1);

            //change coordinates till one is selected in which the player isnt standing
            boolean goodCoordinates=false;
            do{
                if(!handler.getWorld().playerLocation[appleX][appley]){
                    goodCoordinates=true;
                }
            }while(!goodCoordinates);

            apple = new Apple(handler,appleX,appley);
            appleLocation[appleX][appley]=true;

        }
        if(!pearOnBoard && handler.getWorld().player.score >= 20){
            pearOnBoard=true;
            int pearX = new Random().nextInt(handler.getWorld().GridWidthHeightPixelCount-1);
            int pearY = new Random().nextInt(handler.getWorld().GridWidthHeightPixelCount-1);

            //change coordinates till one is selected in which the player isnt standing
            boolean goodCoordinates=false;
            do{
                if(!handler.getWorld().playerLocation[pearX][pearY]){
                    goodCoordinates=true;
                }
            }while(!goodCoordinates);

            pear = new Pear(handler, pearX, pearY);
            pearLocation[pearX][pearY]=true;
    }
    }

    @Override
    public void render(Graphics g){
        super.render(g);
        player.render(g,playerLocation);
    }

}
