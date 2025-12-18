 
public class Battery {
	 private double autonomie ;
	 
	 public Battery (double autonomie)
	 {
		 this.autonomie = autonomie ;
	 }
	 
	 public double getAutonomie()
	 {
		 return autonomie ;
	 }
	 
	 public void setAutonomie(double nValeur)
	 {
		 autonomie = nValeur ;
	 }
	 
	 public void chargerBattery()
	 {
		 autonomie = 100 ;
	 }
	 
 }