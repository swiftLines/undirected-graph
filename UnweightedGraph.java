import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

    public boolean hasCycles() {
        boolean[] isVisited = new boolean[vertices.size()];
        
        for (int i = 0; i < vertices.size(); i++) {
            if (!isVisited[i]) {
                if (hasCycleDFS(i, -1, isVisited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasCycleDFS(int vertex, int parent, boolean[] isVisited) {
        isVisited[vertex] = true;

        for (int neighbor : neighbors.get(vertex)) {
            if (!isVisited[neighbor]) {
                if (hasCycleDFS(neighbor, vertex, isVisited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public boolean isConnected() {
        boolean[] isVisited = new boolean[vertices.size()];
        
        dfs(0, isVisited);

        for (boolean v : isVisited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    public void dfs(int vertex, boolean[] isVisited) {
        isVisited[vertex] = true;

        for (int neighbor : neighbors.get(vertex)) {
            if (!isVisited[neighbor]) {
                dfs(neighbor, isVisited);
            }
        }
    }

    public List<String> dfs() {
        List<String> searchOrder = new ArrayList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            
            if (!isVisited[vertex]) {
                isVisited[vertex] = true;
                searchOrder.add(vertices.get(vertex));

                for (int neighbor : neighbors.get(vertex)) {
                    if (!isVisited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return searchOrder;
    }

    public List<String> bfs() {
        List<Integer> searchOrder = new ArrayList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        isVisited[0] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            searchOrder.add(vertex);

            for (int neighbor : neighbors.get(vertex)) {
                if (!isVisited[neighbor]) {
                    queue.offer(neighbor);
                    isVisited[neighbor] = true;
                }
            }
        }

        List<String> namedOrder = new ArrayList<>();
        for (int index : searchOrder) {
            namedOrder.add(vertices.get(index));
        }

        return namedOrder;

    }
}
