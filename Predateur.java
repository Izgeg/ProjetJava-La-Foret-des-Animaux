public interface Predateur{
    
    public abstract void combattre(Animal a, Foret f);
    public abstract boolean estProie(Animal a);
    public abstract double attaquer();
    public abstract void chasser(Animal a);
    public abstract void sentirProie(Foret f);
    public abstract void mangerAnimal(Animal a, Foret f);
}
