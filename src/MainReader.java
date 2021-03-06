
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ivan Kovlov
 */
class MainReader {
    
    public static int width;
    public static int height;
    public static int numberLabels;
    public static String placement_model;
    public static String distribution;
    public static int mapNumber;
    public static int MAXLEVEL;
    public static int MAXPOINTS;
    
    public MainReader() {
        
    }
    ExperimentOutput EO = ExperimentOutput.getExperimentOutput();;
    Method2Pos pos_2 = new Method2Pos();
    Method4Pos pos_4 = new Method4Pos();
    MethodSlider slider = new MethodSlider();
    MergeSort mergesort = new MergeSort();
    public static Point[] points;
    public static Model pModel;
    
    private JFrame f;
    private JPanel p;
    private JLabel l;
    private JPanel plot;
    
    /*
    In order to view the panel correcly
    switch to full screen mode.
    By default you will see small window
    which tells you what positioning model is used
    and message about going fullscreen
    */
    public void Gui(String s, int n, Point[] pnt) {
        double[] shift = new double[pnt.length];
        int[][] x_p = new int[pnt.length][2];
        String[] pos_s = new String[pnt.length];
        String pos_ = s;
        f = new JFrame("Label point plot: " + s + " model");
        f.setVisible(true);
        f.setSize(500, 55);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setExtendedState(Frame.MAXIMIZED_BOTH);
        p = new JPanel();
        plot = new PlotPanel(x_p, shift, pos_s, pos_, pnt);
        plot.setSize(10000, 10000);
        p.setLayout(new BorderLayout());
        p.setBackground(Color.YELLOW);
        l = new JLabel(s + " go fullscreen");
        p.add(l, BorderLayout.NORTH);
        f.add(p, BorderLayout.NORTH);
        f.add(plot, BorderLayout.CENTER);
        JPanel test = new JPanel();
        plot.setPreferredSize(new Dimension(10000, 10000));
        JScrollPane scrollFrame = new JScrollPane(plot);
        plot.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(930, 970));
        f.add(scrollFrame);
        
