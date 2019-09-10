package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage title;
    public static BufferedImage settings;
    public static BufferedImage Pause;
    public static BufferedImage GameOver;
    public static BufferedImage[] Resume;
    public static BufferedImage[] BTitle;
    public static BufferedImage[] BSettings;
    public static BufferedImage[] True;
    public static BufferedImage[] False;
    public static BufferedImage[] Exit;
    public static ImageIcon icon;

    public Images() {

        butstart = new BufferedImage[3];
        Resume = new BufferedImage[2];
        BTitle = new BufferedImage[2];
        Exit = new BufferedImage[2];
        True = new BufferedImage[2];
        False = new BufferedImage[2];
        BSettings = new BufferedImage[3];

        try {

            title = ImageIO.read(getClass().getResourceAsStream("/Sheets/Title.png"));
            settings = ImageIO.read(getClass().getResourceAsStream("/Sheets/Settings.png")); //added settings background
            Pause = ImageIO.read(getClass().getResourceAsStream("/Buttons/Pause.png"));
            GameOver = ImageIO.read(getClass().getResourceAsStream("/Buttons/GameOver.png")); //added gameover background
            
            
            Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Resume.png"));
            Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeP.png"));
            
            BTitle[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));
            BTitle[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            
            //For new Exit button
            Exit[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Exit.png"));
            Exit[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ExitP.png"));

            //For new True button
            True[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/boolean/True.png"));
            True[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/boolean/TrueGP.png"));
            
            //For new False button
            False[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/boolean/False.png"));
            False[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/boolean/FalseRP.png"));
            
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));		//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));		//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));	//clickbut
            
            // for new settings button
            BSettings[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/BSettings.png"));	//Normal Button
            BSettings[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/BSettingsH.png"));	//Button on Hover
            BSettings[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/BSettingsP.png"));	//Pressed Button

            icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/icon.png")));


        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
