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

public class Loup extends Animal implements Predateur{
    public static final double attaqueLoup = 75.;
    private final static int odorat = 4;
    private final static int vitesse = 4;
    

    ////////////////////////////CONSTRUCTEURS////////////////////////////
    
    public Loup(int x, int y, int age, char sexe){
	super(x,y,age,sexe);
try{
			animalImage = ImageIO.read(new File("loup.png"));
		}catch(Exception e){e.printStackTrace();}


    }
    
    public Loup(int x, int y){
        super(x,y);
try{
			animalImage = ImageIO.read(new File("loup.png"));
		}catch(Exception e){e.printStackTrace();}
    }
    
    public Loup(){
	super();
try{
			animalImage = ImageIO.read(new File("loup.png"));
		}catch(Exception e){e.printStackTrace();}
    }

    ////////////////////////////ACTTIONS////////////////////////////

    public void sentirProie(Foret f){
	ArrayList<Animal> proiesPotentielles = new ArrayList<Animal>() ;
	
	    
	for(int i = Math.max(this.getY() - odorat,0) ; i < Math.min(this.getY() + odorat, Constante.tailleY - 1) ; ++i){
	    
	    for(int j = Math.max(this.getX() - odorat,0) ; j < Math.min(this.getX() + odorat, Constante.tailleX - 1) ; ++j){
		ArrayList<Animal> list = f.foret[i][j].getAnimaux();
		for(Animal a : list){
		    if(estProie(a))
			proiesPotentielles.add(a);
		}
	    }
	}
	if(proiesPotentielles.isEmpty())
	    return;
	
	chasser(plusProche(proiesPotentielles));
		
    }

        public void chasser(Animal a){
    	//System.out.println(a.getClass());
    	
    
		if(a.x > this.x){
			directionX = Math.min(a.x - this.x,vitesse);
		}
		else{
			directionX = Math.max(a.x - this.x,-vitesse);
		}
		
		if(a.y > this.y){
			directionY = Math.min(a.y - this.y,vitesse);
		}
		else{
			directionY = Math.max(a.y - this.y,-vitesse);
		}
    
    	x+= directionX;
    	y+= directionY;
    }

    
    public double attaquer(){
	return Math.random()*Constante.JetOff + attaqueLoup + energie;
    }    




    public Animal seReproduire(Animal a, Foret f){
	boolean nouveau = false;
	Animal bebe;
		
	for(Animal b : f.foret[this.getX()][this.getY()].getAnimaux()){
	    if(naissance(b))
		nouveau = true ;
	    break;
	}
	if (nouveau){
	    bebe = new Loup(this.x, this.y);
	    return bebe;
	}
	else
	    return null;
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
    public boolean naissance(Animal a){
	if(a instanceof Loup && a.getSexe() != this.getSexe())	
	    if(Math.random()*100 < Constante.reproduction)
		return true;
	    else 
		return false;
	else 
	    return false;	
    }



    public boolean estProie(Animal a){
	if(a instanceof Renard)
	    return true;
	if(a instanceof Lapin)
	    return true;

	return false;
    }

	public void mangerAnimal(Animal a, Foret f){
		this.energie += 0.5*a.energie;//pris au pif
		((Animal)a).mourir(f);
		
}

	public void combattre(Animal a, Foret f){
		double attack = this.attaquer();		
		double defense = ((Proie)a).seDefendre();
		if(attack >= defense){
			mangerAnimal(a, f);
			if(attack == defense)
				this.energie -= 10;//en cas d'égalité, le prédateur gagne mais perd de l'energie
		}
		else{
		this.energie -= (defense - attack)*5;	
		}
	
	}

public void afficher(Graphics2D g, int posX, int posY){
		g.drawImage(animalImage,posX,posY,null);
}


    
}