        if (s.equals("2pos")) {
            for (int i = 0; i < pnt.length; i ++) {
                x_p[i][0] = (int) pnt[i].getX();
                x_p[i][1] = (int) pnt[i].getY();
                
                if ( ! (pnt[i].getLabels().isEmpty())) {
                    pos_s[i] = pnt[i].getLabels().get(0).getPlacement().toString();
                }
            }
        }
    }
    
    void Reader() {
        //  System.out.println("Reading file");
        try {
            
            
            
            File file = new File("C:\\Users\\s130973\\Dropbox\\DBL Algorithms\\DataGenerating\\Data testing\\Additional for experiment (not QuadTree)\\4posCluster200x100#7.txt");
            //file = new File("2kDimension55.txt");
            
            //file = new File("input.txt");
            
            
            
            //file = new File("input7.txt");
            
            
            Scanner sc = new Scanner(file);
            
            // Get the model data
            placement_model = sc.nextLine().substring(17);
            pModel = Model.fromString(placement_model);
            width = Integer.parseInt(sc.nextLine().substring(7));
            height = Integer.parseInt(sc.nextLine().substring(8));
            int number_points = Integer.parseInt(sc.nextLine().substring(18));
            numberLabels = number_points;
            // Create array for points
            points = new Point[number_points];
            // Place each point in the array
            for (int i = 0; i < number_points; i ++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                
                points[i] = new Point(x, y, i, Model.fromString(placement_model));
            }
            
            // Determine what placement model is called for
            if (placement_model.equals("2pos")) {
                //Point[] points_2pos = pos_2.PositionCalculator(width, height, points);
                mergesort.sort(points);
                pos_2.quadtree(points);
                pos_2.findCollisions(points);
                //pos_2.searchClauses(points);
                //pos_2.makeLiterals();
                pos_2.Output2Position(placement_model, width, height, number_points, points);
                //Gui(placement_model, number_points, points_2pos);
                DBLGUI gui = new DBLGUI();
            }
            if (placement_model.equals("4pos")) {
                Point[] points_4pos = pos_4.PositionCalculator(width, height, points);
                pos_4.Annealing(points);
                pos_4.Output4Position(placement_model, width, height, number_points, points_4pos);
                //Gui(placement_model, number_points, points_4pos);
                DBLGUI gui = new DBLGUI();
            }
            if (placement_model.equals("1slider")) {
                Point[] points_slider = slider.originalOrder(points);
                slider.OutputSlider(placement_model, width, height, number_points, points_slider);
                Gui(placement_model, number_points, points_slider);
                //DBLGUI gui = new DBLGUI();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void multipleFiles() throws IOException{
        try {
            
            // Directory path here. Depending on the experiment you're running.
            //String path = "D:\\Documents\\NetBeansProjects\\Peach-is-sooo-sorry\\Experimental Data\\QuadTreeExperiment";
            String path = "/home/nlcj/algorithms/experimental/quadtree/";
            //String path = "D:\\Documents\\NetBeansProjects\\Peach-is-sooo-sorry\\Experimental Data\\2posExperiment";
            //String path = "D:\\Documents\\NetBeansProjects\\Peach-is-sooo-sorry\\Experimental Data\\4posExperiment";
            
            File folder = new File(path);
            File[] files = folder.listFiles();
            
            // Check if there are files
            if( files.length > 0 ) {
                // For each file, run the algorithm
                int fileCounter = 0;
                for( File file : files ) {
                    fileCounter++;
                    //TODO: include for quadtree experiment
                    /*
                    int[] forLoopValues = {5, 10, 25, 50, 100, 150, 200, 250};
                    for (int j = 0; j < forLoopValues.length; j++){
                        this.MAXLEVEL = forLoopValues[j];
                        for (int k = 0; k < forLoopValues.length; k++){
                            this.MAXPOINTS = forLoopValues[k];
                            */
                            // Get the content
                            Scanner sc = new Scanner( file );
                            
                            System.out.println( fileCounter + "/" + files.length + " " + this.MAXLEVEL + "-" + this.MAXPOINTS + " " + file );
                            
                            // Get the model data
                            placement_model = sc.nextLine().substring(17);
                            pModel = Model.fromString(placement_model);
                            width = Integer.parseInt(sc.nextLine().substring(7));
                            height = Integer.parseInt(sc.nextLine().substring(8));
                            int number_points = Integer.parseInt(sc.nextLine().substring(18));
                            numberLabels = number_points;
                            String temp = file.getName().substring(placement_model.length());
                            distribution = temp.startsWith("Cluster")?"Cluster":temp.startsWith("Even")?"Even":"Real";
                            String[] parts = temp.split("#");
                            mapNumber = Integer.parseInt(parts[1].substring(0, parts[1].length()-4));
                            
                            // Create array for points
                            points = new Point[number_points];
                            
                            // Place each point in the array
                            for (int i = 0; i < number_points; i ++) {
                                
                                int x = sc.nextInt();
                                int y = sc.nextInt();
                                
                                points[i] = new Point(x, y, i, Model.fromString(placement_model));
                            }
                            
                            // Run algorithm according to the placement model
                            switch( placement_model ) {
                                case "2pos":
                                    //TODO: Include for model experiment. Start experiment. 
                                    /*
                                    long startTime = System.nanoTime();
                                    */
                                    // Get the output of slider and place it in a file
                                    // Copied from above - ask Stefan
                                    pos_2 = new Method2Pos();
                                    Point[] points_2pos = pos_2.PositionCalculator(width, height, points);
                                    pos_2.quadtree(points);
                                    pos_2.findCollisions(points);
                                    //pos_2.Output2Position(placement_model, width, height, number_points, points);
                                    //TODO: Include for model experiment. End experiment. 
                                    /*
                                    long endTime = System.nanoTime();
                                    long totalTime = endTime - startTime;
                                    EO.modelArrays(totalTime);
                                    */
                                    break;
                                case "4pos":
                                    //TODO: Include for model experiment. Start experiment. 
                                    /*
                                    long startTime = System.nanoTime();
                                    */
                                    // Get the output of slider and place it in a file
                                    // Copied from above - ask Stefan
                                    pos_4 = new Method4Pos();
                                    Point[] points_4pos = pos_4.PositionCalculator(width, height, points);
                                    pos_4.Annealing(points);
                                    //pos_4.Output4Position(placement_model, width, height, number_points, points_4pos);
                                    //TODO: Include for model experiment. End experiment. 
                                    /*
                                    long endTime = System.nanoTime();
                                    long totalTime = endTime - startTime;
                                    EO.modelArrays(totalTime);
                                    */
                                    break;
                                case "1slider":
                                    //TODO: Include for model experiment. Start experiment. 
                                    /*
                                    long startTime = System.nanoTime();
                                    */
                                    // Get the output of slider and place it in a file
                                    slider = new MethodSlider();
                                    Point[] points_slider = slider.originalOrder(points);
                                    slider.OutputSlider(placement_model, width, height, number_points, points_slider);
                                    //TODO: Include for model experiment. End experiment. 
                                    /*
                                    long endTime = System.nanoTime();
                                    long totalTime = endTime - startTime;
                                    EO.modelArrays(totalTime);
                                    */
                                    break;
                                default:
                                    // Unknown placement model
                                    System.out.println( "This placement model is not supported" );
                                    file.delete();
                                    break;
                            }
                        //TODO: include for Quadtree experiment
                        //}
                    //TODO: include for quadtree experiment
                    //}
                }
                EO.closeExperiment();
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) throws IOException {
        new MainReader().Reader();
    }
}
