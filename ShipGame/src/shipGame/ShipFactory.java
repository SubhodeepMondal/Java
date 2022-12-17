package shipGame;

public class ShipFactory {
	public EnemyShip makeEnemyShip(String newShipType) {
		if(newShipType.equals("U")) {
			return new UFOShip();
		}
		else if(newShipType.equals("R")){
			return new RocketShip();
		}
		else if(newShipType.equals("B")) {
			return new BigUFOShip();
		}
		else return null;
	}
}
