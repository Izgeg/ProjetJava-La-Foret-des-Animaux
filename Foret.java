import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Foret{
    public Case[][] foret;
    private int longueur;
    private int largeur;

    ////////////////////////////CONSTRUCTEURS////////////////////////////
    public Foret(int longueur, int largeur){
	foret = new Case[longueur][largeur];
	this.longueur = longueur;
	this.largeur = largeur;
    }

    public Foret(){
	foret = new Case[10][10];
	this.longueur = 10;
	this.largeur = 10;
    }


    ////////////////////////////GETTEURS////////////////////////////
    public Case[][] getForet(){
	return foret;
    }
	
    public int getLongueur(){
	return longueur;
    }
	
    public int getLargeur(){
	return largeur;
    }
	
    
    ////////////////////////////UTILITAIRES////////////////////////////
    public void initForet(){
	for(int i=0;i<longueur;i++){
	    for(int j=0;j<largeur;j++){
		foret[i][j]= new Case();
		foret[i][j].initAleaCase();
	    }
	}
    }
	
    public void addAnimal(Animal a){
	foret[a.getX()][a.getY()].ajouterAnimal(a);
    }
	
    public Foret refreshForet(){
    	Foret foretStock = this.clone();
    	for(int i=0;i<longueur;i++){
    	    for(int j=0;j<largeur;j++){
    		if (!(foret[i][j].getAnimaux().isEmpty())){   		    
			for(Animal a : foret[i][j].getAnimaux()){
	    		        // Marcher
				foret[i][j].marcherAnimaux();
	      			// Sentir + Chasser + Fuir
	    			// Une fois tous les prédateurs en place 
	    			// A la place de instanceof Loup ===> instanceof Predateur
	    			if(a instanceof Proie){
	    			    ((Proie)a).sentirPredateur(this);
	    			}
	    			if(a instanceof Predateur){
	    			    ((Predateur)a).sentirProie(this);
	    			}
	    			// Manger eventuellement seBattre à rajouter
	    			mangerList(foret[i][j].getAnimaux());
			
			
	    			mangerHerbivore(foret[i][j]);
	    			resetManger(this);
	    			foretStock.addAnimal(a);	
    		    	}
    		    
    		}

		// Vieillissement des animaux + Plantes
    		vieillirForet(foretStock.foret[i][j]);
	    }
	   
	}
	return foretStock;
    }



    public void vieillirForet(Case c){
        if (!(c.getAnimaux().isEmpty())){
            for(Animal a : c.getAnimaux()){
                a.vieillir();
		if (a.getEnergie() <= 0) a.mourir();
		}
        	   
        }
		c.setTerrain(c.getTerrain().actualiserTerrain()); 
		c.setAnimaux(c.nettoyerMorts(c.getAnimaux()));
}
    


	
    public void mangerHerbivore(Case c){
	for(Animal a : c.getAnimaux()){
	    if(a instanceof Proie && !(a.getBienManger())){
		if(c.getTerrain() instanceof Plante){
                        if(((Plante)(c.getTerrain())).estComestible()){
                            ((Proie)a).mangerPlante(((Plante)(c.getTerrain())));
                        }
                    }
                } 
	    }
	}
    
	
    /*public void mangerList(ArrayList<Animal> list){
	ArrayList<Animal> listTemp = list;	
	for(Animal a : listTemp){
	
	    if(a instanceof Predateur && list.contains(a)){
		for(Animal b : listTemp){
			System.out.println("No soucis2 en ("+ a.getX() + "," + a.getY() +")");
		    if(((Predateur)(a)).estProie(b) && list.contains(b)){
			if(a.getBienManger()==false){
			    ((Predateur)a).combattre(b);
				System.out.println("bite");
				return;
			
			    // Pour sortir & éviter le segmentation fault
			}
		    }
		}
	    }
	}
    }*/
    
	public void mangerList(ArrayList<Animal> list){
		Predateur pred = null;
		Animal b = null;		
		for(Animal a : list){
			if (a instanceof Predateur) pred = (Predateur)a;
				if (pred != null){
					b = pred.choisirProie(list);
					if(b != null) pred.combattre(b);		
				}
  	}
}
	
    public void resetManger(Foret f){
	for(int i=0;i<longueur;i++){
	    for(int j=0;j<largeur;j++){
		if (!(foret[i][j].getAnimaux().isEmpty())){
		    for(Animal a : foret[i][j].getAnimaux()) a.setbienManger(false);
		}
	    }
	}
    }
	
    public Foret clone(){
	Foret CloneForet;
	CloneForet = new Foret(this.longueur, this.largeur);
	CloneForet.foret = new Case[longueur][largeur];
		
		
	for(int i=0;i<longueur;i++){
	    for(int j=0;j<largeur;j++){
		CloneForet.foret[i][j] = foret[i][j].clone();
	    }
	}
	return CloneForet;
    }
	
	
    public void afficherForet(Graphics2D g){
	//dessin des cases
	for(int i=0;i<longueur;i++){
	    for(int j=0;j<largeur;j++){
		foret[i][j].getTerrain().afficher(g,i*100,j*100);
		
		foret[i][j].afficherAnimaux(g,i*100,j*100);
		
		//System.out.print("\t");
	    }
	    //System.out.print("\n");
	}
    }

}
