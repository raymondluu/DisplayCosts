
package structures;

/**
 * Thrown when there is an attempt to access the front of an empty queue.
 */
@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {

  public EmptyQueueException() {
    super();
  }

  public EmptyQueueException(final String errMsg) {
    super(" " + errMsg);
  }
}
