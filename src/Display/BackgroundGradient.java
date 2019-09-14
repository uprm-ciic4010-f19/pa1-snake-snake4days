package Display;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import com.sun.javafx.geom.Point2D;

@SuppressWarnings("serial")
public class BackgroundGradient extends JComponent {

	public void paint(Graphics g, Point2D p1, Color colorOne, Point2D p2, Color colorTwo) {

		Graphics2D g2d = (Graphics2D) g;
		
		int startX = 0, startY = 780, endX = 780, endY = 0; //from Left Bottom to Top Left

		GradientPaint gradient = new GradientPaint(startX, startY, colorOne, endX, endY, colorTwo);
		g2d.setPaint(gradient);

		Rectangle background = new Rectangle(0,0,780,780);
		g2d.fill(background);
		g2d.draw(background);

	}
}

