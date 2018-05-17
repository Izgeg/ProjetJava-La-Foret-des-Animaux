import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public abstract class Animal{
    protected int x;
    protected int y;
    protected int directionX;
    protected int directionY;
    protected int energie;
    protected int age;//en mois
    protected char sexe;
    protected boolean bienManger = false;
	protected boolean mort = false;
	protected BufferedImage animalImage;


    ////////////////////////////CONSTRUCTEURS////////////////////////////
    public Animal(int x, int y, int age, char sexe){
		this.x = x;// position
		this.y = y;// de l'animal
		this.sexe = sexe ;// 'm' ou 'f' pour les 2 sexes
		energie = 100 ;//énergie de base pour les animaux
		this.age = age;
		directionX = (int)(Math.random()*3)-1;
		directionY = (int)(Math.random()*3)-1;
		try{
			animalImage = ImageIO.read(new File("animal.png"));
		}catch(Exception e){e.printStackTrace();}
    }

    public Animal(int x, int y){ //Pour les bébés
		this(x,y,0,'m');		
		if(Math.random() > 0.5) this.sexe = 'f';
		   
	}

    public Animal(){
		this((int)(Math.random()*10),(int)(Math.random()*10),12,'m');
		if(Math.random() > 0.5) this.sexe = 'f';
		this.energie = (int)Math.random()*50 + 50;
    }
	


    ////////////////////////////SETTEURS////////////////////////////

    public void setX(int x){
	this.x = x;
    }

    public void setY(int y){
	this.y = y;
    }

    public void vieillir(){
	age++;
    }

    public void setBienManger(boolean a){
    	bienManger = a ;
    }

	public void mourir(){
		mort = true;
	}
    
    ////////////////////////////GETTEURS////////////////////////////

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public int getEnergie(){
	return energie;
    }

    public int getAge(){
	return age;
    }

    public char getSexe(){
	return sexe;
    }
	
	public boolean getBienManger(){
		return bienManger;
	}

	public boolean enVie(){
		return !mort ;
	}
    ////////////////////////////ACTIONS////////////////////////////
    public void marcher(){
	if((x+directionX >= 0) && (x+directionX < Constante.tailleY))
	    x += directionX;
	if((y+directionY >= 0) && (y+directionY < Constante.tailleY))
	    y += directionY;
	energie -= Math.abs(directionX); 
	energie -= Math.abs(directionY);
	energie -= (int)(age/100) ; //il perd plus d'energie en vieillissant
	
	directionX = (int)(Math.random()*3)-1;
	directionY = (int)(Math.random()*3)-1;
    }

    public void mangerEnergie(){
        if(energie < 80)
            energie += 20;
        else
            energie = 100;
    }

    
    public abstract Animal seReproduire(Foret f);
    public abstract boolean naissance(Animal a);
        
	public boolean dejaManger(){
        return this.bienManger;
    }
    public void setbienManger(boolean a){
        this.bienManger = a;
    }

	public void afficher(Graphics2D g, int posX, int posY){
		g.drawImage(animalImage,posX,posY,null);
}
    
}
