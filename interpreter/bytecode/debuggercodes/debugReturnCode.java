package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ReturnCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class debugReturnCode extends ReturnCode {
 public void execute(VirtualMachine vm) {
  DebuggerVirtualMachine dvm = (DebuggerVirtualMachine) vm;

  super.execute(vm);
  dvm.popEnvironmentStack();
 }
}
