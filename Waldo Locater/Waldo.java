import java.awt.Color;
import java.awt.Point;

/**
 * The Waldo Program calculates the sum of the absolute differences (SAD)
 * between the RGB values in the scene image and in the template image and
 * once a match has been found the program draws a box around Waldo.
 * 
 * @author Sameer Mehta
 * @version 1.0
 * @since 2014-11-16
 */
public class Waldo {

	/**
	 * Makes a Waldo object with no parameters.
	 */
	public Waldo() {
		
	}

	/**
	 * This method calculates the SADs given 2 pictures: a template and a scene.
	 * 
	 * @param template the template image (waldo)
	 * @param search the search image (scene)
	 * @return sads the 2D array containing all sad scores
	 */
	public int[][] sadMatch(Picture template, Picture search) {
	
		
		int[][] sads = new int[search.getWidth() - template.getWidth() - 1][search.getHeight() - template.getHeight() - 1];

		
		int[][][] waldo = convertoArray(template);
		
		int[][][] scene = convertoArray(search);
		int sad;



		// SADs

		for (int i = 0; i < search.getHeight() - template.getHeight() - 1; i++) {

			for (int j = 0; j < search.getWidth() - template.getWidth() - 1; j++) {
				sad = 0;
				for (int y = 0; y < template.getHeight(); y++) {
					for (int x = 0; x < template.getWidth(); x++) {
					
						// SAD values 
						sad += Math.abs(waldo[x][y][0] - scene[x + j][y + i][0]);

					}
				}
				sads[j][i] = sad;
			}
		}
		return sads;
	}

	/**
	 * This method searches a 2D array and returns a point where the lowest SAD value occurs.
	 * 
	 * @param array the 2D integer array
	 * @return point the row and column which has lowest SAD value.
	 */
	public Point find2DMin(int[][] array) {
		Point point = new Point();
		int lowestNumber = 999999;

		for (int col = 0; col < array[0].length; col++) {
			for (int row = 0; row < array.length; row++) {
				if (array[row][col] < lowestNumber) {
					lowestNumber = array[row][col];
					point.setLocation(row, col);
					if (lowestNumber == 0) {
						break;
					}
				}
			}
		}

		return point;
	}

	/**
	 * This method draws a box with the width and height of the template image
	 * onto the scene image at the x,y coordinates of the point.
	 * 
	 * @param search the search image (scene)
	 * @param template the template image (waldo)
	 * @param point the point containing x,y coordinate of lowest SAD value
	 */
	public void displayMatch(Picture search, Picture template, Point point) {
		int height = template.getHeight();
		int width = template.getWidth();

		// Middle, width
		for (int i = 0; i < width; i++) {
			search.getPixel(point.x + i, point.y).setColor(Color.WHITE);
			search.getPixel(point.x + i, point.y + height - 1).setColor(
					Color.WHITE);
		}
		if (point.x != 0 && point.y != 0 && point.x != search.getWidth() - 1
				&& point.y != search.getHeight() - 1) {
			// Outside, width
			for (int i = 0; i < width + 2; i++) {
				search.getPixel(point.x - 1 + i, point.y - 1).setColor(
						Color.WHITE);
				search.getPixel(point.x - 1 + i, point.y + height).setColor(
						Color.WHITE);
			}
			// Outside, height
			for (int i = 0; i < height; i++) {
				search.getPixel(point.x - 1, point.y + i).setColor(Color.WHITE);
				search.getPixel(point.x + width, point.y + i).setColor(
						Color.WHITE);
			}
		}
		// Inner, width
		for (int i = 0; i < width - 2; i++) {
			search.getPixel(point.x + i + 1, point.y + 1).setColor(Color.WHITE);
			search.getPixel(point.x + i + 1, point.y + height - 2).setColor(
					Color.WHITE);
		}
		// Middle, height
		for (int i = 0; i < height - 2; i++) {
			search.getPixel(point.x, point.y + 1 + i).setColor(Color.WHITE);
			search.getPixel(point.x + width - 1, point.y + 1 + i).setColor(
					Color.WHITE);
		}
		// Inner, height
		for (int i = 0; i < height - 4; i++) {
			search.getPixel(point.x + 1, point.y + 2 + i).setColor(Color.WHITE);
			search.getPixel(point.x + width - 2, point.y + 2 + i).setColor(
					Color.WHITE);
		}
	}

	/**
	 * This method returns a 3D integer array containing the RGB values for
	 * every pixel for the  picture.
	 * 
	 * @param pic any image
	 * @return array 3D integer array containing RBG values
	 */
	private int[][][] convertoArray(Picture pic) {
		int[][][] array = new int[pic.getWidth()][pic.getHeight()][3];

		for (int i = 0; i < pic.getHeight(); i++) {
			for (int j = 0; j < pic.getWidth(); j++) {
				array[j][i][0] = pic.getPixel(j, i).getRed();
				array[j][i][1] = pic.getPixel(j, i).getGreen();
				array[j][i][2] = pic.getPixel(j, i).getBlue();
			}
		}

		return array;
	}


}
