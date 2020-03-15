/* Pixel.java */

package list;

/**
 *  A Pixel is the item for DListNode.
 *  
 */

public class Pixel {

	public short red;
	public short green;
	public short blue;


  /**
   *  Pixel() constructor.
   *  @param r the red color.
   *  @param g the green color.
   *  @param b the blue color.
   */
	Pixel(short r, short g, short b){
		red = r;
		green = g;
		blue = b;
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
		String result = " (red: " +red+ ", green: "+green+", blue: "+blue+",) ";
		return result;

	}

}