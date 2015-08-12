/*
 * DisplayCostsMain TCSS 342 Assignment 5
 * 
 * Raymond Luu
 * TCSS 342 Winter 2013
 * Assignment 5
 */

package application;

import java.util.Scanner;

import structures.Vertex;
import structures.WeightedAdjMatrixGraph;
import structures.WeightedGraph;

/**
 * A program to display path costs in a weighted directed graph.
 * 
 * @author Alan Fowler
 * @version Spring 2011
 */
public final class DisplayCostsMain {

  /**
   * A value the user can enter to quit the program.
   */
  private static final int QUIT_OPTION = 0;

  /**
   * The graph used in this program.
   */
  private final WeightedGraph<String> my_graph;

  /**
   * Private constructor to inhibit external instantiation.
   */
  private DisplayCostsMain() {
    my_graph = new WeightedAdjMatrixGraph<String>();
  }

  /**
   * The start point for the program.
   * 
   * @param the_args command line arguments - ignored
   */
  public static void main(final String[] the_args) {
    new DisplayCostsMain().start();
  }

  /**
   * Calls various methods to provide program functionality.
   */
  private void start() {
    FileIO.createGraphFromFile(my_graph);
    final Vertex<String>[] vertices = my_graph.getVertices();

    // comment in the next line to display a representation of the graph
    // System.out.println(my_graph);

    // report basic statistics
    System.out.println("The number of vertices in the graph : " 
                       + my_graph.getNumberOfVertices());
    System.out.println("The number of edges in the graph : " + my_graph.getNumberOfEdges());

    // find diameter
    double diameter;
    diameter = 0;
    // add code here to set diameter correctly or write a helper method to do it
    // added code by Raymond Luu
    final double[][] shortest_paths = WeightedAdjMatrixGraph.floydShortestPaths(
                                                  (WeightedAdjMatrixGraph<String>) my_graph);
    
    for (int i = 0; i < my_graph.getNumberOfVertices(); i++) {
      for (int j = 0; j < i; j++) {
        if (shortest_paths[i][j] >= diameter) {
          diameter = shortest_paths[i][j];
        }
      }
    }
    // End of added code
    System.out.println("The diameter of this graph is : " + diameter);

    // create a Scanner for keyboard input
    final Scanner console = new Scanner(System.in);
    boolean run_again = true;

    // display an introduction
    displayIntro();

    while (run_again) { // loop until the user chooses to quit

      int from = 0;
      int to = 0;

      // get the user's choice for a start city
      from =
          promptForChoice(console,
                          "\nChoose a city to start at " + "(1 - "
                              + my_graph.getNumberOfVertices() + ") or enter " + QUIT_OPTION
                              + " to quit the program : ");

      // perform some processing based on the menu choice
      if (from == QUIT_OPTION) {
        run_again = false;
      } else {
        System.out.println(vertices[from - 1] + " has degree " 
                           + my_graph.getNeighbors(vertices[from - 1]).size() + ".");

        // get the user's choice for an end city
        to =
            promptForChoice(console,
                            "\nChoose a city to end at (1 - " + my_graph.getNumberOfVertices()
                                + ") or " + QUIT_OPTION + " to quit : ");
      }
      if (to == QUIT_OPTION) {
        run_again = false;
      } else {
        displayPathLength(vertices[from - 1], vertices[to - 1]);
      }
    }
    System.out.println("\nThanks for trying this program. Have a nice day.");
  }

  /**
   * Displays an introduction to the program.
   */
  public void displayIntro() {
    System.out.println("\nThis program reports the length of "
                       + "the shortest path between two cities.");
    System.out.println("The program will repeat until the user chooses to quit.");
    System.out.println("\nThe cities are:");
    int city = 1;
    for (Vertex<String> name : my_graph.getVertices()) {
      System.out.printf("%-3d%s\n", city++, name);
    }
  }

  /**
   * Prompts for a menu choice in the range 1 to QUIT_OPTION.
   * 
   * @param the_console a Scanner used to capture user input
   * @param the_prompt a prompt to the user
   * @return the number entered by the user
   */
  private int promptForChoice(final Scanner the_console, final String the_prompt) {
    int choice = getInt(the_console, the_prompt);
    while (choice < 0 || choice > my_graph.getNumberOfVertices()) {
      System.out.println("Invalid selection. Please try again.");
      choice = getInt(the_console, the_prompt);
    }
    return choice;
  }

  /**
   * Prompts for an integer until an integer is entered.
   * 
   * This method is adopted from getInt() on page 315 of
   * "Building Java Programs" by Reges and Stepp
   * 
   * @param the_console a Scanner used to capture user input
   * @param the_prompt a prompt to the user
   * @return the integer entered by the user
   */
  private int getInt(final Scanner the_console, final String the_prompt) {
    System.out.print(the_prompt);
    while (!the_console.hasNextInt()) {
      the_console.next();
      System.out.println("Enter an integer. Please try again.");
      System.out.print(the_prompt);
    }
    return the_console.nextInt();
  }

  /**
   * Displays the length of the path from point_1 to point_2.
   * 
   * @param point_1 the start point for the path
   * @param point_2 the end point for the path
   */
  private void displayPathLength(final Vertex<String> point_1, final Vertex<String> point_2) {
    int path_length;
    path_length = 0;
    // add code here to set path_length correctly
    // added code by Raymond Luu
    path_length = (int) ((WeightedAdjMatrixGraph<String>) my_graph).minimalPath(point_1, 
                                                                                point_2);
    // End of code
    System.out.println("The distance from " + point_1 + " to " + point_2 + " is: "
                       + path_length);
  }

}
