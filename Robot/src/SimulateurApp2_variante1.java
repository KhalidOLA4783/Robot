/**
 * Programme principal avec la méthode main
 * Simulation d’un robot explorateur sur un terrain 10x10
 * @author Département TIC - ESIGELEC
 * @version 2.2
 */
public class SimulateurApp2_variante1 {

	public static void main(String[] args) {
		// Création de l'environnement et récupération du terrain
		Terrain terrain = Environnement.creerEnvironnement(10, 10);

		// Création du robot
		Robot robot = new Robot(0, 0, "sud",100);

		// Ajout du robot sur le terrain
		terrain.ajouterRobot(robot);
		terrain.ajouterVictimePositionAleatoireColonne9();
		terrain.ajouterTousLesMurs();

		// Mise à jour des composants graphiques
		terrain.updateIHM();
		
		// Initialisation de la carte
		char[][] carte = new char[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				carte[i][j] = '?';}}
		// Declaratio des variables a utilisé
		int countPorte= 0;
		int[] lignesPortes = new int[5];
		int nombreDeI = 0;
		int ligne = 0, colonne = 0, colonne2 = 0;
		while(countPorte < 4){// boucle qui compte le nombre de porte si la porte vient a 4 il s'arrete
			for (int i = 0; i < 10; i++) {
				nombreDeI++;
				robot.tournerGauche(); // le robot regarde vers l'est (droite)
				ligne = robot.getLigne();
				colonne = robot.getColonne();
				carte[ligne][colonne] = ' '; // Position exploree
				// S'il y a un obstacle devant il marque O"
				if (robot.isObstacleDevant()) {
					if (colonne + 1 < 10) 
						carte[ligne][colonne + 1] = 'O';// IL place 'O' la colonne +1
					robot.tournerDroite();// Tourne a droite (il regarde vers le sud)
					if (!robot.isObstacleDevant()) {
						robot.avancer(); 
						ligne = robot.getLigne();
						colonne = robot.getColonne();
						carte[ligne][colonne] = ' ';} }
				else{
					// Pas d'obstacle => il continue vers le mur du fond
					countPorte++; // compteur de porte
					colonne = robot.getColonne();
					for (int k = ligne ; k < 10; k++){// Si il trouve porte tout le reste en bas avant de venir a porte  devient ' '
						carte[k][colonne] = ' '; }
					robot.avancer();
					ligne = robot.getLigne();
					colonne = robot.getColonne();
					carte[ligne][colonne] = 'P';// Il avance arrivé au niveau de porte il met porte
					if(countPorte <= 4){
						lignesPortes [countPorte] = ligne ;
					} // Enregistre les lignes de chaque porte
					colonne2 = colonne; // Pour continuer a utiliser la variable colonne dans la meme boucle je l'ai affecté a colonne2
					for (int k = ligne + 1; k < 10; k++){// Ce for permet de créer les mur sur chaque ligne en bas de la porte
						carte[k][colonne2] = 'O'; 
					}
					robot.avancer(); // il avance encore apres etre entre
					break;}
			}
			// Apres avoir exploré la colonne, il remonte
			if (nombreDeI == 0){// Ce cas car la porte peut etre completement en haut 
				robot.tournerDroite();
			} 
			else {
				robot.tournerGauche(); // regarde vers le nord	
				for (int i = 0; i < nombreDeI; i++) {
					if (robot.isObstacleDevant() == true){// si il rencontre d'obstacle il tourne deux fois a droite pour regarder vers le bas 
						robot.tournerDroite();
						robot.tournerDroite(); // demi-tour
						break;} 
					else{
						robot.avancer(); // il remonte
					} } }
		}
		for(int i = 0 ; i < 9 ; i++){
			if(robot.isSurVictime()){
				carte[robot.getLigne()][robot.getColonne()] = 'V';
				carte[robot.getLigne()][robot.getColonne() +1] = 'O';
				for(int k = robot.getLigne()+1 ; k < 10 ; k++){
					carte[k][8] = ' ';
					carte[k][9] = 'O';
				}
				break;}
			else{
				carte[robot.getLigne()][robot.getColonne()] = ' ';
				carte[robot.getLigne()][robot.getColonne() + 1] = 'O';
				robot.avancer();
			} }
		// Affichage final de la carte
		System.out.println("Carte final du terrain avec ' 'comme espace, 'O' comme obstacle et 'P' comme porte");
	    for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++) {
				System.out.printf("|%c|\t", carte[i][j]);
			}
			System.out.printf("\n");	}
	    double longueur = 0 ;
	    robot.tournerDroite();// J'avaisfais un programme avant e t ca verifiais uniquement si le robot etait juste a droite donc je l'ai tourner a droite pour aplliqué le programme
	    int cheminageLigne = 0 , cheminageColonne = 0 ;// variable cheminage ligne et colonne
		char [][] chemin = new char [10][10];
		int n =  lignesPortes.length ;
		chemin[0][0] =  'D';
		for(int k = n - 1  ; k >0 ; k--){
			if(robot.getLigne() == 0 && robot.getColonne() == 0)
				break;
			int actuelle = robot.getLigne();
			if( actuelle > lignesPortes[k]){
				robot.tournerDroite();
				longueur += 0.25 ;
				for(int i = 0 ; i < actuelle - lignesPortes[k] ; i++){
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1 ;}
				robot.tournerGauche();
				longueur += 0.25 ;
				cheminageLigne = robot.getLigne();
				cheminageColonne = robot.getColonne();
				chemin[cheminageLigne][cheminageColonne] = 'O';
				robot.avancer();
				longueur += 1 ;
				cheminageLigne = robot.getLigne();
				cheminageColonne = robot.getColonne();
				chemin[cheminageLigne][cheminageColonne] = 'O';
				robot.avancer();
				longueur += 1 ;
			}
			else if (actuelle < lignesPortes[k]){
				robot.tournerGauche();
				longueur += 0.25 ;
				for(int i = 0 ; i < lignesPortes[k] - actuelle ; i++){
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1 ;
				}
				robot.tournerDroite();
				longueur += 0.25 ;
				cheminageLigne = robot.getLigne();
				cheminageColonne = robot.getColonne();
				chemin[cheminageLigne][cheminageColonne] = 'O';
				robot.avancer();
				longueur += 1;
				cheminageLigne = robot.getLigne();
				cheminageColonne = robot.getColonne();
				chemin[cheminageLigne][cheminageColonne] = 'O';
				robot.avancer();
				longueur += 1 ;
			}
			else if (actuelle == lignesPortes[k]){
				if(robot.getDirection() == "nord"){
					robot.tournerGauche();
					longueur += 0.25 ;
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1;
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1;
				}
				else if(robot.getDirection() == "sud"){
					robot.tournerDroite();
					longueur += 0.25 ;
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1 ;
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1 ;
				}
				else if(robot.getDirection() == "ouest"){
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1 ;
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1;
				}
				else {
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1;
					cheminageLigne = robot.getLigne();
					cheminageColonne = robot.getColonne();
					chemin[cheminageLigne][cheminageColonne] = 'O';
					robot.avancer();
					longueur += 1 ;
				} }	
		}
		if(robot.getLigne() != 0 ){
			robot.tournerDroite();
			longueur += 0.25 ;
			int position_0C = robot.getLigne();
			for(int k = 0 ; k < position_0C ; k++){
				cheminageLigne = robot.getLigne();
				cheminageColonne = robot.getColonne();
				chemin[cheminageLigne][cheminageColonne] = 'O';
				robot.avancer();
				longueur += 1;}
		}
		// Je veux prndre les positions des derniers 'P' et 'V'
		int pos_P_8_colonne = 0 ;
		int pos_V_9_colonne = 0 ;
		for(int i = 0 ; i < 10 ; i ++){
			if(carte[i][7] =='P'){
				pos_P_8_colonne = i ;
			}
			if(carte[i][8] == 'V'){
				pos_V_9_colonne = i ;
			}
			chemin[i][8] = ' ' ; }
		if( pos_P_8_colonne >= pos_V_9_colonne ){
			for(int i = pos_V_9_colonne ; i <=pos_P_8_colonne ; i++){
				chemin[i][8] = 'O';
			} }
		else if( pos_P_8_colonne <= pos_V_9_colonne ){
			for(int i =  pos_P_8_colonne ; i <=pos_V_9_colonne ; i++){
				chemin[i][8] = 'O'; }
		}
		System.out.println("\nChemin 'O' comme porte");
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++) {
				System.out.printf("|%c|\t", chemin[i][j]);
			}
			System.out.printf("\n"); }
	    System.out.println("Longueur = " + longueur); }
		}
