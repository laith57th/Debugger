package interpreter.bytecode.debuggercodes;

import java.util.ArrayList;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class LineCode extends ByteCode{
 int lineNumber;

 @Override
 public void init(ArrayList<String> args){
  if(Integer.parseInt(args.get(0)) != -1){
   lineNumber = Integer.parseInt(args.get(0));
  }
 }

 @Override
 public void execute(VirtualMachine vm){
  DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
  dvm.functionEnvironmentStack.peek().setCurrentLineNumber(lineNumber);
 }
 
 public int getLineNumber(){
  return lineNumber;
 }
}
