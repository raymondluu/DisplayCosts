
package tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import structures.AdjMatrixDiGraph;
import structures.BFSSearcher;
import structures.DFSSearcher;
import structures.Vertex;

public class TestAdjMatrixDiGraph extends TestCase {
  private AdjMatrixDiGraph<String> graph = null;

  private Vertex<String> v1, v2, v3, v4;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    graph = new AdjMatrixDiGraph<String>();
  }

  @Override
  protected void tearDown() throws Exception {
    graph = null;
    super.tearDown();
  }

  /**
   * Verify basic constructor attributes.
   */
  public void testConstructor() {
    final AdjMatrixDiGraph<String> g = new AdjMatrixDiGraph<String>();
    assertEquals(0, g.getNumberOfVertices());
    assertEquals(0, g.getNumberOfEdges());
  }

  /**
   * Verify that we can add vertices to a graph and get the correct Vertex
   * count.
   */
  public void testAddVertices() {
    v1 = new Vertex("A");
    graph.addVertex(v1);
    assertTrue(graph.getNumberOfVertices() == 1);
    assertTrue(graph.getNumberOfEdges() == 0);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    assertTrue(graph.getNumberOfVertices() == 2);
    graph.addVertex(new Vertex("C"));
    assertTrue(graph.getNumberOfVertices() == 3);
  }

  /**
   * Verify that we can add vertices and to a graph and get the correct Vertex
   * and Edge count.
   */
  public void testAddVerticesWithEdges() {
    v1 = new Vertex("A");
    graph.addVertex(v1);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    assertTrue(graph.getNumberOfVertices() == 2);
    assertTrue(graph.getNumberOfEdges() == 1);
    v3 = new Vertex("C");
    graph.addVertex(v3);
    graph.addEdge(v3, v2); // C -> B
    assertTrue(graph.getNumberOfVertices() == 3);
    assertTrue(graph.getNumberOfEdges() == 2);
  }

  /**
   * Test that we can add bidirectional edges (a pair of directed edges).
   */
  public void testAddBidirectionalEdges() {
    v1 = new Vertex("A");
    graph.addVertex(v1);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    graph.addEdge(v2, v1); // B -> A
    assertTrue(graph.getNumberOfVertices() == 2);
    assertTrue(graph.getNumberOfEdges() == 2);
    v3 = new Vertex("C");
    graph.addVertex(v3);
    graph.addEdge(v2, v3); // B -> C
    graph.addEdge(v3, v2); // C -> B
    assertTrue(graph.getNumberOfVertices() == 3);
    assertTrue(graph.getNumberOfEdges() == 4);
  }

  /**
   * Verify that duplicate edges don't get counted.
   */
  public void testAddDuplicateEdge() {
    v1 = new Vertex("A");
    graph.addVertex(v1);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    graph.addEdge(v2, v1);
    assertTrue(graph.getNumberOfVertices() == 2);
    assertTrue(graph.getNumberOfEdges() == 1);

    // add the duplicate edge
    try {
      graph.addEdge(v2, v1);
      Assert.fail("duplicate edge exception not caught");
    } catch (final IllegalArgumentException e) {
    }
    assertTrue(graph.getNumberOfVertices() == 2);
    assertTrue(graph.getNumberOfEdges() == 1);
  }

  /**
   * Verify that we can get the neighbors of a vertex. That is, the adjacent
   * vertices.
   */
  public void testNeighbors() {
    v1 = new Vertex("A");
    graph.addVertex(v1);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    graph.addEdge(v2, v1); // B -> A
    v3 = new Vertex("C");
    graph.addVertex(v3);
    graph.addEdge(v2, v3); // B -> C
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
    v1 = new Vertex("A");
    graph.addVertex(v1);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    v3 = new Vertex("C");
    graph.addVertex(v3);
    graph.addEdge(v2, v3); // B -> C

    try {
      graph.removeEdge(v1, v3); // remove nonexistent edge
      fail("failed to detect deleted non-existent edge");
    } catch (final IllegalArgumentException e) {
    }

    assertTrue(graph.getNumberOfVertices() == 3);
    assertTrue(graph.getNumberOfEdges() == 2);
    // verify that v1 still has its neightbors
    List<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph.getNeighbors(v1);
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
    v1 = new Vertex("A");
    graph.addVertex(v1);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    v3 = new Vertex("C");
    graph.addVertex(v3);
    graph.addEdge(v1, v3); // A -> C
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
    graph.addEdge(v1, v3); // A -> C
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
    v1 = new Vertex("A");
    graph.addVertex(v1);
    v2 = new Vertex("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    v3 = new Vertex("C");
    graph.addVertex(v3);
    graph.addEdge(v2, v3); // B -> C

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
    v1 = new Vertex<String>("A");
    graph.addVertex(v1);
    v2 = new Vertex<String>("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    v3 = new Vertex<String>("C");
    graph.addVertex(v3);
    graph.addEdge(v2, v3); // B -> C
    v4 = new Vertex<String>("D");
    graph.addVertex(v4);
    graph.addEdge(v4, v2); // D -> B
    v2 = new Vertex<String>("E");
    graph.addVertex(v2);
    graph.addEdge(v3, v2); // C -> B
    v2 = new Vertex<String>("G");
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
    v1 = new Vertex<String>("A");
    graph.addVertex(v1);
    final Vertex<String> origin = v1;
    v2 = new Vertex<String>("B");
    graph.addVertex(v2);
    graph.addEdge(v1, v2); // A -> B
    v3 = new Vertex<String>("C");
    graph.addVertex(v3);
    graph.addEdge(v2, v3); // B -> C
    v4 = new Vertex<String>("D");
    graph.addVertex(v4);
    graph.addEdge(v4, v2); // D -> B
    final Vertex<String> dest = new Vertex<String>("E");
    graph.addVertex(dest);
    graph.addEdge(v3, dest); // C -> D
    v2 = new Vertex<String>("G");
    graph.addVertex(v2);
    final DFSSearcher<String> dfs = new DFSSearcher<String>(graph);

    ArrayList<Vertex<String>> thepath = (ArrayList<Vertex<String>>) dfs.getPath(origin, dest);
    assertTrue(thepath.contains(origin));
    assertTrue(thepath.contains(v1));
    assertTrue(thepath.contains(v3));
    assertTrue(thepath.contains(dest));
    assertFalse(thepath.contains(v2));
    assertFalse(thepath.contains(v4));

    final BFSSearcher<String> bfs = new BFSSearcher<String>(graph);
    thepath = (ArrayList<Vertex<String>>) bfs.getPath(origin, dest);
    assertTrue(thepath.contains(origin));
    assertTrue(thepath.contains(v1));
    assertTrue(thepath.contains(v3));
    assertTrue(thepath.contains(dest));
    assertFalse(thepath.contains(v2));
    assertFalse(thepath.contains(v4));
  }
}
