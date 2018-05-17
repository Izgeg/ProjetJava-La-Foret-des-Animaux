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
    private ArrayList<Animal> animaux; 
    private Terrain terrain;
    ////////////////////////////CONSTRUCTEURS////////////////////////////

    public Case(Terrain t){
	animaux = new ArrayList<Animal>();
	terrain = t;
    }
    

    public Case(){
    	this(new Terre());
    }
    



    public void initAleaCase(){
	double rand = Math.random();
    	if(rand < 0.3334) terrain = new Terre();
    	else if (rand < 0.6667){
	if (Math.random() > 0.5) terrain = new Plante();
	else terrain = new Plante(false);
	} 		
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
    public void setTerrain(Terrain terrain){
	this.terrain = terrain;
    }
    public void setAnimaux(ArrayList<Animal> list){
	this.animaux = list ;
	}
    
    
    ////////////////////////////UTILITAIRES////////////////////////////

    // Clone case ne clone pas l'arrayList
    public Case clone(){
    	Case CloneCase;
    	CloneCase = new Case(terrain);

    	
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
			a.afficher(g,i*20+caseX,j*20+caseY);
			i++;
				if (i > 80){
					i = 0;
					j += 20;
				}
			    
		}
    }
    
    public void ajouterAnimal(Animal a){
    	animaux.add(a);
    }
    
    
    
    public void enleverAnimal(Animal a){
		animaux.remove(a);
    }
    
	public ArrayList<Animal> nettoyerMorts(ArrayList<Animal> list){	
		ArrayList<Animal> listActualisee = (ArrayList<Animal>)list.clone();	
		for(Animal a : list){
			if(!a.enVie() || a.getEnergie() <= 0) listActualisee.remove(a);
		}
		return listActualisee;
	}
	
}
