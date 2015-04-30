
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s124006
 */
public class DataGenerator {
    // Variables
    String placementModel;
    int labelWidth;
    int labelHeight;
    int amountOfPoints;
    int maxAxisX;
    int maxAxisY;
    int minX;
    int maxX;
    int minY;
    int maxY;
    double accuracy;
    PrintWriter file;
    Scanner input;
    
    // Constructor
    private void askData() {
        try {
            // Try to get input from the user
            input = new Scanner( System.in ).useLocale( Locale.US );
            
            // Store the variables
            System.out.print("Enter the placement model (2pos, 4pos, 1slider): ");
            this.placementModel = input.nextLine();
            System.out.print("Enter the width of the label: ");
            this.labelWidth = input.nextInt();
            System.out.print("Enter the height of the label: ");
            this.labelHeight = input.nextInt();
            System.out.print("Enter the amount of points: ");
            this.amountOfPoints = input.nextInt();
            System.out.print("Maximum x-axis: ");
            this.maxAxisX = input.nextInt();
            System.out.print("Maximum y-axis: ");
            this.maxAxisY = input.nextInt();
            System.out.print("Enter the minimum integer on the x-axis: ");
            this.minX = input.nextInt();
            System.out.print("Enter the maximum integer on the x-axis: ");
            this.maxX = input.nextInt();
            System.out.print("Enter the minimum integer on the y-axis: ");
            this.minY = input.nextInt();
            System.out.print("Enter the maximum integer on the y-axis: ");
            this.maxY = input.nextInt();
            System.out.print("Enter the accuracy (0 to 1), 1 is 100% accurate: ");
            this.accuracy = input.nextDouble();
            
            // Initialize the file
            file = new PrintWriter("data-of-awesomeness.txt", "UTF-8");
            
            // Print the default information
            file.println( "placement model: " + this.placementModel );
            file.println( "width: " + this.labelWidth );
            file.println( "height: " + this.labelHeight );
            file.println( "number of points: " + this.amountOfPoints );
            
            // Add output
            output();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(DataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Generate the output
    private void output() {
        // For each point
        for( int i = 0; i < amountOfPoints; i++ ) {
            // Create x and y limits with accuracy
            int accurateMinX = (int) Math.round( this.minX * accuracy );
            int accurateMaxX = (int) Math.round( this.maxX / accuracy );
            int accurateMinY = (int) Math.round( this.minY * accuracy );
            int accurateMaxY = (int) Math.round( this.maxY / accuracy );
            
            // Check if the Y is outside of the screen
            if( accurateMaxY > this.maxAxisY ) {
                // Set to max Y
                accurateMaxY = this.maxAxisY;
            }
            
            // Check if the Y is outside of the screen
            if( accurateMaxX > this.maxAxisX ) {
                // Set to max Y
                accurateMaxX = this.maxAxisX;
            }
            
            // Get the x and y value
            int xValue = randInt( accurateMinX, accurateMaxX );
            int yValue = randInt( accurateMinY, accurateMaxY );
            
            // Write this point to the file
            file.println( xValue + " " + yValue );
        }
        
        // Close the file
        file.close();
    }
    
    /**
    * Returns a pseudo-random number between min and max, inclusive.
    * The difference between min and max can be at most
    * <code>Integer.MAX_VALUE - 1</code>.
    *
    * @param min Minimum value
    * @param max Maximum value.  Must be greater than min.
    * @return Integer between min and max, inclusive.
    * @see java.util.Random#nextInt(int)
    */
    public static int randInt(int min, int max) {

       // NOTE: Usually this should be a field rather than a method
       // variable so that it is not re-seeded every call.
       Random rand = new Random();

       // nextInt is normally exclusive of the top value,
       // so add 1 to make it inclusive
       int randomNum = rand.nextInt((max - min) + 1) + min;

       return randomNum;
    }
   
    public static void main(String[] args) {
        new DataGenerator().askData();
    }
}
