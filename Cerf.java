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


public class Cerf extends Animal implements Proie{
    public  static final int defenseCerf = 80;
    private final static int odorat = 3;
    private final static int vitesse = 4;
    
    ////////////////////////////CONSTRUCTEURS////////////////////////////
    public Cerf(int x, int y, int age, char sexe){
	super(x,y,age,sexe);
try{
			animalImage = ImageIO.read(new File("cerf.png"));
		}catch(Exception e){e.printStackTrace();}


    }
    
    public Cerf(int x, int y){
        super(x,y);
try{
			animalImage = ImageIO.read(new File("cerf.png"));
		}catch(Exception e){e.printStackTrace();}
    }
    
    public Cerf(){
	super();
try{
			animalImage = ImageIO.read(new File("cerf.png"));
		}catch(Exception e){e.printStackTrace();}
    }
    
    ////////////////////////////ACTIONS////////////////////////////
    
    public int seDefendre(){
        return (int)(defenseCerf + energie);
        
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
	    bebe = new Cerf(this.x, this.y);
	    return bebe;
	}
	else
	    return null;
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

	public void sentirPlante(Foret f){
		for(int i = this.getY() + 1 ; i < this.getY() - 1 ; ++i){
	    	for(int j = this.getX() - 1 ; i < this.getX() + 1 ; ++j){
				if(f.foret[i][j].getTerrain() instanceof Plante) allerVers(i,j);
			}
		}
	}

	public void mangerPlante(Plante p){
		p.setTaille(p.getTaille()-4);
		this.setBienManger(true);
		this.mangerEnergie();
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
    

    ////////////////////////////UTILITAIRES////////////////////////////
    
    
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
	
	return false;
    }
    
    public boolean naissance(Animal a){
	if(a instanceof Cerf && a.getSexe() != this.getSexe())	
	    if(Math.random()*100 < Constante.reproductionHerbivores)
		return true;
	    else 
		return false;
	else 
	    return false;	
    }
    public void afficher(Graphics2D g, int posX, int posY){
		g.drawImage(animalImage,posX,posY,null);
}

	public String toString(){
		return "Je suis un Cerf, j'ai " + age + " mois" +  " il me reste " + energie + "d'energie" ;
}
}
