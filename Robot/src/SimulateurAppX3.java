/**
 * Programme principal avec la méthode main
 * @author Département TIC - ESIGELEC
 * @version 2.2
 */
import java.util.ArrayList;
public class SimulateurAppX3 {

	public static void main(String[] args) {
		// création de l'environnement et récupération du terrain
		Terrain terrain = Environnement.creerEnvironnement(10, 10);

		// création du robot
		Robot robot = new Robot(0, 0, "sud" , 0);

		// ajout du robot sur le terrain
		terrain.ajouterRobot(robot);
		terrain.ajouterSalleCentrale();
		terrain.ajouterVictimeSalleCentrale();
		
		// mise à jour des composants graphiques
		
		terrain.updateIHM();
		
		// ajouter ici le code de déplacement du robot
		
		ArrayList<String> chemin = new ArrayList<>();
		robot.tournerGauche();
		chemin.add("G");
		for(int i = 0 ; i <=5 ; i++){
			if(i < 3){
				robot.avancer();
				chemin.add("A");
			}
			else if(i == 3){
				robot.tournerDroite();
				chemin.add("D");
			}
			else{
				robot.avancer();
				chemin.add("A"); }
		}
		
		chemin.add("G");
		String etat = "" ;
		int countPorte = 0 ;
		while(countPorte < 1){
		
				if(robot.isObstacleDevant()){
					robot.tournerGauche();
				}
				else{
					chemin.add("D");
				}
				robot.avancer();
				chemin.add("A");
				
				if(!robot.isObstacleDevant()) {
					robot.tournerDroite();
					if(robot.isObstacleDevant() && robot.isObstacleDerriere()){
						countPorte++;	robot.tournerGauche();
						break;
					}
					
				}

		}
		
		int findVictime = 0;
	while(findVictime < 1){
			robot.tournerDroite();
			if(robot.isSurVictime()){
				etat = robot.getEtatVictime();
				System.out.println(etat);
				findVictime ++ ;
				break;
			}
			if(robot.isObstacleDevant()){
				robot.tournerGauche();
				if(robot.isObstacleDevant()){
					robot.tournerGauche();
					chemin.add("G");
					robot.avancer();
					chemin.add("A");
					if(robot.isSurVictime()){
						etat = robot.getEtatVictime();
						System.out.println(etat);
						findVictime ++ ;
						break;
					}
				}
				else{
					robot.avancer();
					chemin.add("A");
					if(robot.isSurVictime()){
						etat = robot.getEtatVictime();
						System.out.println(etat);
						findVictime ++ ;
						break;
					}	}	
			}
			else{
				chemin.add("D");
				robot.avancer();
				chemin.add("A");
				if(robot.isSurVictime()){
					etat = robot.getEtatVictime();
					System.out.println(etat);
					findVictime ++ ;
					break;
				}	}
		}
	while( robot.getLigne() != 0 && robot.getColonne() != 0){
		for(int i = chemin.size()-1 ; i > 0 ; i--){
			if(chemin.get(i) == "A")	{
				robot.reculer();
			}
			else if (chemin.get(i) == "G"){
				robot.tournerDroite();
			}
			else if (chemin.get(i) == "D"){
				robot.tournerGauche();
			}
			else{
				System.out.println("error");
			} }
	}
	
	// ROBOT MEDICAL
	RobotMedicalSpecialise robotMedical = new RobotMedicalSpecialise (0, 0, "sud" , 0);
	terrain.ajouterRobot(robotMedical);
	
	for(int i = 0 ; i < chemin.size() ; i++){
		if(chemin.get(i) == "G"){
			robotMedical.tournerGauche();		
		}
		else if (chemin.get(i) == "D"){
			robotMedical.tournerDroite();
		}
		else if (chemin.get(i) == "A"){
			robotMedical.avancer();
		}
		else {
			System.out.println("error");
		}
	}
	switch (etat) {
	case "saignement" : System.out.println("Trousse de soins");
		break;
	case "asphyxie" : System.out.println("Masque d'oxigenation artificielle");
		break;
	case "fracture" : System.out.println("Matelas immobiliser");
		break;
	case "arret cardiaque" : System.out.println("Defibrillateur");
		break;
	default : System.out.println("Error");
		break;
	}
	}
}
