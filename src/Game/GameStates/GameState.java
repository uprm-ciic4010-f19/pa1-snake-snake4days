package Game.GameStates;

import Game.Entities.Dynamic.Player;
import Main.Handler;
import Worlds.WorldBase;
import Worlds.WorldOne;

import java.awt.*;

import com.sun.javafx.geom.Point2D;

import Display.BackgroundGradient;


/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {

    private WorldBase world;

    public GameState(Handler handler){
        super(handler);
        world = new WorldOne(handler);
        handler.setWorld(world);
        handler.getWorld().player= new Player(handler);
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;
                handler.getWorld().appleLocation[i][j]=false;
                handler.getWorld().pearLocation[i][j]=false;

            }
        }
        handler.getWorld().playerLocation[handler.getWorld().player.xCoord][handler.getWorld().player.yCoord] =true;


    }

    @Override
    public void tick() {

        handler.getWorld().tick();

    }

    @Override
    public void render(Graphics g) {
    	
    	//Adds new background gradient
    	BackgroundGradient gradient = new BackgroundGradient();
    	//Gradient colors desired Starting Points
    	Point2D BottomLeft = new Point2D(0, 780);
		Point2D TopRight = new Point2D(780, 0);
    	//paints/renders the gradient
    	gradient.paint(g, BottomLeft, new Color(255, 240, 241), TopRight, new Color(84, 81, 87));
    	
        handler.getWorld().render(g);

    }

}
