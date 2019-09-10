package Main;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class Launch {

	private static boolean isSolidColor;
	private static boolean isHard;
	
    public static void main(String[] args) {
        GameSetUp game = new GameSetUp("Snake", 780, 780);
        game.start();
    }

    //getters and setters
	public static boolean isSolidColor() {
		return isSolidColor;
	}

	public static void setSolidColor(boolean isSolidColor) {
		Launch.isSolidColor = isSolidColor;
	}

	public static boolean isHard() {
		return isHard;
	}

	public static void setHard(boolean isHard) {
		Launch.isHard = isHard;
	}
    
}
