public interface Proie{
    public abstract boolean estPredateur(Animal a);
    public abstract double seDefendre();
    public abstract void fuir(Animal a);
    public abstract void sentirPredateur(Foret f);
}
