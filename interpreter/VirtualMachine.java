/**
 * DO NOT provide a method that returns components contained WITHIN the VM (this 
 * is the exact situation that will break encapsulation) - you should request 
 * that the VM performs operations on its components. This implies that the VM 
 * owns the components and is free to change them, as needed, without breaking 
 * clients' code (e.g., suppose I decide to change the name of the variable that 
 * holds my runtime stack - if your code had referenced that variable then your 
 * code would break. This is not an unusual situation - you can consider the 
 * names of methods in the Java libraries that have been deprecated).
 * 
 * Consider that the VM calls the individual ByteCodes' execute method and 
 * passes itself as a parameter. For the ByteCode to execute, it must invoke 
 * one or more methods in the runStack. It can do this by executing 
 * VM.runStack.pop(); however, this does break encapsulation. To avoid this, 
 * you'll need to have a corresponding set of methods within the VM that do 
 * nothing more than pass the call to the runStack. e.g., you would want to 
 * define a VM method:
 *     public int popRunStack() {
 *       return runStack.pop();
 *     }
 * called by, e.g.,
 *     int temp = VM.popRunStack();
 */
package interpreter;

import java.util.Stack;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.debuggercodes.FormalCode;
import interpreter.bytecode.debuggercodes.FunctionCode;
import interpreter.bytecode.debuggercodes.LineCode;

public class VirtualMachine {

  private int pc;
  private RunTimeStack runTimeStack;
  // This may not be the right parameterized type!!
  private Stack<Integer> returnAddresses;
  private boolean isRunning;
  private Program program;
  private boolean dumpIsOn = false;

  public VirtualMachine(Program program) {
    this.program = program;
    this.pc = 0;
  }

  public void executeProgram() {
    runTimeStack = new RunTimeStack();
    returnAddresses = new Stack<>();
    isRunning = true;

    while (isRunning) {
      ByteCode code = program.getCode(pc);
      while(code instanceof FunctionCode || code instanceof FormalCode || code instanceof LineCode){
        pc++;
        code = program.getCode(pc);
      } 

      code.execute(this);
      pc++;
      if (dumpIsOn && pc > 0) {
        runTimeStack.dump();
      }
    }
  }

  public void Initialize(){
    runTimeStack = new RunTimeStack();
    returnAddresses = new Stack<>();
  }

  public boolean getRunningStatus() {
    return isRunning;
  }

  public void setRunningStatus(boolean status) {
    this.isRunning = false;
  }

  public boolean dumpIsOn() {
    return dumpIsOn;
  }

  public void setDump(boolean status) {
    this.dumpIsOn = status;
  }

  public RunTimeStack getRunStack() {
    return this.runTimeStack;
  }

  public int getpc() {
    return pc;
  }

  public void setpc(int newpc) {
    this.pc = newpc;
  }

  public Stack<Integer> getReturnAddresses() {
    return this.returnAddresses;
  }
}