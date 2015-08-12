
package structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFSSearcher<T> {

  final private Graph<T> graph;

  private Map<Vertex<T>, Vertex<T>> visited;

  private boolean pathFound;

  /**
   * 
   * @param g The graph to search.
   * @throws NullArgumentException if <code>g</code> is <code>null</code>.
   */
  public DFSSearcher(final Graph<T> g) {
    this.graph = g;
  }

  /**
   * Determine if there is a path from <code>v1</code> to <code>v2</code> in
   * graph.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @return <code>true</code> if there is a path from <code>v1</code> to
   *         <code>v2</code> in the graph.
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph.
   */
  public boolean containsPath(final Vertex<T> v1, final Vertex<T> v2) {
    doDFS(v1, v2);
    return pathFound;
  }

  /**
   * Determine if the length of the path from <code>v1</code> to <code>v2</code>
   * in the graph.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @return int The length of the path from <code>v1</code> to <code>v2</code>;
   *         0 if there is no path
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph.
   */
  public int getPathLength(final Vertex<T> v1, Vertex<T> v2) {
    doDFS(v1, v2);
    int pathLength = 0;
    if (pathFound) {
      Vertex<T> neighbor;
      while ((neighbor = visited.get(v2)) != null) {
        pathLength++;
        v2 = neighbor;
      }
      pathLength--;
    }

    // System.out.printf( "returning %d as the pathlength\n", pathLength);
    return pathLength;
  }

  /**
   * Get the vertices on the path from <code>v1</code> to <code>v2</code> in the
   * graph.
   * 
   * @param v1 The source vertex; must not be <code>null</code> and must be a
   *          vertex in this graph.
   * @param v2 The destination vertex; must not be <code>null</code> and must be
   *          a vertex in this graph.
   * @return List A list of the vertices on the path from <code>v1</code> to
   *         <code>v2</code>. This list will be empty if there is no path.
   * @throws IllegalArgumentException if <code>v1</code> or <code>v2</code> are
   *           <code>null</code> or are not in this graph.
   */
  public List<Vertex<T>> getPath(final Vertex<T> v1, Vertex<T> v2) {
    final List<Vertex<T>> thepath = new ArrayList<Vertex<T>>();
    doDFS(v1, v2);
    if (pathFound) {
      thepath.add(v2);
      Vertex<T> neighbor;
      while ((neighbor = visited.get(v2)) != null) {
        if (!neighbor.getLabel().equals("")) {
          thepath.add(neighbor);
          // System.out.printf("Adding %s to the path\n", neighbor );
        }
        v2 = neighbor;
      }
    }
    java.util.Collections.reverse(thepath);
    return thepath;
  }

  private void doDFS(final Vertex<T> origin, final Vertex<T> target) {
    pathFound = false;
    visited = new HashMap<Vertex<T>, Vertex<T>>();
    visited.put(origin, new Vertex(""));
    dfs(origin, target);
  }

  private void dfs(final Vertex<T> origin, final Vertex<T> target) {
    if (origin == target) {
      pathFound = true;
      return;
    }
    final Stack<Vertex<T>> neighborsToVisit = new Stack<Vertex<T>>();
    final List<Vertex<T>> neighbors = (ArrayList<Vertex<T>>) graph.getNeighbors(origin);
    // push 'em in reverse order to "leftmost" neighbor is on top
    for (int i = neighbors.size() - 1; i >= 0; i--) {
      neighborsToVisit.push(neighbors.get(i));
    }

    while (!neighborsToVisit.empty()) {
      final Vertex<T> v = neighborsToVisit.pop();
      if (visited.get(v) == null) {
        visited.put(v, origin);
        dfs(v, target);
      }
    }
  }
}
