import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.lang.Math;

public class Case{
    protected Color couleur;
    public ArrayList<Animal> animaux; 
    protected Terrain terrain;
    
    ////////////////////////////CONSTRUCTEURS////////////////////////////

    public Case(Terrain t){
	animaux = new ArrayList<Animal>();
	terrain = t;
    }
    

    public Case(){
    	this(new Terre());
    }
    



    public void initAleaCase(){
	System.out.println("hello2");
	double rand = Math.random();
    	if(rand < 0.3334) terrain = new Terre();
    	else if (rand < 0.6667) terrain = new Plante();		
    	else terrain = new Verdure();
    }
    ////////////////////////////GETTEURS////////////////////////////

    public Terrain getTerrain(){
	return terrain;
    }

    public ArrayList<Animal> getAnimaux(){
	return animaux;
    }
    
    ////////////////////////////SETTEURS////////////////////////////
    public void setTerrain(Terrain t){
	this.terrain = terrain;
    }
    
    
    
    ////////////////////////////UTILITAIRES////////////////////////////

    // Clone case ne clone pas l'arrayList
    public Case clone(){
    	Case CloneCase;
    	CloneCase = new Case(terrain);
    	CloneCase.couleur = couleur;
    	
    	CloneCase.animaux = new ArrayList<Animal>();
    	// CloneCase.animaux = new ArrayList<Animal>(animaux);
    	
    	return CloneCase;
    }
    
	public void marcherAnimaux(){
		for(Animal a : animaux){
			a.marcher();
		}
	
	}

    public void afficherAnimaux(Graphics2D g, int caseX, int caseY){
	int i = 0,j=0;
    	for(Animal a : animaux){
		a.afficher(g,i*5+caseX,j*5+caseY);
		i++;
		if (i > 95){
			i = 0;
			j++;
		}
			    
	}
    }
    
    public void ajouterAnimal(Animal a){
    	animaux.add(a);
    }
    
    
    
    public void enleverAnimal(Animal a){
	animaux.remove(a);
    }
    

}
