public abstract class Animal{
    protected int x;
    protected int y;
    protected int directionX;
    protected int directionY;
    protected int energie;
    protected int age;//en mois
    protected final char sexe;
    protected boolean bienManger = false; 


    ////////////////////////////CONSTRUCTEURS////////////////////////////
    public Animal(int x, int y, int age, char sexe){
	this.x = x;// position
	this.y = y;// de l'animal
	this.sexe = sexe ;// 'm' ou 'f' pour les 2 sexes
	energie = 100 ;//énergie de base pour les animaux
	this.age = age;
	directionX = (int)(Math.random()*3)-1;
	directionY = (int)(Math.random()*3)-1;
    }

    public Animal(int x, int y){ //Pour les bébés
	this.x = x;
	this.y = y;
	if(Math.random() > 0.5)
	    sexe = 'f';
	else
	    sexe = 'm';
	energie = 100;
	age = 0;
	directionX = (int)(Math.random()*3)-1;
	directionY = (int)(Math.random()*3)-1;
    }

    public Animal(){
	x = (int)(Math.random()*Constante.tailleX);
	y = (int)(Math.random()*Constante.tailleY);
	
	age = 12;
	energie = (int)Math.random()*50 + 50;
	if(Math.random() > 0.5)
	    sexe = 'f';
	else
	    sexe = 'm';
	directionX = (int)(Math.random()*3)-1;
	directionY = (int)(Math.random()*3)-1;
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
    	bienmanger = a ;
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
	public boolean getBienManger{
		return bienmanger;
	}
    ////////////////////////////ACTIONS////////////////////////////
    public void marcher(){
	if((x+directionX >= 0) && (x+directionX < Constante.tailleY))
	    x += directionX;
	if((y+directionY >= 0) && (y+directionY < Constante.tailleY))
	    y += directionY;
	energie -= Math.abs(directionX) + Math.abs(directionY);
	directionX = (int)(Math.random()*3)-1;
	directionY = (int)(Math.random()*3)-1;
    }

    public void MangerEnergie(){
        if(energie < 80)
            energie += 20;
        else
            energie = 100;
    }

    
    public abstract Animal seReproduire(Animal a, Foret f);
        public boolean dejaManger(){
        return this.bienManger;
    }
    public void setbienManger(boolean a){
        this.bienManger = a;
    }
    
}
