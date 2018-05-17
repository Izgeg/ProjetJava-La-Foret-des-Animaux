import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public abstract class Terrain{
	
    protected BufferedImage terrainImage;	

    public void afficher(Graphics2D g, int posX, int posY){
		g.drawImage(terrainImage, posX, posY,null);
    }

	public abstract Terrain actualiserTerrain();
}
