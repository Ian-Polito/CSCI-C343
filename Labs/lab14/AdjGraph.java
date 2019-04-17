/*
C343 Summer 2018
Lab 14
Ian Polito
ipolito
*/

// a simple implementation for graphs with adjacency lists
// C343 Summer 2018
// we use a few java.util classes:
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class AdjGraph implements Graph {

    private boolean digraph;
    private int totalNode;
    private Vector<String> nodeList;
    private int totalEdge;
    private Vector<LinkedList<Integer>>  adjList; // adjacency list
    private Vector<Boolean> visited;
    private Vector<Integer> nodeEnum; // list of nodes pre visit

    public AdjGraph() {
        init();
    }

    public AdjGraph(boolean ifdigraph) {
        init();
        digraph = ifdigraph;
    }

    public void init() {
        nodeList = new Vector<String>();
        adjList = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNode = totalEdge = 0;
        digraph = false;
    }

    // set vertices
    public void setVertices(String[] nodes) {
        for(int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNode ++;
        }
    }

    // add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        totalNode ++;
    }

    public int getNode(String node) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    // return the number of vertices
    public int length() {
        return nodeList.size();
    }

    // add edge from v1 to v2
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdge ++;
        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well
            if (digraph == false) setEdge(getNode(v2), getNode(v1), weight);
        }
    }

    // it is important to keep track if a vertex is visited or not (for traversal)
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }

    public void clearWalk() {
        // clean up before traversing
        nodeEnum.clear();
        for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph
        for(int i = 0; i < nodeList.size(); i ++) {
            if(ifVisited(i) == false) {
                if(method.equals("BFS")) BFS(i);      // i is the start node
                else if(method.equals("DFS")) DFS(i); // i is the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    // Lab 14 TODO:
    //
    // write your componentsAndSizes() method here.
    //method lists all connected components and their sizes
    public void componentsAndSizes() {
        int total = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            if (!(ifVisited(i))) {
                total++;
                System.out.println("Component " + (total - 1) + " contains " + (BFSsize(i)) + " node(s).");
            }
        }
    }

    public int BFSsize(int s) {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        int size = 0;
        toVisit.add(s);
        while(toVisit.size() > 0) {
            int v = toVisit.remove(0); // first-in, first-visit
            setVisited(v);
            size++;
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for(int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if((ifVisited(v1) == false) && (toVisit.contains(v1) == false) && (adjList.elementAt(s).contains(v1))) {
                    toVisit.add(v1);
                }
            }
        }
        return size;
    }

    public void DFS(int v) {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for(int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if(ifVisited(v1) == false) DFS(v1);
        }
    }

    public void BFS(int s) {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while(toVisit.size() > 0) {
            int v = toVisit.remove(0); // first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for(int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
                    toVisit.add(v1);
                }
            }
        }
    }

    public void display() {
        System.out.println("total nodes: " + totalNode);
        System.out.println("total edges: " + totalEdge);
    }

    public void displayEnum() {
        for(int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //TESTING ADJGRAPH
        AdjGraph test = new AdjGraph();
        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");
        test.setEdge("A","B",5);
        test.setEdge("A","C",5);
        test.setEdge("C","B",5);
        test.addVertex("D");
        test.addVertex("E");
        test.setEdge("B","D",5);
        test.setEdge("B","E",5);
        test.setEdge("D","E",5);
        test.addVertex("F");
        test.addVertex("G");
        test.addVertex("H");
        test.addVertex("I");
        test.addVertex("J");
        test.display();
        test.componentsAndSizes();
        System.out.println();

        AdjGraph test2 = new AdjGraph();
        test2.addVertex("A");
        test2.addVertex("B");
        test2.addVertex("C");
        test2.setEdge("A","B",5);
        test2.setEdge("A","C",5);
        test2.addVertex("D");
        test2.addVertex("E");
        test2.setEdge("B","D",5);
        test2.setEdge("B","E",5);
        test2.setEdge("D","E",5);
        test2.addVertex("F");
        test2.addVertex("G");
        test2.addVertex("H");
        test2.setEdge("F","G",5);
        test2.setEdge("F","H",5);
        test2.setEdge("G","H",5);
        test2.addVertex("I");
        test2.addVertex("J");
        test2.setEdge("I","J",5);
        test2.display();
        test2.componentsAndSizes();
        System.out.println();

        AdjGraph test3 = new AdjGraph();
        test3.addVertex("A");
        test3.addVertex("B");
        test3.addVertex("C");
        test3.addVertex("D");
        test3.setEdge("A","B",5);
        test3.setEdge("B","C",5);
        test3.setEdge("C","D",5);
        test3.setEdge("D","A",5);
        test3.setEdge("B","D",5);
        test3.addVertex("E");
        test3.setEdge("C","E",5);
        test3.addVertex("F");
        test3.addVertex("G");
        test3.addVertex("H");
        test3.addVertex("I");
        test3.setEdge("I","E",5);
        test3.addVertex("J");
        test3.setEdge("E","F",5);
        test3.setEdge("F","G",5);
        test3.setEdge("G","H",5);
        test3.setEdge("H","E",5);
        test3.setEdge("F","H",5);
        test3.display();
        test3.componentsAndSizes();
    }
    // Lab 14 TODO:
    // write a main() method here:
    // 1) instantiate a new graph,
    // 2) assign 2 vertices and edges to the graph,
    // 3) then display the graph's content (use display() )
    // 4) finally call your componentsAndSizes() method to provide
    //    output results as from Lab 14 instructions

    //   provide 3 different examples,
    //   with at least 10 nodes for each different graph
}