import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ForetMain{

	public static void main(String[] args){
		int nbCerf = 20;
		int nbLapin = 25;
		int nbLoup = 20;
		int nbOurs = 15;
		int nbRenard = 30;
		


		Foret testForet = new Foret(Constante.tailleX,Constante.tailleY);
		Fenetre testFenetre = new Fenetre("Foret Simulation", 1002,1040, testForet);	

		
			

		
		
		System.out.println(testForet.getLargeur());
		
		System.out.println(testForet.getLongueur());
		
		testForet.initForet();
		for(int i = 0; i < nbCerf ; ++i){
			Cerf c = new Cerf();
			testForet.addAnimal(c);
		}
		
		for(int i = 0; i < nbLapin ; ++i){
			testForet.addAnimal(new Lapin());
		}
		for(int i = 0; i < nbLoup ; ++i){
			testForet.addAnimal(new Loup());
		}
		for(int i = 0; i < nbOurs ; ++i){
			testForet.addAnimal(new Ours());
		}
		for(int i = 0; i < nbRenard ; ++i){
			testForet.addAnimal(new Renard());
		}		
		
		testFenetre.afficher();
		while (true){
			testForet = testForet.refreshForet();
			testFenetre.setForet(testForet);			
			testFenetre.afficher();
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){e.printStackTrace();}
		}


	}
}
