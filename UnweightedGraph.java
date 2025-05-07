import java.util.ArrayList;
import java.util.List;

public class UnweightedGraph {
    protected List<String> vertices = new ArrayList<>();
    protected List<List<Integer>> neighbors = new ArrayList<>();

    public void addVertex(String vertex) {
        vertices.add(vertex);
        neighbors.add(new ArrayList<>());
    }

    public boolean addEdge(String v1, String v2) {
        int index1 = vertices.indexOf(v1);
        int index2 = vertices.indexOf(v2);

        if (index1 == -1 || index2 == -1) {
            return false;
        }

        neighbors.get(index1).add(index2);
        neighbors.get(index2).add(index1);
        return true;
    }

}
