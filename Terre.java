import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Terre extends Terrain{
    public Terre(){
        try{
	    terrainImage = ImageIO.read(new File("terre.png"));
	}catch(Exception e){e.printStackTrace();}
    }
	
	public  Terrain actualiserTerrain(){
		if(Math.random() > 0.99){
			//System.out.println("Je suis de la Terre qui devient de la Verdure");
			return new Verdure();
		}
		return this;
	}
	
		

}
