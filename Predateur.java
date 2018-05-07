public interface Predateur{
    
    public abstract void combattre(Animal a);
    public abstract boolean estProie(Animal a);
    public abstract double attaquer();
    public abstract void chasser(Animal a);
    public abstract void sentirProie(Foret f);
    
}
