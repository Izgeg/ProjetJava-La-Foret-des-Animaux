import java.lang.Math;
import java.util.ArrayList;


public class Ours extends Animal implements Predateur{
    public static final double attaqueOurs = 90.;
    private final static int odorat = 3;
    private final static int vitesse = 2;
    
    
    ////////////////////////////CONSTRUCTEURS////////////////////////////
    
    public Ours(int x, int y, int age, char sexe){
	super(x,y,age,sexe);
    }
    
    public Ours(int x, int y){
        super(x,y);

    }

    public Ours(){
	super();

    }

    ////////////////////////////ACTIONS////////////////////////////

    public Animal seReproduire(Animal a, Foret f){
	boolean nouveau = false;
	Animal bebe;
		
	for(Animal b : f.foret[this.getX()][this.getY()].getAnimaux()){
	    if(naissance(b))
		nouveau = true ;
	    break;
	}
	if (nouveau){
	    bebe = new Ours(this.x, this.y);
	    return bebe;
	}
	else
	    return null;
    }
    public double attaquer(){
	return Math.random()*Constante.JetOff + Constante.attaqueOurs + energie;
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
    public boolean estProie(Animal a){
	if(a instanceof Renard)
	    return true;
	if(a instanceof Lapin)
	    return true;
	if(a instanceof Cerf)
	    return true;
	
	return false;
    }
    public boolean naissance(Animal a){
	if(a instanceof Ours && a.getSexe() != this.getSexe())	
	    if(Math.random()*100 < Constante.reproduction)
		return true;
	    else 
		return false;
	else 
	    return false;	
    }

public void combattre(Animal a);{
		double attack = this.attaquer();		
		double defense = a.seDefendre();
		if(attack >= defense){
			manger(a);
			if(attack == defense)
				this.energie -= 10;//en cas d'égalité, le prédateur gagne mais perd de l'energie
		}
		else{
		this.energie -= (defense - attack)*5;	
		}
	
	}

    

}
