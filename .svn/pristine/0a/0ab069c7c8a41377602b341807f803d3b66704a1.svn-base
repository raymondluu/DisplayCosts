/*
 * WeightedAdjMatrixGraph Modified by Alan Fowler for TCSS 342 Assignment 5
 * 
 * Added: toString() floydShortestPaths()
 */

package structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * A weighted, undirected graph stored in an adjacency matrix. The weights must
 * be >= 0.
 */
public class WeightedAdjMatrixGraph<T> extends AdjMatrixGraph<T> implements WeightedGraph<T> {
  /**
   * The default weight for an edge in a weighted graph.
   */
  public static final float DEFAULT_WEIGHT = (float) 1.0;

  /**
   * Store weight edges. The adjacency matrix storing edges is in an ancestor
   * class.
   */
  protected double[][] weights;

  public WeightedAdjMatrixGraph() {
    super();
    weights = new double[AdjMatrixGraph.SIZE][AdjMatrixGraph.SIZE];
  }

  /**
   * Add an edge connecting vertex <code>v1</code> to <code>v2</code>. In an
   * undirected graph, this edge is bidirectional.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param weight The weight of this edge; must be >= 0.0.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph, or if
   *           <code>weight</code> is < 0.
   */
  @Override
  public void addEdge(final Vertex<T> v1, final double weight, final Vertex<T> v2) {
    if (weight < 0.0) {
      throw new IllegalArgumentException("Edge weight " + " must be >= 0.0");
    }

    super.addEdge(v1, v2);

    // if we get here, method in superclass didn't throw
    // an exception and method preconditions are met
    this.setEdgeWeight(v1, weight, v2);
  }

  /**
   * Get the weight of the edge from <code>v1</code> to <code>v2</code>.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @return double The weight of the edge from <code>v1</code> to
   *         <code>v2</code>.
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph.
   */
  @Override
  public double getEdgeWeight(final Vertex<T> v1, final Vertex<T> v2) {
    final int v1Pos = super.getVerticesIndexFor(v1);
    final int v2Pos = super.getVerticesIndexFor(v2);
    // if we get here, method in superclass didn't throw
    // an exception and method preconditions are met
    return weights[v1Pos][v2Pos];
  }

  /**
   * Reset the weight for the edge connecting vertex <code>v1</code> to
   * <code>v2</code>.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param newWeight The weight of this edge; must be >= 0.0.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph, or if
   *           <code>weight</code> is < 0.
   */
  @Override
  public void setEdgeWeight(final Vertex<T> v1, final double newWeight, final Vertex<T> v2) {
    if (newWeight < 0.0) {
      throw new IllegalArgumentException("Edge weight " + "must be >= 0.0");
    }
    final int v1Pos = super.getVerticesIndexFor(v1);
    final int v2Pos = super.getVerticesIndexFor(v2);
    // if we get here, method in superclass didn't throw an
    // exception and method preconditions are met
    weights[v1Pos][v2Pos] = newWeight;
    weights[v2Pos][v1Pos] = newWeight;
  }

  // overloaded methods from AdjMatrixGraph
  /**
   * Add an edge connecting vertex <code>v1</code> to <code>v2</code>. The edge
   * is bidirectional in an undirected graph. The default weight for an edge is
   * <code>DEFAULT_WEIGHT</code>.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph.
   */
  @Override
  public void addEdge(final Vertex<T> v1, final Vertex<T> v2) {
    this.addEdge(v1, DEFAULT_WEIGHT, v2);
  }

