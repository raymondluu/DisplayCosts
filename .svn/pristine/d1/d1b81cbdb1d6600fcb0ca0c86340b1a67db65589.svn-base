/*
 * Tuple.java TCSS 342 Assignment 5
 */

package structures;

/**
 * A tuple consists of two elements. The elements may be of different types. If
 * both elements are of the same data type then class Pair may be more
 * appropriate.
 * 
 * @author Alan Fowler
 * @version Spring 2011
 * 
 * @param <T> The data type of the first element
 * @param <U> The data type of the second element
 * @see Pair
 */
public class Tuple<T, U> {

  /**
   * The first element in this Tuple.
   */
  private T my_first_element;

  /**
   * The second element in this Tuple.
   */
  private U my_second_element;

  /**
   * Construct an instance of a <tt>Tuple</tt> initialized to the given
   * elements.
   * 
   * @param element_1 the first element of this tuple
   * @param element_2 the second element of this tuple
   * @throws IllegalArgumentException if either <tt>element_1</tt> or
   *           <tt>element_2</tt> is <tt>null</tt>.
   */
  public Tuple(final T element_1, final U element_2) throws IllegalArgumentException {
    if ((element_1 == null) || (element_2 == null)) {
      throw new IllegalArgumentException();
    }
    my_first_element = element_1;
    my_second_element = element_2;
  }

  /**
   * Return the value of the first element of this tuple.
   * 
   * @return the first element of this tuple
   */
  public T getFirstElement() {
    return my_first_element;
  }

  /**
   * Return the value of the second element of this tuple.
   * 
   * @return the second element of this tuple
   */
  public U getSecondElement() {
    return my_second_element;
  }

  /**
   * Set the first element of this tuple to the new value.
   * 
   * @param the_element the new value for the first element of this tuple
   * @throws IllegalArgumentException if <tt>newFirst</tt> is <tt>null</tt>.
   */
  public void setFirstElement(final T the_element) throws IllegalArgumentException {
    if (the_element == null) {
      throw new IllegalArgumentException();
    }
    my_first_element = the_element;
  }

  /**
   * Set the second element of this tuple to the new value.
   * 
   * @param the_element the new value for the second element of this tuple
   * @throws IllegalArgumentException if <tt>newFirst</tt> is <tt>null</tt>.
   */
  public void setSecondElement(final U the_element) throws IllegalArgumentException {
    if (the_element == null) {
      throw new IllegalArgumentException();
    }
    my_second_element = the_element;
  }

}
