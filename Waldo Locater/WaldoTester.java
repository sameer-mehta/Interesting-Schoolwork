import java.awt.Point;

/**
 * Tester program for Waldo.
 * Used to test all the methods of Waldo program.
 * 
 * @author Sameer Mehta
 * @version 1.0
 * @since 2014-11-16
 */
public class WaldoTester
{

	/**
	* The main method:test all the methods of Waldo program.
	*/
	public static void main(String[] args) 
	{
	
		Picture template = new Picture("waldo.png");
		
		Picture scene = new Picture("scene.png");
	
		Waldo w = new Waldo();
		
		int [][] array =  w.sadMatch(template,scene);
		
		Point p = w.find2DMin(array);
		
		w.displayMatch(scene,template,p);
		
		scene.show();
		scene.write("WaldoBoxed.jpg");

		
		
	
		
	
	}
	
	
}