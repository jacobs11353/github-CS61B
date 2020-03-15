/* PixImage.java */

package pj1;

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

import java.util.Arrays;

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */
  private int width;
  private int height;
  private short[][] pix_red;
  private short[][] pix_green;
  private short[][] pix_blue;


  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
  	this.width = width;
  	this.height = height;
  	this.pix_red = new short[width][height];
  	this.pix_green = new short[width][height];
  	this.pix_blue = new short[width][height];
    // Your solution here.
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return this.width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return this.height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return this.pix_red[x][y];
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return this.pix_green[x][y];
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return this.pix_blue[x][y];
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
  	this.pix_red[x][y] = red;
  	this.pix_green[x][y] = green;
  	this.pix_blue[x][y] = blue;
    // Your solution here.
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
  	String result = " ";
  	for(int i=0; i<this.getHeight(); i++){
  		result = result + "{ ";
  		for(int j=0; j<this.getWidth(); j++){
  			result = result + " ("+ j+","+i+"): [";
  			//result = result + "[ ";
  			result = result + this.getRed(j,i) + ", ";
  			result = result + this.getGreen(j,i) + ", ";
  			result = result + this.getBlue(j,i);
  		    result = result + " ], ";
  		}
  		result = result + " } \r\n";
  	}
    // Replace the following line with your solution.
    return result;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
    if(numIterations<1){
    	return this;
    }
    //System.out.println("1.inside boxBlur, numIterations:"+numIterations);
    if(this.width<2 || this.height<2){
    	System.exit(0);
    }
    short average_blue;
    short average;
    PixImage currentImage = new PixImage(this.width, this.height);
    // corner: average of four pixel 
	    //right-bottom
	    average_blue = (short)(this.getBlue(this.width-1, this.height-1)+ this.getBlue(this.width-2,this.height-1) + this.getBlue(this.width-1,this.height-2) + this.getBlue(this.width-2,this.height-2));
	    average = (short)(average_blue/4);
	    currentImage.setPixel(this.width -1, this.height -1, average, average, average);

	    // right-top
	    average_blue = (short)(this.getBlue(this.width-1, 0)+ this.getBlue(this.width-2,0) + this.getBlue(this.width-1,1) + this.getBlue(this.width-2,1));
	     average = (short)(average_blue/4);
	    currentImage.setPixel(this.width -1, 0, average, average, average);

	    //left-bottom
	    average_blue = (short)(this.getBlue(0, this.height-1)+ this.getBlue(0,this.height-2) + this.getBlue(1,this.height-1) + this.getBlue(1,this.height-2));
	    average = (short)(average_blue/4);
	    currentImage.setPixel(0, this.height -1, average, average, average);

	    //left-top
	    average_blue = (short)(this.getBlue(0, 0)+ this.getBlue(0,1) + this.getBlue(1,0) + this.getBlue(1,1));
	    average = (short)(average_blue/4);
	    currentImage.setPixel(0, 0, average, average, average);

	 // edge: average of six pixel 
	    //top
	    for(int m=1; m<this.width-1;m++){
	    	average_blue = (short)(this.getBlue(m,0)+this.getBlue(m-1,0)+this.getBlue(m+1,0)
	    					+this.getBlue(m,1)+this.getBlue(m-1,1)+this.getBlue(m+1,1));
	    	average = (short)(average_blue/6);
	    	currentImage.setPixel(m, 0, average, average, average);
	    }
	    //bottom
	    for(int m=1; m<this.width-1;m++){
	    	average_blue = (short)(this.getBlue(m,this.height-1)+this.getBlue(m-1,this.height-1)+this.getBlue(m+1,this.height-1)
	    					+this.getBlue(m,this.height-2)+this.getBlue(m-1,this.height-2)+this.getBlue(m+1,this.height-2));
			average = (short)(average_blue/6);
	    	currentImage.setPixel(m, this.height-1, average, average, average);
	    }
	    //left
	    for(int n=1; n<this.height-1;n++){
	    	average_blue = (short)(this.getBlue(0,n)+this.getBlue(0,n-1)+this.getBlue(0,n+1)
	    	               +this.getBlue(1,n)+this.getBlue(1,n-1)+this.getBlue(1,n+1));
	    	average = (short)(average_blue/6);           
	    	currentImage.setPixel(0, n, average, average, average);
	    }
	    //right
	    for(int n=1; n<this.height-1;n++){
	    	average_blue = (short)(this.getBlue(this.width-1,n)+this.getBlue(this.width-1,n-1)+this.getBlue(this.width-1,n+1)
	    				   +this.getBlue(this.width-2,n)+this.getBlue(this.width-2,n-1)+this.getBlue(this.width-2,n+1));
	        average = (short)(average_blue/6);            
	    	currentImage.setPixel(this.width-1, n, average, average, average);			   
	    }
	// innner: average of nine pixel
	    for(int j=1; j<height-1; j++){
	    	for(int i=1; i<width-1; i++){
	    		average_blue = (short)(this.getBlue(i,j)+this.getBlue(i-1,j)+this.getBlue(i+1,j)
	    		               +this.getBlue(i,j-1)+this.getBlue(i-1,j-1)+this.getBlue(i+1,j-1)
	    		               +this.getBlue(i,j+1)+this.getBlue(i-1,j+1)+this.getBlue(i+1,j+1));
	    	average = (short)(average_blue/9);
	    	currentImage.setPixel(i, j, average, average, average);
	    	}
	    }
    //System.out.println("2.currentImage:"+ currentImage);
    numIterations = numIterations - 1;
    //System.out.println("3.numIterations is :" + numIterations);
    return currentImage.boxBlur(numIterations);
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
  int  gx_red;
  int  gx_green;
	int  gx_blue;
	int  gy_red;
	int  gy_green;
	int  gy_blue;
	long energy;

  short[][] gx_op= new short[][]{{1,0,-1},{2,0,-2},{1,0,-1}};
  short[][] gy_op= new short[][]{{1,2,1},{0,0,0},{-1,-2,-1}};

  short[][] pixFrame = new short[3][3];

	PixImage currentImage = new PixImage(this.width, this.height);

    for(int i=0; i<this.width; i++){
      for(int j=0; j<this.height; j++){
        //red
        frame_matrix(pixFrame,i,j,0);
        //System.out.println("pix Frame in location ("+i+", "+j+") is : "+Arrays.deepToString(pixFrame));
        gx_red = matrixMulti(gx_op, pixFrame);
        gy_red = matrixMulti(gy_op, pixFrame);

        //green
        frame_matrix(pixFrame,i,j,2);
        gx_green = matrixMulti(gx_op, pixFrame);
        gy_green = matrixMulti(gy_op, pixFrame);

        //blue
        frame_matrix(pixFrame,i,j,3);
        gx_blue = matrixMulti(gx_op, pixFrame);
        gy_blue = matrixMulti(gy_op, pixFrame);

        // sum the square of the 3 gradients at each pixels
  
        //red
        energy = (long)(Math.pow(gx_red,2)+Math.pow(gy_red,2)+
                        Math.pow(gx_green,2)+Math.pow(gy_green,2)+
                        Math.pow(gx_blue,2)+Math.pow(gy_blue,2));
        currentImage.setPixel(i,j,mag2gray(energy),mag2gray(energy),mag2gray(energy));

      }
    }

    return currentImage;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }

  public void frame_matrix(short[][] pixFrame, int x, int y, int color){

      int x_index = 0;
      int y_index = 0;

      for(int i=0; i<3; i++ ){
        for(int j=0; j<3; j++){
          // x level
          if ((x-1+i)<0){
            x_index = 0;
          }else if((x-1+i)>this.width-1){
            x_index = this.width-1;
          }else{
            x_index = x-1+i;
          }

          //y level
          if((y-1+j)<0){
            y_index = 0;
          }else if((y-1+j)>this.height-1){
            y_index = this.height-1;
          }else{
            y_index = y-1+j;
          }

          //color level
          if(color == 0){
            pixFrame[i][j] = this.getRed(x_index,y_index);
          }else if(color ==1){
            pixFrame[i][j] = this.getGreen(x_index,y_index);
          }else{
            pixFrame[i][j] = this.getBlue(x_index,y_index);
          }

        }

      }
      return;
  }

  public short matrixMulti(short[][] operator, short[][] pixFrame){
    short sum =0;
    for(int i=0; i<3; i++){
      for(int j=0; j<3; j++){
        sum += operator[i][j]* pixFrame[i][j];
      }
    }
    return sum;
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
   // System.out.println("width:" + width);
   // System.out.println("height:" + height);
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
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
        //PixImage image1 = array2PixImage(new int[][] { {1, 0, -1}, {2, 0, -2}, {1, 0, -1} });
        //System.out.println("image1.(0,0): "+image1.getRed(0,0)+" "+image1.getGreen(0,0)+" "+image1.getBlue(0,0));

       // System.out.println("image1.(0,1): "+image1.getRed(0,1)+" "+image1.getGreen(0,1)+" "+image1.getBlue(0,1));

       // System.out.println("image1.(0,2): "+image1.getRed(0,2)+" "+image1.getGreen(0,2)+" "+image1.getBlue(0,2));

       // System.out.println("***************************************************");
      //          System.out.println("image1.(1,0): "+image1.getRed(1,0)+" "+image1.getGreen(1,0)+" "+image1.getBlue(1,0));

        //System.out.println("image1.(1,1): "+image1.getRed(1,1)+" "+image1.getGreen(1,1)+" "+image1.getBlue(1,1));

       // System.out.println("image1.(1,2): "+image1.getRed(1,2)+" "+image1.getGreen(1,2)+" "+image1.getBlue(1,2));
        //        System.out.println("***************************************************");
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.println(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    System.out.println(image1.boxBlur(1));
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}



  // // corner: average of four pixel 
  //     //right-bottom
  //   gx_red = 3*(this.getRed(this.width-2,this.height-1)-this.getRed(this.width-1,this.height-1))+this.getRed(this.width-2,this.height-2)-this.getRed(this.width-1,this.height-2);
  //   gy_red = 3*(this.getRed(this.width-1,this.height-2)-this.getRed(this.width-1,1))+this.getRed(this.width-2,0)-this.getRed(this.width-2,1);
      
  //     energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //     currentImage.setPixel(this.width -1, this.height -1, mag2gray(energy), mag2gray(energy), mag2gray(energy));

  //     // right-top
  //   gx_red = 3*(this.getRed(this.width-2,0)-this.getRed(this.width-1,0))+this.getRed(this.width-2,1)-this.getRed(this.width-1,1);
  //   gy_red = 3*(this.getRed(this.width-1,0)-this.getRed(this.width-1,1))+this.getRed(this.width-2,0)-this.getRed(this.width-2,1);
      
  //     energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //     currentImage.setPixel(this.width -1, this.height -1, mag2gray(energy), mag2gray(energy), mag2gray(energy));

  //     //left-bottom
  //     gx_red = this.getRed(0,this.height-2)-this.getRed(1,this.height-2)+ 3*(this.getRed(0,this.height-1)-this.getRed(1,height-1));
  //     gy_red = this.getRed(1,this.height-2)-this.getRed(1,height-1)+3*(this.getRed(0,height-2)-this.getRed(0,height-1));

  //     energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //     currentImage.setPixel(this.width -1, this.height -1, mag2gray(energy), mag2gray(energy), mag2gray(energy));
      
  //     //left-top
  //     gx_red = 3*(this.getRed(0,0)-this.getRed(1,0))+this.getRed(0,1)-this.getRed(1,1);
  //     gy_red = 3*(this.getRed(0,0)-this.getRed(0,1))+this.getRed(1,0)-this.getRed(1,1);

  //     energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //     currentImage.setPixel(this.width -1, this.height -1, mag2gray(energy), mag2gray(energy), mag2gray(energy));

  //  // edge: average of six pixel 
  //     //top
  //     for(int m=1; m<this.width-1;m++){
  //       gx_red = 3*(this.getRed(m-1,0)-this.getRed(m+1,0))+this.getRed(m-1,1)-this.getRed(m+1,1);
  //       gy_red = this.getRed(m-1,0)+2*(this.getRed(m,0))+this.getRed(m+1,0)-this.getRed(m-1,1)-2*(this.getRed(m,1))-this.getRed(m+1,1); 
        
  //       energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //       currentImage.setPixel(m, 0, mag2gray(energy), mag2gray(energy), mag2gray(energy));
  //     }
  //     //bottom
  //     for(int m=1; m<this.width-1;m++){
  //       gx_red = 3*(this.getRed(m-1,height-1)-this.getRed(m+1,height-1))+this.getRed(m-1,height-2)-this.getRed(m+1,height-2);
  //       gy_red = this.getRed(m-1,height-2)+2*(this.getRed(m,height-2))+this.getRed(m+1,height-2)
  //                -this.getRed(m-1,height-1)-2*(this.getRed(m,height-1))-this.getRed(m+1,height-1); 
      
  //     energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //       currentImage.setPixel(m, this.height-1, mag2gray(energy), mag2gray(energy), mag2gray(energy));
  //     }
  //     //left
  //     for(int n=1; n<this.height-1;n++){
  //       gx_red = this.getRed(width-2,n-1)+2*(this.getRed(width-2,n))+this.getRed(width-2,n+1)
  //               -this.getRed(width-1,n-1)-2*(this.getRed(width-1,n))-this.getRed(width-1,n+1);
  //       gy_red = 3*(this.getRed(width-2,n-1)-this.getRed(width-2,n+1))+this.getRed(width-1,n-1)-this.getRed(width-1,n+1);
        
  //       energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //       currentImage.setPixel(0, n, mag2gray(energy), mag2gray(energy), mag2gray(energy));
  //     }
  //     //right
  //     for(int n=1; n<this.height-1;n++){
  //       gx_red = this.getRed(0,n-1)+2*(this.getRed(0,n))+this.getRed(0,n+1)-this.getRed(1,n-1)-2*(this.getRed(1,n))-this.getRed(1,n+1);
  //       gy_red = 3*(this.getRed(0,n-1)-this.getRed(0,n+1))+this.getRed(1,n-1)-this.getRed(1,n+1); 
        
  //       energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));          
  //       currentImage.setPixel(this.width-1, n, mag2gray(energy), mag2gray(energy), mag2gray(energy));        
  //     }
  // // innner: average of nine pixel
  //     for(int j=1; j<height-1; j++){
  //       for(int i=1; i<width-1; i++){
  //         gx_red = this.getRed(i-1,j-1)+2*(this.getRed(i-1,j))+this.getRed(i-1,j+1)
  //                   -this.getRed(i+1,j-1)+2*(this.getRed(i+1,j))+this.getRed(i+1,j+1);

  //         gy_red = this.getRed(i-1,j-1)+2*(this.getRed(i,j-1))+this.getRed(i+1,j-1)-this.getRed(i-1,j+1)-2*(this.getRed(i,j+1))-this.getRed(i+1,j+1);

  //         energy = (long)(Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2)+(int)Math.pow(gx_red,2)+(int)Math.pow(gy_red,2));
  //       currentImage.setPixel(i, j, mag2gray(energy), mag2gray(energy), mag2gray(energy));
  //       }
  //     }