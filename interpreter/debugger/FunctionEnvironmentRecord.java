package interpreter.debugger;

import java.util.Iterator;

public class FunctionEnvironmentRecord {
  private String name;
  private int start, end, currentLineNumber;

  Table symbolTable = new Table();

  public void beginScope() {
    symbolTable.beginScope();
  }

  public void setFunctionInfo(String functionName, int startingLineNumber, int endingLineNumber) {
    this.name = functionName;
    this.start = startingLineNumber;
    this.end = endingLineNumber;
  }

  public int getCurrentLine(){
    return currentLineNumber;
  }

  public void setCurrentLineNumber(int currentLineNumber) {
    this.currentLineNumber = currentLineNumber;
  }

  public void enter(String symbol, int value) {
    symbolTable.put(symbol, value);
  }

  public void pop(int count) {
    symbolTable.endScope(count);
  }

  public Object get(String var) {
    return symbolTable.get(var);
  }

  public Table getSymbolTable(){
    return symbolTable;
  }

  public int getStart(){
    return start;
  }
  
  public int getEnd(){
    return end;
  }

  /**
   * Utility method to test your implementation. The output should be:
   * (<>, -, -, -, -)
   * (<>, g, 1, 20, -)
   * (<>, g, 1, 20, 5)
   * (<a/4>, g, 1, 20, 5)
   * (<b/2, a/4>, g, 1, 20, 5)
   * (<b/2, a/4, c/7>, g, 1, 20, 5)
   * (<b/2, a/1, c/7>, g, 1, 20, 5)
   * (<b/2, a/4>, g, 1, 20, 5)
   * (<a/4>, g, 1, 20, 5)
   */

  public String toString() {
    Iterator<String> iterate = symbolTable.keys().iterator();
    String var = "", temp = "";
    if (symbolTable.keys().size() != 0) {
      temp = iterate.next().toString();
      var += "(<" + temp + "/" + this.get(temp);
      while (iterate.hasNext()) {
        temp = iterate.next().toString();
        var += "," + temp + "/" + this.get(temp);
      }
      var += ">" + ", " + name + ", " + start + ", " + end + ", " + currentLineNumber + ")";
    } else{
      var = "(<>, " + name + ", " + start + ", " + end + ", " + currentLineNumber + ")";
    }
    return var;
  }

  public static void main(String[] args) {
    FunctionEnvironmentRecord record = new FunctionEnvironmentRecord();

    record.beginScope();
    System.out.println(record);

    record.setFunctionInfo("g", 1, 20);
    System.out.println(record);

    record.setCurrentLineNumber(5);
    System.out.println(record);

    record.enter("a", 4);
    System.out.println(record);

    record.enter("b", 2);
    System.out.println(record);

    record.enter("c", 7);
    System.out.println(record);

    record.enter("a", 1);
    System.out.println(record);

    record.pop(2);
    System.out.println(record);

    record.pop(1);
    System.out.println(record);
  }
}