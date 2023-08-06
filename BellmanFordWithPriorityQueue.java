
/*
Question 3-b) 
    Implement bellman ford algorithm and priority queue using maximum heap.
    
[5 Marks]
 */
import java.util.*;

class Edge {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    int vertices, edges;
    List<Edge> edgeList;

    public Graph(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.edgeList = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
    }

    public void bellmanFord(int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int i = 1; i <= vertices - 1; i++) {
            for (Edge edge : edgeList) {
                int u = edge.source;
                int v = edge.destination;
                int w = edge.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edgeList) {
            int u = edge.source;
            int v = edge.destination;
            int w = edge.weight;

            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // Print shortest distances
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + ": " + distance[i]);
        }
    }
}

class PriorityQueueMaxHeap<T> {
    List<T> heap;
    Comparator<T> comparator;

    public PriorityQueueMaxHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public void insert(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public T extractMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }

        T max = heap.get(0);
        int lastIndex = heap.size() - 1;
        swap(0, lastIndex);
        heap.remove(lastIndex);
        heapifyDown(0);
        return max;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int lastIndex = heap.size() - 1;
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largestIndex = index;

            if (leftChildIndex <= lastIndex
                    && comparator.compare(heap.get(leftChildIndex), heap.get(largestIndex)) > 0) {
                largestIndex = leftChildIndex;
            }

            if (rightChildIndex <= lastIndex
                    && comparator.compare(heap.get(rightChildIndex), heap.get(largestIndex)) > 0) {
                largestIndex = rightChildIndex;
            }

            if (largestIndex != index) {
                swap(index, largestIndex);
                index = largestIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

public class BellmanFordWithPriorityQueue {
    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;
        Graph graph = new Graph(vertices, edges);
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 5);
        graph.addEdge(1, 4, -4);
        graph.addEdge(2, 3, -3);
        graph.addEdge(2, 4, 9);
        graph.addEdge(3, 1, -2);
        graph.addEdge(4, 0, 2);
        graph.bellmanFord(0);

        // Priority Queue Example
        Comparator<Integer> maxComparator = Comparator.reverseOrder();
        PriorityQueueMaxHeap<Integer> pq = new PriorityQueueMaxHeap<>(maxComparator);
        pq.insert(5);
        pq.insert(3);
        pq.insert(8);
        pq.insert(1);
        pq.insert(10);

        while (!pq.isEmpty()) {
            System.out.println(pq.extractMax());
        }
    }
}
