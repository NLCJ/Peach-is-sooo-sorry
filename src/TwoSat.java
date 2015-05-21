
import java.util.*;

/**
 *
 * @author Stefan Habets
 */
public final class TwoSat {

    public static DirectedGraph g;
    /**
     * Given as input a list of clauses representing a 2-CNF formula, returns
     * whether that formula is satisfiable.
     *
     * @param formula The input 2-CNF formula.
     * @return Whether the formula has a satisfying assignment.
     */
    public static <T> Clause isSatisfiable(List<Clause<T>> formula) {
        /* Begin by populating a set of all the variables in this formula. */
        Set<T> variables = new HashSet<T>();
        for (Clause<T> clause : formula) {
            variables.add(clause.first().value());
            variables.add(clause.second().value());   
        }

        // System.out.println("Variables: " + variables);

        /* Construct the directed graph of implications.  Begin by creating the
         * nodes.
         */
        DirectedGraph<Literal<T>> implications = new DirectedGraph<Literal<T>>();
        for (T variable : variables) {
            /* Add both the variable and its negation. */
            implications.addNode(new Literal<T>(variable, true));
            implications.addNode(new Literal<T>(variable, false));
        }

        /* From each clause (A or B), add two clauses - (~A -> B) and (~B -> A)
         * to the graph as edges.
         */
        for (Clause<T> clause : formula) {
            implications.addEdge(clause.first().negation(), clause.second());
            implications.addEdge(clause.second().negation(), clause.first());
        }

//        System.out.println("Implications: ");
//        for (Literal<T> l : implications) {
//            System.out.println(l + " TO " + implications.edgesFrom(l));
//        }

        /* Compute the SCCs of this graph */
        Map<Literal<T>, Integer> scc = SCC.stronglyConnectedComponents(implications);

        /* Finally, check whether any literal and its negation are in the same
         * strongly connected component.
         */
        //    System.out.println(scc);
        for (T variable : variables) {
            if (scc.get(new Literal<T>(variable, true)).equals(scc.get(new Literal<T>(variable, false)))) {
                return new Clause(new Literal<T>(variable, true), new Literal<T>(variable, false));
            }
        }
        /* If not, the formula must be satisfiable. */
        //  System.out.println("No Problems!");
        g = implications;
        return null;
    }
}
