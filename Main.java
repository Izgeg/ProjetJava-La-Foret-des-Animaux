public class Main
{
    public static void main(String[] args){
		
	Foret testForet = new Foret(Constante.tailleX,Constante.tailleY);
		
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
		
		
		
	testForet.afficherForet();
	//testForet.addAnimal(c3);
	//testForet.afficherForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();
	testForet = testForet.refreshForet();


    }

}
