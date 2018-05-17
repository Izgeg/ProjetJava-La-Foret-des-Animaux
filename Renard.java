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

public class Renard extends Animal implements Predateur, Proie{
    private static final int odorat = 3;
    private static final int vitesse = 3;
    public  static final int defenseRenard = 35;    
    public  static final int attaqueRenard = 50;

    
    ////////////////////////////CONSTRUCTEURS////////////////////////////
    
    public Renard(int x, int y, int age, char sexe){
	super(x,y,age,sexe);
	try{
	    animalImage = ImageIO.read(new File("renard.png"));
	}catch(Exception e){e.printStackTrace();}


    }
    
    public Renard(int x, int y){
        super(x,y);
	try{
	    animalImage = ImageIO.read(new File("renard.png"));
	}catch(Exception e){e.printStackTrace();}
    }
    
    public Renard(){
	super();
	try{
	    animalImage = ImageIO.read(new File("renard.png"));
	}catch(Exception e){e.printStackTrace();}
    }
    ////////////////////////////ACTIONS////////////////////////////

    public int seDefendre(){
	return (int)(defenseRenard + energie);
    }    
    
    public int attaquer(){
        return (int)(Math.random()*35 + attaqueRenard + energie);
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
	    bebe = new Renard(this.x, this.y);
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
	energie -= Math.abs(directionX);
	energie -= Math.abs(directionY);
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

    

    ////////////////////////////UTILITAIRES////////////////////////////
	public Animal choisirProie(ArrayList<Animal> list){
		for(Animal a : list){
			if (this.estProie(a) && a.enVie()) return a;
			}
		return null;	
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
	
	return false;
    }
    public boolean estProie(Animal a){
	if(a instanceof Lapin)
	    return true;
	
	return false;
    }

    public boolean naissance(Animal a){
	if(a instanceof Renard && a.getSexe() != this.getSexe())	
	    if(Math.random()*100 < Constante.reproductionCarnivores)
		return true;
	    else 
		return false;
	else 
	    return false;	
    }

	public void mangerPlante(Plante p){
		p.setTaille(p.getTaille()-2);
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

    public void mangerAnimal(Animal a){
		this.energie += 0.5*a.getEnergie();//pris au pif
		a.mourir();
		this.setbienManger(true);
		
}
    
    public void combattre(Animal a){	
		double attack = this.attaquer();		
		double defense = ((Proie)a).seDefendre();
		System.out.println("nous sommes " + this.toString() + "\n attaque :" + attack + "\n et" + a.toString() + "\n defense :" + defense + " et nous nous battons en " + a.getX() + "," + a.getY());
		if(attack >= defense){
			mangerAnimal(a);
			if(attack == defense) this.energie -= 10;//en cas d'égalité, le prédateur gagne mais perd de l'energie
		}
		else this.energie -= (defense - attack)*5;	
	}

	public String toString(){
		return "Je suis un Renard, j'ai " + age + " mois" + " il me reste " + energie + "d'energie";
}
    
    public void afficher(Graphics2D g, int posX, int posY){
	g.drawImage(animalImage,posX,posY,null);
    }

}
