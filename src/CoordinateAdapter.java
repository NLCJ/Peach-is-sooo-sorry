import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*                 ACCESS FOR STEPHAN HABBETS IS PROHIBITED   
                            ,ooo888888888888888oooo,
                          o8888YYYYYY77iiiiooo8888888o
                         8888YYYY77iiYY8888888888888888
                        [88YYY77iiY88888888888888888888]
                        88YY7iYY888888888888888888888888
                       [88YYi 88888888888888888888888888]
                       i88Yo8888888888888888888888888888i
                       i]        ^^^88888888^^^     o  [i
                      oi8  i           o8o          i  8io
                    ,77788o ^^  ,oooo8888888ooo,   ^ o88777,
                    7777788888888888888888888888888888877777
                     77777888888888888888888888888888877777
                      77777788888888^7777777^8888888777777
       ,oooo888 ooo   88888778888^7777ooooo7777^8887788888        ,o88^^^^888oo
    o8888777788[];78 88888888888888888888888888888888888887 7;8^ 888888888oo^88
   o888888iii788 ]; o 78888887788788888^;;^888878877888887 o7;[]88888888888888o
   88888877 ii78[]8;7o 7888878^ ^8788^;;;;;;^878^ ^878877 o7;8 ]878888888888888
  [88888888887888 87;7oo 777888o8888^;ii;;ii;^888o87777 oo7;7[]8778888888888888
  88888888888888[]87;777oooooooooooooo888888oooooooooooo77;78]88877i78888888888
 o88888888888888 877;7877788777iiiiiii;;;;;iiiiiiiii77877i;78] 88877i;788888888
 88^;iiii^88888 o87;78888888888888888888888888888888888887;778] 88877ii;7788888
;;;iiiii7iiii^  87;;888888888888888888888888888888888888887;778] 888777ii;78888
;iiiii7iiiii7iiii77;i88888888888888888888i7888888888888888877;77i 888877777ii78
iiiiiiiiiii7iiii7iii;;;i7778888888888888ii7788888888888777i;;;;iiii 88888888888
i;iiiiiiiiiiii7iiiiiiiiiiiiiiiiiiiiiiiiii8877iiiiiiiiiiiiiiiiiii877   88888
ii;;iiiiiiiiiiiiii;;;ii^^^;;;ii77777788888888888887777iii;;  77777           78
77iii;;iiiiiiiiii;;;ii;;;;;;;;;^^^^8888888888888888888777ii;;  ii7         ;i78
^ii;8iiiiiiii ';;;;ii;;;;;;;;;;;;;;;;;;^^oo ooooo^^^88888888;;i7          7;788
o ^;;^^88888^     'i;;;;;;;;;;;;;;;;;;;;;;;;;;;^^^88oo^^^^888ii7         7;i788
88ooooooooo         ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 788oo^;;          7;i888
887ii8788888      ;;;;;;;ii;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;^87           7;788
887i8788888^     ;;;;;;;ii;;;;;;;oo;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;,,,      ;;888
87787888888     ;;;;;;;ii;;;;;;;888888oo;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;,,;i788
87i8788888^       ';;;ii;;;;;;;8888878777ii8ooo;;;;;;;;;;;;;;;;;;;;;;;;;;i788 7
77i8788888           ioo;;;;;;oo^^ooooo ^7i88^ooooo;;;;;;;;;;;;;;;;;;;;i7888 78
7i87788888o         7;ii788887i7;7;788888ooooo7888888ooo;;;;;;;;;;;;;;oo ^^^ 78
i; 7888888^      8888^o;ii778877;7;7888887;;7;7788878;878;;    ;;;;;;;i78888o ^
i8 788888       [88888^^ ooo ^^^^^;;77888^^^^;;7787^^^^ ^^;;;;  iiii;i78888888
^8 7888^        [87888 87 ^877i;i8ooooooo8778oooooo888877ii; iiiiiiii788888888
  ^^^          [7i888 87;; ^8i;;i7888888888888888887888888   i7iiiiiii88888^^
               87;88 o87;;;;o 87i;;;78888788888888888888^^ o 8ii7iiiiii;;
               87;i8 877;77888o ^877;;;i7888888888888^^ 7888 78iii7iii7iiii
               ^87; 877;778888887o 877;;88888888888^ 7ii7888 788oiiiiiiiii
                 ^ 877;7 7888888887 877i;;8888887ii 87i78888 7888888888
                  [87;;7 78888888887 87i;;888887i  87ii78888 7888888888]
                  877;7 7788888888887 887i;887i^  87ii788888 78888888888
                  87;i8 788888888888887 887ii;;^ 87ii7888888 78888888888
                 [87;i8 7888888888888887 ^^^^   87ii77888888 78888888888
                 87;;78 7888888888888887ii      87i78888888 778888888888
                 87;788 7888888888888887i]      87i78888888 788888888888
                [87;88 778888888888888887       7ii78888888 788888888888
                87;;88 78888888888888887]       ii778888888 78888888888]
                7;;788 7888888888888888]        i7888888888 78888888888'
                7;;788 7888888888888888         'i788888888 78888888888
                7;i788 788888888888888]          788888888 77888888888]
                '7;788 778888888888888]         [788888888 78888888888'
                ';77888 78888888888888          8888888888 7888888888]
                 778888 78888888888888          8888888888 7888888888]
                  78888 7888888888888]         [8888888888 7888888888
                   7888 788888888888]          88888888888 788888888]
                    778 78888888888]           ]888888888 778888888]
                    oooooo ^88888^              ^88888^^^^^^^^8888]
                   87;78888ooooooo8o            ,oooooo oo888oooooo
                   [877;i77888888888]          [;78887i8888878i7888;
                    ^877;;ii7888ii788          ;i777;7788887787;778;
                     ^87777;;;iiii777          ;77^^^^^^^^^^^^^^^^;;
                        ^^^^^^^^^ii7]           ^ o88888888877iiioo
                           77777o               [88777777iiiiii;;778
                            77777iii            8877iiiii;;;77888888]
                            77iiii;8           [77ii;778 788888888888
                            7iii;;88           iii;78888 778888888888
                           77i;78888]          ;;;;i88888 78888888888
                          ,7;78888888          [;;i788888 7888888888]
                          i;788888888           ;i7888888 7888888888
                          ;788888888]           i77888888 788888888]
                          ';88888888'           [77888888 788888888]
                           [[8ooo88]             78888888 788888888
                            [88888]              78888888 788888888
                              ^^^                [7888888 77888888]
                                                  88888888 7888887
                                                  77888888 7888887
                                                   ;i88888 788888i
                                                  ,;;78888 788877i7
                                                 ,7;;i;777777i7i;;7
                                                 87778^^^ ^^^^87778
                                                  ^^^^ o777777o ^^^
                                                  o77777iiiiii7777o
                                                 7777iiii88888iii777
                                                ;;;i7778888888877ii;;
                                               [i77888888^^^^8888877i]
                                               77888^oooo8888oooo^8887]
                                              [788888888888888888888888]
                                              88888888888888888888888888
                                              ]8888888^iiiiiiiii^888888]
                                                iiiiiiiiiiiiiiiiiiiiii
                                                    ^^^^^^^^^^^^^
 */

