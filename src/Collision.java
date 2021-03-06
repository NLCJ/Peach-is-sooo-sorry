
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Janice Conquet
 */
public class Collision {

    public List<Label> collisions;
    public List<Point> sliderCollisions;

    public Collision() {
        collisions = new ArrayList<>();
        sliderCollisions = new ArrayList<>();
    }

    /**
     * Checks for intersections of labels between point p and q
     *
     * @param p: the first point
     * @param q: the second point
     * @return List of labels that overlap
     */
    public static void collide(Point p, List<Label> q, Map<Label, Set<Label>> coll) {
        List<Label> pLabels = p.getLabels();

        for (Label lp : pLabels) {
            for (Label lq : q) {
                if (lp.equals(lq)) {
                    continue;
                }
                if (intersects(lp, lq)) {
                    addCollisionToMap(coll, lp, lq);
                }
            }
        }
    }

    /**
     * Checks if two labels overlap
     *
     * @param p: first label
     * @param q: second label
     * @return p and q intersect
     */
    public static boolean intersects(Label p, Label q) {
//        if(p.getAnchor().getX() == q.getAnchor().getX()){
//            if (Math.abs(p.getReference().getY() - q.getReference().getY()) < MainReader.height) {
//                return true;
//            }
       // }
        //System.out.println(p + " " + q + " refrence p: " + p.getReference() + " reference g: "+ q.getReference()+ " refX - RefX: " + Math.abs(p.getReference().getX() - q.getReference().getX()));
        //System.out.println(p + " " + q + " anchor: " + q.getAnchor().getX() + " reference: " + q.getReference() +" " + Math.abs(p.getAnchor().getX() - q.getAnchor().getX()));
        /*if(p.getAnchor().getX() == q.getAnchor().getX() && p.getAnchor().getY() == q.getAnchor().getY() ){
            //System.out.println(p + " " + q + " are equal");
            //System.out.println(p + " " + q + " anchor: " + q.getAnchor().getX() + " reference: " + q.getReference() + " is false");
            return false;
        }
        
        if (Math.abs(p.getReference().getX() - q.getReference().getX()) <= MainReader.width ) {
            if (Math.abs(p.getReference().getY() - q.getReference().getY()) <= MainReader.height) {
                //System.out.println(p + " " + q + " is true " + Math.abs(p.getReference().getY() - q.getReference().getY()));
                return true;
            }
        }*/
        //System.out.println("p: " + p + " q: " + q);
        if((p.getAnchor().getX() == q.getAnchor().getX()) && (p.getAnchor().getY() == q.getAnchor().getY()) ){
           //if(p.getAnchor().getX() == 914 && p.getAnchor().getY() == 9292){
               //System.out.println("In equal: " + p + " " + q);
            //}
            return false;
        }
        
        //if the x of p is bigger than the x of pot
        if(p.getReference().getX() >= q.getReference().getX()){
            //calculate the gap and if the gap is less than 0
            double gap = (p.getReference().getX()) - (q.getReference().getX() + MainReader.width);
            if(gap < 0){
                if(p.getReference().getY() >= q.getReference().getY()){
                    //calculate the y gap and if the y gap is less than 0 return true
                    double ygap = p.getReference().getY() - (q.getReference().getY()+MainReader.height);
                    //System.out.println("p: " + p.getX() + " " + p.getY() + "pot: " + pot.getX() + " " + pot.getY() + " ygap p>=pot: " + ygap);
                    if(ygap < 0){
                        //if(p.getAnchor().getX() == 914 && p.getAnchor().getY() == 9292){
                            //System.out.println("Collision p>q & p>q: " + p.getReference() + " " + p.getPlacement() +" " + q.getReference() + " " + q.getPlacement());
                        //}
                        return true;
                    }
                }
                if(p.getReference().getY() < q.getReference().getY()){
                    //calculate the y gap and if the y gap is less than 0 return true
                    double ygap = q.getReference().getY() - (p.getReference().getY()+MainReader.height);
                    //System.out.println("p: " + p.getX() + " " + p.getY() + "pot: " + pot.getX() + " " + pot.getY() + " ygap p>=pot: " + ygap);
                    if(ygap < 0){
                        //if(p.getAnchor().getX() == 914 && p.getAnchor().getY() == 9292){
                            //System.out.println("Collision p>q & p<q: " + p.getReference() + " " + p.getPlacement() +" " + q.getReference() + " " + q.getPlacement());
                        //}
                        return true;
                    }
                }
            }
        }
        //if the x of p is less than the x of pot
        if(p.getReference().getX() < q.getReference().getX()){
            //calculate the gap and if the gap is less than 0
            double gap = (q.getReference().getX()) - (p.getReference().getX()+MainReader.width);
            //System.out.println("gap p<pot: " + gap);
            if(gap < 0){
                if(p.getReference().getY() >= q.getReference().getY()){
                    //calculate the y gap and if the y gap is less than 0 then return true
                    double ygap = p.getReference().getY() - (q.getReference().getY()+MainReader.height);
                    if(ygap < 0){
                        //if(p.getAnchor().getX() == 914 && p.getAnchor().getY() == 9292){
                            //System.out.println("Collision p<q & p>q: " + p.getReference() + " " + p.getPlacement() +" " + q.getReference() + " " + q.getPlacement());
                        //}
                        return true;
                    }
                }
                if(p.getReference().getY() < q.getReference().getY()){
                    //calculate the y gap and if the y gap is less than 0 then return true
                    double ygap = q.getReference().getY() - (p.getReference().getY()+MainReader.height);
                    if(ygap < 0){
                        //if(p.getAnchor().getX() == 914 && p.getAnchor().getY() == 9292){
                            //System.out.println("Collision p<q & p<q: " + p.getReference() + " " + p.getPlacement() +" " + q.getReference() + " " + q.getPlacement());
                        //}
                        return true;
                    }
                }
            }
        }
        //if(p.getAnchor().getX() == 914 && p.getAnchor().getY() == 9292 ){
            //System.out.println("NO Collision: " + p.getReference() + " " + p.getPlacement() +" " + q.getReference() + " " + q.getPlacement());
        //}
        return false;
    }

