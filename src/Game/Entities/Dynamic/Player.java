package Game.Entities.Dynamic;

import Main.Handler;
import Main.Launch;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.Entities.Static.Apple;
import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

	public int lenght;
	public boolean justAte;
	private Handler handler;

	public int xCoord;
	public int yCoord;

	public int moveCounter;

	public int speedBoundary; //moveCounter boundary variable for debugging

	public double score;

	public double steps; //counts the steps the snake made

	public String direction; //is your first name one?
	
	public static Color appleColor = Color.RED;

	public Player(Handler handler){    	
		this.handler = handler;
		xCoord = 0;
		yCoord = 0;
		moveCounter = 0;
		speedBoundary = 5; //initial speed
		direction= "Right";
		justAte = false;
		lenght= 1;
		score = 0;
		steps = 0;

	}

	public void tick(){
		
		//if settings set to hard
		if (Launch.isHard()) {
			speedBoundary = 1; //changes speed boundary and snakes goes FAST
		}
		
		moveCounter++;
		if(moveCounter>=speedBoundary) {
			checkCollisionAndMove();
			moveCounter=1;
			steps++;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && (direction != "Down")){
			direction="Up";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && (direction != "Up")){
			direction="Down";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && (direction != "Right")){
			direction="Left";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && (direction != "Left")){
			direction="Right";
		}
		// adds tail at the end of the snake when you press N
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
			lenght ++;
			handler.getWorld().body.addLast(new Tail(xCoord, yCoord, handler));
		}

		//Snake goes faster when "=" aka "+" is pressed
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)) {
			speedBoundary--;
		}

		//Snake goes slower when "-" is pressed
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {
			speedBoundary++;
		}

		//Escape key pauses game
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setState(handler.getGame().pauseState); 
		}

		//G key calls Game over for debugging
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_G)) {
			State.setState(handler.getGame().gameOverState); 
		}
		checkApple();

	}

	public void checkCollisionAndMove(){
		handler.getWorld().playerLocation[xCoord][yCoord]=false;
		int x = xCoord;
		int y = yCoord;
		switch (direction){
		case "Left":
			if(xCoord==0){
				xCoord = handler.getWorld().GridWidthHeightPixelCount - 1;
			}else{
				xCoord--;
			}
			break;
		case "Right":
			if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				xCoord = 0;
			}else{
				xCoord++;
			}
			break;
		case "Up":
			if(yCoord==0){
				yCoord = handler.getWorld().GridWidthHeightPixelCount - 1;
			}else{
				yCoord--;
			}
			break;
		case "Down":
			if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				yCoord = 0;
			}else{
				yCoord++;
			}
			break;
		}

		handler.getWorld().playerLocation[xCoord][yCoord]=true;


		if(handler.getWorld().appleLocation[xCoord][yCoord] && Apple.isGood()){
			Eat();
			this.setJustAte(true);
			score += Math.sqrt(2 * score + 1); // adds score when snake eats apple
			speedBoundary -= 0 + 1; //increases speed each time the snake eats
		}
		if(handler.getWorld().appleLocation[xCoord][yCoord] && !Apple.isGood()){
			Eat();
			this.setJustAte(true);
			score -= Math.sqrt(2*score + 1);
			handler.getWorld().body.removeLast();
		}


		if(!handler.getWorld().body.isEmpty()) {
			handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
			handler.getWorld().body.removeLast();
			handler.getWorld().body.addFirst(new Tail(x, y, handler));
		}

		if (lenght > 1) {
			for (int i = 0; i < handler.getWorld().body.size(); i++) {
				if (handler.getWorld().body.get(i).x == xCoord && handler.getWorld().body.get(i).y == yCoord) {
					kill();
					State.setState(handler.getGame().gameOverState);
				}
			}

		}


	}

	public void checkApple() {
		if(steps > 400) {
			Apple.setGood(false);
			setAppleColor(new Color(139,69,19));
		}
		if(handler.getWorld().player.justAte) { // resets the steps 
			steps = 0;
			setJustAte(false);
			setAppleColor(Color.RED);
		}

	}
	

	public void render(Graphics g,Boolean[][] playeLocation){
		
		//random colors generator
		Random r = new Random();
		int R = r.nextInt(256);
		int G = r.nextInt(256);
		int B = r.nextInt(256);
		
		//score board
		g.setFont(new Font("Futura LT", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("Score: " + (int) score, 630, 20);

		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

				if(playeLocation[i][j]){
					
					//if setting set to true
					if (Launch.isSolidColor()) {
						g.setColor(Color.green); //sets snake color to green
					} else {
						g.setColor(new Color(R,G,B)); //otherwise snake has random colors
					}
					
					g.fillRect((i*handler.getWorld().GridPixelsize),
							(j*handler.getWorld().GridPixelsize),
							handler.getWorld().GridPixelsize,
							handler.getWorld().GridPixelsize);
				}
				
				if(handler.getWorld().appleLocation[i][j]) {
					g.setColor(appleColor);
					g.fillRect((i*handler.getWorld().GridPixelsize),
							(j*handler.getWorld().GridPixelsize),
							handler.getWorld().GridPixelsize,
							handler.getWorld().GridPixelsize);
				}

			}
		}

	}

	public void Eat(){
		lenght++;
		Tail tail= null;
		handler.getWorld().appleLocation[xCoord][yCoord]=false;
		handler.getWorld().appleOnBoard=false;
		
		if (Apple.isGood()) {
		
			switch (direction){
			case "Left":
				if( handler.getWorld().body.isEmpty()){
					if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail = new Tail(this.xCoord+1,this.yCoord,handler);
					}else{
						if(this.yCoord!=0){
							tail = new Tail(this.xCoord,this.yCoord-1,handler);
						}else{
							tail =new Tail(this.xCoord,this.yCoord+1,handler);
						}
					}
				}else{
					if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
					}else{
						if(handler.getWorld().body.getLast().y!=0){
							tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
						}else{
							tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);
	
						}
					}
	
				}
				break;
			case "Right":
				if( handler.getWorld().body.isEmpty()){
					if(this.xCoord!=0){
						tail=new Tail(this.xCoord-1,this.yCoord,handler);
					}else{
						if(this.yCoord!=0){
							tail=new Tail(this.xCoord,this.yCoord-1,handler);
						}else{
							tail=new Tail(this.xCoord,this.yCoord+1,handler);
						}
					}
				}else{
					if(handler.getWorld().body.getLast().x!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
					}else{
						if(handler.getWorld().body.getLast().y!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
						}
					}
	
				}
				break;
			case "Up":
				if( handler.getWorld().body.isEmpty()){
					if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=(new Tail(this.xCoord,this.yCoord+1,handler));
					}else{
						if(this.xCoord!=0){
							tail=(new Tail(this.xCoord-1,this.yCoord,handler));
						}else{
							tail=(new Tail(this.xCoord+1,this.yCoord,handler));
						}
					}
				}else{
					if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
					}else{
						if(handler.getWorld().body.getLast().x!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
						}
					}
	
				}
				break;
			case "Down":
				if( handler.getWorld().body.isEmpty()){
					if(this.yCoord!=0){
						tail=(new Tail(this.xCoord,this.yCoord-1,handler));
					}else{
						if(this.xCoord!=0){
							tail=(new Tail(this.xCoord-1,this.yCoord,handler));
						}else{
							tail=(new Tail(this.xCoord+1,this.yCoord,handler));
						} System.out.println("Tu biscochito");
					}
				}else{
					if(handler.getWorld().body.getLast().y!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
					}else{
						if(handler.getWorld().body.getLast().x!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
						}
					}
	
				}
				break;
			}
			
			
			
			
			handler.getWorld().body.addLast(tail);
			handler.getWorld().playerLocation[tail.x][tail.y] = true;
		}
	}

	public void kill(){
		lenght = 0;
		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

				handler.getWorld().playerLocation[i][j]=false;

			}
		}
	}

	public boolean isJustAte() {
		return justAte;
	}

	public void setJustAte(boolean justAte) {
		this.justAte = justAte;
	}
	public static void setAppleColor(Color c) {
		appleColor = c;
	}
}
