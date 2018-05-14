import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Fenetre{

	private JFrame fenetre;
	private Canvas canvas;
	private BufferStrategy bs;
	
	private Foret foret;
	
	private String titre;
	private int longueur;
	private int hauteur;
	
	public Fenetre(String title, int longueur, int hauteur, Foret foret){
		this.titre = titre;
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.foret = foret;

		fenetre = new JFrame(titre);
		fenetre.setSize(longueur,hauteur);		
		canvas = new Canvas();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);
		
		fenetre.setVisible(true);
		fenetre.add(canvas);
		
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
	}
	
	public JFrame getFenetre(){
		return fenetre;
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public void setForet(Foret foret){
		this.foret = foret;
	}
	public void afficher(){
		Graphics2D g = null;
		do{
   			try{
   				g = (Graphics2D)bs.getDrawGraphics();
   				foret.afficherForet(g);
    			}
    			finally{
           			g.dispose(); //termine l'utilisation de l'outil de dessin
    			}
    			bs.show(); //actualise la fenetre de dessin avec la nouvelle
		} while (bs.contentsLost()); //tant que l'actualisation de la fenetre nest pas complete, recommencer
		Toolkit.getDefaultToolkit().sync(); //force la fenetre a update meme qd souris en dehors
	}
	
}