    /**
     * Adds a collision to a given collisions mapping
     *
     * @param collisions: map of current collisions
     * @param p: source label
     * @param q: overlapping label
     */
    private static void addCollisionToMap(Map<Label, Set<Label>> coll, Label p, Label q) {
        Set<Label> temp;
        final Point anchorP = p.getAnchor();
        final Point anchorQ = q.getAnchor();
        if (anchorP.getX() <= anchorQ.getX()) {
            if (anchorP.getX() < anchorQ.getX()) {
                temp = addCollisionL(coll, p, q);
                coll.put(p, temp);
            } else {
                if (anchorP.getY() < anchorQ.getY()) {
                    temp = addCollisionL(coll, p, q);
                    coll.put(p, temp);
                } else {
                    temp = addCollisionL(coll, q, p);
                    coll.put(q, temp);
                }
            }
        } else {
            temp = addCollisionL(coll, q, p);
            coll.put(q, temp);
        }
    }

    private static Set<Label> addCollisionL(Map<Label, Set<Label>> coll, Label p, Label q) {
        Set<Label> temp;
        //System.out.println("map: " + coll + " contains p: " + coll.containsKey(p));
        if (coll.containsKey(p)) {
            temp = coll.get(p);
        } else {
            temp = new HashSet<Label>();
        }
        temp.add(q);
        //System.out.println("collision p: " + p + " temp: " + temp);
        return temp;
    }

    public static void removeCollisionFromMap(Map<Label, Set<Label>> collisions, Label p, Label q) {
        final Set<Label> getP = collisions.get(p);
        
        if (getP != null &&  ! getP.isEmpty()) {
            getP.remove(q);
            collisions.get(p).remove(q);
            if (getP.isEmpty()) {
                collisions.remove(p);
            }
        }
        final Set<Label> getQ = collisions.get(q);
        if (getQ != null &&  ! getQ.isEmpty()) {
            getQ.remove(p);
            collisions.get(q).remove(p);
            if (getQ.isEmpty()) {
                collisions.remove(q);
            }
        }
    }

    public List allCollisions(List potential, Label l) {
        collisions.clear();
        for (Object potential1 : potential) {
            Label l2 = (Label) potential1;
            if (intersects(l, l2)) {
                collisions.add(l2);
            }
        }
        return collisions;
    }

    public static Map<Label, Set<Label>> allCollisions(List<Label> potential, Point p, Map<Label, Set<Label>> collisions) {
        collide(p, potential, collisions);
        return collisions;
    } 
    
    //------ 4pos Collisions
    
