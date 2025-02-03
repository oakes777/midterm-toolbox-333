import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Toolbox {

  /**
   * Removes an element from an array of strings at the specified index, padding
   * with nulls at the end.
   *
   * @param array the array of strings to modify
   * @param index the index of the element to remove
   * @throws IllegalArgumentException if the array is null or the index is out of
   *                                  bounds
   */
  public static void removeElementInPlace(String[] array, int index) {
    if (array == null || index < 0 || index >= array.length) {
      throw new IllegalArgumentException("Array cannot be null and index must be within bounds.");
    }

    // Shift elements left from the removal index
    for (int i = index; i < array.length - 1; i++) {
      array[i] = array[i + 1];
    }

    // Set the last element to null
    array[array.length - 1] = null;
  }

  /**
   * Adds an element to an array of strings at a specified location in-place,
   * evicting the last value.
   *
   * @param array the array of strings to modify
   * @param index the index at which to add the new element
   * @param value the value to add
   * @throws IllegalArgumentException if the array is null or the index is out of
   *                                  bounds
   */
  public static void addElementInPlace(String[] array, int index, String value) {
    if (array == null || index < 0 || index >= array.length) {
      throw new IllegalArgumentException("Array cannot be null and index must be within bounds.");
    }

    // Shift elements right from the last index down to the insertion index
    for (int i = array.length - 1; i > index; i--) {
      array[i] = array[i - 1];
    }

    // Insert the new value at the specified index
    array[index] = value;

  }

  /**
   * Finds the tail of a singly linked list given the head.
   *
   * @param head the head node of the singly linked list
   * @return the tail node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the head is null
   */
  public static SingleNode findTail(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    // Traverse until the last node (where next is null)
    SingleNode current = head;
    while (current.next != null) {
      current = current.next;
    }

    return current; // The last node is the tail
  }

  /**
   * Finds the head of a doubly linked list given the tail.
   *
   * @param tail the tail node of the doubly linked list
   * @return the head node of the list, or null if the list is empty
   * @throws IllegalArgumentException if the tail is null
   */
  public static DoubleNode findHead(DoubleNode tail) {
    if (tail == null) {
      throw new IllegalArgumentException("Tail cannot be null.");
    }

    // Traverse backwards until we reach the head (where prev is null)
    DoubleNode current = tail;
    while (current.prev != null) {
      current = current.prev;
    }

    return current; // The first node is the head
  }

  /**
   * Counts the occurrences of values in a linked list.
   *
   * @param head the head node of the linked list
   * @return a map where the keys are the values in the list, and the values are
   *         the counts of occurrences
   * @throws IllegalArgumentException if the head is null
   */
  public static Map<Integer, Integer> countOccurrences(SingleNode head) {
    if (head == null) {
      throw new IllegalArgumentException("Head cannot be null.");
    }

    Map<Integer, Integer> occurrences = new HashMap<>();
    SingleNode current = head;

    // Traverse the list and count occurrences
    while (current != null) {
      occurrences.put(current.data, occurrences.getOrDefault(current.data, 0) + 1);
      current = current.next;
    }

    return occurrences;
  }

  /**
   * Removes a node from a doubly linked list.
   *
   * @param node the node to remove
   * @throws IllegalArgumentException if the node is null
   */
  public static void removeNode(DoubleNode node) {
    if (node == null) {
      throw new IllegalArgumentException("Node cannot be null.");
    }

    // If the node has a previous node, update its next pointer
    if (node.prev != null) {
      node.prev.next = node.next;
    }

    // If the node has a next node, update its previous pointer
    if (node.next != null) {
      node.next.prev = node.prev;
    }

    // Nullify the node's pointers to help with garbage collection
    node.next = null;
    node.prev = null;

  }

  /**
   * Finds the nth element in a singly linked list.
   *
   * @param head the head node of the singly linked list
   * @param n    the index of the element to find (0-based)
   * @return the nth node, or null if the index is out of bounds
   * @throws IllegalArgumentException if the head is null or n is negative
   */
  public static SingleNode findNthElement(SingleNode head, int n) {
    if (head == null || n < 0) {
      throw new IllegalArgumentException("Head cannot be null and n cannot be negative.");
    }

    SingleNode current = head;
    int count = 0;

    // Traverse the list until reaching the nth node
    while (current != null) {
      if (count == n) {
        return current; // Found the nth node
      }
      current = current.next;
      count++;
    }

    // If n is out of bounds, return null
    return null;
  }

  /**
   * Inserts a new node into a singly linked list given a pointer to a node in the
   * middle of the list.
   *
   * @param node    the node before which the new node is to be inserted
   * @param newNode the new node to insert
   * @throws IllegalArgumentException if either node or newNode is null
   */
  public static void insertNode(SingleNode node, SingleNode newNode) {
    if (node == null || newNode == null) {
      throw new IllegalArgumentException("Node and newNode cannot be null.");
    }

    // Link newNode to the next node in the list
    newNode.next = node.next;
    // link node to newNode
    node.next = newNode;

  }

  /**
   * Rotates a queue to the left by the specified number of positions in-place.
   * 
   * The first k elements of the queue are moved to the end, preserving the order
   * of all elements.
   *
   * Example:
   * Given a queue [1, 2, 3, 4, 5] and k = 2, the result will be [3, 4, 5, 1, 2].
   *
   * @param queue the queue to rotate
   * @param k     the number of positions to rotate to the left
   * @throws IllegalArgumentException if the queue is null or k is negative
   */
  public static void rotateQueueLeft(Queue<Integer> queue, int k) {
    if (queue == null || k < 0) {
      throw new IllegalArgumentException("Queue cannot be null and k cannot be negative.");
    }

    for (int i = 0; i < k; i++) {
      // Remove front element and add it to the back
      queue.add(queue.poll());
    }

  }

  /**
   * Checks if a string has balanced parentheses using a stack.
   * 
   * A string is considered to have balanced parentheses if each opening
   * parenthesis
   * '(' has a corresponding closing parenthesis ')', and the parentheses are
   * correctly nested.
   *
   * Example:
   * - Input: "(()())" -> Returns true
   * - Input: "(()" -> Returns false
   * - Input: ")" -> Returns false
   *
   * @param input the string to check
   * @return true if the string has balanced parentheses, false otherwise
   * @throws IllegalArgumentException if the input string is null
   */
  public static boolean hasBalancedParentheses(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input string cannot be null.");
    }

    Stack<Character> stackie = new Stack<>();

    for (char chichi : input.toCharArray()) {
      if (chichi == '(') {
        stackie.push(chichi);
      } else if (chichi == ')') {
        if (stackie.isEmpty()) {
          // closing parenthesis with no matching opening
          return false;
        }
        stackie.pop();
      }
    }

    // If stack is empty, parentheses are balanced
    return stackie.isEmpty();
  }

}