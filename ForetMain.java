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
		
		Foret testForet = new Foret(Constante.tailleX,Constante.tailleY);
		Fenetre testFenetre = new Fenetre("Foret Simulation", 1002,1040, testForet);	

		Cerf c1 = new Cerf();
		Cerf c2 = new Cerf();
		Cerf c3 = new Cerf();
		
		Ours o1 = new Ours();
		Ours o2 = new Ours();
		
		Lapin l1 = new Lapin();
		Lapin l2 = new Lapin();
		
		Loup Lo1 = new Loup();
		Loup Lo2 = new Loup();
		
		Renard r1 = new Renard();
		
		
		System.out.println(testForet.getLargeur());
		
		System.out.println(testForet.getLongueur());
		
		testForet.initForet();
		
		testForet.addAnimal(c1);
		testForet.addAnimal(c2);
		testForet.addAnimal(c3);
		testForet.addAnimal(o1);
		testForet.addAnimal(o2);
		testForet.addAnimal(l1);
		testForet.addAnimal(l2);
		testForet.addAnimal(r1);

		testForet.addAnimal(Lo1);
		testForet.addAnimal(Lo2);		
		
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
