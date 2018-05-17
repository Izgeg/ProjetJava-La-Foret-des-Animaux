import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public interface Predateur{
    
    public abstract void combattre(Animal a);
    public abstract boolean estProie(Animal a);
    public abstract int attaquer();
    public abstract void chasser(Animal a);
    public abstract void sentirProie(Foret f);
    public abstract void mangerAnimal(Animal a);
	public abstract Animal choisirProie(ArrayList<Animal> list);
}
