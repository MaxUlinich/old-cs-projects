
import java.awt.Graphics;
import java.awt.Font;

public class WGraph<E> {
    private MyHashMap<E, MyHashMap<E, Integer>> wGraph;
    private Double scaleFactor;

    public WGraph(Double num) {
        wGraph = new MyHashMap();
        scaleFactor = num;

    }

    public String toString() {
        String s = "";
        for (int i = 0; i < wGraph.keySet().toDLList().size(); i++) {// goes through each node
            E node = wGraph.keySet().toDLList().get(i);
            s += node + " --> ";
            for (int j = 0; j < wGraph.get(node).keySet().toDLList().size(); j++) { // goes through each connection
                E connection = wGraph.get(node).keySet().toDLList().get(j); // the connection
                s += connection.toString() + ":" + wGraph.get(node).get(connection) + ", "; // adds each connction
            }
            s += "\n";
        }
        return s;
    }

    public void addData(E data) {
        wGraph.put(data, new MyHashMap<E, Integer>());
    }

    public void addEdge(E d1, E d2, int weight) {
        wGraph.get(d1).put(d2, weight);
        wGraph.get(d2).put(d1, weight);
    }

    public void addEdge(E d1, E d2) {
        Location l1 = (Location) d1;
        Location l2 = (Location) d2;
        int weight = (int) (Math.sqrt(Math.pow((l1.getX() - l2.getX()), 2) + Math.pow((l1.getY() - l2.getY()), 2))
                * scaleFactor);
        wGraph.get(d1).put(d2, weight);
        wGraph.get(d2).put(d1, weight);
    }

    public Integer getWeight(E d1, E d2) {
        try {
            return wGraph.get(d1).get(d2);
        } catch (Exception e) {

        }
        return null;
    }

    public MyHashSet<E> keySet() {
        return wGraph.keySet();
    }

    public MyHashSet<E> getConnections(E node) {
        return wGraph.get(node).keySet();
    }

    public void drawMe(Graphics g) {

        for (int i = 0; i < wGraph.keySet().toDLList().size(); i++) {// goes through each node
            Location node = (Location) wGraph.keySet().toDLList().get(i);
            node.drawMe(g);
            for (int j = 0; j < wGraph.get(node).keySet().toDLList().size(); j++) { // goes through each connection
                Location connection = (Location) wGraph.get(node).keySet().toDLList().get(j); // the connection
                g.drawLine(node.getX(), node.getY(), connection.getX(), connection.getY());
                g.setFont(new Font("Arial", 0, 9));
                g.drawString(wGraph.get(node).get(connection) + "mi", (node.getX() + connection.getX()) / 2,
                        (node.getY() + connection.getY()) / 2);
            }

        }

    }
}