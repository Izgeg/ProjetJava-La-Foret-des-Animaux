import java.util.ArrayList;
import java.awt.*;
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
    	if(Math.random() < 0.2){
	    terrain = new Terre();
    	}
    	else{
    	    if(Math.random() < 0.4){
    		terrain = new Plante();	
    	    }
    	    else{
    	        
        	    if(Math.random() < 0.6){
        		terrain = new Arbre();	
        	    }
        	    
        	    else{
    	            if(Math.random() < 0.8){
    		        terrain = new Eau();	
    	            }
    	            else{
		                terrain = new Verdure();
	               }
	            }
	        }
    	}
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
    
    public void setCouleur(Terrain t){
    	if(t instanceof Eau)
	    couleur = Color.blue;
    	else if(t instanceof Terre)
	    couleur = new Color(63,34,4);//RGB du marron	
    	else if(t instanceof Verdure){
	    if(t instanceof Plante){
		couleur = new Color(86,130,3);// Vert avocat
	    }
	    else if(t instanceof Arbre){
		couleur = new Color(0,86,27);// Vert empire
	    }
	    else{
		couleur = new Color(58,137,35);//RGB d'un vert	
	    }
	}
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

    public void afficherAnimaux(){
    	for(Animal a : animaux){
	    if(a instanceof Cerf){
		System.out.print("C");
	    }
	    if(a instanceof Ours){
		System.out.print("O");
	    }
	    if(a instanceof Lapin){
		System.out.print("L");
	    }
	    if(a instanceof Loup){
		System.out.print("Lo");
	    }
	    if(a instanceof Renard){
		System.out.print("R");
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
