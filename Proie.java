public interface Proie{
    public abstract boolean estPredateur(Animal a);
    public abstract int seDefendre();
    public abstract void fuir(Animal a);
    public abstract void sentirPredateur(Foret f);
    public abstract void mangerPlante(Plante p);
	public abstract void sentirPlante(Foret f);
}
