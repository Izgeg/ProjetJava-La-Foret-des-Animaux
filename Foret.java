import java.util.ArrayList;

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
    		if (!(foret[i][j].animaux.isEmpty())){
    		    for(int k = 0; k<foret[i][j].animaux.size(); k++){
    		        // Marcher
			foret[i][j].marcherAnimaux();
      			// Sentir + Chasser + Fuir
    			// Une fois tous les prédateurs en place 
    			// A la place de instanceof Loup ===> instanceof Predateur
    			if(foret[i][j].getAnimaux().get(k) instanceof Proie){
    			    ((Proie)(foret[i][j].getAnimaux().get(k))).sentirPredateur(this);
    			}
    			if(foret[i][j].getAnimaux().get(k) instanceof Predateur){
    			    ((Predateur)(foret[i][j].getAnimaux().get(k))).sentirProie(this);
    			}
    			
    			
    			// Manger eventuellement seBattre à rajouter
    			mangerList(foret[i][j].getAnimaux());
    			mangerHerbivore(foret[i][j]);
    			
    			
    			resetManger(this);
    			foretStock.addAnimal(foret[i][j].animaux.get(k));
    		    }
    		    
    		}

		// Vieillissement des animaux + Plantes
    		vieillirForet(foretStock.foret[i][j]);
	    }
	   
	}
	foretStock.afficherForet();

	return foretStock;
    }
	
    public void vieillirForet(Case c){
        if (!(c.animaux.isEmpty())){
            for(Animal a : c.getAnimaux()){
                a.vieillir();

            }
        }
    
        if(c.getTerrain() instanceof Plante){
            if(((Plante)(c.getTerrain())).estComestible()){
                System.out.println("TEST");
                ((Plante)(c.getTerrain())).vieillir();
            }
        }
    }


	
    public void mangerHerbivore(Case c){
	for(int k=0 ; k < c.getAnimaux().size() ; k++){
	    if(c.getAnimaux().get(k) instanceof Proie){
		if(c.getTerrain() instanceof Plante){
    
                    if(c.getTerrain() instanceof Arbre){
                        if(((Arbre)(c.getTerrain())).estProducteur()){
                            ((Arbre)(c.getTerrain())).estMange();
                            c.getAnimaux().get(k).setbienManger(true);
                            c.getAnimaux().get(k).MangerEnergie();
                        }
                    }
                    else{
                        if(((Plante)(c.getTerrain())).estComestible()){
                            ((Plante)(c.getTerrain())).estMange();
                            c.getAnimaux().get(k).setbienManger(true);
                            c.getAnimaux().get(k).MangerEnergie();
                        }
                    }
                } 
	    }
	}
    }

	
	
    // ++ Rajouter seBattre ???
    public void mangerList(ArrayList<Animal> list){
	for(Animal a : list){
	    for(Animal b : list){
		if(a instanceof Predateur){
		    if(((Predateur)(a)).estProie(b)){
			if(((Predateur)(a)).dejaManger() == false){
			    a.MangerEnergie();
			    list.remove(b);
			    a.setbienManger(true);
			    // Rappel de manger sur la list car comme la liste à changer de size, si on ne rappelle pas segmentation fault
			    // eventuellement créer un attribut rassasié, qui empêche un loup de manger 2 proies ???
			    mangerList(list);
	                    
			    // Pour sortir & éviter le segmentation fault
	                    return;
			}
		    }
		}
	    }
	}
    }
	
    public void resetManger(Foret f){
	for(int i=0;i<longueur;i++){
	    for(int j=0;j<largeur;j++){
		if (!(foret[i][j].animaux.isEmpty())){
		    for(Animal a : foret[i][j].animaux){
			a.setbienManger(false);
		    }
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
	
	
    public void afficherForet(){
	System.out.println("_ _ _ _ _ _ _ _ _ _");
	for(int i=0;i<longueur;i++){
	    for(int j=0;j<largeur;j++){
		if (foret[i][j].getTerrain() instanceof Verdure){
		    if (foret[i][j].getTerrain() instanceof Plante){
		        if (foret[i][j].getTerrain() instanceof Arbre){
		            System.out.print("A : ");
		        }
		        else{
		            System.out.print("P : ");
		        }
		    }
		    else{
			System.out.print("V : ");
		    }
		}
		if (foret[i][j].getTerrain() instanceof Terre){
		    System.out.print("T : ");
		}
		if (foret[i][j].getTerrain() instanceof Eau){
		    System.out.print("E : ");
		}
		
		foret[i][j].afficherAnimaux();
		
		System.out.print("\t");
	    }
	    System.out.print("\n");
	}
    }

}
