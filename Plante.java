import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Plante extends Verdure{
    private boolean comestible;
    private int taille;
    
    public Plante(boolean comestible,int taille){
        this.comestible = comestible;
        this.taille = taille;
	
	try{
		terrainImage = ImageIO.read(new File("plante.png"));
		System.out.println("hello");
	}catch(Exception e){e.printStackTrace();}
    } 
    
    public Plante(boolean comestible){
       this(comestible, (int)(Math.random()*11));
    }

    public Plante(){
        this(true, (int)(Math.random()*11));
    }
    
    public boolean estComestible(){
        return comestible;
    }
    
    public void vieillir(){
        taille += 2;
    }
    
    public boolean estMange(){
        if(taille > 0){
            taille--;
            return true;
        }
        else{
            // Faire changer de classe devient Verdure ou devient Terre à voir
        
            return false;    
        }
    }
    
}
