
/**
 * Classe représentant le robot medical spécialisé.
 * 
 * @author Département TIC - ESIGELEC
 * @version 1.2
 */
public class RobotMedicalSpecialise extends Robot {
	
	/**
	 * @see Robot#Robot(int, int, String)
	 */
	public RobotMedicalSpecialise(int ligne, int colonne, String direction, double batterie) {
		super(ligne, colonne, direction, batterie);
	}
	
	@Override
	protected String getCheminImageRobot() {
		return "./data/robot_medical.png";
	}
	
	@Override
	protected String getCheminImageRobotDetruit() {
		return "./data/robot_medical_detruit.png";
	}

}
