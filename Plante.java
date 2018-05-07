public class Plante extends Verdure{
    private boolean comestible;
    private int taille;
    
    Plante(boolean comestible,int taille){
        this.comestible = comestible;
        this.taille = taille;
    } 
    
    Plante(boolean comestible){
        this.comestible = comestible;
        this.taille = (int)(Math.random()*11);
    }

    Plante(){
        this.comestible = true;
        this.taille = (int)(Math.random()*11);
    }
    
    public boolean estComestible(){
        return comestible;
    }
    
    public void vieillir(){
        taille += 2;
    }
    
    public boolean estMange(){
        if(taille > 0){
            taille--;
            return true;
        }
        else{
            // Faire changer de classe devient Verdure ou devient Terre à voir
        
            return false;    
        }
    }
    
}