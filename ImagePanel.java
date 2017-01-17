package NewTree;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel{
	//private Image image;
	private BufferedImage image;

	public ImagePanel(String fileName){
		try{
			this.image = ImageIO.read(new File(fileName));
		}catch(IOException ioe){
			System.err.println("Image File Not Exist");
			this.image = null;
		}catch(IllegalArgumentException iae){
			System.err.println("Image File Not Exist");
			this.image = null;
		}
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		double imageToPanelWidth = this.getWidth() / (image.getWidth() * 1.0);
		double imageToPanelHeight = this.getHeight() / (image.getHeight() * 1.0);

		AffineTransform at = AffineTransform.getScaleInstance(imageToPanelWidth, imageToPanelHeight);
		g2.drawImage(image, at, this);
	}

}