    /* --you can get the list of labels that collide with the label of point p (randomly assigned)--*/
    public List<Label> fourPosCollision(List<Point> potential, Point p) {
        collisions.clear();
        //get the label of the point p (which has a randomly assigned placement)
        Label l = p.getLabels().get(0);
        //for each point in the potential list
        for(int i = 0; i < potential.size(); i++){
            //get the label from the point q in the potential list (which has a randomly assigned placement)
            Point q = potential.get(i);
            Label l2 = q.getLabels().get(0);
            //if the labels intersect, add it to collision list
            if(intersects(l,l2)){
                collisions.add(l2);
            }
        }
        //System.out.println("Label: " + l + " collision size: " + collisions.size());
//        for(int i = 0; i < collisions.size(); i++){
//            //System.out.println("coll: " + collisions.get(i));
//        }
        //return the collisions
        return collisions;
    }
    
    /* --you can get a map with label and set of label like the 2pos--*/
    public Map<Label, Set<Label>> fourPosAllCollisions(List<Point> potential, Point p, Map<Label, Set<Label>> map){
        fourPosCollide(p, potential, map);
        return map;
    }
    
    public Map<Label, Set<Label>> fourPosCollide(Point p, List<Point> potential, Map<Label, Set<Label>> map) {
        List<Label> listL = fourPosCollision(potential, p);
        Label l = p.getLabels().get(0);
        for(int i = 0; i < listL.size(); i++){
            addCollisionToMap(map, l, listL.get(i));
        }
        return map;
    }
    
    
    //------- Slider Collisions
    public List sliderCollisions(List potential, Point p){
        sliderCollisions.clear();
        //for all the points in the list with potential collisions
        for(int i = 0; i < potential.size(); i++){
            //if sliderCollide is true
            Point pot = (Point)potential.get(i);
            if(sliderCollide(p, pot)){
                //add point to the sliderCollisions list
                Point pp = (Point) potential.get(i);
                sliderCollisions.add(pp);
            }
        }
        //for(int i = 0; i < sliderCollisions.size(); i++){
            //Point cp = (Point) sliderCollisions.get(i);
            //System.out.println("point: " + p.getX() + " " + p.getY() + " collides with: " + cp.getX() + " " + cp.getY());
        //}
        //return the list sliderCollisions
        return sliderCollisions;
    }
    
    public boolean sliderCollide(Point p, Point pot){
        if(p.getX() == pot.getX() && p.getY() == pot.getY()){
            return false;
        }
        //if the x of p is bigger than the x of pot
        if(p.getX() >= pot.getX()){
            //calculate the gap and if the gap is less than 0
            double gap = (p.getX() - MainReader.width) - (pot.getX() + MainReader.width);
            //System.out.println("p: " + p.getX() + " " + p.getY() + "pot: " + pot.getX() + " " + pot.getY() + " gap p>=pot: " + gap);
            if(gap < 0){
                if(p.getY() >= pot.getY()){
                    //calculate the y gap and if the y gap is less than 0 return true
                    double ygap = p.getY() - (pot.getY()+MainReader.height);
                    //System.out.println("p: " + p.getX() + " " + p.getY() + "pot: " + pot.getX() + " " + pot.getY() + " ygap p>=pot: " + ygap);
                    if(ygap < 0){
                        return true;
                    }
                }
                if(p.getY() < pot.getY()){
                    //calculate the y gap and if the y gap is less than 0 return true
                    double ygap = pot.getY() - (p.getY()+MainReader.height);
                    //System.out.println("p: " + p.getX() + " " + p.getY() + "pot: " + pot.getX() + " " + pot.getY() + " ygap p>=pot: " + ygap);
                    if(ygap < 0){
                        return true;
                    }
                }
            }
        }
        //if the x of p is less than the x of pot
        if(p.getX() < pot.getX()){
            //calculate the gap and if the gap is less than 0
            double gap = (pot.getX() - MainReader.width) - (p.getX() + MainReader.width);
            //System.out.println("gap p<pot: " + gap);
            if(gap < 0){
                if(p.getY() >= pot.getY()){
                    //calculate the y gap and if the y gap is less than 0 then return true
                    double ygap = p.getY() - (pot.getY()+MainReader.height);
                    if(ygap < 0){
                        return true;
                    }
                }
                if(p.getY() < pot.getY()){
                    //calculate the y gap and if the y gap is less than 0 then return true
                    double ygap = pot.getY() - (p.getY()+MainReader.height);
                    if(ygap < 0){
                        return true;
                    }
                }
            }
        }
        //if all fails then there's no collision and thus return false
        return false;
    }
}
