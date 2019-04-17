/*
C343 Summer 2018
Lab 15
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

    public void numberOfEdges() {
        int n = nodeList.size();
        int[][] NofE = new int[n][n];
        for (int i = 0; i < n; i++) {
            NofE[i][i] = 0;
        }

        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (u != v) {
                    if (adjList.elementAt(u).contains(v)) {
                        NofE[u][v] = 1;
                    } else {
                        NofE[u][v] = 999;
                    }
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (NofE[i][j] > NofE[i][k] + NofE[k][j]) {
                        NofE[i][j] = NofE[i][k] + NofE[k][j];
                    }
                }
            }
        }

        //print the resulting matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(NofE[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //TESTING NumberOfEdges()
        AdjGraph test = new AdjGraph();
        //textbook example
        test.addVertex("J1");
        test.addVertex("J2");
        test.addVertex("J3");
        test.addVertex("J4");
        test.addVertex("J5");
        test.addVertex("J6");
        test.addVertex("J7");
        test.setEdge("J1","J2",1);
        test.setEdge("J1","J3",1);
        test.setEdge("J2","J6",1);
        test.setEdge("J2","J5",1);
        test.setEdge("J2","J4",1);
        test.setEdge("J3","J4",1);
        test.setEdge("J4","J5",1);
        test.setEdge("J5","J7",1);
        test.numberOfEdges();

        //AI's example in Lab
        System.out.println();
        AdjGraph test2 = new AdjGraph();
        test2.addVertex("1");
        test2.addVertex("2");
        test2.addVertex("3");
        test2.addVertex("4");
        test2.addVertex("5");
        test2.setEdge("1","3",1);
        test2.setEdge("1","4",1);
        test2.setEdge("1","5",1);
        test2.setEdge("2","4",1);
        test2.setEdge("2","5",1);
        test2.setEdge("3","4",1);
        test2.setEdge("3","5",1);
        test2.setEdge("3","1",1);
        test2.setEdge("4","1",1);
        test2.setEdge("4","2",1);
        test2.setEdge("4","3",1);
        test2.setEdge("4","5",1);
        test2.setEdge("5","1",1);
        test2.setEdge("5","2",1);
        test2.setEdge("5","3",1);
        test2.setEdge("5","4",1);
        test2.numberOfEdges();

        //A third example
        System.out.println();
        AdjGraph test3 = new AdjGraph();
        test3.addVertex("1");
        test3.addVertex("2");
        test3.addVertex("3");
        test3.addVertex("4");
        test3.addVertex("5");
        test3.addVertex("6");
        test3.setEdge("1","6",1);
        test3.setEdge("1","5",1);
        test3.setEdge("1","3",1);
        test3.setEdge("2","3",1);
        test3.setEdge("2","4",1);
        test3.setEdge("2","5",1);
        test3.setEdge("2","6",1);
        test3.setEdge("3","1",1);
        test3.setEdge("3","2",1);
        test3.setEdge("4","2",1);
        test3.setEdge("4","6",1);
        test3.setEdge("5","1",1);
        test3.setEdge("5","2",1);
        test3.setEdge("6","2",1);
        test3.setEdge("6","1",1);
        test3.setEdge("6","4",1);
        test3.numberOfEdges();
    }
}