
package structures;

/**
 * A weighted, directed graph stored in an adjacency matrix. The weights must be
 * >= 0.
 */
public class WeightedAdjMatrixDiGraph<T> extends AdjMatrixDiGraph<T> implements
    WeightedGraph<T> {

  protected double[][] weights;

  public WeightedAdjMatrixDiGraph() {
    super();
    weights = new double[AdjMatrixDiGraph.SIZE][AdjMatrixDiGraph.SIZE];
  }

  /**
   * Add an edge connecting vertex <code>v1</code> to <code>v2</code> with
   * weight <code>weight</code>.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param weight The weight (cost) of this edge; must be >= 0.0.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph, or
   *           <code>weight</code> < 0.0.
   */
  @Override
  public void addEdge(final Vertex<T> v1, final double weight, final Vertex<T> v2) {
    if (weight < 0.0) {
      throw new IllegalArgumentException("Edge weight must be >= 0.0");
    }
    super.addEdge(v1, v2);
    this.weights[v1Pos][v2Pos] = weight;
  }

  /**
   * Add an edge connecting vertex <code>v1</code> to <code>v2</code>. This
   * edge's weight is 1.0 by default.
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
    this.addEdge(v1, 1.0, v2);
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
    final int srcPos = super.getVerticesIndexFor(v1);
    final int destPos = super.getVerticesIndexFor(v2);
    // if we get here, method in superclass didn't throw an exception
    // and method preconditions are met
    return weights[srcPos][destPos];
  }

  /**
   * Reset the weight for the edge connecting vertex <code>v1</code> to
   * <code>v2</code>.
   * 
   * @param src The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param newWeight The weight (cost) of this edge; must be >= 0.0.
   * @param dest The destination vertex; must not be <code>null</code> and must
   *          be a vertex in this graph.
   * @throws IllegalArgumentException if <tt<v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph, or if
   *           <code>weight</code> is < 0.
   */
  @Override
  public void setEdgeWeight(final Vertex<T> src,
                            final double newWeight,
                            final Vertex<T> dest) {
    if (newWeight < 0.0) {
      throw new IllegalArgumentException("Edge weight must be >= 0.0");
    }
    final int srcPos = super.getVerticesIndexFor(src);
    final int destPos = super.getVerticesIndexFor(dest);
    // if we get here, method in superclass didn't throw an exception
    // and method preconditions are met
    weights[srcPos][destPos] = newWeight;
  }
}
