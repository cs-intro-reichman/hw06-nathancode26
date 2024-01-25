// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);
		Color[][] thor = read("thor.ppm");
		Color[][] ironman = read("ironman.ppm");
		morph(ironman,thor, 4);

		// Creates an image which will be the result of various 
		// image processing operations:
		//Color[][] imageOut;
		// Tests the horizontal flipping of an image:
		//imageOut = scaled(tinypic,3,5);
		//System.out.println();
		//print(imageOut);
		//System.out.println();
		//Color[][] imageOut2=flippedVertically(tinypic);
		//print(imageOut2);
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		Color image[][] = new Color[numRows][numCols];
		in.readInt();
		for(int i=0;i<numRows;i++){
			for (int j=0;j<numCols;j++){
					image[i][j]=new Color(in.readInt(),in.readInt(),in.readInt());
			}
		}
		// Creates the image array
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		int len_rows=image.length;
		int len_cols=image[0].length;
		System.out.println(len_rows+ " " +len_cols);
		for(int i=0;i<len_rows;i++){
			for(int j=0;j<len_cols;j++){
				print(image[i][j]);
			}
			System.out.println();
		}
		//// Replace this comment with your code
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int len_rows=image.length;
		int len_cols=image[0].length;
		for(int i=0;i<len_rows;i++)
			for(int j=0;j<len_cols/2;j++){
				Color temp=image[i][j];
				image[i][j]=image[i][len_cols-1-j];
				image[i][len_cols-1-j]=temp;
			}
		//// Replace the following statement with your code
		return image;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int len_rows=image.length;
		int len_cols=image[0].length;
		for(int i=0;i<len_rows/2;i++)
			for(int j=0;j<len_cols;j++){
				Color temp=image[i][j];
				image[i][j]=image[len_rows-1-i][j];
				image[len_rows-1-i][j]=temp;
			}
		//// Replace the following statement with your code
		return image;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		int red = pixel.getRed();
		int green = pixel.getGreen();
		int blue = pixel.getBlue();
		int  lum = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
		Color pix = new Color(lum,lum,lum);
		//// Replace the following statement with your code
		return pix;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		int len_rows=image.length;
		int len_cols=image[0].length;
		for(int i=0;i<len_rows;i++)
			for(int j=0;j<len_cols;j++){
				image[i][j]=luminance(image[i][j]);
			}
		//// Replace the following statement with your code
		return image;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		double len_rows=image.length;
		double len_cols= image[0].length;
		Color output_image[][] = new Color[height][width];
		for(int i=0;i<height;i++){
			for(int j =0;j<width;j++){
				int r = (int)(i*(len_rows/height));
				int c = (int)(j*(len_cols/width));
				output_image[i][j]=image[r][c];
			}
		}
		//// Replace the following statement with your code
		return output_image;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int red = (int)(alpha*(double)c1.getRed()+(1.0-alpha)*(double)c2.getRed());
		int green = (int)(alpha*(double)c1.getGreen()+(1.0-alpha)*(double)c2.getGreen());
		int blue = (int)(alpha*(double)c1.getBlue()+(1.0-alpha)*(double)c2.getBlue());
        return new Color(red,green,blue);
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int len_rows=image1.length;
		int len_cols=image1[0].length;
		Color[][] image3 = new Color[len_rows][len_cols];
		for(int i=0 ;i<len_rows;i++){
			for(int j=0; j<len_cols;j++){
				image3[i][j]=blend(image1[i][j],image2[i][j],alpha);
			}
		}
		//// Replace the following statement with your code
		return image3;
	}
	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		int len_r_s = source.length;
		int len_c_s =source[0].length;
		int len_r_t = target.length;
		int len_c_t =target[0].length;
		setCanvas(source);
		display(target);
		double alpha;
		StdDraw.pause(3000);
		if(len_r_t!=len_r_s || len_c_t!=len_c_s){
			Color[][] new_target =scaled(target,len_c_s,len_r_s);
			display(new_target);
			for(int i=0 ;i<n;i++){
				alpha=((double)(n-i))/((double)n);
				new_target=blend(source,new_target,alpha);;
				Runigram.setCanvas(source);
			}
		}
		else {
			for (int j=0 ;j<n;j++){
				alpha=((double)(n-j))/((double)n);
				target=blend(source,target,alpha);
				Runigram.setCanvas(source);
				display(target);
			}
		}
		//// Replace this comment with your code
	}

	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

