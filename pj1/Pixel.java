/* Pixel.java */

package pj1;

/**
 *  A Pixel is the item for DListNode.
 *  
 */

public class Pixel {

	public int red;
	public int green;
	public int blue;
  public int runLengths;


  /**
   *  Pixel() constructor.
   *  @param r the red color.
   *  @param g the green color.
   *  @param b the blue color.
   */
	Pixel(int r, int g, int b, int l){
		red = r;
		green = g;
		blue = b;
    runLengths = l;
	}

  /**
   *  toString() returns a String representation of this Pixel.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this Pixel.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
	public String toString(){
		String result = " (red: " +red+ ", green: "+green+", blue: "+blue+", runLengths: "+runLengths+",) ";
		return result;

	}

}