public class Pair<E> {
    Integer shortestDistance;
    E previousNode;

    public Pair(){
        shortestDistance = null;
        previousNode = null;

    }
    public void setShortestDistance(int i){
        shortestDistance = i;
    }
    public void setpreviousNode(E node){
        previousNode = node;
    }

    public Integer getShortestDistance(){
        return shortestDistance;
    }
    public E getPreviousNode(){
        return previousNode;
    }
}
