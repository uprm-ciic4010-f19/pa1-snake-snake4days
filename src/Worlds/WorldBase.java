package Worlds;

import Game.Entities.Dynamic.Player;
import Game.Entities.Dynamic.Tail;
import Game.Entities.Static.Apple;
import Game.Entities.Static.Pear;

import Main.Handler;

import java.awt.*;
import java.util.LinkedList;


/**
 * Created by AlexVR on 7/2/2018.
 */
public abstract class WorldBase {

    //How many pixels are from left to right
    //How many pixels are from top to bottom
    //Must be equal
    public int GridWidthHeightPixelCount;

    //automatically calculated, depends on previous input.
    //The size of each box, the size of each box will be GridPixelsize x GridPixelsize.
    public int GridPixelsize;

    public Player player;

    protected Handler handler;


    public Boolean appleOnBoard;
    protected Apple apple;
    public Boolean[][] appleLocation;
    
    public Pear pear;
    public Boolean[][] pearLocation;
    public Boolean pearOnBoard;


    public Boolean[][] playerLocation;

    public LinkedList<Tail> body = new LinkedList<>();


    public WorldBase(Handler handler){
        this.handler = handler;

        appleOnBoard = false;
        pearOnBoard = false;


    }
    public void tick(){



    }

    public void render(Graphics g){

    	//grid no longer needed so it wont be rendered
    	//for (int i = 0; i <= 780; i = i + GridPixelsize) {

    	//	Color transparent = new Color(0 , 0, 0, 0); //creates new transparent color instance in sRGB
    	//    g.setColor(transparent); //sets color to transparent
    	//    g.drawLine(0, i, handler.getWidth() , i);
    	//    g.drawLine(i,0,i,handler.getHeight());
    	//}

    	//score board
    	g.setFont(new Font("Futura LT", Font.BOLD, 20));
    	g.setColor(Color.BLACK);
    	g.drawString("Score: " + (int) player.score, 630, 40);


    }

}
