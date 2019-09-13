package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameOverState extends State {

    private int count = 0;
    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        
        //Return To title button
        uiManager.addObjects(new UIImageButton(276 + 130, handler.getHeight()/2 + (handler.getHeight()/3 + 40), 100, 80, Images.BTitle, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));

        //Restart Game button
        uiManager.addObjects(new UIImageButton(276, handler.getHeight()/2 + (handler.getHeight()/3 + 40), 135, 80, Images.Restart, () -> {
            handler.getMouseManager().setUimanager(null);
            handler.getGame().reStart();
            State.setState(handler.getGame().gameState);
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
        g.drawImage(Images.GameOver,0,0,780,780,null);
        uiManager.Render(g);

    }
}
