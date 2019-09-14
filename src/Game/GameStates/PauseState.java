package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

import com.sun.javafx.geom.Point2D;

import Display.BackgroundGradient;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

    private int count = 0;
    private UIManager uiManager;

    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);

        uiManager.addObjects(new UIImageButton(52, 185, 150, 80, Images.Resume, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
        }));

        uiManager.addObjects(new UIImageButton(52, (185+78), 100, 80, Images.BTitle, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }


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
    	
        g.drawImage(Images.Pause,0,0,780,390,null);
        uiManager.Render(g);

    }
}
