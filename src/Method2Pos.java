
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stefan Habets
 */
public class Method2Pos {

    private Point[] result;
    ArrayList clauses = new ArrayList();
    ArrayList<Point> possibleCollisions = new ArrayList();
    Collision c = new Collision();
    QuadTree quad = new QuadTree(1, 0, 20, 0, 20);

    //The method which "calculates" the position of the labels
    public Point[] PositionCalculator(int w, int h, Point[] p) {
        //Set default position to NE
        for (Point point : p) {
            point.setPosition("NE");
        }
        //Return the point in the original order
        return originalOrder(p);
    }

//    public void makeLiterals() {
//        for (int i = 0; i < value; i ++) {
//            new Literal(i, true);
//            new Literal(i, false);
//        }
//        Literal li = new Literal(100, true);
//        Literal lit = new Literal(2, false);
//        makeClauses(li, lit);
//        Literal lite = new Literal(100, true);
//        Literal liter = new Literal(2, true);
//        makeClauses(lite, liter);
//        Literal litera = new Literal(100, false);
//        Literal literal = new Literal(2, false);
//        makeClauses(litera, literal);
////        Literal lita = new Literal(100, false);
////        Literal litar = new Literal(2, true);
////        makeClauses(lita, litar);
//        removeClauses(clauses);
//    }

    public void makeClauses(Literal one, Literal two) {
        Clause clause = new Clause(one, two);
        clauses.add(clause);
    }

    public void searchClauses(Point[] points) {
        List<Point> collisions = new ArrayList();
        for (Point p : points) {
            collisions.clear();
            collisions = realCollisions(posCollisions(p), p);
//        for (int j = 0; j < collisions.size(); j ++) {
//            Point point = (Point) collisions.get(j);
//            System.out.println(point.getX() + " aa " + point.getY());
//       }
            if (Arrays.asList(p.getTwoPosition()).contains("NE")) {
                Literal falseP = new Literal(p.getOrigin(), false);
                for (int i = 0; i < collisions.size(); i ++) {
                    if (collisions.get(i).equals(p)) {
                        break;
                    }
                    int value = collisions.get(i).getOrigin();
                    if (Arrays.asList(collisions.get(i).getTwoPosition()).contains("NE")) {
                        Literal falseCollision = new Literal(value, false);
                        makeClauses(falseP, falseCollision);
                    }
                    if (Arrays.asList(collisions.get(i).getTwoPosition()).contains("NW")) {
                        Literal trueCollision = new Literal(value, true);
                        makeClauses(falseP, trueCollision);
                    }
                }
            }
            if (Arrays.asList(p.getTwoPosition()).contains("NW")) {
                Literal trueP = new Literal(p.getOrigin(), true);
                for (Point collision : collisions) {
                    if (collision.equals(p)) {
                        break;
                    }
                    int value = collision.getOrigin();
                    if (Arrays.asList(collision.getTwoPosition()).contains("NE")) {
                        Literal falseCollision = new Literal(value, false);
                        makeClauses(trueP, falseCollision);
                    }
                    if (Arrays.asList(collision.getTwoPosition()).contains("NW")) {
                        Literal trueCollision = new Literal(value, true);
                        makeClauses(trueP, trueCollision);
                    }
                }
            }
        }
        for (int i = 0; i < clauses.size(); i ++) {
            System.out.println(clauses.get(i));
        }
        removeClauses(clauses);
    }

    public void removeClauses(List<Clause<Literal>> clauses) {
        while (TwoSat.isSatisfiable(clauses) != null) {
            Literal badPoint = TwoSat.isSatisfiable(clauses);

            System.out.println("niet nullo " + badPoint.toString());
            for (int j = 0; j < clauses.size(); j ++) {
                if (clauses.get(j).first().value() == badPoint.value() || clauses.get(j).second().value() == badPoint.value()) {
                    clauses.remove(j);
                    j --;
                }
            }
            for (int i = 0; i < clauses.size(); i ++) {
                System.out.println(clauses.get(i));
            }
        }
        System.out.println("nullo");
    }

    public void quadtreee(Point[] p) {
        for (Point points : p) {
            quad.insert(points);
        }
    }

    public ArrayList posCollisions(Point p) {

        possibleCollisions.clear();
        quad.retrieve(possibleCollisions, p);

//        for (int j = 0; j < possibleCollisions.size(); j ++) {
//            Point point = (Point) possibleCollisions.get(j);
//            System.out.println(point.getX() + " " + point.getY());
//        }
        return possibleCollisions;
    }

    public List realCollisions(ArrayList possiCollisions, Point p) {
        List<Point> col = new ArrayList();
        col.clear();
        col = c.allCollisions(possiCollisions, p);
//        for (int i = 0; i < testt.size(); i++) {
//            System.out.println(testt.get(i).getX() + " uu " + testt.get(i).getY());
//        }
        return col;
    }

//Puts the points back into their original order as it was documented.
    public Point[] originalOrder(Point[] p) {
        //Original order output
        Point[] originalOrder = new Point[p.length];

        //For each point - place at the original position
        for (Point point : p) {
            originalOrder[point.getOrigin()] = point;
        }

        // Store the result
        this.result = originalOrder;

        return originalOrder;
    }

    public void Output2Position(String s, int w, int h, int n_p, Point[] p) {
        //Reorder the points to the original order
        Point[] output = PositionCalculator(w, h, p);
        //Required static output
        System.out.println("placement model: " + s);
        System.out.println("width: " + w);
        System.out.println("height: " + h);
        System.out.println("number of points: " + n_p);
        System.out.println("numbers of labels: " + n_p);

        //Output each of the points
//        for (Point point : output) {
//            System.out.println(point.getX() + " " + point.getY() + " " + point.getPosition());
//        }
    }

    /**
     * Return the result
     *
     * @return
     */
    public Point[] getResult() {
        // Return the resutls
        return this.result;
    }
}
