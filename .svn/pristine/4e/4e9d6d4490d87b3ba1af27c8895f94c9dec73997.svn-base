/*
 * HeapPriorityQueue.java TCSS 342 Assignment 5
 */

package structures;

import java.util.Collection;
import java.util.Comparator;

/**
 * Provides "highest priority element in the queue is the first element out"
 * (HPIFO) functionality.
 * 
 * @author Alan Fowler
 * @version Spring 2011
 * @param <E> the data type of the elements stored in this priority queue
 */
public class HeapPriorityQueue<E> implements PriorityQueue<E> {

  /**
   * The backing store for this priority queue.
   */
  private final ArrayMinHeap<E> my_data;

  /**
   * The Comparator used to order the elements in this priority queue.
   */
  private final Comparator<? super E> my_comparator;

  /**
   * Constructs an empty priority queue. The ordering of elements in the
   * priority queue is defined by <code>the_comparator</code>.
   * 
   * @param the_comparator the Comparator used to order the elements of this
   *          priority queue
   * @throws IllegalArgumentException if the_comparator is null.
   */
  public HeapPriorityQueue(final Comparator<? super E> the_comparator)
      throws IllegalArgumentException {

    if (the_comparator == null) {
      throw new IllegalArgumentException();
    }
    my_comparator = the_comparator;
    my_data = new ArrayMinHeap<E>(my_comparator);
  }

  /**
   * Constructs a priority queue with the elements from
   * <code>the_collection</code> using the ordering defined by
   * <code>the_comparator</code>.
   * 
   * @param the_collection the Collection used to populate this priority queue
   * @param the_comparator the Comparator used to order this priority queue
   * @throws IllegalArgumentException if either argument is null.
   */
  public HeapPriorityQueue(final Collection<? extends E> the_collection,
                           final Comparator<? super E> the_comparator)
      throws IllegalArgumentException {

    if ((the_comparator == null) || (the_collection == null)) {
      throw new IllegalArgumentException();
    }
    my_comparator = the_comparator;
    my_data = new ArrayMinHeap<E>(my_comparator);
    for (E next_element : the_collection) {
      my_data.add(next_element);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {
    my_data.clear();
  }

  /** {@inheritDoc} */
  @Override
  public E dequeue() {
    if (isEmpty()) {
      throw new EmptyPriorityQueueException();
    }
    return my_data.top();
  }

  /** {@inheritDoc} */
  @Override
  public void enqueue(final E the_element) {
    my_data.add(the_element);
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    return my_data.isEmpty();
  }

  /** {@inheritDoc} */
  @Override
  public E peek() {
    if (isEmpty()) {
      throw new EmptyPriorityQueueException();
    }
    return my_data.peek();
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    return my_data.size();
  }

}
