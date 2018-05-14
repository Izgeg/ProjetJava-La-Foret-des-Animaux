import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Verdure extends Terrain{
    public Verdure(){

	try{
		terrainImage = ImageIO.read(new File("verdure.png"));
	}catch(Exception e){e.printStackTrace();}
    }
}
