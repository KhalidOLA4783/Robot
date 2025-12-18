/**
 * Programme principal avec la méthode main
 * Simulation d’un robot explorateur sur un terrain 10x10
 * @author Département TIC - ESIGELEC
 * @version 2.2
 */
public class SimulateurApp2_variante2 {
	public static void main(String[] args) {
		// Création de l'environnement et récupération du terrain
		Terrain terrain = Environnement.creerEnvironnement(10, 10);

		// Création du robot
		Robot robot = new Robot(0, 0, "sud",100);

		// Ajout du robot sur le terrain
		terrain.ajouterRobot(robot);
		terrain.ajouterVictimePositionAleatoireColonne9();
		terrain.ajouterTousLesMursDeuxPortes();

		// Mise à jour des composants graphiques
		terrain.updateIHM();
		
		// Initialisation de la carte
		char[][] carte = new char[10][10]		;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				carte[i][j] = '?';}
		}
		// Declaration des variables a utilisé
		int countPorte= 0;
		int[] lignesPortes1 = new int[10];
		int[] lignesPortes2 = new int[10];
		// Grace a ca je pourrais directement fais un if differeent de -1 pour avoir la main sur le lignes de chaque portes
		for(int i = 0 ; i < 10 ; i++){
			lignesPortes1[i] = -1 ;
			lignesPortes2[i] = -1 ;
		}
		int nombreDeI = 0;
		int ligne = 0, colonne = 0, colonne2 = 0;
	while(countPorte < 8){// boucle qui compte le nombre de porte si la porte vient a 4 il s'arrete
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
						carte[ligne][colonne] = ' ';}
				}
				else{
					// Pas d'obstacle => il continue vers le mur du fond
					countPorte++; // compteur de porte
					if (countPorte % 2 != 0){
						ligne = robot.getLigne();
						colonne = robot.getColonne();
						carte[ligne][colonne + 1] = 'P';
						if(countPorte <= 8){
							lignesPortes1[countPorte] = ligne ;
						} // Enregistre les lignes de chaque porte
						robot.tournerDroite();
						robot.avancer();}
					else{
						colonne = robot.getColonne();
						for (int k = ligne ; k < 10; k++)// Si il trouve porte tout le reste en bas avant de venir a porte  devient ' '
						{
							carte[k][colonne] = ' ';
						}
						robot.avancer();
						ligne = robot.getLigne();
						colonne = robot.getColonne();
						carte[ligne][colonne] = 'P';// Il avance arrivé au niveau de porte il met porte
						if(countPorte <= 8){
							lignesPortes2[countPorte] = ligne ;
						} // Enregistre les lignes de chaque porte
						colonne2 = colonne; // Pour continuer a utiliser la variable colonne dans la meme boucle je l'ai affecté a colonne2
						for (int k = ligne + 1; k < 10; k++){// Ce for permet de créer les mur sur chaque ligne en bas de la porte
							carte[k][colonne2] = 'O'; }
						robot.avancer(); // il avance encore apres etre entre
						break;
					} } }
			// Apres avoir exploré la colonne, il remonte
			if(countPorte %2 == 0){
				if (nombreDeI == 0){ // Ce cas car la porte peut etre completement en haut 
					robot.tournerDroite();} 
				else {
					robot.tournerGauche(); // regarde vers le nord	
					for (int i = 0; i < nombreDeI; i++) {
						if (robot.isObstacleDevant() == true){// si il rencontre d'obstacle il tourne deux fois a droite pour regarder vers le bas 
							robot.tournerDroite();
							robot.tournerDroite(); // demi-tour
							break;} 
						else{
							robot.avancer(); // il remonte
						}
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
				robot.avancer();}
		}
		// Affichage final de la carte
		System.out.println("Carte final du terrain avec ' 'comme espace, 'O' comme obstacle et 'P' comme porte");
	    for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.printf("|%c|\t", carte[i][j]);}
			System.out.printf("\n");
		}
	
	  double longueur = 0 ;
	    robot.tournerDroite();// J'avaisfais un programme avant e t ca verifiais uniquement si le robot etait juste a droite donc je l'ai tourner a droite pour aplliqué le programme
	    int cheminageLigne = 0 , cheminageColonne = 0 ;// variable cheminage ligne et colonne
		char [][] chemin = new char [10][10];
		int n =  lignesPortes1.length ;
		chemin[0][0] =  'D';
		int choix ;
		for(int j = n- 2 ; j>=0 ; j--){
			if(lignesPortes1[j] != -1){
				if(robot.getLigne() == 0 && robot.getColonne() == 0){
					break;}
				int actuelPosition = robot.getLigne() , position1 = 0 , position2 = 0  ;
				position1 = actuelPosition - lignesPortes1[j];
				position1 = Math.abs(position1)   ;
				position2 = actuelPosition - lignesPortes2[j + 1];
				position2 = Math.abs(position2);
				if(position1 <= position2){
					choix = lignesPortes1[j];
				}
				else{
					choix = lignesPortes2[j+1];}
				if( actuelPosition > choix){
					robot.tournerDroite();
					longueur += 0.25 ;
					for(int i = 0 ; i < actuelPosition - choix; i++){
						cheminageLigne = robot.getLigne();
						cheminageColonne = robot.getColonne();
						chemin[cheminageLigne][cheminageColonne] = 'O';
						robot.avancer();
						longueur += 1 ;
					}
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
				else if (actuelPosition < choix){
					robot.tournerGauche();
					longueur += 0.25 ;
					for(int i = 0 ; i < choix - actuelPosition ; i++){
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
				else if (actuelPosition == choix){
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
					} }	}	
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
			chemin[i][8] = ' ' ;
		}
		if( pos_P_8_colonne >= pos_V_9_colonne ){
			for(int i = pos_V_9_colonne ; i <=pos_P_8_colonne ; i++){
				chemin[i][8] = 'O';}
		}
		else if( pos_P_8_colonne <= pos_V_9_colonne ){
			for(int i =  pos_P_8_colonne ; i <=pos_V_9_colonne ; i++){
				chemin[i][8] = 'O';}
		}
		System.out.println("\nChemin 'O' comme porte");
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++) {
				System.out.printf("|%c|\t", chemin[i][j]);
			}
			System.out.printf("\n");
		}
	    System.out.println("Longueur = " + longueur);
		}
	}
		
		
		
