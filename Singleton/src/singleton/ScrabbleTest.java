package singleton;
import java.util.LinkedList;
public class ScrabbleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singleton newInstance= Singleton.getInstance();
		System.out.println("Instance ID: "+System.identityHashCode(newInstance));
		System.out.println(newInstance.getLetterList());
		LinkedList<String> playerOneTiles=newInstance.getLetterList();
		System.out.println("Player 1:"+playerOneTiles);
		System.out.println(newInstance.getLetterList());
		Singleton instanceTwo= Singleton.getInstance();
		System.out.println("Instance ID: "+System.identityHashCode(newInstance));
		System.out.println("Player 2:"+playerOneTiles);
	}

}
