package Game.GameStates;


import Main.GameSetUp;
import Main.Handler;
import Main.Launch;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

import com.sun.media.jfxmedia.AudioClip;

public class SettingsState extends State {

    private UIManager uiManager;

    public SettingsState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);        
        
        //Snake Color Booleans
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2 + 5, handler.getHeight()/2 - handler.getHeight()/3 + 33, 103, 81, Images.True, () -> {
            handler.getMouseManager().setUimanager(null);
            Launch.setSolidColor(true);
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2 + 117 + 23, handler.getHeight()/2 - handler.getHeight()/3 + 33, 117, 81, Images.False, () -> {
            handler.getMouseManager().setUimanager(null);
            Launch.setSolidColor(false);
        }));
        
        //Difficulty Booleans
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2 - 43, handler.getHeight()/2 - handler.getHeight()/5 + 12, 103, 81, Images.True, () -> {
            handler.getMouseManager().setUimanager(null);
            Launch.setHard(true);
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2 + 117 - 23, handler.getHeight()/2 - handler.getHeight()/5 + 12, 117, 81, Images.False, () -> {
            handler.getMouseManager().setUimanager(null);
            Launch.setHard(false);
        }));
        
        //Music booleans
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2 - 120, handler.getHeight()/2 - handler.getHeight()/7 + 43, 103, 81, Images.True, () -> {
            handler.getMouseManager().setUimanager(null);
            GameSetUp.getAudioClip().start(); //start music
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2 - 120 + 117 + 23, handler.getHeight()/2 - handler.getHeight()/7 + 43, 117, 81, Images.False, () -> {
            handler.getMouseManager().setUimanager(null);
            GameSetUp.getAudioClip().stop(); //stop music
        }));
        
        //Exit Button
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2 - handler.getWidth()/2 + 65, handler.getHeight()/2 + handler.getHeight()/3, 100, 80, Images.Exit, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));
        
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.settings,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);

    }


}
