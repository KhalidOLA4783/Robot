/**
 * Programme principal avec la méthode main
 * @author Département TIC - ESIGELEC
 * @version 2.2
 */
public class SimulateurApp1_variante1 {

	public static void main(String[] args) {
		// création de l'environnement et récupération du terrain
		Terrain terrain = Environnement.creerEnvironnement(10, 10);

		// création du robot
		Robot robot = new Robot(0, 0, "sud",100);

		// ajout du robot sur le terrain
		terrain.ajouterRobot(robot);
		terrain.ajouterPlusieursVictimes();
		
		// mise à jour des composants graphiques
		
		terrain.updateIHM();
		
		// ajouter ici le code de déplacement du robot
	
	int nombre_totale_victime = 0 ;
	float gravity_total = 0 , gravity1;
	int j = 0;
	while (j<5)
	{
		for(int i = 0 ; i < 9 ; i++)
		{
			robot.avancer();
			if(robot.isSurVictime() == true)
			{
				nombre_totale_victime += 1 ;
				gravity1 = robot.detecterGravite();
				System.out.println(gravity1) ;
				gravity_total += gravity1 ;
				gravity1 = 0 ;
				robot.sauverVictime();
			}
		}
		
		robot.tournerGauche();
		if (robot.isObstacleDevant() == false)
		{ 
			robot.avancer();
			if(robot.isSurVictime() == true)
			{
				nombre_totale_victime += 1 ;
				gravity1 = robot.detecterGravite();
				System.out.println(gravity1) ;
				gravity_total += gravity1 ;
				gravity1 = 0 ;
				robot.sauverVictime();
			}
			robot.tournerGauche();
		}
		else
		{
			robot.tournerDroite();
		}
		
		for(int i = 0 ; i < 9 ; i++)
		{
			robot.avancer();
			if(robot.isSurVictime() == true)
			{
				nombre_totale_victime += 1 ;
				gravity1 = robot.detecterGravite();
				System.out.println(gravity1) ;
				gravity_total += gravity1 ;
				gravity1 = 0 ;
				robot.sauverVictime();
			}
		}
		
		robot.tournerDroite();
		if (robot.isObstacleDevant() == false )
		{
			robot.avancer();
			if(robot.isSurVictime() == true)
			{
				nombre_totale_victime += 1 ;
				gravity1 = robot.detecterGravite();
				System.out.println(gravity1) ;
				gravity_total += gravity1 ;
				gravity1 = 0 ;
				robot.sauverVictime();
			}
			robot.tournerDroite();
		}
		else
		{
			robot.tournerGauche();
		}
		
		j++;
	}	
	float Moyenne = 0 ;
	Moyenne = gravity_total / nombre_totale_victime ;
	System.out.println("Moyenne = " + Moyenne);
	}

	
	

}
