package interpreter.bytecode.debuggercodes;
import java.util.ArrayList;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class FunctionCode extends ByteCode {
 private int start, end;
 private String functionName;

 @Override
 public void init(ArrayList<String> args) {
  functionName = args.get(0);
  start = Integer.parseInt(args.get(1));
  end = Integer.parseInt(args.get(2));
 }

 @Override
 public void execute(VirtualMachine vm){
  DebuggerVirtualMachine dvm = (DebuggerVirtualMachine) vm;
  dvm.environmentRecordPeek().setFunctionInfo(functionName, start, end);
 }
}
