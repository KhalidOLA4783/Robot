/**
 * Programme principal avec la méthode main
 * @author Département TIC - ESIGELEC
 * @version 2.2
 */
import java.util.Scanner;
public class SimulateurApp1_variante2 {
    public static void main(String[] args) {
        
        // création de l'environnement et récupération du terrain
        Terrain terrain = Environnement.creerEnvironnement(10, 10);

        // création du robot
        Robot robot = new Robot(0, 0, "sud",100);

        // ajout du robot sur le terrain
        terrain.ajouterRobot(robot);

        // mise à jour des composants graphiques
        terrain.updateIHM();
        
        // Choix de langage
        
        System.out.println("1 : Francais");
        System.out.println("2 : English");
        System.out.println("3 : Deutsch");
        System.out.println("4 : Portugais");
        // Lecture du nombre de victimes
        int nombre_victime = 0;
        int langue ;
        Scanner lectureClavier = new Scanner(System.in);
        do {
    		System.out.println("Veuillez choisir une langue : ");
    		langue = lectureClavier.nextInt();
    	  } while(langue < 1 || langue > 4);
    	
        do {
        	if (langue == 1)
        	{
        		System.out.println("Veuillez entrer le nombre de victime ! Nombre = ");
        	}
        	else if (langue == 2)
        	{
        		System.out.println("Please enter the number of victims! Number =");
        	}
        	else if (langue == 3)
        	{
        		System.out.println("Bitte geben Sie die Anzahl der Opfer an! Anzahl =");
        	}
        	else if (langue == 4)
        	{
        		System.out.println("Por favor, insira o número de vítimas! Número = ");
        	}
        	else
        	{
        		System.out.println("...");
        	}
            nombre_victime = lectureClavier.nextInt();
        } while (nombre_victime < 1 || nombre_victime > 6);

        terrain.ajouterNombreDeVictimes(nombre_victime);
        terrain.updateIHM();

        // Détection de la victime la plus grave
        int gravity = 0, gravitymax = 0;
        int j = 0, i;
        int ligne = 0, colonne = 0;

        int nombre_totale_victime = 0 ;
    	float gravity_total = 0 , gravity1;
    	
        while (j < 5) {
            for (i = 0; i < 9; i++) {
                robot.avancer();
                if (robot.isSurVictime()) {
                    gravity = robot.detecterGravite();
                    nombre_totale_victime += 1 ;
    				gravity1 = robot.detecterGravite();
    				System.out.println(gravity1) ;
    				gravity_total += gravity1 ;
    				gravity1 = 0 ;
                    
                    if (gravity > gravitymax) {
                        gravitymax = gravity;
                        ligne = robot.getLigne();
                        colonne = robot.getColonne();
                    }
                }
            }

            robot.tournerGauche();
            if (!robot.isObstacleDevant()) {
                robot.avancer();
                if (robot.isSurVictime()) {
                    gravity = robot.detecterGravite();
                    nombre_totale_victime += 1 ;
    				gravity1 = robot.detecterGravite();
    				System.out.println(gravity1) ;
    				gravity_total += gravity1 ;
    				gravity1 = 0 ;
                    if (gravity > gravitymax) {
                        gravitymax = gravity;
                        ligne = robot.getLigne();
                        colonne = robot.getColonne();
                    }
                }
                robot.tournerGauche();
            } else {
                robot.tournerDroite();
            }

            for (i = 0; i < 9; i++) {
                robot.avancer();
                if (robot.isSurVictime()) {
                    gravity = robot.detecterGravite();
                    nombre_totale_victime += 1 ;
    				gravity1 = robot.detecterGravite();
    				System.out.println(gravity1) ;
    				gravity_total += gravity1 ;
    				gravity1 = 0 ;
                    if (gravity > gravitymax) {
                        gravitymax = gravity;
                        ligne = robot.getLigne();
                        colonne = robot.getColonne();
                    }
                }
            }

            robot.tournerDroite();
            if (!robot.isObstacleDevant()) {
                robot.avancer();
                if (robot.isSurVictime()) {
                    gravity = robot.detecterGravite();
                    nombre_totale_victime += 1 ;
    				gravity1 = robot.detecterGravite();
    				System.out.println(gravity1) ;
    				gravity_total += gravity1 ;
    				gravity1 = 0 ;
                    if (gravity > gravitymax) {
                        gravitymax = gravity;
                        ligne = robot.getLigne();
                        colonne = robot.getColonne();
                    }
                }
                robot.tournerDroite();
            } else {
                robot.tournerGauche();
            }

            j++;
        }

        // Affichage final
        
        float Moyenne = 0 ;
    	Moyenne = gravity_total / nombre_totale_victime ;
    	
    	if(langue == 1)
    	{
    		System.out.println("Moyenne = " + Moyenne);
            System.out.println("Gravité maximale détectée : " + gravitymax);
            System.out.println("Position correspondante : ligne = " + ligne + ", colonne = " + colonne);
    	}
    	else if(langue == 2)
    	{
    		System.out.println("Average =" + Moyenne);
            System.out.println("Maximum severity detected:" + gravitymax);
            System.out.println("Corresponding position: line = " + ligne + ", Column = " + colonne);
    	}
    	else if(langue == 3)
    	{
    		System.out.println("Durchschnitt = " + Moyenne);
            System.out.println("Maximaler Schweregrad erkannt: " + gravitymax);
            System.out.println("Entsprechende Position: Linie = " + ligne + ", Spalte = " + colonne);
    	}
    	else if (langue == 4)
    	{
    		System.out.println("Média = " + Moyenne);
    		System.out.println("Gravidade máxima detectada : " + gravitymax);
            System.out.println("Posição correspondente: linha = " + ligne + ", coluna = " + colonne);
    	}
    	else
    	{
    		System.out.println("...");
    	}
    	
        
        
        int k = 0 ;
        robot.tournerGauche();
        
        	
        for( k = 0 ; k < 9 - colonne ; k++)
        {
        	robot.avancer();
        }
        
        robot.tournerGauche();
        for( k = 0 ; k < ligne ; k++)
        {
        	robot.avancer();
        }
    	lectureClavier.close();
        	
    }
}
