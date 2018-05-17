import java.lang.Math;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


public class Lapin extends Animal implements Proie{
    public  static final double defenseLapin = 0.5;
    private static final int odorat = 2;
    private static final int vitesse = 1;

    ////////////////////////////CONSTRUCTEURS////////////////////////////
    
   public Lapin(int x, int y, int age, char sexe){
	super(x,y,age,sexe);
try{
			animalImage = ImageIO.read(new File("lapin.png"));
		}catch(Exception e){e.printStackTrace();}


    }
    
    public Lapin(int x, int y){
        super(x,y);
try{
			animalImage = ImageIO.read(new File("lapin.png"));
		}catch(Exception e){e.printStackTrace();}
    }
    
    public Lapin(){
	super();
try{
			animalImage = ImageIO.read(new File("lapin.png"));
		}catch(Exception e){e.printStackTrace();}
    }
    ////////////////////////////ACTIONS////////////////////////////
    
    public int seDefendre(){
	return (int)(defenseLapin + energie);
    }    


    public Animal seReproduire(Foret f){
	boolean nouveau = false;
	Animal bebe;
		
	for(Animal b : f.foret[this.getX()][this.getY()].getAnimaux()){
	    if(naissance(b))
		nouveau = true ;
	    break;
	}
	if (nouveau){
	    bebe = new Lapin(this.x, this.y);
	    return bebe;
	}
	else
	    return null;
    }
	
	public void mangerPlante(Plante p){
		p.setTaille(p.getTaille()-3);
		this.setBienManger(true);
		this.mangerEnergie();
	}
	public void sentirPlante(Foret f){
		for(int i = this.getY() + 1 ; i < this.getY() - 1 ; ++i){
	    	for(int j = this.getX() - 1 ; i < this.getX() + 1 ; ++j){
				if(f.foret[i][j].getTerrain() instanceof Plante) allerVers(i,j);
			}
		}
	}
	
	public void allerVers(int i, int j){
		if(i > this.x){
			directionX = Math.min(i - this.x,vitesse);
		}
		else{
			directionX = Math.max(i - this.x,-vitesse);
		}
		
		if(j > this.y){
			directionY = Math.min(j - this.y,vitesse);
		}
		else{
			directionY = Math.max(j - this.y,-vitesse);
		}
    
    	x+= directionX;
    	y+= directionY;
		energie -= Math.abs(directionX);
		energie -= Math.abs(directionY);
	}

    public void sentirPredateur(Foret f){
	ArrayList<Animal> predateursPotentielles = new ArrayList<Animal>() ;
	for(int i = this.getY() + odorat ; i < this.getY() - odorat ; ++i){
	    for(int j = this.getX() - odorat ; i < this.getX() + odorat ; ++j){
		ArrayList<Animal> list = f.foret[i][j].getAnimaux();
		for(Animal a : list){
		    if(estPredateur(a))
			predateursPotentielles.add(a);
		}
	    }
	}
	if(predateursPotentielles.isEmpty())
	    return;
	
	fuir(plusProche(predateursPotentielles));
		
    
    }

    // A REVOIR
     public void fuir(Animal a){
    		if(a.x > this.x){
			directionX = -1*Math.min(a.x - this.x,vitesse);
		}
		else{
			directionX = -1*Math.max(a.x - this.x,-vitesse);
		}
		
		if(a.y > this.y){
			directionY = -1*Math.min(a.y - this.y,vitesse);
		}
		else{
			directionY = -1*Math.max(a.y - this.y,-vitesse);
		}
    
    	x+= directionX;
    	y+= directionY;
	energie -= Math.abs(directionX);
	energie -= Math.abs(directionY);
    }

    
    ////////////////////////////UTILITAIRES////////////////////////////

    public boolean naissance(Animal a){
	if(a instanceof Lapin && a.getSexe() != this.getSexe())	
	    if(Math.random()*100 < Constante.reproductionHerbivores)
		return true;
	    else 
		return false;
	else 
	    return false;	
    }
    public Animal plusProche(ArrayList<Animal> list){
	double min_dist=odorat*2;
	int indice = 0;
	int indice_dist_min = 0;
	for(Animal a : list){
	    if (Math.sqrt(Math.pow(a.x,2) + Math.pow(a.y,2))<min_dist){
		min_dist = Math.sqrt(Math.pow(a.x,2) + Math.pow(a.y,2));
		indice_dist_min = indice;
	    }
	    indice ++;
	}
	return list.get(indice_dist_min);
    }

    
    public boolean estPredateur(Animal a){
	if(a instanceof Loup)
	    return true;
	if(a instanceof Ours)
	    return true;
	if(a instanceof Renard)
	    return true;
	
	return false;
    }

	public String toString(){
		return "Je suis un Lapin, j'ai " + age + " mois" + " il me reste " + energie + "d'energie" ;
}
    public void afficher(Graphics2D g, int posX, int posY){
		g.drawImage(animalImage,posX,posY,null);
}
}
