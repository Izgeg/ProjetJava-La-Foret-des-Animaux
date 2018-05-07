import java.lang.Math;
import java.util.ArrayList;

public class Lapin extends Animal{
    public static final double defenseLapin = 0.5;
    private final static int odorat = 2;
    private final static int vitesse = 1;

    ////////////////////////////CONSTRUCTEURS////////////////////////////
    
    public Lapin(int x, int y, int age, char sexe){
	super(x,y,age,sexe);

    }
    
    public Lapin(int x, int y){
        super(x,y);

    }

    public Lapin(){
	super();

    }
    
    ////////////////////////////ACTIONS////////////////////////////
    
    public double seDefendre(){
	return Math.random()*Constante.JetDef + Constante.defenseLapin + energie;
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
	    bebe = new Lapin(this.x, this.y);
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
    }

    
    ////////////////////////////UTILITAIRES////////////////////////////

    public boolean naissance(Animal a){
	if(a instanceof Lapin && a.getSexe() != this.getSexe())	
	    if(Math.random()*100 < Constante.reproduction)
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
    
}
