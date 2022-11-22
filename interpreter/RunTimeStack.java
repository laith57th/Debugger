package interpreter;

import java.util.Stack;
import java.util.Vector;
import java.util.ArrayList;

public class RunTimeStack {

  private Stack<Integer> framePointers;
  // This may not be the right parameterized type!!
  private Vector<Integer> runStack;
  private int frameIndex;

  public RunTimeStack() {
    runStack = new Vector<>();
    framePointers = new Stack<>();
    framePointers.add(0);
  }

  /**
   * The purpose of this function is to dump the RunTimeStack for the
   * purpose of debugging.
   */
  public void dump() {
    ArrayList<Integer> framePointer = new ArrayList<Integer>();
    if (frameIndex > 0) {
      System.out.print("[");
      for (int i = 0; i < runStack.size(); i++) {
        if (i == frameIndex - 1) {
          System.out.print(runStack.get(i));
        } else if (i < frameIndex) {
          System.out.print(runStack.get(i) + ", ");
        } else {
          framePointer.add(runStack.get(i));
        }
      }
      System.out.print("]");
    } else {
      System.out.print(runStack);
    }

    if (frameIndex > 0) {
      System.out.print(framePointer);
    }
    System.out.println();
  }

  public int getValue(int value){
    return runStack.get(value);
  }

  /**
   * Returns the top item on the runtime stack.
   */
  public int peek() {
    int peek = runStack.get(runStack.size() - 1);
    return peek;
  }

  /**
   * Pops the top item from the runtime stack, returning the item.
   */
  public int pop() {
    int pop = runStack.remove(runStack.size() - 1);
    return pop;
  }

  /**
   * Push an item on to the runtime stack, returning the item that was just
   * pushed.
   */
  public int push(int item) {
    runStack.add(item);
    return item;
  }

  /**
   * This second form with an Integer parameter is used to load literals onto the
   * stack.
   */
  public Integer push(Integer i) {
    runStack.add(i);
    return i;
  }

  public int length() {
    return runStack.size() - 1;
  }

  public void setFrameIndex(int index) {
    this.frameIndex = index;
  }

  /**
   * Start a new frame, where the parameter offset is the number of slots
   * down from the top of the RunTimeStack for starting the new frame.
   */
  public void newFrameAt(int offset) {
    framePointers.push(runStack.size() - offset);
  }

  /**
   * We pop the top frame when we return from a function; before popping, the
   * functions’ return value is at the top of the stack so we’ll save the value,
   * pop the top frame, and then push the return value.
   */
  public void popFrame() {
    int frame = pop();
    while (runStack.size() > framePointers.peek()) {
      pop();
    }
    frameIndex = 0;
    framePointers.pop();
    runStack.add(frame);
  }

  /**
   * Used to store into variables.
   */
  public int store(int offset) {
    int store = this.pop();
    runStack.set(offset, store);
    return store;
  }

  /**
   * Used to load variables onto the stack.
   */
  public int load(int offset) {
    int load;
    if (framePointers.peek() + offset < runStack.size()) {
      load = runStack.get(framePointers.peek() + offset);
    } else {
      load = runStack.get(framePointers.peek());
    }

    runStack.add(load);
    return load;
  }

  public int getLastFrameValue() {
    return framePointers.peek();
  }
}