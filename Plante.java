import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Plante extends Terrain{
    private boolean comestible;
    private int taille;
    
    public Plante(boolean comestible,int taille){
        this.comestible = comestible;
        this.taille = taille;
	
	try{
		terrainImage = ImageIO.read(new File("plante.png"));
	}catch(Exception e){e.printStackTrace();}
    } 
    
    public Plante(boolean comestible){
       this(comestible, (int)(Math.random()*Constante.TailleApparitionPlante));
    }

    public Plante(){
        this(true, (int)(Math.random()*Constante.TailleApparitionPlante));
    }
    
    public boolean estComestible(){
        return comestible;
    }
    
    public void vieillir(){
        if (taille < Constante.TailleMaxPlante) taille += 1;
	else taille = Constante.TailleMaxPlante;
    }
    
	public int getTaille(){
		return taille ;
	}

	public void setTaille(int taille){
		this.taille = taille;
	}
	    public Terrain estMange(){
        if(taille > 2) return new Plante(true,taille-3);
        else return new Verdure();
	}

	public void afficher(Graphics2D g, int posX, int posY){
		g.drawImage(terrainImage, posX, posY,null);
		g.drawString(""+taille, posX+50, posY+50);
    }

	public Terrain actualiserTerrain(){
		if (taille <= 0) return new Terre();
		else this.vieillir();		
		return this ;
	}
    
}
