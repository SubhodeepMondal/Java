package shipGame;
import java.util.Scanner;
public class MainGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnemyShip theEnemy =null;
		ShipFactory shipFactory= new ShipFactory();
		Scanner userInput=new Scanner(System.in);
		System.out.println("What type of ship?(U/R/B)");
		if(userInput.hasNextLine()) {
			String typeofShip=userInput.nextLine();
			theEnemy=shipFactory.makeEnemyShip(typeofShip);
			if(theEnemy!=null) {
				doStuffEnemy(theEnemy);
			}
			else System.out.println("Please enter U,R, or B next time");
		}

	}
	public static void doStuffEnemy(EnemyShip anEnemyShip){
		anEnemyShip.displayEnemyShip();
		anEnemyShip.followHeroShip();
		anEnemyShip.enemyShipShoots();
		
	}

}
