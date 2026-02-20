public class ShortestPath<E> {
    private WGraph<E> graph;
    private MyHashMap<E, Pair<E>> table;
    private MyHashSet<E> visited;
    private MyHashSet<E> unvisited;

    public ShortestPath(WGraph<E> g) {
        graph = g;
        table = new MyHashMap<E, Pair<E>>();

        visited = new MyHashSet<E>();
        unvisited = new MyHashSet<E>();
    }

    private void createTable() {
        table.clear();
        for (int i = 0; i < graph.keySet().toDLList().size(); i++) {

            table.put(graph.keySet().toDLList().get(i), new Pair());

        }

    }

    public void findPaths(E start) {
        visited.clear();
        unvisited.clear();
        E current = start;
        table.get(current).setShortestDistance(0);
        table.get(current).setpreviousNode(null);
        unvisited.add(current);

        // for(int i = 0; i<graph.keySet().toDLList().size(); i++){
        // E connection = graph.keySet().toDLList().get(i);

        // unvisited.add(connection);
        // table.get(connection).setpreviousNode(start);
        // table.get(connection).setShortestDistance(graph.getWeight(connection,start));
        // }
        // adds the connections of the starting node to the unvisited
        while (unvisited.size() != 0) {
            visited.add(current);
            unvisited.remove(current);

            for (int i = 0; i < graph.getConnections(current).toDLList().size(); i++) {
                E connection = graph.getConnections(current).toDLList().get(i); // this is the connection
                if (!visited.contains(connection)) {
                    unvisited.add(connection);
                }
                int distanceFromStart = table.get(current).getShortestDistance() + graph.getWeight(current, connection);
                if (table.get(connection).getShortestDistance() == null
                        || table.get(connection).getShortestDistance() > distanceFromStart) {
                    table.get(connection).setShortestDistance(distanceFromStart);
                    table.get(connection).setpreviousNode(current);
                }

            }
            current = null;
            for (int i = 0; i < unvisited.toDLList().size(); i++) {
                E node = unvisited.toDLList().get(i); // gets the unvisited nodes
                if (current == null
                        || table.get(node).getShortestDistance() < table.get(current).getShortestDistance()) {
                    current = node;
                }
            }

        }
    }

    public String getShortestPath(E start, E end) {

        createTable();

        findPaths(start); // fills in the table
        // for(int i =0; i<table.size(); i++){
        // //System.out.println(table.keySet().toDLList().get(i) +" "+
        // table.get(table.keySet().toDLList().get(i)).getShortestDistance() + " " +
        // table.get(table.keySet().toDLList().get(i)).getPreviousNode());
        // }
        String toReturn = "";
        E current = end;
        while (!current.equals(start)) {
            toReturn = current + " " + toReturn;

            current = table.get(current).getPreviousNode();

        }
        toReturn = start + " " + toReturn;
        toReturn += table.get(end).getShortestDistance();
        return toReturn;

    }

    public DLList<E> getShortestDLList(E start, E end) {
        createTable();

        findPaths(start); // fills in the table
        // for(int i =0; i<table.size(); i++){
        // //System.out.println(table.keySet().toDLList().get(i) +" "+
        // table.get(table.keySet().toDLList().get(i)).getShortestDistance() + " " +
        // table.get(table.keySet().toDLList().get(i)).getPreviousNode());
        // }
        DLList<E> toReturn = new DLList<E>();
        E current = end;
        while (!current.equals(start)) {
            // toReturn = current + " " + toReturn;
            toReturn.add(0, current);
            current = table.get(current).getPreviousNode();

        }
        // toReturn = start + " " + toReturn;
        // System.out.println("Before: " + toReturn + " size " + toReturn.size());

        // System.out.println("Adding: " + start);
        toReturn.add(0, start);
        // System.out.println("After: " + toReturn);
        // toReturn += table.get(end).getShortestDistance();
        return toReturn;

    }

}
