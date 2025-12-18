/**
 * Programme principal avec la méthode main
 * @author Département TIC - ESIGELEC
 * @version 2.2
 */
import java.util.ArrayList ;
public class SimulateurApp5 {

    public static void main(String[] args) {
        // création de l'environnement et récupération du terrain
        Terrain terrain = Environnement.creerEnvironnement(10, 10);

        // création du robot et ajout de battery
        Robot robot = new Robot(0, 0, "sud",100);
       
        // ajout du robot sur le terrain
        terrain.ajouterRobot(robot);
        terrain.ajouterObstaclesAleatoiresIsoles(100);

        // mise à jour des composants graphiques
        terrain.updateIHM();

        // === DÉPLACEMENT DU ROBOT ==
        
        ArrayList<String> maListe = new ArrayList<>();
        int count = 0 ;
        for (int j = 0; j < 9; j++) 
        {
            for (int i = 0; i < 9; i++) 
            {
                if (robot.isObstacleDevant()) {
                	count++;
                    if (i != 8) 
                    {
                        // Cas obstacle avant la fin de ligne
                        if (robot.getDirection().equals("sud")) 
                        {
                            robot.tournerGauche();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            i++;
                            robot.tournerDroite();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            robot.tournerDroite();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            robot.tournerGauche();
                        } 
                        else if (robot.getDirection().equals("nord"))
                        {
                            robot.tournerDroite();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            i++;
                            robot.tournerGauche();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            robot.tournerGauche();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            robot.tournerDroite();
                        }
                       System.out.println("Batterie actuelle : " + robot.getBatterie());
                        boolean resultat = robot.verification(robot.getBatterie());
                        if(resultat) {
                        	 robot.retourCasedepart(robot.getLigne(), robot.getColonne(), robot.getDirection(), maListe);
                             robot.chargerBatterie();
                             robot.retourPosition(maListe);
               			}
                    } 
                    else 
                    {
                        // Cas obstacle en fin de ligne
                        if (robot.getDirection().equals("sud"))
                        {
                            robot.tournerGauche();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            i++;
                            robot.tournerDroite();
                            if (!robot.isObstacleDevant()) robot.avancer();
                        } 
                        else if (robot.getDirection().equals("nord")) 
                        {
                            robot.tournerDroite();
                            if (!robot.isObstacleDevant()) robot.avancer();
                            i++;
                            robot.tournerGauche();
                            if (!robot.isObstacleDevant()) robot.avancer();
                        }
                    }
                   System.out.println("Batterie actuelle : " + robot.getBatterie());
                    boolean resultat = robot.verification(robot.getBatterie());
                    if(resultat) {
                    	 robot.retourCasedepart(robot.getLigne(), robot.getColonne(), robot.getDirection(), maListe);
                         robot.chargerBatterie();
                         robot.retourPosition(maListe);
                    }
                } 
                else 
                {
                    // Pas d’obstacle
                     robot.avancer();
                }
                System.out.println("Batterie actuelle : " + robot.getBatterie());
                boolean resultat = robot.verification(robot.getBatterie());
                if(resultat) {
                	 robot.retourCasedepart(robot.getLigne(), robot.getColonne(), robot.getDirection(), maListe);
                     robot.chargerBatterie();
                     robot.retourPosition(maListe);
                }

            }

            // === Changement de colonne ===
            // On se déplace à gauche ou à droite pour passer à la ligne suivante
            if(robot.getColonne() == j)
	            {
	                if (robot.getDirection().equals("sud")) 
	                {
	                    robot.tournerGauche();
	                    if (!robot.isObstacleDevant()) 
	                    {
	                        robot.avancer();
	                    } 
	                    else 
	                    {
	                    	count++;
	                        robot.tournerGauche();
	                        if (!robot.isObstacleDevant()) robot.avancer();
	                        robot.tournerDroite();
	                        if (!robot.isObstacleDevant()) robot.avancer();
	                    }
	                    robot.tournerGauche();
	                } 
	                else if (robot.getDirection().equals("nord"))
	                {
	                    robot.tournerDroite();
	                    if (!robot.isObstacleDevant()) 
	                    {
	                        robot.avancer();
	                    } 
	                    else 
	                    {
	                    	count ++ ;
	                        robot.tournerDroite();
	                        if (!robot.isObstacleDevant()) robot.avancer();
	                        robot.tournerGauche();
	                        if (!robot.isObstacleDevant()) robot.avancer();
	                    }
	                    robot.tournerDroite();
	                }
	                System.out.println("Batterie actuelle : " + robot.getBatterie());
	                boolean resultat = robot.verification(robot.getBatterie());
	                if(resultat) {
	                	 robot.retourCasedepart(robot.getLigne(), robot.getColonne(), robot.getDirection(), maListe);
	                     robot.chargerBatterie();
	                     robot.retourPosition(maListe);
	                }
	            }
            else
            {
            	robot.tournerDroite();
            	robot.tournerDroite();
            }
           System.out.println("Batterie actuelle : " + robot.getBatterie());
            boolean resultat = robot.verification(robot.getBatterie());
            if(resultat) {
            	 robot.retourCasedepart(robot.getLigne(), robot.getColonne(), robot.getDirection(), maListe);
                 robot.chargerBatterie();
                 robot.retourPosition(maListe);
            }
        }
        System.out.println(count);
     
     
    }
}