/**
 *
 * @author ivank_000
 */
public class CoordinateAdapter {
   
    
    double[][] Payload(){
        double[][] earth_coordinates = new double[277][2];
        try {
            File file = new File("C:\\Users\\ivank_000\\Documents\\GitHub\\Peach-is-sooo-sorry\\src\\coordinates.txt");            
            Scanner sc = new Scanner(file);
            for (int i =0; i<277;i++) {
               earth_coordinates[i][0]=sc.nextDouble();
               earth_coordinates[i][1]=sc.nextDouble();   
            }   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainReader.class.getName()).log(Level.SEVERE, null, ex);
        }
   return earth_coordinates; 
       }
    
    int mapMinX(double[][] nl_map){
        int minX = 696969696;
        for(int i =0;i<nl_map.length;i++){
            if(minX>nl_map[i][0]){
                minX=(int)nl_map[i][0];
            }
        }
    return minX;
    }
    
    
    int mapMaxX(double[][] nl_map){
        int maxX = 0;
        for(int i =0;i<nl_map.length;i++){
            if(maxX<nl_map[i][0]){
                maxX=(int)nl_map[i][0];
            }
        }
    return maxX;
    }
    
     int mapMinY(double[][] nl_map){
        int minY = 696969696;
        for(int i =0;i<nl_map.length;i++){
            if(minY>nl_map[i][1]){
                minY=(int)nl_map[i][1];
            }
        }
    return minY;
    }
    
    
    int mapMaxY(double[][] nl_map){
        int maxY = 0;
        for(int i =0;i<nl_map.length;i++){
            if(maxY<nl_map[i][1]){
                maxY=(int)nl_map[i][1];
            }
        }
    return maxY;
    }
    
    
    int[][] recursivePointExtender(double[][] pnts){
         int[][] final_points = new int[pnts.length][2];    
   
    
    
    return final_points; 
    }
    
    void Converter(){
        double[][] CoC = Payload();
        for(int i = 0; i<CoC.length;i++){
            //range extender to get rid of the negatives and transforming to 
            //local system (whole Earth size is now mapped to the plane
            CoC[i][0]=(((CoC[i][0]+180)/360)*10000)-6000;
            CoC[i][1]=(((CoC[i][1]+90)/180)*5000)+7000;
            System.out.println((int)CoC[i][0]+" "+(int)CoC[i][1]);
        }
     System.out.println(mapMinX(CoC)+"<->"+mapMaxX(CoC)+" "+mapMinY(CoC)+"<->"+mapMaxY(CoC));
    }

    public static void main(String[] args) {
        new CoordinateAdapter().Converter();
    }
}
    
