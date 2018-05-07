public class Arbre extends Plante{
    private boolean producteur;


    ////////////////////////////CONSTRUCTEURS////////////////////////////
    public Arbre(){
        super();
        producteur = true;
    }
    
    public Arbre(boolean producteur){
        super();
        this.producteur = producteur;
    }



    public boolean estProducteur(){
        return producteur;
    }

    ////////////////////////////UTILITAIRES////////////////////////////
    public boolean estMange(){
        return true;
    }
    
    
}