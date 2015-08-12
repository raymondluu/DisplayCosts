/*
 * EmptyPriorityQueueException TCSS 342 Assignment 5
 */

package structures;

/**
 * Thrown when an empty priority queue has been accessed.
 * 
 * @author Alan Fowler
 * @version Spring 2011
 */
public class EmptyPriorityQueueException extends RuntimeException {

  /**
   * A generated serial version UID.
   */
  private static final long serialVersionUID = -5288309290889409167L;

  /**
   * Constructs a new EmptyPriorityQueueException.
   */
  public EmptyPriorityQueueException() {
    super();
  }

  /**
   * Constructs a new EmptyPriorityQueueException using the parameter as a
   * message.
   * 
   * @param the_message the message to include with this Exception.
   */
  public EmptyPriorityQueueException(final String the_message) {
    super(" " + the_message);
  }

}
