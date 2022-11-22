package interpreter.debugger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.debuggercodes.LineCode;

public class DebuggerVirtualMachine extends VirtualMachine {
  public Stack<FunctionEnvironmentRecord> functionEnvironmentStack;
  protected ArrayList<Entry> sourceCodeEntries;
  private BufferedReader sourceReader;
  private boolean dvmIsRunning;
  private String readLine;
  private Program program;
  private Entry entry;

  public DebuggerVirtualMachine(Program program, String sourceFile) throws IOException {
    super(program);
    this.program = program;
    functionEnvironmentStack = new Stack<FunctionEnvironmentRecord>();
    sourceCodeEntries = new ArrayList<Entry>();
    functionEnvironmentStack.push(new FunctionEnvironmentRecord());
    this.sourceReader = new BufferedReader(new FileReader(sourceFile));
    readLine = sourceReader.readLine();
    while (readLine != null) {
      entry = new Entry();
      entry.setIsBreakPoint(false);
      entry.setLineNumber(getEntriesSize() + 1);
      entry.setSourceLine(readLine);
      sourceCodeEntries.add(entry);
      readLine = sourceReader.readLine();
    }
    program = new Program();
  }

  public void continueExecute() {
    dvmIsRunning = true;

    if (getpc() == 0) {
      Initialize();
    }

    while (dvmIsRunning) {
      ByteCode code = program.getCode(getpc());
      int line;
      if (code instanceof LineCode) {
        code.execute(this);
        line = functionEnvironmentStack.peek().getCurrentLine();
        if(line>0){
          line--;
        }
        if (hasBreakpoint(line) && line > 0) {
          dvmIsRunning = false;
        }
        setpc(getpc() + 1);
      } else {
        code.execute(this);
        setpc(getpc() + 1);
      }
    }
  }

  public void printSourceCode() {
    int start = environmentRecordPeek().getStart();
    int end = environmentRecordPeek().getEnd();

    if (start == 0 && end == 0) {
      for (int i = 0; i < getEntriesSize(); i++) {
          if (hasBreakpoint(i)) {
            System.out.println("* " + getLineNumber(i) + ": " + getSourceLine(i));
          } else {
            System.out.println("  " + getLineNumber(i) + ": " + getSourceLine(i));
          }
      }
    } else {
      for (int i = start - 1; i < end; i++) {
        if (hasBreakpoint(i)) {
          System.out.println("* " + getLineNumber(i) + ": " + getSourceLine(i));
        } else {
          System.out.println("  " + getLineNumber(i) + ": " + getSourceLine(i));
        }
      }
    }
  }

  public void pushOntoFunctionEnvRecord(FunctionEnvironmentRecord record) {
    functionEnvironmentStack.push(record);
  }

  public void stepExecute() {
    ByteCode code = program.getCode(getpc());
    if (getpc() == 0) {
      Initialize();
    }
    if (code instanceof LineCode) {
      code.execute(this);
      if (hasBreakpoint(functionEnvironmentStack.peek().getCurrentLine())) {
        dvmIsRunning = false;
      }
      setpc(getpc() + 1);
    } else {
      code.execute(this);
      setpc(getpc() + 1);
    }
  }

  public void printCurrentLocals() {
    FunctionEnvironmentRecord locals = environmentRecordPeek();
    for (String key : locals.getSymbolTable().keys()) {
      int offset = (Integer) locals.get(key) + getRunStack().getLastFrameValue();
      System.out.println(key + ": " + getRunStack().getValue(offset));
    }
  }

  // @Override
  // public void executeProgram(){
  // while(getRunningStatus()){
  // if(DebuggerVMIsRunning){
  // ByteCode code = this.program.getCode(getpc());
  // code.execute(this);
  // this.setpc(getpc()+1);
  // if(getpc() == program.getVector().size()){
  // System.out.println("Debugging finished!");
  // this.setpc(0);
  // this.functionEnvironmentStack = new Stack<>();
  // functionEnvironmentStack.push(new FunctionEnvironmentRecord());
  // }
  // }
  // }
  // }

  public ArrayList<Entry> getEntries() {
    return sourceCodeEntries;
  }

  public void setDebuggerRunningStatus(boolean flag) {
    this.dvmIsRunning = flag;
  }

  public FunctionEnvironmentRecord environmentRecordPeek() {
    return functionEnvironmentStack.peek();
  }

  public void popEnvironmentRecord(int pop) {
    functionEnvironmentStack.peek().pop(pop);
  }

  public void popEnvironmentStack() {
    functionEnvironmentStack.pop();
  }

  public int getEntriesSize() {
    return sourceCodeEntries.size();
  }

  public int getLineNumber(int index) {
    return sourceCodeEntries.get(index).getLineNumber();
  }

  public String getSourceLine(int index) {
    return sourceCodeEntries.get(index).getSourceLine();
  }

  public boolean hasBreakpoint(int index) {
    return sourceCodeEntries.get(index).getIsBreakPoint();
  }

  public void setBreakPoint(Boolean flag, int index) {
    sourceCodeEntries.get(index).setIsBreakPoint(flag);
  }
}

class Entry {
  private int lineNumber;
  private String sourceLine;
  private Boolean isBreakPoint;

  public int getLineNumber() {
    return lineNumber;
  }

  public Boolean getIsBreakPoint() {
    return isBreakPoint;
  }

  public void setIsBreakPoint(Boolean isBreakPoint) {
    this.isBreakPoint = isBreakPoint;
  }

  public String getSourceLine() {
    return sourceLine;
  }

  public void setSourceLine(String sourceLine) {
    this.sourceLine = sourceLine;
  }

  public void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }
}