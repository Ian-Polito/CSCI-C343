/*
C343 Summer 2018
Lab 18
Ian Polito
ipolito
 */

// a simple implementation for graphs with adjacency lists
// lab 17 starter file
// C343 Summer 2018
// we use a few java.util classes:
import java.util.*;

public class AdjGraph implements Graph {

    // is it a directed graph (true or false) :
    private boolean digraph;

    private int totalNodes;
    // all the nodes in the graph:
    private Vector<String> nodeList;

    private int totalEdges;
    // all the adjacency lists, one for each node in the graph:
    private Vector<LinkedList<Integer>>  adjList;

    // all the weights of the edges, one for each node in the graph:
    private Vector<LinkedList<Integer>> adjWeight;

    // every visited node:
    private Vector<Boolean> visited;
    // list of nodes pre-visit:
    private Vector<Integer> nodeEnum;

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
        adjWeight = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNodes = totalEdges = 0;
        digraph = false;
    }

    // set vertices
    public void setVertices(String[] nodes) {
        for (int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            adjWeight.add(new LinkedList<Integer>());
            visited.add(false);
            totalNodes ++;
        }
    }

    // add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        adjWeight.add(new LinkedList<Integer>());
        totalNodes ++;
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

    // add edge from v1 to v2:
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdges ++;
            LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
            tmp2.add(weight);
            adjWeight.set(v1,  tmp2);
        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if ((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2:
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well:
            if (digraph == false) {
                setEdge(getNode(v2), getNode(v1), weight);
            }
        }
    }

    // keep track whether a vertex has been visited or not,
    //    for graph traversal purposes:
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }

    public LinkedList<Integer> getNeighbors(int v) {
        return adjList.get(v);
    }
    public int getWeight(int v, int u) {
        LinkedList<Integer> tmp = getNeighbors(v);
        LinkedList<Integer> weight = adjWeight.get(v);
        if (tmp.contains(u)) {
            return weight.get(tmp.indexOf(u));
        } else {
            return 999;
        }
    }

    public void clearWalk() {
        nodeEnum.clear();
        for (int i = 0; i < nodeList.size(); i ++)
            visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph:
        for (int i = 0; i < nodeList.size(); i ++) {
            if (ifVisited(i) == false) {
                if (method.equals("BFS")) BFS(i);      // i is the start node
                else if (method.equals("DFS")) DFS(i); // i is the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    public int minVertex(int[] D) {
        int v = 0;
        //initialize v to any unvisited vertex
        for (int i = 0; i < D.length; i++) {
            if (this.ifVisited(i) == false) {
                v = i;
                break;
            }
        }
        for (int i = 0; i < D.length; i++) {
            //now find smallest value
            if (this.ifVisited(i) == false && (D[i] < D[v])) {
                v = i;
            }
        }
        return v;
    }

    public void dijkstra1(int source) {
        int[] D = new int[nodeList.size()];
        for (int i = 0; i < D.length; i++) {
            D[i] = 999;
        }
        D[source] = 0;

        for (int i = 0; i < D.length; i++) {
            //process the vertices
            int v = minVertex(D);
            this.setVisited(v);
            if (D[v] == 999) {
                //unreachable
                return;
            }
            //RELAX
            for (int w = 0; w < nodeList.size(); w++) {
                if (D[w] > (D[v] + this.getWeight(v,w))) {
                    D[w] = D[v] + this.getWeight(v,w);
                }
            }
        }
        System.out.println(Arrays.toString(D));
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
        System.out.println("total nodes: " + totalNodes);
        System.out.println("total edges: " + totalEdges);
    }

    public void displayEnum() {
        for(int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }

    //implementation of Prim's algorithm for finding the MST
    public void mstPrim() {
        int[] dist = new int[nodeList.size()];
        //set dist[0] to 0
        dist[0] = 0;
        //set dist[v] to infinity for all other nodes
        for (int i = 1; i < dist.length; i++) {
            dist[i] = 99999;
        }
        //set cost (of the MST) to 0 (initial MST is empty)
        int cost = 0;
        //repeat until Q becomes empty
        for (int i = 0; i < nodeList.size(); i++) {
            //find vertex u with the smallest dist[u] among all unsettled vertices
            //remove u from S, update the cost (cost <- cost + dist[u])
            int u = minVertex(dist);
            this.setVisited(u);
            cost += dist[u];
            //update dist[v] for every neighbor of u:
            LinkedList<Integer> neighbors = adjList.elementAt(u);
            for (int v : neighbors) {
                //RELAXATION
                if (getWeight(u, v) < dist[v]) {
                    dist[v] = getWeight(u, v);
                }
            }
        }
        //print the array
        System.out.println(Arrays.toString(dist));
        //print the cost of the MST
        System.out.println(cost);
    }

    public static void main(String argv[]) {
        //TESTING PRIM ALGORITHM

        //first example: g1
        AdjGraph g1 = new AdjGraph(false);
        String[] nodes1 = {"A", "B", "C", "D", "E"};
        g1.setVertices(nodes1);
        g1.setEdge("A", "B", 3);
        g1.setEdge("B", "C", 2);
        g1.setEdge("C", "D", 5);
        g1.setEdge("A", "E", 20);
        g1.setEdge("C","E",10);
        g1.mstPrim();

        //second example: g2
        AdjGraph g2 = new AdjGraph(false);
        String[] nodes2 = {"a", "b", "c", "d", "e", "f"};
        g2.setVertices(nodes2);
        g2.setEdge("a", "b", 9);
        g2.setEdge("a", "f", 5);
        g2.setEdge("a", "e", 3);
        g2.setEdge("b", "c", 5);
        g2.setEdge("b", "f", 4);
        g2.setEdge("c", "d", 2);
        g2.setEdge("c", "f", 8);
        g2.setEdge("d", "f", 7);
        g2.setEdge("d", "e", 1);
        g2.setEdge("e", "f", 5);
        g2.mstPrim();
    }
}
