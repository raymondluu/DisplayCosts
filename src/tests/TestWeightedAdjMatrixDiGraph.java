
package tests;

import java.util.ArrayList;

import junit.framework.TestCase;

import structures.BFSSearcher;
import structures.DFSSearcher;
import structures.Vertex;
import structures.WeightedAdjMatrixDiGraph;

public class TestWeightedAdjMatrixDiGraph extends TestCase {
  private WeightedAdjMatrixDiGraph<String> graph = null;

  private Vertex<String> v1, v2, v3, v4, v5, v6;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    graph = new WeightedAdjMatrixDiGraph<String>();
    v1 = new Vertex("v1");
    graph.addVertex(v1);
    v2 = new Vertex("v2");
    graph.addVertex(v2);
    v3 = new Vertex("v3");
    graph.addVertex(v3);
  }

  @Override
  protected void tearDown() throws Exception {
    graph = null;
    super.tearDown();
  }

  public void testConstructor() {
    final WeightedAdjMatrixDiGraph<String> g = new WeightedAdjMatrixDiGraph<String>();
    assertTrue(g.getNumberOfVertices() == 0);
    assertTrue(g.getNumberOfEdges() == 0);
  }

  public void testAddVertices() {
    assertEquals(3, graph.getNumberOfVertices());
  }

  public void testAddVertexWithEdges() {
    graph.addEdge(v1, v2); // v1 -> v2
    graph.addEdge(v3, v2); // v3 -> v2
    assertEquals(3, graph.getNumberOfVertices());
    assertEquals(2, graph.getNumberOfEdges());
  }

  public void testAddVertexWithEdgesWeights() {
    v4 = new Vertex("v4");
    graph.addVertex(v4);
    v5 = new Vertex("v5");
    graph.addVertex(v5);
    graph.addEdge(v5, 4.0, v2);
    v6 = new Vertex("v6");
    graph.addVertex(v6);
    graph.addEdge(v6, v3);
    assertEquals(6, graph.getNumberOfVertices());
    assertEquals(2, graph.getNumberOfEdges());
    assertEquals(4.0, graph.getEdgeWeight(v5, v2), .05);
    assertEquals(1.0, graph.getEdgeWeight(v6, v3), .05);
  }

  public void testSetWeightConstraint() {
    try {
      graph.addEdge(v1, -1, v2); // v1 -> v2
      fail("exception should have been thrown for illegal weight");
    } catch (final IllegalArgumentException e) {
      // do nothing - this is good news
    }
    try {
      graph.addEdge(v1, 1, v2); // v1 -> v2
      graph.setEdgeWeight(v1, -1, v2);
      fail("exception should have been thrown for illegal weight");
    } catch (final IllegalArgumentException e) {
      // do nothing - this is good news
    }
  }

  /**
   * Verify that we can get the neighbors of a vertex. That is, the adjacent
   * vertices.
   */
  public void testNeighbors() {
    graph.addEdge(v1, v2); // v1 -> v2
    graph.addEdge(v2, v1); // v2 -> v1
    graph.addEdge(v2, v3); // v2 -> v3
    assertTrue(graph.getNumberOfVertices() == 3);
    assertTrue(graph.getNumberOfEdges() == 3);
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
    assertTrue(neighbors.size() == 1);

    neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v2);
    assertTrue(neighbors.size() == 2);
    assertTrue(neighbors.contains(v1));
    assertTrue(neighbors.contains(v3));
  }

  /**
   * Verify that removing a nonexistent edge does not detach any connected
   * vertices.
   */
  public void testRemoveEdgeNotExist() {

    graph.addEdge(v1, v2); // v1 -> v2
    graph.addEdge(v2, v3); // v2 -> v3
    try {
      graph.removeEdge(v1, v3); // remove nonexistent edge
      fail("failed to detect deleted non-existent edge");
    } catch (final IllegalArgumentException e) {
    }

    assertTrue(graph.getNumberOfVertices() == 3);
    assertTrue(graph.getNumberOfEdges() == 2);
    // verify that v1 still has its neightbors
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
    assertTrue(neighbors.size() == 1);
    assertTrue(neighbors.contains(v2));

    neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v2);
    assertTrue(neighbors.size() == 1);
    assertTrue(neighbors.contains(v3));
  }

  /**
   * Verify that removing an edge updates the associated vertices. Make sure we
   * can add the edge back.
   */
  public void testRemoveEdgeExists() {

    graph.addEdge(v1, v2); // v1 -> v2
    graph.addEdge(v1, v3); // v1 -> v3
    assertEquals(3, graph.getNumberOfVertices());
    assertEquals(2, graph.getNumberOfEdges());
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
    assertTrue(neighbors.size() == 2);
    assertTrue(neighbors.contains(v2));
    assertTrue(neighbors.contains(v3));

    // now remove an edge
    graph.removeEdge(v1, v3);
    assertEquals(1, graph.getNumberOfEdges());
    neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v2));

    // now add the edge back
    graph.addEdge(v1, v3); // v1 -> v3
    neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
    assertTrue(neighbors.size() == 2);
    assertTrue(neighbors.contains(v2));
    assertTrue(neighbors.contains(v3));
    assertEquals(3, graph.getNumberOfVertices());
    assertEquals(2, graph.getNumberOfEdges());
  }

  /**
   * Verify that removing a vertex removes the associated edges. Make sure we
   * can add the vertex back.
   */
  public void testRemoveVertex() {
    graph.addEdge(v1, v2); // v1 -> v2
    graph.addEdge(v2, v3); // v2 -> v3

    // Now remove the vertex
    graph.removeVertex(v2);
    assertEquals(2, graph.getNumberOfVertices());
    assertEquals(0, graph.getNumberOfEdges());
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
    assertEquals(0, neighbors.size());

    // now add a vertex back
    graph.addVertex(v2);
    graph.addEdge(v1, v2);
    graph.addEdge(v2, v3);
    assertEquals(3, graph.getNumberOfVertices());
    assertEquals(2, graph.getNumberOfEdges());

    neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v2);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v3));

    neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v2));
  }

  /**
   * Verify that DFSSearcher and BFSSearcer can find a path that exists in the
   * graph and does not find a path that does NOT exist.
   */

  public void testPath() {
    graph.addEdge(v1, v2); // v1 -> v2
    graph.addEdge(v2, v3); // v2 -> v3
    v4 = new Vertex<String>("v4");
    graph.addVertex(v4);
    graph.addEdge(v4, v2); // v3 -> v4
    v5 = new Vertex<String>("v5");
    graph.addVertex(v5);
    graph.addEdge(v3, v5); // v3 -> v5
    v2 = new Vertex<String>("Disconnected");
    graph.addVertex(v2);
    final DFSSearcher<String> dfs = new DFSSearcher<String>(graph);
    assertTrue(dfs.containsPath(v1, v3));
    assertEquals(2, dfs.getPathLength(v1, v3));
    assertEquals(0, dfs.getPathLength(v3, v2));
    assertFalse(dfs.containsPath(v3, v1));

    // Verify that BFSSearcher also works
    final BFSSearcher<String> bfs = new BFSSearcher<String>(graph);
    assertTrue(bfs.containsPath(v1, v3));
    assertEquals(2, bfs.getPathLength(v1, v3));
    assertEquals(0, bfs.getPathLength(v3, v2));
    assertFalse(bfs.containsPath(v3, v1));
  }

  /**
   * Verify that DFSSearcher gets the vertices on the path from the source to
   * the destination.
   */
  public void testGetPath() {
    graph.addEdge(v1, v2); // v1 -> v2
    graph.addEdge(v2, v3); // v2 -> v3
    v4 = new Vertex<String>("v4");
    graph.addVertex(v4);
    graph.addEdge(v4, v2); // v4 ->v2
    final Vertex<String> dest = new Vertex<String>("v5");
    graph.addVertex(dest);
    graph.addEdge(v3, dest); // v3 -> v5
    v2 = new Vertex<String>("G");
    graph.addVertex(v2);
    final DFSSearcher<String> dfs = new DFSSearcher<String>(graph);

    ArrayList<Vertex<String>> thepath = (ArrayList<Vertex<String>>) dfs.getPath(v1, dest);
    assertTrue(thepath.contains(v1));
    assertTrue(thepath.contains(v3));
    assertTrue(thepath.contains(dest));
    assertFalse(thepath.contains(v2));
    assertFalse(thepath.contains(v4));

    final BFSSearcher<String> bfs = new BFSSearcher<String>(graph);
    thepath = (ArrayList<Vertex<String>>) bfs.getPath(v1, dest);
    assertTrue(thepath.contains(v1));
    assertTrue(thepath.contains(v3));
    assertTrue(thepath.contains(dest));
    assertFalse(thepath.contains(v2));
    assertFalse(thepath.contains(v4));
  }
}
