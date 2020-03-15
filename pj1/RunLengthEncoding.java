/* RunLengthEncoding.java */

package pj1;

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;
import java.util.Arrays;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  private DList runObject;
  private int width;
  private int height;

  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
    // Your solution here.
    // initialize DList variables
    runObject = new DList();
    this.width = width;
    this.height = height;

    // create black pixel
    int black = 0;
    int run_length = 1;
    Pixel pixel = new Pixel(black, black, black, run_length);

    //assign pixel to run-length encoding prototyp 
    for(int i=0; i<(width*height); i++){
    	runObject.insertBack(pixel);
    }

  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
    // Your solution here.
    //check prerequiste condition
    if((red.length != green.length)|| (red.length != blue.length) || (red.length != runLengths.length)){
    	System.out.println("RGB length is not equal. red:"+red.length+", green:"+green.length+", blue:"+blue.length+", run:"+runLengths.length+". ");
    	System.exit(1);
    }
    int sum = 0;
    for(int j=0; j< runLengths.length; j++){
    	sum += runLengths[j];
    }
    if(sum != (width*height)){
    	System.out.println("run length is not equal to pixel volume");
    	System.exit(1);
    }

    //initialize RunLengthEncoding object variables
    runObject = new DList();
    this.width = width;
    this.height = height;

    for(int i=0;i<red.length;i++){
    	//create Pixel
    	Pixel pixel = new Pixel(red[i],green[i],blue[i],runLengths[i]);

    	//assign each pixel to run prototype
    	runObject.insertBack(pixel);
    }

  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return this.width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return this.height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    // Replace the following line with your solution.
    //System.out.println("the input object is : "+ runObject.front());
    
    RunIterator i = new RunIterator(runObject.front());
    return i;
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
    PixImage resultImage = new PixImage(this.width, this.height);
    RunIterator i = this.iterator();
    int[] result = i.next();
    for(int n=0; n<this.getHeight(); n++){
      for(int m=0; m<this.getWidth(); m++){

        if(result[0] ==0){
          result = i.next(); 
        }
        //System.out.println("reslut[0] now is: "+ result[0]+"|| m: "+m+", n: "+n);
        resultImage.setPixel(m,n,(short)result[1],(short)result[2],(short)result[3]);
        result[0] = result[0] - 1;     
      }
    }
    return resultImage;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.

    return this.runObject.toString();
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
    // Your solution here, but you should probably leave the following line
    // at the end.
    runObject = new DList();
    this.width = image.getWidth();
    this.height = image.getHeight();
    short prev_color = image.getRed(0,0);
    Pixel pixel = new Pixel(prev_color, image.getGreen(0,0), image.getBlue(0,0), 0);
    int runLengths = 0;

    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        if(prev_color == image.getRed(j,i)){
          //add Pixel.run_length by one
          runLengths ++;
        }else{
          //initialize the final version of pixel
          pixel = new Pixel(pixel.red, pixel.green, pixel.blue, runLengths);
          // insert back the current Pixel
          runObject.insertBack(pixel);
          //assign prev_color with image.getRed(j,i)
          prev_color = image.getRed(j,i);
          //runLenghts go back to 1
          runLengths = 1;
          // initialize the new pixel
          pixel = new Pixel(image.getRed(j,i), image.getGreen(j,i), image.getBlue(j,i), 1);
        }
      }
    }
    // insert the last run object
    runObject.insertBack(pixel);
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
    // Your solution here.
    RunIterator i = this.iterator();
    int[] result = i.next();
    int prev_color_red = result[1];
    int prev_color_green = result[2];
    int prev_color_blue = result[3];
    int sum = result[0];

    //first check condition
    //System.out.println("the runObject.length() is : "+ runObject.length());
    for(int k=1; k<this.runObject.length();k++){
      //System.out.println("before i.next, k is :"+k);
      result = i.next();
      //System.out.println("after i.next, result is :"+ result);
      if(prev_color_red == result[1]|| prev_color_green == result[2] || prev_color_blue == result[3]||result[0]<1){
        System.out.println("Warning! Two consecutive runs have the same RGB intensities");
        return;
      }else{
        sum = sum + result[0];
        prev_color_red = result[1];
        prev_color_green = result[2];
        prev_color_blue = result[3];
      }
    }

    //second check condition
    if(sum != (this.width*this.height)){
      System.out.println("Warning! sum of all run lengths does not equal the number of pixels in the image.");
    }
    return;
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
  	int index = 0;
  	int position = (y*this.width)+x+1;
    //System.out.println("*********************");
    //System.out.println("the position is: "+position);
  	DListNode current = runObject.front();
    Pixel current_item;
    Pixel current_next_item;
    Pixel current_prev_item;

  	//iterate the list RunObject
  	for(int k=0; k<this.runObject.length();k++){
      current_item = (Pixel)(current.item);
      if(runObject.next(current) != null){
        current_next_item = (Pixel)((runObject.next(current)).item);
      }else{
        current_next_item = null;
      }
      if(runObject.prev(current) != null){
        current_prev_item = (Pixel)((runObject.prev(current)).item);
      }else{
        current_prev_item = null;
      }
  		index = index + current_item.runLengths;
      //System.out.println("the current index is :"+ index);
  		//locate the correct position
  		//incex == position
  		if(index == position){
  			//current Node value equals the changed value
  			if(current_item.red == red && current_item.green == green && current_item.blue == blue){
  				return;
  			}else{
  				if(current_item.runLengths != 1){
	  				//condition 1: current Node value == next Node value 
	  				if(current_next_item != null && red == current_next_item.red){
              //System.out.println("inside red == current_next_item.red statement");
	  					current_item.runLengths --;
              current_next_item.runLengths ++;
	  				}
	  				//condition 2: current Node value != next Node value && prev Node value 
	  				else{
	  				 	current_item.runLengths --;
	  				 	Pixel pixel = new Pixel(red, green, blue, 1);
	  				 	runObject.insertAfter(pixel, current);
	  				}

  				}else{
            //System.out.println("this runLengths == 1 !!!");
  					//condition 3: current Node value == prev Node value
  					if(runObject.prev(current) != null && red == current_prev_item.red){
		  				current_prev_item.runLengths ++;
              if(runObject.next(current) != null && red ==  current_next_item.red){
                // remove next Node if it is equals to the current Node and prev Node
                current_prev_item.runLengths = current_prev_item.runLengths + current_next_item.runLengths;
                runObject.remove(runObject.next(current));
              }
		  				runObject.remove(current);
  					}
	  				//condition 4: current Node value == next Node value
	  				else if(runObject.next(current) != null && red ==  current_next_item.red){
	  					current_next_item.runLengths ++;
	  					runObject.remove(current);

	  				}
		  			//condition 5: current Node value != next Node value && prev Node value 
		  			else{
              //System.out.println("current node value != next Node value && prev Node value");
		  				current_item.red = red;
		  				current_item.green = green;
		  				current_item.blue = blue;
		  			}
  				
  				}

  			}
        break;
  			//finish all conditions for index == position
  		}
  		//index > position
  		else if(index > position){
  			//current Node value equals the changed value
  			if(current_item.red == red && current_item.green == green && current_item.blue == blue){
  				return;
  			}else{
  				// position is not at the begining of run
  				if((index - position + 1) != current_item.runLengths){
	  				//condition 6: locate at the middle of the run, break the original run into three new run;
	  				Pixel current_px = new Pixel(red, green, blue, 1);
	  				Pixel post_px = new Pixel(current_item.red, current_item.green, current_item.blue, (index - position));
	  				current_item.runLengths = current_item.runLengths -1 -index + position;
	  				runObject.insertAfter(current_px, current);
	  				runObject.insertAfter(post_px, runObject.next(current));
  				}
  				// position is at the begining of run
  				else{
  					if(runObject.prev(current) != null && current_prev_item.red != red){
		  				//condition 8: position value != prev Node value, break the original run into two part, value not same as prev
		  				current_item.runLengths --;
		  				Pixel pixel = new Pixel(red,green,blue,1);
		  				runObject.insertBefore(pixel, current);
  					}
  					else if(runObject.prev(current) != null && current_prev_item.red == red){
	 	  				//condition 7:	position value = prev Node value, break the original run into two part, value same as prev
		  				current_prev_item.runLengths ++;
		  				current_item.runLengths --;
  					}else{
  						current_item.runLengths --;
		  				Pixel pixel = new Pixel(red,green,blue,1);
		  				runObject.insertBefore(pixel, current);
  					}
  				}
  				// finish position at/(not at) the begining of the run
  			}
        break;
  			//finish all conditions for index > position
  		}
  		else{
  			//incex < position do nothing, keep iterate
        //int p = k+1;
        //System.out.println("keep iterate for the "+p+" ths runObject");
  			current = runObject.next(current);
  		}
  	}
    // Your solution here, but you should probably leave the following line
    //   at the end.
    check();
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
    System.out.println("**************************");
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    System.out.println("The result of setPixel is:");
    System.out.println(rle);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);

    //int[] red = {7,88,0};
    //int[] runLengths = {3,5,4};

    //RunLengthEncoding rle0 = new RunLengthEncoding(4, 3,red,red,red,runLengths);
    //System.out.println("Testing first constructor:"+ rle0);
    //System.out.println("*****************************************");
    //RunIterator i = rle0.iterator();
    //for(int k=0; k<3; k++){
    //	int[] result = i.next();
    //	System.out.println( k+"the current run is : runLenght-"+result[0]+", red-"+result[1]+", green-"+result[2]+", blue-"+result[3]);
    //}
    //System.out.println("*****************************************");
    //System.out.println("Testing toPixImage() on a 4*3 encoding.");
    //PixImage image0 = array2PixImage(new int[][] { { 7,  88,  0},
    //                                               { 7,  88,  0},
    //                                               { 7,  88,  0},
    //                                               {88,  88,  0} });
    //doTest(image0.equals(rle0.toPixImage()),"image0 -> RLE0 -> image does not reconstruct the original image");
    //System.out.println("image0 : "+image0);
    //System.out.println("rel0.toPixImage() :"+ rle0.toPixImage());
    //System.out.println("*****************************************");
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    //System.out.println("Testing the third constructor :");
    //System.out.println(rle1);
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding. Setting RLE1[0][0] = 42");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding. Setting RLE1[1][0] = 42");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    //System.out.println("the correct answer is : "+image1);
    //System.out.println("my answer is : "+ rle1.toPixImage());
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");
    System.out.println("Testing setPixel() on a 3x3 encoding. Setting RLE1[0][1] = 2");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding. Setting RLE1[0][0] = 0");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.  Setting RLE1[2][2] = 7");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding. RLE1[2][2] = 42");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding. RLE1[1][2] = 42");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.Setting RLE2[0][1] = 2");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding. Setting RLE2[2][0] = 2");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding. Setting RLE3[4][0] = 6");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding. Setting RLE3[0][1] = 6");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding. Setting RLE3[0][0] = 1");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding. Setting RLE4[2][0] = 0");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding. Setting RLE4[1][0] = 0 ");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding. Setting RLE4[1][0] = 1");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
