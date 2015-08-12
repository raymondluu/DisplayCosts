
package tests;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import structures.AdjMatrixGraph;
import structures.BFSSearcher;
import structures.Vertex;

// import java.util.List;

public class TestGraph extends TestCase {
  private AdjMatrixGraph<String> graph = null;

  private AdjMatrixGraph<String> graph2 = null;

  private AdjMatrixGraph<String> bfsGraph = null;

  private Vertex<String> v1, v2, v3, v4, v5, v6;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    graph = new AdjMatrixGraph<String>();
    graph2 = new AdjMatrixGraph<String>();
    v1 = new Vertex("v1");
    graph2.addVertex(v1);
    v2 = new Vertex("v2");
    graph2.addVertex(v2);
    v3 = new Vertex("v3");
    graph2.addVertex(v3);

    bfsGraph = new AdjMatrixGraph<String>();
    v1 = new Vertex<String>("v1");
    bfsGraph.addVertex(v1);
    v2 = new Vertex<String>("v2");
    bfsGraph.addVertex(v2);
    bfsGraph.addEdge(v1, v2);
    v3 = new Vertex<String>("v3");
    bfsGraph.addVertex(v3);
    bfsGraph.addEdge(v1, v3);
    v4 = new Vertex<String>("v4");
    bfsGraph.addVertex(v4);
    bfsGraph.addEdge(v4, v2);
    v5 = new Vertex<String>("v5");
    bfsGraph.addVertex(v5);
    bfsGraph.addEdge(v5, v3);
    v6 = new Vertex<String>("v6");
    bfsGraph.addVertex(v6);
    bfsGraph.addEdge(v6, v4);
    bfsGraph.addEdge(v6, v5);
  }

  @Override
  protected void tearDown() throws Exception {
    graph = null;
    graph2 = null;
    bfsGraph = null;
    super.tearDown();
  }

  public void testConstructor() {
    assertEquals(0, graph.getNumberOfVertices());
    assertEquals(0, graph.getNumberOfEdges());
  }

  /**
   * Verify that we can add vertices to a graph and get the correct Vertex
   * count.
   */
  public void testAddVertices() {
    assertEquals(3, graph2.getNumberOfVertices());
  }

  /**
   * Verify that we can add vertices and to a graph and get the correct Vertex
   * and Edge count.
   */
  public void testAddVerticesWithEdges() {
    graph2.addEdge(v1, v2); // A <-> B
    graph2.addEdge(v3, v2); // C <-> B
    assertEquals(3, graph2.getNumberOfVertices());
    assertEquals(2, graph2.getNumberOfEdges());
  }

  /**
   * Verify that we can get the neighbors of a vertex. That is, the adjacent
   * vertices.
   */
  public void testNeighbors() {
    graph2.addEdge(v1, v2); // A <-> B
    // graph2.addEdge( v2, v1 ); // B <-> A DUPLICATE
    graph2.addEdge(v2, v3); // B <-> C
    assertEquals(3, graph2.getNumberOfVertices());
    assertEquals(2, graph2.getNumberOfEdges());
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v1);
    assertEquals(1, neighbors.size());

    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v2);
    assertEquals(2, neighbors.size());
    assertTrue(neighbors.contains(v1));
    assertTrue(neighbors.contains(v3));
  }

  /**
   * Verify that removing a nonexistent edge does not detach any connected
   * vertices.
   */
  public void testRemoveEdgeNotExist() {
    graph2.addEdge(v1, v2); // A <-> B
    graph2.addEdge(v2, v3); // B <-> C

    try {
      graph2.removeEdge(v1, v3); // remove nonexistent edge
      fail("failed to detect deleted non-existent edge");
    } catch (final IllegalArgumentException e) {
    }

    assertEquals(3, graph2.getNumberOfVertices());
    assertEquals(2, graph2.getNumberOfEdges());
    // verify that v1 still has its neightbors
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v1);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v2));

    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v2);
    assertEquals(2, neighbors.size());
    assertTrue(neighbors.contains(v1));
    assertTrue(neighbors.contains(v3));
  }

  /**
   * Verify that removing an edge updates the associated vertices. Make sure we
   * can add the edge back.
   */
  public void testRemoveEdgeExists() {
    graph2.addEdge(v1, v2); // A <-> B
    graph2.addEdge(v1, v3); // A <-> C
    assertEquals(3, graph2.getNumberOfVertices());
    assertEquals(2, graph2.getNumberOfEdges());

    // now remove an edge
    graph2.removeEdge(v1, v3);
    assertEquals(1, graph2.getNumberOfEdges());
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v1);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v2));

    // now add the edge back
    graph2.addEdge(v1, v3); // A <-> C
    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v1);
    assertTrue(neighbors.size() == 2);
    assertTrue(neighbors.contains(v2));
    assertTrue(neighbors.contains(v3));
    assertEquals(3, graph2.getNumberOfVertices());
    assertEquals(2, graph2.getNumberOfEdges());
  }

  // now include tests for exceptional conditions
  public void testAddNullVertex() {
    try {
      graph2.addEdge(v1, null);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }

    // now try it with null as the origin vertex
    try {
      graph.addEdge(null, v1);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
  }

  public void testAddVertexNotInGraph() {
    v2 = new Vertex<String>("Poo");
    try {
      graph.addEdge(v1, v2);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
    try {
      graph.addEdge(v2, v1);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
  }

  /**
   * Verify that removing a vertex removes the associated edges. Make sure we
   * can add the vertex back.
   */
  public void testRemoveVertex1() {
    graph2.addEdge(v1, v2); // A <-> B
    graph2.addEdge(v2, v3); // B <-> C

    // Now remove the vertex
    graph2.removeVertex(v2);
    assertEquals(2, graph2.getNumberOfVertices());
    assertEquals(0, graph2.getNumberOfEdges());
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v1);
    assertEquals(0, neighbors.size());
    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v3);
    assertEquals(0, neighbors.size());

    // now add a vertex back
    graph2.addVertex(v2);
    graph2.addEdge(v1, v2);
    graph2.addEdge(v2, v3);
    assertEquals(3, graph2.getNumberOfVertices());
    assertEquals(2, graph2.getNumberOfEdges());

    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v2);
    assertEquals(2, neighbors.size());
    assertTrue(neighbors.contains(v1));
    assertTrue(neighbors.contains(v3));

    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v1);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v2));
  }

  /**
   * Verify that removing a vertex removes the associated edges. Make sure we
   * can add the vertex back.
   */
  public void testRemoveVertex2() {
    graph2.addEdge(v1, v2); // A <-> B
    graph2.addEdge(v2, v3); // B <-> C

    // Now remove the vertex
    graph2.removeVertex(v1);
    assertEquals(2, graph2.getNumberOfVertices());
    assertEquals(1, graph2.getNumberOfEdges());
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v2);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v3));
    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v3);
    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(v2));
  }

  /**
   * Verify that removing a vertex removes the associated edges.
   */
  public void testRemoveVertex3() {
    graph2.addEdge(v1, v2); // v1 <-> v2
    graph2.addEdge(v3, v2); // v3 <-> v2
    graph2.addVertex(v4);
    graph2.addEdge(v4, v2); // v4 <-> v2

    // Now remove the vertex
    graph2.removeVertex(v2);
    assertEquals(3, graph2.getNumberOfVertices());
    assertEquals(0, graph2.getNumberOfEdges());
    ArrayList<Vertex<String>> neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v1);
    assertEquals(0, neighbors.size());
    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v3);
    assertEquals(0, neighbors.size());
    neighbors = (ArrayList<Vertex<String>>) graph2.getNeighbors(v4);
    assertEquals(0, neighbors.size());

  }

  public void testDuplicateElement() {
    try {
      graph2.addVertex(new Vertex("v1"));
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
  }

  /**
   * Adding duplicate edges should not change the edge count. Since this graph
   * is undirected, this test tries two forms of a duplicate edge (v1 to v2 and
   * v2 to v1).
   */
  public void testDuplicateEdge() {
    graph2.addEdge(v1, v2);
    assertEquals(1, graph2.getNumberOfEdges());
    // add the duplicate edge
    try {
      graph2.addEdge(v2, v1);
      Assert.fail("duplicate edge exception not caught");
    } catch (final IllegalArgumentException e) {
    }
    assertEquals(1, graph2.getNumberOfEdges());
    try {
      graph2.addEdge(v1, v2);
      Assert.fail("duplicate edge exception not caught");
    } catch (final IllegalArgumentException e) {
    }
    assertEquals(1, graph2.getNumberOfEdges());
  }

  public void testNullAndMissingNeighbor() {
    v1 = null;
    // neighbor is null
    try {
      graph.getNeighbors(v1);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }

    // neighbor not in the graph
    try {
      graph.getNeighbors(new Vertex<String>("Poo"));
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
  }

  public void testRemoveNullAndMissingVertex() {
    v1 = null;
    // neighbor is null
    try {
      graph.removeVertex(v1);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }

    // neighbor not in the graph
    try {
      graph.removeVertex(new Vertex<String>("Poo"));
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
  }

  public void testRemoveNullAndMissingEdge() {
    // there are four combinations, at least
    v2 = new Vertex<String>("Poo");
    // null second argument - bad!
    try {
      graph.removeEdge(v1, null);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }

    // null first argument - bad!
    try {
      graph.removeEdge(null, v1);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }

    // v2 not in the graph - second argument
    try {
      graph.removeEdge(v1, v2);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }

    // v2 not in the graph - first argument
    try {
      graph.removeEdge(v2, v1);
      Assert.fail("exception should have been thrown");
    } catch (final IllegalArgumentException e) {
    }
  }

  public void testbfsSearch() {
    final BFSSearcher<String> bfsSearcher = new BFSSearcher<String>(bfsGraph);
    final ArrayList<Vertex<String>> list = (ArrayList<Vertex<String>>) bfsSearcher.bfs(v1);
    assertEquals(6, list.size());
    assertTrue(list.get(0).equals(v1));
    assertTrue(list.get(1).equals(v2));
    assertTrue(list.get(2).equals(v3));
    assertTrue(list.get(3).equals(v4));
    assertTrue(list.get(4).equals(v5));
    assertTrue(list.get(5).equals(v6));
  }

  public void testbfsSearchContainsPathExists() {
    final BFSSearcher<String> bfsSearcher = new BFSSearcher<String>(bfsGraph);
    assertEquals(true, bfsSearcher.containsPath(v1, v6));
  }

  public void testbfsSearchConatainsPathNotExists() {
    final Vertex<String> v7 = new Vertex<String>("v7");
    bfsGraph.addVertex(v7);
    final BFSSearcher<String> bfsSearcher = new BFSSearcher<String>(bfsGraph);
    assertEquals(false, bfsSearcher.containsPath(v1, v7));
  }

  public void testbfsSearchGetPathLengthExists() {
    final BFSSearcher<String> bfsSearcher = new BFSSearcher<String>(bfsGraph);
    assertEquals(3, bfsSearcher.getPathLength(v1, v6));
    assertEquals(2, bfsSearcher.getPathLength(v1, v5));
    assertEquals(1, bfsSearcher.getPathLength(v1, v2));
  }

  public void testbfsSearchGetPathLengthNotExists() {
    final Vertex<String> v7 = new Vertex<String>("v7");
    bfsGraph.addVertex(v7);
    final BFSSearcher<String> bfsSearcher = new BFSSearcher<String>(bfsGraph);
    assertEquals(0, bfsSearcher.getPathLength(v1, v7));
  }

  public void testbfsSearchGetPathExists() {
    final BFSSearcher<String> bfsSearcher = new BFSSearcher<String>(bfsGraph);
    final ArrayList<Vertex<String>> thePath =
        (ArrayList<Vertex<String>>) bfsSearcher.getPath(v1, v6);
    assertEquals(4, thePath.size());
    assertTrue(thePath.contains(v1));
    assertTrue(thePath.contains(v2));
    assertTrue(thePath.contains(v4));
    assertTrue(thePath.contains(v6));
  }

}