  /**
   * Find a minimal cost path from <code>src</code> to <code>dest</code> in this
   * graph. Assumes edge weights are positive.
   * 
   * @param src Vertex The first vertex in the path.
   * @param dest Vertex The last vertex in the path.
   * @return double The cost of the path or -1 if none is found.
   */
  public double minimalPath(final Vertex<T> src, final Vertex<T> dest) {
    // keep track of which vertices have been visited already
    final ArrayList<Vertex<T>> visitedVertices = new ArrayList<Vertex<T>>();

    // Comparator for the priority queue where the shortest
    // paths found so far a stored.
    final Comparator pathCostComparator = new Comparator() {
      @Override
      public int compare(final Object o1, final Object o2) {
        final Double i1 = ((Tuple<Vertex<T>, Double>) o1).getSecondElement();
        final Double i2 = ((Tuple<Vertex<T>, Double>) o2).getSecondElement();
        return i1.compareTo(i2);
      }

      @Override
      public boolean equals(final Object obj) {
        return false;
      }
    };

    // Stores the shortest paths from the source vertex
    // found so far. These are stored as tuples.
    // The first field of the tuple is the terminating
    // node in some shortest path starting from src
    // The second field is the cost of that path
    final PriorityQueue<Tuple<Vertex<T>, Double>> pq =
        new HeapPriorityQueue<Tuple<Vertex<T>, Double>>(pathCostComparator);

    Tuple<Vertex<T>, Double> pathTuple;

    // start with the source, which has a cost of 0 to
    // get to itself
    pq.enqueue(new Tuple(src, 0.0));

    while (!pq.isEmpty()) {
      // get cheapest path seen so far from src to some
      // other vertex
      pathTuple = pq.dequeue();

      // extract the fields of the tuple so we can
      // work with them
      final Vertex<T> v = pathTuple.getFirstElement();
      final double minCostToV = pathTuple.getSecondElement();

      visitedVertices.add(v); // visit vertex v

      // if v is the destination vertex, we are done
      if (v.equals(dest)) {
        return minCostToV;
      }

      // okay, not done yet; look at the vertices
      // adjacent to v
      final ArrayList<Vertex<T>> neighbors = (ArrayList<Vertex<T>>) getNeighbors(v);
      while (!neighbors.isEmpty()) {
        final Vertex<T> w = neighbors.remove(0); // next neighbor

        // if w hasn't been visited already, add it to
        // the priority queue
        if (!visitedVertices.contains(w)) {
          // get the total path cost from src to v
          final double minCostToW = minCostToV + getEdgeWeight(v, w);
          pathTuple = new Tuple<Vertex<T>, Double>(w, minCostToW);
          pq.enqueue(pathTuple);
        }
      }
    }
    // if the loop terminates naturally, we never found
    // the destination vertex, so return failure
    return -1;
  }

  // Methods added for TCSS 342

  /**
   * Floyd-Warshall all-pairs shortest-path algorithm. Creates a matrix of
   * shortest distance paths.
   * http://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm
   * 
   * @param the_graph the graph to process
   * @return A 2D array representing all shortest paths through the_graph
   */
  public static double[][] floydShortestPaths(final WeightedAdjMatrixGraph<String> the_graph) {

    final double[][] shortest_paths =
        new double[the_graph.numberOfVertices][the_graph.numberOfVertices];

    // copy edge weights to shortest_paths array
    for (int row = 0; row < shortest_paths.length; row++) {
      for (int col = 0; col < shortest_paths.length; col++) {
        if (row != col) { // leave the row == col diagonal set to zero
          double weight = the_graph.weights[col][row];
          if (weight == 0) { // no edge here
            weight = Double.MAX_VALUE;
          }
          shortest_paths[col][row] = weight;
        }
      }
    }

    // calculate shortest paths
    for (int k = 0; k < shortest_paths.length; k++) {
      for (int i = 0; i < shortest_paths.length; i++) {
        for (int j = 0; j < shortest_paths.length; j++) {
          shortest_paths[i][j] =
              Math.min(shortest_paths[i][j], shortest_paths[i][k] + shortest_paths[k][j]);
        }
      }
    }
    return shortest_paths;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append("\nWeights:\n");
    for (int index = 0; index < numberOfVertices; index++) {
      sb.append(String.format("%15s ", vertices[index]));
      sb.append(Arrays.toString(weights[index]) + "\n");
    }
    return sb.toString();
  }
}
